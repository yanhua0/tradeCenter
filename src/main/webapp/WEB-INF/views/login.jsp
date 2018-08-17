<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head><title>国电煤炭电商平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="Shortcut icon" href="http://www.jq22.com/favicon.ico">
	<link href="static/css/style.css" rel="stylesheet" type="text/css" media="all" />
	<link rel="stylesheet" href="static/css/bg.css">
	<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
	<style>

		.theme{
			
			transition: all 1.6s;  
			animation:myfirst 2s;
			animation-iteration-count:1;/*infinite*/
			-webkit-animation:myfirst 2s; /* Safari and Chrome */
			-webkit-animation-iteration-count:1;
			z-index:5;
			position:absolute;
		}
		.theme:hover{
			/*transform: scale(1.5);  */
			transform: rotateY(360deg);

		}
		@keyframes myfirst
		{
			from {transform: rotateY(0deg);}
			to {transform: rotateY(360deg);}
		}
		/* Safari and Chrome */
		@-webkit-keyframes myfirst 
		{
			from {transform: rotateY(0deg);}
			to {transform: rotateY(360deg);}
		}
		#box{
			position:absolute;
			z-index:5;
			display: none;
		}
		/*@keyframes css*/
		/*{*/
			/*from {transform: rotate(0deg);}*/
			/*to {transform: rotate(360deg);}*/
		/*}*/
		/*!* Safari and Chrome *!*/
		/*@-webkit-keyframes css*/
		/*{*/
			/*from {transform: rotate(0deg);}*/
			/*to {transform: rotate(360deg);}*/
		/*}*/
	</style>

</head>
<body>
	<div class="slideshow">
		<div class="slideshow-image" style="background-image: url('static/images/02.png')"></div>
		<div class="slideshow-image" style="background-image: url('static/images/03.jpg')"></div>
		<div class="slideshow-image" style="background-image: url('static/images/04.png')"></div>
		<div class="slideshow-image" style="background-image: url('static/images/02.png')"></div>
	</div>
	<div class="padding-all">
		<div class="header">
			<h1><img class="theme" src="static/images/5.png" alt=" "></h1>
		</div>
		<div class="header1">
			
		</div>

		<div class="design-w3l">
			<div class="mail-form-agile" id="box">
				<form action="check" method="post">
					<h1 style="color: red;">${error}</h1>
					<input type="text" name="username" placeholder="用户：" value="1" />
					<input type="password"  name="password" class="padding" placeholder="密码："  value="1"/>
					<input type="submit" value="登录">
				</form>
			</div>
			<div class="clear"> </div>
		</div>

	</div>
</body>
<script>

	$("#box").fadeIn(1000);
</script>
</html>