<%--
  Created by IntelliJ IDEA.
  User: dotoan
  Date: 4/16/14
  Time: 8:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="postgraduatems.persistence.entities.Lecturer" %>
<%@ page import="java.util.List" %>
<%@ page import="postgraduatems.persistence.entities.Postgraduate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
  <div id="wrapper">
    <div id="header">
      <%@ include file="header.jsp" %>
    </div>
    <div id="content">
      <h1>Đăng kí đề tài</h1>
      <% Object thesisBOError = request.getAttribute("thesisBOError");%>
      <% if (thesisBOError != null) {%>
        <div class="alert alert-warning" align="center">

           <h2>Dịch vụ từ thành phần Thesis Services chưa sẵn sàng!<br>
           Chức năng đăng kí đề tài hiện tại chưa thể sử dụng!</h2>

        </div>
      <%} else {%>
      <% 
        Postgraduate postgraduate = (Postgraduate) request.getAttribute("postgraduate");
        Object readonlyAttr =   request.getAttribute("readonly");
     //   System.out.println(readonlyAttr);
        String readonly = ""; String hidden="";
        if (readonlyAttr != null){
          readonly = "readonly";
          hidden ="style='visibility:hidden'";
        }
      %>
      <div class="row">
        <div class="center span8 offset4">
          <% if (readonlyAttr != null) { %>
            <div class="alert alert-warning">
              Bạn không thể điều chỉnh thông tin luận văn!
            </div>
          <% } %>
          <% Object registerSuccess = request.getAttribute("updatedThesis");%>
          <% if (registerSuccess != null) { %>
            <div class="alert alert-success">
              Thông tin đề tài được cập nhật thành công!
            </div>
          <% } %>
          <% Object fieldsError = request.getAttribute("fieldsError");%>
            <% if (fieldsError != null) { %>
              <div class="alert alert-error">
                Nhập thông tin đề tài và chọn giảng viên!
              </div>
            <% } %>

            <% Object chosenFile = request.getAttribute("chosenFile");%>
            <% if (chosenFile != null) { %>
            <div class="alert alert-error">
                Phải chọn đề cương để tải lên!
            </div>
            <% } %>

            <% Object sizeError = request.getAttribute("sizeError");%>
            <% if (sizeError != null) { %>
            <div class="alert alert-error">
                Kích thước đề cương không được quá 1MB!
            </div>
            <% } %>

            <% Object formatError = request.getAttribute("formatError");%>
            <% if (formatError != null) { %>
            <div class="alert alert-error">
               Đề cương phải ở định dạng pdf!
            </div>
            <% } %>



            <form action="/postgraduate/thesis/update" method="POST" enctype="multipart/form-data">
            <label>Tên đề tài</label>
            <textarea rows="4" cols="40" name="thesisName" <%= readonly %>><%= postgraduate.getThesis().getName()%></textarea>
            <%
              List<Lecturer> lecturers = (List<Lecturer>) request.getAttribute("lecturers");
              String selected="";
            %>
            <label>Giảng viên hướng dẫn</label>
            <% if (lecturers != null) { %>
              <select name="lecturerId" <%= readonlyAttr != null ? "disabled" : ""%>>
                <option value="">Chọn giáo viên hướng dẫn</option>

                <% for (Lecturer lecturer : lecturers) { %>
                  <% 
                    if (postgraduate.getLecturer() != null) {
                      if (postgraduate.getLecturer().getId() == lecturer.getId()) {
                          selected = "selected";
                      }
                    }
                  %>
                  <option value="<%= lecturer.getId()%>" <%= selected %> >
                      <%= lecturer.getDegree() + ". " + lecturer.getName()%>
                  </option>
                  <% selected = ""; %>
                <% } %>
              </select>
            <% } %>
            <br/>
              <p class="info">Đính kèm đề cương (định dạng pdf và không quá 1MB)</p>
                <input  <%= readonlyAttr != null
                    ? "disabled" : ""%> type="file" name="file"/>
                 <br/>

                <input type="submit" value="Ghi nhận" class="btn btn-primary btn-large" <%=hidden%>/>

          </form>

        </div>
      </div>
        <%}%>
    </div>
    <div id="footer"><%@ include file="footer.jsp" %></div>
  </div>
</body>
</html>
