<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>

<script type="text/javascript">
<!--

//-->
$('#dg').datagrid({    
    url:'datagrid_data.json',    
    columns:[[    
        {field:'code',title:'Code',width:100},    
        {field:'name',title:'Name',width:100},    
        {field:'price',title:'Price',width:100,align:'right'}    
    ]]    
});  
</script>

<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<table id="dg"></table>  
</body>
</html>