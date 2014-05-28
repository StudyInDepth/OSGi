<%--
  Created by IntelliJ IDEA.
  User: dotoan
  Date: 3/26/14
  Time: 10:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="postgraduatems.persistence.entities.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Danh sách học viên</title>
    <script>

        function getLectureDetail(id) {
            $.post("/lecturer/lecturer/detail",
                    {
                        id: id
                    },
                    function (data, status) {
                        if (data == "false") {
                            alert("Lecturer services chưa sẵn sàng");
                        } else {
                            if(id != "false") {
                                location.href = "/lecturer/lecturer/detail?id=" + id;
                            }
                        }
                    });
        }

        function getPostgraduateDetail(id) {
            location.href = "/lecturer/postgraduate/detail?id=" + id;
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
                <h1>Danh sách đề tài phản biện</h1>

                <table class="postgraduate-table-result table table-bordered" id="postgraduate_list">
                    <tr class="label-info">
                        <th>Học viên</th>
                        <th>Đề tài</th>
                        <th>Khóa học</th>
                        <th>Giảng viên hướng dẫn</th>
                        <th>Trạng thái</th>

                    </tr>
                    <% List<Thesis> theses = (List<Thesis>) request
                            .getAttribute("theses"); %>
                    <% if (theses != null) { %>

                    <% for (Thesis thesis : theses) { %>
                    <tr>
                        <td> <a href="#" onclick="getPostgraduateDetail('<%= thesis.getPostgraduate().getId()%>')">
                            <%= thesis.getPostgraduate().getName() %></a></td>
                        <td><%= thesis.getName() %></td>
                        <td><%= thesis.getPostgraduate().getAcademicYear() %></td>
                        <td>
                            <%if (thesis.getPostgraduate().getLecturer() != null) {%>
                            <a href="#"
                               onclick="getLectureDetail('<%=thesis.getPostgraduate().getLecturer().getId()%>')">
                                <%= thesis.getPostgraduate().getLecturer().getName()%>
                            </a>
                            <%} else {%>
                            Chưa có giảng viên
                            <%}%>
                        </td>
                        <td><%=thesis.getState()%>

                        </td>
                    </tr>
                    <% } %>

                    <% } %>
                </table>
            </div>
        </div>
    </div>
    <div id="footer"><%@ include file="footer.jsp" %></div>
</div>
</body>
</html>
