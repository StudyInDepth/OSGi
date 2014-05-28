
Feature: Xem thông tin seminar
  là người dùng của khoa, tôi có thể xác nhận đề tài đăng kí của học viên

Scenario Outline: Xem thông tin seminar
  Given Tôi đã đăng nhập với người dùng "<người dùng>"
  When Tôi vào phần xem semina
  Then Tôi nhìn thấy thông tin của seminar gần nhất

Examples:
  |người dùng         |
  |cán bộ khoa        |