<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>国电煤炭电商平台</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">  
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
        #ul1 ul{
            width: 800px;
            position: absolute;
            left: 50%;
            top: 50%;
            transform:translate(-50%,0%);
        }

        #img{
            height:110px;
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
        <img id="img" src="static/images/u5.jpg" alt="logo" style="position: relative">
        <div id="ul1">
         
            <ul>
                <li><a href="first.jsp" target="content">首页</a></li>
                <li><a href="add" target="content">交易中心</a></li>
                <c:if test="${users.role.action.equals('电厂审核')}">

                <li><a href="member" target="content">会员中心</a></li> </c:if>
                <c:if test="${users.role.action.contains('分子公司')}">
                    <li><a href="member" target="content">会员中心</a></li>
                </c:if>
                <li><a href="mes" target="content">系统消息</a></li>
                <li><a href="login" target="_top">
                    <c:if test="${users==null}">
                    登录</c:if>
                    <c:if test="${users!=null}">
                        退出</c:if>
                </a></li>
            </ul>
        </div>
        <c:if test="${users!=null}">
        <div id="welc" class="fixed"><h3>welcome&nbsp;<span>${users.name}</span></h3></div>
        </c:if>
        <div></div>
    </body>
    </html>