<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" %>
<html>
<head>
	<title>Login</title>
	<link href="resources/css/iview.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="resources/js/vue.min.js"></script>
	<script type="text/javascript" src="resources/js/iview.min.js"></script>
	<style>
		.ivu-form{
			text-align: center;
    		background: #F3F3F3;
    		padding: 1px;
		}
		.ivu-input, .ivu-btn{
			margin-top:15px;
			width:80%;
		}
		.ivu-icon{
			right:30px;
			top: 15px;
		}
	</style>
</head>
<body">
	<div id="login" style="width:25%;margin:200px auto;">
		<i-form class="myForm">
			<h2 style="text-align:center;margin-top:15px;margin-bottom:20px;">单点登录系统</h2>
	    	<form-item>
	            <i-input v-model="username" placeholder="请输入用户名..." type="email" name="username" clearable >{{username}}</i-input>
	        </form-item>
	        <form-item>
	            <i-input v-model="password" placeholder="请输入密码..." type="password" name="password" clearable >{{password}}</i-input>
	        </form-item>
	        <form-item>
	        	<i-button type="primary" v-on:click="submit"  long>确定</i-button>
	        </form-item>
	    </i-form>
		
	</div>
	
	<script type="text/javascript" color="110,0,0" opacity='0.7' zIndex="-2" count="300" src="resources/js/canvas-nest.js"></script>
	<script src="resources/js/jquery-1.10.2.min.js"></script>
	<script src="resources/js/config.js"></script>
	
	<script>
		/* 获取URL中参数 */
		function GetRequest() {  
		   var url = location.search; //获取url中"?"符后的字串  
		   var theRequest = new Object();  
		   if (url.indexOf("?") != -1) {  
		      var str = url.substr(1);  
		      strs = str.split("&");  
		      for(var i = 0; i < strs.length; i ++) {  
		         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);  
		      }  
		   }  
		   return theRequest;  
		}  
		var vm = new Vue({
			el:"#login",
			data:{
				urlParams:"",	//验证成功后跳转的网址数据
				username:"",
				password:"",
				aoData:{username:"",password:""}
			},
			methods:{
				submit: function(){
					this.aoData.username = this.username;
					this.aoData.password = this.password;
					var that = this;
			    	$.ajax({
			            "dataType":'json',
			            "type":"post",
			            "url":config.ajaxUrls.loginHome,
			            "data":this.aoData,
			            "success": function (response) {
		                	console.log("res",response);
			                if(response.success===false){
			                	that.$Notice.error({title:response.message});
			                }else{
			                	that.$Notice.success({title:response.message + "  2秒钟后进行页面跳转"});
			                	setTimeout(function(){					//延时两秒进行页面跳转
	                				location.href = that.urlParams.aaa;
	                			},2000);
			                }
			            }
			        });	 
				}
			},
			created:function(){
				this.urlParams = GetRequest();	//获取url中的参数		http://localhost:8080/jianmasso/login?aaa="http://www.baidu.com"
			}
		})
	</script>
</body>
</html>
