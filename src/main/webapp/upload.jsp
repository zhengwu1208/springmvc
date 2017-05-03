<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试springmvc中上传的实现</title>
</head>
<body>
<form action="upload.do" method="post" enctype="multipart/form-data">
    <input type="text" name="name"/>
    <input type="file" name="file"/>
    <input type="submit"/>
</form>
</body>
</html>
