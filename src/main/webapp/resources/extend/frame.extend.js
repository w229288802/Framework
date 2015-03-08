/**
 * 下载文件
 */
function download_file(url)
{
	if (typeof (download_file.iframe) == "undefined")
	{
		var iframe = document.createElement("iframe");
		download_file.iframe = iframe;
		document.body.appendChild(download_file.iframe);
	}
	download_file.iframe.src = url;
	if (download_file.iframe.attachEvent){
		download_file.iframe.attachEvent (function() {
    		handlerAjax($.parseJSON(download_file.iframe.contentDocument.body.innerHTML));
   		});
	}else{
		download_file.iframe.onload = function() {
    		handlerAjax($.parseJSON(download_file.iframe.contentDocument.body.innerHTML));
   		}
	}
	//document.body.removeChild(iframe);移除
	download_file.iframe.style.display = "none";
} 
$.fn.myTreeTable=function(op){
	var p = DWZ.getPanel();
	var checkboxKey=checkboxKey?op.id:'id';
	var checkboxField=checkboxField?op.ids:'ids';
	var pid=op&&op.pid;
	var $grid = $(this);
	var html = '<thead><tr>';
	$.each(op.columns,function(){
		html+="<th align='center'>"+this.name+"</th>";
	});
	if(checkboxField){   
		html+='<th align="center" width="25"><input class="checkboxCtrl"  group="'+checkboxField+'" type="checkbox"></th>';
	}
	html += '</tr></thead>';
	html +='<tbody>';
	var isAll = true;
	$.each(op.data.rows,function(i){
		var row = this;
		var p= pid&&this[pid]!=null?'data-tt-parent-id="'+this[pid]+'"':'';
		html+='<tr data-tt-id="'+this[checkboxKey]+'" '+p+'>';
		$.each(op.columns,function(i){
			var align=this.align?' align="'+this.align+'"':'';
			var title=this.title?' title="'+this.title+'"':'';
			var val;
			if(this.formatter){
				val = this.formatter(row[this.field],i,row);
			}else{
				val = row[this.field];
			}
			html+='<td'+align+title+'>'+(val==null?'':val)+'</td>';
		}); 
		if(checkboxField!=undefined){
			var checked = this.checked_?"checked='checked'":"";
			if(this.checked_==0)isAll=false;
 			html+='<td><input name="'+checkboxField+'" type="checkbox" '+checked+' value="'+(row[checkboxKey]?row[checkboxKey]:'')+'"></td>';
 		}
		html+='</tr>';
	});
	html +='</tbody>';
	$grid.append(html); 
	if(isAll){
 		$(".checkboxCtrl",p).attr("checked","checked");
	}
 };
var println=function(o){
	for(var p in o){console.log(p + " : " + o[p]);} 
}
var initTable = function(ctx,tableid,column,operation,pageNum,numPerPage,targetType){
	var p = DWZ.getPanel();
	if($.pdialog.getCurrent()==null||$.trim($.pdialog.getCurrent().css("display"))=="none"){
		targetType ="navTab";
	}else{
		targetType="dialog";
	}
	column.push(operation);
	var input = $("input",p).serialize();
	$().ajaxUrl({type:'POST',url:ctx+"!listTable.action",data:input,callback:function(data){
	 	$("#"+tableid,p).table({data:data,columns:columns,ids:"ids",id:"id"});
	 	$("#"+tableid,p).jTable();
	 	$(":checkbox.checkboxCtrl",p).dwzCheckbox();
	 	//initUI();不能全部重新初始化，否则参数不对
	 	$("a[target=dialog]",p).bindDialog();
	 	$(".pagination",p).pagination({targetType:targetType,rel:'',totalCount:data.totals,currentPage:pageNum,numPerPage:numPerPage});
	 	$("#_totals",p).html(data.totals);
	 	$("select[ns=pageable_select]",p).selectBox(numPerPage);
	}});
};

var deleteByIds = function(ctx){
	var p = navTab.getCurrentPanel();
	if($(":checkbox.checkboxCtrl",p).attr("checked")){
		var pageNum = $("input[name='pageNum']",p).val();
		if(pageNum>1)
		$("input[name='pageNum']",p).val(pageNum-1);
	}
	alertMsg.confirm("你确定要删除吗",{okCall:function(){
		var selected = $(":checkbox:checked",p).serialize();
		$().ajaxUrl({
			type:'POST',
			url:ctx+"!delete.action",
			data:selected,
			callback:function(){
				navTab.reload(ctx+".action",p);
			}
		});
	}});
};
$.fn.checkbox=function(){
	var p = DWZ.getPanel();
	var parent =$(this);
	var name = parent.attr("group");
	var checkboxs = parent.parents("table").find(":checkbox[name='"+name+"']");
	$(this,p).click(function(){
		var checked = this.checked;
		checkboxs.each(function(){
			this.checked=checked;
			if(this.checked){
				$(this).next("input").remove();
			}else{
				$(this).after("<input type='hidden' name='"+name+"_' value='"+this.value+"'>");
			}
		});		
	});
	checkboxs.each(function(){
		if(!this.checked){
			$(this).after("<input type='hidden' name='"+name+"_' value='"+this.value+"'>");
		}
	
	});
	checkboxs.click(function(){
		var cs = parent.parents("table").find(":checkbox[name='"+name+"']");
		var checkedLength = cs.filter("input:checked").length;
		if(this.checked){
			$(this).next("input").remove();
		}else{
			$(this).after("<input type='hidden' name='"+name+"_' value='"+this.value+"'>");
		}
		if(checkedLength<cs.length){
			parent.attr("checked",false);
		}else if (checkedLength==cs.length){
			parent.attr("checked",true);
		}
	});
}
/*var Pagination = function(opts) {
		this.opts = $.extend({
			targetType:"navTab",	// navTab, dialog
			rel:"", //用于局部刷新div id号
			totalCount:0,
			numPerPage:10,
			pageNumShown:10,
			currentPage:1,
			callback:function(){return false;}
		}, opts);
	}*/