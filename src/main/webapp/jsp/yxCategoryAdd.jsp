<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<script type="text/javascript" src="js/jquery/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/addstyle.css">
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.yx.dao.Type"%>
<%@ page import="com.yx.dao.Category"%>
<%@ page import="com.yx.dao.CategoryAdd"%>
<%@ page import="com.yx.dao.IndexDetail"%>
<%@ page import="com.yx.dao.Department"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>武汉亚洲心脏病医院</title>
</head>
<%
	CategoryAdd ca = (CategoryAdd) request.getAttribute("CategoryAdd");
	List<Category> catList = new ArrayList<Category>();
	List<Type> typeList = new ArrayList<Type>();
	if (ca.getCategoryList() == null) {
		catList = (List<Category>) session.getAttribute("catList");
	} else {
		catList = ca.getCategoryList();
		session.setAttribute("catList", catList);
	}
	if (ca.getTypeList() == null) {
		typeList = (List<Type>) session.getAttribute("typeList");
	} else {
		if ("0".equals(ca.getCategory_detailHidden())) {
			typeList = new ArrayList<Type>();
		} else {
			typeList = ca.getTypeList();
			session.setAttribute("typeList", typeList);
		}
	}
	if (ca.getCategory() == null) {
		ca.setCategory(new Category());
	}
	if (ca.getType() == null) {
		ca.setType(new Type());
	}
	if (ca.getIndexDetail() == null) {
		ca.setIndexDetail(new IndexDetail());
	}
	if (ca.getDepartment() == null) {
        ca.setDepartment(new Department());
    }
	String businessKey = ca.getBusinessKey();
	String message = ca.getMessage();
	String category_type = ca.getType().getCategory_type();
	String category_detail = ca.getIndexDetail().getCategory_detail();
	String type_detail = ca.getIndexDetail().getType_detail();
	String indexDetail = ca.getIndexDetail().getIndexDetail();
	String indexFormula = ca.getIndexDetail().getIndexFormula();
	String remark = ca.getIndexDetail().getRemark();
%>
<body>
	<div class="form_content">
		<span id="message"><strong><%=message%></strong></span>
		<form id="formId" method="post">
			<input type="hidden" id="category_detailHidden" name="CategoryAdd.category_detailHidden" />
			<fieldset>
				<legend>添加类型选择</legend>
				<div class="form-row">
					<div class="field-label">
						<label for="CategoryAdd.businessKey">请选择添加类型：</label>
					</div>
					<div class="field-widget">
						<select id="businessKey" name="CategoryAdd.businessKey" class="validate-selection" onchange="onaddType(1)">
							<option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="3">部门添加</option>
							<option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="0">一级指标分类添加</option>
							<option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="1">二级指标分类添加</option>
							<option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="2">指标明细添加</option>
						</select>
					</div>
				</div>
			</fieldset>
			<fieldset id="depDIV">
				<legend>部门</legend>
				<div class="form-row">
					<div class="field-label">
						<label for="CategoryAdd.Department.name">部门名称：</label>
					</div>
					<div class="field-widget">
						<input type="text" id="department" name="CategoryAdd.Department.name" class="required" value="<%=ca.getDepartment().getName()%>" />
					</div>
				</div>
			</fieldset>
			<fieldset id="catDIV">
				<legend>一级分类</legend>
				<div class="form-row">
					<div class="field-label">
						<label for="CategoryAdd.Category.categoryName">一级分类名称：</label>
					</div>
					<div class="field-widget">
						<input type="text" id="category" name="CategoryAdd.Category.categoryName" class="required" value="<%=ca.getCategory().getCategoryName()%>" />
					</div>
				</div>
			</fieldset>
			<fieldset id="typeDIV">
				<legend>二级分类</legend>
				<div class="form-row">
					<div class="field-label">
						<label for="CategoryAdd.Type.category_type">一级分类名称：</label>
					</div>
					<div class="field-widget">
						<select id="category_type" name="CategoryAdd.Type.category_type" class="validate-selection">
							<c:forEach var="cat" items="${catList}">
								<option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="${cat.id}">${cat.categoryName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-row">
					<div class="field-label">
						<label for="CategoryAdd.Type.typeName">二级分类名称：</label>
					</div>
					<div class="field-widget">
						<input type="text" id="typeName" name="CategoryAdd.Type.typeName" class="required" value="<%=ca.getType().getTypeName()%>" />
					</div>
				</div>
			</fieldset>
			<fieldset id="detailDIV">
				<legend>指标明细</legend>
				<div class="form-row">
					<div class="field-label">
						<label for="CategoryAdd.IndexDetail.category_detail">一级分类名称：</label>
					</div>
					<div class="field-widget">
						<select id="category_detail" name="CategoryAdd.IndexDetail.category_detail" class="validate-selection" onchange="changeValue(1);">
							<option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="">--请选择一级分类--</option>
							<c:forEach var="cat" items="${catList }">
								<option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="${ cat.id}">${ cat.categoryName}</option>
							</c:forEach>
						</select>
					</div>
					</div>
					<div class="form-row">
					<div class="field-label">
						<label for="CategoryAdd.IndexDetail.type_detail">二级分类名称：</label>
					</div>
					<div class="field-widget">
						<select id="type_detail" name="CategoryAdd.IndexDetail.type_detail" class="validate-selection">
							<option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="">--请选择二级分类--</option>
							<c:forEach var="type" items="${typeList }">
								<option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="${ type.id}">${ type.typeName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-row">
					<div class="field-label">
						<label for="CategoryAdd.IndexDetail.indexID">编号：</label>
					</div>
					<div class="field-widget">
						<input type="text" id="indexID" name="CategoryAdd.IndexDetail.indexID" class="required" value="<%=ca.getIndexDetail().getIndexID()%>" />
					</div>
					</div>
					<div class="form-row">
					<div class="field-label">
						<label for="CategoryAdd.IndexDetail.indexName">指标名称：</label>
					</div>
					<div class="field-widget">
						<input type="text" id="indexName" name="CategoryAdd.IndexDetail.indexName" class="required" value="<%=ca.getIndexDetail().getIndexName()%>" />
					</div>
				</div>
				<div class="form-row">
					<div class="field-label">
						<label for="CategoryAdd.IndexDetail.indexDetail">指标定义：</label>
					</div>
					<div class="field-widget">
						<textarea id="indexDetail" name="CategoryAdd.IndexDetail.indexDetail" class="required"><%=ca.getIndexDetail().getIndexDetail() %></textarea>
					</div>
					</div>
                    <div class="form-row">
					<div class="field-label">
						<label for="CategoryAdd.IndexDetail.indexFormula">计算公式：</label>
					</div>
					<div class="field-widget">
						<textarea id="indexFormula" name="CategoryAdd.IndexDetail.indexFormula" class="required"><%=ca.getIndexDetail().getIndexFormula() %></textarea>
					</div>
				</div>
				<div class="form-row">
					<div class="field-label">
						<label for="CategoryAdd.IndexDetail.computingCycle">统计周期：</label>
					</div>
					<div class="field-widget">
						<input type="text" id="computingCycle" name="CategoryAdd.IndexDetail.computingCycle" class="required" value="<%=ca.getIndexDetail().getComputingCycle()%>" />
					</div>
					</div>
                    <div class="form-row">
					<div class="field-label">
						<label for="CategoryAdd.IndexDetail.cycleUnit">周期单位：</label>
					</div>
					<div class="field-widget">
						<input type="text" id="cycleUnit" name="CategoryAdd.IndexDetail.cycleUnit" class="required" value="<%=ca.getIndexDetail().getCycleUnit()%>" />
					</div>
				</div>
				<div class="form-row">
					<div class="field-label">
						<label for="CategoryAdd.IndexDetail.department">所属科室：</label>
					</div>
					<div class="field-widget">
						<input type="text" id="department" name="CategoryAdd.IndexDetail.department" class="required" value="<%=ca.getIndexDetail().getDepartment()%>" />
					</div>
					</div>
                    <div class="form-row">
					<div class="field-label">
						<label for="CategoryAdd.IndexDetail.remark">备注：</label>
					</div>
					<div class="field-widget">
						<textarea id="remark" name="CategoryAdd.IndexDetail.remark" class="required"><%=ca.getIndexDetail().getRemark()%></textarea>
					</div>
				</div>
	</fieldset>
	<fieldset>
		<div class="form-row">
			<input type="button" class="submit" value="信息添加" onclick="changeValue(0);" /> 
			<input type="button" class="reset" value="返回首页" onclick="changeValue(2);" />
		</div>
	</fieldset>
	</form>
	</div>
</body>
<script type="text/javascript">
var category_type = "<%=category_type%>";
var category_detail = "<%=category_detail%>";
var type_detail = "<%=type_detail%>";
	$(document).ready(function() {
		$("#category_type").val(category_type);
		$("#category_detail").val(category_detail);
		$("#type_detail").val(type_detail);
		onaddType(0);
	});

	function onaddType(tag) {
		var businessKey = "";
		if (tag == 1) {
			businessKey = $("#businessKey").val();
		} else if (tag == 0) {
			businessKey =<%=businessKey%>;
			$("#businessKey").val(businessKey);
		}

		if (businessKey == "0") {
			$("#catDIV").show();
			$("#line1").show();
			$("#typeDIV").hide();
			$("#line2").hide();
			$("#detailDIV").hide();
			$("#line3").hide();
			$("#depDIV").hide();
            $("#line4").hide();
		} else if (businessKey == "1") {
			$("#catDIV").hide();
			$("#line1").hide();
			$("#typeDIV").show();
			$("#line2").show();
			$("#detailDIV").hide();
			$("#line3").hide();
			$("#depDIV").hide();
            $("#line4").hide();
		} else if (businessKey == "2") {
			$("#catDIV").hide();
			$("#line1").hide();
			$("#typeDIV").hide();
			$("#line2").hide();
			$("#detailDIV").show();
			$("#line3").show();
			$("#depDIV").hide();
            $("#line4").hide();
		} else if (businessKey == "3") {
            $("#catDIV").hide();
            $("#line1").hide();
            $("#typeDIV").hide();
            $("#line2").hide();
            $("#detailDIV").hide();
            $("#line3").hide();
            $("#depDIV").show();
            $("#line4").show();
        }
	}

	function changeValue(value) {
		$("#category_detailHidden").val(value);
		if (value == 0 || value == 1){
			$("#formId").attr("action", "Init.action");
		} else {
			$("#formId").attr("action", "index.jsp");
		}
		$("#formId").submit();
	}
</script>
</html>
