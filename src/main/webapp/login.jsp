<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.springframework.security.web.WebAttributes;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring 认证登录</title>

</head>
<script type="text/javascript">
	window.onload=function(){
		document.getElementsByName("j_username")[0].focus();
	};
	/* if(window.document.parent!=undefined&&window.document.parent!=window.document){
		window.location="${base}/login.jsp";
	} */
</script>
<body>
	<div class="panel">
	<span style="color:red">${sessionScope.WebAttributes.AUTHENTICATION_EXCEPTION}
	</span>
	<form action="j_spring_security_check" method="post">
   	账号：<input name="j_username" /><br/>
   	密码：<input name="j_password" type="password"/><br/>
   	<input value="提交" type="submit"/>
   </form>
	</div>
</body>
</html>