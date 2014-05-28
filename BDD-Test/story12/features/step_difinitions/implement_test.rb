Given(/^Tôi khởi tạo dữ liệu$/) do
  initialize_database
end

Given(/^Tôi đã đăng nhập với người dùng cán bộ khoa$/) do
  @account = DATA["Người dùng"]["cán bộ khoa"]
  visit "/login"
  fill_in "email", :with => @account["Email"]
  fill_in "password", :with => @account["Password"]
  click_button "Đăng nhập"
end

Given(/^Tôi đang ở phần xếp học viên vào tiểu ban seminar$/) do
  click_link "Seminar"
  click_link "Xếp học viên vào tiểu ban"
end

When(/^Tôi xếp cho các học viên đã đăng kí vào tiểu ban "(.*?)"$/) do |tieu_ban|
	@tieu_ban = tieu_ban
	select tieu_ban, from: "select_ssid"
  @postgraduates =  DATA["Học viên đã đăng kí"]
  @postgraduates.each do |_, postgraduate|
  	click_button "button_#{postgraduate["id"]}"
  end
end

Then(/^Các học viên đó sẽ được chuyển sang tiểu ban mới thành công$/) do
	click_link "Seminar"
  click_link "Xếp học viên vào tiểu ban"
  @postgraduates.each do |_, postgraduate|
  	find("#ss_name_#{postgraduate["id"]}").text.should have_content @tieu_ban
  end
end

Then(/^Tôi không thể xếp cho các học viên chưa đăng kí vào tiểu ban$/) do
  @postgraduates =  DATA["Học viên chưa đăng kí"]
  @postgraduates.each do |_, postgraduate|
  	should have_no_button "button_#{postgraduate["id"]}"
  end
end
