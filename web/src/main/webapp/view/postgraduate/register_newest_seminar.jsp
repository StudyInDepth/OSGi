<%@ page import="postgraduatems.persistence.entities.Seminar" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="postgraduatems.persistence.entities.ThesisState" %>
<%--
  Created by IntelliJ IDEA.
  User: dotoan
  Date: 5/2/14
  Time: 11:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title></title>
</head>
<body>
  <div id="wrapper">
    <div id="header">
      <%@ include file="header.jsp" %>
    </div>
    <div id="content">
      <h1>Đăng kí seminar</h1>
      <% Seminar seminar = (Seminar) request.getAttribute("seminar");%>
      <% String state = (String) request.getAttribute("state");%>
      <% Object nullSeminar =  request.getAttribute("nullSeminar");%>
      <% DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");%>
      <% if (nullSeminar != null) {%>
        <div class="alert alert-warning">Chưa có seminar nào được mở!</div>
      <%}%>
      <%if (state != null && state.equals(ThesisState.STARTED)) {%>
          <%if (seminar != null) {%>
              <% boolean registered = (Boolean) request.getAttribute("registered");%>
              <% boolean registrable = (Boolean) request.getAttribute("registrable");%>
              <% boolean dueDate = (Boolean) request.getAttribute("dueDate");%>
              <% Object registerSuccess =   request.getAttribute("register_succeeded");%>
              <% Object unregisterSuccess =  request.getAttribute("unregister_succeeded");%>
              <h2>Thông tin seminar mới nhất</h2>
              <div class="row">
                <div class="span12 offset4">
                  <%if (!registrable && dueDate) {%>
                      <div class="alert alert-warning">Hết hạn đăng kí</div>
                  <%} else if (!registrable && !dueDate) {%>
                      <div class="alert alert-warning">Chưa tới hạn đăng kí</div>
                  <%}%>
                  <% if (registerSuccess != null) { %>
                    <div class="alert alert-success">Đăng kí seminar thành công!</div>
                  <%}%>

                  <% if (unregisterSuccess != null) { %>
                    <div class="alert alert-success">Hủy  đăng kí seminar thành công!</div>
                  <%}%>
                  <table class="table table-striped" style="width:600px">
                    <tr><th style="width:200px">Tiêu đề</th><td><%= seminar.getTitle()%></td></tr>
                    <tr><th>Mô tả</th><td><%= seminar.getDescription()%></td></tr>
                    <tr><th>Ngày dự kiến</th><td><%= dateFormat.format(seminar.getScheduledDate())%></td></tr>
                    <tr><th>Ngày bắt đầu đăng kí</th>
                      <td><%= dateFormat.format(seminar.getStartRegisteringDate())%></td></tr>
                    <tr><th>Ngày hết hạn đăng kí</th>
                      <td><%= dateFormat.format(seminar.getDueRegisteringDate())%></td></tr>
                    <tr><th>Trạng thái đăng kí</th>
                      <td>
                      <% if (registered) {%>
                          <p class="text-success">Đã đăng kí</p>
                      <%} else {%>
                          <p class="text-warning">Chưa đăng kí</p>
                      <%}%>
                      </td>
                    </tr>
                  </table>
                  <br />
                  <% if (!registered && registrable) {%>

                    <form action="/postgraduate/seminar/register" method="post">
                        <input type="hidden" name="register" value="<%=seminar.getId()%>"/>
                        <input type="submit" value="Đăng kí" class="btn btn-large btn-primary"/>
                    </form>

                  <%} else if (registered && registrable) {%>
                    <form action="/postgraduate/seminar/register" method="post">
                        <input type="hidden" name="unregister" value="<%=seminar.getId()%>"/>
                        <input type="submit" value="Hủy đăng kí" class="btn btn-large btn-primary"/>
                    </form>
                  <%} %>
                </div>
              </div>
          <%}  %>
        <%} else {%>
        <p class="text-warning">Đề tài phải ở trạng thái đang thực hiện mới có thể đăng kí seminar!</p>
        <%}%>
      </div>
    <div id="footer"><%@ include file="footer.jsp" %></div>
  </div>
</body>
</html>
