
Feature: Mở tiểu ban seminar
  là người dùng của khoa, tôi có thể xác nhận đề tài đăng kí của học viên

Background: khởi tạo dữ liệu
  Given Tôi khởi tạo dữ liệu

Scenario Outline:  Xếp học viên thành công
  Given Tôi đã đăng nhập với người dùng cán bộ khoa
    And Tôi đang ở phần xếp học viên vào tiểu ban seminar
  When Tôi xếp cho các học viên đã đăng kí vào tiểu ban "<tiểu ban>"
  Then Các học viên đó sẽ được chuyển sang tiểu ban mới thành công
Examples:
  |tiểu ban                           |
  |Tiểu ban Khoa học máy tính        |
  |Tiểu ban Hệ thông thông tin        |

Scenario: Không thể xếp tiểu ban  
  Given Tôi đã đăng nhập với người dùng cán bộ khoa
    And Tôi đang ở phần xếp học viên vào tiểu ban seminar
  Then Tôi không thể xếp cho các học viên chưa đăng kí vào tiểu ban