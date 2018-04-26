<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="include/top.jsp" %>
 
 	<%@ include file="include/header.jsp" %>
 	
 	<%@ include file="include/navi.jsp" %>
 	<div class="main">
 		<div class="main_box clearfix">
	 		<div class="category">
	 				<c:forEach var="aCategory" items="${category}">
	 					<div class="items_w">
	 						<img src="static/tbk/images/category_arrow.png" width="5" class="arrow" />
			 				<ul class="items clearfix">
			 					<c:forEach var="bCategory" items="${aCategory}">
			 						<li>
				 						<a href="tag?fid=${bCategory.id}&q=${bCategory.title}">${bCategory.title}</a>
				 						<span class="line">/</span>
				 					</li>
			 					</c:forEach>
			 				</ul>
		 				</div>
	 				</c:forEach>
	 		</div>
	 		
	 		<div class="category_popup_wrap">
		 		<c:forEach var="popupContent" items="${popupList}">
		 			<div class="category_popup">
			 			<div class="inner">
			 				<c:forEach var="popupCategory" items="${popupContent}">
					 			<div class="panel clearfix">
					 				<div class="title">
					 					<h3><a href="tag?fid=${popupCategory.id}&q=${popupCategory.title}">${popupCategory.title}</a></h3>
					 				</div>
					 				<ul class="subclass">
					 					<c:forEach var="popupSubclass" items="${popupCategory.subset}">
					 						<li><a href="tag?fid=${popupCategory.id}&q=${popupSubclass.title}">${popupSubclass.title}</a></li>
					 					</c:forEach>
					 				</ul>
					 			</div>
				 			</c:forEach>
			 			</div>
			 		</div>
		 		</c:forEach>
	 		</div>
	 		
	 		<div class="banner" id="banner">
	 			<ul class="banner-list">
	 				<c:forEach var="item" items="${banner}">
	 					<li>
			 				<a href="${item.url}" title="${item.title}" target="_blank">
				 				<img src="${item.src}" width="710" height="280" />
				 			</a>
			 			</li>
		 			</c:forEach>
	 			</ul>
	 			<div class="slide-nav">
		 			<span id="slide-nav">
		 				<a href="javascript:void(0)"></a>
		 				<a href="javascript:void(0)"></a>
		 				<a href="javascript:void(0)"></a>
		 				<a href="javascript:void(0)"></a>
		 			</span>
	 			</div>
	 			<a href="javascript:void(0)" class="ctrl previous"><</a>
	 			<a href="javascript:void(0)" class="ctrl next">></a>
	 		</div>
	 		<div class="right_sidebar">
	 			<div class="small_image">
	 				<a href="${imageModel.url}" title="${imageModel.title}" target="_blank"><img src="${imageModel.src}" width="270" height="100" /></a>
	 			</div>
	 			<div class="menu clearfix">
	 				<ul>
	 					<c:forEach var="item" items="${menu}">
	 						<li class="vertica_line"><a href="${item.url}" style="color:${item.color}" target="_blank">${item.title}</a></li>
	 					</c:forEach>
	 					<c:if test="${menu.size() % 2 == 1}">
							<li class="vertica_line"></li>
						</c:if>
	 				</ul>
	 			</div>
	 		</div>
 		</div>
 	</div>
 	
 	<div class="w1190">
	 	<div class="sales clearfix">
	 		<%-- <ul>
	 			<c:forEach var="item" items="${hot}">
	 				<li class="item1">
		 				<a href="${item.url}" class="linkimg">
		 					<img src="${item.src}" width="60" height="60" />
		 				</a>
		 				<div class="desc">
		 					<a href="${item.url}">
			 					<p class="p1">${item.title}</p>
			 					<p class="p2">${item.description}</p>
		 					</a>
		 					<!-- <p class="p3">销量:25561110</p> -->
		 				</div>
		 			</li>
	 			</c:forEach>
	 		</ul> --%>
	 		<%-- <ul>
	 				<li class="item1">
		 				<a href="${item.url}" class="linkimg">
		 					<img src="http://img1.tbcdn.cn/tfscom/i1/2345690225/TB1GWjYnYwTMeJjSszfXXXbtFXa_!!0-item_pic.jpg" width="60" height="60" />
		 				</a>
		 				<div class="desc">
		 					<a href="${item.url}">
			 					<p class="p1">葡萄酒</p>
			 					<p class="p2">法国原瓶进口红酒 送醒酒器酒杯罗莎田园干红葡萄酒2支装750ml*2</p>
			 					<p class="p3">价格: ¥ 78    销量:12354</p>
		 					</a>
		 					<!-- <p class="p3">销量:25561110</p> -->
		 				</div>
		 			</li>
	 		</ul> --%>
	 		<ul>
	 			<c:forEach var="item" items="${hot}">
	 				<li>
		 				<a href="${item.item_url}" class="linkimg" target="_blank">
		 					<img src="${item.pict_url}" width="80" height="80" />
		 				</a>
		 				<div class="desc">
		 					<a href="${item.item_url}" target="_blank">
			 					<p class="p1" style="color:${item.color}">${item.title}</p>
			 					<p class="p2" style="color:${item.color}">${item.description}</p>
			 					<p class="p3">价格: ¥ ${item.zk_final_price}</p>
			 					<p class="p4">销量: ${item.volume}</p>
		 					</a>
		 				</div>
		 			</li>
	 			</c:forEach>
	 		</ul>
	 	</div>
 	</div>

 	<div class="w1190">
 		<div class="list wrap-1">
 			<div class="inner">
 				<div class="title_bar jxhh_title">
 					<span><img src="static/tbk/images/jxhh.jpg" width="246" height="21" /></span>
 				</div>
 				<div class="list-bar">
 					<ul class="clearfix">
 						<c:forEach var="item" items="${choiceTag}">
 							<li><a href="${item.url}" style="color:${item.color}">${item.title}</a></li>
 						</c:forEach>
 					</ul>
 				</div>
 			
	 			<p id="loading" id="loading" v-if="isShow">载入中...</p>
	 			<div id="best">
	 				
		 			<ul class="clearfix" >
		 				<li class="item" v-for="o in best">
		 					<div class="a">
		 						<div class="img">
		 							<a :href="o.item_url" target="_blank"><img :src="o.pict_url" /></a>
		 						</div>
		 						<div class="b">
		 							<p class="title"><a :href="o.item_url" target="_blank">{{o.title}}</a></p>
		 							<div class="clearfix">
			 							<span class="price-old">价格: ¥ <del>{{o.reserve_price}}</del></span>
			 							<span class="sold">已售: <strong>{{o.volume}}</strong></span>
		 							</div>
		 							<div class="clearfix">
		 								<span class="price-current">¥<span>{{o.zk_final_price}}</span></span>
		 								<a :href="o.item_url" target="_blank" class="buy">购买</a>
		 							</div>
		 						</div>
		 					</div>
		 				</li>
		 			</ul>
		 			<div class="loadMore">
		 				<a href="javascript:void(0)" v-on:click="loadMore" v-if="isMore">点击更多<img src="static/tbk/images/loadmore.png" width="16" /></a>
		 				<span v-if="!isMore">已经没有了!</span>
		 			</div>
	 			</div>
	 			
	 		</div>
 		</div>
 	</div>
 	
 	<script type="text/javascript">
 		
 	
 		//幻灯片
		$(function(){
			var banner = $("#banner"),bannerList = $(".banner-list"),item = bannerList.find("li");
			var vWidth = banner.width(),len = item.length;
			bannerList.css("width",vWidth*len);
			
			var category = $(".category"),category_popup_wrap = $(".category_popup_wrap");
			category_popup_wrap.css("left",category.offset().left+category.width()-8);
			
			
			var slideNavHtml = "";
			for(var j=0; j<len; j++){
				if(j == 0){
					slideNavHtml += " <a href='javascript:void(0)' class='active'></a>";
				}else{
					slideNavHtml += " <a href='javascript:void(0)'></a>";
				}
			}
			$("#slide-nav").html(slideNavHtml);
			
			var i = 0,timer=null;
			function run(){
				timer = setInterval(function(){
	 				if(i == len-1){
	 					i=-1;
	 				}
	 				i++;
	 				move(i);
	 			},10000);
			}
			run();
			
			function move(i){
				$("#slide-nav").find("a").eq(i).addClass("active").siblings().removeClass("active");
				bannerList.animate({
					left : (i*vWidth) * -1
				});
			}
			
			banner.find(".ctrl").hover(function(){
				timer && clearInterval(timer);
			},function(){
				run();
			});
			
			banner.hover(function(){
				banner.find(".ctrl").fadeIn();
			},function(){
				banner.find(".ctrl").fadeOut();
			});
			
			banner.find(".previous").on("click",function(){
				if(i<=0){
					return false;
				}
				move(--i);
			});
			banner.find(".next").on("click",function(){
				if(i==len-1){
					i=-1;
				}
				move(++i);
			});

			$("#slide-nav a").each(function(index){
				$(this).hover(function(){
					timer && clearInterval(timer);
					i = index;
					move(index);
				},function(){
					run();
				});
			});
			
		});
		
		//分类选择
		$(function(){
			var categoryTimer = null;
			$(".category").hover(function(){
				
			},function(){
				categoryTimer = setTimeout(function(){
					$(".category_popup_wrap").hide();
				},50);
			});
			
			$(".category .items").each(function(i){
	 			var closeTimer = null,iTimer = null;
	 			$(this).hover(function(){
	 				closeTimer && clearTimeout(closeTimer);
	 				iTimer && clearTimeout(iTimer);
	 				var _this = $(this);
	 				iTimer = setTimeout(function(){
	 					$(".category_popup_wrap").show();
	 					_this.addClass("active");
	 	 				$(".category_popup").eq(i).show();
	 				},50)
	 				
	 			},function(){
	 				iTimer && clearTimeout(iTimer);
	 				closeTimer && clearTimeout(closeTimer);
	 				var _this = $(this);
	 				closeTimer = setTimeout(function(){
	 					_this.removeClass("active");
	 					$(".category_popup").eq(i).hide();
	 				},50)
	 			});
	 			$(".category_popup").hover(function(){
	 				categoryTimer && clearTimeout(categoryTimer);
	 				closeTimer && clearTimeout(closeTimer);
	 			},function(){
	 				$(".category_popup").hide();
	 				$(".category_popup_wrap").hide();
	 				$(".category .items").removeClass("active");
	 			})
	 		});
		});
 	</script>
 	
<%@ include file="include/footer.jsp" %>
 	
