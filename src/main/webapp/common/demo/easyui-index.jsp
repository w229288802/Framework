<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>这是首页</title>

	<%-- <link rel="stylesheet" href="${base}/resources/widget/ztree/css/demo.css" type="text/css"> --%>
	<link rel="stylesheet" href="${base}/resources/widget/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${base}/resources/widget/ztree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${base}/resources/widget/ztree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="${base}/resources/widget/ztree/js/jquery.ztree.exedit-3.5.js"></script>
	<SCRIPT type="text/javascript">
		<!--
		var setting = {
			view: {
				selectedMulti: false
			},
			edit: {
				enable: true,
				showRemoveBtn: false,
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
				beforeClick:beforeClick,
				onClick:onClick
			}
			
		};

		var zNodes =[
			{ id:1, pId:0, name:"父节点 1", open:true},
			{ id:11, pId:1, name:"叶子节点 1-1",target:"${base}/resources/widget/ztree/css/zTreeStyle/zTreeStyle.css"},
			{ id:12, pId:1, name:"叶子节点 1-2"},
			{ id:13, pId:1, name:"叶子节点 1-3"},
			{ id:2, pId:0, name:"父节点 2", open:true},
			{ id:21, pId:2, name:"叶子节点 2-1"},
			{ id:22, pId:2, name:"叶子节点 2-2"},
			{ id:23, pId:2, name:"叶子节点 2-3"},
			{ id:3, pId:0, name:"父节点 3", open:true},
			{ id:31, pId:3, name:"叶子节点 3-1"},
			{ id:32, pId:3, name:"叶子节点 3-2"},
			{ id:33, pId:3, name:"叶子节点 3-3"}
		];
		var log, className = "dark";
		function beforeClick(treeId, treeNode, clickFlag) {
			className = (className === "dark" ? "":"dark");
			showLog("[ "+getTime()+" beforeClick ]&nbsp;&nbsp;" + treeNode.name );
			return (treeNode.click != false);
		}
		function onClick(event, treeId, treeNode, clickFlag) {
			alert(treeNode.target);
			$("#sys_department_tabs").tabs("add",{title: '新选项卡面板',href:treeNode.target});
			//showLog("[ "+getTime()+" onClick ]&nbsp;&nbsp;clickFlag = " + clickFlag + " (" + (clickFlag===1 ? "普通选中": (clickFlag===0 ? "<b>取消选中</b>" : "<b>追加选中</b>")) + ")");
		}
		function beforeDrag(treeId, treeNodes) {
			return false;
		}
		function beforeRemove(treeId, treeNode) {
			className = (className === "dark" ? "":"dark");
			showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
			return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
		}
		function onRemove(e, treeId, treeNode) {
			showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		}
		function beforeRename(treeId, treeNode, newName) {
			if (newName.length == 0) {
				alert("节点名称不能为空.");
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
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
		function add(e) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			isParent = e.data.isParent,
			nodes = zTree.getSelectedNodes(),
			treeNode = nodes[0];
			if (treeNode) {
				treeNode = zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, isParent:isParent, name:"new node" + (newCount++)});
			} else {
				treeNode = zTree.addNodes(null, {id:(100 + newCount), pId:0, isParent:isParent, name:"new node" + (newCount++)});
			}
			if (treeNode) {
				zTree.editName(treeNode[0]);
			} else {
				alert("叶子节点被锁定，无法增加子节点");
			}
		};
		function edit() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			treeNode = nodes[0];
			if (nodes.length == 0) {
				alert("请先选择一个节点");
				return;
			}
			zTree.editName(treeNode);
		};
		function remove(e) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			treeNode = nodes[0];
			if (nodes.length == 0) {
				alert("请先选择一个节点");
				return;
			}
			var callbackFlag = $("#callbackTrigger").attr("checked");
			zTree.removeNode(treeNode, callbackFlag);
		};
		function clearChildren(e) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			treeNode = nodes[0];
			if (nodes.length == 0 || !nodes[0].isParent) {
				alert("请先选择一个父节点");
				return;
			}
			zTree.removeChildNodes(treeNode);
		};
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			$("#addParent").bind("click", {isParent:true}, add);
			$("#addLeaf").bind("click", {isParent:false}, add);
			$("#edit").bind("click", edit);
			$("#remove").bind("click", remove);
			$("#clearChildren").bind("click", clearChildren);
		});
		//-->
	</SCRIPT>

</head>
<body class="easyui-layout">

	<div data-options="region:'north',border:false" style="height:60px;padding:10px">north region</div>
	<div data-options="region:'west',split:true,title:'系统菜单'" style="width:150px;">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	<!-- <div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div> -->
	<!-- <div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div> -->
	<div id="sys_department_tabs" class="easyui-tabs" data-options="region:'center'">

	  <div title="Tab1" style="padding:20px;display:none;">   
        tab1    
    </div>   
    <div title="Tab2" data-options="closable:true" style="overflow:auto;padding:20px;display:none;">   
        tab2    
    </div>   
    <div title="Tab3" data-options="iconCls:'icon-reload',closable:true" style="padding:20px;display:none;">   
        tab3    
    </div>  

	<!-- <div class="right">
		<ul class="info">
			<li class="title"><h2>1、addNodes / editName / removeNode / removeChildNodes 方法操作说明</h2>
				<ul class="list">
				<li>利用 addNodes / editName / removeNode / removeChildNodes 方法也可以实现 增 / 删 / 改 节点的目的，这里简单演示使用方法</li>
				<li>cancelEditName 方法仅仅是在节点进入名称编辑状态时有效，请在必要时使用，Demo 不进行此方法的演示</li>
				<li class="highlight_red">利用 setting.data.keep.parent / leaf 属性 实现了父节点、叶子节点的状态锁定</li>
				<li><p>对节点进行 增 / 删 / 改，试试看：<br/>
					&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="callbackTrigger" checked /> removeNode 方法是否触发 callback<br/>
					&nbsp;&nbsp;&nbsp;&nbsp;[ <a id="addParent" href="#" title="增加父节点" onclick="return false;">增加父节点</a> ]
					&nbsp;&nbsp;&nbsp;&nbsp;[ <a id="addLeaf" href="#" title="增加叶子节点" onclick="return false;">增加叶子节点</a> ]
					&nbsp;&nbsp;&nbsp;&nbsp;[ <a id="edit" href="#" title="编辑名称" onclick="return false;">编辑名称</a> ]<br/>
					&nbsp;&nbsp;&nbsp;&nbsp;[ <a id="remove" href="#" title="删除节点" onclick="return false;">删除节点</a> ]
					&nbsp;&nbsp;&nbsp;&nbsp;[ <a id="clearChildren" href="#" title="清空子节点" onclick="return false;">清空子节点</a> ]<br/>
					remove log:<br/>
					<ul id="log" class="log"></ul>
				</li>
				<li class="highlight_red">使用 zTreeObj.addNodes / cancelEditName / editName / removeNode / removeChildNodes 方法，详细请参见 API 文档中的相关内容</li>
				</ul>
			</li>
			<li class="title"><h2>2、setting 配置信息说明</h2>
				<ul class="list">
				<li>同 "基本 增 / 删 / 改 节点"</li>
				<li class="highlight_red">保持 父 / 叶子 节点状态，需要设置 setting.data.keep.parent / leaf 属性，详细请参见 API 文档中的相关内容</li>
				</ul>
			</li>
			<li class="title"><h2>3、treeNode 节点数据说明</h2>
				<ul class="list">
				<li>同 "基本 增 / 删 / 改 节点"</li>
				</ul>
			</li>
		</ul>
	</div> -->
</div>
</body>
</html>