<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="static/js/jquery-1.11.1.min.js"></script>
    <style>
        body{
            background: rgb(178, 227, 249);
        }
        .title{
            width:100px;
            height:40px;
            border-radius:5px;
            background:#00ADFF;
            text-align:center;
            line-height: 40px;
            color:#fff;
            font-family: "Microsoft YaHei";
            position:relative;
            top:19px;

        }
        .title1{
            position:relative;
            position: relative;
            top: 59px;
            left: 950px;
        }
        hr{
            border: 0;
            height:1px;
            background-color: #00ADFF;
        }
        #sd{
            height:200px;
        }

        .thr{
            background: #33B6F5;
            color: #fff;
        }
        .test{
            position: relative;
            height: 20px;
        }
        .test ul{

            position: absolute;
            left: 50%;
            top: -30%;
            transform:translate(-50%,-50%);
        }
        #con{
            display: none;
        }
    </style>

</head>
<body>
<div class="container" id="con">
    <c:if test="${users.role.action.equals('电厂创建')}">
        <a href="create" target="content" class="title1">&nbsp;&nbsp;发布采购信息..</a></c:if>
    <div class="title">采购信息</div>
 <hr>
    <div class="panel">

        <div class="panel-body">
            <table class="table table-condensed table-hover" style="word-break:break-all; word-wrap:break-all;">

                <thead class="thr">
                <tr>
                    <th>煤种</th>
                    <th>基低位发热量</th>
                    <th>空干基水分</th>
                    <th>干基高位发热量</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>交货方式</th>
                    <th>交货地点</th>
                    <th>发布/截止时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="buyInfo" items="${page.list}">
                    <tr>
                    <td>${buyInfo.type1}</td>
                    <td>${buyInfo.hot1}</td>
                    <td>${buyInfo.air}</td>
                    <td>${buyInfo.hot2}</td>
                    <td>--</td>
                    <td>${buyInfo.number}</td>
                    <td>${buyInfo.checkgoodsType}</td>
                    <td>${buyInfo.place}</td>
                    <td><fmt:formatDate value="${buyInfo.createTime}" pattern="yyyy-MM-dd" />至
                        <fmt:formatDate value="${buyInfo.endTime}" pattern="yyyy-MM-dd" />
                    </td>
                    <td><a href="show?id=${buyInfo.id}" target="content">查看</a></td>
                </tr>
                </c:forEach>

                </tbody>
            </table>
            <%--分页实现--%>
            <c:if test="${page.pages>1}">

            <div class="test">
                <ul class="pagination">
                    <c:if test="${page.isFirstPage==true}"><li><a>首页</a></li></c:if>
                    <c:if test="${page.isFirstPage==false}">
                        <li><a href="add?page=${page.firstPage}">首页</a></li></c:if>
                    <c:if test="${page.hasPreviousPage==true}">
                        <li><a href="add?page=${page.prePage}">&laquo;</a></li></c:if>
                    <c:if test="${page.hasPreviousPage==false}">
                        <li><a>&laquo;</a></li></c:if>


                    <c:forEach begin="1" end="${page.pages}" var="index">
                        <c:if test="${page.pageNum==index}"><li>
                            <a style="background-color: #337ab7;color:#fff">${index}</a></li>
                        </c:if>
                        <c:if test="${page.pageNum!=index}">
                            <li><a href="add?page=${index}">${index}</a></li></c:if>
                    </c:forEach>
                    <c:if test="${page.hasNextPage==true}">
                        <li><a href="add?page=${page.nextPage}">&raquo;</a></li></c:if>
                    <c:if test="${page.hasNextPage==false}">
                        <li><a>&raquo;</a></li></c:if>
                    <c:if test="${page.isLastPage==true}">
                        <li><a >末页</a></li></c:if>
                    <c:if test="${page.isLastPage==false}">
                        <li><a href="add?page=${page.lastPage}">末页</a></li></c:if>
                </ul>
            </div>
                </c:if>


        </div>
    </div>

    <c:if test="${users.role.action.equals('电厂创建')}">
        <a href="supplier" target="content" class="title1">&nbsp;&nbsp;发布销售信息..</a></c:if>
    <div class="title">销售信息</div>
    <hr>
    <div class="panel">

        <div class="panel-body">
            <div id="sd" style="overflow-y: scroll">
                <table class="table table-condensed table-hover" style="word-break:break-all; word-wrap:break-all;">

                    <thead class="thr">
                    <tr>
                        <th>煤种</th>
                        <th>基低位发热量</th>
                        <th>空干基水分</th>
                        <th>干基高位发热量</th>
                        <th>价格</th>
                        <th>数量</th>
                        <th>交货方式</th>
                        <th>交货地点</th>
                        <th>发布时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="list" items="${list}">
                        <tr>
                            <td>${list.getCoalType()}</td>
                            <td>${list.getBaseLowCalorificValue()}</td>
                            <td>${list.getAirDryBasisMoisture() }</td>
                            <td>${list.getDryBaseHighCalorificValue() }</td>
                            <td>${list.getPrice()}</td>
                            <td>${list.getNumber() }</td>
                            <td>${list.getDeliveryMethod()}</td>
                            <td>${list.getTradingLocations()}</td>
                            <td><fmt:formatDate value="${list.releaseEndTime}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        $("#con").show(200);
    })
</script>
</body>
</html>