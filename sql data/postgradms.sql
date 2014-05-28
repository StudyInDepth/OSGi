/*
Navicat MySQL Data Transfer

Source Server         : postgradms
Source Server Version : 50615
Source Host           : localhost:3306
Source Database       : postgradms

Target Server Type    : MYSQL
Target Server Version : 50615
File Encoding         : 65001

Date: 2014-05-29 00:10:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for department_staff
-- ----------------------------
DROP TABLE IF EXISTS `department_staff`;
CREATE TABLE `department_staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `major_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `U_DPRTTFF_USER_ID` (`user_id`),
  KEY `I_DPRTTFF_MAJOR` (`major_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of department_staff
-- ----------------------------
INSERT INTO `department_staff` VALUES ('1', '34', '1');
INSERT INTO `department_staff` VALUES ('2', '35', '2');
INSERT INTO `department_staff` VALUES ('3', '37', '3');
INSERT INTO `department_staff` VALUES ('4', '36', '4');

-- ----------------------------
-- Table structure for lecturers
-- ----------------------------
DROP TABLE IF EXISTS `lecturers`;
CREATE TABLE `lecturers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `degree` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone_number` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `major_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `U_LCTURRS_USER_ID` (`user_id`),
  KEY `I_LCTURRS_MAJOR` (`major_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of lecturers
-- ----------------------------
INSERT INTO `lecturers` VALUES ('1', 'ĐHCN', 'TS', 'hoann@vnu.edu.vn', 'Nguyễn Ngọc Hóa', '01234561238', '1', '2');
INSERT INTO `lecturers` VALUES ('2', 'ĐHCN', 'ThS', 'hanhdp@vnu.edu.vn', 'Dư Phương Hạnh', '12356552124', '2', '2');
INSERT INTO `lecturers` VALUES ('3', 'ĐHCN', 'ThS', 'hailp@vnu.edu.vn', 'Lê Hồng Hải', '12548588553', '3', '2');
INSERT INTO `lecturers` VALUES ('4', 'ĐHCN', 'PGS.TS', 'chaunh@vnu.edu.vn', 'Nguyễn Hải Châu', '12365555552', '4', '2');
INSERT INTO `lecturers` VALUES ('5', 'ĐHCN', 'TS', 'minhdl@vnu.edu.vn', 'Dương Lê Minh', '12333233335', '9', '4');
INSERT INTO `lecturers` VALUES ('6', 'ĐHCN', 'ThS', 'thanhld@vnu.edu.vn', 'Lê Đình Thanh', '25122222224', '10', '4');
INSERT INTO `lecturers` VALUES ('7', 'ĐHCN', 'TS', 'maitt@vnu.edu.vn', 'Trần Trúc Mai', '12332302210', '11', '4');
INSERT INTO `lecturers` VALUES ('8', 'ĐHCN', 'TS', 'thond@vnu.edu.vn', 'Nguyễn Đại Thọ', '01233322333', '12', '4');
INSERT INTO `lecturers` VALUES ('9', 'ĐHCN', 'TS', 'vinhls@vnu.edu.vn', 'Lê Sỹ Vinh', '12121212122', '17', '3');
INSERT INTO `lecturers` VALUES ('10', 'ĐHCN', 'TS', 'hieuls@vnu.edu.vn', 'Lê Quang Hiếu', '12212122224', '18', '3');
INSERT INTO `lecturers` VALUES ('11', 'ĐHCN', 'TS', 'khoiln@vnu.edu.vn', 'Lê Nguyên Khôi', '01220220220', '19', '3');
INSERT INTO `lecturers` VALUES ('12', 'ĐHCN', 'PGS.TS', 'cuongla@vnu.edu.vn', 'Lê Anh Cường', '12121212121', '20', '3');
INSERT INTO `lecturers` VALUES ('13', 'ĐHCN', 'TS', 'hungpn@vnu.edu.vn', 'Phạm Ngọc Hùng', '12121212133', '25', '1');
INSERT INTO `lecturers` VALUES ('14', 'ĐHCN', 'TS', 'chauttm@vnu.edu.vn', 'Trần Thị Minh Châu', '23928329382', '26', '1');
INSERT INTO `lecturers` VALUES ('15', 'ĐHCN', 'TS', 'chaunth@vnu.edu.vn', ' Nguyễn Thị Huyền Châu', '2323232323', '27', '1');
INSERT INTO `lecturers` VALUES ('16', 'ĐHCN', 'TS', ' hieuvd@vnu.edu.vn', ' Võ Đình Hiếu', '2515454552', '28', '1');

-- ----------------------------
-- Table structure for majors
-- ----------------------------
DROP TABLE IF EXISTS `majors`;
CREATE TABLE `majors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of majors
-- ----------------------------
INSERT INTO `majors` VALUES ('1', 'Công nghệ phần mềm');
INSERT INTO `majors` VALUES ('2', 'Hệ thống thống thông tin');
INSERT INTO `majors` VALUES ('3', 'Khoa học máy tính');
INSERT INTO `majors` VALUES ('4', 'Mang và truyền thông');

-- ----------------------------
-- Table structure for postgraduates
-- ----------------------------
DROP TABLE IF EXISTS `postgraduates`;
CREATE TABLE `postgraduates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date_of_birth` date NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone_number` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `seminar_subcommittee_id` int(11) DEFAULT NULL,
  `lecturer_id` int(11) DEFAULT NULL,
  `major_id` int(11) DEFAULT NULL,
  `seminar_id` int(11) DEFAULT NULL,
  `thesis_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `U_PSTGDTS_USER_ID` (`user_id`),
  KEY `I_PSTGDTS_LECTURER` (`lecturer_id`),
  KEY `I_PSTGDTS_MAJOR` (`major_id`),
  KEY `I_PSTGDTS_SEMINAR` (`seminar_id`),
  KEY `I_PSTGDTS_SEMINARSUBCOMMITTEE` (`seminar_subcommittee_id`),
  KEY `I_PSTGDTS_THESIS` (`thesis_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of postgraduates
-- ----------------------------
INSERT INTO `postgraduates` VALUES ('1', 'Thanh Hóa', '1992-05-28', 'dieutth@vnu.edu.vn', 'Trần Thị Hồng Diệu', '12154587845', '5', '16', null, '1', '2', null, '1');
INSERT INTO `postgraduates` VALUES ('2', 'Hà Nội', '1992-05-13', 'hanhdh@vnu.edu.vn', 'Dương Hồng Hạnh', '12365778878', '6', '16', null, '2', '2', null, '2');
INSERT INTO `postgraduates` VALUES ('3', 'Nghệ An', '1992-05-29', 'vuqt@vnu.edu.vn', 'Quan Tuấn Vũ', '12356499799', '7', '16', null, '3', '2', null, '3');
INSERT INTO `postgraduates` VALUES ('4', 'Hà Nội', '1992-05-13', 'nguyenbnt@vnu.edu.vn', 'Bùi Ngọc Thực Nguyên', '12122222222', '8', '16', null, '4', '2', null, '4');
INSERT INTO `postgraduates` VALUES ('5', 'Thái Bình', '1992-05-19', 'anhmv@vnu.edu.vn', 'Mai Việt Anh', '12333323212', '13', '16', null, '5', '4', null, '5');
INSERT INTO `postgraduates` VALUES ('6', 'Bắc Giang', '1992-04-13', 'hankh@vnu.edu.vn', 'Nguyễn Khắc Hoàng Hà', '12102002224', '14', '16', null, '6', '4', null, '6');
INSERT INTO `postgraduates` VALUES ('7', 'Hải Dương', '1992-05-20', 'hails@vnu.edu.vn', 'Lê Sơn Hải', '20210220224', '15', '16', null, '7', '4', null, '7');
INSERT INTO `postgraduates` VALUES ('8', 'Hải Dương', '1992-05-26', 'hiepnv@vnu.edu.vn', 'Nguyễn Văn Hiệp', '01212011024', '16', '16', null, '8', '4', null, '8');
INSERT INTO `postgraduates` VALUES ('9', 'Hà Nội', '1992-06-13', 'quanth@vnu.edu.vn', 'Trần Hùng Quân', '12121212124', '21', '16', null, '9', '3', null, '9');
INSERT INTO `postgraduates` VALUES ('10', 'Hải Phòng', '1992-05-28', 'thaidt@vnu.edu.vn', 'Đặng Trần Thái', '12233555555', '22', '16', null, '10', '3', null, '10');
INSERT INTO `postgraduates` VALUES ('11', 'Hà Nội', '1991-05-26', 'thanhnd@vnu.edu.vn', 'Nguyễn Đại Thành', '67890932323', '23', '16', null, '11', '3', null, '11');
INSERT INTO `postgraduates` VALUES ('12', 'Hà Nam', '1992-03-03', 'thinhnk@vnu.edu.vn', 'Nguyễn Khánh Thịnh', '55454545555', '24', '16', null, '12', '3', null, '12');
INSERT INTO `postgraduates` VALUES ('13', 'Thanh Hóa', '1992-05-19', 'trinhlk@vnu.edu.vn', 'Lê Khánh Trình', '2323232323', '29', '16', null, '13', '1', null, '13');
INSERT INTO `postgraduates` VALUES ('14', 'Hà Nội', '1992-05-27', 'thoainn@vnu.edu.vn', 'Nguyễn Ngọc Thoại', '1212121211', '30', '16', null, null, '1', null, '14');
INSERT INTO `postgraduates` VALUES ('15', 'Hải Phòng', '1991-03-03', 'toandv@vnu.edu.vn', 'Đỗ Văn Toàn', '1554515152', '31', '16', null, '14', '1', null, '15');
INSERT INTO `postgraduates` VALUES ('16', 'Nam Định', '1992-05-08', 'quynt@vnu.edu.vn', 'Nguyễn Thị Quý', '2121212112', '32', '16', null, '15', '1', null, '16');
INSERT INTO `postgraduates` VALUES ('17', 'Nam Định', '1992-07-18', 'taibd@vnu.edu.vn', 'Bùi Đức Tài', '1212121216', '33', '16', null, '16', '1', null, '17');

-- ----------------------------
-- Table structure for seminars
-- ----------------------------
DROP TABLE IF EXISTS `seminars`;
CREATE TABLE `seminars` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `due_reg_date` date DEFAULT NULL,
  `scheduled_date` date DEFAULT NULL,
  `start_reg_date` date DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of seminars
-- ----------------------------
INSERT INTO `seminars` VALUES ('1', 'seminar cho khoa 16', '2014-05-30', '2014-05-31', '2014-05-23', 'Seminar K16');

-- ----------------------------
-- Table structure for seminar_subcommittees
-- ----------------------------
DROP TABLE IF EXISTS `seminar_subcommittees`;
CREATE TABLE `seminar_subcommittees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `held_date` date DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `place` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `staring_time` time DEFAULT NULL,
  `president_id` int(11) DEFAULT NULL,
  `secretary_id` int(11) DEFAULT NULL,
  `seminar_id` int(11) DEFAULT NULL,
  `vice_president_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `I_SMNRTTS_PRESIDENT` (`president_id`),
  KEY `I_SMNRTTS_SECRETARY` (`secretary_id`),
  KEY `I_SMNRTTS_SEMINAR` (`seminar_id`),
  KEY `I_SMNRTTS_VICEPRESIDENT` (`vice_president_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of seminar_subcommittees
-- ----------------------------

-- ----------------------------
-- Table structure for seminar_subcommittees_lecturers
-- ----------------------------
DROP TABLE IF EXISTS `seminar_subcommittees_lecturers`;
CREATE TABLE `seminar_subcommittees_lecturers` (
  `ssc_id` int(11) DEFAULT NULL,
  `lid` int(11) DEFAULT NULL,
  KEY `I_SMNRRRS_ELEMENT` (`lid`),
  KEY `I_SMNRRRS_SSC_ID` (`ssc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of seminar_subcommittees_lecturers
-- ----------------------------

-- ----------------------------
-- Table structure for theses
-- ----------------------------
DROP TABLE IF EXISTS `theses`;
CREATE TABLE `theses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `file_uploaded` tinyint(1) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `state` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of theses
-- ----------------------------
INSERT INTO `theses` VALUES ('1', '', '1', 'Phát triển công cụ biên tập bản đồ trên nền Web', 'Chuẩn bị đề tài');
INSERT INTO `theses` VALUES ('2', '', '1', 'Xây dựng ứng dụng theo dõi thông tin trên Internet', 'Chuẩn bị đề tài');
INSERT INTO `theses` VALUES ('3', '', '1', 'Phát triển ứng dụng quản lý người dùng dịch vụ xe bus trên nền Android', 'Chuẩn bị đề tài');
INSERT INTO `theses` VALUES ('4', 'Đề tài này chuẩn bị khá tốt', '1', 'Phát triển ứng dụng chơi nhạc trên Android qua thiết bị Airport Express', 'Đang thực hiện');
INSERT INTO `theses` VALUES ('5', '', '1', 'Phát triển cơ chế xác thực theo danh sách điều khiển truy cập cho WebDAN', 'Chuẩn bị đề tài');
INSERT INTO `theses` VALUES ('6', '', '1', 'Định vị trong mạng cảm biến không dây', 'Chuẩn bị đề tài');
INSERT INTO `theses` VALUES ('7', '', '1', 'Tích hợp chữ ký số cho phần mềm Quản lý văn bản', 'Chuẩn bị đề tài');
INSERT INTO `theses` VALUES ('8', '', '1', 'Xây dựng giải pháp tổng đài hiệu quả cho doanh nghiệp', 'Chuẩn bị đề tài');
INSERT INTO `theses` VALUES ('9', '', '1', 'Question Anlysis for a Vietnamese Community-Based Question Answering system', 'Chuẩn bị đề tài');
INSERT INTO `theses` VALUES ('10', '', '1', 'An investigation of word segmentation and POS tagging in subjective detection', 'Chuẩn bị đề tài');
INSERT INTO `theses` VALUES ('11', '', '1', 'A study on the construction of an individual\'s whole genome', 'Chuẩn bị đề tài');
INSERT INTO `theses` VALUES ('12', '', '1', 'Vietnamese reference genome construction', 'Chuẩn bị đề tài');
INSERT INTO `theses` VALUES ('13', '', '1', 'Phương pháp và công cụ kiểm thử tự động cho các ứng dụng web', 'Chuẩn bị đề tài');
INSERT INTO `theses` VALUES ('14', '', null, '', 'Chưa bắt đầu');
INSERT INTO `theses` VALUES ('15', 'de cuong hop le', '1', 'Phát triển phần mềm hướng thành phần sử dụng OSGi', 'Chuẩn bị đề tài');
INSERT INTO `theses` VALUES ('16', '', '1', 'Một phương pháp phân tích yêu cầu bộ xử lý mới cho hệ thời gian thực với deferred preemtion', 'Chuẩn bị đề tài');
INSERT INTO `theses` VALUES ('17', 'Đề tài tốt', '1', 'Xây dựng công cụ\r\n thu thập và phân tích thông tin trên Web 2.0', 'Chuẩn bị đề tài');

-- ----------------------------
-- Table structure for theses_lecturers
-- ----------------------------
DROP TABLE IF EXISTS `theses_lecturers`;
CREATE TABLE `theses_lecturers` (
  `thesis_id` int(11) DEFAULT NULL,
  `lecturer_id` int(11) DEFAULT NULL,
  KEY `I_THSSRRS_ELEMENT` (`lecturer_id`),
  KEY `I_THSSRRS_THESIS_ID` (`thesis_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of theses_lecturers
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `U_USERS_EMAIL` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'hoann@vnu.edu.vn', '123', 'lecturer');
INSERT INTO `users` VALUES ('2', 'hanhdp@vnu.edu.vn', '123', 'lecturer');
INSERT INTO `users` VALUES ('3', 'hailp@vnu.edu.vn', '123', 'lecturer');
INSERT INTO `users` VALUES ('4', 'chaunh@vnu.edu.vn', '123', 'lecturer');
INSERT INTO `users` VALUES ('5', 'dieutth@vnu.edu.vn', '123', 'postgraduate');
INSERT INTO `users` VALUES ('6', 'hanhdh@vnu.edu.vn', '123', 'postgraduate');
INSERT INTO `users` VALUES ('7', 'vuqt@vnu.edu.vn', '123', 'postgraduate');
INSERT INTO `users` VALUES ('8', 'nguyenbnt@vnu.edu.vn', '123', 'postgraduate');
INSERT INTO `users` VALUES ('9', 'minhdl@vnu.edu.vn', '123', 'lecturer');
INSERT INTO `users` VALUES ('10', 'thanhld@vnu.edu.vn', '123', 'lecturer');
INSERT INTO `users` VALUES ('11', 'maitt@vnu.edu.vn', '123', 'lecturer');
INSERT INTO `users` VALUES ('12', 'thond@vnu.edu.vn', '123', 'lecturer');
INSERT INTO `users` VALUES ('13', 'anhmv@vnu.edu.vn', '123', 'postgraduate');
INSERT INTO `users` VALUES ('14', 'hankh@vnu.edu.vn', '123', 'postgraduate');
INSERT INTO `users` VALUES ('15', 'hails@vnu.edu.vn', '123', 'postgraduate');
INSERT INTO `users` VALUES ('16', 'hiepnv@vnu.edu.vn', '123', 'postgraduate');
INSERT INTO `users` VALUES ('17', 'vinhls@vnu.edu.vn', '123', 'lecturer');
INSERT INTO `users` VALUES ('18', 'hieuls@vnu.edu.vn', '123', 'lecturer');
INSERT INTO `users` VALUES ('19', 'khoiln@vnu.edu.vn', '123', 'lecturer');
INSERT INTO `users` VALUES ('20', 'cuongla@vnu.edu.vn', '123', 'lecturer');
INSERT INTO `users` VALUES ('21', 'quanth@vnu.edu.vn', '123', 'postgraduate');
INSERT INTO `users` VALUES ('22', 'thaidt@vnu.edu.vn', '123', 'postgraduate');
INSERT INTO `users` VALUES ('23', 'thanhnd@vnu.edu.vn', '123', 'postgraduate');
INSERT INTO `users` VALUES ('24', 'thinhnk@vnu.edu.vn', '123', 'postgraduate');
INSERT INTO `users` VALUES ('25', 'hungpn@vnu.edu.vn', '123', 'lecturer');
INSERT INTO `users` VALUES ('26', 'chauttm@vnu.edu.vn', '123', 'lecturer');
INSERT INTO `users` VALUES ('27', 'chaunth@vnu.edu.vn', '123', 'lecturer');
INSERT INTO `users` VALUES ('28', 'hieuvd@vnu.edu.vn', '123', 'lecturer');
INSERT INTO `users` VALUES ('29', 'trinhlk@vnu.edu.vn', '123', 'postgraduate');
INSERT INTO `users` VALUES ('30', 'thoainn@vnu.edu.vn', '123', 'postgraduate');
INSERT INTO `users` VALUES ('31', 'toandv@vnu.edu.vn', '123', 'postgraduate');
INSERT INTO `users` VALUES ('32', 'quynt@vnu.edu.vn', '123', 'postgraduate');
INSERT INTO `users` VALUES ('33', 'taibd@vnu.edu.vn', '123', 'postgraduate');
INSERT INTO `users` VALUES ('34', 'cnpm@vnu.edu.vn', '123', 'dept');
INSERT INTO `users` VALUES ('35', 'httt@vnu.edu.vn', '123', 'dept');
INSERT INTO `users` VALUES ('36', 'mmt@vnu.edu.vn', '123', 'dept');
INSERT INTO `users` VALUES ('37', 'khmt@vnu.edu.vn', '123', 'dept');
INSERT INTO `users` VALUES ('38', 'cntt@vnu.edu.vn', '123', 'faculty');
