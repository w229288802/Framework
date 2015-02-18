<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="${base }/resources/widget/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${base }/resources/widget/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${base }/resources/widget/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>

<script src="${base }/resources/widget/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${base }/resources/widget/dwz/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${base }/resources/widget/dwz/bin/dwz.min.js"  type="text/javascript"></script>

<title>Insert title here</title>
<link rel="stylesheet" href="${base}/resources/widget/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${base}/resources/widget/ztree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${base}/resources/widget/ztree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="${base}/resources/widget/ztree/js/jquery.ztree.exedit-3.5.js"></script>
	<script type="text/javascript" src="${base}/resources/app/menu/ztree.js"></script>
		

</head>
<script type="text/javascript">
$(function(){
	DWZ.init("${base }/resources/widget/dwz/dwz.frag.xml", {
		loginUrl:"login.html",
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		keys: {statusCode:"statusCode", message:"message"}, //【可选】
		ui:{hideMode:'offsets'}, //【可选】hideMode:navTab组件切换的隐藏方式，支持的值有’display’，’offsets’负数偏移位置的值，默认值为’display’
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"});
			
		}
	});
	DWZ.ajaxError=function(xhr, ajaxOptions, thrownError){
		if (alertMsg) {
			if(xhr.status==0){
				alertMsg.error("<div>世界上最遥远的距离就是没网</div>")
			}else if(xhr.status==404){
				alertMsg.error("<div>唉哟！没有找到页面！</div>")
			}else if(xhr.status==500){
				alertMsg.error("<div>服务器出错了</div><hr/><div>出错信息:"+xhr.responseText+"</div>");
			}else{
				alertMsg.error("<div>Http状态: " + xhr.status + " " + xhr.statusText + "</div>" 
				/* + "<div>设置: "+ajaxOptions + "</div>"
				+ "<div>异常: "+thrownError + "</div>" */
				+"<hr/>"
				+ "<div>"+xhr.responseText+"</div>");
			}
		} else {
			alert("Http status: " + xhr.status + " " + xhr.statusText + "\najaxOptions: " + ajaxOptions + "\nthrownError:"+thrownError + "\n" +xhr.responseText);
		}
	}
});
$(document).ready(function(){
	var columns=[
	        {field:'name'},
	        {field:'description'}
	        ];
	$.fn.datagrid=function(columns,data){
		
	};
});
</script>

<body scroll="no">

	<div id="layout">
		<div id="header">
			<div class="headerNav">
				
			</div>
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>
				<div class="accordion" fillSpace="sidebar">
					<div class="accordionContent">
					<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<div class="alertInfo">
								<p><a href="https://code.csdn.net/dwzteam/dwz_jui/tree/master/doc" target="_blank" style="line-height:19px"><span>DWZ框架使用手册</span></a></p>
								<p><a href="http://pan.baidu.com/s/18Bb8Z" target="_blank" style="line-height:19px">DWZ框架开发视频教材</a></p>
							</div>
							<div class="right">
							<p>待办工作32项，消息212条</p>
							<p>07月12日，星期二</p>
							</div>
							<p><span>DWZ富客户端框架 </span></p>
							<p><a href="demo_page2.html" target="dialog">DWZ小组</a></p>
						</div>
						<div class="pageFormContent" layoutH="80" style="margin-right:230px">
						<h2>标题</h2>
						<p>内容</p>
						</div>
				</div>			
			</div>
		</div>

	</div>
	<div id="footer">Copyright &copy; 2010 <a href="demo_page2.html" target="dialog">DWZ团队</a> 京ICP备05019125号-10</div>
	</div>
</body>
</html>