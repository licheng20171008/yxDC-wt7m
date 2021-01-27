<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<script type="text/javascript" src="js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/laydate/laydate.js"></script>
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
    int perPage = 20;
    List<Category> catList = new ArrayList<Category>();
    if (ca.getCategoryList() == null) {
        catList = (List<Category>) session.getAttribute("catList");
    } else {
        catList = ca.getCategoryList();
        session.setAttribute("catList", catList);
    }
    
    List<Type> typeList = new ArrayList<Type>();
    if (ca.getTypeList() == null) {
        typeList = (List<Type>) session.getAttribute("typeList");
    } else {
    	typeList = ca.getTypeList();
        session.setAttribute("typeList", typeList);
    }
    
    List<Department> depList = new ArrayList<Department>();
    if(ca.getDepList() == null){
    	depList = (List<Department>) session.getAttribute("depList");
    } else {
    	depList = ca.getDepList();
    	session.setAttribute("depList", depList);
    }
    int depSize = ca.getDepCount();
    int depCurPage = ca.getDepCurPage();
    int depCountPage = ca.getDepCountPage();
    
    List<Category> catViewList = new ArrayList<Category>();
    if (ca.getCategoryViewList() == null) {
    	catViewList = (List<Category>) session.getAttribute("catViewList");
    } else {
    	catViewList = ca.getCategoryViewList();
        session.setAttribute("catViewList", catViewList);
    }
    int catSize = ca.getCatCount();
    int catCurPage = ca.getCatCurPage();
    int catCountPage = ca.getCatCountPage();
    
    List<Type> typeViewList = new ArrayList<Type>();
    if (ca.getTypeViewList() == null) {
    	typeViewList = (List<Type>) session.getAttribute("typeViewList");
    } else {
    	typeViewList = ca.getTypeViewList();
        session.setAttribute("typeViewList", typeViewList);
    }
    int typeSize = ca.getTypeCount();
    int typeCurPage = ca.getTypeCurPage();
    int typeCountPage = ca.getTypeCountPage();
    
    List<IndexDetail> idiList = new ArrayList<IndexDetail>();
    if(ca.getIdList() == null){
    	idiList = (List<IndexDetail>) session.getAttribute("idiList");
    } else {
    	idiList = ca.getIdList();
        session.setAttribute("idiList", idiList);
    }
    int idSize = ca.getIdCount();
    int idCurPage = ca.getIdCurPage();
    int idCountPage = ca.getIdCountPage();
    
    String businessKey = ca.getBusinessKey();
    String message = ca.getMessage();
    String selectBox = ca.getSelectBox();
%>
<body>
    <span id="message"><strong><%=message%></strong></span>
    <hr style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
    <form id="editForm" method="post">
        <input type="hidden" id="category_detailHidden" name="CategoryAdd.category_detailHidden" /> 
        <input type="hidden" id="operation" name="CategoryAdd.operation" /> 
        <input type="hidden" id="depCurPage" name="CategoryAdd.depCurPage" /> 
        <input type="hidden" id="catCurPage" name="CategoryAdd.catCurPage" /> 
        <input type="hidden" id="typeCurPage" name="CategoryAdd.typeCurPage" /> 
        <input type="hidden" id="idCurPage" name="CategoryAdd.idCurPage" /> 
        <span style="font-family: '宋体'; font-size: 19px;">请选择查询类型：</span>
        <select id="businessKey" name="CategoryAdd.businessKey" style="font-family: '宋体'; font-size: 19px;" onchange="oneditType(1)">
            <option style="width: 400px;font-family: '宋体'; font-size: 19px;" value="0">部门查询</option>
            <option style="width: 400px;font-family: '宋体'; font-size: 19px;" value="1">一级指标查询</option>
            <option style="width: 400px;font-family: '宋体'; font-size: 19px;" value="2">二级指标查询</option>
            <option style="width: 400px;font-family: '宋体'; font-size: 19px;" value="3">指标明细查询</option>
        </select>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        <input type="button" onclick="changeHidden(100)" value="返回首页" />
    <hr id="line0" style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
    <div id="depDIV">
            <span style="font-family: '宋体'; font-size: 19px;">部门名称：</span>
            <input type="text" id="department" name="CategoryAdd.Department.name" value="<%=ca.getDepartment().getName() %>"/>
            <input type="hidden" id="departmentID" name="CategoryAdd.Department.id" value="<%=ca.getDepartment().getId() %>"/>
        </div>
    <hr id="line1" style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
    <div id="catDIV">
            <span style="font-family: '宋体'; font-size: 19px;">一级指标名称：</span>
            <input type="text" id="category" name="CategoryAdd.Category.categoryName" value="<%=ca.getCategory().getCategoryName() %>"/>
            <input type="hidden" id="categoryID" name="CategoryAdd.Category.id" value="<%=ca.getCategory().getId() %>"/>
     </div>
    <hr id="line2" style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
    <div id="typeDIV">
            <span style="font-family: '宋体'; font-size: 19px;">一级指标名称：</span> 
            <select id="category_type" name="CategoryAdd.Type.category_type" style="font-family: '宋体'; font-size: 19px;" >
				<option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="">--请选择一级指标--</option>
				<c:forEach var="cat" items="${catList }">
					<option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="${ cat.id}">${ cat.categoryName}</option>
				</c:forEach>
			</select>
			<span style="font-family: '宋体'; font-size: 19px;">二级指标名称：</span>
			<input type="text" id="typeName" name="CategoryAdd.Type.typeName" value="<%=ca.getType().getTypeName() %>"/>
			<input type="hidden" id="typeId" name="CategoryAdd.Type.id" value="<%=ca.getType().getId() %>"/>
        </div>
        <hr id="line3" style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
        <div id="detailDIV">
            <table border="0" cellpadding="10" width="100%" cellspacing="0" style="font-family: '宋体'; font-size: 19px;">
                <tr>
                    <td><span style="font-family: '宋体'; font-size: 19px;">一级指标名称：</span></td>
                    <td>
                        <select id="category_detail" name="CategoryAdd.IndexDetail.category_detail" style="font-family: '宋体'; font-size: 19px;" onchange="changeValue(1);">
                            <option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="">--请选择一级指标--</option>
                            <c:forEach var="cat" items="${catList }">
                                <option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="${ cat.id}">${ cat.categoryName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><span style="font-family: '宋体'; font-size: 19px;">二级指标名称：</span></td>
                    <td>
                        <select id="type_detail" name="CategoryAdd.IndexDetail.type_detail" style="font-family: '宋体'; font-size: 19px;">
                            <option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="">--请选择二级指标--</option>
                            <c:forEach var="type" items="${typeList }">
                                <option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="${ type.id}">${ type.typeName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><span style="font-family: '宋体'; font-size: 19px;">编号：</span></td>
                    <td><input type="text" id="indexID" name="CategoryAdd.IndexDetail.indexID" value="<%=ca.getIndexDetail().getIndexID() %>" /></td>
                    <td><span style="font-family: '宋体'; font-size: 19px;">指标名称：</span></td>
                    <td><input type="text" id="indexName" name="CategoryAdd.IndexDetail.indexName" value="<%=ca.getIndexDetail().getIndexName() %>" /></td>
                    <td><input type="hidden" id="idi" name="CategoryAdd.IndexDetail.id" /></td>
                </tr>
                <tr>
                    <td><span style="font-family: '宋体'; font-size: 19px;">指标定义：</span></td>
                    <td><textarea id="indexDetail" name="CategoryAdd.IndexDetail.indexDetail"><%=ca.getIndexDetail().getIndexDetail() %></textarea></td>
                    <td><span style="font-family: '宋体'; font-size: 19px;">计算公式：</span></td>
                    <td><textarea id="indexFormula" name="CategoryAdd.IndexDetail.indexFormula"><%=ca.getIndexDetail().getIndexFormula() %></textarea></td>
                </tr>
                <tr>
                    <td><span style="font-family: '宋体'; font-size: 19px;">统计周期：</span></td>
                    <td><input type="text" id="computingCycle" name="CategoryAdd.IndexDetail.computingCycle" value="<%=ca.getIndexDetail().getComputingCycle() %>" /></td>
                    <td><span style="font-family: '宋体'; font-size: 19px;">周期单位：</span></td>
                    <td><input type="text" id="cycleUnit" name="CategoryAdd.IndexDetail.cycleUnit" value="<%=ca.getIndexDetail().getCycleUnit() %>" /></td>
                </tr>
                <tr>
                    <td><span style="font-family: '宋体'; font-size: 19px;">所属科室：</span></td>
                    <td><input type="text" id="id_department" name="CategoryAdd.IndexDetail.department" value="<%=ca.getIndexDetail().getDepartment() %>"/></td>
                    <td><span style="font-family: '宋体'; font-size: 19px;">备注：</span></td>
                    <td><textarea id="remark" name="CategoryAdd.IndexDetail.remark"><%=ca.getIndexDetail().getRemark() %></textarea></td>
                </tr>
            </table>
        </div>
        <hr id="line4" style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
        <input type="button" onclick="changeHidden(0)" value="查询指标基本信息" />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        <input type="button" onclick="changeHidden(1)" value="修改指标基本信息" />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        <input type="button" onclick="changeHidden(2)" value="冻结/解冻指标基本信息" />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        <input type="button" onclick="changeHidden(3)" value="报表导出" />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        <span>模糊查询：</span><input id="selectBox" name="CategoryAdd.selectBox" type="checkbox" value="1"/>
    <hr id="line5" style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
    <p align="center">
        <strong>查询信息显示</strong>
    </p>
    <div id="tableDIV">
    <div id="departmentSelect" >
			<div align="right">
				&nbsp;共<%=depSize%>条记录&nbsp;&nbsp;&nbsp;每页<%=perPage%>条&nbsp;&nbsp;&nbsp;共<%=depCountPage%>页&nbsp;&nbsp;&nbsp;当前第<%=depCurPage%>页
				<input type="button" <%if (depCurPage > 1) {%> onclick="onPage(1);" <%}%> value="首页" /> 
				<input type="button" <%if (depCurPage > 1) {%> onclick="onPage(<%=depCurPage - 1%>);" <%}%> value="上一页" /> 
				<input type="button" <%if (depCurPage < depCountPage && depCountPage > 1) {%> onclick="onPage(<%=depCurPage + 1%>);" <%}%> value="下一页" /> 
				<input type="button" <%if (depCurPage > 0) {%> onclick="onPage(<%=depCountPage%>);" <%}%> value="尾页" /> 
				<input type="button" onclick="onPage(0);" value="转到" />第 
				<input id=top type="text" style="TEXT-ALIGN: right" size=3 />&nbsp;页
			</div>
			<br/>
		<table border="1" cellpadding="10" width="100%" cellspacing="0" style="font-family: '宋体'; font-size: 19px;">
        <tr></tr>
        <tr>
            <th>部门ID</th>
            <th>部门名称</th>
            <th>冻结时间</th>
            <th>操作</th>
        </tr>
            <c:forEach var="dep" items="${depList }">
                <tr>
                    <td>${dep.id }</td>
                    <td>${dep.name }</td>
                    <td>${dep.abateTime }</td>
                    <td align="center">
                        <input type="button" onclick="depChange(this)" value="修改" /> 
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <div align="right">
                &nbsp;共<%=depSize%>条记录&nbsp;&nbsp;&nbsp;每页<%=perPage%>条&nbsp;&nbsp;&nbsp;共<%=depCountPage%>页&nbsp;&nbsp;&nbsp;当前第<%=depCurPage%>页
                <input type="button" <%if (depCurPage > 1) {%> onclick="onPage(1);" <%}%> value="首页" /> 
                <input type="button" <%if (depCurPage > 1) {%> onclick="onPage(<%=depCurPage - 1%>);" <%}%> value="上一页" /> 
                <input type="button" <%if (depCurPage < depCountPage && depCountPage > 1) {%> onclick="onPage(<%=depCurPage + 1%>);" <%}%> value="下一页" /> 
                <input type="button" <%if (depCurPage > 0) {%> onclick="onPage(<%=depCountPage%>);" <%}%> value="尾页" /> 
                <input type="button" onclick="onPage(0);" value="转到" />第 
                <input id=top type="text" style="TEXT-ALIGN: right" size=3 />&nbsp;页
            </div>
            </div>
            <div id="categorySelect" >
            <div align="right">
                &nbsp;共<%=catSize%>条记录&nbsp;&nbsp;&nbsp;每页<%=perPage%>条&nbsp;&nbsp;&nbsp;共<%=catCountPage%>页&nbsp;&nbsp;&nbsp;当前第<%=catCurPage%>页
                <input type="button" <%if (catCurPage > 1) {%> onclick="onPage(1);" <%}%> value="首页" /> 
                <input type="button" <%if (catCurPage > 1) {%> onclick="onPage(<%=catCurPage - 1%>);" <%}%> value="上一页" /> 
                <input type="button" <%if (catCurPage < catCountPage && catCountPage > 1) {%> onclick="onPage(<%=catCurPage + 1%>);" <%}%> value="下一页" /> 
                <input type="button" <%if (catCurPage > 0) {%> onclick="onPage(<%=catCountPage%>);" <%}%> value="尾页" /> 
                <input type="button" onclick="onPage(0);" value="转到" />第 
                <input id=top type="text" style="TEXT-ALIGN: right" size=3 />&nbsp;页
            </div>
            <br/>
        <table border="1" cellpadding="10" width="100%" cellspacing="0" style="font-family: '宋体'; font-size: 19px;">
        <tr></tr>
        <tr>
            <th>一级分类ID</th>
            <th>一级分类名称</th>
            <th>冻结时间</th>
            <th>操作</th>
        </tr>
            <c:forEach var="catView" items="${catViewList }">
                <tr>
                    <td>${catView.id }</td>
                    <td>${catView.categoryName }</td>
                    <td>${catView.abateTime }</td>
                    <td align="center">
                        <input type="button" onclick="catChange(this)" value="修改" /> 
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <div align="right">
                &nbsp;共<%=catSize%>条记录&nbsp;&nbsp;&nbsp;每页<%=perPage%>条&nbsp;&nbsp;&nbsp;共<%=catCountPage%>页&nbsp;&nbsp;&nbsp;当前第<%=catCurPage%>页
                <input type="button" <%if (catCurPage > 1) {%> onclick="onPage(1);" <%}%> value="首页" /> 
                <input type="button" <%if (catCurPage > 1) {%> onclick="onPage(<%=catCurPage - 1%>);" <%}%> value="上一页" /> 
                <input type="button" <%if (catCurPage < catCountPage && catCountPage > 1) {%> onclick="onPage(<%=catCurPage + 1%>);" <%}%> value="下一页" /> 
                <input type="button" <%if (catCurPage > 0) {%> onclick="onPage(<%=catCountPage%>);" <%}%> value="尾页" /> 
                <input type="button" onclick="onPage(0);" value="转到" />第 
                <input id=top type="text" style="TEXT-ALIGN: right" size=3 />&nbsp;页
            </div>
            </div>
            <div id="typeSelect" >
            <div align="right">
                &nbsp;共<%=typeSize%>条记录&nbsp;&nbsp;&nbsp;每页<%=perPage%>条&nbsp;&nbsp;&nbsp;共<%=typeCountPage%>页&nbsp;&nbsp;&nbsp;当前第<%=typeCurPage%>页
                <input type="button" <%if (typeCurPage > 1) {%> onclick="onPage(1);" <%}%> value="首页" /> 
                <input type="button" <%if (typeCurPage > 1) {%> onclick="onPage(<%=typeCurPage - 1%>);" <%}%> value="上一页" /> 
                <input type="button" <%if (typeCurPage < typeCountPage && typeCountPage > 1) {%> onclick="onPage(<%=typeCurPage + 1%>);" <%}%> value="下一页" /> 
                <input type="button" <%if (typeCurPage > 0) {%> onclick="onPage(<%=typeCountPage%>);" <%}%> value="尾页" /> 
                <input type="button" onclick="onPage(0);" value="转到" />第 
                <input id=top type="text" style="TEXT-ALIGN: right" size=3 />&nbsp;页
            </div>
            <br/>
        <table border="1" cellpadding="10" width="100%" cellspacing="0" style="font-family: '宋体'; font-size: 19px;">
        <tr></tr>
        <tr>
            <th>二级分类ID</th>
            <th>二级分类名称</th>
            <th>所属一级分类</th>
            <th>冻结时间</th>
            <th>操作</th>
        </tr>
            <c:forEach var="typeView" items="${typeViewList }">
                <tr>
                    <td>${typeView.id }</td>
                    <td>${typeView.typeName }</td>
                    <td>${typeView.category_type }</td>
                    <td>${typeView.abateTime }</td>
                    <td align="center">
                        <input type="button" onclick="typeChange(this)" value="修改" /> 
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <div align="right">
                &nbsp;共<%=typeSize%>条记录&nbsp;&nbsp;&nbsp;每页<%=perPage%>条&nbsp;&nbsp;&nbsp;共<%=typeCountPage%>页&nbsp;&nbsp;&nbsp;当前第<%=typeCurPage%>页
                <input type="button" <%if (typeCurPage > 1) {%> onclick="onPage(1);" <%}%> value="首页" /> 
                <input type="button" <%if (typeCurPage > 1) {%> onclick="onPage(<%=typeCurPage - 1%>);" <%}%> value="上一页" /> 
                <input type="button" <%if (typeCurPage < typeCountPage && typeCountPage > 1) {%> onclick="onPage(<%=typeCurPage + 1%>);" <%}%> value="下一页" /> 
                <input type="button" <%if (typeCurPage > 0) {%> onclick="onPage(<%=typeCountPage%>);" <%}%> value="尾页" /> 
                <input type="button" onclick="onPage(0);" value="转到" />第 
                <input id=top type="text" style="TEXT-ALIGN: right" size=3 />&nbsp;页
            </div>
            </div>
            <div id="indexdetailSelect" >
            <div align="right">
                &nbsp;共<%=idSize%>条记录&nbsp;&nbsp;&nbsp;每页<%=perPage%>条&nbsp;&nbsp;&nbsp;共<%=idCountPage%>页&nbsp;&nbsp;&nbsp;当前第<%=idCurPage%>页
                <input type="button" <%if (idCurPage > 1) {%> onclick="onPage(1);" <%}%> value="首页" /> 
                <input type="button" <%if (idCurPage > 1) {%> onclick="onPage(<%=idCurPage - 1%>);" <%}%> value="上一页" /> 
                <input type="button" <%if (idCurPage < idCountPage && idCountPage > 1) {%> onclick="onPage(<%=idCurPage + 1%>);" <%}%> value="下一页" /> 
                <input type="button" <%if (idCurPage > 0) {%> onclick="onPage(<%=idCountPage%>);" <%}%> value="尾页" /> 
                <input type="button" onclick="onPage(0);" value="转到" />第 
                <input id=top type="text" style="TEXT-ALIGN: right" size=3 />&nbsp;页
            </div>
            <br/>
        <table border="1" cellpadding="10" width="100%" cellspacing="0" style="font-family: '宋体'; font-size: 19px;">
        <tr></tr>
        <tr>
            <th>指标ID</th>
            <th>一级分类ID</th>
            <th>二级分类ID</th>
            <th>指标编号</th>
            <th>指标名称</th>
            <th>指标定义</th>
            <th>计算公式</th>
            <th>统计周期</th>
            <th>周期单位</th>
            <th>所属科室</th>
            <th>备注</th>
            <th>冻结时间</th>
            <th>操作</th>
        </tr>
            <c:forEach var="idi" items="${idiList }">
                <tr>
                    <td>${idi.id }</td>
                    <td>${idi.category_detail }</td>
                    <td>${idi.type_detail }</td>
                    <td>${idi.indexID }</td>
                    <td>${idi.indexName }</td>
                    <td>${idi.indexDetail }</td>
                    <td>${idi.indexFormula }</td>
                    <td>${idi.computingCycle }</td>
                    <td>${idi.cycleUnit }</td>
                    <td>${idi.department }</td>
                    <td>${idi.remark }</td>
                    <td>${idi.abateTime }</td>
                    <td align="center">
                        <input type="button" onclick="idChange(this)" value="修改" /> 
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <div align="right">
                &nbsp;共<%=idSize%>条记录&nbsp;&nbsp;&nbsp;每页<%=perPage%>条&nbsp;&nbsp;&nbsp;共<%=idCountPage%>页&nbsp;&nbsp;&nbsp;当前第<%=idCurPage%>页
                <input type="button" <%if (idCurPage > 1) {%> onclick="onPage(1);" <%}%> value="首页" /> 
                <input type="button" <%if (idCurPage > 1) {%> onclick="onPage(<%=idCurPage - 1%>);" <%}%> value="上一页" /> 
                <input type="button" <%if (idCurPage < idCountPage && idCountPage > 1) {%> onclick="onPage(<%=idCurPage + 1%>);" <%}%> value="下一页" /> 
                <input type="button" <%if (idCurPage > 0) {%> onclick="onPage(<%=idCountPage%>);" <%}%> value="尾页" /> 
                <input type="button" onclick="onPage(0);" value="转到" />第 
                <input id=top type="text" style="TEXT-ALIGN: right" size=3 />&nbsp;页
            </div>
            </div>
		</div>
    </form>
    <hr style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
</body>
<script type="text/javascript">
    $(document).ready(function() {
    	var selectBox = "<%=selectBox %>";
    	if (selectBox == "1"){
    		$("#selectBox").attr("checked", true);
    	} else {
    		$("#selectBox").attr("checked", false);
    	}
    	oneditType(0);
    });
    
    function oneditType(tag) {
        var businessKey = "";
        if (tag == 1) {
            businessKey = $("#businessKey").val();
        } else if (tag == 0) {
            businessKey =<%=businessKey%>;
            $("#businessKey").val(businessKey);
        }

        if (businessKey == "0") {
            $("#line0").show();
            $("#depDIV").show();
            $("#line1").hide();
            $("#catDIV").hide();
            $("#line2").hide();
            $("#typeDIV").hide();
            $("#line3").hide();
            $("#detailDIV").hide();
            $("#departmentSelect").show();
            $("#categorySelect").hide();
            $("#typeSelect").hide();
            $("#indexdetailSelect").hide();
        } else if (businessKey == "1") {
        	$("#line0").hide();
            $("#depDIV").hide();
            $("#line1").show();
            $("#catDIV").show();
            $("#line2").hide();
            $("#typeDIV").hide();
            $("#line3").hide();
            $("#detailDIV").hide();
            $("#departmentSelect").hide();
            $("#categorySelect").show();
            $("#typeSelect").hide();
            $("#indexdetailSelect").hide();
        } else if (businessKey == "2") {
        	$("#line0").hide();
            $("#depDIV").hide();
            $("#line1").hide();
            $("#catDIV").hide();
            $("#line2").show();
            $("#typeDIV").show();
            $("#line3").hide();
            $("#detailDIV").hide();
            $("#departmentSelect").hide();
            $("#categorySelect").hide();
            $("#typeSelect").show();
            $("#indexdetailSelect").hide();
        } else if (businessKey == "3") {
        	$("#line0").hide();
            $("#depDIV").hide();
            $("#line1").hide();
            $("#catDIV").hide();
            $("#line2").hide();
            $("#typeDIV").hide();
            $("#line3").show();
            $("#detailDIV").show();
            $("#departmentSelect").hide();
            $("#categorySelect").hide();
            $("#typeSelect").hide();
            $("#indexdetailSelect").show();
        }
    }
    
    function changeHidden(value) {
    	var flag = true;
    	var businessKey = $("#businessKey").val();
    	if (value == 3){
    		$("#editForm").attr("action", "Export.action");
    	} else if (value != 100){
            if (value == 1 || value == 2){
            	if (businessKey == "0" && $("#departmentID").val() == ""){
            		flag = false;
            		alert("无法对部门进行操作，请查询并且选择修改的信息再进行操作！！");
            	} else if (businessKey == "1" && $("#categoryID").val() == ""){
            		flag = false;
                    alert("无法对一级分类进行操作，请查询并且选择修改的信息再进行操作！！");
            	} else if (businessKey == "2" && $("#typeId").val() == ""){
                    flag = false;
                    alert("无法对二级分类进行操作，请查询并且选择修改的信息再进行操作！！");
                } else if (businessKey == "3" && $("#idi").val() == ""){
                    flag = false;
                    alert("无法对指标明细进行操作，请查询并且选择修改的信息再进行操作！！");
                }
            }
            $("#editForm").attr("action", "Report.action");
        } else if (value == 100) {
            $("#editForm").attr("action", "index.jsp");
        }
        
        if(flag){
        	$("#operation").val(value);
        	$("#editForm").submit();
        }
    }
    
    function onPage(value){
    	var businessKey = $("#businessKey").val();
    	if (value == 0) {
    		value = $("#top").val();
    	}
    	var flag = false;
    	if (businessKey == "0"){
    		var depCountPage = "<%=depCountPage %>";
    		if (depCountPage > 0 && value > 0 && value <= depCountPage) {
    			$("#depCurPage").val(value);
    			$("#department").val();
    			$("#departmentID").val();
    			flag = true;
    		}
    	} else if (businessKey == "1"){
    		var catCountPage = "<%=catCountPage %>";
            if (catCountPage > 0 && value > 0 && value <= catCountPage) {
                $("#catCurPage").val(value);
                $("#category").val();
                $("#categoryID").val();
                flag = true;
            }
    	} else if (businessKey == "2"){
            var typeCountPage = "<%=typeCountPage %>";
            if (typeCountPage > 0 && value > 0 && value <= typeCountPage) {
                $("#typeCurPage").val(value);
                $("#category_type").val();
                $("#typeName").val();
                $("#typeId").val();
                flag = true;
            }
        } else if (businessKey == "3"){
            var idCountPage = "<%=idCountPage %>";
            if (idCountPage > 0 && value > 0 && value <= idCountPage) {
                $("#idCurPage").val(value);
                $("#remark").text();
                $("#department").val();
                $("#cycleUnit").val();
                $("#computingCycle").val();
                $("#indexFormula").text();
                $("#indexDetail").text();
                $("#indexName").val();
                $("#indexID").val();
                $("#type_detail").val();
                $("#category_detail").val();
                $("#idi").val();
                flag = true;
            }
        }
    	if (flag){
    		$("#editForm").attr("action", "Report.action");
            $("#editForm").submit();
    	} else {
    		alert("跳转页数输入错误，请确认！！");
    	}
    }
    
    function depChange(obj){
    	$("#department").val($(obj).parent().prev().prev().text());
    	$("#departmentID").val($(obj).parent().prev().prev().prev().text());
    }
    
    function catChange(obj){
        $("#category").val($(obj).parent().prev().prev().text());
        $("#categoryID").val($(obj).parent().prev().prev().prev().text());
    }
    
    function typeChange(obj){
        $("#typeName").val($(obj).parent().prev().prev().prev().text());
        $("#category_type").children("option[value=" + $(obj).parent().prev().prev().text() + "]").attr("selected", true);
        $("#category_type").attr("disabled", true);
        $("#typeId").val($(obj).parent().prev().prev().prev().prev().text());
    }
    
    function idChange(obj){
        $("#remark").text($(obj).parent().prev().prev().text());
        $("#id_department").val($(obj).parent().prev().prev().prev().text());
        $("#cycleUnit").val($(obj).parent().prev().prev().prev().prev().text());
        $("#computingCycle").val($(obj).parent().prev().prev().prev().prev().prev().text());
        $("#indexFormula").text($(obj).parent().prev().prev().prev().prev().prev().prev().text());
        $("#indexDetail").text($(obj).parent().prev().prev().prev().prev().prev().prev().prev().text());
        $("#indexName").val($(obj).parent().prev().prev().prev().prev().prev().prev().prev().prev().text());
        $("#indexID").val($(obj).parent().prev().prev().prev().prev().prev().prev().prev().prev().prev().text());
        var type_detail = $(obj).parent().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().text();
        $("#type_detail").children("option[value=" + type_detail + "]").attr("selected", true);
        var category_detail = $(obj).parent().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().text();
        $("#category_detail").children("option[value=" + category_detail + "]").attr("selected", true);
        $("#idi").val($(obj).parent().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().prev().text());
    }
</script>
</html>
