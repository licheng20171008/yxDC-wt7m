<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="js/jquery/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/styles.css">
<title>武汉亚洲心脏病医院</title>
</head>
<body>
	<div class="htmleaf-container">
		<div class="wrapper">
			<div class="container">
				<h1>Welcome</h1>
				<form id="init" class="form" method="post">
					<input type="hidden" name="hiddenValue" value="0" /> 
					<input type="submit" onclick="submitForm(0)" value="欢迎进入指标添加" /> <br /> <br />
					<input type="submit" onclick="submitForm(1)" value="批量指标导入" /> <br /> <br /> 
					<input type="submit" onclick="submitForm(2)" value="欢迎进入指标维护" />
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
	function submitForm(value) {
		if (value == '0') {
			$("#init").attr("action", "Init.action");
		} else if (value == '1') {
			$("#init").attr("action", "fileUpload.action");
		} else if (value == '2') {
			$("#init").attr("action", "Report.action");
		} else if (value == '3') {
			$("#init").attr("action", "Export.action");
		}
	}
</script>
</html>