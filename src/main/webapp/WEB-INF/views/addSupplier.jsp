<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Bootstrap 实例 - 基本的输入框组</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        body{
            background: rgb(178, 227, 249);
        }
    </style>
    <script type="text/javascript">

        function fn() {
            var b=document.getElementById("b").value;
            var c=document.getElementById("c").value;
            var d=document.getElementById("d").value;
            var e=document.getElementById("e").value;
            var f=document.getElementById("f").value;
            var g=document.getElementById("g").value;
            var h=document.getElementById("h").value;
            var date=document.getElementById("date").value;
            if(b==""){
                alert("请输入基低位发热量");
                return false;
            }
            else if(c==""){
                alert("请输入空干基水分");
                return false;
            }
            else if(d==""){
                alert("请输入干基高位发热量");
                return false;
            }
            else if(e==""){
                alert("请输入价格");
                return false;
            }

            else if(f==""){
                alert("请输入数量");
                return false;
            }

            else if(g==""){
                alert("请输入交货方式");
                return false;
            }

            else if(h==""){
                alert("请输入交货地点");
                return false;
            }
            else if(date==""){
                alert("请输入发布时间");
                return false;
            }else {
                return true;
            }
        }
    </script>
</head>
<body>

<div style="padding: 100px 100px 10px;">
    <form action="insert12" method="post" class="bs-example bs-example-form" role="form" onsubmit="return fn()">
        <div class="input-group">
            <span class="input-group-addon">煤种:</span>
            <select class="form-control" name="a">
                <option>烟煤</option>
                <option>无烟煤</option>
                <option>褐煤</option>
            </select>

        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon">基低位发热量:</span>
            <input type="text" name="b" class="form-control" placeholder="请输入内容" id="b">
        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon">空干基水分:</span>
            <input type="text" name="c" class="form-control" placeholder="请输入内容" id="c">
        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon">干基高位发热量:</span>
            <input type="text" name="d" class="form-control" placeholder="请输入内容" id="d">
        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon">价格:</span>
            <input type="text" name="e" class="form-control" placeholder="请输入内容" id="e">
        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon">数量:</span>
            <input type="text" name="f" class="form-control" placeholder="请输入内容" id="f">
        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon">交货方式:</span>
            <input type="text" name="g" class="form-control" placeholder="请输入内容" id="g">
        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon">交货地点:</span>
            <input type="text" name="h" class="form-control" placeholder="请输入内容" id="h">
        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon">发布时间:</span>
            <input type="date"  name="date" class="form-control" placeholder="请输入内容" id="date">
        </div>
        <br>
        <input type="submit" class="btn btn-primary" value="提交" >
    </form>
</div>
</body>
</html>