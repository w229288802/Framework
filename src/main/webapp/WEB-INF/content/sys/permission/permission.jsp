<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<link rel="stylesheet" href="${base}/resources/widget/treetable/css/jquery.treetable.theme.default.css" type="text/css">
<link rel="stylesheet" href="${base}/resources/widget/treetable/css/jquery.treetable.css" type="text/css">
<script type="text/javascript" src="${base}/resources/widget/treetable/jquery.treetable.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var operation ={name:'操作',align:'center',formatter:function(val,index,row){
		var opt = '<a class="add"  target="dialog" href="${actionPath}!edit.action?id='+row.id+'"  mask="true"><span>修改</span></a>&nbsp';
		opt += '<a class="updae" width="800" height="480" target="dialog" href="${base}/sys/menu/menu.action?id='+row.id+'"  mask="true"><span>授权</span></a>';
		return opt;
	}};
	var columns=[
	 	        {field:'name',name:'菜单名',align:'center'},
	 	        {field:'type',name:'类型',align:'center'},
	 	        {field:'url',name:'地址',align:'center'},
	 	        {field:'description',name:'描述',align:'center'},
	];
	//initTable("${actionPath}","sys_menu_treetable",columns,operation,"${pageNum}","${numPerPage}");
	
	$().ajaxUrl({type:'POST',url:"${actionPath}!listTable.action?id=${id}",callback:function(data){
	 	$("#sys_menu_treetable").myTreeTable({data:data,columns:columns,ids:"ids",id:"id",pid:"pid"});
		$("#sys_menu_treetable").treetable({ expandable: true });
		$("#sys_menu_treetable").treetable("expandAll");
		$(":checkbox.checkboxCtrl",DWZ.getPanel()).checkbox();
	}});
	
});

</script>
<div class="pageContent">
	<form method="post" onsubmit="return validateCallback(this,dialogAjaxDone);" class="pageForm required-validate" action="${actionPath}!grant.action" >
	
	<input name="id" type="hidden"  value="${id }" />
	
	<div class="pageFormContent" layoutH="56">
		<table id="sys_menu_treetable"  width="100%" ></table>
	</div>
	
	<!-- 操作 -->
	<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确认授权</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
