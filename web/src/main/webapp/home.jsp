<%--
  Created by IntelliJ IDEA.
  User: dotoan
  Date: 3/27/14
  Time: 12:19 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
  <title>Home page</title>
  <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
  <link href="bootstrap/css/custom.css" rel="stylesheet">
  <link href="bootstrap/css/style.css" rel="stylesheet">
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
  <div id="wrapper">
    <div id="header">
      <header class="navbar navbar-fixed-top navbar-inverse">
        <div class="navbar-inner">
          <div class="container">
            <div id="logo">UET</div>
            <nav>
              <ul class="nav pull-right">
                <li><a href="/home.jsp">Trang chủ</a></li>
                <li><a href="/login">Đăng nhập</a></li>
              </ul>
            </nav>
          </div>
        </div>
      </header>
    </div>
    <div id="content">
      <div class="container">
        <div class="center hero-unit">
          <h1>
            Hệ thống đăng kí luận văn cao học
          </h1>
          <h2>
            Chào mừng bạn đến với hệ thống quản lý luận văn học viên cao học.
          </h2>
          <a class="btn btn-primary btn-large center" href="/login">Đăng nhập</a>
        </div>
      </div>
    </div>
    <div id="footer">
     <div class="container">
        <p class="muted credit"><a href="">Thông tin nhóm phát triển</a> và <a href="">Liên hệ</a>.</p>
      </div>
    </div>
  </div>
</body>
</html>
