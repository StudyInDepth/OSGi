Given(/^Tôi đã đăng nhập với tài khoản "(.*?)"$/) do |account|
  @account = DATA["Người dùng"][account.to_s]
  visit "/login"
  fill_in "email", :with => @account["email"]
  fill_in "password", :with => @account["password"]
  click_button "Đăng nhập"
end

Given(/^Tôi chọn xem danh sách học viên$/) do
  click_link "Học viên"
  click_link "Danh sách học viên"
end

When(/^Tôi nhập thông tin tìm kiếm như sau "(.*?)" chọn khóa "(.*?)"$/) do |keyword, khoa|
  @result = Array.new
  DATA["Danh sách học viên"].each do |_,postgraduate|
    if check_in([postgraduate["Họ tên"], postgraduate["Đề tài"], postgraduate["Khóa"],
      postgraduate["Trạng thái"], postgraduate["Giảng viên hướng dẫn"]], keyword)
      if khoa.to_s == "Tất cả" || postgraduate["Khóa"].to_i == khoa.to_i
        @result << postgraduate
      end
    end
  end
  khoa = "Chọn khóa" if khoa.eql?("Tất cả")
  fill_in "keyword", with: keyword
  select khoa.to_s, from: "chon_khoa"
  click_button "Tìm kiếm"
end

Then(/^Tôi sẽ nhìn thấy danh sách học viên của khóa này với đầy đủ các thông tin như yêu cầu$/) do
  @result.each do |postgraduate|
    should have_content postgraduate["họ tên"]
    should have_content postgraduate["đề tài"]
    should have_content postgraduate["khóa học"]
    should have_content postgraduate["trạng thái"]
    should have_content postgraduate["giảng viên"]
  end

  should have_css "table#postgraduate_list tr", count: @result.size + 1
end