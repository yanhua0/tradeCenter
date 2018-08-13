<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>页面出错啦！</title>
    <style>
        body {
            padding:0px;
            margin:0px;
            background:hsl(212, 50%, 50%);
        }
        .sun {
            width:40px;
            height:40px;
            border-radius:360px;
            background:white;
            right:40px;
            top:-120px;
            position:absolute;
            animation-name: sunrise;
            animation-duration: 1s;
            animation-timing-function: ease;
            animation-iteration-count: 1;
            animation-direction: normal;
            animation-delay: .1;
            animation-play-state: running;
            animation-fill-mode: forwards;
        }
        @keyframes sunrise {
            0% {
                top: -120px;
            }
            25% {
                top:19px;
                right: 40px;
            }
            50% {
                top:25px;
                right: 40px;
            }
            100% {
                top:18px;
                right: 40px;
            }
        }
        .wmd1 {
            -webkit-transform: scale(.6);
            position:absolute;
            top:120px;
            left:200px;
            perspective: 1000px;
        }
        .base {
        }
        .blades {
            width: 350px;
            height: 350px;
            left: 10%;
            top: 10%;
            z-index:2;
            border-radius: 50%;
            position: absolute;
            margin-top: -30px;
            margin-left: 50px;
            animation: spin 6s linear infinite;
        }
        .blade1 {
            background: white;
            position:absolute;
            width:41px;
            height:139px;
            top:-10px;
            left:150.5px;
            transform:rotate(0deg);
            display:inline-block;
            background:
                    linear-gradient(135deg, transparent 20px, white 0),  linear-gradient(225deg, transparent 20px, white 0),  linear-gradient(315deg, transparent 20px, white 0),  linear-gradient(45deg, transparent 20px, white 0);
            background-position: top left, top right, bottom right, bottom left;
            background-size: 50% 50%;
            background-repeat: no-repeat;
        }
        .blade2 {
            background:white;
            position:absolute;
            width:41px;
            height:139px;
            top:105.5px;
            left:41px;
            transform:rotate(-90deg);
            display:inline-block;
            background:
                    linear-gradient(135deg, transparent 20px, white 0),  linear-gradient(225deg, transparent 20px, white 0),  linear-gradient(315deg, transparent 20px, white 0),  linear-gradient(45deg, transparent 20px, white 0);
            background-position: top left, top right, bottom right, bottom left;
            background-size: 50% 50%;
            background-repeat: no-repeat;
        }
        .blade3 {
            background:white;
            position:absolute;
            width:41px;
            height:139px;
            top:105.5px;
            right:41px;
            transform:rotate(-270deg);
            display:inline-block;
            background:
                    linear-gradient(135deg, transparent 20px, white 0),  linear-gradient(225deg, transparent 20px, white 0),  linear-gradient(315deg, transparent 20px, white 0),  linear-gradient(45deg, transparent 20px, white 0);
            background-position: top left, top right, bottom right, bottom left;
            background-size: 50% 50%;
            background-repeat: no-repeat;
        }
        .blade4 {
            background:white;
            position:absolute;
            width:41px;
            height:139px;
            bottom:-10px;
            left:150.5px;
            transform:rotate(180deg);
            display:inline-block;
            background:
                    linear-gradient(135deg, transparent 20px, white 0),  linear-gradient(225deg, transparent 20px, white 0),  linear-gradient(315deg, transparent 20px, white 0),  linear-gradient(45deg, transparent 20px, white 0);
            background-position: top left, top right, bottom right, bottom left;
            background-size: 50% 50%;
            background-repeat: no-repeat;
        }
        .vane1 {
            width:1px;
            height:350px;
            left:175px;
            background:white;
            position:absolute;
            transform:rotate(90deg);
        }
        .vane2 {
            width:1px;
            height:350px;
            left:171.5px;
            background:white;
            position:absolute;
            transform:rotate(180deg);
        }
        .base .bottom_base {
            position:absolute;
            width:90px;
            height:100px;
            left:162px;
            border-right: 16px solid transparent;
            border-left: 16px solid transparent;
            border-bottom: 380px solid white;
            opacity:.8;
            z-index:-1;
            top:42.5px;
        }
        ul {
            position:absolute;
            top:180px;
            left:-30px;
        }
        li {
            width:10px;
            height:10px;
            background:white;
            padding:2px;
            display:block;
            margin: 30px;
            box-shadow: inset 0px -2px 0px lightgray;
        }
        li:nth-child(2) {
            position:absolute;
            top:-45px;
            left:20px;
        }
        li:nth-child(1) {
            position:absolute;
            top:35px;
            left:50px;
        }
        li:nth-child(3) {
            position:absolute;
            top:75px;
            left:50px;
        }
        @keyframes spin {
            0% {
                transform:rotate(0deg);
            }
            100% {
                transform:rotate(-360deg);
            }
        }
        #big{
            font-size:20px;
            color:#fff ;
            text-align: center;
        }
    </style>
    <script src="static/js/prefixfree.min.js"></script>
</head>
<body>
<div id="big">{"errorCode":${message.errorCode},"errorInfo":${message.errorInfo}}</div>
<!-- the windmill -->
<div class="sun"></div>
<div class="wmd1">
    <div class="blades">
        <div class="blade2"></div>
        <div class="blade1"></div>
        <div class="vane1"></div>
        <div class="blade3"></div>
        <div class="blade4"></div>
        <div class="vane2"></div>
    </div>
    <div class="base">
        <div class="bottom_base">
            <ul>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </div>
    </div>
    <div class="wmd1">
        <div class="blades">
            <div class="blade2"></div>
            <div class="blade1"></div>
            <div class="vane1"></div>
            <div class="blade3"></div>
            <div class="blade4"></div>
            <div class="vane2"></div>
        </div>
        <div class="base">
            <div class="bottom_base">
                <ul>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                </ul>
            </div>
        </div>
        <div class="wmd1">
            <div class="blades">
                <div class="blade2"></div>
                <div class="blade1"></div>
                <div class="vane1"></div>
                <div class="blade3"></div>
                <div class="blade4"></div>
                <div class="vane2"></div>
            </div>
            <div class="base">
                <div class="bottom_base">
                    <ul>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                    </ul>
                </div>
            </div>

</body>
<script type="text/javascript">
    //解决iframe下系统超时无法跳出iframe框架的问题
    if (top.location !== self.location) {
        top.location=self.location;
    }
</script>
</html>