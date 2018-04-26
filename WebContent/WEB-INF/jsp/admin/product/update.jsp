<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加商品</title>
<script type="text/javascript" src="/static/common/js/jquery.min.js"></script>
<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>
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
<body ng-app="app" ng-controller="validateCtrl">
	<a href="update?id=${command.id}" class="btn btn-info">刷新</a>
	<form:form class="form-horizontal" name="infoForm" action="iupdate" method="POST">
	
	
		<div class="form-group">
	    	<label for="title" class="col-sm-2 control-label">标题</label>
		    <div class="col-sm-4">
		  		<form:input path="title" class="form-control" id="title" ></form:input>
		  	</div>
	  	</div>
	
		<div class="form-group">
	    	<label for="description" class="col-sm-2 control-label">说明</label>
		    <div class="col-sm-4">
		  		<form:input path="description" class="form-control" id="description" ></form:input>
		  	</div>
	  	</div>
	
		<div class="form-group">
		  	<label for="picker" class="col-sm-2 control-label">颜色</label>
		  	<div class="col-sm-4">
				<form:input path="color" class="form-control" id="picker" ></form:input>
			</div>
			<div class="col-sm-6">
				演示:<strong id="demo-font" style="color:${command.color}">文字颜色</strong> <span id="demo-block" style="padding:8px;vertical-align: middle;    display: inline-block;background:${command.color}"></span>
			</div>
		</div>
	
	  <div class="form-group">
	    <label for="tag" class="col-sm-2 control-label">标签</label>
	    <div class="col-sm-4">
	      	<select class="form-control" name="tag" id="tag" ng-model="tagValue" ng-change="tagSelect()">
	      		<option value="null">无</option>
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
	  
	  	<form:input path="id" class="hide"></form:input>
	  	<form:input path="tag_f_id" ng-model="tag_f_id" class="hide"></form:input>
	  	<form:input path="tag_id" ng-model="tag_id" class="hide"></form:input>
	  	<form:input path="tag_f_title" ng-model="tag_f_title" class="hide"></form:input>
	  	<form:input path="tag_title" ng-model="tag_title" class="hide"></form:input>
	  	<form:input path="keyword" ng-model="tag_keyword" class="hide"></form:input>
	<div class="form-group">
	    <label class="col-sm-2 control-label"></label>
	    <div class="col-sm-4">
	    	<button type="submit" name="submitType" class="btn btn-danger">更新</button>
	    </div>
	  </div>  
		  
	</form:form>
	<script type="text/javascript">
		var app = angular.module("app",[]);
		app.controller("validateCtrl",["$scope","$http",function($scope,$http){
			$scope.tag_f_id = ${command.tag_f_id}
			$scope.tag_f_title = "${command.tag_f_title}"
			$scope.tag_id = ${command.tag_id} 
			$scope.tag_title = "${command.tag_title}"
			//$scope.tagValue = '{"fid":'+${command.tag_f_id}+'",ftitle":'+'"${command.tag_f_title}"'+',"cid":'+${command.tag_id} +',"ctitle":'+'"${command.tag_title}"'+'}'
			var cid = ${command.tag_id},ctitle = "${command.tag_title}";
			if(cid == 0 && ctitle.length == 0){
				$scope.tagValue = JSON.stringify({"fid" : ${command.tag_f_id},"ftitle" : "${command.tag_f_title}"});
			}else{
				$scope.tagValue = JSON.stringify({"fid" : ${command.tag_f_id},"ftitle" : "${command.tag_f_title}","cid" : ${command.tag_id},"ctitle" : "${command.tag_title}"});
			}
			$scope.tagSelect = function(){
				/* if(this.tagValue == "null"){
					alert("请选择分类!")
					return ;
				} */
				var tag = this.tagValue == "null" ? {fid:0,ftitle:"",cid:0,ctitle:""} : JSON.parse(this.tagValue);
				$scope.tag_f_id = tag.fid;
				$scope.tag_f_title = tag.ftitle;
				if(!tag.hasOwnProperty("cid") && !tag.hasOwnProperty("ctitle")){
					$scope.tag_id = 0;
					$scope.tag_title = ""; 
					$scope.tag_keyword = tag.ftitle;
				}else{
					$scope.tag_id = tag.cid;
					$scope.tag_title = tag.ctitle;
					$scope.tag_keyword = tag.ftitle+tag.ctitle;
				}
				console.log(JSON.parse(this.tagValue));
			}
		}]);
		$(function(){
			$('#picker').colpick({

				layout:'hex',

				submit:0,

				colorScheme:'dark',
				color:'${command.color}',

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