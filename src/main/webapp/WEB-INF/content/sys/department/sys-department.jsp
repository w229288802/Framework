<script>
var columns=[
 	        {field:'name',name:'名字',align:'center'},
 	        {field:'description',name:'描述'}
 	        ];
 	        
 	$.fn.table=function(columns,data){
 		var $grid = $(this);
 		var html = '<thead><tr>';
 		$.each(columns,function(){
 			html+="<th>"+this.name+"</th>";
 		});
 		html += '</tr></thead>';
 		html +='<tbody>';
 		$.each(data.rows,function(i){
 			var row = this;
 			html+='<tr>';
 			$.each(columns,function(i){
 				var align=this.align?' align="'+this.align+'"':'';
 				var title=this.title?' title="'+this.title+'"':'';
 				html+='<td'+align+title+'>'+row[this.field]+'</td>';
 			});
 			html+='</tr>';
 		});
 		html +='</tbody>';
 		$grid.append(html);
 	};
	$.ajax(ctx+"/sys/department/sys-department!list.action",{
		success:function(data){
		 	$("#table1").table(columns,data);
		 	$("#table1").jTable();
		}
     });
	//$("#table1").datagrid(columns);
</script>
<%@ include file="/common/taglib.jsp" %>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${base }/sys/menu/sys-menu!input.action" target="dialog" rel="dlg_page11" mask="true"><span>添加</span></a></li>
			<li><a class="delete" href="demo/common/ajaxDone.html?uid={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="demo_page4.html?uid={sid_user}" target="navTab"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table id="table1" width="100%" layoutH="300">
	</table>
	<form id="pagerForm" action="${base}/sys/department/sys-department.action" method="post">
	<input type="hidden" name="pageNum" value="1" /><!--【必须】value=1可以写死-->
	<input type="hidden" name="numPerPage" value="20" /><!--【可选】每页显示多少条-->
	<input type="hidden" name="orderField" value="xxx" /><!--【可选】查询排序-->
	<input type="hidden" name="orderDirection" value="asc" /><!--【可选】升序降序-->

	<!--【可选】其它查询条件，业务有关，有什么查询条件就加什么参数。
	也可以在searchForm上设置属性rel=”pagerForm”，js框架会自动把searchForm搜索条件复制到pagerForm中 -->
	<input type="hidden" name="name" value="xxx" />
	<input type="hidden" name="status" value="active" />
	</form> 
	
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="200" numPerPage="20" pageNumShown="10" currentPage="1"></div>

	</div>
</div>