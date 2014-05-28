Given(/^Tôi đã đăng nhập với người dùng bộ môn thuộc bộ môn "(.*?)"$/) do |bo_mon|  
  @account = DATA["Người dùng bộ môn"][bo_mon.to_s]
  visit "/login"
  fill_in "email", :with => @account["Email"]
  fill_in "password", :with => @account["Password"]
  click_button "Đăng nhập"
end

Given(/^Tôi đang ở phần "(.*?)"$/) do |link|
  click_link link
end

When(/^Tôi chọn nhận xét cho học viên thuộc khóa "(.*?)"$/) do |khoa|
  khoa = "Chọn khóa" if khoa.eql?("Tất cả")
  select khoa.to_s, from: "chon_khoa"
  click_button "Tìm kiếm"
end

When(/^Tôi nhận xét đề tài cho một học viên ở trạng thái "(.*?)" thuộc chuyên ngành của tôi quản lý$/) do |trang_thai|
  @postgraduate
  DATA["Danh sách học viên"].each do |_, postgraduate|
    if postgraduate["Trạng thái"].eql?(trang_thai)
      @postgraduate = postgraduate
      break
    end
  end
end

Then(/^Nhận xét của tôi cho học viên đó sẽ được cập nhật$/) do
  pending # express the regexp above with the code you wish you had
end

Given(/^Tôi đã đăng nhập với người dùng "(.*?)" thuộc chuyên ngành "(.*?)"$/) do |arg1, arg2|
  pending # express the regexp above with the code you wish you had
end

When(/^Tôi nhận xét đề tài cho một học viên ở trạng thái "(.*?)" thuộc chuyên ngành của tôi$/) do |arg1|
  pending # express the regexp above with the code you wish you had
end

Then(/^Tôi không thể nhận xét đề tài cho một học viên không ở trạng thái "(.*?)" hoặc khác chuyên ngành tôi quản lý$/) do |arg1|
  pending # express the regexp above with the code you wish you had
end
