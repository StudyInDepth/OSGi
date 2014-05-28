Given(/^Tôi khởi tạo dữ liệu$/) do
  initialize_database
end

Given(/^Tôi đã đăng nhập với người dùng học viên ở trạng thái "(.*?)"$/) do |trang_thai|
  @account = DATA["Người dùng học viên"][trang_thai.to_s]
  visit "/login"
  fill_in "email", :with => @account["Email"]
  fill_in "password", :with => @account["Password"]
  click_button "Đăng nhập"
end

Given(/^Tôi đang ở phần đăng kí khóa luận$/) do
  click_link "Luận văn"
  click_link "Đăng kí đề tài"
end

When(/^Tôi nhập thông tin đề tài là "(.*?)"$/) do |title|
  fill_in "thesisName", :with => title
end

When(/^Tôi chọn giảng viên là "(.*?)"$/) do |lecturer|
  select lecturer, from: "lecturerId"
end

When(/^Tôi upload file "(.*?)"$/) do |file_name|
  file = File.dirname(__FILE__) + "/data/" << "file_upload/" << file_name
  attach_file("file", 
    File.expand_path("features/step_difinitions/data/file_upload/" + file_name))
  click_button "Ghi nhận"
end

Then(/^Tôi sẽ nhìn thấy thông báo "(.*?)"$/) do |message|
  #should have_content message
end

Then(/^Trạng thái của tôi sẽ là "(.*?)"$/) do |trang_thai|
  click_link @account["Tên"]
  click_link "Thông tin học viên"
  should have_content trang_thai
end

Then(/^Trạng thái của tôi sẽ không thay đổi$/) do
  click_link @account["Tên"]
  click_link "Thông tin học viên"
  should have_content @account["Trạng thái"]
end

Then(/^Tôi sẽ không thể nhập thông tin đề tài$/) do
  page.should have_field "lecturerId", :disabled => true
end
