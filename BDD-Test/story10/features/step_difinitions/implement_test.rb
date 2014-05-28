Given(/^Tôi đã đăng nhập với người dùng "(.*?)"$/) do |user|  
  @account = DATA["Người dùng"][user.to_s]
  visit "/login"
  fill_in "email", :with => @account["Email"]
  fill_in "password", :with => @account["Password"]
  click_button "Đăng nhập"
end

When(/^Tôi vào phần xem semina$/) do
  click_link "Seminar"
	click_link "Xem seminar"
end

Then(/^Tôi nhìn thấy thông tin của seminar gần nhất$/) do
  seminar = DATA["Seminar mới nhất"].first[1].each do |key, info|
  	if key.include?("Ngày")
  		d = Date.parse(info)
      day   = d.mday < 10 ? "0#{d.mday}" : "#{d.mday}"
      month = d.month < 10 ? "0#{d.month}" : "#{d.month}"
      info  = "#{day}-#{month}-#{d.year}"
  	end
    next if key.include?("Mô tả")
  	should have_content info
  end
  sleep 10
end
