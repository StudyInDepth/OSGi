
Feature: xem thông tin chi tiết học viên
  Thông tin chi tiết của học viên gồm có: 
  - Thông tin cá nhân: họ tên, khóa học, số điện thoại, email, địa chỉ.
  - Thông tin về đề tài: tên đề tài, trạng thái.
  - Thông tin về giảng viên: tên giảng viên, học vị, email, số điện thoại, địa chỉ.
  Với người dùng là giảng viên, khoa, bộ môn tôi có thể xem thông tin chi tiết của tất cả học viên.
  Với người dùng là học viên thì chỉ được xem thông tin chi tiết của mình.

Scenario Outline: xem thông tin chi tiết học viên
  Given Tôi đã đăng nhập với người dùng "<người dùng>"
    And Tôi đang ở phần xem danh sách học viên
  When Tôi muốn xem thông tin chi tiết của học viên "<họ tên>"
  Then Tôi sẽ nhìn thấy thông tin chi tiết của học viên đó
    
Examples:
  |người dùng                 |họ tên              |
  |giảng viên                 |Doãn Huyền Trang    |
  |giảng viên                 |Đỗ Văn Toàn         |
  |bộ môn                     |Nguyễn Hữu Kết      |
  |bộ môn                     |Nguyễn Ngọc Thoại   |
  |bộ môn                     |Lê Khánh Trình      |
  |cán bộ khoa                |Mai Việt Anh        |
  |cán bộ khoa                |Nguyễn Hoàng Hà     |
  |cán bộ khoa                |Nguyễn Ngọc Thoại   |
  |học viên                   |Doãn Huyền Trang    |
  |học viên                   |Phạm Anh Tú         |

Scenario: Không thể xem thông tin chi tiết của viên khác
  Given Tôi đã đăng nhập với người dùng "học viên"
    And Tôi đang ở phần xem danh sách học viên
  Then Tôi sẽ không thể xem được thông tin chi tiết của các học viên
