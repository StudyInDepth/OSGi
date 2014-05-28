<%@ page import="postgraduatems.persistence.entities.Postgraduate" %>
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
<script src="../bootstrap/js/html5shiv.js"></script>
<![endif]-->

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

<% Postgraduate myPostgraduate = (Postgraduate) session.getAttribute("postgraduate");%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="navbar navbar-fixed-top navbar-inverse">

  <div class="navbar-inner">
    <div class="container">
      <div id="logo">UET</div>
      <nav>

        <ul class="nav pull-right">
          <li><a href="/postgraduate/home">Trang chủ</a></li>
          <li class="dropdown">
                <a href="#" class="dropdown-toggle">Học viên</a>
                <ul class="dropdown-menu">
                  <li><a href="/postgraduate/postgraduate/list">Danh sách học viên</a></li>
                </ul>
              </li> 
          <li>
          <li class="dropdown">
            <a href="#" class="dropdown-toggle">Luận văn</a>
            <ul class="dropdown-menu">
              <li><a href="/postgraduate/thesis/update">Đăng kí đề tài</a></li>
              <li><a href="/postgraduate/seminar/register">Đăng kí Seminar</a></li>
              <li><a href="/postgraduate/seminasubcommittee/detail">Thông tin tiểu ban</a></li>
            </ul>
          </li>     
          <li class="dropdown">
            <a href="#" class="dropdown-toggle">Học viên <%= myPostgraduate.getName()%></a>
            <ul class="dropdown-menu">
              <li><a href="/postgraduate/userpostgraduate/detail">Thông tin học viên</a></li>
              <li><a href="#">Đổi mật khẩu</a></li>
              <li class="divider"></li>
              <li><a href="/logout">Đăng xuất</a></li>
            </ul>
          </li>   
        </ul>
      </nav>
    </div>
  </div>
</header>