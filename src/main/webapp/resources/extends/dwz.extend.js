DWZ.ajaxError=function(xhr, ajaxOptions, thrownError){
		if($.trim($.pdialog.getCurrent().css("display"))=="none"){
			navTab.closeCurrentTab();
		}else{
			$.pdialog.closeCurrent();
		}
		if (alertMsg) {
			if(xhr.status==0){
				alertMsg.error("<div>世界上最遥远的距离就是没网</div>");
			}else if(xhr.status==404){
				alertMsg.error("<div>唉哟！没有找到页面！</div>");
			}else if(xhr.status==501){
				alertMsg.error("<div>服务器出错了</div><hr/><div>出错信息:"+xhr.responseText+"</div>");
			}else{
				alertMsg.error("<div>Http状态: " + xhr.status + " " + xhr.statusText + "</div>" 
				+"<hr/>"
				+ "<div>"+xhr.responseText+"</div>");
			}
		} else {
			alert("Http status: " + xhr.status + " " + xhr.statusText + "\najaxOptions: " + ajaxOptions + "\nthrownError:"+thrownError + "\n" +xhr.responseText);
		}
};
$.fn.table=function(data,columns,checkboxField,checkboxKey){
		
	checkboxField=checkboxField?checkboxField:'ids';
	checkboxKey=checkboxKey?checkboxKey:'id';
	var $grid = $(this);
	var html = '<thead><tr>';
	if(checkboxField){   
		html+='<th width="25"><input class="checkboxCtrl"  group="'+checkboxField+'" type="checkbox"></th>';
	}
	$.each(columns,function(){
		html+="<th align='center'>"+this.name+"</th>";
	});
	html += '</tr></thead>';
	html +='<tbody>';
	$.each(data.rows,function(i){
		var row = this;
		html+='<tr>';
		if(checkboxField!=undefined){
 			html+='<td><input name="'+checkboxField+'" type="checkbox" value="'+(row[checkboxKey]?row[checkboxKey]:'')+'"></td>';
 		}
		$.each(columns,function(){
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
		html+='</tr>';
	});
	html +='</tbody>';
	$grid.append(html); 
 		
 };
$.fn.selectBox=function(numPerPage){
	if(numPerPage==undefined){
		alert('selectBox(numPerPage未定义)');
	}
	$(this).find("option:selected").removeAttr("selected");
	$(this).find("option").each(function(){
		if(numPerPage==this.innerHTML){
			$(this).attr("selected","selected");
		}
	});
	$(this).combox();
};
$.fn.bindDialog=function(){
	$(this).each(function(){
		$(this).unbind();
		$(this).click(function(event){
			var $this = $(this);
			var title = $this.attr("title") || $this.text();
			var rel = $this.attr("rel") || "_blank";
			var options = {};
			var w = $this.attr("width");
			var h = $this.attr("height");
			if (w) options.width = w;
			if (h) options.height = h;
			options.max = eval($this.attr("max") || "false");
			options.mask = eval($this.attr("mask") || "false");
			options.maxable = eval($this.attr("maxable") || "true");
			options.minable = eval($this.attr("minable") || "true");
			options.fresh = eval($this.attr("fresh") || "true");
			options.resizable = eval($this.attr("resizable") || "true");
			options.drawable = eval($this.attr("drawable") || "true");
			options.close = eval($this.attr("close") || "");
			options.param = $this.attr("param") || "";

			var url = unescape($this.attr("href")).replaceTmById($(event.target).parents(".unitBox:first"));
			DWZ.debug(url);
			if (!url.isFinishedTm()) {
				alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
				return false;
			}
			$.pdialog.open(url, rel, title, options);
			
			return false;
		});
	});
};
$.fn.checkbox=function(){
	var p = navTab.getCurrentPanel();
	$(this).checkboxCtrl(p);
	var parent =$(this);
	var name = parent.attr("group");
	var checkboxs = parent.parents(".grid").find(":checkbox[name='"+name+"']");
	checkboxs.click(function(){
		var cs = parent.parents(".grid").find(":checkbox[name='"+name+"']");
		var checkedLength = cs.filter("input:checked").length;
		if(checkedLength<cs.length){
			parent.attr("checked",false);
		}else if (checkedLength==cs.length){
			parent.attr("checked",true);
		}
	});
};