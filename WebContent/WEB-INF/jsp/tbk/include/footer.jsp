<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="footer">
	<div class="w1190">
		${webInfo.copyright}
	</div>
</div>
 </body>
 <script type="text/javascript">
 		var i=0,busy = false;
 		var loading = new Vue({
			el : "#loading",
			data : {
				isShow : true
			}
		})
 		var best = new Vue({
 			el : "#best",
 			data : {
 				isMore : null,
 				best : []
 			},
 			methods : {
 				loadMore : function(){
 					if(busy){
 	 					return;
 	 				}
 	 				busy = true;
 	 				$.ajax({
 	 	 	 			type : "POST",
 	 	 	 			url : "api/best",
 	 	 	 			data : {
 	 	 					page : ++i,
 	 	 					max :25
 	 	 		 		},
 	 	 	 			success : function(data){
 	 	 	 				var data = JSON.parse(data);
 	 	 	 				best.isMore = data.isMore;
	 	 	 	 			for(var i=0; i<data.products.length; i++){
	 	 	 	 				best.best.push(data.products[i])
	 	 	 				}
 	 	 	 				busy = false;
 	 	 	 			}
 	 	 	 		});
 				}
 			}
 		});
 		

 			$.ajax({
 	 			type : "POST",
 	 			url : "api/best",
 	 			data : {
 					page : i,
 					max :40
 		 		},
 	 			success : function(data){
 	 				var data = JSON.parse(data);
 	 				best.isMore = data.isMore;
 	 				best.best=data.products;
 	 				busy = false;
 	 				loading.isShow = false;
 	 			}
 	 		});
 		
 		
 		
 		$(function(){
			var status = "1";
			$("#search_submit").on("click",function(){
				if(status == "1" || $("#searchBox").val().length <= 0){
					window.location='search?keyword='+'${keyword}';
				}else if(status == "2" || $("#searchBox").val().length >0){
					window.location='search?keyword='+$("#searchBox").val();
				}
				
			});
			$("#searchBox").on("blur",function(){
				if($("#searchBox").val().length >0){
					status = "2";
				}else if($("#searchBox").val().length <= 0){
					status = "1";
				}
			});
			$("#searchBox").on("keyup",function(event){
				if(event.which == 13){
					if($("#searchBox").val().length >0){
						window.location='search?keyword='+$("#searchBox").val();
					}else if($("#searchBox").val().length <= 0){
						window.location='search?keyword='+'${keyword}';
					}
				}
			});
		});
 	
 		
 		
 	</script>
 <script type="text/javascript">
    (function(win,doc){
        var s = doc.createElement("script"), h = doc.getElementsByTagName("head")[0];
        if (!win.alimamatk_show) {
            s.charset = "gbk";
            s.async = true;
            s.src = "https://alimama.alicdn.com/tkapi.js";
            h.insertBefore(s, h.firstChild);
        };
        var o = {
            pid: "mm_10096859_33570397_122572608",/*推广单元ID，用于区分不同的推广渠道*/
            appkey: "",/*通过TOP平台申请的appkey，设置后引导成交会关联appkey*/
            unid: "",/*自定义统计字段*/
            type: "click" /* click 组件的入口标志 （使用click组件必设）*/
        };
        win.alimamatk_onload = win.alimamatk_onload || [];
        win.alimamatk_onload.push(o);
    })(window,document);
</script>
 
</html>