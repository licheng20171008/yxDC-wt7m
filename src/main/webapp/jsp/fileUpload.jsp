<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<script type="text/javascript" src="js/jquery/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/styles.css">
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<title>武汉亚洲心脏病医院</title>
</head>
<%
	String message = (String) request.getAttribute("message");
%>
<body>
	<b id="message" style="color: #FF0000;"><%=message%></b>
	<div class="htmleaf-container">
		<div class="wrapper">
			<div class="container">
				<h1>选择文件</h1>
				<form id="fileUpload" class="form" method="post" enctype="multipart/form-data">
						<input id="filePath" type="file" class="file" name="file" />
						<input type="button" value="文件上传" onclick="buttonSubmit(1)" /> 
						<input type="button" value="返回首页" onclick="buttonSubmit(0)" />
				</form>
			</div>
			<ul class="bg-bubbles">
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
		</div>
	</div>
</body>
<script type="text/javascript">
	function buttonSubmit(value) {
		if (value == '0') {
			$("#fileUpload").attr("action", "index.jsp");
		} else if (value == '1') {
			$("#fileUpload").attr("action", "fileUpload.action");
		}
		$("#fileUpload").submit();
	}
</script>
</html>