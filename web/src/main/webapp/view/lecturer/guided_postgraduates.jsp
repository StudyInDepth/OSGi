<%--
  Created by IntelliJ IDEA.
  User: dotoan
  Date: 3/26/14
  Time: 10:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="postgraduatems.persistence.entities.Postgraduate" %>
<%@ page import="java.util.List" %>
<%@ page import="postgraduatems.persistence.entities.ThesisState" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Danh sách học viên</title>
  <script>
    function getPostgraduateDetail(pid) {
      location.href = "/lecturer/postgraduate/detail?id=" + pid;
    }
  </script>
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
      <div class="container">
        <div class="center hero-content">
          <h1>Danh sách học viên hướng dẫn</h1>
          <table class="postgraduate-table-result table table-bordered" id="postgraduate_list">
            <tr class="label-info">
              <th>Học viên</th>
              <th>Đề tài</th>
              <th>Khóa học</th>
              <th>Giảng viên hướng dẫn</th>
              <th>Trạng thái</th>

            </tr>
            <% List<Postgraduate> postgraduates = (List<Postgraduate>) request
                .getAttribute("postgraduates"); %>
            <% if (postgraduates != null) { %>
            <% for (Postgraduate p : postgraduates) { %>
            <tr>
              <td> <a href="#" onclick="getPostgraduateDetail('<%= p.getId()%>')">
                  <%= p.getName() %></a></td>
              <td><%= p.getThesis().getName() %></td>
              <td><%= p.getAcademicYear() %></td>
              <td><%= p.getLecturer().getName() %></td>
              <td><%= p.getThesis().getState()%>

            </tr>
            <% } %>
            <% } %>
          </table>
        </div>
      </div>
    </div>
    <div><%@ include file="footer.jsp" %></div>
  </div>
</body>
</html>
