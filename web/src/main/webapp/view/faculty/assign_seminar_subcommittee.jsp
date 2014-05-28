<%@ page import="postgraduatems.persistence.entities.Postgraduate" %>
<%@ page import="java.util.List" %>
<%@ page import="postgraduatems.persistence.entities.SeminarSubCommittee" %>
<%--
  Created by IntelliJ IDEA.
  User: dotoan
  Date: 5/14/14
  Time: 11:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    function assign(pid, ss_name) {
        var select_ssid = document.getElementById("select_ssid").value;
        if (select_ssid != "") {
            $.post("/faculty/seminarsubcommittee/assign",
                    {
                        pid: pid,
                        ssid: select_ssid
                    },
                    function (data, status) {
                        if (data == "false") {
                            alert("Seminar services chưa sẵn sàng");
                        } else if (data == "empty_ssid"){
                            alert("Phải chọn tiểu ban trước!");
                        } else {
                            document.getElementById(ss_name).innerHTML = data;
                        }
                    });

        } else {
            alert("Phải chọn tiểu ban trước!");
        }
    }

    function changeButton() {
        var select = document.getElementById("select_ssid");
        var button_text = select.options[select.selectedIndex].innerText;
        var buttons = document.getElementsByTagName("button");

        for (var i = 0; i < buttons.length; i++) {
            buttons[i].innerHTML = button_text;
        }
    }
</script>

<%List<Postgraduate> postgraduates = (List<Postgraduate>) request.getAttribute("postgraduates");%>
<%List<SeminarSubCommittee> seminarSubCommittees = (List<SeminarSubCommittee>) request.getAttribute("seminarSubCommittees");%>
<%Object emptySeminarSubCommittees =  request.getAttribute("emptySeminarSubCommittees");%>

<%
    System.out.println(seminarSubCommittees + " nullpoint");
    System.out.println(emptySeminarSubCommittees + " nullpoint 1");
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
      <h1>Xếp học viên vào tiểu ban</h1>
        <%Object fieldError = request.getAttribute("fieldError");%>

        <div class="row">
        <div class="span14 offset2">
          <%Object seminarServices = request.getAttribute("seminar_services_unavailable");%>
          <%if (seminarServices != null) {%>
            <div class="alert alert-warning" style="width: 550px">
               Dịch vụ của thành phần Seminar Services chưa sẵn sàng!
            </div>
          <%} else { %>

          <%if (fieldError != null) {%>
            <div class="alert alert-warning" style="width: 550px">
              Phải chọn tiểu ban để phân học viên vào!
            </div>
          <%}%>

            <%if (emptySeminarSubCommittees != null) {%>
            <div class="alert alert-warning" style="width: 550px">
                Chưa có tiểu ban nào được mở!
            </div>
            <%}%>

            <%if (seminarSubCommittees != null &&  postgraduates != null) {%>
          Chọn tiểu ban để phân học viên vào!
          <div style="width: 150px">
          <select name="ssid" id="select_ssid" onchange="changeButton()" style="width:auto;">
              <% String selectedSeminarSubCommittee = request.getParameter("ssid"); int ssid = 0;
                  System.out.println(selectedSeminarSubCommittee + " selected ssid");
              %>
              <% if (selectedSeminarSubCommittee != null && !selectedSeminarSubCommittee.equals("")) {%>
                  <% ssid = Integer.parseInt(selectedSeminarSubCommittee);%>
              <%}%>
              <option value="">Chọn tiểu ban</option>
              <% for (SeminarSubCommittee seminarSubCommittee : seminarSubCommittees) {%>

                    <option value="<%=seminarSubCommittee.getId()%>"
                          <%= (ssid == seminarSubCommittee.getId()) ? "selected" : ""%> >
                          <%= seminarSubCommittee.getName()%>
                    </option>
              <%}%>
          </select>
          </div>
          <table class="table table-striped" style="width:1000px">
                <tr>
                    <th>Họ tên</th>
                    <th>Khóa</th>
                    <th>Ngành</th>
                    <th>Tên đề tài</th>
                    <th>Tiểu ban</th>
                    <th style="width: 200px">Phân vào tiểu ban</th>
                </tr>
            <% for (Postgraduate postgraduate : postgraduates) {%>
                <tr>
                    <td>
                        <a href="/faculty/postgraduate/detail?id=<%=postgraduate.getId()%>">
                            <%= postgraduate.getName()%>
                        </a>
                    </td>
                    <td><%= postgraduate.getAcademicYear()%></td>
                    <td><%= postgraduate.getMajor().getName()%></td>
                    <td><%= postgraduate.getThesis().getName()%></td>
                    <td id="ss_name_<%=postgraduate.getId()%>">
                        <%= (postgraduate.getSeminarSubCommittee() == null) ? "Chưa xếp" :
                                postgraduate.getSeminarSubCommittee().getName() %>
                    </td>
                    <td>
                      <button id="button_<%= postgraduate.getId() %>" onclick='assign("<%= postgraduate.getId()%>", "ss_name_<%=postgraduate.getId()%>")' class="btn-primary">Chọn tiểu ban</button>
                    </td>
                </tr>
            <%}%>
            <%}%>
            </table>
            <%}%>
        </div>
      </div>
    </div>
    <div id="foooter"><%@ include file="footer.jsp" %></div>
  </div>
</body>
</html>