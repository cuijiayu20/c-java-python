<%--
  Created by IntelliJ IDEA.
  User: 崔家瑜
  Date: 2022/11/19
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=gbk"%>
<html>
<head>
    <title>处理定单页面</title>
</head>
<body>
你的订购信息如下:<br>
<jsp:include page="item.jsp" flush="true">
    <jsp:param name="item" value="mp3"/>
    <jsp:param name="price" value="98.00"/>
</jsp:include>
<hr>
<jsp:include page="item.jsp" flush="true">
    <jsp:param name="item" value="mp4"/>
    <jsp:param name="price" value="168.00"/>
</jsp:include>
</body>
</html>
