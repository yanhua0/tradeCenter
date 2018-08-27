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
      span{
          color: #ff1d21;
      }
      #con{
          display: none;
      }
  </style>
</head>
<body>
	<div class="container" id="con">
    <div class="panel panel-primary">
      <div class="panel-heading text-center ">
        <h5 class="name">收到的信息</h5></div>
        <div class="panel-body">
            <table class="table table-condensed table-hover" style="word-break:break-all; word-wrap:break-all;">
           <!-- <caption class="name">用户信息</caption> -->
           <thead class="thr">
            <tr>
            <th>序号</th>
             <th>信息</th>
             <th>发件人</th>
             <th>发送时间</th>
             <th>操作</th>
            </tr>
         </thead>
         <tbody>
         <c:forEach var="mes" items="${page.list}" varStatus="status" >
             <tr>
<td>${status.index+1}</td>
<td>${mes.message}</td>
<td>${mes.sendname}</td>
<td><fmt:formatDate value="${mes.sendTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
<td><a href="deleteMes?id=${mes.id}" class="glyphicon glyphicon-trash" onclick="if(confirm('确认删除这条信息?')==false){return false;}else{return true}"></a></td>
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
                        <li><a href="mes?page=${page.firstPage}">首页</a></li></c:if>
                    <c:if test="${page.hasPreviousPage==true}">
                        <li><a href="mes?page=${page.prePage}">&laquo;</a></li></c:if>
                    <c:if test="${page.hasPreviousPage==false}">
                        <li><a>&laquo;</a></li></c:if>


                    <c:forEach begin="1" end="${page.pages}" var="index">
                        <c:if test="${page.pageNum==index}"><li>
                            <a style="background-color: #337ab7;color:#fff">${index}</a></li>
                        </c:if>
                        <c:if test="${page.pageNum!=index}">
                            <li><a href="mes?page=${index}">${index}</a></li></c:if>
                    </c:forEach>
                    <c:if test="${page.hasNextPage==true}">
                        <li><a href="mes?page=${page.nextPage}">&raquo;</a></li></c:if>
                    <c:if test="${page.hasNextPage==false}">
                        <li><a>&raquo;</a></li></c:if>
                    <c:if test="${page.isLastPage==true}">
                        <li><a >末页</a></li></c:if>
                    <c:if test="${page.isLastPage==false}">
                        <li><a href="mes?page=${page.lastPage}">末页</a></li></c:if>
                </ul>
            </div>
                </c:if>

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