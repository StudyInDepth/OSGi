<%@ page import="postgraduatems.persistence.entities.Seminar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: dotoan
  Date: 4/30/14
  Time: 5:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Seminar seminar = (Seminar) request.getAttribute("seminar");%>
<% DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");%>
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
      <h1>Chỉnh sửa thông tin seminar</h1>
      <div class="row">
        <div class="span8 offset4">
          <%Object fieldError = request.getAttribute("field_error");%>
          <% if (fieldError != null) {%>
            <div class="alert alert-error">Các thông tin phải nhập đầy đủ!</div>
          <%}%>
          <form action="/faculty/seminar/edit" method="post">
              <label>Tiêu đề</label>
              <input type="text" name="title" value="<%= seminar.getTitle()%>"/>
                
              <label>Mô tả</label>
              <textarea name="des"><%= seminar.getDescription()%></textarea>
              <label>Ngày dự kiến</label>
              <input type="date" name="scheduled_date" value="<%= dateFormat.format(seminar.getScheduledDate())%>">
              <label>Ngày bắt đầu đăng kí</label>
              <input type="date" name="start_reg_date" value="<%= dateFormat.format(seminar.getStartRegisteringDate())%>">
              <label>Ngày hết hạn đăng kí</label>
              <input type="date" name="due_reg_date" value="<%= dateFormat.format(seminar.getDueRegisteringDate())%>">
            <input type="hidden" value="<%=seminar.getId()%>" name="sid"/>
            <br />
            <input type="submit" value="Ghi nhận" class="btn btn-large btn-primary">
          </form>
        </div>
      </div>
    </div>
    <div id="foooter"><%@ include file="footer.jsp" %></div>
  </div>
</body>
</html>