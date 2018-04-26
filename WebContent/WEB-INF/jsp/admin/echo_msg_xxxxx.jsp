<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="project001.admin.entity.Admin" %>
<%
	response.setCharacterEncoding("utf-8");
	Admin admin = (Admin)session.getAttribute("adminInfo");
	if(admin == null){
		response.sendRedirect("/project001/admin/login");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<title>提示信息</title>

</head>
<body>
	<div class="container-fluid" style="padding-top:20px;">
		<div class="panel panel-info">
		  <div class="panel-heading">
		    <h3 class="panel-title">提示</h3>
		  </div>
		  <div class="panel-body">
		    ${msg} <a href="javascript:void(0)" onclick="goto()">返回</a>
		  </div>
		</div>
	</div>
	<script type="text/javascript">
		function goto(){
			window.location.href="${adminPath}${contextPath}";
		}
		
	</script>
</body>
</html>