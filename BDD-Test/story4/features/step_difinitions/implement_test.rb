Given(/^Tôi đã đăng nhập với người dùng "(.*?)"$/) do |account|
  @account = DATA["Người dùng"][account.to_s]
  visit "/login"
  fill_in "email", :with => @account["email"]
  fill_in "password", :with => @account["password"]
  click_button "Đăng nhập"
end

Given(/^Tôi đang ở phần xem danh sách học viên$/) do
  click_link "Học viên"
  click_link "Danh sách học viên"
  click_button "Tìm kiếm"
end

When(/^Tôi muốn xem thông tin chi tiết của học viên "(.*?)"$/) do |link|
  @result
  DATA["Thông tin chi tiết học viên"].each do |_, postgraduate|
    if postgraduate["Họ tên"].eql?(link)
      @result = postgraduate
      break
    end
  end
  click_link link
end

Then(/^Tôi sẽ nhìn thấy thông tin chi tiết của học viên đó$/) do
  title = ["Họ tên",  "Khóa học",  "Điện thoại",  "Email",
    "Địa chỉ", "Tên đề tài",  "Trạng thái đề tài", "Tên giảng viên",
    "Học vị giảng viên", "Email giảng viên",  "Số điện thoại giảng viên",
    "Ngành học", "Địa chỉ giảng viên"]
  title.each do |title_column|
    should have_content @result[title_column]
  end
end

Then(/^Tôi sẽ không thể xem được thông tin chi tiết của các học viên$/) do
  DATA["Thông tin chi tiết học viên"].each do |_, postgraduate|
    #should have_no_link postgraduate["Họ tên"]
  end
end
