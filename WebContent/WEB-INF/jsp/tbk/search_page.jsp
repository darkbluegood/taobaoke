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
 						<li class="title"><strong>猜您喜欢:</strong></li>
 						<c:forEach var="item" items="${likeToYou}">
 							<li><a href="${item.url}">${item.title}</a></li>
 						</c:forEach>
 						<%-- <c:if test="${cid.length() <= 0}">
 							<li><a href="tag?fid=${fid}&cid=&mode=${mode}" style="color:red">全部</a></li>
 						</c:if>
 						<c:if test="${cid.length() > 0}">
 							<li><a href="tag?fid=${fid}&cid=&mode=${mode}">全部</a></li>
 						</c:if>
 						<c:forEach var="item" items="${subTag}">
 							<c:if test="${item.id == cid}">
 							<li><a href="tag?fid=${item.fid}&cid=${item.id}&mode=${mode}" style="color:red">${item.title}</a></li>
 							</c:if>
 							<c:if test="${item.id != cid}">
 							<li><a href="tag?fid=${item.fid}&cid=${item.id}&mode=${mode}">${item.title}</a></li>
 							</c:if>
 						</c:forEach> --%>
 					</ul>
 				</div>
		 		
 			</div>
 		</div>
 		<div class="wrap-1 sort-box clearfix">
 			<ul>
 				<c:if test='${sort.length() <= 0}'>
 					<li><a href="search?keyword=${keyword}&sort=" style="color:red">综合</a></li>
 				</c:if>
 				<c:if test='${sort.length() > 0}'>
 					<li><a href="search?keyword=${keyword}&sort=">综合</a></li>
 				</c:if>
 				<c:if test='${sort.equals("sale")}'>
 					<li><a href="search?keyword=${keyword}&sort=sale" style="color:red">销量</a></li>
 				</c:if>
 				<c:if test='${!sort.equals("sale")}'>
 					<li><a href="search?keyword=${keyword}&sort=sale">销量</a></li>
 				</c:if>
 				<c:if test='${sort.equals("price_down")}'>
 					<li><a href="search?keyword=${keyword}&sort=price_down" style="color:red">价格从低到高</a></li>
 				</c:if>
 				<c:if test='${!sort.equals("price_down")}'>
 					<li><a href="search?keyword=${keyword}&sort=price_down">价格从低到高</a></li>
 				</c:if>
 				<c:if test='${sort.equals("price_up")}'>
 					<li><a href="search?keyword=${keyword}&sort=price_up" style="color:red">价格从高到低</a></li>
 				</c:if>
 				<c:if test='${!sort.equals("price_up")}'>
 					<li><a href="search?keyword=${keyword}&sort=price_up">价格从高到低</a></li>
 				</c:if>
 			</ul>
 		</div>
 		<div class="list page">
 			<div class="">
	 			<%@ include file="include/list.jsp" %>
	 		</div>
 		</div>
 	</div>

 	
 <%@ include file="include/footer.jsp" %>