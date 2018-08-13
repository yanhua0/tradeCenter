<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>


<head>


    <meta charset="UTF-8">


    <title>CSS3垂直手风琴折叠菜单 </title>


    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Iconos -->

    <link href="http://www.jq22.com/jquery/font-awesome.4.6.0.css" rel="stylesheet">


    <link rel="stylesheet" href="static/css/style1.css" media="screen" type="text/css"/>

    <style>
        .accordion {
            background: url(static/images/751733.jpg);
            background-size: cover;

        }
    </style>


</head>


<body>

<!-- Contenedor -->

<ul id="accordion" class="accordion">

    <li class="open">

        <div class="link"><i class="fa fa-paint-brush"></i>事物中心<i class="fa fa-chevron-down"></i></div>

        <ul class="submenu aa">
            <c:if test="${users.role.action.equals('阳光用户')}">
                <li><a href="list" target="content">参与报价</a></li>
            </c:if>
            <c:if test="${users.role.action.contains('分子公司')}">
                <li><a href="gys" target="content">选择供应商</a></li>
            </c:if>
            <c:if test="${users.role.action.equals('电厂审核')}">
                <li><a href="gys" target="content">选择供应商</a></li>
            </c:if>
            <li><a href="mes" target="content">系统通知</a></li>


        </ul>

    </li>

    <li>
        <!-- 这里做权限管理 -->
        <div class="link"><i class="fa fa-code"></i>采购信息<i class="fa fa-chevron-down"></i></div>
        <ul class="submenu">
            <li><a href="add" target="content">采购信息</a></li>
            <li><a href="add" target="content">销售信息</a></li>
            <li><a href="supplier" target="content">发布销售信息</a></li>
        </ul>

    </li>

    <li>

        <div class="link"><i class="fa fa-mobile"></i>个人账户<i class="fa fa-chevron-down"></i></div>

        <ul class="submenu">
            <li><a href="money1" target="content">保证金查看</a></li>

        </ul>

    </li>

    <li class="open">
        <div class="link"><i class="fa fa-globe"></i>友情链接<i class="fa fa-chevron-down"></i></div>

        <ul class="submenu aa">

            <li><a href="http://www.cgdc.com.cn/" target="_blank" class="news_index_bottom_href"
                   onclick="$.get(&#39;/friendlink_view.jspx?id=50&#39;)">
                中国国电集团 </a>

            <li><a href="http://www.czce.com.cn/portal/index.htm" target="_blank" class="news_index_bottom_href"
                   onclick="$.get(&#39;/friendlink_view.jspx?id=78&#39;)">
                郑州商品交易所 </a>


            <li><a href="http://www.cctd.com.cn/" target="_blank" class="news_index_bottom_href"
                   onclick="$.get(&#39;/friendlink_view.jspx?id=53&#39;)">
                中国煤炭市场网
            </a>

            <li><a href="http://www.shcce.com/" target="_blank" class="news_index_bottom_href"
                   onclick="$.get(&#39;/friendlink_view.jspx?id=75&#39;)">
                上海煤炭交易中心</a>

        </ul>

    </li>
</ul>
<script src='static/js/jquery.js'></script>
<script src="static/js/index.js"></script>

</body>
</html>