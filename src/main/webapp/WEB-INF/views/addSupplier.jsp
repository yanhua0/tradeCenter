<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Bootstrap 实例 - 基本的输入框组</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

    <style>
        body{
            background: rgb(178, 227, 249);
        }
    </style>
</head>
<body>

<div style="padding: 100px 100px 10px;">
    <form action="insert12" method="post" class="bs-example bs-example-form" role="form">
        <div class="input-group">
            <span class="input-group-addon">煤种:</span>
            <input type="text" name="a" class="form-control" placeholder="请输入内容">
        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon">基低位发热量:</span>
            <input type="text" name="b" class="form-control" placeholder="请输入内容">
        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon">空干基水分:</span>
            <input type="text" name="c" class="form-control" placeholder="请输入内容">
        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon">干基高位发热量:</span>
            <input type="text" name="d" class="form-control" placeholder="请输入内容">
        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon">价格:</span>
            <input type="text" name="e" class="form-control" placeholder="请输入内容">
        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon">数量:</span>
            <input type="text" name="f" class="form-control" placeholder="请输入内容">
        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon">交货方式:</span>
            <input type="text" name="g" class="form-control" placeholder="请输入内容">
        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon">交货地点:</span>
            <input type="text" name="h" class="form-control" placeholder="请输入内容">
        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon">发布时间:</span>
            <input type="date"  name="date" class="form-control" placeholder="请输入内容">
        </div>
        <br>
        <input type="submit" class="btn btn-primary" value="提交">
    </form>
</div>
</body>
</html>