<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <title>${webinfo.title}</title>
  <link rel="stylesheet" href="${staticPath}css/css.css" />
 </head>
 <body>
 	<div class="header">
 		<div class="w1190 clearfix">
	 		<h1 class="logo">
	 			<img src="${staticPath}images/t01dbbb2e6fd11fd369.png" />
	 		</h1>
	 		<div class="search">
	 			<div class="a clearfix">
	 				<input type="text" class="textBox" placeholder="请输入您要搜索的商品名称或链接" />
	 				<input type="submit" class="submit" />
	 			</div>
	 		</div>
 		</div>
 	</div>
	
 	<div class="w1190">

 		<div class="list">
 			<ul class="clearfix">
 			 <c:forEach var="o" items="${items}">
 				<li class="item">
 					<div class="a">
 						<div class="img">
 							<a href="" target="_blank"><img src="${o.pict_url}" /></a>
 						</div>
 						<div class="b">
 							<p class="title"><a href="" target="_blank">${o.title}</a></p>
 							<div class="clearfix">
	 							<span class="price-old">价格: ¥ <del>${o.reserve_price}</del></span>
	 							<span class="sold">已售: <strong>${o.volume}</strong></span>
 							</div>
 							<div class="clearfix">
 								<span class="price-current">¥${o.zk_final_price}</span>
 								<a href="" target="_blank" class="buy">购买</a>
 							</div>
 						</div>
 					</div>
 				</li>
 			</c:forEach> 
 				
 			</ul>
 		</div>
 	</div>
 	
 	<div class="footer">
 		<div class="w1190">
 			Copyright © 2017 Mai80.com Technology Co., Ltd.  粤ICP备17046420号-1
 		</div>
 	</div>
 </body>
</html>