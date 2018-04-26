<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类管理</title>
<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
	<style type="text/css">
		*{margin:0;padding:0;}
		ul,ol{ list-style:none outside none; }
		.add{ margin-left:10px;display:inline-block;line-height:1.1 }
	</style>
</head>
<body>
	<form:form class="form-horizontal" name="infoForm" action="executeAdd" method="POST">
		<input type="hidden" value="${fid}" name="fid" />
		<c:if test="${fid_title != null && fid != 0}">
			<input type="hidden" value="${fid_title}" name="fid_title" />
			<div class="form-group">
			    <label for="title" class="col-sm-2 control-label">父类名称</label>
			    <div class="col-sm-6">
			      ${fid_title}
			    </div>
			    <div class="col-sm-4">
			    </div>
			</div>
		</c:if>
	  <div class="form-group">
	    <label for="title" class="col-sm-2 control-label">分类名称</label>
	    <div class="col-sm-6">
	      <input type="text" class="form-control" name="title" id="title" placeholder="请填写分类名称" />
	    </div>
	    <div class="col-sm-4">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="sort" class="col-sm-2 control-label">排序ID</label>
	    <div class="col-sm-6">
	      <input type="text" class="form-control" name="sort" id="sort" placeholder="请填写排序ID" />
	    </div>
	    <div class="col-sm-4">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="keyword" class="col-sm-2 control-label">关键字</label>
	    <div class="col-sm-6">
	      <input type="text" class="form-control" name="keyword" id="keyword" placeholder="请填写关键字" />
	    </div>
	    <div class="col-sm-4">
	    	多个请用"|"隔开
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="submit" class="col-sm-2 control-label"></label>
	    <div class="col-sm-6">
			<button type="submit" name="submitType" class="btn btn-danger">添加</button>
	    </div>
	    <div class="col-sm-4">
	    </div>
	  </div>
	</form:form>

</body>
</html>