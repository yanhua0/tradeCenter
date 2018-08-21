<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
	<meta charset="UTF-8">
	<title>国电煤炭电商平台</title>
	<link rel="Shortcut icon" href="http://www.jq22.com/favicon.ico">
	<script src="static/js/jquery-1.11.1.min.js"></script>
	<script src="static/js/plug-in.js"></script>
	<style>

		#top{
			width:100%;
			height:25%;
		}
		#first{
			width:20%;
			height:100%;
		}
		#form{
			width:79%;
			height:100%;
		}
		#under{
			width:100%;
			height:90px;
		}
	</style>
<body>
<iframe id="top" name=""  scrolling="no" src="top.jsp" frameborder="0" ></iframe>
<iframe id="first" name="" scrolling="no" src="left.jsp" frameborder="0" ></iframe>
<iframe id="form" scrolling="yes" src="first.jsp" name="content" frameborder="0" ></iframe>
<iframe id="under" scrolling="no" border="0 " frameborder="0" src="bottom.jsp" frameborder="0"></iframe>
</body>
</head>
<script src="static/js/plug-in.js"></script>

<script>
$(function(){
    $.post("mesCheck",{},function (result) {
		if(result!=0){
            $.Pop('收到新信息啦！！',{Animation:'layer-rollIn'});
            $(".showAlert").css({"top":"300px"});
		}
    });
});
</script>
</html>
  