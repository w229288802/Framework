<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出错了</title>
</head>
<body>
<script language="javascript">
// 这个脚本是 ie6和ie7 通用的脚本
function custom_close(){
	if
	(
		confirm("您确定要关闭本页吗？")){
		window.opener=null;
		window.open('','_self');
		window.close();
	}
	else{}
}
</script>
<span><font color="FF0000">
出错信息：
</font>${errorInfo }
</span><br/>
<input id="btnClose" type="button" value="关闭本页" onClick="custom_close()" />
</body>
</html>