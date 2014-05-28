<%--
  Created by IntelliJ IDEA.
  User: dotoan
  Date: 5/13/14
  Time: 1:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Uploading Form</title>
</head>
<body>
<h3>File Upload:</h3>
Select a file to upload: <br />
<form action="/postgraduate/file/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" size="1000" />
    <br />
    <input type="text" name="name"/>
    <input type="submit" value="Upload File" />
</form>
</body>
</html>
