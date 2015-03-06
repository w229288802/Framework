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

var initTable = function(ctx,tableid,column,operation,pageNum,numPerPage,panel){
	var p = navTab.getCurrentPanel();
	column.push(operation);
	var input = $("input",p).serialize();
	$().ajaxUrl({type:'POST',url:ctx+"!listTable.action",data:input,callback:function(data){
	 	$("#"+tableid,p).table(data,columns,"ids","id");
	 	$("#"+tableid,p).jTable();
	 	$(":checkbox.checkboxCtrl",p).checkbox();
	 	//initUI();不能全部重新初始化，否则参数不对
	 	$("a[target=dialog]",p).bindDialog();
	 	$(".pagination",p).pagination({rel:panel,totalCount:data.totals,currentPage:pageNum,numPerPage:numPerPage});
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