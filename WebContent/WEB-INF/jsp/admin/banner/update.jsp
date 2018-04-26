<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<title>Insert title here</title>
<script src="https://unpkg.com/vue"></script>
<script type="text/javascript" src="/static/common/js/jquery.min.js"></script>
</head>
<body>
<div id="form">
	<form:form class="form-horizontal" name="infoForm" action="iupdate" method="POST" enctype="multipart/form-data">
		<form:input path="id" class="form-control" id="id" style="display:none"></form:input>
	  <div class="form-group">
	    <label for="title" class="col-sm-2 control-label">标题</label>
	    <div class="col-sm-6">
	    	<form:input path="title" class="form-control" v-model="title" id="title"></form:input>
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="sort" class="col-sm-2 control-label">排序ID</label>
	    <div class="col-sm-6">
	    	<form:input path="sort" class="form-control" v-model="sort" id="sort"></form:input>
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="url" class="col-sm-2 control-label">URL</label>
	    <div class="col-sm-6">
	    	<form:input path="url" class="form-control" id="url"></form:input>
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="image" class="col-sm-2 control-label">图片</label>
	    <div class="col-sm-6">
	      <input type="file" name="image" id="image" />
	    </div>
	  </div>
	  <div class="form-group">
	  		<label for="image" class="col-sm-2 control-label"></label>
		    <div class="col-sm-6">
		      <img src="${imageUrl}" width="150" />
		    </div>
	  </div>
	  <div class="form-group">
	  	<label for="" class="col-sm-2 control-label"></label>
	    <div class="col-sm-6">
	    	<a href="javascript:void(0)" v-on:click="submits" class="btn btn-danger">更新</a>
	    </div>
	  </div>
	</form:form>
	</div>
	<script type="text/javascript">
	var form = new Vue({
		el : "#form",
		data : {
			sort : "${command.sort}",
			title : "${command.title}"
		},
		methods :{
			submits : function(){
				
				if(form.sort == null || form.title == null || form.sort == "" || form.title == ""){
					alert("不能为空")
					return;
				}
				
				var r = /^\+?[0-9][0-9]*$/;
				if(!r.test(form.sort)){
					alert("排序仅支持数字");
					return;
				}
				$("#command").submit();
			}
		}
	})
	</script>
</body>
</html>