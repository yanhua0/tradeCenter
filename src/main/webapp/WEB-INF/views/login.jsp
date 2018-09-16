<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head><title>用户登录</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="Shortcut icon" href="http://www.jq22.com/favicon.ico">
	<link href="static/css/style.css" rel="stylesheet" type="text/css" media="all" />
	<link rel="stylesheet" href="static/css/bg.css">
	<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
	<script>
        function getTimestamp() {
            var time = new Date();
            return time.getTime();
        }
        function changVerifyCode() {
            $('#verifyCodeImage').attr('src', 'getVerifyCode?' + getTimestamp());
        }

        function check(){
            if($("input[name='username']").val()==""){
                alert("用户名不能为空！");
                return false;
            }
            else if($("input[name='password']").val()==""){
                alert("密码不能为空");
                return false;
            }
            else if($("input[name='verifyCode']").val()==""){
                alert("验证码不能为空");
                return false;
            }
            return true;
        }
	</script>
	<link rel="stylesheet" href="static/css/bg.css">
	<style type="text/css">
		body{
			margin:0;
		}
		#bodyBox{
			width:1200px;
			height:600px;
			left:50%;
			margin-left:-600px;
			position:absolute;
			z-index:100;
		}
		#logoBox{
			width:1200px;
			height:150px;
		}
		#loginInfo{
			width:1200px;
			height:370px;
			margin-top:10px;
			display: none;
		}
		#loginForm{
			width:400px;
			height:350px;
			background-image:url("static/images/loginbj.png");
			margin-left:400px;
			padding:0px 50px;
			border-radius: 5px;
		}
		#loginErr{
			width:400px;
			height:40px;
			color:red;
			line-height:40px;
			font-size:15px;
		}
		.textBox{
			width:400px;
			height:40px;
			border-bottom:1px solid #fff;
			line-height: 40px;
		}
		#icon{
			width:40px;
			height:36px;
			float:left;
		}
		.txt{
			width:328px;
			height:40px;
			line-height:40px;
			border-top-style:none;
			border-left-style:none;
			border-right-style:none;
			border-bottom-style:none;
			background-color:transparent;
			font-size:20px;
			color:#fff;
			outline: none;
			font-family: YOUYUAN;
			float:right;
		}
		#btn{
			width:400px;
			height:50px;
			margin-top:40px;
			background-color: #18BADE;
			font-size: 20px;
			color:#fff;
			font-family: YOUYUAN;
			border:none;
			border-radius:6px;
			cursor: pointer;
		}
		#verificationCodeBox{
			width:400px;
			height:40px;
			margin-top:30px;
		}
		#verifyCode{
			width:190px;
			height:40px;
			background-color: #eee;
			float:right;
			color:#aaa;
			font-size:25px;
			line-height: 40px;
			text-align: center;
		}
		::-webkit-input-placeholder{
			color:#ddd;
			font-size:15px;
		}
		::-moz-placeholder{
			color:#ddd;
			font-size:15px;
		}
		::-ms-input-placeholder{
			color:#ddd;
			font-size:15px;
		}
       h1{
		   font-size: 30px;
	   }
		#img{
			transition: all 1.6s;
			animation:myfirst 2s;
			animation-iteration-count:2;/*infinite*/
			-webkit-animation:myfirst 2s; /* Safari and Chrome */
			-webkit-animation-iteration-count:2;
		}
		#img:hover{
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
		img{
			max-width: 100%;
		}
	</style>
</head>
<body>
<div class="slideshow">
	<div class="slideshow-image" style="background-image: url('static/images/login/3.jpg')"></div>
	<div class="slideshow-image" style="background-image: url('static/images/login/2.jpg')"></div>
	<div class="slideshow-image" style="background-image: url('static/images/login/3.jpg')"></div>
	<div class="slideshow-image" style="background-image: url('static/images/login/2.jpg')"></div>
</div>
<div id="bodyBox">
	<div id="logoBox">
		<div style="width:200px;height:50px;margin-left:50px;"></div>
		<img id="img" src="static/images/5.png" width="400px"/>
	</div>
	<div id="loginInfo">
		<div id="loginForm">
			<div id="loginErr">
        <h1>${error}</h1>
			</div>
			<form action="check" method="post" onsubmit="return check()">
				<div class="textBox">
					<img src="static/images/uname1.png" id="icon"/>
					<input id="name" class="txt" type="text" name="username" placeholder="用户名" value="${users.username}"/>
				</div>
				<div class="textBox" style="margin-top:30px;">
					<img src="static/images/upwd1.png" id="icon"/>
					<input id="pwd" class="txt" type="password" name="password" placeholder="登录密码"  value="${users.password}"/>
				</div>
				<div id="verificationCodeBox">
					<div class="textBox" style="width:190px;float:left;">
						<img src="static/images/verifyCode1.png" id="icon"/>
						<input class="txt" type="text" name="verifyCode" placeholder="验证码" style="width:120px;margin-top:0px;float:right;" maxlength="4" value="${yzm}"/>
					</div>
					<div id="verifyCode">
						<img src="getVerifyCode" width="190px" height="40px" id="verifyCodeImage" title="换一张" onclick="changVerifyCode()" style="cursor: pointer;">
					</div>
				</div>
				<input id="btn" type="submit" value="登录">
			</form>
		</div>
	</div>
</div>
</body>
	<script>
		$(function () {
			$("#loginInfo").show(1000);
			if($("#name").val()==""){
                $("#name").val(1);
			}
            if($("#pwd").val()==""){
                $("#pwd").val(1);
            }
		})
	</script>
</html>