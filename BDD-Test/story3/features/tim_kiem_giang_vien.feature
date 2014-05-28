
Feature: Tìm kiếm giảng viên
    Là người dùng tôi có thể  giảng viên theo họ tên, nơi làm việc
    Kết quả là một danh sách giảng viên, với mỗi giảng viên cần liệt kê các thông tin sau đây trên 1 dòng:
    họ tên, học hàm học vị, khoa, nơi làm việc

Scenario Outline: Tìm kiếm giảng viên
  Given Tôi đã đăng nhập với tài khoản "<tài khoản>"
    And Tôi chọn xem danh sách giảng viên
  When Tôi nhập thông tin tìm kiếm như sau "<từ khóa>"
  Then Tôi sẽ nhìn thấy danh sách giảng viên với đầy đủ các thông tin như yêu cầu
    
Examples:
  |tài khoản                  |từ khóa             |
  |giảng viên                 |Bùi Thế Duy         |
  |giảng viên                 |Lê Phê Đô           |
  |giảng viên                 |Tiến sĩ             |
  |bộ môn                     |dacphuong@vnu.edu.vn|
  |bộ môn                     |Hà Nội              |
  |bộ môn                     |trucmai@vnu.edu.vn  |
  |cán bộ khoa                |Đoàn Minh Phương    |
  |cán bộ khoa                |ngochung@vnu.edu.vnu|
  |cán bộ khoa                |09546546553         |
  |cán bộ khoa                |Phạm Ngọc Hùng      |