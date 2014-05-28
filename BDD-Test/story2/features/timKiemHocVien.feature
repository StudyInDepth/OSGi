
Feature: Tìm kiếm học viên
    Là người dùng tôi có thể tìm kiếm học viên theo tên học viên, đề tài, trạng thái đề tài,
        hoặc giáo viên hướng dẫn của một khóa hoặc tất cả.
    Kết quả là một danh sách học viên, với mỗi học viên cần liệt kê các thông tin sau đây trên 1 dòng:
    họ tên, tên đề tài, khóa học, giáo viên hướng dẫn, trạng thái luận văn

Scenario Outline: Tìm kiếm học viên
    Given Tôi đã đăng nhập với tài khoản "<tài khoản>"
        And Tôi chọn xem danh sách học viên
    When Tôi nhập thông tin tìm kiếm như sau "<từ khóa>" chọn khóa "<khóa>"
    Then Tôi sẽ nhìn thấy danh sách học viên của khóa này với đầy đủ các thông tin như yêu cầu

Examples:
    Examples:
    |tài khoản                  |từ khóa            |khóa           |
    |học viên                   | Nguyễn Ngọc Thoại |17             |
    |học viên                   | Nguyễn Ngọc Thoại |19             |
    |học viên                   | Hà Đức Trung      |17             |
    |học viên                   | Lê Khánh Trình    |Tất cả         |
    |giảng viên                 | Đỗ Văn Toàn       |17             |
    |giảng viên                 | Nguyễn Hữu Kết    |18             |
    |giảng viên                 | Nguyễn Ngọc Thoại |17             |
    |bộ môn                     | Nguyễn Ngọc Thoại |17             |
    |bộ môn                     | Nguyễn Ngọc Thoại |17             |
    |bộ môn                     | Toán tin ứng dụng |Tất cả         |
    |bộ môn                     | Nhận dạng hình ảnh|Tất cả         |
    |cán bộ khoa                | Bùi Thế Duy       |18             |
    |cán bộ khoa                | Hoàn thành        |17             |
    |cán bộ khoa                | Hoàn thành        |Tất cả         |
    |cán bộ khoa                | Chưa bắt đầu      |17             |