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
	$.ajax(ctx+"/sys/department/department!list.action?pageNum=${pageNum}",{
		success:function(data){
		 	$("#table1").table(columns,data);
		 	$("#table1").jTable();
		},
		error:DWZ.ajaxError
     });
</script>
<%@ include file="/common/taglib.jsp" %>
<c:set var="baseAction" value="${base }/sys/department/department"></c:set>
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
	<table id="table1" width="100%" layoutH="180">
	</table>

	<%@ include file="/common/pageable.jspf" %>
</div>