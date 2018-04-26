<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>标签</title>
<link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<style type="text/css">
	.add_navi{ padding:10px 0 0px }
	.wrap{ width:500px }
	.add,.update{ margin-left:10px;display:inline-block;line-height:1.1 }
	.set-sort,.category-title{ display:inline-block; }
	.title_add{ font-size:14px; }
	.set-sort{ padding-left:8px; }
	.set-sort .sort-input{ width:50px }
	.sort_submit{ position:absolute;top:-10000000px; }
</style>
<script src="https://unpkg.com/vue"></script>
<script type="text/javascript" src="/static/common/js/jquery.min.js"></script>
</head>
<body>
	<p class="add_navi">
		<a href="add" class="btn btn-info">添加标签</a>
	</p>
	
	<p id="loading" id="loading" v-if="isShow">载入中...</p>
	
	<div class="wrap" id="tags">
		
		<%-- <c:forEach var="item" items="${tagListV2}">
			<p>${item.title}</p>
			<c:forEach var="item" items="${item.subset}">
				<p>---${item.title}</p>
				<c:forEach var="item" items="${item.subset}">
					<p>------${item.title}</p>
				</c:forEach>
			</c:forEach>
		</c:forEach> --%>
		<div v-for="data in list">
		
			<h3>{{data.sort}}  ID:{{data.id}}  {{data.title}}  <a :href="'add?fid='+data.id+'&type_name='+data.title" class="add title_add" /> 添加子类</a>  <a :href="'update?id='+data.id" class="update title_add">修改</a></h3>
			<div class="panel panel-default" v-for="data in data.subset">
				<div class="panel-heading clearfix">
				    <h3 class="panel-title category-title">{{data.sort}} ID:{{data.id}}  {{data.title}}</h3>  
					<a :href="'add?fid='+data.id+'&type_name='+data.title+'&title='+data.title" class="add" /> 添加子类</a>
				    <a :href="'update?id='+data.id" class="update">修改</a>
				</div>
				<div class="panel-body">
			    	<ul>
			    		<li v-for="data in data.subset">{{data.sort}}   ID:{{data.id}}  {{data.title}} <a :href="'update?id='+data.id" class="update">修改</a></li>
			    	</ul>
				</div>
			</div>
		</div>
		<%-- <c:forEach var="itemV1" items="${tagListV2}">
			<h3>${itemV1.sort}  ID:${itemV1.id}  ${itemV1.title}  <a href="add?fid=${itemV1.id}&type_name=${itemV1.title}" class="add title_add" /> 添加子类</a>  <a href="update?id=${itemV1.id}" class="update title_add">修改</a></h3> 
			<c:forEach var="item" items="${itemV1.subset}">
				<div class="panel panel-default">
				  <div class="panel-heading clearfix">
				    <h3 class="panel-title category-title">${item.sort} ID:${item.id}  ${item.title}</h3>  
					<a href="add?fid=${item.id}&type_name=${itemV1.title}&title=${item.title}" class="add" /> 添加子类</a>
				    <a href="update?id=${item.id}" class="update">修改</a>
				  </div>
				  <div class="panel-body">
				    	<ul>
				    		<c:forEach var="item" items="${item.subset}">
				    			<li>${item.sort}   ID:${item.id}  ${item.title} <a href="update?id=${item.id}" class="update">修改</a></li>
				    		</c:forEach>
				    	</ul>
				  </div>
				</div>
			</c:forEach>
		</c:forEach> --%>
	
		<%-- <c:forEach var="item" items="${tagList}">
			<h3>${item.typeName}</h3>
			<c:forEach var="list" items="${item.tagList}">
				<div class="panel panel-default">
				  <div class="panel-heading clearfix">
				    <h3 class="panel-title category-title">ID:${list.id}  ${list.title}</h3>  
					<a href="add?fid=${list.id}&type=${list.type}&type_name=${list.fid_title}&title=${list.title}" class="add" /> 添加子类</a>
				    <a href="update?id=${list.id}" class="update">修改</a>
				  </div>
				  <div class="panel-body">
				    	<ul>
				    		<c:forEach var="c" items="${list.subset}">
				    			<li>ID:${c.id}  ${c.title} <a href="update?id=${c.id}" class="update">修改</a></li>
				    		</c:forEach>
				    	</ul>
				  </div>
				</div>
			</c:forEach>
		</c:forEach> --%>

	</div>
	
	<script type="text/javascript">
		var tags = new Vue({
			el : "#tags",
			data : {
				list : []
			}
		});
		
		var loading = new Vue({
			el : "#loading",
			data : {
				isShow : true
			}
		})
		
		$.ajax({
			url : "tagList",
			type : "POST",
			success : function(data){
				var data = JSON.parse(data);
				tags.list = data.list;
				console.log(data)
				loading.isShow = false;
			}
		})
	</script>
	
</body>
</html>