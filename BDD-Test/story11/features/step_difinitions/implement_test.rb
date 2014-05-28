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

Given(/^Tôi đang ở phần mở tiểu ban seminar$/) do
  click_link "Seminar"
  click_link "Mở tiểu ban seminar"
end

When(/^Tôi nhập các thông tin cho tiểu ban semina$/) do
  @thong_tin = DATA["Nhập tiểu ban"]["bản ghi thành công"]
  fill_in "name", with: @thong_tin["Tên tiểu ban"]
  fill_in "place", with: @thong_tin["Địa điểm"]
  fill_in "held_date", with: @thong_tin["Ngày diễn ra"]
  fill_in "starting_time", with: @thong_tin["Thời gian"]
  select @thong_tin["Chọn seminar"], from: "seminar_id"
  select @thong_tin["Chủ tịch"], from: "president_id"
  select @thong_tin["Phó chủ tịch"], from: "vice_president_id"
  select @thong_tin["Thư kí "], from: "secretary_id"
  @thong_tin["Member_ids"].split(",").each do |member_id|
    check "check_#{member_id}"
  end unless @thong_tin["Member_ids"].empty?
end

When(/^Tôi ghi nhận$/) do
  click_button "Mở tiểu ban"
end

Then(/^Semina được mở thành công và tôi nhìn thấy thông tin semina tôi vừa mở$/) do
  should have_content @thong_tin["Tên tiểu ban"]
  # should have_content @thong_tin["Địa điểm"]
  # should have_content @thong_tin["Ngày diễn ra"]
  should have_content @thong_tin["Thời gian"]
  should have_content @thong_tin["Chọn seminar"]
  should have_content @thong_tin["Chủ tịch"]
  should have_content @thong_tin["Phó chủ tịch"]
  should have_content @thong_tin["Thư kí "]
  @thong_tin["Thành viên"].split(",").each do |member|
    should have_content member
  end unless @thong_tin["Thành viên"].empty?
end

When(/^Tôi nhập các thông tin cho semina "(.*?)"$/) do |thong_tin|
  @thong_tin = DATA["Nhập tiểu ban"][thong_tin]
  fill_in "name", with: @thong_tin["Tên tiểu ban"]
  fill_in "place", with: @thong_tin["Địa điểm"]
  fill_in "held_date", with: @thong_tin["Ngày diễn ra"]
  fill_in "starting_time", with: @thong_tin["Thời gian"]
  select @thong_tin["Chọn seminar"], from: "seminar_id"
  select @thong_tin["Chủ tịch"], from: "president_id"
  select @thong_tin["Phó chủ tịch"], from: "vice_president_id"
  select @thong_tin["Thư kí "], from: "secretary_id"
  @thong_tin["Member_ids"].split(",").each do |member_id|
    check "check_#{member_id}"
  end unless @thong_tin["Thành viên"].empty?
end

Then(/^Tiểu ban seminar mở không thành công và tôi nhìn thấy thông báo lỗi trên màn hình$/) do
  should have_css(".alert-error")
end
