<%--
  Created by IntelliJ IDEA.
  User: dotoan
  Date: 4/1/14
  Time: 3:59 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="postgraduatems.persistence.entities.Lecturer" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lecturer detail</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1,
      maximum-scale=1, user-scalable=no">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <% Lecturer lecturer = (Lecturer) request.getAttribute("lecturer"); %>
</head>
<body>
  <div id="wrapper">
    <div id="header">
      <%@ include file="header.jsp" %>
    </div>
    <div id="content">
      <h1>Thông tin giảng viên</h1>
      <div class="row">
          <div class="span12 offset4">          
          <% if(lecturer != null) { %>
            <table class="table table-striped" style="width:600px">
              <tr>
                <th style="width:200px">Họ tên</th>
                <td><%= lecturer.getName()%></td>
              </tr>
              <tr>
                <th>Học vị</th>
                <td><%= lecturer.getDegree()%></td>
            </tr>

                <tr>
                    <th>Bộ môn</th>
                    <td><%= lecturer.getMajor().getName()%></td>
                </tr>
              <tr>
                <th>Email</th>
                <td><%= lecturer.getEmail()%></td>
              </tr>
              <tr>
                <th>Điện thoại</th>
                <td><%= lecturer.getPhoneNumber()%></td>
              </tr>
              <tr>
                <th>Địa chỉ</th>
                <td><%= lecturer.getAddress()%></td>
              </tr>
            </table>
          <% } %>
        </div>
      </div>
    </div>
    <div id="footer"><%@ include file="footer.jsp" %></div>
  </div>
</body>
</html>
