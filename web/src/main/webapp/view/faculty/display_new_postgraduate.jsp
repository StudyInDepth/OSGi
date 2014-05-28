
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="postgraduatems.persistence.entities.Postgraduate" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <title>Postgraduate detail</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1,
      maximum-scale=1, user-scalable=no">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <% Postgraduate postgraduate = (Postgraduate) request.getAttribute("postgraduate");%>
    <% DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");%>

</head>
<body>
  <div id="wrapper">
    <div id="header">
        <%@ include file="header.jsp" %>
    </div>
    <div id="content">
      <%@ include file="header.jsp" %>
      <h1>Thông tin học viên</h1>
      <div class="row">
        <div class="span12 offset4">            
          <% if (postgraduate != null) { %>
          <table class="table table-striped" style="width:600px">
              <tr>
                  <th style="width:200px">Họ tên</th>
                  <td><%= postgraduate.getName()%></td>
              </tr>

              <tr>
                  <th>Ngày sinh</th>
                  <td><%= dateFormat.format(postgraduate.getDateOfBirth())%></td>
              </tr>
              <tr>
                  <th>Khóa học</th>
                  <td><%= postgraduate.getAcademicYear()%></td>
              </tr>
              <tr>
                  <th>Điện thoại</th>
                  <td><%= postgraduate.getPhoneNumber()%></td>
              </tr>
              <tr>
                  <th>Email</th>
                  <td><%= postgraduate.getEmail()%> </td>
              </tr>
              <tr>
                  <th>Địa chỉ</th>
                  <td><%= postgraduate.getAddress()%></td>
              </tr>
          </table>
          <% } else { %>
          	<div class="alert alert-error" style="width: 550px">Tạo học viên không thành công!</div>
          <% } %>
        </div>
      </div>
   	</div>
  <div><%@ include file="footer.jsp" %></div>
  </div>
</body>
</html>


