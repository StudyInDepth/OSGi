Given(/^Tôi khởi tạo dữ liệu$/) do
  initialize_database
end

Given(/^Tôi đã đăng nhập với người dùng "(.*?)"$/) do |user|  
  @account = DATA["Người dùng"][user.to_s]
  visit "/login"
  fill_in "email", :with => @account["Email"]
  fill_in "password", :with => @account["Password"]
  click_button "Đăng nhập"
end

Given(/^Tôi đang ở phần Nhận đề tài$/) do
  click_link "Học viên"
  click_link "Nhận đề tài"
end

When(/^Tôi xác nhận đề tài cho các học viên ở trạng thái đề tài "(.*?)" của khóa "(.*?)"$/) do |trang_thai, khoa|
  @postgraduates = []
  DATA["Danh sách học viên"].each do |_, postgraduate|
    if khoa.to_s.eql?("Tất cả")
      @postgraduates.push postgraduate if postgraduate["Trạng thái"].eql?(trang_thai)
    elsif postgraduate["Khóa"].to_i == khoa.to_i and postgraduate["Trạng thái"].eql?(trang_thai)
      @postgraduates.push postgraduate
    end
  end

  khoa = "Chọn khóa" if khoa.to_s.eql?("Tất cả")
  select khoa.to_s, from: "chon_khoa"
  click_button "Tìm kiếm"

  @postgraduates.each do |postgraduate|
    should have_css("#check_#{postgraduate["Thesis_id"]}")
    check "check_" + postgraduate["Thesis_id"]
  end
  click_button "Nhận đề tài"
end

Then(/^Trạng thái của các học viên đó sẽ là "(.*?)"$/) do |trang_thai|
  click_link "Học viên"
  click_link "Danh sách học viên"
  click_button "Tìm kiếm"
  @postgraduates.each do |postgraduate|
    find("#state_#{postgraduate["Thesis_id"]}").text.should have_content trang_thai
  end
end

Then(/^Tôi sẽ không thể xác nhận đề tài cho các học viên ở trạng thái đề tài "(.*?)" của khóa "(.*?)"$/) do |trang_thai, khoa|
  @postgraduates = []
  DATA["Danh sách học viên"].each do |_, postgraduate|
    if khoa.to_s.eql?("Tất cả")
      @postgraduates.push postgraduate if postgraduate["Trạng thái"].eql?(trang_thai)
    elsif postgraduate["Khóa"].to_i == khoa.to_i and postgraduate["Trạng thái"].eql?(trang_thai)
      @postgraduates.push postgraduate
    end
  end

  khoa = "Chọn khóa" if khoa.to_s.eql?("Tất cả")
  select khoa.to_s, from: "chon_khoa"
  click_button "Tìm kiếm"

  @postgraduates.each do |postgraduate|
    should have_no_css("#check_#{postgraduate["Thesis_id"]}")
  end
end
