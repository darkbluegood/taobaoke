<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="include/top.jsp" %>
 	<%@ include file="include/header.jsp" %>
 	
 	<%@ include file="include/navi.jsp" %>
 	
 	<div class="w1190">
 		<div class="wrap-1 clearfix">
 			<div class="inner">
 				<div class="list-bar">
 					<ul class="clearfix">
 						<li class="title"><strong>分类:</strong></li>
 						<c:if test="${cid.length() <= 0}">
 							<li><a href="tag?fid=${fid}&cid=&mode=${mode}" style="color:red">全部</a></li>
 						</c:if>
 						<c:if test="${cid.length() > 0}">
 							<li><a href="tag?fid=${fid}&cid=&mode=${mode}">全部</a></li>
 						</c:if>
 						<c:forEach var="item" items="${subTag}">
 							<c:if test='${item.title.equals(q)}'>
 							<li><a href="tag?fid=${item.fid}&q=${item.title}" style="color:red">${item.title}</a></li>
 							</c:if>
 							<c:if test="${!item.title.equals(q)}">
 							<li><a href="tag?fid=${item.fid}&q=${item.title}">${item.title}</a></li>
 							</c:if>
 						</c:forEach>
 					</ul>
 				</div>
		 		
 			</div>
 		</div>
 		<div class="wrap-1 sort-box clearfix">
 			<ul>
 				<c:if test="${tag.id == 0}">
	 				<c:if test='${tag.sort == 0}'>
	 					<li><a href="tag?fid=${tag.fid}&q=${q}" style="color:red">综合</a></li>
	 				</c:if>
	 				<c:if test='${tag.sort != 0}'>
	 					<li><a href="tag?fid=${tag.fid}&q=${q}">综合</a></li>
	 				</c:if>
	 				<c:if test='${tag.sort == 1}'>
	 					<li><a href="tag?fid=${tag.fid}&q=${q}&sort=1" style="color:red">销量</a></li>
	 				</c:if>
	 				<c:if test='${tag.sort != 1}'>
	 					<li><a href="tag?fid=${tag.fid}&q=${q}&sort=1">销量</a></li>
	 				</c:if>
	 				<c:if test='${tag.sort == 2}'>
	 					<li><a href="tag?fid=${tag.fid}&q=${q}&sort=2" style="color:red">价格从低到高</a></li>
	 				</c:if>
	 				<c:if test='${tag.sort != 2}'>
	 					<li><a href="tag?fid=${tag.fid}&q=${q}&sort=2">价格从低到高</a></li>
	 				</c:if>
	 				<c:if test='${tag.sort == 3}'>
	 					<li><a href="tag?fid=${tag.fid}&q=${q}&sort=3" style="color:red">价格从高到低</a></li>
	 				</c:if>
	 				<c:if test='${tag.sort != 3}'>
	 					<li><a href="tag?fid=${tag.fid}&q=${q}&sort=3">价格从高到低</a></li>
	 				</c:if>
 				</c:if>
 				
 				<c:if test="${tag.id > 0}">
 					<c:if test='${tag.sort == 0}'>
	 					<li><a href="tag?fid=${tag.fid}&id=${tag.id}" style="color:red">综合</a></li>
	 				</c:if>
	 				<c:if test='${tag.sort != 0}'>
	 					<li><a href="tag?fid=${tag.fid}&id=${tag.id}">综合</a></li>
	 				</c:if>
	 				<c:if test='${tag.sort == 1}'>
	 					<li><a href="tag?fid=${tag.fid}&q=${q}&id=${tag.id}&sort=1" style="color:red">销量</a></li>
	 				</c:if>
	 				<c:if test='${tag.sort != 1}'>
	 					<li><a href="tag?fid=${tag.fid}&q=${q}&id=${tag.id}&sort=1">销量</a></li>
	 				</c:if>
	 				<c:if test='${tag.sort == 2}'>
	 					<li><a href="tag?fid=${tag.fid}&q=${q}&id=${tag.id}&sort=2" style="color:red">价格从低到高</a></li>
	 				</c:if>
	 				<c:if test='${tag.sort != 2}'>
	 					<li><a href="tag?fid=${tag.fid}&q=${q}&id=${tag.id}&sort=2">价格从低到高</a></li>
	 				</c:if>
	 				<c:if test='${tag.sort == 3}'>
	 					<li><a href="tag?fid=${tag.fid}&q=${q}&id=${tag.id}&sort=3" style="color:red">价格从高到低</a></li>
	 				</c:if>
	 				<c:if test='${tag.sort != 3}'>
	 					<li><a href="tag?fid=${tag.fid}&q=${q}&id=${tag.id}&sort=3">价格从高到低</a></li>
	 				</c:if>
 				</c:if>
 				
 			</ul>
 		</div>
 		<div class="list page wrap-1">
 			<div class="inner">
	 			<%@ include file="include/list.jsp" %>
	 		</div>
 		</div>
 	</div>
 <%@ include file="include/footer.jsp" %>