require 'spreadsheet'
require 'mysql'

CONNECT = Mysql.new('localhost', 'osgi', '123', 'postgradms')

@file_sql = File.open "#{File.dirname(__FILE__)}/data.sql", 'w'
@db_init = Spreadsheet.open "#{File.dirname(__FILE__)}/db_init.xls"
@db_init.worksheets.each do |sheet|
  name = sheet.name
  name_column = sheet.row(0).join(",")  
  sql = "INSERT INTO #{name} (#{name_column}) VALUES "
  sheet.each 1 do |row|
    next if row[0].to_s.empty?
    value = row.map{|cell|
      cell.eql?("null") ? "''" : "\'#{cell.to_s}\'"
    }.join(",")
    sql = sql + "(#{value}),"
  end
  sql[sql.size - 1] = ";\n"
  @file_sql.write "DELETE FROM #{name};\n"
  @file_sql.write sql
  CONNECT.query "DELETE FROM #{name};"
  CONNECT.query(sql)
end
@file_sql.close

@db_test = Spreadsheet.open "#{File.dirname(__FILE__)}/db_test.xls"
data = Hash.new
@db_test.worksheets.each do |sheet|
  name = sheet.name
  name_column = sheet.first
  result_table = Hash.new
  sheet.each 1 do |row|
    next if row[0].nil?
    j = 0
    hash = Hash.new
    row.each do |cell|
      hash[name_column[j]] = cell.to_s
      j = j + 1
    end
    result_table[row[0]] = hash
  end
  data[name]  = result_table
end
DATA = data

DATA["Seminar"].each do |_, seminar_info|
  seminar_info.each do |key, value|
    if key.include?("date")
      seminar_info[key] =  (Date.today + value.split("+")[1].to_i).to_s unless value.empty?
    end
  end
end

def initialize_database
  File.open("#{File.dirname(__FILE__)}/data.sql", 'r').each_line do |line|
    CONNECT.query line
  end
end

def update_newest_seminar(type)
  puts DATA["Seminar"]
  info = DATA["Seminar"][type]

  sql = "Update seminars set due_reg_date = '#{info["due_reg_date"]}', scheduled_date = '#{info["scheduled_date"]}',
        start_reg_date = '#{info["start_reg_date"]}' where id= #{info["id"]}"
  CONNECT.query sql
end