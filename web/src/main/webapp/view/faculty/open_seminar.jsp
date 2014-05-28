<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: dotoan
  Date: 4/30/14
  Time: 5:55 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%
    java.util.Date currentUtilDate = new java.util.Date();
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    java.sql.Date currentSqlDate = java.sql.Date.valueOf(format.format(currentUtilDate));
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
  %>
  <%Object fieldError = request.getAttribute("field_error");%>
  <%Object startDueDateError = request.getAttribute("start_due_date_error");%>
  <%Object startScheduledDateError = request.getAttribute("start_scheduled_date_error");%>
  <%Object startCurrentDateError = request.getAttribute("start_current_date_error");%>
  <%Object currentScheduledDateError = request.getAttribute("current_scheduled_date_error");%>
  <%Object dueScheduledDateError = request.getAttribute("due_scheduled_date_error");%>
<html>
<head>
  <title>Khoa|Trang chủ</title>  
</head>
<body>
  <div id="wrapper">
    <div id="header">
      <%@ include file="header.jsp" %>
    </div>
    <div id="content">
      <h1> Mở seminar</h1>
      <div class="row">
        <div class="span8 offset4">
          <% if (fieldError != null) {%>
            <div class="alert alert-error">Các thông tin phải nhập đầy đủ!</div>
          <%}%>

          <% if (startDueDateError != null) {%>
            <div class="alert alert-error">Ngày bắt đầu đăng kí phải trước ngày hết hạn đăng kí!</div>
          <%}%>

          <% if (dueScheduledDateError != null) {%>
            <div class="alert alert-error">Ngày hết hạn đăng kí phải trước ngày dự kiến!</div>
          <%}%>

          <% if (startScheduledDateError != null) {%>
            <div class="alert alert-error">Ngày bắt đầu đăng kí phải trước ngày dự kiến!</div>
          <%}%>

          <% if (startCurrentDateError != null) {%>
            <div class="alert alert-error">Ngày bắt đầu đăng kí phải sau ngày hiện tại
              <%= dateFormat.format(currentSqlDate)%>!</div>
          <%}%>

          <% if (currentScheduledDateError != null) {%>
            <div class="alert alert-error">
              Ngày bắt dự kiến phải sau ngày hiện tại <%= dateFormat.format(currentSqlDate)%>!
            </div>
          <%}%>
          <form action="/faculty/seminar/save" method="get">
            <label>Tiêu đề</label> <input type="text" name="title"/><br>
            <label>Mô tả</label><textarea name="des"></textarea><br>
            <label>Ngày dự kiến</label> <input type="date" name="scheduled_date" ><br>
            <label>Ngày bắt đầu đăng kí</label> <input type="date" name="start_reg_date" ><br>
            <label>Ngày hết hạn đăng kí</label> <input type="date" name="due_reg_date" ><br>
            <input type="submit" value="Mở seminar" class="btn btn-large btn-primary">
          </form>
        </div>
      </div>
    </div>
    <div id="foooter"><%@ include file="footer.jsp" %></div>
  </div>
</body>
</html>
