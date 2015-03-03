var initTable = function(ctx,tableid,pageNum,numPerPage){
	var p = navTab.getCurrentPanel();
	var input = $("input",p).serialize();
	$().ajaxUrl({type:'POST',url:ctx+"!listTable.action",data:input,callback:function(data){
	 	$("#"+tableid,p).table(data,columns,"ids","id");
	 	$("#"+tableid,p).jTable();
	 	$(":checkbox.checkboxCtrl",p).checkbox();
	 	//initUI();不能全部重新初始化，否则参数不对
	 	$("a[target=dialog]",p).bindDialog();
	 	$(".pagination",p).pagination({totalCount:data.totals,currentPage:pageNum,numPerPage:numPerPage});
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