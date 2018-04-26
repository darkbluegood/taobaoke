<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:if test="${list.size() <= 0}">
	<p class="nothing">暂时没有宝贝上架</p>
</c:if>

<ul class="clearfix">
	<c:forEach var="o" items="${list}">
		<li class="item">
			<div class="a">
				<div class="img">
					<a href="${o.item_url}" target="_blank"><img src="${o.pict_url}" /></a>
				</div>
				<div class="b">
					<p class="title"><a href="${o.item_url}" target="_blank">${o.title}</a></p>
					<div class="clearfix">
						<span class="price-old">价格: ¥ <del>${o.reserve_price}</del></span>
						<span class="sold">已售: <strong>${o.volume}</strong></span>
					</div>
					<div class="clearfix">
						<span class="price-current">¥${o.zk_final_price}</span>
						<a href="${o.item_url}" target="_blank" class="buy">购买</a>
					</div>
				</div>
			</div>
		</li>
	</c:forEach>
</ul>