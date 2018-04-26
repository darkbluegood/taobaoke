<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="header">
	<div class="w1190 clearfix">
		<h1 class="logo">
			<img src="${webInfo.logoUrl}" />
		</h1>
		<div class="search_wrap">
			<div class="search">
				<div class="a clearfix">
					<input type="text" name="keyword" id="searchBox" class="textBox" placeholder="${keyword}" />
					<input type="submit" class="submit" id="search_submit" value="搜索" />
				</div>
			</div>
			<ul class="keyword">
				<c:forEach var="item" items="${searchKeyword}">
					<li><a href="${item.url}" target="_blank">${item.title}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
