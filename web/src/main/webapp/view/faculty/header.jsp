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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="navbar navbar-fixed-top navbar-inverse">
  <div class="navbar-inner">
    <div class="container">
      <div id="logo">UET</div>
      <nav>
        <ul class="nav pull-right">
            <li><a href="/faculty/home">Trang chủ</a></li>
            <li><a href="/faculty/lecturer/list">Danh sách giảng viên</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle">Học viên</a>
              <ul class="dropdown-menu">
                <li><a href="/faculty/postgraduate/list">Danh sách học viên</a></li>
                <li><a href="/faculty/postgraduate/add">Thêm học viên</a></li>
                <li class="divider"></li>
                <li><a href="/faculty/thesis/accept">Nhận đề tài</a></li>
                <li><a href="/faculty/thesis/verify">Duyệt đề tài</a></li>
                <li><a href="/faculty/startedtheses/list">Phân giảng viên phản biện</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle">Seminar</a>
              <ul class="dropdown-menu">
                <li><a href="/faculty/seminar/open">Mở seminar</a></li>
                <li><a href="/faculty/seminar/list">Xem seminar</a></li> 
                <li class="divider"></li>
                <li><a href="/faculty/seminarsubcommittee/open">Mở tiểu ban seminar</a></li>
                <li><a href="/faculty/seminarsubcommittee/assign">Xếp học viên vào tiểu ban</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle">Cán bộ khoa CNTT</a>
              <ul class="dropdown-menu">
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
