<%--
  Created by IntelliJ IDEA.
  User: dotoan
  Date: 3/26/14
  Time: 10:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="postgraduatems.persistence.entities.Postgraduate" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh sách học viên</title>
    <script charset="UTF-8">
        function getLectureDetail(id) {
            $.post("/dept/lecturer/detail",
                    {
                        id: id
                    },
                    function (data, status) {
                        if (data == "false") {
                            alert("Lecturer services chưa sẵn sàng");
                        } else {
                            if (id != "false") {
                                location.href = "/dept/lecturer/detail?id=" + id;
                            }
                        }
                    });
        }
        function getPostgraduateDetail(id) {
            location.href = "/dept/postgraduate/detail?id=" + id;
        }

        function save(thesis_id, comment_edit, comment_id, save, cancel, edit) {

            var thesis_comment = document.getElementById(comment_edit).value;
            // thesis_comment = thesis_comment.replace(/\n|\r\n|\r/g, "<br/>");
            thesis_comment = thesis_comment.trim();

            $.post("/dept/thesis/commend",
                    {
                        tid: thesis_id,
                        comment: thesis_comment
                    },
                    function (data, status) {
                        if (data == "false") {
                            alert("Thesis services chưa sẵn sàng");
                        } else {

                            document.getElementById(comment_id).innerHTML = data;
                            document.getElementById(save).style.visibility = "hidden";
                            document.getElementById(comment_id).style.visibility = "visible";
                            document.getElementById(cancel).style.visibility = "hidden";
                            document.getElementById(edit).style.visibility = "visible";
                            document.getElementById(comment_edit).style.visibility = "hidden";

                        }
                    });


        }


        function edit(edit, save, cancel, comment_id, comment_edit) {
            document.getElementById(edit).style.visibility = "hidden";
            document.getElementById(comment_id).style.visibility = "hidden";
            document.getElementById(save).style.visibility = "visible";
            document.getElementById(comment_edit).style.visibility = "visible";
            document.getElementById(cancel).style.visibility = "visible";
        }


        function cancel(cancel, save, edit, comment_id, comment_edit) {
            document.getElementById(cancel).style.visibility = "hidden";
            document.getElementById(save).style.visibility = "hidden";
            document.getElementById(edit).style.visibility = "visible";
            document.getElementById(comment_id).style.visibility = "visible";
            document.getElementById(comment_edit).style.visibility = "hidden";
            var comment = document.getElementById(comment_id).innerText;
            document.getElementById(comment_edit).value = comment;


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
                <%List<Postgraduate> postgraduates = (List<Postgraduate>) request.getAttribute("postgraduates");%>
                <h1>Danh sách học viên</h1>

                <form action="/dept/thesis/commend" method="get">
                    <input type="text" name="keyword" placeholder="Từ khóa" id="keyword_id" size="30"
                            value="<%=(request.getParameter("keyword") == null ? "" : request.getParameter("keyword"))%>" style="width: 250px"/>
                    <select name="year" class="postgraduate-select" id="year_id">
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


                    <select name="state" class="postgraduate-select" id="state_id" style="width: 180px">
                        <option value="">Chọn trạng thái đề tài</option>
                        <%
                            String selectedState = request.getParameter("state");

                            List<String> states = (List<String>) request.getAttribute("states");
                            for (String state : states) {
                        %>
                        <option value="<%= state%>"  <%=((state != null && state.equals(selectedState) ) == true) ?
                                "selected" : "" %>  ><%= state%>
                        </option>
                        <% }%>
                    </select>

                    <input type="submit" value="Tìm kiếm" class="btn btn-primary find-submit">

                </form>
                <table class="postgraduate-table-result table table-bordered" id="postgraduate_list">
                    <tr class="label-info">
                        <th>Học viên</th>
                        <th>Đề tài</th>
                        <th>Khóa</th>
                        <th>GVHD</th>
                        <th>Đề cương</th>
                        <th>Nhận xét của bộ môn</th>


                    </tr>

                    <% if (postgraduates != null) { %>

                        <% for (Postgraduate p : postgraduates) { %>
                        <tr>
                            <td> <a href="#" onclick="getPostgraduateDetail('<%= p.getId()%>')">
                                <%= p.getName() %></a></td>
                            <td style="width:200px; word-wrap:break-word;"><%= (p.getThesis().getName().equals("")) ?
                                    "Chưa có đề tài" : p.getThesis().getName(

                            ) %>
                            </td>
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
                            <td>
                               <%if (p.getThesis().isFileUploaded()) {%>
                                <a href="/common/thesis/download?pid=<%=p.getId()%>">Xem</a>
                               <%} else {%>
                                Chưa có
                                <%}%>
                            </td>
                            <input type="hidden" name="thesisId" value="<%=p.getThesis().getId()%>"/>
                            <input type="hidden" name="year" id="h_year_<%=p.getId()%>"/>
                            <input type="hidden" name="state" id="h_state_<%=p.getId()%>"/>
                            <input type="hidden" name="keyword" id="h_keyword_<%=p.getId()%>"/>
                            <td style="width:400px; word-wrap:break-word;">
                                <div>
                                    <div id="comment_id_<%=p.getId()%>">
                                       <%
                                        if (p.getThesis().getComment() == null ||
                                                p.getThesis().getComment().equals("")) {
                                       %>
                                            Chưa có nhận xét nào
                                        <%} else {%>
                                            <%=p.getThesis().getComment()%>
                                        <%}%>
                                    </div>
                                    <a onclick='edit("edit_<%=p.getId()%>", "save_<%=p.getId()%>",
                                            "cancel_<%=p.getId()%>", "comment_id_<%=p.getId()%>", "comment_edit_<%=p.getId()%>")'
                                       href="#" id="edit_<%=p.getId()%>">Sửa</a>
                                    <textarea id="comment_edit_<%=p.getId()%>" name="comment" style="visibility: hidden;
                                 width: 350px;"><%=p.getThesis().getComment()%></textarea>
                                </div>


                                <a onclick='save("<%=p.getThesis().getId()%>", "comment_edit_<%=p.getId()%>",
                                        "comment_id_<%=p.getId()%>", "save_<%=p.getId()%>", "cancel_<%=p.getId()%>",
                                        "edit_<%=p.getId()%>")'
                                   style="visibility: hidden"
                                   id="save_<%=p.getId()%>" href="#">Lưu</a>
                                <a onclick='cancel("cancel_<%=p.getId()%>", "save_<%=p.getId()%>",
                                        "edit_<%=p.getId()%>", "comment_id_<%=p.getId()%>", "comment_edit_<%=p.getId()%>")'
                                   href="#" id="cancel_<%=p.getId()%>" style="visibility: hidden">Hủy</a>

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
