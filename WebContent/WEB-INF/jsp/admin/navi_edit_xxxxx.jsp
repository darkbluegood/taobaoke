<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>导航栏-编辑</title>
<link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
</head>
<body>
	<div class="container-fluid website-container">
		<form class="form-horizontal">
		  <div class="form-group">
		    <label for="title" class="col-sm-1 control-label">名称</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="title" placeholder="请填写标题">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="sort" class="col-sm-1 control-label">排序</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="sort" placeholder="请填写排序">
		    </div>
		  </div>

		  <div class="form-group">
		    <div class="col-sm-offset-1 col-sm-10">
		      <button type="submit" class="btn btn-default">修改</button>
		    </div>
		  </div>
		</form>
		
	</div>
</body>
</html>