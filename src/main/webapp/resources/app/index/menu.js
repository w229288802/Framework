var setting = {
	view: {
		selectedMulti: false
	},
	edit: {
		//enable: false,是否打开编辑
		showRemoveBtn: false,
		showRenameBtn: false
	},
	data: {
		keep: {
			parent:true,
			leaf:true
		},
		simpleData: {
			enable: true
		}
	},
	callback: {
		onClick:onClick
	}
};
function onClick(event, treeId, treeNode, clickFlag) {
	if($.trim(treeNode.url)!=""){
		navTab.openTab(treeNode.name,ctx+treeNode.url,{title:treeNode.name, fresh:false, data:{} });
	}
	event.preventDefault();
}

$(document).ready(function(){
	$().loadUrl(ctx+"/sys/permission/permission!listAjax.action",null,function(data){
		$.each(data,function(){
			this.pId=this.pid; 
			this.isParent=this.type=='F'?false:true;
			this.open=this.type=='F'?false:true;
		});
		$.fn.zTree.init($("#index_tree"), setting, data);
	});
});
	
