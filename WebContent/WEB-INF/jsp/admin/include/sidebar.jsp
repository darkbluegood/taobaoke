<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<aside class="layout-side">
	<ul class="side-menu">
		
	   <li class="menu-header menu-item">主菜单</li>
	   <li class="menu-item">
	   		<a href="${adminPath}config/index"><i class="icon-font "></i><span>网站设置</span><i class="icon-font icon-right"></i></a>
	   		<ul class="menu-item-child" id="menu-child-3">
		    </ul>
	   </li>
	   <li class="menu-item">
	   		<a href="${adminPath}tag/index"><i class="icon-font "></i><span>标签</span><i class="icon-font icon-right"></i></a>
	   		<ul class="menu-item-child" id="menu-child-3">
		    </ul>
	   </li>
	   <%-- <li class="menu-item">
	   		<a href="${adminPath}category/index"><i class="icon-font "></i><span>分类</span><i class="icon-font icon-right"></i></a>
	   		<ul class="menu-item-child" id="menu-child-3">
		    </ul>
	   </li> --%>
	   <li class="menu-item">
	   		<a href="${adminPath}banner/index"><i class="icon-font "></i><span>幻灯片</span><i class="icon-font icon-right"></i></a>
	   		<ul class="menu-item-child" id="menu-child-3">
		    </ul>
	   </li>
	   <li class="menu-item">
	   		<a href="${adminPath}product/form"><i class="icon-font "></i><span>添加商品</span><i class="icon-font icon-right"></i></a>
	   		<ul class="menu-item-child" id="menu-child-3">
		    </ul>
	   </li>
	   <li class="menu-item">
	   		<a href="${adminPath}product/list?pageNo=1"><i class="icon-font "></i><span>商品列表</span><i class="icon-font icon-right"></i></a>
	   		<ul class="menu-item-child" id="menu-child-3">
		    </ul>
	   </li>
	   <%-- <li class="menu-item">
	   		<a href="${adminPath}product/list?pageNo=1"><i class="icon-font "></i><span>商品管理</span><i class="icon-font icon-right"></i></a>
		    <ul class="menu-item-child" id="menu-child-3">
		     	<li><a href="${adminPath}product/form"><i class="icon-font"></i><span>添加商品</span></a></li>
		    	<li><a href="${adminPath}product/list?pageNo=1"><i class="icon-font"></i><span>商品列表</span></a></li>
		    </ul>
	    </li> --%>
	   <!-- <li class="menu-item"><a href=""><i class="icon-font "></i><span>订单管理</span><i class="icon-font icon-right"></i></a>
	    <ul class="menu-item-child" id="menu-child-6">
	     <li><a href="home3.html"><i class="icon-font"></i><span>已付款</span></a></li>
	     <li><a href="home4.html"><i class="icon-font"></i><span>未付款</span></a></li>
	    </ul></li> -->
	   <li class="menu-header menu-item">框架案例</li>
	   <li class="menu-item"><a href=""><i class="icon-font"></i><span>新功能</span></a></li>
	   <li class="menu-item"><a href=""><i class="icon-font "></i><span>多级</span><i class="icon-font icon-right"></i></a>
	    <ul class="menu-item-child" id="menu-child-10">
	     <li><a href=""><i class="icon-font"></i><span>一级</span></a></li>
	     <li><a href=""><i class="icon-font "></i><span>一级</span><i class="icon-font icon-right"></i></a>
	      <ul class="menu-item-child" id="menu-child-12">
	       <li><a href=""><i class="icon-font"></i><span>二级</span></a></li>
	       <li><a href=""><i class="icon-font "></i><span>二级</span><i class="icon-font icon-right"></i></a>
	        <ul class="menu-item-child" id="menu-child-14">
	         <li><a href=""><i class="icon-font"></i><span>三级</span></a></li>
	         <li><a href=""><i class="icon-font "></i><span>三级</span><i class="icon-font icon-right"></i></a>
	          <ul class="menu-item-child" id="menu-child-16">
	           <li><a href=""><i class="icon-font"></i><span>四级</span></a></li>
	           <li><a href=""><i class="icon-font"></i><span>四级</span></a></li>
	          </ul></li>
	        </ul></li>
	      </ul></li>
	    </ul></li>
	  </ul>
</aside>