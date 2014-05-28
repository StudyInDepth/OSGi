def mix_string str
  for i in 0..(str.size - 1)
    str[i] = (rand() * 2).to_i == 1 ? str[i].upcase : str[i].downcase
  end
  str
end
def check_in list, key
  check = false
  list.each do |value|
    if convert(value).downcase.include? convert(key).downcase
      check = true
    end
  end
  check
end
def convert s
  temp = s.dup
  list = {"a"=>"áàảãạăắằẳẵặâấầẩẫậ", "e"=>"éèẻẽẹêếềểễệ", "i" => "íìỉĩị",
    "d" => "đ",
    "o" => "óòỏõọôốồổỗộơớờởỡợ", "u" => "úùủũụưứừửữự", "y" => "ýỳỷỹỵ",
    "A" => "ÁÀẢÃẠĂẮẰẲẴẶÂẦẤẨẪẬ", "E" => "ÉÈẺẼẸÊẾỀỂỄỆ", "I" => "ÍÌỈĨỊ",
    "D" => "Đ",
    "O" => "ÓÒỎÕỌỐỒỔỖỘƠỜỚỞỠỢ", "U" => "ÚÙỦŨỤỨỪỬỮỰ", "Y" => "ÝỲỶỸỴ"}
  list.each do |key, value|
    value.each_char do |char|
      temp.gsub!(char, key)
    end
  end
  temp
end