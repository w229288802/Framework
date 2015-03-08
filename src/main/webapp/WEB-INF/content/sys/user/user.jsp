<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<script>
var operation ={name:'操作',align:'center',formatter:function(val,index,row){
 	var opt = '<a class="add" target="dialog" href="${actionPath}!edit.action?id='+row.id+'"  mask="true"><span>修改</span></a>&nbsp';
 	opt += '<a class="updae" rel="a2" width="800" height="300"  target="dialog" href="${base}/sys/role/role.action?isForm=true&id='+row.id+'"  mask="true"><span>授权</span></a>';
 	return opt;
}};
var columns=[
 	        {field:'username',name:'用户名',align:'center'},
 	        {field:'name',name:'姓名',align:'center'},
 	        {field:'phone',name:'手机',align:'center'},
 	        {field:'email',name:'邮件',align:'center'},
 	        {field:'department',name:'部门',align:'center'},
];

$(document).ready(function(){
	initTable("${actionPath}","sys_user_table",columns,operation,"${pageNum}","${numPerPage}");
});

</script>

<form id="pagerForm" action="${actionPath}.action" method="post">
	<input type="hidden" name="pageNum" value="${pageNum }" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="${numPerPage }" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="orderField" value="${orderField }" /><!--【可选】查询排序-->
<input type="hidden" name="orderDirection" value="${orderDirection }" /><!--【可选】升序降序-->
</form> 
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="demo_page1.html" method="post">
	<div class="searchBar">
		<!--<ul class="searchContent">
			<li>
				<label>我的客户：</label>
				<input type="text"/>
			</li>
			<li>
			<select class="combox" name="province">
				<option value="">所有省市</option>
				<option value="北京">北京</option>
				<option value="上海">上海</option>
				<option value="天津">天津</option>
				<option value="重庆">重庆</option>
				<option value="广东">广东</option>
			</select>
			</li>
		</ul>
		-->
		<table class="searchContent">
			<tr>
				<td>
					我的客户：<input type="text" name="keyword" />
				</td>
				<td>
					<select class="combox" name="province">
						<option value="">所有省市</option>
						<option value="北京">北京</option>
						<option value="上海">上海</option>
						<option value="天津">天津</option>
						<option value="重庆">重庆</option>
						<option value="广东">广东</option>
					</select>
				</td>
				<td>
					建档日期：<input type="text" class="date" readonly="true" />
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>
			</ul>
		</div>
	</div>
	</form>
</div>
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
	
	<table id="sys_user_table" width="100%" layoutH="138"></table>
	<div class="panelBar">
			
		<s:if test="%{#parameters.isForm[0]}">
		</s:if>
		<s:else>
		<div class="pages">
			<span>显示</span>
			<select ns="pageable_select" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共<label id="_totals"></label>条</span>
		</div>
		</s:else>
	
		<div class="pagination" targetType="dialog" totalCount="" numPerPage="" pageNumShown="" currentPage="">
		</div>
	</div>
</div>
