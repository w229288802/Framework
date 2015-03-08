<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<script>
var operation ={name:'操作',align:'center',formatter:function(val,index,row){
	var opt = '<a class="add"  target="dialog" href="${actionPath}!edit.action?id='+row.id+'"  mask="true"><span>修改</span></a>&nbsp';
	opt += '<a class="updae" width="800" height="480" target="dialog" href="${base}/sys/permission/permission.action?id='+row.id+'"  mask="true"><span>授权</span></a>';
	return opt;
}};
var columns=[
 	        {field:'name',name:'角色名',align:'center'},
 	        {field:'description',name:'描述',align:'center'},
];

$(document).ready(function(){
	initTable("${actionPath}","sys_user_table",columns,operation,"${pageNum}","${numPerPage}");
});

</script>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${actionPath}!edit.action" target="dialog"  mask="true"><span>添加</span></a></li>
			<li class="line">line</li>
			<li><a class="delete"  onclick="deleteByIds('${actionPath}')" title="确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="javascript:void(0);" onclick="download_file('${actionPath }!exportExcel.action')" <%-- href="${actionPath }!exportExcel.action" target="dwzExport" --%> targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table id="sys_user_table" width="100%" ></table>

	<%@ include file="/common/pageable.jspf" %>
</div>