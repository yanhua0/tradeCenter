<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
      <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
      <style>
      #body{
        width:100%;
        height:100%;
        margin:0; padding:0;
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
      .title1{
          position:relative;
          position: relative;
          left: 950px;
          top: 58px;
          font-size: 17px;
      }

    .panel{
        height:350px;
        overflow: hidden;

    }
          .container{
              opacity:0.9;
          }
          .thr{
              background: #33B6F5;
              color: #fff;
          }
      </style>
   
</head>
<body id="body">
<div  class="container">
<a href="add" target="content" class="title1">&nbsp;&nbsp;更多..</a>
<div class="title">采购信息</div>
<hr>
  <div class="panel">
         
       <div class="panel-body">

           <table class="table table-condensed table-hover" style="word-break:break-all; word-wrap:break-all;">
    <thead class="thr">
   <tr>
       <th id="tss">煤种</th>
       <th>基低位发热量</th>
       <th>空干基水分</th>
       <th>干基高位发热量</th>
       <th>价格</th>
       <th>数量</th>
       <th>交货方式</th>
       <th>交货地点</th>
       <th>发布/截止时间</th>
   </tr>
   </thead>
   <tbody id="tob">
   </tbody>
</table>
</div>


</div>
<a href="add" target="content" class="title1">&nbsp;&nbsp;更多..</a>
<div class="title">销售信息</div>
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
          <th>发布时间</th>
      </tr>
   </thead>
   <tbody id="tbo">
   </tbody>
</table>
</div>

</div>
</div>
<script src="static/js/jquery-1.11.1.min.js"></script>
<script>
    $(function () {

        var start="";
        var end="";
        $.post("findAll",{},function (data) {
            for(var p in data){
                start=formatNumToDate(data[p].createTime);
               end=formatNumToDate(data[p].endTime);
                $("#tob").append("<tr><td>"+data[p].type1+"</td><td>"+data[p].hot1+"</td> <td>"+data[p].air+"</td><td>"+data[p].hot2+"</td><td>--</td><td>"+data[p].number+"</td><td>"+data[p].checkgoodsType+"</td>" +
                    "<td>"+data[p].place+"</td>" +
                    "<td>"+start+"至"+end+"</td></tr>");
            }
        });
        $.post("findAllList",{},function (data) {
            for(var p in data){
                start=formatNumToDate(data[p].releaseEndTime);
                $("#tbo").append("<tr><td>"+data[p].coalType+"</td><td>"+data[p].baseLowCalorificValue+"</td> <td>"+data[p].airDryBasisMoisture+"</td><td>"+data[p].dryBaseHighCalorificValue+"</td><td>"+data[p].price+"</td><td>"+data[p].number+"</td><td>"+data[p].deliveryMethod+"</td>" +
                    "<td>"+data[p].tradingLocations+"</td>" +
                    "<td>"+start+"</td></tr>");
            }
        });

        // Date类型转化
        function compareNine(value) {
            return value > 9 ? value : '0' + value;
        }
        function formatNumToDate(value) {
            var now=new Date(value)
            //直接借助datapattern.js扩展 return now.pattern('yyyy-MM-dd hh:mm:ss');
            //或者使用下面方式计算
            var year = now.getYear() + 1900;//或者 now.getFullYear();
            var month = now.getMonth() + 1;
            var date = now.getDate();
            var hour = now.getHours();
            var minute = now.getMinutes();
            var second = now.getSeconds();
            return year + "-" + compareNine(month) + "-" + compareNine(date);
        }
    })
</script>

</body>
</html>