<%@ page import="java.util.List" %>
<%@ page import="postgraduatems.persistence.entities.Postgraduate" %>
<%@ page import="postgraduatems.persistence.entities.Seminar" %>
<%@ page import="postgraduatems.persistence.entities.Lecturer" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: dotoan
  Date: 5/6/14
  Time: 10:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List<Postgraduate> postgraduates = (List<Postgraduate>) request.getAttribute("postgraduates");%>
<%List<Seminar> seminars = (List<Seminar>) request.getAttribute("seminars");%>
<%List<Lecturer> lecturers = (List<Lecturer>) request.getAttribute("lecturers");%>
<%Seminar seminar = (Seminar) request.getAttribute("seminar");%>
<%
    java.util.Date currentUtilDate = new java.util.Date();
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    java.sql.Date currentSqlDate = java.sql.Date.valueOf(format.format(currentUtilDate));
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
%>
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
      <h1>Mở tiểu ban seminar</h1>
      <div class="row">
        <div class="span12 offset4">
          <% if (seminar != null) {%>            
            <%Object fieldError = request.getAttribute("field_error");%>
            <%if (fieldError != null) {%>
              <div class="alert alert-error">
                Phải nhập đầy đủ thông tin trong tất cả các trường!</div>
            <%}%>

            <%Object heldDateError = request.getAttribute("heldDateError");%>
            <%if (heldDateError != null) {%>
              <div class="alert alert-error">
                Ngày tổ chức phải sau ngày hiện tại <%= dateFormat.format(currentSqlDate)%>!
              </div>
            <%}%>
            <form method="post" action="/faculty/seminarsubcommittee/open">
               <table class="table" style="width:600px">
                <tr><th style="width:200px">Tên tiểu ban</th>
                  <td><input type="text" name="name" value="<%= request.getParameter("name") == null ? "" : request.getParameter("name")%>"/></td></tr>

                <tr><th>Địa điểm</th>
                  <td><input type="text" name="place" value="<%= request.getParameter("place") == null ? "" :
                  request.getParameter("place")%>"/></td></tr>
                <tr><th>Ngày diễn ra</th>
                  <td><input type="date" name="held_date"/></td>
                
                <tr><th>Thời gian</th><td><input type="time" name="starting_time"/></td></tr>
                <tr><th>Chọn seminar</th>
                  <td>
                    <select  name="seminar_id">
                      <option value="" />Chọn seminar</option>
                        <option value="<%= seminar.getId()%>" > <%= seminar.getTitle()%>
                      </option>
                    </select>
                  </td>
                </tr>
                <tr><th>Chọn chủ tịch tiểu ban</th>
                  <td>
                    <select  name="president_id">
                      <option value="" />Chọn chủ tịch</option>
                      <% for (Lecturer lecturer : lecturers)  { %>
                          <option value="<%= lecturer.getId()%>" > <%= lecturer.getDegree() + ". " + lecturer.getName()%>
                          </option>
                      <%}%>
                    </select>
                  </td></tr>

                <tr><th>Chọn phó chủ tịch tiểu ban</th>
                  <td>
                    <select name="vice_president_id">
                      <option value="" />Chọn phó chủ tịch</option>
                      <% for (Lecturer lecturer : lecturers)  { %>
                          <option value="<%= lecturer.getId()%>" > <%= lecturer.getDegree() + ". " + lecturer.getName()%>
                          </option>
                      <%}%>
                    </select>
                  </td></tr>
                <tr><th>Chọn thư kí tiểu ban</th>
                  <td>
                    <select name="secretary_id" >
                      <option value="" />Chọn thư kí</option>
                      <% for (Lecturer lecturer : lecturers)  { %>
                          <option value="<%= lecturer.getId()%>" > <%= lecturer.getDegree() + ". " + lecturer.getName()%>
                          </option>
                      <%}%>
                    </select>
                  </td></tr>
              </table>
              <br/>
              <p>Chọn các thành viên của tiểu ban</p>

              <% for (Lecturer lecturer : lecturers)  { %>
                <div>
                  <input type="checkbox" name="lecturer_ids" id="check_<%= lecturer.getId()%>" value="<%= lecturer.getId()%>" >
                    <%= lecturer.getDegree() + ". " + lecturer.getName()%>
                </div>
              <%}%>
              <br />
              <input type="submit" value="Mở tiểu ban" class="btn btn-large btn-primary"/>
            </form>
          <%} else {%>
            <div class="alert alert-warning">Chưa có seminar mới được mở</div>
            <a href="/faculty/seminar/open">Mở một seminar mới</a><br/>
          <%}%>
        </div>
      </div>
    </div>
    <div id="foooter"><%@ include file="footer.jsp" %></div>
  </div>
</body>
</html>