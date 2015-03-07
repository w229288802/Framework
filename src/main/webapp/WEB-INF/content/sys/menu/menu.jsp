<%@ include  file="/common/taglib.jsp"%>
<style type="text/css">
	ul.rightTools {float:right; display:block;}
	ul.rightTools li{float:left; display:block; margin-left:5px}
</style>
<style>
<!--
-->
</style>
<script type="text/javascript">
var setting = {
		view: {
			selectedMulti: false
		},
		edit: {
			enable: true,
			showRemoveBtn: true,
			showRenameBtn: false
		},
		data: {
			keep: {
				parent:true,
				leaf:true
			},
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeDrag: beforeDrag,
			beforeRemove: beforeRemove,
			beforeRename: beforeRename,
			onRemove: onRemove,
			onClick:onClick
		}
	};

	function onClick(event, treeId, treeNode){
		/* $("#jbsxBox").loadUrl("${base}/sys/department/department.action", {}, function(){
		//	$("#jbsxBox").find("[layoutH]").layoutH();
		}); */ 
		$("input").eq(1).focus(function(){this.select();});
		var input = $("input").serialize();
		$().ajaxUrl({
			url:"${actionPath}!input.action?id="+treeNode.id,
			callback:function(data){
				$("input").each(function(){
					this$=$(this);
					var val = data[this$.attr("name")];
					if(val){
						this$.val(val);
					}
				});
			}
		});
	}
	var log, className = "dark";
	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	var index_zTree = $.fn.zTree.getZTreeObj("index_tree");
	function beforeRemove(treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		var index_zTree_node = index_zTree.getNodeByTId(treeNode.tId);
		alertMsg.confirm('确认删除菜单吗',{okCall:function(){
			$().ajaxUrl({
				url:"${actionPath}!delete.action?ids="+treeNode.id,
			});
			index_zTree.removeNode(index_zTree_node);
			zTree.removeNode(treeNode);
		}});
		return false;
	}
	function onRemove(e, treeId, treeNode) {
		showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
	}
	function beforeRename(treeId, treeNode, newName) {
		if (newName.length == 0) {
			alert("节点名称不能为空.");
			var zTree = $.fn.zTree.getZTreeObj("sys_menu_tree");
			setTimeout(function(){zTree.editName(treeNode);}, 10);
			return false;
		}
		return true;
	}
	function showLog(str) {
		if (!log) log = $("#log");
		log.append("<li class='"+className+"'>"+str+"</li>");
		if(log.children("li").length > 8) {
			log.get(0).removeChild(log.children("li")[0]);
		}
	}
	function getTime() {
		var now= new Date(),
		h=now.getHours(),
		m=now.getMinutes(),
		s=now.getSeconds(),
		ms=now.getMilliseconds();
		return (h+":"+m+":"+s+ " " +ms);
	}

	var newCount = 1;
	
	function saveMemu(event){
		var zTree = $.fn.zTree.getZTreeObj("sys_menu_tree");
		var index_tree=$.fn.zTree.getZTreeObj("index_tree");
		var $form =$("#sys_menu_form");
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		var data=$form.serialize()+"&pid="+treeNode.pId;
		if (!$form.valid()) {
			return ;
		}
		$().ajaxUrl({
			type:'POST',
			data:data,
			url:"${actionPath}!saveAndReturn.action",
			callback:function(data){
				var menuZtree_node=zTree.getNodeByParam("id",data.id);
				var index_tree_node=index_tree.getNodeByParam("id",data.id);
				$("input").each(function(){
					this$=$(this);
					 menuZtree_node[this$.attr("name")]=this$.val();
					 index_tree_node[this$.attr("name")]=this$.val();
				});
				zTree.updateNode(menuZtree_node);
				index_tree.updateNode(index_tree_node);
			}
		});
	}
	function add(e) {
		var zTree = $.fn.zTree.getZTreeObj("sys_menu_tree");
		var index_tree=$.fn.zTree.getZTreeObj("index_tree");
		isParent = e.data.isParent,
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		var $form =$("#sys_menu_form");
		if (!$form.valid()) {
			return ;
		}
		if(!treeNode){
			alertMsg.info('请选中父菜单');
			return;
		}
		var data=$form.find("input[name!=id]").serialize()+"&pid="+treeNode.id;
		var index_zTree_node = index_zTree.getNodeByTId(treeNode.tId);
		if(!treeNode.isParent){
			alertMsg.info('该菜单不是父菜单');
			return;
		}
		$().ajaxUrl({
			type:'POST',
			data:data,
			url:"${actionPath}!saveAndReturn.action",
			callback:function(data){
				$("input").each(function(){
					this$=$(this);
					var val = data[this$.attr("name")];
					if(val){
						this$.val(val);
					};
				});
				index_tree.addNodes(index_zTree_node,{id:data.id, pId:data.pid, isParent:isParent, name:data.name});
				treeNode = zTree.addNodes(treeNode, {id:data.id, pId:data.pid, isParent:isParent, name:data.name});
			}
		});
	};
	function edit() {
		var zTree = $.fn.zTree.getZTreeObj("sys_menu_tree"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		if (nodes.length == 0) {
			alert("请先选择一个节点");
			return;
		}
		zTree.editName(treeNode);
	};
	function remove(e) {
		var zTree = $.fn.zTree.getZTreeObj("sys_menu_tree"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		if (nodes.length == 0) {
			alertMsg.info("请先选择一个节点");
			return;
		}
		var callbackFlag = $("#callbackTrigger").attr("checked");
		zTree.removeNode(treeNode, callbackFlag);
	};
	function clearChildren(e) {
		var zTree = $.fn.zTree.getZTreeObj("sys_menu_tree"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		if (nodes.length == 0 || !nodes[0].isParent) {
			alert("请先选择一个父节点");
			return;
		}
		zTree.removeChildNodes(treeNode);
	};
	
	$(document).ready(function(){
		$().ajaxUrl({
			url:'${actionPath}!listAll.action',
			callback:function(data){
				$.each(data,function(){
					this.pId=this.pid;
					this.open=true;
					this.isParent=this.type=='F'?false:true;
				});
				$.fn.zTree.init($("#sys_menu_tree"), setting, data);
				$("#addParentNode").bind("click", {isParent:true}, add);
				$("#addLeaf").bind("click", {isParent:false}, add);
				$("#edit").bind("click", edit);
				$("#remove").bind("click", remove);
				$("#saveMemu").bind("click", saveMemu);
				$("#clearChildren").bind("click", clearChildren);
			}
		});
	});
	
</script>
<div class="pageContent" >

	<div class="panel" defH="50" >
			<h1>添加菜单</h1>
			<div>
				<form  id="sys_menu_form" >
				<input type="hidden" name="id" value="${id }"/>
				名字：<input type="text" name="name" value="${name }" class="required"/>
				类型：<input type="text" name="type" value="${type}"/>
				编码：<input type="text" name="code" value="${code}"/>
				地址：<input type="text" name="url" value="${url}" size="60"/>
				</form>
				<ul >
					<li><a id="addParentNode" class="button" href="#"><span>添加父菜单</span></a></li>
					<li><a id="addLeaf" class="button" href="#"><span>添加子菜单</span></a></li>
					<li><a id="saveMemu" class="button" href="#"><span>保存菜单</span></a></li>
				</ul>
			</div>
	</div>
	<div class="panel" defH="415">
		<h1>自定义菜单功能</h1>
		<div  style="float:left;  overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
			<div class="pageContent">
				<ul  id="sys_menu_tree" class="ztree"></ul>
			</div>
		</div>
		<div id="jbsxBox" class="unitBox" style="margin-left:246px">
		</div>
	</div>
</div>
	


	

