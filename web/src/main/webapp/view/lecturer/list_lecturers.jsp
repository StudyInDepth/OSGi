<%--
  Created by IntelliJ IDEA.
  User: dotoan
  Date: 3/26/14
  Time: 10:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="postgraduatems.persistence.entities.Lecturer" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Danh sách giảng viên</title>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>

<body>
  <div id="wrapper">
    <div id="header">
      <%@ include file="header.jsp" %>
    </div>
    <div id="content">
      <div class="container">
        <div class="center hero-content">
            <h1>Danh sách giảng viên</h1>

            <form method="get" action="/lecturer/lecturer/list">
              <div>
                <input placeholder="Họ tên/Học vị" type="text" name="keyword" class="lecturer-keyword" size="40" value=
                  "<%=(request.getParameter("keyword") == null) ? "" : request.getParameter("keyword")%>"/>
                <input type="submit" value="Tìm kiếm" class="postgraduate-submit btn btn-primary"/>
              </div>
            </form>
            <table class="postgraduate-table-result table table-bordered" id="lecturer_list">
              <tr class="label-info">
                <th>Họ tên</th>
                <th>Học vị</th>
                <th>Bộ môn</th>
                <th>Email</th>
                <th>Điện thoại</th>
                <th>Địa chỉ</th>
              </tr>
              <%List<Lecturer> lecturerList = (List<Lecturer>) request.getAttribute("lecturerList"); %>
              <%for (Lecturer lecturer : lecturerList) { %>
                <tr>
                  <td><%= lecturer.getName()%></td>
                  <td><%= lecturer.getDegree()%></td>
                  <td><%= lecturer.getMajor().getName()%></td>
                  <td><%= lecturer.getEmail()%></td>
                  <td><%= lecturer.getPhoneNumber()%></td>
                  <td><%= lecturer.getAddress()%></td>
                </tr>
              <%}%>
            </table>
        </div>
      </div>
    </div>
    <div id="footer"><%@ include file="footer.jsp" %></div>
  </div>
</body>
</html>
