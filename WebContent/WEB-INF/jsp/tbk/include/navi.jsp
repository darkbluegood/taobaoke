<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="navi">
	<div class="w1190 clearfix">
		<div class="all-category">
			<img src="static/tbk/images/category.png" width="18" /> 全部商品分类
		</div>
		<ul class="items clearfix">
			<li><a href="/">首页</a></li>
			<c:forEach var="item" items="${navi}">
				<li><a href="tag?fid=${item.id}&id=${item.id}" target="_blank">${item.title}</a></li>
			</c:forEach>
		</ul>
	</div>
</div>