<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<body>
<h2>
表单提交
</h2>
<form action="${pageContext.request.contextPath}/user/insertUser" method="get">
    <input type="hidden" name="id">
    姓名：<input name="userName" value="songzhengwu"><br/>
    电话：<input name="userPhone" value="13212341234"><br/>
    邮箱：<input name="userEmail" value="szw@123.com"><br/>
    <input type="submit" value="提交">
</form>
</body>
</html>
