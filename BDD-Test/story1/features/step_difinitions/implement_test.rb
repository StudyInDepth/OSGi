Given(/^Tôi đang ở "(.*?)"$/) do |link|
  visit link
end

When(/^Tôi đăng nhập với người dùng "(.*?)"$/) do |account_name|
  @account= DATA["Người dùng"][account_name.to_s]
  fill_in "email", :with => @account["email"]
  fill_in "password", :with => @account["password"]
  click_button "Đăng nhập"
end

Then(/^Tôi sẽ đăng nhập thành công vào trang của người dùng đó$/) do
  should have_no_link "Đăng nhập"
  click_link @account["Tên"]
  should have_link "Đăng xuất"
end

Then(/^Tôi có thể đăng xuất ra khỏi hệ thống$/) do
  click_on "Đăng xuất"
  should have_title "Đăng nhập"
  should have_link "Đăng nhập"
  should have_no_link "Đăng xuất"
end

When(/^Tôi đến trang "(.*?)"$/) do |link|
  visit link
end

Then(/^Tôi sẽ không phải đăng nhập lại$/) do
  should have_no_link "Đăng nhập"
  click_link @account["Tên"]
  should have_link "Đăng xuất"
end

When(/^Tôi đăng nhập với email là "(.*?)" password là "(.*?)"$/) do |email, password|
  fill_in "email", :with => email
  fill_in "password", :with => password
  click_button "Đăng nhập"
end

Then(/^Tôi không đăng nhập được vào hệ thống, tôi bị quay lại trang login và được thông báo về lỗi khi đăng nhập\.$/) do
  should have_title "Đăng nhập"
  should have_link "Đăng nhập"
end
