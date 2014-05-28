Given(/^Tôi đã đăng nhập với tài khoản "(.*?)"$/) do |account|
  @account_logined = DATA["Người dùng"][account.to_s]
  visit "/login"
  fill_in "email", :with => @account_logined["email"]
  fill_in "password", :with => @account_logined["password"]
  click_button "Đăng nhập"
end

Given(/^Tôi chọn xem danh sách giảng viên$/) do
  click_link "Danh sách giảng viên"
end

When(/^Tôi nhập thông tin tìm kiếm như sau "(.*?)"$/) do |keyword|
  @result = Array.new
  DATA["Danh sách giảng viên"].each do |_,lecturer|
    if check_in([lecturer["Họ tên"], lecturer["Học vị"], lecturer["Email"],
      lecturer["Điện thoại"], lecturer["Địa chỉ"]], keyword)
      @result << lecturer
    end
  end
  fill_in "keyword", with: keyword
  click_button "Tìm kiếm"
end



Then(/^Tôi sẽ nhìn thấy danh sách giảng viên với đầy đủ các thông tin như yêu cầu$/) do\
  @result.each do |lecturer|
    should have_content lecturer["Họ tên"]
    should have_content lecturer["Học vị"]
    should have_content lecturer["Bộ môn"]
    should have_content lecturer["Email"]    
    should have_content lecturer["Điện thoại"]
    should have_content lecturer["Địa chỉ"]
  end

  should have_css "table#lecturer_list tr", count: @result.size + 1  
end
