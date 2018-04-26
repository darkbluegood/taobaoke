<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<title>Insert title here</title>
</head>
<body>
	<form:form class="form-horizontal" name="infoForm" action="iupdate" method="POST">
		<form:input path="id" class="hide"></form:input>
		
		  <div class="form-group">
		    <label for="title" class="col-sm-2 control-label">分类名称</label>
		    <div class="col-sm-6">
		      <form:input path="title" class="form-control" id="title"></form:input>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="sort" class="col-sm-2 control-label">排序</label>
		    <div class="col-sm-6">
		      <form:input path="sort" class="form-control" id="sort"></form:input>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="keyword" class="col-sm-2 control-label">关键字</label>
		    <div class="col-sm-6">
		    	<form:input path="keyword" class="form-control" id="keyword"></form:input>
		    </div>
		    <div class="col-sm-4">
		    	多个请用"|"隔开
		    </div>
		  </div>
	
		  <div class="form-group">
		  	<label for="" class="col-sm-2 control-label"></label>
		    <div class="col-sm-6">
		    	<button type="submit" name="submitType" class="btn btn-danger">更新</button>
		    </div>
		  </div>
	  
	</form:form>
</body>
</html>