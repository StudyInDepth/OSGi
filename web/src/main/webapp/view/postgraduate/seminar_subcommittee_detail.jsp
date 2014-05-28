<%@ page import="postgraduatems.persistence.entities.Lecturer" %>
<%@ page import="postgraduatems.persistence.entities.Postgraduate" %>
<%@ page import="postgraduatems.persistence.entities.SeminarSubCommittee" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: dotoan
  Date: 5/7/14
  Time: 1:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");%>
<% SeminarSubCommittee seminarSubCommittee = (SeminarSubCommittee) request.getAttribute("committee");%>




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
        <h1>Thông tin tiểu ban</h1>
        <div class="row">
            <div class="span12 offset4">
                <%if (seminarSubCommittee != null) {%>
                <table class="table table-striped" style="width:600px">
                    <tr><th style="width:200px">Tên tiểu ban</th>
                        <td><%=seminarSubCommittee.getName()%></td></tr>
                    <tr><th>Tiểu ban của seminar</th>
                        <td><%=seminarSubCommittee.getSeminar().getTitle()%></td></tr>
                    <tr><th>Địa điểm</th>
                        <td><%=seminarSubCommittee.getName()%></td></tr>
                    <tr><th>Ngày diễn ra</th>
                        <td><%=dateFormat.format(seminarSubCommittee.getHeldDate())%></td></tr>
                    <tr><th>Giờ diễn ra</th>
                        <td><%=seminarSubCommittee.getStaringTime()%></td>
                    <tr><th>Chủ tịch tiểu ban</th>
                        <td><%=seminarSubCommittee.getPresident().getName()%></td></tr>
                    <tr><th>Phó chủ tịch</th>
                        <td><%=seminarSubCommittee.getVicePresident().getName()%></td></tr>
                    <tr><th>Thư kí tiểu ban</th>
                        <td><%=seminarSubCommittee.getSecretary().getName()%></td></tr>
                    </tr>
                </table>
                <p>Danh sách thành viên tiểu ban</p>
                <table class="table table-striped" style="width:600px">
                    <% for (Lecturer lecturer : seminarSubCommittee.getLecturers()) {%>
                    <tr><th style="width:200px">Thành viên</th>
                        <td><%= lecturer.getName()%></td></tr>
                    <%}%>
                </table>


                <p>Danh sách học viên tiểu ban</p>
                <table class="table table-striped" style="width:600px">
                    <% for (Postgraduate postgraduate : seminarSubCommittee.getPostgraduates()) {%>
                    <tr><th style="width:200px">Học viên</th>
                        <td><%= postgraduate.getName()%></td></tr>
                    <%}%>
                </table>


                <% } %>
            </div>
        </div>
    </div>
    <div id="foooter"><%@ include file="footer.jsp" %></div>
</div>
</body>
</html>