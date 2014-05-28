<%--
  Created by IntelliJ IDEA.
  User: dotoan
  Date: 3/30/14
  Time: 11:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="postgraduatems.persistence.entities.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Lecturer detail</title>
    <% Postgraduate postgraduate = (Postgraduate) request.getAttribute("postgraduate");%>
    <% DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");%>

</head>
<body>
  <div id="wrapper">
    <div id="header">
        <%@ include file="header.jsp" %>
    </div>
    <div id="content">
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
                    <th>Ngành học</th>
                    <td><%= postgraduate.getMajor().getName()%></td>
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
            <p>Thông tin đề tài</p>
            <table class="table table-striped" style="width:600px">
                <tr>
                    <th style="width:200px">Tên đề tài</th>
                    <td><%= postgraduate.getThesis().getName()%></td>
                </tr>

                <tr>
                    <th>Trạng thái</th>
                    <td><%= postgraduate.getThesis().getState()%></td>
                </tr>


                <tr>
                    <th>Đề cương</th>

                    <%if (postgraduate.getThesis().isFileUploaded()) {%>
                    <td><a href="/common/thesis/download?pid=<%=postgraduate.getId()%>">Tải đề cương</a></td>
                    <%} else {%>
                    <td>Chưa có</tr>
                    <%}%>
                </tr>
                <tr>
                    <th>Nhận xét</th>
                    <td><%=postgraduate.getThesis().getComment()%></td>
                </tr>


                <% int i = 0; for (postgraduatems.persistence.entities.Lecturer lecturer : postgraduate.getThesis().getReviewers()) {

                %>
                <tr>
                    <th>Giảng viên phản biện <%=++i%></th>
                    <td>
                        <a href="/postgraduate/lecturer/detail?id=<%=lecturer.getId()%>">
                            <%=lecturer.getDegree() + ". " + lecturer.getName()%>
                        </a>
                    </td>
                <tr/>

                <%}%>

            </table>


            <p>Thông tin giảng viên</p>
            <table class="table table-striped" style="width:600px">
                <tr>
                    <th style="width:200px">Tên giảng viên</th>
                    <td>
                        <%= (postgraduate.getLecturer() == null) ? "Chưa có giảng viên" : postgraduate.getLecturer().getName()%>
                    </td>
                </tr>
                <tr>
                    <th>Học vị</th>
                    <td>
                        <%= (postgraduate.getLecturer() == null) ? "Chưa có thông tin" : postgraduate.getLecturer().getDegree()%><br/>
                    </td>
                </tr>
                <tr>
                    <th>Bộ môn</th>
                    <td>
                        <%= (postgraduate.getLecturer() == null) ? "Chưa có thông tin" :
                                postgraduate.getLecturer().getMajor().getName()%><br/>
                    </td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td>
                        <%= (postgraduate.getLecturer() == null) ? "Chưa có thông tin" : postgraduate.getLecturer().getEmail()%>
                    </td>
                </tr>
                <tr>
                    <th>Số điện thoại</th>
                    <td>
                        <%= (postgraduate.getLecturer() == null) ? "Chưa có thông tin" : postgraduate.getLecturer().getPhoneNumber()%>
                    </td>
                </tr>
                <tr>
                    <th>Địa chỉ</th>
                    <td>
                        <%= (postgraduate.getLecturer() == null) ? "Chưa có thông tin" : postgraduate.getLecturer().getAddress()%>
                    </td>
                </tr>
            </table>
            <% } %>
          </div>
      </div>
    </div>

    <div><%@ include file="footer.jsp" %></div>
  </div>
</body>
</html>
