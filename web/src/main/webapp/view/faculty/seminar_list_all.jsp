<%@ page import="java.util.List" %>
<%@ page import="postgraduatems.persistence.entities.Seminar" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="postgraduatems.persistence.entities.Postgraduate" %>
<%--
  Created by IntelliJ IDEA.
  User: dotoan
  Date: 5/5/14
  Time: 11:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%List<Seminar> seminars = (List<Seminar>) request.getAttribute("seminars");%>
<% DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");%>
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
      <h1>Danh sách seminar cũ hơn</h1>
       <div class="row">
        <div class="span12 offset4">
          <%if (seminars != null && seminars.size() > 1) {%>
            <table class="table table-striped" style="width:600px">
              <%for (int i = 1; i < seminars.size(); i++) {%>
                <tr><td style="width:300px">
                  Tiêu đề: <%= seminars.get(i).getTitle()%></td>
                  <td style="width:200px">Ngày dự kiến: <%= dateFormat.format(seminars.get(i).getScheduledDate())%></td>
                  <td style="width:100px"><a href="/faculty/seminar/detail?sid=<%=seminars.get(i).getId()%>">Xem</a></td>
                </tr>
              <% } %>
            </table>
          <% } %>
    <div id="foooter"><%@ include file="footer.jsp" %></div>
</body>
</html>