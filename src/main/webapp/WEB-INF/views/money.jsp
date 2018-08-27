<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <title>保证金台账-存款查看页面</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="static/resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
    <link href="static/data/styles.css" type="text/css" rel="stylesheet"/>
    <link href="static/files/保证金台账-存款查看页面/styles.css" type="text/css" rel="stylesheet"/>
    <script src="static/js/jquery-1.11.1.min.js"></script>
   <style>
    body{
         background: rgb(178, 227, 249);
      }
  #qs{
    position:relative;
    width:800px;
    top:130px;
    font-size: 14px;
    }
 .btn-primary{
      width:100px;
      border-radius: 2px;
      height:35px;
      margin-left:140px; 
      margin-top:20px;
   }
   </style>

  
  </head>
  <body>
    <div id="base" class="">

      <!-- Unnamed (横线) -->
      <div id="u0" class="ax_横线">
        <img id="u0_start" class="img " src="static/resources/images/transparent.gif" alt="u0_start"/>
        <img id="u0_end" class="img " src="static/resources/images/transparent.gif" alt="u0_end"/>
        <img id="u0_line" class="img " src="static/images/会员中心/u0_line.png" alt="u0_line"/>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u1" class="ax_文本">
        <img id="u1_img" class="img " src="static/images/会员中心/u104.png"/>
        <!-- Unnamed () -->
        <div id="u2" class="text">
          <p><span>保</span><span>证金账户</span><span>信息</span></p>
        </div>
      </div>

      <!-- Unnamed (横线) -->
      <div id="u3" class="ax_横线">
        <img id="u3_start" class="img " src="static/resources/images/transparent.gif" alt="u3_start"/>
        <img id="u3_end" class="img " src="static/resources/images/transparent.gif" alt="u3_end"/>
        <img id="u3_line" class="img " src="static/images/会员中心/u0_line.png" alt="u3_line"/>
      </div>

   <!-- Unnamed (横线) -->
      <div id="u7" class="ax_横线">
        <img id="u7_start" class="img " src="static/resources/images/transparent.gif" alt="u7_start"/>
        <img id="u7_end" class="img " src="static/resources/images/transparent.gif" alt="u7_end"/>
        <img id="u7_line" class="img " src="static/images/会员中心/u8_line.png" alt="u7_line"/>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u8" class="ax_文本">
        <img id="u8_img" class="img " src="static/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u9" class="text">
          <p><span>报价</span><span>冻结金额</span><span>：</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u10" class="ax_文本">
        <img id="u10_img" class="img " src="static/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u11" class="text">
          <p><span>未冻结金额</span><span>：</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u12" class="ax_文本">
        <img id="u12_img" class="img " src="static/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u13" class="text">
          <p><span>&nbsp;</span>${u.freezeMoney}<span>元</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u14" class="ax_文本">
        <img id="u14_img" class="img " src="static/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u15" class="text">
          <p>${u.money}<span>元</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u16" class="ax_文本">
        <img id="u16_img" class="img " src="static/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u17" class="text">
          <p><span>预存账户</span><span></span><span>金额</span><span>：</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u18" class="ax_文本">
        <img id="u18_img" class="img " src="static/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u19" class="text">
          <p><span>&nbsp;</span><span>${can}</span><span>元</span></p>
        </div>
      </div>

      <!-- Unnamed (横线) -->
      <div id="u20" class="ax_横线">
        <img id="u20_start" class="img " src="static/resources/images/transparent.gif" alt="u20_start"/>
        <img id="u20_end" class="img " src="static/resources/images/transparent.gif" alt="u20_end"/>
       
      </div>

      <!-- Unnamed (形状) -->
      <div id="u21" class="ax_文本">
        <img id="u21_img" class="img " src="static/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u22" class="text">
          <p><span>履约</span><span>冻结</span><span>金额</span><span>：</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u23" class="ax_文本">
        <img id="u23_img" class="img " src="static/resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u24" class="text">
          <p><span>${u.freezeMoney2}元</span></p>
        </div>
      </div>
      <input type="hidden" name="id" id="id"  value="${id}">
     <div id="qs">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;投标数量：<input type="number" id="number" value="${number}" style="width:100px"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

       缴纳保证金金额<input type="text" readonly=＂readonly＂  name="price" id="price" value="${price}">

    <div id="btn">
     <input type="button" id="pay" value="确认缴纳" class="btn btn-primary"><input type="button" id="redirect" value="返回" class="btn btn-primary">
    </div>
     </div>
      <input type="hidden" value="${price}" id="def">

      <input type="hidden" value="${number}" id="def1">
      
  </body>
  <script>
    $(function () {
        $("#number").bind('input propertychange', function() {
            if($("#number").val()<0){
                $("#number").val(0);
            }
            if($("#def").val()!="不要求保证金。"){
             //   console.log($("#number").val().length);
                if($("#number").val().length==0) {
                    $("#price").val(0);
                    $("#number").val(0);
                    return ;
                }

                var unit=parseFloat($("#def").val())/parseInt($("#def1").val());
                var price=unit*parseFloat($("#number").val());
                 $("#price").val(price);

            }
        });
        $("#redirect").click(function () {

           window.location.href="baojia?id="+parseInt($("#id").val());
        });
        $("#pay").click(function () {
            if($("#number").val().length==0){
                alert("数量不能为空！！");
                return false;
            }
            if(confirm("确认缴纳保证金？缴纳后供应数量无法修改!!")==true){
            if($("#price").val()=="不要求保证金。"){
               $.post("pay",{id:parseInt($("#id").val()),price:"0",number:$("#number").val()},function (data) {
                   alert("提交成功!");
                   window.location.href="baojia?id="+parseInt($("#id").val());
               });
            }else{

                $.post("pay",{id:parseInt($("#id").val()),price:$("#price").val(),number:$("#number").val()},function (data) {
                    if(data.state==1){
                        alert(data.stateinfo);
                        window.location.href="baojia?id="+parseInt($("#id").val());
                    }else{
                        alert(data.stateinfo);
                    }
                });
            }

            }
            else{
                return;
            }
        })

    })
  </script>
</html>
