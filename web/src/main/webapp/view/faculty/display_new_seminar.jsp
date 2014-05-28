<%@ page import="postgraduatems.persistence.entities.Seminar" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: dotoan
  Date: 5/2/14
  Time: 11:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Seminar seminar = (Seminar) request.getAttribute("seminar");%>
<% DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");%>

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
      <h1>Thông tin seminar</h1>
      <div class="row">
        <div class="span12 offset4">
        	<div class="alert alert-success" style="width:550px">Tạo seminar thành công</div>
          <%if (seminar != null) {%>
            <table class="table table-striped" style="width:600px">
              <tr><th style="width:200px">Tên seminar</th><td><%= seminar.getTitle()%></td></tr>
              <tr><th>Ngày dự kiến</th>
                <td><%= dateFormat.format(seminar.getScheduledDate())%></td></tr>
              <tr><th>Ngày bắt đầu đăng kí</th>
                <td><%= dateFormat.format(seminar.getStartRegisteringDate())%></td></tr>
              <tr><th>Ngày hết hạn</th>
                <td><%= dateFormat.format(seminar.getDueRegisteringDate())%></td></tr>
            </table>
          <% } %>
        </div>
      </div>
    </div>
    <div id="foooter"><%@ include file="footer.jsp" %></div>
  </div>
</body>
</html>