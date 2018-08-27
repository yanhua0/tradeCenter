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
        hr{
            border: 0;
            height:1px;
            background-color: #00ADFF;
        }
        .thr{
            background: #33B6F5;
            color: #fff;
        }
        #con{
            display: none;
        }

    </style>

</head>
<body>
<div class="container" id="con" >
    <div class="title">筛选供应商</div>
    <hr>
    <div class="panel">

        <div class="panel-body">
            <table class="table table-condensed table-hover" style="word-break:break-all;">

                <thead class="thr">
                <tr id="first">
                    <th nowrap="nowrap">序号</th>
                    <th nowrap="nowrap">单据编号</th>
                    <th nowrap="nowrap">申请人</th>
                    <th nowrap="nowrap">煤种</th>
                    <th nowrap="nowrap">基低位发热量</th>
                    <th nowrap="nowrap">空干基水分</th>
                    <th nowrap="nowrap">干基高位发热量</th>
                    <th nowrap="nowrap">价格</th>
                    <th nowrap="nowrap">数量</th>
                    <th nowrap="nowrap">交货方式</th>
                    <th nowrap="nowrap">交货地点</th>
                    <th nowrap="nowrap">发布/截止时间</th>
                    <th nowrap="nowrap">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="buyInfo" items="${buyInfo}" varStatus="status">
                    <tr>
                    <td>${status.index+1}</td>
                    <td>${buyInfo.sno}</td>
                    <td>${buyInfo.applicant}</td>
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

                    <td>
                        <!-- 报价后的连接 -->
                        <c:if test="${buyInfo.checkLevel>=2}">
                            <c:if test="${users.role.action.equals('分子公司审核')}">
                                <a href="gys1?id=${buyInfo.id}" target="content">审核</a>
                            </c:if>
                            <c:if test="${users.role.action.equals('电厂审核')}">
                                <a href="gys2?id=${buyInfo.id}" target="content">审核</a>
                            </c:if>
                            <c:if test="${users.role.action.equals('分子公司审批')}">
                                <a href="gys3?id=${buyInfo.id}" target="content">审批</a>
                            </c:if>
                        </c:if>
                    </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

            </div>
        </div>

    </div>
<script>
    $(function () {
        $("#con").show(300);

    })
</script>

</body>
</html>