<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
var operation ={name:'操作',align:'center',formatter:function(val,index,row){
 	return '<a class="add" target="dialog" href="${actionPath}!edit.action?id='+row.id+'"  mask="true"><span>修改</span></a>';
}};
var columns=[
 	        {field:'name',name:'名字',align:'center'},
 	        {field:'description',name:'描述'}
 	        ];
 	        
$(document).ready(function(){
	initTable("${actionPath}","sys_user_table",columns,operation,"${pageNum}","${numPerPage}");
});
</script>
<%@ include file="/common/taglib.jsp" %>
<c:set var="baseAction" value="${base }/sys/department/department"></c:set>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${actionPath}!edit.action" target="dialog"  mask="true"><span>添加</span></a></li>
			<li class="line">line</li>
			<li><a class="delete"  onclick="deleteByIds('${actionPath}')" title="确定要删除吗?"><span>删除</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="${actionPath }!exportExcel.action" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table id="sys_user_table" width="100%" ></table>

	<%@ include file="/common/pageable.jspf" %>
</div>