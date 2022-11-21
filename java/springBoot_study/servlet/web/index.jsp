<%--
  Created by IntelliJ IDEA.
  User: 崔家瑜
  Date: 2022/10/20
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>my JSP 'index.jsp' staring page</title>
    <meta http-equiv="pragma" content="no-cache" >
    <meta http-equiv="cache-control" content="no-cache" >
    <meta http-equiv="expires" content="0" charset="utf-8">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3" >
    <meta http-equiv="description" content="This is my page" >
    <meta charset="utf-8">
  </head>
  <body>

  This is my page<br>
  <form action="/servletText_war_exploded/cuijiayu2" method="post">
    用户名：<input type="text" name="username"><br/>
    密码：&nbsp;&nbsp;&nbsp;<input type="password" name="password"><br/>
    爱好：
    <input type="checkbox" name="hobby" value="sing">
    <input type="checkbox" name="hobby" value="dance">
    <input type="checkbox" name="hobby" value="football"><br/>
    <input type="submit" value="提交"><br/>
  </form>
  </body>
</html>
