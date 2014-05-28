
Feature: nhận đăng kí đề tài luận văn
  là người dùng của khoa, tôi có thể xác nhận đề tài đăng kí của học viên

Background: khởi tạo dữ liệu
  Given Tôi khởi tạo dữ liệu

Scenario Outline: Mở Semina thành công
  Given Tôi đã đăng nhập với người dùng "cán bộ khoa"
    And Tôi đang ở phần Mở semina
  When Tôi nhập các thông tin cho semina như sau "<tiêu đề>", "<mô tả>", "<dự kiến thực hiện>", "<bắt đầu đăng kí>", "<hết hạn đăng kí>"
    And Tôi ghi nhận
  Then Semina được mở thành công và tôi nhìn thấy thông tin semina tôi vừa mở

Examples:
  |tiêu đề  |mô tả            |dự kiến thực hiện  |bắt đầu đăng kí  |hết hạn đăng kí  |
  |Semina1  |Seminar đầu tiên |to day + 10        |to day + 1      |to day + 3       |
  |Seminar1 |Seminar đầu tiên |to day + 10        |to day + 0      |to day + 9       |


Scenario Outline: Mở Semina không thành công
  Given Tôi đã đăng nhập với người dùng "cán bộ khoa"
    And Tôi đang ở phần Mở semina
  When Tôi nhập các thông tin cho semina như sau "<tiêu đề>", "<mô tả>", "<dự kiến thực hiện>", "<bắt đầu đăng kí>", "<hết hạn đăng kí>"
    And Tôi ghi nhận
  Then Semina được mở không thành công và tôi nhìn thấy thông báo lỗi trên màn hình

Examples:
  |tiêu đề  |mô tả          |dự kiến thực hiện  |bắt đầu đăng kí  |hết hạn đăng kí  |
  |         |Semina đầu tiên|to day + 10        |to day + 1       |to day + 3      |
  |Semina1  |               |to day + 10        |to day + 1       |to day + 3      |
  |Semina1  |Semina đầu tiên|                   |to day + 1       |to day + 3      |
  |Semina1  |Semina đầu tiên|to day + 10        |                 |to day + 3      |
  |Semina1  |Semina đầu tiên|to day + 10        |to day + 1       |                |
  |         |               |                   |                 |                |
  |Semina1  |Semina đầu tiên|to day + 10        |to day + 1       |to day + 1      |
  |Semina1  |Semina đầu tiên|to day + 10        |to day + 1       |to day + 0      |
  |Semina1  |Semina đầu tiên|to day + 3         |to day + 1       |to day + 3      |
  |Semina1  |Semina đầu tiên|to day + 2         |to day + 1       |to day + 1      |
  |Semina1  |Semina đầu tiên|to day + 1         |to day + 1       |to day + 1      |
  |Semina1  |Semina đầu tiên|to day + 0         |to day + 1       |to day + 1      |

