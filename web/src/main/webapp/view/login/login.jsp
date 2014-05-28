<%@ page import="postgraduatems.persistence.entities.Postgraduate" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Đăng nhập</title> 
    <link href="../../bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="../../bootstrap/css/custom.css" rel="stylesheet">
    <script type="text/javascript" src="../../bootstrap/js/scripts.js"></script>
    <script type="text/javascript" src="../../bootstrap/js/jquery.min.js"></script>
    <script type="text/javascript" src="../../bootstrap/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1,
      maximum-scale=1, user-scalable=no">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>

<body>
  <div id="wrapper">
    <div id="header">
      <%@ include file="header.jsp" %>
    </div>
    <div id="content">
      <div class="row">
        <div class="span6 offset5">
          <% Object o = request.getAttribute("login_err"); %>
              <% if (o != null) { %>
              <div class="alert alert-error">Người dùng không tồn tại hoặc mật khẩu không đúng</div>
              <% } %>  
          <h1>Đăng nhập</h1>
          <div>
            <form method="post" action="/login">
              <div>Email </div><div><input type="text" name="email" size="40" /><br/></div>
              <div>Mật khẩu </div><div><input type="password" name="password" size="40" /><br/></div>
              <input type="submit" value="Đăng nhập" class="btn btn-primary btn-large"/><br/>
            </form>
          </div>
        </div>
      </div>
    </div>
    <div id="footer"><%@ include file="footer.jsp" %></div>
  </div>
</body>
</html>