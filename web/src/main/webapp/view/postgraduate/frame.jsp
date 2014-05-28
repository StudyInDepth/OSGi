<%--
  Created by IntelliJ IDEA.
  User: dotoan
  Date: 5/18/14
  Time: 10:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
OKOK

<% String pid = (String) request.getParameter("pid");%>
<%=pid%>
<form action="/postgraduate/frame" method="get">
    <input name="pid" type="text"/>
    <input name="OK" type="submit"/>
</form>
</body>
</html>
