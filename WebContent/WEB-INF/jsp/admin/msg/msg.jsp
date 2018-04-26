<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提示</title>
<link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<style type="text/css">
	.box{ width:500px;margin:0 auto }
</style>
</head>
<body>
	<div class="box">
		<c:if test="${code == 0}">
			<p class="alert alert-danger">${info}</p>
		</c:if>
		<c:if test="${code == 1}">
			<p class="alert alert-info">${info}</p>
		</c:if>
		<a href="${backUrl}" class="btn btn-info">返回</a>
	</div>
	
	<script type="text/javascript">
		
	</script>
</body>
</html>