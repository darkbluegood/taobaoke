<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
	<style type="text/css">
		.ltable>tbody>tr>td{ vertical-align:middle }
		.add_navi{ padding:10px 0 0px }
	</style>
<script src="https://unpkg.com/vue"></script>
<script type="text/javascript" src="/static/common/js/jquery.min.js"></script>
</head>
<body>
	<p class="add_navi">
		<a href="add" class="btn btn-info">添加幻灯片</a>
	</p>
	<p id="loading" id="loading" v-if="isShow">载入中...</p>
	<table class="table table-hover ltable table-responsive" id="banner">
		<tr>
			<th>ID</th>
			<th>标题</th>
			<th>预览图</th>
			<th>URL</th>
			<th>排序</th>
			<th>编号</th>
			<th>创建日期</th>
			<th>操作</th>
		</tr>
		<tr v-for="data in list">
			<td>{{data.id}}</td>
			<td>{{data.title}}</td>
			<td><img :src="data.src" width="150" /></td>
			<td>{{data.url}}</td>
			<td>{{data.sort}}</td>
			<td>{{data.uuid}}</td>
			<td>{{data.id}}</td>
			<td>{{data.createTime}}</td>
			<td><a :href="'update?id='+data.id">修改</a></td>
		</tr>
		<%-- <c:forEach var="item" items="${bannerList}">
			<tr>
				<td>${item.id}</td>
				<td>${item.title}</td>
				<td><img src="${item.src}" width="150" /></td>
				<td>${item.url}</td>
				<td>${item.sort}</td>
				<td>${item.uuid}</td>
				<td>${item.createTime}</td>
				<td><a href="update?id=${item.id}">修改</a></td>
			</tr>
		</c:forEach> --%>
	</table>
	<script type="text/javascript">
		var banner = new Vue({
			el : "#banner",
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
			type : "POST",
			url : "bannerList",
			success : function(data){
				var data = JSON.parse(data);
				banner.list = data.list
				loading.isShow = false;
			}
		})
	</script>
	
</body>
</html>