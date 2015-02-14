<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
<!--

//-->
$(document).ready(function(){
	
$('#dg').datagrid({    
    url:"${basePath}/sys/department/sys-department!list.action",    
    columns:[[    
        {field:'id',title:'Code',width:100},    
        {field:'name',title:'Name',width:100},    
        {field:'description',title:'Price',width:100,align:'right'}    
    ]]    
});  
});
</script>
</head>
<table id="dg" class="easyui-datagrid" ></table>  
</body>
</html>