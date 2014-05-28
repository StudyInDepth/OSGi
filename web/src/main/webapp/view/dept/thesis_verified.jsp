<%--
  Created by IntelliJ IDEA.
  User: dotoan
  Date: 3/30/14
  Time: 11:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="postgraduatems.persistence.entities.Postgraduate" %>
<%@ page import="postgraduatems.persistence.entities.ThesisState" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Lecturer detail</title> 
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <% Postgraduate postgraduate = (Postgraduate) request.getAttribute("postgraduate");%>
</head>
<body>
  <%@ include file="header.jsp" %>
  <div class="container">
    <div class="center hero-content">
      <div class="postgraduate-detail">
        <h2 class="center">Thông tin học viên</h2>
        <% if (postgraduate != null) { %>
          <table>
            <tr>
              <td class="post-table-title">Họ tên</td>
              <td><%= postgraduate.getName()%></td>
            </tr>
            <tr>
              <td class="post-table-title">Khóa học</td>
              <td><%= postgraduate.getAcademicYear()%></td>
            </tr>
            <tr>
              <td class="post-table-title">Điện thoại</td>
              <td><%= postgraduate.getPhoneNumber()%></td>
            </tr>
            <tr>
              <td class="post-table-title">Email</td>
              <td><%= postgraduate.getEmail()%> </td>
            </tr>
            <tr>
              <td class="post-table-title">Địa chỉ</td>
              <td><%= postgraduate.getAddress()%></td>
            </tr>

          </table>
          Thông tin đề tài
          <table>
              <tr>
                  <td class="post-table-title">Tên đề tài</td>
                  <td><%= postgraduate.getThesis().getName()%></td>
              </tr>

              <tr>
                  <td class="post-table-title">Trạng thái</td>
                  <td><%= postgraduate.getThesis().getState()%></td>
              </tr>
              <tr>
                  <td class="post-table-title">Giảng viên</td>
                  <td><%= postgraduate.getThesis().isConfirmed() ? "Đã xác  nhận" : "Chưa xác nhận"%></td>
              </tr>
              <tr>
                  <td class="post-table-title">Khoa</td>
                  <td><%= (postgraduate.getThesis().getState().equals(ThesisState.BEING_VERIFIED)
                          || postgraduate.getThesis().getState().equals(ThesisState.STARTED))?
                          "Chấp nhận" : "Chưa chấp nhận"%></td>
              </tr>
              <tr>
                  <td class="post-table-title">Đề cương</td>
                  <td><%= postgraduate.getThesis().isDescriptionUploaded() ?
                  "<a href='/common/thesis/description/download?pid=" + postgraduate.getId() +
                   "'>Tải đề cương</a>"
                  : "Chưa có đề cương" %>

              </tr>

          </table>

        <% } %>
      </div>
    </div>
  </div>
</html>
