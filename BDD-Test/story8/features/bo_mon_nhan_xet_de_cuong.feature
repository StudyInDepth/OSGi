
Feature: nhận đăng kí đề tài luận văn
  là người dùng của khoa, tôi có thể xác nhận đề tài đăng kí của học viên

Scenario Outline: có thể xác nhận đề tài (học viên chưa được xác nhận đề tài)
  Given Tôi đã đăng nhập với người dùng bộ môn thuộc bộ môn "<bộ môn>"
    And Tôi đang ở phần "Nhận xét đề tài"  
  When Tôi chọn nhận xét cho học viên thuộc khóa "<khóa>"
    And Tôi nhận xét đề tài cho một học viên ở trạng thái "Đợi duyệt đề cương" thuộc chuyên ngành của tôi quản lý
  Then Nhận xét của tôi cho học viên đó sẽ được cập nhật

Examples:
  |bộ môn                 |khóa               |  
  |Công nghệ phần mềm     |19                 |
  |Mạng và truyền thông   |19                 |
  |Hệ thống thông tin     |19                 |
  |Khoa học máy tính      |19                 |
  |Công nghệ phần mềm     |Tất cả             |
  |Mạng và truyền thông   |Tất cả             |
  |Hệ thống thông tin     |Tất cả             |
  |Khoa học máy tính      |Tất cả             |


Scenario Outline: có thể xác nhận đề tài (học viên chưa được xác nhận đề tài)
  Given Tôi đã đăng nhập với người dùng "bộ môn" thuộc chuyên ngành "<chuyên ngành>"
    And Tôi đang ở phần "Nhận xét đề tài"  
  When Tôi chọn nhận xét cho học viên thuộc khóa "<khóa>"
    And Tôi nhận xét đề tài cho một học viên ở trạng thái "Đợi duyệt đề cương" thuộc chuyên ngành của tôi
  Then Tôi không thể nhận xét đề tài cho một học viên không ở trạng thái "Đợi duyệt đề cương" hoặc khác chuyên ngành tôi quản lý

Examples:
  |bộ môn                 |khóa               |
  |Công nghệ phần mềm     |19                 |
  |Mạng và truyền thông   |19                 |
  |Hệ thống thông tin     |19                 |
  |Khoa học máy tính      |19                 |
  |Công nghệ phần mềm     |Tất cả             |
  |Mạng và truyền thông   |Tất cả             |
  |Hệ thống thông tin     |Tất cả             |
  |Khoa học máy tính      |Tất cả             |