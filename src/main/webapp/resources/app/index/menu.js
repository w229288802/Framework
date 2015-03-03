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
	navTab.openTab(treeNode.name,ctx+treeNode.url,{title:treeNode.name, fresh:false, data:{} });
	event.preventDefault();
}

$(document).ready(function(){
	$().loadUrl(ctx+"/sys/menu/menu!listAjax.action",null,function(data){
		$.each(data,function(){
			this.pId=this.pid; 
			if(this.pid==null||this.pid==""){
				this.open=true;
			}
		});
		$.fn.zTree.init($("#treeDemo"), setting, data);
	});
});
	
