<%@ page import="java.util.List" %>
<%@ page import="postgraduatems.persistence.entities.Seminar" %>
<%@ page import="postgraduatems.persistence.entities.Postgraduate" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: dotoan
  Date: 5/3/14
  Time: 9:01 PM
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
   <script>
        function displaySeminars() {
           location.href = "/faculty/seminar/list?older=yes"
        }
        function edit(id) {
            location.href = "/faculty/seminar/edit?sid="+id
        }
    </script>
</head>
<body>
  <div id="wrapper">
    <div id="header">
      <%@ include file="header.jsp" %>
    </div>
    <div id="content">
      <h1>Thông tin seminar</h1>
      <div class="row">
        <div class="span10 offset4">
	        <div class="alert alert-success" style="width: 550px">Cập nhật thông tin seminar thành công</div>
	        <% Object one = request.getAttribute("one");%>
	          <% if (one != null) {%>
	            <div class="alert alert-warning">Không còn seminar nào cũ hơn!</div>
	          <%}%>
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
	            <br/>
	            <p>Danh sách học viên đăng kí tham gia</p>
	              <% if (seminar.getPostgraduates().size() > 0) {%>
	                  <table class="table" style="width:600px">
	                      <tr>
	                         <th style="width:200px">Họ tên</th>
	                          <th>Khóa</th>
	                          <th>Đề tài</th>
	                          <th>Giảng viên hướng dẫn</th>
	                          <th>Trạng thái luận văn</th>
	                      </tr>
	                      <% for (Postgraduate p : seminar.getPostgraduates()) {%>
	                       <tr>
	                           <td>
	                               <a href="/faculty/postgraduate/detail?id=<%=p.getId()%>">
	                                  <%= p.getName()%>
	                                </a>
	                           </td>
	                           <td><%= p.getAcademicYear()%></td>
	                           <td><%= p.getThesis().getName()%></td>
	                           <td>
	                               <a href="/faculty/lecturer/detail?id=<%=p.getLecturer().getId()%>">
	                                  <%= p.getLecturer().getName()%>
	                               </a>
	                           </td>
	                           <td><%= p.getThesis().getState()%></td>
	                       </tr>
	                      <%}%>
	                  </table>
	              <%} else {%>
	                <p class="text-warning">Chưa có học viên nào đăng kí</p> <br/>
	              <%}%>
	              <button onclick="edit(<%=seminar.getId()%>)" class="btn btn-primary">
	                Chỉnh sửa</button>
	          <%} else {%>
	            <div class="alert alert-warning">Chưa có seminar nào được mở</div>
	          <%}%>
	          <button id="button" onclick="displaySeminars()" class="btn btn-primary">
	            Seminar cũ hơn</button>         
        </div>
      </div>
    </div>
    <div id="foooter"><%@ include file="footer.jsp" %></div>
  </div>
</body>
</html>