
Feature: Mở tiểu ban seminar
  là người dùng của khoa, tôi có thể xác nhận đề tài đăng kí của học viên

Background: khởi tạo dữ liệu
  Given Tôi khởi tạo dữ liệu

Scenario:  Đăng kí thành công
  Given Tôi đã đăng nhập với người dùng học viên ở trạng thái "Đang thực hiện"
    And Tôi đang ở phần đăng kí seminar
    And Seminar đang ở trạng thái "Trong hạn đăng kí"
  When Tôi đăng kí seminar
  Then Tôi đã đăng kí thành công seminar này và tôi có thể hủy đăng kí

Scenario Outline:  Đăng kí không thành công

  Given Tôi đã đăng nhập với người dùng học viên ở trạng thái "<trạng thái>"
    And Tôi đang ở phần đăng kí seminar
    And Seminar đang ở trạng thái "Trong hạn đăng kí"
  Then Tôi không thể đăng kí seminar này
Examples:
  |trạng thái                 |
  |Chưa bắt đầu               |
  |Chuẩn bị đề tài            | 
  |Đợi duyệt đề cương         |
  |Chuẩn bị bảo vệ            |
  |Đã bảo vệ                  |

Scenario Outline:  Đăng kí không thành công
  Given Tôi đã đăng nhập với người dùng học viên ở trạng thái "Đang thực hiện"
    And Tôi đang ở phần đăng kí seminar
    And Seminar đang ở trạng thái "<trạng thái>"
  Then Tôi không thể đăng kí seminar này
Examples:
  |trạng thái        |
  |Chưa được đăng kí |
  |Đã hết hạn đăng kí|