
Feature: nhận đăng kí đề tài luận văn
  là người dùng của khoa, tôi có thể xác nhận đề tài đăng kí của học viên

Background: khởi tạo dữ liệu
  

Scenario Outline: có thể xác nhận đề tài (học viên chưa được xác nhận đề tài)
  Given Tôi khởi tạo dữ liệu
    And Tôi đã đăng nhập với người dùng "cán bộ khoa"
    And Tôi đang ở phần Nhận đề tài
  When Tôi xác nhận đề tài cho các học viên ở trạng thái đề tài "Chuẩn bị đề tài" của khóa "<khóa>"
  Then Trạng thái của các học viên đó sẽ là "Đợi duyệt đề cương"

Examples:
  |khóa               |  
  |19                 |
  |19                 |
  |Tất cả             |
  |Tất cả             |

Scenario Outline: không thể xác nhận đề tài (học viên đã được xác nhận đề tài)
  Given Tôi đã đăng nhập với người dùng "cán bộ khoa"
    And Tôi đang ở phần Nhận đề tài
  Then Tôi sẽ không thể xác nhận đề tài cho các học viên ở trạng thái đề tài "<trạng thái>" của khóa "<khóa>"

Examples:
  |trạng thái                 |khóa               |
  |Chưa bắt đầu               |19                 |
  |Đợi duyệt đề cương         |19                 |
  |Đang thực hiện             |19                 |
  |Chuẩn bị bảo vệ            |19                 |
  |Đã bảo vệ                  |19                 |
  |Chưa bắt đầu               |Tất cả             |
  |Đợi duyệt đề cương         |Tất cả             |
  |Đang thực hiện             |Tất cả             |
  |Chuẩn bị bảo vệ            |Tất cả             |
  |Đã bảo vệ                  |Tất cả             |