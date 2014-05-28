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

Given(/^Tôi đang ở phần Mở semina$/) do
  click_link "Seminar"
  click_link "Mở seminar"
end

When(/^Tôi nhập các thông tin cho semina như sau "(.*?)", "(.*?)", "(.*?)", "(.*?)", "(.*?)"$/) do |tieu_de, mo_ta, du_kien, bat_dau_dk, ket_thuc_dk|  
  @seminar = Hash.new
  @seminar["tieu_de"] = tieu_de
  @seminar["mo_ta"] = mo_ta
  @seminar["du_kien"] = (Date.today + du_kien.split("+")[1].to_i).to_s unless du_kien.empty?
  @seminar["bat_dau_dk"] = (Date.today + bat_dau_dk.split("+")[1].to_i).to_s unless bat_dau_dk.empty?
  @seminar["ket_thuc_dk"] = (Date.today + ket_thuc_dk.split("+")[1].to_i).to_s unless ket_thuc_dk.empty?

  fill_in "title", :with => @seminar["tieu_de"]
  fill_in "des", :with =>  @seminar["mo_ta"]
  fill_in "scheduled_date", :with => @seminar["du_kien"]
  fill_in "start_reg_date", :with => @seminar["bat_dau_dk"]
  fill_in "due_reg_date", :with =>  @seminar["ket_thuc_dk"]
end

When(/^Tôi ghi nhận$/) do
  click_button "Mở seminar"
end

Then(/^Semina được mở thành công và tôi nhìn thấy thông tin semina tôi vừa mở$/) do
  should have_content "Tạo seminar thành công"
  should have_css(".alert-success")
  should have_content @seminar["tieu_de"]
  #should have_content @seminar["mo_ta"]
end

Then(/^Semina được mở không thành công và tôi nhìn thấy thông báo lỗi trên màn hình$/) do
  should have_css(".alert-error")
  should have_content "Mở seminar"
  should have_button "Mở seminar"
end
