<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HttpMessageConverter Demo</title>
</head>
<body>
    <div id="resp"></div>
    <input type="button" onclick="req();" value="请求"/>
<script src="assets/js/jquery.js" type="text/javascript"></script>
<!-- 定义一个JS脚本片段，用来发送AJAX异步请求 -->
<script>
    function req(){
        $.ajax({
            url: "convert",
            data: "1-henryInSH", //1 发送给后台的数据 这里的格式是用“-”分隔开来
            type:"POST",
            contentType:"application/henry", //2 contentType字段设置的值 是 自定义的媒体类型
            success: function(data){
                $("#resp").html(data);
            }
        });
    }

</script>
</body>
</html>