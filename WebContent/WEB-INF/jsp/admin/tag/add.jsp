<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<script type="text/javascript" src="/static/common/js/jquery.min.js"></script>
<script type="text/javascript" src="/static/common/js/colpick.js"></script>
<link rel="stylesheet" href="/static/common/css/colpick.css" type="text/css"/>
<title>Insert title here</title>
<script src="https://unpkg.com/vue"></script>
</head>
<body>
<div id="form">
	<form:form class="form-horizontal" name="infoForm" action="executeAdd1" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="fid" value="${tag.fid}" />
	  <div class="form-group">
	    <label for="title" class="col-sm-2 control-label">导航名称</label>
	    <div class="col-sm-6">
	      <input type="text" class="form-control" v-model="title" name="title" id="title" placeholder="请填写导航名称" />
	    </div>
	  </div>
	  <div class="form-group">
	  	<label for="picker" class="col-sm-2 control-label">颜色</label>
	  	<div class="col-sm-6">
			<input type="text" class="form-control" id="picker" name="color" />
		</div>
		<div class="col-sm-4">
			演示:<strong id="demo-font">文字颜色</strong> <span id="demo-block" style="padding:8px;vertical-align: middle;    display: inline-block;"></span>
		</div>
	  </div>
	  <div class="form-group">
	    <label for="description" class="col-sm-2 control-label">描述</label>
	    <div class="col-sm-6">
	      <input type="text" class="form-control" name="description" id="description" placeholder="请填写描述" />
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="sort" class="col-sm-2 control-label">排序</label>
	    <div class="col-sm-6">
	      <input type="text" class="form-control" v-model="sort" name="sort" id="sort" placeholder="请填写排序ID" />
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="keyword" class="col-sm-2 control-label">关键字</label>
	    <div class="col-sm-6">
	      <input type="text" class="form-control" name="keyword" id="keyword" placeholder="请填写关键字" />
	    </div>
	    <div class="col-sm-4">
	    	多个请用","隔开  如:"鞋,零食"
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="url" class="col-sm-2 control-label">自定义链接</label>
	    <div class="col-sm-6">
	      <input type="text" class="form-control" name="url" id="url" placeholder="请填写URL" />
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="image" class="col-sm-2 control-label">图片</label>
	    <div class="col-sm-6">
	      <input type="file" name="image" id="image" />
	    </div>
	  </div>
	  <%-- <c:if test='${tag.fid == 0}'>
		  <div class="form-group">
		    <label for="sort" class="col-sm-2 control-label">类型</label>
		    <div class="col-sm-6">
		      <select name="type" class="form-control">
		      	<c:forEach var="item" items="${itype}">
		      		<option value="${item.id}:${item.title}">${item.title}</option>
		      	</c:forEach>
		      </select>
		    </div>
		    <div class="col-sm-4">
		    	<a href="addTypeView" class="btn btn-info">新建类型</a>
		    </div>
		  </div>
	  </c:if> --%>
	  <%-- <c:if test='${tag.fid != 0}'>
		  <div class="form-group">
		    <label for="sort" class="col-sm-2 control-label">父类标签</label>
		    <div class="col-sm-6">
		    	<input type="hidden" name="type" value="${tag.type}:${tag.type_name}" />
		      <span>${tag.type_name} - ${tag.title}</span>
		    </div>
		  </div>
	  </c:if> --%>
	  <div class="form-group">
	    <label for="sort" class="col-sm-2 control-label">父类标签</label>
	    <div class="col-sm-6">
	      <span>${tag.type_name} - ${tag.title}</span>
	    </div>
	  </div>
	  <div class="form-group">
	  	<label for="" class="col-sm-2 control-label"></label>
	    <div class="col-sm-6">
	    	<a href="javascript:void(0)" v-on:click="submits" class="btn btn-danger">添加</a>
	    </div>
	  </div>
	</form:form>
</div>
	<script type="text/javascript">
	var form = new Vue({
		el : "#form",
		data : {
			sort : null,
			title : null
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