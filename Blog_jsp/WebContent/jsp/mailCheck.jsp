<%@ page import="com.lvhongbin.bean.EmailCheck" %>
<%--Created by IntelliJ IDEA.
  User: hblvs
  Date: 2017/11/13
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mail Check</title>

    <%
        String mail=request.getParameter("mailCheck");
       	String phone=request.getParameter("phoneCheck");
        EmailCheck emailcheck=new EmailCheck(mail,phone);
        out.println("该邮箱地址的验证结果是："+emailcheck.isEmail());
        out.println("该电话号码的验证结果是："+emailcheck.isPhone());
    %>
</head>
<body>

</body>
</html>
