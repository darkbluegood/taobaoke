<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		.wrap{ width:500px }
		.add,.update{ margin-left:10px;display:inline-block;line-height:1.1 }
		.fid{padding:10px 0;}
		.set-sort,.category-title{ display:inline-block; }
		.set-sort{ padding-left:8px; }
		.set-sort .sort-input{ width:50px }
		.sort_submit{ position:absolute;top:-10000000px; }
	</style>
</head>
<body>
	<div class="fid">
		<a href="add?id=0&fid_title=" class="btn btn-info">添加大类</a>
	</div>
	<div class="wrap">
		<c:forEach var="item" items="${categoryList}">
			<div class="panel panel-default">
			  <div class="panel-heading">
			    <h3 class="panel-title category-title">ID:${item.id}  ${item.title}</h3>  
			    <a href="add?id=${item.id}&fid_title=${item.title}" class="add" />添加子类</a>
			    <a href="update?id=${item.id}" class="update">修改</a>
			  </div>
			  <div class="panel-body">
			    	<ul>
			    		<c:forEach var="c" items="${item.subset}">
			    			<li>ID:${c.id}  ${c.title}  <a href="update?id=${c.id}" class="update">修改</a></li>
			    		</c:forEach>
			    	</ul>
			  </div>
			</div>
		</c:forEach>
	</div>

</body>
</html>