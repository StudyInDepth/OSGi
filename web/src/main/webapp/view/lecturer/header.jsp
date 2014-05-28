
<script src="../bootstrap/js/html5shiv.js"></script>


<link href="../../bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="../../bootstrap/css/custom.css" rel="stylesheet">
<script type="text/javascript" src="../../bootstrap/js/scripts.js"></script>
<script type="text/javascript" src="../../bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" src="../../bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../bootstrap/js/bootstrap.js"></script>
<link href="../../bootstrap/css/style.css" rel="stylesheet">

<script>
     $(document).ready(function(){
        $('.dropdown-toggle').dropdown()
    });
</script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header class="navbar navbar-fixed-top navbar-inverse">

    <div class="navbar-inner">
    <div class="container">
      <div id="logo">Đại học công nghệ</div>
        <%@ page import="postgraduatems.persistence.entities.Lecturer" %>

        <nav>
          <ul class="nav pull-right">
              <li><a href="/lecturer/home">Trang chủ</a></li>
              <li><a href="/lecturer/lecturer/list">DS giảng viên</a></li>
              <li><a href="/lecturer/seminarsubcommittee/detail">Seminar</a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle">Học viên</a>
                <ul class="dropdown-menu">
                  <li><a href="/lecturer/postgraduate/list">Danh sách học viên</a></li>
                  <li><a href="/lecturer/postgraduates/guide">Học viên hướng dẫn</a></li> 
                  <li><a href="/lecturer/theses/review">Đề tài phản biện</a></li>
                </ul>
              </li>

              <% Lecturer myLecturer = (Lecturer) session.getAttribute("lecturer");%>

              <li class="dropdown">
                <a href="#" class="dropdown-toggle">Giảng viên <%= myLecturer.getName()%></a>
                <ul class="dropdown-menu">
                  <li><a href="/lecturer/userlecturer/detail">Xem thông tin giảng viên</a></li>
                  <li><a href="">Đổi mật khẩu</a></li>
                  <li class="divider"></li>
                  <li><a href="/logout">Đăng xuất</a></li>
                </ul>
              </li>   
          </ul>
      </nav>
      </div>
  </div>
</header>