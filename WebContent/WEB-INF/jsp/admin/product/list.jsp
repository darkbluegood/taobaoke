<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://unpkg.com/vue"></script>
<script type="text/javascript" src="/static/common/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<style type="text/css">
	.ltable{ width:2700px; }
	.ltable>tbody>tr>td{ vertical-align:middle }
	.small_images{ display:inline-block;margin-right:2px;margin-bottom:2px; }
	.title{/* width:300px; */overflow: hidden;white-space: nowrap;text-overflow: ellipsis;}
	.refresh{ margin:10px 0 }
	p{ margin:0; }
	.search{ width:400px; }
</style>
<title>商品列表</title>
</head>
<body>
	
	<div class="row refresh">
		<div class="col-md-1">
			<div>
				<a href="list?pageNo=1" class="btn btn-info">刷新</a>
			</div>
		</div>
		
		<div class="col-md-11">
			<div class="search pull-right" id="search">
				<div class="row">
					<div class="col-md-10">
						<input type="text" v-model="id" placeholder="输入商品ID" class="form-control" />
					</div>
					<div class="col-md-2">
						<input type="submit" value="查找" v-on:click="getList" class="btn btn-danger" />
					</div>
				</div>
				
			</div>
		</div>
	</div>

	<p id="loading" id="loading" v-if="isShow">载入中...</p>
	
	<!-- <ul id="test">
		<li v-for="data in productList">{{data.title}}</li>
	</ul> -->
	<table class="table table-hover ltable table-responsive" id="test">
		<tr>
			<th>操作</th>
			<th><input type="radio" /></th>
			<th>ID</th>
			<th>标题</th>
			<th>标签父ID</th>
			<th>父标签</th>
			<th>关键字</th>
			<th>主图</th>
			<th>小图</th>
			<th>一口价</th>
			<th>折扣价</th>
			<th>卖家类型</th>
			<th>所在地</th>
			<th>商品链接</th>
			<th>卖家昵称</th>
			<th>卖家ID</th>
			<th>销量</th>
			<th>作者</th>
			<th>淘宝客链接</th>
			<th>编号</th>
			<th>日期</th>
		</tr>
		<tr v-for="data in productList">
			<td><a :href="'update?id='+data.id">编辑</a></td>
			<td><input type="radio" name="select" /></td>
			<td>{{data.num_iid}}</td>
			<td><p class="title">{{data.title}}</p></td>
			<td>{{data.tag_f_id}}</td>
			<td>{{data.tag_f_title}}</td>
			<td>{{data.keyword}}</td>
			<td><img :src="data.pict_url" width="80" /></td>
			<td>
				<img :src="img" class="small_images" width="60" v-for='img in data.small_images.split(",")' />
			</td>
			<td>{{data.reserve_price}}</td>
			<td>{{data.zk_final_price}}</td>
			<td>{{data.user_type}}</td>
			<td>{{data.provcity}}</td>
			<td><a :href="data.item_url" target="_blank">网址</a></td>
			<td>{{data.nick}}</td>
			<td>{{data.seller_id}}</td>
			<td>{{data.volume}}</td>
			<td>{{data.author}}</td>
			<td>无</td>
			<td>{{data.uuid}}</td>
			<td>{{data.createTime}}</td>
		</tr>
		<%-- <c:forEach var="item" items="${list}">
		<tr>
			<td><a href="update?id=${item.id}">编辑</a></td>
			<td><input type="radio" name="select" value="${item.id}" ng-click="toDelete(${item.id})" /></td>
			<td>${item.num_iid}</td>
			<td><p class="title">${item.title}</p></td>
			<td>${item.tag_f_id}</td>
			<td>${item.tag_f_title}</td>
			<td>${item.keyword}</td>
			<td><img src="${item.pict_url}" width="80" /></td>
			<td>
				<img v-bind:src="img" class="small_images" width="60" v-for='img in "${item.small_images}".split(",")' />
			</td>
			<td>${item.reserve_price}</td>
			<td>${item.zk_final_price}</td>
			<td>${item.user_type}</td>
			<td>${item.provcity}</td>
			<td><a href="${item.item_url}" target="_blank">网址</a></td>
			<td>${item.nick}</td>
			<td>${item.seller_id}</td>
			<td>${item.volume}</td>
			<td>${item.author}</td>
			<td>无</td>
			<td>${item.uuid}</td>
			<td>${item.createTime}</td>
		</tr>
		</c:forEach> --%>
	</table>
	
	<div class="text-center" id="nav">
		<nav aria-label="Page navigation">
		  <ul class="pagination">
		    <%-- <li>
		      <a href="list?pageNo=${pageNo-1 == 0 ? 1 : pageNo-1}" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li> --%>
		    <%-- <c:forEach var="i" begin="1" end="${pages}">
		    	<li class="${ i == pageNo ? 'active' : '' }" ><a href="list?pageNo=${i}"><c:out value="${i}" /></a></li>
		    </c:forEach> --%>
		    
		    <li v-bind:class="n == cur?'active':''" v-for="n in pages"><a href="javascript:void(0)" v-on:click="page(n)">{{n}}</a></li>
		    
		    <%-- <li>
		      <a href="list?pageNo=${pageNo+1 > pages ? pages : pageNo+1}" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li> --%>
		  </ul>
		</nav>
		<span class="label label-info">共{{count}}条</span>
	</div>
	
	
</body>

	<script type="text/javascript">
		
		
		var list = new Vue({
			el : "#test",
			data : {
				productList : []
			}
		});
		var nav = new Vue({
			el : "#nav",
			data : {
				pages : null,
				cur : 1,
				count : null
			},
			methods : {
				page : function(n){
					pages(n)
					console.log(n)
				}
			}
		});
		
		var loading = new Vue({
			el : "#loading",
			data : {
				isShow : true
			}
		})
		
		 var search = new Vue({
			el : "#search",
			data : {
				id : null
			},
			methods : {
				getList : function(){
					$.ajax({
						url : "productSearch",
						type : "POST",
						data : {
							pageNo : 1,
							num_iid : search.id
						},
						success : function(data){
							var data = JSON.parse(data);
							list.productList = data.list;
							nav.count = data.count;
							nav.pages = Math.ceil(data.count/10);
							console.log(nav.pages)
						}
					});
				}
			}
		}); 
		
		function pages(n){
			nav.cur = n;
			$.ajax({
				url : "productList",
				type : "POST",
				data : {
					pageNo : n
				},
				success : function(data){
					var data = JSON.parse(data);
					list.productList = data.list;
					nav.count = data.count;
					nav.pages = Math.ceil(data.count/10);
					console.log("4")
					loading.isShow = false;
				}
			});
		}
		pages(1);
		
		

	</script>

</html>