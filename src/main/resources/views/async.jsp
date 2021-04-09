<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>servlet async support</title>

</head>
<body>



<script type="text/javascript" src="assets/js/jquery.js"></script>
<script type="text/javascript">
    // 在客户端，使用jquery 来 发送AJAX请求；    特征：没有浏览器的兼容性问题
	deferred();//1 在页面打开后，就会直接 向后台发送请求
	
	function deferred(){
	    $.get('defer',function(data){
	        console.log(data); //2 在浏览器的控制台 来 输出服务器端推送的数据
	        deferred(); //3 当一次C-S请求完成后，会再次向后台发送请求
	    });
	}


</script>
</body>
</html>