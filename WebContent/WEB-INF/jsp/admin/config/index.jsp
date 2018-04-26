<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${website.title}</title>
<link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<style type="text/css">
	.website-container{padding-top:20px;}
</style>
</head>
<body>
	<div class="container-fluid website-container">
		<form:form action="submit" method="POST" name="infoForm" enctype="multipart/form-data">
		  <div class="form-group">
		    <label for="title">网站标题</label>
		    <form:input path="title" class="form-control" id="title" />
		  </div>
		  <div class="form-group">
		    <label for="keyword">Meta关键字</label>
		    <form:input path="keyword" class="form-control" id="keyword" />
		  </div>
		  <div class="form-group">
		    <label for="description">Meta描述</label>
		    <form:input path="description" class="form-control" id="description" />
		  </div>
		  <div class="form-group">
		    <label for="copyright">网站版权</label>
		    <form:input path="copyright" class="form-control" id="copyright" />
		  </div>
		  <div class="form-group">
		    <label for="logo">LOGO上传</label>
		    <input type="file" name="logo" id="logo" />
		    <img src="${logoUrl}" />
		  </div>
		  <button type="submit" class="btn btn-default">提交</button>
		</form:form>
	</div>
</body>
	<script type="text/javascript">
		
	</script>
</html>




