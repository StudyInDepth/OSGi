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
<%@ page import="postgraduatems.persistence.entities.Major" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách học viên</title>
    <script>
      function getLectureDetail(id) {
        $.post("/faculty/lecturer/detail",
          {
              id: id
          },
          function (data, status) {
              if (data == "false") {
                  alert("Lecturer services chưa sẵn sàng");
              } else {
                  if(id != "false") {
                  location.href = "/faculty/lecturer/detail?id=" + id;
                  }
              }
          });
      }
      function getPostgraduateDetail(id) {
          location.href = "/faculty/postgraduate/detail?id=" + id;
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
          <%Object postgraduateBOError = request.getAttribute("postgraduateBOError");%>
          <% if (postgraduateBOError != null) {%>
            <div class="alert alert-warning">
                Dịch vụ từ thành phần Postgraduate Services chưa sẵn sàng để sử dụng!
            </div>
          <%} else {%>
          <h1>Danh sách học viên</h1>
          <form method="post" action="/faculty/postgraduate/list">
            <div>
                <input type="text" name="keyword" size="40" class="postgraduate-keyword" placeholder="Từ khóa"
                  value="<%=(request.getParameter("keyword") == null) ? "" : request.getParameter("keyword")%>"/>

                <select id="chon_khoa" name="year" class="postgraduate-select" style="width:auto;">
                  <option value="">Chọn khóa</option>
                  <%
                    String selectedYear = request.getParameter("year");
                    int _year = 0;
                    if (selectedYear != null && selectedYear.equals("") == false) {
                        _year = Integer.parseInt(selectedYear);
                    }
                    List<Integer> years = (List<Integer>) request.getAttribute("years");
                    for (int year : years) {
                  %>
                  <option value="<%= year%>"  <%=((_year != 0 && _year == year) == true) ? 
                    "selected" : "" %>  ><%= year%>
                  </option>
                  <% }%>
                </select>


                <select id="chon_major" name="major" class="postgraduate-select" style="width:auto;">
                    <option value="">Chọn ngành</option>
                    <%
                        String selectedMajorId = request.getParameter("major");

                        List<Major> majors = (List<Major>) request.getAttribute("majors");
                        for (Major major : majors) {
                    %>
                    <option value="<%= major.getId()%>"  <%=(selectedMajorId != null && !selectedMajorId.equals("")
                            && Integer.parseInt(selectedMajorId) == major.getId()) ?
                            "selected" : "" %>  ><%= major.getName()%>
                    </option>
                    <% }%>
                </select>

                <select id="chon_state" name="state" class="postgraduate-select" style="width:auto;">
                    <option value="">Trạng thái đề tài</option>
                    <%
                        String selectedState = request.getParameter("state");

                      List<String> states = (List<String>) request.getAttribute("states");
                        for (String state : states) {
                    %>
                    <option value="<%= state%>"  <%=((selectedState != null && selectedState.equals(state)) == true) ?
                            "selected" : "" %>  ><%= state%>
                    </option>
                    <% }%>
                </select>

                <input type="submit" value="Tìm kiếm" class="postgraduate-submit btn btn-primary"/>
            </div>
          </form>
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
                  <td><%= (p.getThesis().getName().equals("")) ? "Chưa có đề tài" : p.getThesis().getName() %></td>
                <td><%= p.getAcademicYear() %></td>
                  <td>

                      <%if (p.getLecturer() != null) {%>
                      <a href="#"
                         onclick="getLectureDetail('<%=p.getLecturer().getId()%>')">
                          <%= p.getLecturer().getDegree() + ". " + p.getLecturer().getName()%>

                      </a>
                      <%} else {%>
                      Chưa có giảng viên
                      <%}%>

                  </td>
                <td id="state_<%= p.getThesis().getId() %>"><%= p.getThesis().getState()%>
                </td>
              </tr>
            <% } %>

            <% } %>
          </table>
            <%}%>
        </div>
      </div>
    </div>
    <div id="footer"><%@ include file="footer.jsp" %></div>
  </div>
</body>
</html>
