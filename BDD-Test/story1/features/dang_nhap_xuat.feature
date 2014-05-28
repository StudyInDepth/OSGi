Feature: Đăng nhập/xuất
    Tôi có thể đăng nhập vào hệ thống bằng email và password. Hệ thống phải phân loại các tài khoản đăng nhập vào hệ thống. Sau khi đăng nhập, các loại tài khoản khác nhau sẽ phải vào trang home tương ứng với tài khoản đó

Background: Đến trang login
    Given Tôi đang ở "/login"

Scenario Outline: Đăng nhập
    When Tôi đăng nhập với người dùng "<người dùng>"
    Then Tôi sẽ đăng nhập thành công vào trang của người dùng đó
        And Tôi có thể đăng xuất ra khỏi hệ thống
Examples:
    |người dùng                 |
    |giảng viên                 |
    |bộ môn                     |
    |cán bộ khoa                |
    |học viên                   |

Scenario Outline: Đăng nhập và quay về trang login
    Tôi đã đăng nhập vào hệ thống, khi tôi quay lại trang đăng nhập tôi sẽ không phải đăng nhập lại và sẽ được chuyển đến trang chủ của tôi.

    When Tôi đăng nhập với người dùng "<người dùng>"
        And Tôi đến trang "/login"
    Then Tôi sẽ không phải đăng nhập lại
Examples:
    |người dùng                 |
    |giảng viên                 |
    |bộ môn                     |
    |cán bộ khoa                |
    |học viên                   |

Scenario Outline: Đăng nhập lỗi
    When Tôi đăng nhập với email là "<email>" password là "<password>"
    Then Tôi không đăng nhập được vào hệ thống, tôi bị quay lại trang login và được thông báo về lỗi khi đăng nhập.
Examples:
    |email                      |password               |
    |                           |123                    |
    |ngocthoaia1@gmail.com      |                       |
    |                           |                       |
    |error email                |error password            |
