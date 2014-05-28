
Feature: Mở tiểu ban seminar
  là người dùng của khoa, tôi có thể xác nhận đề tài đăng kí của học viên

Background: khởi tạo dữ liệu
  Given Tôi khởi tạo dữ liệu

Scenario: Mở tiểu ban seminar thành công
  Given Tôi đã đăng nhập với người dùng cán bộ khoa
    And Tôi đang ở phần mở tiểu ban seminar
  When Tôi nhập các thông tin cho tiểu ban semina
    And Tôi ghi nhận
  Then Semina được mở thành công và tôi nhìn thấy thông tin semina tôi vừa mở

Scenario Outline: Mở tiểu ban seminar không thành công
  Given Tôi đã đăng nhập với người dùng cán bộ khoa
    And Tôi đang ở phần mở tiểu ban seminar
  When Tôi nhập các thông tin cho semina "<thông tin lỗi>"
    And Tôi ghi nhận
  Then Tiểu ban seminar mở không thành công và tôi nhìn thấy thông báo lỗi trên màn hình

Examples:
  |thông tin lỗi       |
  |bản ghi lỗi 1       |
  |bản ghi lỗi 2       |
  |bản ghi lỗi 3       |
  |bản ghi lỗi 4       |
  |bản ghi lỗi 5       |
  |bản ghi lỗi 6       |
  |bản ghi lỗi 7       |
  |bản ghi lỗi 8       |