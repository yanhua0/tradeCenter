<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>国电煤炭电商平台</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        *{
            margin: 0;
            padding: 0;
            list-style: none;
        }
        #frist{
            position: relative;
            height: 220px;

            background-image:url(static/images/banner02.png);

        }
        #frist ul li{
            border-radius: 5px;
            background-color: blue;
            width: 120px;
            height: 50px;
            text-align: center;
            line-height: 50px;
            float: left;
            /*  position: relative;*/
            margin: 20px 10px 520px 30px;
            /* color: floralwhite;*/
            font-size: 20px;
            
            background: -webkit-linear-gradient(left, #18BADE , #0089FF); /* Safari 5.1 - 6.0 */
            background: -o-linear-gradient(right, #18BADE , #0089FF); /* Opera 11.1 - 12.0 */
            background: -moz-linear-gradient(right, #18BADE , #0089FF); /* Firefox 3.6 - 15 */
            background: linear-gradient(to right, #18BADE , #0089FF); /* 标准的语法（必须放在最后） */
        }
        #ul1{

            position:relative;
            
        }
        .weather{
            position: relative;
            left: 37%;
            top: -50%;
        }
        #ul1 ul{
            width: 800px;
            position: absolute;
            left: 50%;
            top: 50%;
            transform:translate(-50%,0%);
        }

        #img{
            height:100px;
            transition: all 1.6s;
             animation:myfirst 2s;
            animation-iteration-count:3;/*infinite*/
            -webkit-animation:myfirst 2s; /* Safari and Chrome */
            -webkit-animation-iteration-count:3;
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
        #ul1 ul li a{
            display: block;
            text-decoration: none;
            color:#fff;
        }
        #ul1 ul li a{

            box-shadow: inset 0 0 20px rgba(255, 255, 255, 0);
            outline: 1px solid;

            text-shadow: none;
            -webkit-transition: all 1250ms cubic-bezier(0.19, 1, 0.22, 1);
            transition: all 1250ms cubic-bezier(0.19, 1, 0.22, 1);
        }

        #ul1 ul li a:hover {
            border: 1px solid;
            box-shadow: inset 0 0 20px rgba(255, 255, 255, 0.5), 0 0 20px rgba(255, 255, 255, 0.2);  box-shadow: inset 0 0 20px rgba(255, 255, 255, 0.5), 0 0 20px rgba(255, 255, 255, 0.2);
            outline-color: rgba(255, 255, 255, 0);
            outline-offset: 15px;
            text-shadow: 1px 1px 2px #427388;
        }
        .fixed {
            position: fixed;
            top: 1;
            left: 0;
            max-width:300px;
            font-size: 30px;
            color:#00E8FF;

        }

span{
    color: #F1F1F1;
}
    </style>
</head>
<body>

    <div style="" id="frist">
        <img id="img" src="static/images/5.png" alt="logo" style="position: relative">
        <div id="ul1">
         
            <ul>
                <li><a href="first.jsp" class="fi" target="content" rel="popover" data-content="返回网站首页"  data-placement="left" >首页</a></li>
                <li><a href="add"  target="content" class="fi" rel="popover" data-content="查看采购信息和销售信息"  data-placement="top" >交易中心</a></li>
                <c:if test="${users.role.action.equals('电厂审核')}">

                <li><a href="member" target="content" class="fi" rel="popover" data-content="审核创建的采购订单"  data-placement="top">会员中心</a></li> </c:if>
                <c:if test="${users.role.action.contains('分子公司')}">
                    <li><a href="member" target="content" class="fi" rel="popover" data-content="审核创建的采购订单"  data-placement="top">会员中心</a></li>
                </c:if>
                <li><a href="mes" target="content" class="fi" rel="popover" data-content="查看收到的信息"  data-placement="top">账户消息</a></li>
                <li><c:if test="${users==null}">
                        <a href="login" target="_top" class="fi" rel="popover" data-content="跳转登录页面"  data-placement="right">登录</a></c:if>
                    <c:if test="${users!=null}">
                        <a href="login" target="_top" class="fi" rel="popover" data-content="退出当前账号"  data-placement="right">退出</a></c:if>
                </li>
            </ul>
        </div>
        <c:if test="${users!=null}">
        <div id="welc" class="fixed"><h3>welcome&nbsp;<span>${users.name}</span></h3></div>
        </c:if>
        <div></div>
    </body>
<div class="weather"><iframe class="weather" style="color:red;" allowtransparency="true" frameborder="0" width="385" height="96" scrolling="no" src="http://tianqi.2345.com/plugin/widget/index.htm?s=2&z=3&t=0&v=0&d=3&bd=0&k=&f=&ltf=009944&htf=cc0000&q=1&e=1&a=1&c=54511&w=385&h=96&align=center"></iframe></div>
<script>
    $(function () {
        $(".fi").popover({trigger: 'hover'});
    });
</script>
    </html>