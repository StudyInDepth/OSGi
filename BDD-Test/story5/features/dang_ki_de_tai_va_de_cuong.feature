
Feature: nhập thông tin đề tài
  là người dùng học viên, khi tôi đang ở trạng thái Chưa bắt đầu hoặc Chuẩn bị đề tài
  tôi có thể nhập thông tin đề tài của mình
  Sau khi nhập đề tài thì trạng thái của tôi sẽ là chuẩn bị đề tài

Background: khởi tạo dữ liệu
  Given Tôi khởi tạo dữ liệu

Scenario Outline: nhập thông tin đề tài thành công
  Given Tôi đã đăng nhập với người dùng học viên ở trạng thái "<trạng thái>"
    And Tôi đang ở phần đăng kí khóa luận
  When Tôi nhập thông tin đề tài là "<thông tin đề tài>"
    And Tôi chọn giảng viên là "<giảng viên>"
    And Tôi upload file "<tên file>"
  Then Tôi sẽ nhìn thấy thông báo "Thông tin đề tài được cập nhật thành công!"
    And Trạng thái của tôi sẽ là "Chuẩn bị đề tài"
    
Examples:
  |trạng thái                 |thông tin đề tài         |giảng viên               |tên file       |
  |Chưa bắt đầu               |Đề tài 1                 |Lê Đình Thanh            |success.pdf    |
  |Chuẩn bị đề tài            |Đề tài 2                 |Dương Lê Minh            |success.pdf    |

Scenario Outline: nhập thông tin bị lỗi
  Given Tôi đã đăng nhập với người dùng học viên ở trạng thái "<trạng thái>"
    And Tôi đang ở phần đăng kí khóa luận
  When Tôi nhập thông tin đề tài là "<thông tin đề tài>"
    And Tôi chọn giảng viên là "<giảng viên>"
    And Tôi upload file "<tên file>"
  Then Tôi sẽ nhìn thấy thông báo "Nhập thông tin đề tài và chọn giảng viên!"
    And Trạng thái của tôi sẽ không thay đổi
Examples:
  |trạng thái                 |thông tin đề tài         |giảng viên               |tên file           |
  |Chưa bắt đầu               |Đề tài 1                 |Chọn giáo viên hướng dẫn |success.pdf        |
  |Chưa bắt đầu               |                         |Lê Đình Thanh            |success.pdf        |
  |Chưa bắt đầu               |Đề tài 1                 |Lê Đình Thanh            |type-error.error   |
  |Chưa bắt đầu               |Đề tài 1                 |Lê Đình Thanh            |size-error.pdf     |
  |Chuẩn bị đề tài            |Đề tài 2                 |Chọn giáo viên hướng dẫn |success.pdf        |
  |Chuẩn bị đề tài            |                         |Lê Đình Thanh            |success.pdf        |
  |Chưa bắt đầu               |Đề tài 2                 |Lê Đình Thanh            |type-error.error   |
  |Chưa bắt đầu               |Đề tài 2                 |Lê Đình Thanh            |size-error.pdf     |

Scenario Outline: không thể nhập thông tin đề tài
  Given Tôi đã đăng nhập với người dùng học viên ở trạng thái "<trạng thái>"
    And Tôi đang ở phần đăng kí khóa luận
  Then Tôi sẽ không thể nhập thông tin đề tài
Examples:
  |trạng thái                 |
  |Đợi duyệt đề cương         |
  |Đang thực hiện             |
  |Chuẩn bị bảo vệ            |
  |Đã bảo vệ                  |
