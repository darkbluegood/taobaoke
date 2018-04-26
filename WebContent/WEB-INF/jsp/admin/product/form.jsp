<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加商品</title>

<script src="https://unpkg.com/vue"></script>
<script type="text/javascript" src="/static/common/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />

<script type="text/javascript" src="/static/common/js/colpick.js"></script>
<link rel="stylesheet" href="/static/common/css/colpick.css" type="text/css"/>
	<style type="text/css">
		*{margin:0;padding:0;}
		ul,ol{ list-style:none outside none; }
		.small_images{  width:334px;}
		.small_images li{ float:left;margin-left:3px;  }
		.small_images li img{ display:block;border:1px solid #ccc }
	</style>
</head>
<body >


	<a href="form" class="btn btn-info">刷新</a>
	<form:form class="form-horizontal" name="infoForm" action="add" method="POST">
		<div id="vueModel">
		<input type="hidden" value="" name="convert" />
		  <div class="form-group">
		    <label for="pid" class="col-sm-2 control-label">商品ID</label>
		    <div class="col-sm-6">
		      <input type="text" v-model="num_iid" class="form-control" name="num_iid" id="pid" placeholder="请填写商品ID" />
		    </div>
		    <div class="col-sm-4">
		    	<button type="button" v-on:click="get_data" class="btn btn-info">获取</button>
		    	如:https://detail.tmall.com/item.htm?id=<strong style="color:red">543139800949</strong>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="title" class="col-sm-2 control-label">标题</label>
		    <div class="col-sm-6" >
		    	<!-- <input type="text" v-model="title" class="form-control" placeholder="请填写标题" id="title" /> -->
		    	<form:input path="title" v-model="title" class="form-control" placeholder="请填写标题" id="title" />
		    	<%-- <form:input path="title" v-model="title" class="form-control" placeholder="请填写标题" id="title" /> --%>
		    	<%-- <form:input path="title" ng-model="title" class="form-control" placeholder="请填写标题" id="title" /> --%>
		    </div>
		  </div>
		   <div class="form-group">
		  	<label for="picker" class="col-sm-2 control-label">颜色</label>
		  	<div class="col-sm-6">
				<input type="text" name="color" class="form-control" id="picker" />
			</div>
			<div class="col-sm-4">
				演示:<strong id="demo-font">文字颜色</strong> <span id="demo-block" style="padding:8px;vertical-align: middle;    display: inline-block;"></span>
			</div>
		  </div>
		  <div class="form-group">
		    <label for="description" class="col-sm-2 control-label">说明</label>
		    <div class="col-sm-6">
	
		    	<form:input path="description" class="form-control" placeholder="请填写商品描述" id="description" />
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="author" class="col-sm-2 control-label">作者</label>
		    <div class="col-sm-6">
		      <input type="text" name="author" class="form-control" value="${author}" id="author" readonly="readonly" placeholder="请填写作者">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">商品主图</label>
		    <div class="col-sm-6">
		    	<div class="pull-left pict_url">
		      		<img v-bind:src="pict_url" width="218" height="218" />
		      		<input type="hidden" name="pict_url" v-model="pict_url"  />
		    	</div>
		    	<ul class="pull-left small_images">
		    		<input type="hidden" name="small_images" v-model="small_images" />
		    		<li v-for="d in small_images.split(',')"><img v-bind:src="d" width="80" height="80" /></li>
		    	</ul>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="reserve_price" class="col-sm-2 control-label">一口价</label>
		    <div class="col-sm-4">
		      <input type="text" v-model="reserve_price" readonly="readonly" name="reserve_price" class="form-control" id="reserve_price" placeholder="请填写一口价">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="zk_final_price" class="col-sm-2 control-label">折扣价</label>
		    <div class="col-sm-4">
		      <input type="text" v-model="zk_final_price" readonly="readonly" name="zk_final_price" class="form-control"  id="zk_final_price" placeholder="请填写折扣价">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="user_type" class="col-sm-2 control-label">卖家类型</label>
		    <div class="col-sm-4">
		      <input type="text" v-model="user_type" readonly="readonly" name="user_type" class="form-control" id="user_type" placeholder="请填写卖家类型">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="provcity" class="col-sm-2 control-label">宝贝所在地</label>
		    <div class="col-sm-4">
		      <input type="text" v-model="provcity" readonly="readonly" name="provcity" class="form-control" id="provcity" placeholder="请填写宝贝所在地">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="item_url" class="col-sm-2 control-label">商品地址</label>
		    <div class="col-sm-4">
		      <input type="text" v-model="item_url" readonly="readonly" name="item_url" class="form-control" id="item_url" placeholder="请填写商品地址">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="nick" class="col-sm-2 control-label">卖家昵称</label>
		    <div class="col-sm-4">
		      <input type="text" v-model="nick" readonly="readonly" name="nick" class="form-control" id="nick" placeholder="请填写卖家昵称">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="seller_id" class="col-sm-2 control-label">卖家id</label>
		    <div class="col-sm-4">
		      <input type="text" v-model="seller_id" readonly="readonly" name="seller_id" class="form-control" id="seller_id" placeholder="请填写卖家id">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="volume" class="col-sm-2 control-label">30天销量</label>
		    <div class="col-sm-4">
		      <input type="text" v-model="volume" readonly="readonly" name="volume" class="form-control" id="volume" placeholder="请填写30天销量">
		    </div>
		  </div>
		  <%-- <div class="form-group">
		    <label for="category" class="col-sm-2 control-label">分类</label>
		    <div class="col-sm-4">
		      	<select class="form-control" name="category" id="category">
		      		<c:forEach var="item" items="${categoryList}">
		      			<option value="${item.id}:${item.title}">${item.title}</option>
		      			<c:forEach var="sub" items="${item.subset}">
		      				<option value="${item.id}:${item.title}|${sub.id}:${sub.title}">|--${sub.title}</option>
		      			</c:forEach>
		      		</c:forEach>
				</select>
		    </div>
		  </div> --%>
		  
		  <div class="form-group">
		    <label for="tag" class="col-sm-2 control-label">标签</label>
		    <div class="col-sm-4">
		    	<%--<select class="form-control" name="tag_id" id="tag">
		      		<option value="null">无</option>
		      		<c:forEach var="item" items="${tagList}">
		      			<option value="null">${item.typeName}</option>
		      			<c:forEach var="list" items="${item.tagList}">
		      				<option value="${list.id}">|--${list.title}</option>
		      				<c:forEach var="c" items="${list.subset}">
		      					<option value="${c.id}">|---------${c.title}</option>
					    	</c:forEach>
		      			</c:forEach>
		      		</c:forEach>
				</select>
				<select class="form-control" name="tag" id="tag">
		      		<option value="null">无</option>
		      		<c:forEach var="item" items="${tagList}">
		      			<option value="null">${item.typeName}</option>
		      			<c:forEach var="list" items="${item.tagList}">
		      				<option value="${list.id}:${list.title}">|--${list.title}</option>
		      				<c:forEach var="c" items="${list.subset}">
		      					<option value="${list.id}:${list.title}|${c.id}:${c.title}">|---------${c.title}</option>
					    	</c:forEach>
		      			</c:forEach>
		      		</c:forEach>
				</select>
		      	<select class="form-control" name="tag" id="tag" ng-model="tagValue" ng-change="tagSelect()">
		      		<option value="null">无</option>
		      		<c:forEach var="item" items="${tagList}">
		      			<option value="null">${item.title}</option>
		      			<c:forEach var="list" items="${item.tagList}">
		      				<option value='{"fid":${list.id},"ftitle":"${list.title}"}'>|--${list.title}</option>
		      				<c:forEach var="c" items="${list.subset}">
		      					<option value='{"fid":${list.id},"ftitle":"${list.title}","cid":${c.id},"ctitle":"${c.title}"}'>|---------${c.title}</option>
					    	</c:forEach>
		      			</c:forEach>
		      		</c:forEach>
				</select>
				<select class="form-control" name="tag" id="tag" ng-model="tagValue" ng-change="tagSelect()">
		      		<c:forEach var="item" items="${tagList}">
		      			<option value='null'>${item.title}</option>
		      			<c:forEach var="list" items="${item.subset}">
		      				<option value='{"fid":${list.id},"ftitle":"${list.title}"}'>|--${list.title}</option>
		      				<c:forEach var="c" items="${list.subset}">
		      					<option value='{"fid":${list.id},"ftitle":"${list.title}","cid":${c.id},"ctitle":"${c.title}"}'>|---------${c.title}</option>
					    	</c:forEach>
		      			</c:forEach>
		      		</c:forEach>
				</select> --%>
				<select class="form-control" v-model="tags" name="tag" id="tag" v-on:change="tagSelect">
		      		<c:forEach var="item" items="${tagList}">
		      			<option value='{"fid":${item.id},"ftitle":"${item.title}"}'>${item.title}</option>
		      			<c:forEach var="list" items="${item.subset}">
		      				<option value='{"fid":${list.id},"ftitle":"${list.title}"}'>|--${list.title}</option>
		      				<c:forEach var="c" items="${list.subset}">
		      					<option value='{"fid":${list.id},"ftitle":"${list.title}","cid":${c.id},"ctitle":"${c.title}"}'>|---------${c.title}</option>
					    	</c:forEach>
		      			</c:forEach>
		      		</c:forEach>
				</select>
		    </div>
		  </div>
			<input type="text" name="tag_f_id" v-model="tag_f_id" class="hide" />
			<input type="text" name="tag_id" v-model="tag_id" class="hide" />
			<input type="text" name="tag_f_title" v-model="tag_f_title" class="hide" />
			<input type="text" name="tag_title" v-model="tag_title" class="hide" />
			<input type="text" name="keyword" v-model="tag_keyword" class="hide" />
			
		<div class="form-group">
		    <label  class="col-sm-2 control-label"></label>
		    <div class="col-sm-4">
		    	<button type="button" v-on:click="submit_click" name="submitType" class="btn btn-danger">提交</button>
		    </div>
		</div>
	</div>
	</form:form>
	
	<!-- <div id="test">
		<input type="text" v-model="title" id="title" />
	</div> -->
	
	<script type="text/javascript">
		var checkInfo = false;
		var vueModel = new Vue({
			el : "#vueModel",
			data :{
				title : "",
				s_images : "",
				pict_url : "",
				small_images :"",
				reserve_price:"",
				zk_final_price : "",
				user_type : "",
				provcity : "",
				item_url : "",
				nick : "",
				seller_id : "",
				volume : "",
				tag_f_id : "",
				tag_f_title : "",
				tag_id : "",
				tag_title : "",
				tag_keyword : "",
				num_iid : "",
				tags : ""
			},
			methods : {
				tagSelect : function(ele){
					var value = ele.target.value;
					var tag = value == "null" ? {fid:0,ftitle:"",cid:0,ctitle:""} : JSON.parse(value);
					
					vueModel.tag_f_id = tag.fid;
					vueModel.tag_f_title = tag.ftitle;
					if(!tag.hasOwnProperty("cid") && !tag.hasOwnProperty("ctitle")){
						vueModel.tag_id = 0;
						vueModel.tag_title = ""; 
						vueModel.tag_keyword = tag.ftitle;
					}else{
						vueModel.tag_id = tag.cid;
						vueModel.tag_title = tag.ctitle;
						vueModel.tag_keyword = tag.ftitle+tag.ctitle;
					}
					
					
					

				},
				submit_click : function(){
					
					if(vueModel.num_iid == null || vueModel.num_iid == ""){
						alert("请输入商品ID");
						return false;
					}
					if(!Number(vueModel.num_iid)){
						alert("商品ID仅支持数字");
						return false;
					}
					
					if(vueModel.tags == ""){
						alert("请您选择标签");
						return false;
					}
					if(!checkInfo){
						alert("请您获取数据");
						return false;
					}
					document.getElementById("command").submit();
				},
				get_data : function(){
					if(vueModel.num_iid == null || vueModel.num_iid == ""){
						alert("请输入商品ID");
						return false;
					}
					if(!Number(vueModel.num_iid)){
						alert("商品ID仅支持数字");
						return false;
					}
					
					
					
					$.ajax({
						type : "POST",
						url : "itemInfoData",
						data : {num_iid:vueModel.num_iid},
						success : function(data){
							
							var data = JSON.parse(data);
							console.log(data)
							if(data.length == 0){
								alert("此商品不支持淘宝客推广");
							}else{
								var data = data[0];
								if(data.hasOwnProperty("code") && 
										data.code == -1 && 
										data.hasOwnProperty("msg")){
									alert(data.msg)
								}else{
									data["small_images"] = data["small_images"] ? data["small_images"].string.toString() : "";
									//console.log(data["small_images"])
									for(var keys in data){
										vueModel[keys] = data[keys];
									}
									checkInfo = true;
								}

							}
						}
					})
					
				}
			}
		})
		
		$(function(){
			$('#picker').colpick({

				layout:'hex',

				submit:0,

				colorScheme:'dark',

				onChange:function(hsb,hex,rgb,el,bySetColor) {

					$(el).css('border-color','#'+hex);
					$("#demo-font").css("color","#"+hex);
					$("#demo-block").css("background","#"+hex);
					
					// Fill the text box just if the color was set using the picker, and not the colpickSetColor function.

					if(!bySetColor) $(el).val("#"+hex);

				}

			}).keyup(function(){

				$(this).colpickSetColor(this.value);

			});
		});
	</script>
</body>
</html>