<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Login</title>
	<link href="resources/login.css" type="text/css" rel="stylesheet">
</head>
<body>
	
	<div>
		<!-- <div style="float:right;" id="github_iframe"></div> -->
	
	
	
		<a href="" style="color:white;font-size:14px;margin-top:15px;margin-right:15px;float:right"><spring:message code="close"/></a></div>
		
		<form class="pCenter" id="myForm" method="post" action="dologin">
			<h1 style="text-align:center;">单点登录系统</h1>
			<div class="row">
				<input class="ctrlInput icon1" type="text" name="username" placeholder="邮箱">
			</div>
			<div class="row">
				<input id="password" class="ctrlInput icon2" type="password" name="password" placeholder="密码">
			</div>
	
			<div class="row submit">
				<input type="submit" class="ctrlBtn" value="登录"> <label class="error tCenter">${error}</label>
			</div>
			<div class="logoBottom">
				<span class="glyphicon glyphicon-user"></span>
			</div>
		</form>
		
		<script type="text/javascript" color="255,0,0" opacity='0.7' zIndex="-2" count="200" src="resources/canvas-nest.js"></script>
		<script type="text/javascript">
		    function async_load() {
		        //async load github
		        var i = document.createElement("iframe");
		        i.src = "https://ghbtns.com/github-btn.html?user=hustcc&repo=canvas-nest.js&type=star&count=true";
		        i.scrolling = "no";
		        i.frameborder = "0";
		        i.border = "0";
		        i.setAttribute("frameborder", "0", 0);
		        i.width = "100px";
		        i.height = "20px";
		        document.getElementById("github_iframe").appendChild(i);
		    }
		    if (window.addEventListener) {window.addEventListener("load", async_load, false);}
		    else if (window.attachEvent) {window.attachEvent("onload", async_load);}
		    else {window.onload = async_load;}
		</script>
	</div>
</body>
</html>
