<%--
  Created by IntelliJ IDEA.
  User: dotoan
  Date: 5/8/14
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
  <title>Khoa|Trang chủ</title>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
  <div id="wrapper">
    <div id="header">
      <%@ include file="header.jsp" %>
    </div>
    <div id="content">
      <h1>Thêm học viên</h1>
      <div class="row">
        <div class="span8 offset4">
          <%Object fieldError = request.getAttribute("field_error");%>
          <%if (fieldError != null) {%>
            <div class="alert alert-error" style="width: 550px">Phải nhập đầy đủ thông tin!</div>
          <%}%>
          <%Object fieldYearError = request.getAttribute("field_year_error");%>
          <%if (fieldYearError != null) {%>
            <div class="alert alert-error" style="width: 550px">Khóa học phải ở dạng số!</div>
          <%}%>
          <form action="/faculty/postgraduate/add" method="post">
            <form action="/faculty/postgraduate/add" method="post">
              <label>Tên học viên</label><input type="text" name="name">
              <label>Khóa</label><input type="text" name="year">
              <label>Ngày sinh</label><input type="date" name="date">
              <label>Email</label><input type="text" name="email">
              <label>Điện thoại</label><input type="text" name="phone">
              <label>Địa chỉ</label><textarea name="address"></textarea>
            <input type="submit" value="Thêm học viên" class="btn btn-large btn-primary"/>
          </form>
        </div>
      </div>
    </div>
    <div id="foooter"><%@ include file="footer.jsp" %></div>
  </div>
</body>
</html>