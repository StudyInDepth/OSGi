Given(/^Tôi khởi tạo dữ liệu$/) do
  initialize_database
end


Given(/^Tôi đã đăng nhập với người dùng học viên ở trạng thái "(.*?)"$/) do |trang_thai|
  @account = DATA["Người dùng học viên"][trang_thai]
  visit "/login"
  fill_in "email", :with => @account["Email"]
  fill_in "password", :with => @account["Password"]
  click_button "Đăng nhập"
end

Given(/^Tôi đang ở phần đăng kí seminar$/) do
  click_link "Luận văn"
  click_link "Đăng kí Seminar"
end

Given(/^Seminar đang ở trạng thái "(.*?)"$/) do |trang_thai|
  update_newest_seminar trang_thai
end

When(/^Tôi đăng kí seminar$/) do
  click_button "Đăng kí"
end

Then(/^Tôi đã đăng kí thành công seminar này và tôi có thể hủy đăng kí$/) do
  should have_content "Đăng kí seminar thành công!"
  should have_no_button "Đăng kí"
  click_button "Hủy đăng kí"
  should have_content "Hủy đăng kí seminar thành công!"
end

Then(/^Tôi không thể đăng kí seminar này$/) do
  should have_no_button "Đăng kí"
end
