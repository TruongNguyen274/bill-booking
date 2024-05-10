/*
 Navicat Premium Data Transfer

 Source Server         : 103.173.255.86
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : 103.173.255.86:3306
 Source Schema         : dbbillbooking

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 24/02/2023 22:28:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `full_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `verify_email` bit(1) DEFAULT NULL,
  `verify_phone` bit(1) DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  `location_id` bigint DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd4vb66o896tay3yy52oqxr9w0` (`role_id`),
  KEY `FKkw0h873hsajegawpc96lp3hvp` (`location_id`),
  CONSTRAINT `FKd4vb66o896tay3yy52oqxr9w0` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKkw0h873hsajegawpc96lp3hvp` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of account
-- ----------------------------
BEGIN;
INSERT INTO `account` (`id`, `created_on`, `updated_on`, `version`, `address`, `avatar`, `email`, `full_name`, `password`, `phone`, `status`, `username`, `verify_email`, `verify_phone`, `role_id`, `location_id`, `code`) VALUES (1, '2022-01-01 00:00:00', '2022-10-01 22:13:40', 19, '', '/photos/890961a6-1e0d-4296-990b-78502e72a10a.PNG', 'info@billbooking.vn', 'ADMIN', '$2a$10$.EEq7vH/zmfoIbgeL0niCO1cpq1.fGr2v8qSqQUZBD46TNTJG8ihK', '5765751231', b'1', 'admin', b'1', b'1', 1, 1, 'US000001');
INSERT INTO `account` (`id`, `created_on`, `updated_on`, `version`, `address`, `avatar`, `email`, `full_name`, `password`, `phone`, `status`, `username`, `verify_email`, `verify_phone`, `role_id`, `location_id`, `code`) VALUES (2, '2022-01-01 00:00:00', '2022-09-23 00:47:24', 7, '', NULL, 'owner@mail.com', 'OWNER', '$2a$10$.EEq7vH/zmfoIbgeL0niCO1cpq1.fGr2v8qSqQUZBD46TNTJG8ihK', '0998909988', b'1', 'owner', b'1', b'1', 2, 1, 'US000002');
INSERT INTO `account` (`id`, `created_on`, `updated_on`, `version`, `address`, `avatar`, `email`, `full_name`, `password`, `phone`, `status`, `username`, `verify_email`, `verify_phone`, `role_id`, `location_id`, `code`) VALUES (3, '2022-01-01 00:00:00', '2022-09-23 00:47:24', 7, '', NULL, 'user@mail.com', 'USER', '$2a$10$.EEq7vH/zmfoIbgeL0niCO1cpq1.fGr2v8qSqQUZBD46TNTJG8ihK', '0998909981', b'1', 'user', b'1', b'1', 3, 1, 'US000003');
INSERT INTO `account` (`id`, `created_on`, `updated_on`, `version`, `address`, `avatar`, `email`, `full_name`, `password`, `phone`, `status`, `username`, `verify_email`, `verify_phone`, `role_id`, `location_id`, `code`) VALUES (17, '2022-09-30 23:37:56', '2022-10-11 22:38:12', 1, 'Số 419 Trần Khát Chân, P.Thanh Nhàn, Q.Nam Từ Liêm, Hà Nội', NULL, 'owner1@gmail.com', 'Avatar - 419 Trần Khát Chân', '$2a$10$4vm/6dDCHyOImwVX621NaeJM1vpzeIwcR/QDUdJNG3kJz2kcmFcjq', '0968668844', b'1', 'owner1@gmail.com', b'0', b'0', 2, 1, 'US000017');
INSERT INTO `account` (`id`, `created_on`, `updated_on`, `version`, `address`, `avatar`, `email`, `full_name`, `password`, `phone`, `status`, `username`, `verify_email`, `verify_phone`, `role_id`, `location_id`, `code`) VALUES (18, '2022-09-30 23:53:38', '2022-09-30 23:53:38', 0, 'Số 2 Lương Ngọc Quyến, P.Hàng Buồm, Q.Hoàn Kiếm, Hà Nội', NULL, 'owner2@gmail.com', 'Trumpet - 2 Lương Ngọc Quyến', '$2a$10$fpbwplgCnGZuWW2miFQJcOpiXA.qRwZbQ/nxyf9VItkjZjnDQ7boa', '0968668855', b'1', 'owner2@gmail.com', b'0', b'0', 2, 1, 'US000018');
INSERT INTO `account` (`id`, `created_on`, `updated_on`, `version`, `address`, `avatar`, `email`, `full_name`, `password`, `phone`, `status`, `username`, `verify_email`, `verify_phone`, `role_id`, `location_id`, `code`) VALUES (19, '2022-10-01 00:02:19', '2022-10-01 00:02:19', 0, '44 Mỹ Đình, Q.Nam Từ Liêm, Hà Nội', NULL, 'owner3@gmail.com', 'Moonstar - 44 Mỹ Đình', '$2a$10$hcuw5s8f8KYpXuNI8hFxcerWo98X0ioukagb7KEoeD5e.z/LO95Jq', '0968668866', b'1', 'owner3@gmail.com', b'0', b'0', 2, 1, 'US000019');
INSERT INTO `account` (`id`, `created_on`, `updated_on`, `version`, `address`, `avatar`, `email`, `full_name`, `password`, `phone`, `status`, `username`, `verify_email`, `verify_phone`, `role_id`, `location_id`, `code`) VALUES (20, '2022-10-01 00:08:09', '2022-10-01 00:08:09', 0, '98 Trung Hòa, Q.Cầu Giấy, Hà Nội', NULL, 'owner4@gmail.com', 'Melody - 98 Trung Hòa', '$2a$10$CVwmYwTq0.4RYpXwMtZZeeqqN7pyMeTYvs5Hgsvb2GQgC0Hn03DeG', '0968668877', b'1', 'owner4@gmail.com', b'0', b'0', 2, 1, 'US000020');
INSERT INTO `account` (`id`, `created_on`, `updated_on`, `version`, `address`, `avatar`, `email`, `full_name`, `password`, `phone`, `status`, `username`, `verify_email`, `verify_phone`, `role_id`, `location_id`, `code`) VALUES (21, '2022-10-01 00:41:51', '2022-10-01 00:41:51', 0, 'Số 162 Nguyễn Văn Cừ, Q.Long Biên, Hà Nội', NULL, 'owner5@gmail.com', 'LION - 162 Nguyễn Văn Cừ', '$2a$10$UFsZdLZScBov42STDcCySOgigJ43vTBECuPOucC5PT9k3itJpNsjC', '0968668888', b'1', 'owner5@gmail.com', b'0', b'0', 2, 1, 'US000021');
INSERT INTO `account` (`id`, `created_on`, `updated_on`, `version`, `address`, `avatar`, `email`, `full_name`, `password`, `phone`, `status`, `username`, `verify_email`, `verify_phone`, `role_id`, `location_id`, `code`) VALUES (22, '2022-10-01 00:46:46', '2022-10-01 00:46:46', 0, 'Số 165B Phùng Hưng, Q.Hoàn Kiếm, Hà Nội', NULL, 'owner6@gmail.com', '2K Lounge - 165B Phùng Hưng', '$2a$10$/a1bfwSNepMpN//lcrIGm.sOpHugTPzsYZBCNGS.AKSUCD9KDABXO', '0968668899', b'1', 'owner6@gmail.com', b'0', b'0', 2, 1, 'US000022');
INSERT INTO `account` (`id`, `created_on`, `updated_on`, `version`, `address`, `avatar`, `email`, `full_name`, `password`, `phone`, `status`, `username`, `verify_email`, `verify_phone`, `role_id`, `location_id`, `code`) VALUES (23, '2022-10-01 00:50:47', '2022-10-01 00:50:47', 0, 'Số 239 Lê Đức Thọ, Q.Nam Từ Liêm, Hà Nội', NULL, 'owner7@gmail.com', 'Lasvegas - 239 Lê Đức Thọ', '$2a$10$A/IzuTELTKunhTqWV4M1h.kRFOUIsnMyjkt2XsiNcTDrYqjtfQo6m', '0968668811', b'1', 'owner7@gmail.com', b'0', b'0', 2, 1, 'US000023');
INSERT INTO `account` (`id`, `created_on`, `updated_on`, `version`, `address`, `avatar`, `email`, `full_name`, `password`, `phone`, `status`, `username`, `verify_email`, `verify_phone`, `role_id`, `location_id`, `code`) VALUES (24, '2022-10-01 00:54:44', '2022-10-01 00:54:44', 0, 'Số 202 Trung Kính, Q.Cầu Giấy, Hà Nội', NULL, 'owner8@gmail.com', 'Kenz - 202 Trung Kính', '$2a$10$66lxgRa/LGyOcBWerG6eVeZ7erxyTs9JsfPgWo6ByqVLBjUD4wRza', '0968668822', b'1', 'owner8@gmail.com', b'0', b'0', 2, 1, 'US000024');
INSERT INTO `account` (`id`, `created_on`, `updated_on`, `version`, `address`, `avatar`, `email`, `full_name`, `password`, `phone`, `status`, `username`, `verify_email`, `verify_phone`, `role_id`, `location_id`, `code`) VALUES (25, '2022-10-01 00:58:59', '2022-10-01 00:58:59', 0, 'Số 67, đường Phạm Viết Chánh, P.Nguyễn Cư Trinh, Q.1, Thành phố Hồ Chí Minh', NULL, 'owner9@gmail.com', 'Kingdom - 67 đường Phạm Viết Chánh', '$2a$10$R0/BqyFyKwzILXT1OAFcNeY/umeXj1HpKktcyu/gIRpUw3nazCKFO', '0968668833', b'1', 'owner9@gmail.com', b'0', b'0', 2, 2, 'US000025');
INSERT INTO `account` (`id`, `created_on`, `updated_on`, `version`, `address`, `avatar`, `email`, `full_name`, `password`, `phone`, `status`, `username`, `verify_email`, `verify_phone`, `role_id`, `location_id`, `code`) VALUES (26, '2022-10-01 01:02:48', '2022-10-01 01:02:48', 0, 'Số 36 Vũ Trọng Khánh, Q.Hà Đông, Hà Nội', NULL, 'owner10@gmail.com', 'Level - 36 Vũ Trọng Khánh', '$2a$10$WRJht/FZAs.s0zOWmvNhKu3NJ8EDYKqFj8RHVrmS4uEw.Nb2kuBhG', '0968661144', b'1', 'owner10@gmail.com', b'0', b'0', 2, 1, 'US000026');
INSERT INTO `account` (`id`, `created_on`, `updated_on`, `version`, `address`, `avatar`, `email`, `full_name`, `password`, `phone`, `status`, `username`, `verify_email`, `verify_phone`, `role_id`, `location_id`, `code`) VALUES (27, '2022-10-01 01:06:23', '2022-10-01 01:06:23', 0, 'Số 148 Trần Phú, Q.Hà Đông, Hà Nội', NULL, 'owner11@gmail.com', '5 sao - 148 Trần Phú', '$2a$10$6qm6cBRNXFhisgRDGiRY3uiVvx8BedrNnnjptpu6EEj6WI8YouicG', '0968661177', b'1', 'owner11@gmail.com', b'0', b'0', 2, 1, 'US000027');
INSERT INTO `account` (`id`, `created_on`, `updated_on`, `version`, `address`, `avatar`, `email`, `full_name`, `password`, `phone`, `status`, `username`, `verify_email`, `verify_phone`, `role_id`, `location_id`, `code`) VALUES (28, '2022-10-01 01:09:36', '2022-10-01 01:09:36', 0, 'Số 142 Lê Văn Thọ, Quận Gò Vấp, Thành phố Hồ Chí Minh', NULL, 'owner12@gmail.com', 'Gold Star - 142 Lê Văn Thọ', '$2a$10$UKss0Nw8a7kzicIdhf6Kh.b6YyE6g4Om/J2DIKq7yLyaZiGb4.EVW', '0968663377', b'1', 'owner12@gmail.com', b'0', b'0', 2, 2, 'US000028');
COMMIT;

-- ----------------------------
-- Table structure for account_permission
-- ----------------------------
DROP TABLE IF EXISTS `account_permission`;
CREATE TABLE `account_permission` (
  `account_id` bigint NOT NULL,
  `permission_id` bigint NOT NULL,
  `id` bigint NOT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  UNIQUE KEY `UK_epx50rcv3ls1v65c4pye8faxw` (`permission_id`),
  KEY `FKcd0ncle1mucnx6xouis6glo94` (`account_id`),
  CONSTRAINT `FK4pl4ktiq7hgfchxntsjyj4uco` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`),
  CONSTRAINT `FKcd0ncle1mucnx6xouis6glo94` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of account_permission
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for booking
-- ----------------------------
DROP TABLE IF EXISTS `booking`;
CREATE TABLE `booking` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `price` double DEFAULT NULL,
  `progress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `account_id` bigint DEFAULT NULL,
  `owner_id` bigint DEFAULT NULL,
  `bill` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `time_order` datetime DEFAULT NULL,
  `total_bill` double DEFAULT NULL,
  `room_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `discount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `total_people` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `is_confirm` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7hunottedmjhtdcvhv4sx6x4a` (`account_id`),
  KEY `FKen0km2d0dv828pfmp1ljx1098` (`owner_id`),
  CONSTRAINT `FK7hunottedmjhtdcvhv4sx6x4a` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FKen0km2d0dv828pfmp1ljx1098` FOREIGN KEY (`owner_id`) REFERENCES `karaoke` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of booking
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `rate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `reply` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `account_id` bigint DEFAULT NULL,
  `owner_id` bigint DEFAULT NULL,
  `progress` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp41h5al2ajp1q0u6ox3i68w61` (`account_id`),
  KEY `FKd26t1m5pmxqcedyiehlf59p9o` (`owner_id`),
  CONSTRAINT `FKd26t1m5pmxqcedyiehlf59p9o` FOREIGN KEY (`owner_id`) REFERENCES `karaoke` (`id`),
  CONSTRAINT `FKp41h5al2ajp1q0u6ox3i68w61` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
INSERT INTO `comment` (`id`, `created_on`, `updated_on`, `version`, `rate`, `reply`, `status`, `account_id`, `owner_id`, `progress`) VALUES (1, '2022-10-05 09:39:05', '2022-10-05 09:39:07', 1, '4', 'A', b'1', 1, 10, 'PENDING');
COMMIT;

-- ----------------------------
-- Table structure for gallery
-- ----------------------------
DROP TABLE IF EXISTS `gallery`;
CREATE TABLE `gallery` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `image` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `link` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of gallery
-- ----------------------------
BEGIN;
INSERT INTO `gallery` (`id`, `created_on`, `updated_on`, `version`, `image`, `link`, `status`, `type`, `title`) VALUES (1, '2022-09-30 22:04:22', '2022-10-09 17:00:49', 2, '/photos/a13a2ceb-1ce0-48f5-b606-155e3735ced0.jpeg', '', b'1', 'SLIDE', 'SLIDE 1');
INSERT INTO `gallery` (`id`, `created_on`, `updated_on`, `version`, `image`, `link`, `status`, `type`, `title`) VALUES (2, '2022-09-30 22:55:52', '2022-10-09 21:16:11', 6, '/photos/54fc3b2b-09f4-4262-ad9a-0ac48acb6910.jpg', '', b'1', 'SLIDE', 'SLIDE 2');
INSERT INTO `gallery` (`id`, `created_on`, `updated_on`, `version`, `image`, `link`, `status`, `type`, `title`) VALUES (3, '2022-09-30 22:56:55', '2022-10-09 21:14:28', 4, '/photos/48128b33-8746-428e-89d3-b688d8a63b67.jpg', '', b'1', 'SLIDE', 'SLIDE 3');
INSERT INTO `gallery` (`id`, `created_on`, `updated_on`, `version`, `image`, `link`, `status`, `type`, `title`) VALUES (10, '2022-10-01 00:25:38', '2022-10-09 17:01:52', 3, '/photos/5e4c5b46-4dc0-4f15-bd74-c24225041b66.jpeg', '', b'1', 'BANNER_RIGHT', 'BANNER RIGHT 1');
INSERT INTO `gallery` (`id`, `created_on`, `updated_on`, `version`, `image`, `link`, `status`, `type`, `title`) VALUES (11, '2022-10-01 00:27:12', '2022-10-09 17:01:59', 2, '/photos/e518a84c-077a-4bd9-9877-222feaa266ba.jpeg', '', b'1', 'BANNER_RIGHT', 'BANNER RIGHT 2');
INSERT INTO `gallery` (`id`, `created_on`, `updated_on`, `version`, `image`, `link`, `status`, `type`, `title`) VALUES (12, '2022-10-01 00:28:11', '2022-10-09 17:02:06', 2, '/photos/4480d81d-767b-42ae-be40-f37c0725cbc4.jpeg', '', b'1', 'BANNER_RIGHT', 'BANNER RIGHT 3');
INSERT INTO `gallery` (`id`, `created_on`, `updated_on`, `version`, `image`, `link`, `status`, `type`, `title`) VALUES (21, '2022-09-30 22:56:55', '2022-10-12 23:04:04', 2, '/photos/277c1dd7-8748-40cb-be1d-f0f036a9b184.jpeg', '', b'1', 'HOME_BANNER_DESKTOP', 'HOME BANNER DESKTOP');
INSERT INTO `gallery` (`id`, `created_on`, `updated_on`, `version`, `image`, `link`, `status`, `type`, `title`) VALUES (22, '2022-09-30 22:56:55', '2022-10-12 23:04:16', 2, '/photos/50c8cd5a-1291-4553-a3a7-3514332a3292.jpeg', '', b'1', 'HOME_BANNER_MOBILE', 'HOME BANNER MOBILE');
COMMIT;

-- ----------------------------
-- Table structure for karaoke
-- ----------------------------
DROP TABLE IF EXISTS `karaoke`;
CREATE TABLE `karaoke` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `description` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `account_id` bigint DEFAULT NULL,
  `location_id` bigint DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `detail` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `regular_price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `sale_price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `total_comment` int DEFAULT NULL,
  `total_rating` double DEFAULT NULL,
  `total_rating1` int DEFAULT '0',
  `total_rating2` int DEFAULT '0',
  `total_rating3` int DEFAULT '0',
  `total_rating4` int DEFAULT '0',
  `total_rating5` int DEFAULT '0',
  `point_id` bigint DEFAULT NULL,
  `voucher` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8ir1gi86m09p49fob2id48h5w` (`account_id`),
  KEY `FK3b1rg78awhlqo86po48s9xq0h` (`location_id`),
  KEY `FKs1s0adxe3baqm0awohm986jwh` (`point_id`),
  CONSTRAINT `FK3b1rg78awhlqo86po48s9xq0h` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`),
  CONSTRAINT `FK8ir1gi86m09p49fob2id48h5w` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FKs1s0adxe3baqm0awohm986jwh` FOREIGN KEY (`point_id`) REFERENCES `point` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of karaoke
-- ----------------------------
BEGIN;
INSERT INTO `karaoke` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`, `account_id`, `location_id`, `address`, `phone`, `avatar`, `detail`, `regular_price`, `sale_price`, `total_comment`, `total_rating`, `total_rating1`, `total_rating2`, `total_rating3`, `total_rating4`, `total_rating5`, `point_id`, `voucher`) VALUES (10, '2022-09-30 23:39:47', '2022-09-30 23:52:09', 1, 'Sở hữu vị trí đắc địa trong quận Nam Từ Liêm, Avatar là quán karaoke Hà Nội nổi tiếng với phong cách hiện đại, trẻ trung mà không kém phần sang trọng. Kho bài hát tại Avatar luôn đáp ứng tiêu chí hay và hot cho những người yêu nhạc. ', 'Avatar - 419 Trần Khát Chân', b'1', 17, 1, 'Số 419 Trần Khát Chân, P.Thanh Nhàn, Q.Nam Từ Liêm, Hà Nội', '0968.66.88.44', '/photos/ebebaf62-0e9b-4171-adfb-86bcf0cc223b.webp', '<p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Avatar - 419 Trần Khát Chân - Điểm karaoke giải trí sôi động của giới trẻ Hà Thành</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Sở hữu vị trí đắc địa trong quận Nam Từ Liêm,&nbsp;<b>Avatar</b>&nbsp;là quán&nbsp;<b>karaoke Hà Nội&nbsp;</b>nổi tiếng với&nbsp;<b>phong cách hiện đại, trẻ trung</b>&nbsp;<b>mà không kém phần sang trọng</b>. Kho bài hát tại&nbsp;<b>Avatar</b>&nbsp;luôn đáp ứng tiêu chí hay và hot cho những người yêu nhạc.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Với mục tiêu đem đến dịch vụ karaoke tốt nhất cho khách hàng,&nbsp;<b>Avatar</b>&nbsp;luôn không ngừng&nbsp;<b>nâng cấp và đổi mới hệ thống âm thanh ánh sáng</b>. Một không gian thời thượng với âm thanh tuyệt vời và&nbsp;<b>hệ thống cách âm chất lượng cao</b>&nbsp;tại&nbsp;<b>Avatar</b>&nbsp;chắc chắn sẽ làm cho buổi tiệc của bạn thêm phần sôi động và náo nhiệt.</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Bên cạnh đó,&nbsp;<b>đội ngũ nhân viên chuyên nghiệp, lịch sự</b>&nbsp;cùng những&nbsp;<b>combo đồ ăn hấp dẫn&nbsp;</b>với&nbsp;<b>mức giá ưu đãi</b>&nbsp;cũng là những lý do mà bạn không nên bỏ qua điểm đến này. Với sức chứa tối đa 40 khách,&nbsp;<b>Avatar</b>&nbsp;phù hợp cho những<b>&nbsp;</b>buổi&nbsp;<b>tụ họp bạn bè, tiệc gia đình&nbsp;</b>và cả những buổi&nbsp;<b>liên hoan của công ty</b>.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Nhấc máy lên và gọi đến số&nbsp;<a href=\"tel:0968.66.88.44\" style=\"background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><b>0968.66.88.44</b></a>&nbsp;để được&nbsp;<b>9LIFE&nbsp;</b>hỗ trợ đặt phòng nhanh chóng và tiện lợi nhất.<b>&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Lưu ý:&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quý khách nên đặt phòng trước 30 phút để được hỗ trợ tốt nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi sẽ huỷ đặt bàn sau 15 phút nếu không liên lạc được với khách hàng.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Giá menu chưa bao gồm VAT. Quý khách có nhu cầu lấy VAT, vui lòng liên hệ trực tiếp tại cơ sở kinh doanh.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi không thu phí đặt cọc khi khách hàng đặt trước. Trong trường hợp khách đặt trước có các yêu cầu đặc biệt, liên hệ trực tiếp chúng tôi để biết thêm chi tiết về phí đặt cọc.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quy định thu phí khi mang đồ ăn/đồ uống từ ngoài vào: liên hệ trực tiếp chúng tôi để biết thêm thông tin chi tiết.</p>', '500,000', '400,000', 0, 0, 0, 0, 0, 0, 0, 1, NULL);
INSERT INTO `karaoke` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`, `account_id`, `location_id`, `address`, `phone`, `avatar`, `detail`, `regular_price`, `sale_price`, `total_comment`, `total_rating`, `total_rating1`, `total_rating2`, `total_rating3`, `total_rating4`, `total_rating5`, `point_id`, `voucher`) VALUES (11, '2022-09-30 23:56:23', '2022-10-01 00:00:19', 1, 'Là một trong những địa điểm giải trí nổi bật trong thị trường nightlife Hà Nội, Trumpet đã nhanh chóng chiếm được cảm tình của khách hàng thuộc mọi độ tuổi. Là quán lounge - pub Hà Nội mang màu sắc cá tính nổi bật, quán gây ấn tượng với thiết kế không gian độc đáo, cách phối màu cực sang trọng, tạo ấn tượng với mọi khách hàng khi lần đầu đặt chân đến đây.', 'Trumpet - 2 Lương Ngọc Quyến', b'1', 18, 1, 'Số 2 Lương Ngọc Quyến, P.Hàng Buồm, Q.Hoàn Kiếm, Hà Nội', '0968668855', '/photos/90103850-ba83-4816-846f-044886b04b2c.jpg', '<p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">* Sức chứa:&nbsp;100 người</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Thể loại nhạc:</b>&nbsp;HipHop, House, Tech, Trance</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Thông tin chung:&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>Trumpet - Số 2 Lương Ngọc Quyến, Hàng Buồm, Hoàn Kiếm, Hà Nội - Không gian âm nhạc sang trọng</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Là một trong những địa điểm giải trí nổi bật trong thị trường nightlife Hà Nội,&nbsp;<b>Trumpet</b>&nbsp;đã nhanh chóng chiếm được cảm tình của khách hàng thuộc mọi độ tuổi. Là quán<b>&nbsp;lounge - pub Hà Nội&nbsp;</b>mang màu sắc cá tính nổi bật, quán gây ấn tượng với thiết kế&nbsp;<b>không gian độc đáo, cách phối màu cực sang trọng</b>, tạo ấn tượng với mọi khách hàng khi lần đầu đặt chân đến đây.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>Trumpet</b>&nbsp;sở hữu&nbsp;<b>dàn DJ chuyên nghiệp</b>&nbsp;cùng&nbsp;<b>hệ thống âm thanh vô cùng sống động</b>. Đến đây, bạn sẽ có cảm giác như đang lạc bước ở một Festival âm nhạc với hàng loạt&nbsp;<b>các ca khúc trending</b>&nbsp;được remix lại. Bên cạnh đó, những màn trình diễn đẳng cấp của các ca sĩ hàng đầu cũng sẽ mang lại cho bạn trải nghiệm khó quên.</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Thực đơn đồ uống tại&nbsp;<b>Trumpet</b>&nbsp;mang những hương vị đặc trưng. Quán còn có nhiều loại&nbsp;<b>rượu nhập khẩu thượng hạng</b>, chắc chắn sẽ làm hài lòng vị giác của mọi khách hàng, ngay cả những người khó tính nhất. Với sức chứa khủng, lên đến 100 người,&nbsp;<b>Trumpet</b>&nbsp;là lựa chọn hoàn hảo cho những&nbsp;<b>buổi tụ họp với bạn bè, liên hoan cùng đồng nghiệp</b>&nbsp;hay<b>&nbsp;tổ chức tiệc vừa và nhỏ</b>.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Nhấc máy lên và gọi đến số&nbsp;<a href=\"tel:0968.66.88.44\" style=\"background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><b>0968.66.88.44</b></a>&nbsp;để được&nbsp;<b>9LIFE</b>&nbsp;hỗ trợ đặt phòng nhanh chóng và tiện lợi nhất.<b>&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Lưu ý:&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quý khách nên đặt phòng trước 30 phút để được hỗ trợ tốt nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi sẽ huỷ đặt bàn sau 15 phút nếu không liên lạc được với khách hàng.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Giá menu chưa bao gồm VAT. Quý khách có nhu cầu lấy VAT, vui lòng liên hệ trực tiếp tại cơ sở kinh doanh.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi không thu phí đặt cọc khi khách hàng đặt trước. Trong trường hợp khách đặt trước có các yêu cầu đặc biệt, liên hệ trực tiếp chúng tôi để biết thêm chi tiết về phí đặt cọc.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quy định thu phí khi mang đồ ăn/đồ uống từ ngoài vào: liên hệ trực tiếp chúng tôi để biết thêm thông tin chi tiết.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chỗ đỗ xe ô tô có mất phí dịch vụ. Quý khách vui lòng liên hệ trực tiếp khi tới địa điểm để biết thêm thông tin chi tiết.</p>', '500,000', '400,000', 0, 0, 0, 0, 0, 0, 0, 1, NULL);
INSERT INTO `karaoke` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`, `account_id`, `location_id`, `address`, `phone`, `avatar`, `detail`, `regular_price`, `sale_price`, `total_comment`, `total_rating`, `total_rating1`, `total_rating2`, `total_rating3`, `total_rating4`, `total_rating5`, `point_id`, `voucher`) VALUES (12, '2022-10-01 00:05:09', '2022-10-01 00:05:09', 0, 'Nằm trong quận Nam Từ Liêm, Moonstar là quán karaoke Hà Nội thu hút khách hàng với không gian hoành tráng và phong cách độc đáo. Từ họa tiết, kiểu dáng bóng đèn cho tới hệ thống ánh sáng đa sắc màu và dàn nội thất sang trọng,... tất cả đều toát lên nét cá tính riêng, vô cùng thú vị. ', 'Moonstar - 44 Mỹ Đình', b'1', 19, 1, '44 Mỹ Đình, Q.Nam Từ Liêm, Hà Nội', '0968668866', '/photos/fc6c8366-0b45-4ea1-87d5-3201ddade9b8.jpg', '<p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">* Sức chứa:&nbsp;Tối đa 60 khách/phòng&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Thể loại nhạc:&nbsp;</b>Mọi thể loại</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Thông tin chung:&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>Moonstar - 44 Mỹ Đình - Nơi giao lưu ca hát lý tưởng cuối tuần&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Nằm trong quận Nam Từ Liêm,&nbsp;<b>Moonstar</b>&nbsp;là quán&nbsp;<b>karaoke Hà Nội</b>&nbsp;thu hút khách hàng với&nbsp;<b>không gian hoành tráng</b>&nbsp;và<b>&nbsp;phong cách độc đáo</b>. Từ họa tiết, kiểu dáng bóng đèn cho tới hệ thống ánh sáng đa sắc màu và dàn nội thất sang trọng,... tất cả đều toát lên nét cá tính riêng, vô cùng thú vị.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Hệ thống âm thanh, ánh sáng luôn được&nbsp;<b>Moonstar</b>&nbsp;đầu tư kỹ càng, đảm bảo&nbsp;<b>chất lượng âm thanh sống động</b>&nbsp;và không gian âm nhạc thăng hoa cho mọi bài hát.&nbsp;<b>Kho nhạc đa dạng</b>,&nbsp;<b>thường xuyên cập nhật những bài hát theo xu hướng mới nhất</b>, phù hợp với sở thích của nhiều khách hàng. Bên cạnh đó, mỗi phòng đều&nbsp;<b>được trang bị hệ thống cách âm chất lượng cao</b>&nbsp;mang đến cho bạn những phút giây thư giãn và thoải mái nhất khi sử dụng dịch vụ.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Một điểm cộng nữa là quán có&nbsp;<b>đội ngũ nhân viên được đào tạo bài bản, nhanh nhẹn và mến khách</b>&nbsp;chắc chắn sẽ làm hài lòng mọi khách hàng. Với sức chứa tối đa 60 người/phòng,&nbsp;<b>Moonstar</b>&nbsp;là địa điểm phù hợp để tổ chức các&nbsp;<b>buổi liên hoan của công ty, tiệc của đại gia đình&nbsp;</b>hay các&nbsp;<b>buổi họp lớp</b>,... Ngoài ra, còn có nhiều<b>&nbsp;combo giảm giá hấp dẫn&nbsp;</b>đang chờ đón bạn tại&nbsp;<b>Moonstar</b>.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Nhấc máy lên và gọi đến số&nbsp;<a href=\"tel:0968.66.88.44\" style=\"background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><b>0968.66.88.44</b></a>&nbsp;để được&nbsp;<b>9LIFE</b>&nbsp;hỗ trợ đặt phòng nhanh chóng và tiện lợi nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Lưu ý:&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quý khách nên đặt phòng trước 30 phút để được hỗ trợ tốt nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi sẽ huỷ đặt bàn sau 15 phút nếu không liên lạc được với khách hàng.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Giá menu chưa bao gồm VAT. Quý khách có nhu cầu lấy VAT, vui lòng liên hệ trực tiếp tại cơ sở kinh doanh.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi không thu phí đặt cọc khi khách hàng đặt trước. Trong trường hợp khách đặt trước có các yêu cầu đặc biệt, liên hệ trực tiếp chúng tôi để biết thêm chi tiết về phí đặt cọc.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quy định thu phí khi mang đồ ăn/đồ uống từ ngoài vào: liên hệ trực tiếp chúng tôi để biết thêm thông tin chi tiết.</p>', '400,000', '100,000', 0, 0, 0, 0, 0, 0, 0, 1, NULL);
INSERT INTO `karaoke` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`, `account_id`, `location_id`, `address`, `phone`, `avatar`, `detail`, `regular_price`, `sale_price`, `total_comment`, `total_rating`, `total_rating1`, `total_rating2`, `total_rating3`, `total_rating4`, `total_rating5`, `point_id`, `voucher`) VALUES (14, '2022-10-01 00:39:21', '2022-10-01 00:39:21', 0, 'Sở hữu vị trí đắc địa trong quận Cầu Giấy, Melody là quán karaoke Hà Nội nổi tiếng với phong cách hiện đại, trẻ trung và không kém phần sang trọng. Kho nhạc tại quán luôn đáp ứng tiêu chí hay và hot cho những người yêu nhạc. ', 'Melody - 98 Trung Hòa', b'1', 20, 1, '98 Trung Hòa, Q.Cầu Giấy, Hà Nội', '0968668877', '/photos/9b70512d-2493-40a9-81a3-8ed5ba492c7a.webp', '<p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">* Sức chứa:&nbsp;10 - 50 người/phòng&nbsp;<br><font color=\"#ffffff\" face=\"SF UI, Helvetica, Arial, sans-serif\"><span style=\"font-size: 14px; background-color: rgb(31, 31, 41);\"><b>* Thể loại nhạc:</b>&nbsp;Mọi thể loại</span></font><br><font color=\"#ffffff\" face=\"SF UI, Helvetica, Arial, sans-serif\"><span style=\"font-size: 14px; background-color: rgb(31, 31, 41);\"><b>* Thông tin chung:&nbsp;</b></span></font><br>Melody - 98 Trung Hòa - Nơi mang lại những giây phút ca hát tuyệt vời</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Sở hữu vị trí đắc địa trong quận Cầu Giấy,&nbsp;<b>Melody</b>&nbsp;là quán&nbsp;<b>karaoke Hà Nội</b>&nbsp;nổi tiếng với<b>&nbsp;phong cách hiện đại, trẻ trung và không kém phần sang trọng</b>. Kho nhạc tại quán luôn đáp ứng tiêu chí hay và hot cho những người yêu nhạc.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Với mục tiêu đem đến dịch vụ karaoke tốt nhất cho khách hàng,&nbsp;<b>Melody</b>&nbsp;luôn không ngừng&nbsp;<b>nâng cấp và đổi mới hệ thống âm thanh ánh sáng</b>. Một không gian thời thượng với âm thanh tuyệt vời và&nbsp;<b>hệ thống cách âm chất lượng cao</b>&nbsp;tại&nbsp;<b>Melody</b>&nbsp;chắc chắn sẽ làm cho buổi tiệc của bạn thêm phần sôi động và náo nhiệt.</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Bên cạnh đó,<b>&nbsp;đội ngũ nhân viên chuyên nghiệp, lịch sự</b>&nbsp;cùng&nbsp;<b>những combo đồ ăn hấp dẫn</b>&nbsp;cũng là những lý do mà bạn không nên bỏ qua điểm đến này. Với sức chứa tối đa lên đến 50 người/phòng,&nbsp;<b>Melody&nbsp;</b>phù hợp cho những buổi&nbsp;<b>tụ họp bạn bè, tiệc gia đình</b>&nbsp;và cả những&nbsp;<b>buổi liên hoan của công ty.</b>&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Nhấc máy lên và gọi đến số&nbsp;<a href=\"tel:0968.66.88.44\" style=\"background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><b>0968.66.88.44</b></a>&nbsp;để được&nbsp;<a href=\"https://www.9life.com.vn/\" style=\"background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\">9LIFE</a>&nbsp;hỗ trợ đặt phòng nhanh chóng và tiện lợi nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><font color=\"#ffffff\" face=\"SF UI, Helvetica, Arial, sans-serif\"><span style=\"font-size: 14px; background-color: rgb(31, 31, 41);\"><b>* Lưu ý:&nbsp;</b></span></font><br>- Quý khách nên đặt phòng trước 30 phút để được hỗ trợ tốt nhất.&nbsp;<br>- Chúng tôi sẽ huỷ đặt bàn sau 15 phút nếu không liên lạc được với khách hàng.&nbsp;<br>- Giá menu chưa bao gồm VAT. Quý khách có nhu cầu lấy VAT, vui lòng liên hệ trực tiếp tại cơ sở kinh doanh.&nbsp;<br>- Chúng tôi không thu phí đặt cọc khi khách hàng đặt trước. Trong trường hợp khách đặt trước có các yêu cầu đặc biệt, liên hệ trực tiếp chúng tôi để biết thêm chi tiết về phí đặt cọc.&nbsp;<br>- Quy định thu phí khi mang đồ ăn/đồ uống từ ngoài vào: liên hệ trực tiếp chúng tôi để biết thêm thông tin chi tiết.&nbsp;</p>', '700,000', '100,000', 0, 0, 0, 0, 0, 0, 0, 1, NULL);
INSERT INTO `karaoke` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`, `account_id`, `location_id`, `address`, `phone`, `avatar`, `detail`, `regular_price`, `sale_price`, `total_comment`, `total_rating`, `total_rating1`, `total_rating2`, `total_rating3`, `total_rating4`, `total_rating5`, `point_id`, `voucher`) VALUES (15, '2022-10-01 00:44:29', '2022-10-01 00:44:29', 0, 'Nhắc đến những quán karaoke Hà Nội nổi tiếng, nhất định phải kể đến LION. Nằm ở quận Long Biên, quán gây ấn tượng với thiết kế sang trọng, đẳng cấp. Với không gian cực kỳ bắt mắt, LION thu hút rất đông khách đến mỗi ngày.', 'LION - 162 Nguyễn Văn Cừ', b'1', 21, 1, 'Số 162 Nguyễn Văn Cừ, Q.Long Biên, Hà Nội', '0968668888', '/photos/47c6ab86-3cd3-42e0-88e5-d9780f114ad5.jpg', '<p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">* Sức chứa:&nbsp;5 - 40 người/phòng</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Thể loại nhạc:&nbsp;</b>Mọi thể loại&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Thông tin chung:&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>DRAGON KTV 1001 Nights - 97 Ngô Quyền - Chốn gặp gỡ cuối ngày cho mọi tín đồ âm nhạc</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Nằm ở con phố đông đúc trong thành phố Hải Dương,&nbsp;<b>DRAGON KTV 1001 Nights</b>&nbsp;là một trong những quán&nbsp;<b>karaoke Hải Dương</b>&nbsp;thu hút rất đông các bạn trẻ cùng những người yêu nhạc. Với&nbsp;<b>thiết kế sang trọng và đẳng cấp</b>,&nbsp;<b>DRAGON KTV 1001 Nights</b>&nbsp;được đánh giá là một công trình nghệ thuật độc đáo được tạo nên bởi bàn tay tài hoa của các nhà thiết kế có tiếng ở Việt Nam.</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Với mong muốn đem lại cho khách hàng một không gian giải trí cao cấp và thời thượng, các thiết bị âm thanh, ánh sáng đều được&nbsp;<b>DRAGON KTV 1001 Nights</b>&nbsp;đầu tư mạnh tay.&nbsp;<b>Tường cách âm hiện đại</b>&nbsp;kết hợp với&nbsp;<b>dàn loa tân tiến</b>&nbsp;cùng<b>&nbsp;hệ thống đèn đa sắc màu</b>&nbsp;sẽ biến mỗi phòng hát trở thành một liveshow lung linh và huyền ảo. Đặc biệt, kho bài hát tại&nbsp;<b>DRAGON KTV 1001 Nights</b>&nbsp;liên tục được&nbsp;<b>cập nhật và bổ sung những ca khúc hot</b>&nbsp;từ các bảng xếp hạng âm nhạc uy tín.</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Menu của quán bao gồm&nbsp;<b>nhiều loại đồ uống đa dạng, các món ăn được chế biến kỹ lưỡng, đảm bảo vệ sinh an toàn thực phẩm</b>.&nbsp;<b>Đội ngũ nhân viên chuyên nghiệp</b>, sẵn sàng xử lý nhanh những yêu cầu của khách hàng. Với sức chứa từ 10 - 40 người/phòng,&nbsp;<b>DRAGON KTV 1001 Nights</b>&nbsp;phù hợp để tổ chức những&nbsp;<b>buổi tiệc của gia đình, công ty</b>&nbsp;hay các<b>&nbsp;buổi liên hoan với bạn bè, họp lớp</b>,...</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Nhấc máy lên và gọi đến số&nbsp;<a href=\"tel:0968.66.88.44\" style=\"background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><b>0968.66.88.44</b></a>&nbsp;để được&nbsp;<b>9LIFE</b>&nbsp;hỗ trợ đặt phòng nhanh chóng và tiện lợi nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Lưu ý:&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quý khách nên đặt phòng trước 30 phút để được hỗ trợ tốt nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi sẽ huỷ đặt bàn sau 15 phút nếu không liên lạc được với khách hàng.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Giá menu chưa bao gồm VAT. Quý khách có nhu cầu lấy VAT, vui lòng liên hệ trực tiếp tại cơ sở kinh doanh.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi không thu phí đặt cọc khi khách hàng đặt trước. Trong trường hợp khách đặt trước có các yêu cầu đặc biệt, liên hệ trực tiếp chúng tôi để biết thêm chi tiết về phí đặt cọc.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quy định thu phí khi mang đồ ăn/đồ uống từ ngoài vào: liên hệ trực tiếp chúng tôi để biết thêm thông tin chi tiết.</p>', '600,000', '100,000', 0, 0, 0, 0, 0, 0, 0, 1, NULL);
INSERT INTO `karaoke` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`, `account_id`, `location_id`, `address`, `phone`, `avatar`, `detail`, `regular_price`, `sale_price`, `total_comment`, `total_rating`, `total_rating1`, `total_rating2`, `total_rating3`, `total_rating4`, `total_rating5`, `point_id`, `voucher`) VALUES (16, '2022-10-01 00:48:48', '2022-10-10 08:44:26', 5, 'Nằm trong quận Hoàn Kiếm, 2K Lounge là địa chỉ lounge Hà Nội cao cấp được nhiều bạn trẻ ưa thích. Nổi bật với cách bày trí độc đáo, tinh tế cùng thể loại nhạc house lak sống động, đây là địa chỉ mà bạn không thể bỏ qua khi muốn tìm một điểm dừng chân khi muốn xả stress cuối ngày.', '2K Lounge - 165B Phùng Hưng', b'1', 22, 1, 'Số 165B Phùng Hưng, Q.Hoàn Kiếm, Hà Nội', '0968668899', '/photos/20997671-66be-46a3-a74f-6b9f22d79954.webp', '<p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">* Sức chứa:&nbsp;60 khách</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Thể loại nhạc:&nbsp;</b>house lak&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Thông tin chung:&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>2K Lounge - 165B Phùng Hưng - Không gian âm nhạc sôi động giữa lòng Hà Nội</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Nằm trong quận Hoàn Kiếm,&nbsp;<b>2K Lounge</b>&nbsp;là địa chỉ<b>&nbsp;lounge Hà Nội</b>&nbsp;cao cấp được nhiều bạn trẻ ưa thích. Nổi bật với&nbsp;<b>cách bày trí độc đáo, tinh tế&nbsp;</b>cùng thể loại&nbsp;<b>nhạc house lak sống động</b>, đây là địa chỉ mà bạn không thể bỏ qua khi muốn tìm một điểm dừng chân khi muốn xả stress cuối ngày.</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>2K Lounge</b>&nbsp;sở hữu&nbsp;<b>không gian hiện đại, trẻ trung</b>&nbsp;cùng với&nbsp;<b>dàn âm thanh, ánh sáng chất lượng cao</b>. Khi đến đây, bạn sẽ được thư giãn với những màn biểu diễn đa màu sắc, cuồng nhiệt, sôi động. Điểm thu hút của quán còn nằm ở<b>&nbsp;không gian âm nhạc mới mẻ</b>&nbsp;cùng&nbsp;<b>combo đồ uống hấp dẫn</b>&nbsp;với&nbsp;<b>mức giá ưu đãi.</b>&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Với sức chứa 60 khách,&nbsp;<b>2K Lounge</b>&nbsp;là điểm đến lý tưởng cho những dịp&nbsp;<b>hội ngộ bạn bè, liên hoan cùng công ty&nbsp;</b>hay những<b>&nbsp;buổi giao lưu sôi nổi,</b>...&nbsp;<b>Đội ngũ nhân viên thân thiện, nhiệt tình</b>&nbsp;hứa hẹn sẽ giúp bạn có khoảng thời gian thú vị khi đến với<b>&nbsp;2K Lounge</b>.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Nhấc máy lên và gọi đến số&nbsp;<a href=\"tel:0968.66.88.44\" style=\"background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><b>0968.66.88.9</b></a>9&nbsp;để được<b>&nbsp;9LIFE</b>&nbsp;hỗ trợ đặt phòng nhanh chóng và tiện lợi nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Lưu ý:&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quý khách nên đặt phòng trước 30 phút để được hỗ trợ tốt nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi sẽ huỷ đặt bàn sau 15 phút nếu không liên lạc được với khách hàng.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Giá menu chưa bao gồm VAT. Quý khách có nhu cầu lấy VAT, vui lòng liên hệ trực tiếp tại cơ sở kinh doanh.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi không thu phí đặt cọc khi khách hàng đặt trước. Trong trường hợp khách đặt trước có các yêu cầu đặc biệt, liên hệ trực tiếp chúng tôi để biết thêm chi tiết về phí đặt cọc.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quy định thu phí khi mang đồ ăn/đồ uống từ ngoài vào: liên hệ trực tiếp chúng tôi để biết thêm thông tin chi tiết.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chỗ đỗ xe ô tô có mất phí dịch vụ. Quý khách vui lòng liên hệ trực tiếp khi tới địa điểm để biết thêm thông tin chi tiết.&nbsp;</p>', '600,000', '50,000', 0, 0, 0, 0, 0, 0, 0, 3, 'FREE 50K');
INSERT INTO `karaoke` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`, `account_id`, `location_id`, `address`, `phone`, `avatar`, `detail`, `regular_price`, `sale_price`, `total_comment`, `total_rating`, `total_rating1`, `total_rating2`, `total_rating3`, `total_rating4`, `total_rating5`, `point_id`, `voucher`) VALUES (17, '2022-10-01 00:52:45', '2022-10-01 00:52:56', 1, 'Nằm trong quận Nam Từ Liêm, Lasvegas là quán karaoke Hà Nội thu hút nhiều bạn trẻ bởi không gian mang phong cách hiện đại, đa màu sắc. Kho bài hát được cập nhật liên tục theo xu hướng mới nhất khiến khách hàng thỏa sức thả mình vào những bản nhạc yêu thích. ', 'Lasvegas - 239 Lê Đức Thọ', b'1', 23, 1, 'Số 239 Lê Đức Thọ, Q.Nam Từ Liêm, Hà Nội', '0968668811', '/photos/0953716e-5982-4359-bf25-3c2cb00c18e9.webp', '<p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">* Sức chứa:&nbsp;Đang cập nhật</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Thể loại nhạc:&nbsp;</b>Mọi thể loại</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Thông tin chung:&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>Lasvegas - 239 Lê Đức Thọ - Điểm karaoke lý tưởng cho những ngày cuối tuần&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Nằm trong quận Nam Từ Liêm,&nbsp;<b>Lasvegas</b>&nbsp;là quán&nbsp;<b>karaoke Hà Nội</b>&nbsp;thu hút nhiều bạn trẻ bởi&nbsp;<b>không gian mang phong cách hiện đại, đa màu sắc</b>.<b>&nbsp;Kho bài hát được cập nhật liên tục theo xu hướng mới nhất</b>&nbsp;khiến khách hàng thỏa sức thả mình vào những bản nhạc yêu thích.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Trở thành điểm giao lưu âm nhạc quen thuộc của giới trẻ Hà Thành, trong những năm qua,<b>&nbsp;Lasvegas</b>&nbsp;không ngừng khẳng định mình là nơi cung cấp dịch vụ karaoke chất lượng cao. Từ&nbsp;<b>ghế sofa, bàn cho tới các thiết bị trong mỗi phòng đều là sản phẩm cao cấp</b>.&nbsp;<b>Hệ thống âm thanh đạt chuẩn, có chất lượng như phòng thu</b>, mang đến cho bạn cảm giác thích thú và thoải mái khi ca hát.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Bên cạnh đó,&nbsp;<b>không gian phòng hát sạch sẽ, rộng rãi; menu đồ uống và đồ ăn vặt đa dạng</b>&nbsp;với&nbsp;<b>mức giá hợp lý</b>&nbsp;là những điểm cộng không thể bỏ qua tại&nbsp;<b>Lasvegas</b>. Quán là lựa chọn hoàn hảo cho những&nbsp;<b>buổi tiệc liên hoan của công ty, đại gia đình</b>,... và cả những&nbsp;<b>buổi giao lưu bạn bè sôi động</b>.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Nhấc máy lên và gọi đến số&nbsp;<a href=\"tel:0968.66.88.44\" style=\"background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><b>0968.66.88.44</b></a>&nbsp;để được&nbsp;<b>9LIFE</b>&nbsp;hỗ trợ đặt phòng nhanh chóng và tiện lợi nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Lưu ý:&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quý khách nên đặt phòng trước 30 phút để được hỗ trợ tốt nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi sẽ huỷ đặt bàn sau 15 phút nếu không liên lạc được với khách hàng.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Giá menu chưa bao gồm VAT. Quý khách có nhu cầu lấy VAT, vui lòng liên hệ trực tiếp tại cơ sở kinh doanh.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi không thu phí đặt cọc khi khách hàng đặt trước. Trong trường hợp khách đặt trước có các yêu cầu đặc biệt, liên hệ trực tiếp chúng tôi để biết thêm chi tiết về phí đặt cọc.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quy định thu phí khi mang đồ ăn/đồ uống từ ngoài vào: liên hệ trực tiếp chúng tôi để biết thêm thông tin chi tiết.&nbsp;</p>', '400,000', '100,000', 0, 0, 0, 0, 0, 0, 0, 2, NULL);
INSERT INTO `karaoke` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`, `account_id`, `location_id`, `address`, `phone`, `avatar`, `detail`, `regular_price`, `sale_price`, `total_comment`, `total_rating`, `total_rating1`, `total_rating2`, `total_rating3`, `total_rating4`, `total_rating5`, `point_id`, `voucher`) VALUES (18, '2022-10-01 00:57:10', '2022-10-09 21:17:20', 2, 'Nằm trong quận Cầu Giấy, Kenz là một trong những quán karaoke Hà Nội thu hút nhiều bạn trẻ với phong cách sang trọng, trẻ trung. Kho bài hát của quán được cập nhật liên tục, các bài hát mới nhất, hay nhất bắt kịp xu hướng của thị trường âm nhạc trong nước và quốc tế. ', 'Kenz - 202 Trung Kính', b'1', 24, 1, 'Số 202 Trung Kính, Q.Cầu Giấy, Hà Nội', '0968668822', '/photos/3738d0a8-6ed8-4319-b9ab-ad5fa72191d1.jpg', '<p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">* Sức chứa:&nbsp;10 - 60 người/phòng - 21&nbsp;phòng</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Thể loại nhạc:&nbsp;</b>Mọi thể loại</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Thông tin chung:&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>Kenz - 202 Trung Kính - Điểm karaoke giải trí sôi động cuối ngày&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Nằm trong quận Cầu Giấy,<b>&nbsp;Kenz</b>&nbsp;là một trong những quán&nbsp;<b>karaoke Hà Nội&nbsp;</b>thu hút nhiều bạn trẻ với&nbsp;<b>phong cách sang trọng, trẻ trung</b>. Kho bài hát của quán được cập nhật liên tục, các&nbsp;<b>bài hát mới nhất, hay nhất</b>&nbsp;bắt kịp xu hướng của thị trường âm nhạc trong nước và quốc tế.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Với&nbsp;<b>dàn âm thanh, ánh sáng hiện đại, sống động</b>,&nbsp;<b>Kenz</b>&nbsp;sẽ mang đến cho khách hàng những khoảnh khắc đáng nhớ và thú vị nhất. Bên cạnh đó,&nbsp;<b>hệ thống phòng hát đảm bảo hoàn toàn cách âm</b>&nbsp;tạo cho khách hàng không gian riêng tư và thoải mái nhất để thỏa sức ca hát.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Với sức chứa 10 - 60 người/phòng,&nbsp;<b>Kenz</b>&nbsp;là điểm đến lý tưởng cho những&nbsp;<b>buổi tiệc công ty, gia đình</b>&nbsp;và cả những&nbsp;<b>buổi liên hoan với bạn bè, đồng nghiệp</b>.&nbsp;<b>Không gian quán sạch sẽ, tiện nghi</b>;&nbsp;<b>menu đa dạng, chuẩn nhà hàng</b>&nbsp;với&nbsp;<b>giả cả hợp lý</b>&nbsp;chắc chắn sẽ khiến bạn hài lòng khi đến đây.</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Nhấc máy lên và gọi đến số&nbsp;<a href=\"tel:0968.66.88.44\" style=\"background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><b>0968.66.88.2</b></a>2&nbsp;để được<b>&nbsp;9LIFE</b>&nbsp;hỗ trợ đặt phòng nhanh chóng và tiện lợi nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Lưu ý:&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quý khách nên đặt phòng trước 30 phút để được hỗ trợ tốt nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi sẽ huỷ đặt bàn sau 15 phút nếu không liên lạc được với khách hàng.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Giá menu chưa bao gồm VAT. Quý khách có nhu cầu lấy VAT, vui lòng liên hệ trực tiếp tại cơ sở kinh doanh.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi không thu phí đặt cọc khi khách hàng đặt trước. Trong trường hợp khách đặt trước có các yêu cầu đặc biệt, liên hệ trực tiếp chúng tôi để biết thêm chi tiết về phí đặt cọc.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quy định thu phí khi mang đồ ăn/đồ uống từ ngoài vào: liên hệ trực tiếp chúng tôi để biết thêm thông tin chi tiết.</p>', '500,000', '100,000', 0, 0, 0, 0, 0, 0, 0, 2, NULL);
INSERT INTO `karaoke` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`, `account_id`, `location_id`, `address`, `phone`, `avatar`, `detail`, `regular_price`, `sale_price`, `total_comment`, `total_rating`, `total_rating1`, `total_rating2`, `total_rating3`, `total_rating4`, `total_rating5`, `point_id`, `voucher`) VALUES (19, '2022-10-01 01:01:02', '2022-10-01 01:13:21', 1, 'Nếu bạn đang muốn tìm một quán karaoke có không gian rộng rãi và tiện nghi trên địa bàn thành phố Hồ Chí Minh, hãy đến với karaoke Kingdom. Nằm ở vị trí thông thoáng trong quận 1, đây là một địa điểm giải trí karaoke Sài Gòn được nhiều khách hàng yêu thích nhờ phong cách hiện đại, trẻ trung. ', 'Kingdom - 67 đường Phạm Viết Chánh', b'1', 25, 2, 'Số 67, đường Phạm Viết Chánh, P.Nguyễn Cư Trinh, Q.1, Thành phố Hồ Chí Minh', '0968668833', '/photos/171b1ace-4b20-415c-badc-28fb81550b0d.jpg', '<p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">* Sức chứa:&nbsp;6 - 50 người/ phòng - 30 phòng</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Thể loại nhạc:&nbsp;</b>Mọi thể loại</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Thông tin chung:&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>Kingdom - Số 67 đường Phạm Viết Chánh - Không gian âm nhạc sành điệu mà bạn không thể bỏ qua</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Nếu bạn đang muốn tìm một quán karaoke có không gian rộng rãi và tiện nghi trên địa bàn thành phố Hồ Chí Minh, hãy đến với karaoke&nbsp;<b>Kingdom</b>. Nằm ở vị trí thông thoáng trong quận 1, đây là một địa điểm giải trí&nbsp;<b>karaoke Sài Gòn</b>&nbsp;được nhiều khách hàng yêu thích nhờ&nbsp;<b>phong cách hiện đại, trẻ trung</b>.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Được thành lập từ năm 2011, đến nay,&nbsp;<b>Kingdom</b>&nbsp;đã trở thành một trong những thương hiệu gây được tiếng vang trong lĩnh vực karaoke. Trong suốt hơn 10 năm qua, quán luôn không ngừng nâng cấp và cải thiện từ cơ sở vật chất cho đến phong cách phục vụ.<b>&nbsp;Hệ thống âm thanh, ánh sáng đạt tiêu chuẩn châu Âu</b>&nbsp;kết hợp với&nbsp;<b>tường cách âm tiên tiến</b>&nbsp;chắc chắn sẽ mang đến cho khách hàng những trải nghiệm tuyệt vời nhất.</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Karaoke&nbsp;<b>Kingdom</b>&nbsp;bao gồm 30 phòng hát có sức chứa từ 6 - 50&nbsp;người/ phòng, là lựa chọn lý tưởng cho những&nbsp;<b>buổi liên hoan của công ty, sum họp gia đình, những cuộc vui với bạn bè</b>&nbsp;hay<b>&nbsp;gặp mặt đối tác</b>.&nbsp;<b>Đội ngũ nhân viên thân thiện, nhiệt tình</b>&nbsp;và&nbsp;<b>menu đồ ăn vặt đa dạng</b>&nbsp;cũng là những lý do mà bạn nên đặt chân đến karaoke&nbsp;<b>Kingdom</b>&nbsp;ít nhất một lần trong đời.</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Nhấc máy lên và gọi đến số&nbsp;<a href=\"tel:0968.66.88.44\" style=\"background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><b>0968.66.88.3</b></a>3&nbsp;để được&nbsp;<b>9LIFE&nbsp;</b>hỗ trợ đặt phòng nhanh chóng và tiện lợi nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Lưu ý:&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quý khách nên đặt phòng trước 30 phút để được hỗ trợ tốt nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi sẽ huỷ đặt bàn sau 15 phút nếu không liên lạc được với khách hàng.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Giá menu chưa bao gồm VAT. Quý khách có nhu cầu lấy VAT, vui lòng liên hệ trực tiếp tại cơ sở kinh doanh.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi không thu phí đặt cọc khi khách hàng đặt trước. Trong trường hợp khách đặt trước có các yêu cầu đặc biệt, liên hệ trực tiếp chúng tôi để biết thêm chi tiết về phí đặt cọc.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quy định thu phí khi mang đồ ăn/đồ uống từ ngoài vào: liên hệ trực tiếp chúng tôi để biết thêm thông tin chi tiết.</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Phí dịch vụ trang trí sự kiện được tính theo nhu cầu trang trí của khách hàng: liên hệ trực tiếp với chúng tôi để biết thêm chi tiết.</p>', '400,000', '50,000', 0, 0, 0, 0, 0, 0, 0, 2, NULL);
INSERT INTO `karaoke` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`, `account_id`, `location_id`, `address`, `phone`, `avatar`, `detail`, `regular_price`, `sale_price`, `total_comment`, `total_rating`, `total_rating1`, `total_rating2`, `total_rating3`, `total_rating4`, `total_rating5`, `point_id`, `voucher`) VALUES (20, '2022-10-01 01:04:39', '2022-10-01 01:04:39', 0, 'Nằm trên con đường thuận tiện về giao thông với chỗ để xe rộng rãi, Level là quán karaoke Hà Nội được nhiều khách hàng tin tưởng lựa chọn nhờ sự tiện nghi và chất lượng. Quán gây ấn tượng với nội thất mạ vàng đem lại cảm giác sang trọng và cao cấp kết hợp với hệ thống điều hoà hai chiều tiên tiến.', 'Level - 36 Vũ Trọng Khánh', b'1', 26, 1, 'Số 36 Vũ Trọng Khánh, Q.Hà Đông, Hà Nội', '0968661144', '/photos/ce37edac-6a5b-4ab8-a695-389d683d0556.jpg', '<p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">* Sức chứa:&nbsp;tối đa&nbsp;40 người/phòng - 40 phòng</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Thể loại nhạc:&nbsp;</b>Mọi thể loại</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Thông tin chung:&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>Level - 36 Vũ Trọng Khánh - Tòa lâu đài âm nhạc hoàng gia cho những người yêu ca hát</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Nằm trên con đường thuận tiện về giao thông với&nbsp;<b>chỗ để xe rộng rãi</b>,&nbsp;<b>Level</b>&nbsp;là quán&nbsp;<b>karaoke Hà Nội</b>&nbsp;được nhiều khách hàng tin tưởng lựa chọn nhờ sự tiện nghi và chất lượng. Quán gây ấn tượng với&nbsp;<b>nội thất mạ vàng</b>&nbsp;đem lại cảm giác sang trọng và cao cấp kết hợp với<b>&nbsp;hệ thống điều hoà hai chiều tiên tiến</b>.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Điều&nbsp;tạo nên sự khác biệt của karaoke&nbsp;<b>Level</b>&nbsp;là&nbsp;<b>hệ thống âm thanh cao cấp, màn hình tivi tinh sắc nét</b>&nbsp;cùng&nbsp;<b>hệ thống đèn chiếu sáng nhiều màu sắc</b>. Đến với quán, bạn sẽ được trải nghiệm chất lượng âm thanh trung thực, sống động như phòng thu.&nbsp;<b>Danh sách bài hát được cập nhật thường xuyên</b>, bao gồm đủ ngôn ngữ như Việt, Anh, Hàn, Trung, Nhật.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>Level</b>&nbsp;còn sở hữu&nbsp;<b>không gian thoáng mát&nbsp;</b>trong đó 40% là không gian vui chơi cho trẻ em và siêu thị, đáp ứng nhu cầu của những gia đình có con nhỏ. Sở hữu quy mô khủng với hệ thống 40 phòng hát trong đó sức chứa tối đa lên đến 40 người/phòng,&nbsp;<b>Level</b>&nbsp;là điểm đến thích hợp cho những&nbsp;<b>bữa tiệc cuối năm, hội họp gia đình, bạn bè</b>&nbsp;hoặc&nbsp;<b>liên hoan công ty</b>.&nbsp;<b>Đội ngũ nhân viên chuyên nghiệp, năng động và nhiệt tình</b>&nbsp;cùng những&nbsp;<b>món ăn Hồng Kông độc đáo</b>&nbsp;với&nbsp;<b>giá cả phải chăng</b>&nbsp;hứa hẹn sẽ mang lại cho bạn khoảng thời gian thư giãn và đáng nhớ.</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Nhấc máy lên và gọi đến số&nbsp;<a href=\"tel:0968.66.88.44\" style=\"background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><b>0968.66.88.44</b></a>&nbsp;để được&nbsp;<b>9LIFE</b>&nbsp;hỗ trợ đặt phòng nhanh chóng và tiện lợi nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Lưu ý:&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quý khách nên đặt phòng trước 30 phút để được hỗ trợ tốt nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi sẽ huỷ đặt bàn sau 15 phút nếu không liên lạc được với khách hàng.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Giá menu chưa bao gồm VAT. Quý khách có nhu cầu lấy VAT, vui lòng liên hệ trực tiếp tại cơ sở kinh doanh.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi không thu phí đặt cọc khi khách hàng đặt trước. Trong trường hợp khách đặt trước có các yêu cầu đặc biệt, liên hệ trực tiếp chúng tôi để biết thêm chi tiết về phí đặt cọc.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quy định thu phí khi mang đồ ăn/đồ uống từ ngoài vào: liên hệ trực tiếp chúng tôi để biết thêm thông tin chi tiết.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Phí dịch vụ trang trí sự kiện được tính theo nhu cầu trang trí của khách hàng: liên hệ trực tiếp với chúng tôi để biết thêm chi tiết.</p>', '500,000', '100,000', 0, 0, 0, 0, 0, 0, 0, 3, NULL);
INSERT INTO `karaoke` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`, `account_id`, `location_id`, `address`, `phone`, `avatar`, `detail`, `regular_price`, `sale_price`, `total_comment`, `total_rating`, `total_rating1`, `total_rating2`, `total_rating3`, `total_rating4`, `total_rating5`, `point_id`, `voucher`) VALUES (21, '2022-10-01 01:08:06', '2022-10-01 01:12:31', 1, '5 sao không chỉ là điểm karaoke Hà Nội sang trọng bậc nhất khu vực Hà Đông mà còn là điểm đến an toàn tuyệt đối với hệ thống chữa cháy tự động trang bị vào tận trong các phòng hát. Kho nhạc phong phú, đa dạng, được bổ sung nhiều ca khúc hot theo tuần nhằm đáp ứng nhu cầu về âm nhạc của khách hàng.', '5 sao - 148 Trần Phú', b'1', 27, 1, 'Số 148 Trần Phú, Q.Hà Đông, Hà Nội', '0968661177', '/photos/38345130-b66d-4eaa-a594-a0215ba72106.jpg', '<p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">* Sức chứa:&nbsp;tối đa 80 người/phòng</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Thể loại nhạc:&nbsp;</b>Mọi thể loại</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Thông tin chung:&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>5 sao - 148 Trần Phú - Điểm karaoke giải trí hiện đại bậc nhất Hà Thành</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>5 sao</b>&nbsp;không chỉ là điểm&nbsp;<b>karaoke Hà Nội</b>&nbsp;sang trọng bậc nhất khu vực Hà Đông mà còn là điểm đến an toàn tuyệt đối với&nbsp;<b>hệ thống chữa cháy tự động</b>&nbsp;trang bị vào tận trong các phòng hát.&nbsp;<b>Kho nhạc phong phú, đa dạng</b>, được bổ sung nhiều ca khúc hot theo tuần nhằm đáp ứng nhu cầu về âm nhạc của khách hàng.</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Sở hữu 5 chi nhánh tại quận Đống Đa, Hoàng Mai, Nam Từ Liêm, Cầu Giấy, Hà Đông, karaoke&nbsp;<b>5 sao</b>&nbsp;tạo điều kiện cho khách hàng có thể lựa chọn địa điểm thuận tiện với nhu cầu cá nhân. Phòng ốc được trang bị&nbsp;<b>thiết bị âm thanh, ánh sáng hiện đại</b>, đảm bảo đem đến cho bạn những phút giây thư giãn và tuyệt vời nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Bên cạnh đó,&nbsp;<b>đội ngũ nhân viên được đào tạo chuyên nghiệp, phục vụ tận tình và chu đáo</b>&nbsp;cùng&nbsp;<b>combo đồ uống ưu đãi</b>&nbsp;chắc chắn sẽ thỏa mãn yêu cầu của bạn. Với sức chứa lớn từ 10-70 người/phòng, karaoke<b>&nbsp;5 sao&nbsp;</b>là địa điểm lý tưởng cho những dịp&nbsp;<b>hội ngộ bạn bè, liên hoan, họp mặt công ty, tiếp khách</b>&nbsp;hay&nbsp;<b>sum họp gia đình.</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Nhấc máy lên và gọi đến số&nbsp;<a href=\"tel:0968.66.88.44\" style=\"background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><b>0968.66.11.7</b></a>7&nbsp;để được&nbsp;<b>9LIFE</b>&nbsp;hỗ trợ đặt phòng nhanh chóng và tiện lợi nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Lưu ý:&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quý khách nên đặt phòng trước 30 phút để được hỗ trợ tốt nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi sẽ huỷ đặt bàn sau 15 phút nếu không liên lạc được với khách hàng.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Giá menu chưa bao gồm VAT. Quý khách có nhu cầu lấy VAT, vui lòng liên hệ trực tiếp tại cơ sở kinh doanh.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi không thu phí đặt cọc khi khách hàng đặt trước. Trong trường hợp khách đặt trước có các yêu cầu đặc biệt, liên hệ trực tiếp chúng tôi để biết thêm chi tiết về phí đặt cọc.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quy định thu phí khi mang đồ ăn/đồ uống từ ngoài vào: liên hệ trực tiếp chúng tôi để biết thêm thông tin chi tiết.</p>', '500,000', '50,000', 0, 0, 0, 0, 0, 0, 0, 3, NULL);
INSERT INTO `karaoke` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`, `account_id`, `location_id`, `address`, `phone`, `avatar`, `detail`, `regular_price`, `sale_price`, `total_comment`, `total_rating`, `total_rating1`, `total_rating2`, `total_rating3`, `total_rating4`, `total_rating5`, `point_id`, `voucher`) VALUES (22, '2022-10-01 01:11:11', '2022-10-01 01:12:56', 1, 'Nằm trong quận Gò Vấp, Gold Star là quán karaoke Sài Gòn được giới trẻ yêu thích bởi phong cách mới mẻ, hiện đại. Quán thu hút khách hàng bởi kho nhạc đa dạng, cập nhật tất cả các bài hát mới nhất, hot nhất dẫn đầu xu hướng youtube trong nước và quốc tế. ', 'Gold Star - 142 Lê Văn Thọ', b'1', 28, 2, 'Số 142 Lê Văn Thọ, Quận Gò Vấp, Thành phố Hồ Chí Minh', '0968663377', '/photos/d1033b15-e02e-44ac-95ff-4a09de331183.webp', '<p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">* Sức chứa:&nbsp;40 người</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Thể loại nhạc:</b>&nbsp;Mọi thể loại</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Thông tin chung:&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>Gold star - 142 Lê Văn Thọ - Điểm karaoke giải trí bậc nhất Sài Thành</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Nằm trong quận Gò Vấp,&nbsp;<b>Gold Star</b>&nbsp;là quán&nbsp;<b>karaoke Sài Gòn</b>&nbsp;được giới trẻ yêu thích bởi&nbsp;<b>phong cách mới mẻ, hiện đại</b>. Quán thu hút khách hàng bởi kho nhạc đa dạng, cập nhật tất cả các&nbsp;<b>bài hát mới nhất, hot nhất</b>&nbsp;dẫn đầu xu hướng youtube trong nước và quốc tế.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Không chỉ dừng lại ở đó,&nbsp;<b>Gold Star</b>&nbsp;còn sở hữu&nbsp;<b>menu rượu và ăn uống”cực chất”</b>&nbsp;với&nbsp;<b>mức giá ưu đãi</b>&nbsp;cùng&nbsp;<b>dàn âm thanh ánh sáng hiện đại và hệ thống cách âm đạt chuẩn</b>. Đặc biệt những hoạt động giải trí thú vị khác cũng là ưu điểm khiến&nbsp;<b>Gold Star</b>&nbsp;trở thành điểm hẹn lý tưởng của các bạn trẻ Sài Thành.</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Không gian&nbsp;<b>Gold Star</b>&nbsp;được&nbsp;<b>thiết kế độc đáo, tinh tế</b>;&nbsp;<b>chỗ đỗ xe hoàn toàn miễn phí&nbsp;</b>cùng&nbsp;<b>chương trình ưu đãi những gói trang trí sự kiện cơ bản</b>&nbsp;cũng là những điểm cộng không thể bỏ qua tại quán. Với sức chứa 40 người,&nbsp;<b>Gold Star</b>&nbsp;là không gian lý tưởng để&nbsp;<b>tụ họp bạn bè, công ty, tổ chức những bữa tiệc gia đình hay giao lưu bạn bè sôi nổi.</b>&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Nhấc máy lên và gọi đến số&nbsp;<a href=\"tel:0968.66.88.44\" style=\"background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;\"><b>0968.66.33.7</b></a>7&nbsp;để được&nbsp;<b>9LIFE</b>&nbsp;hỗ trợ đặt phòng nhanh chóng và tiện lợi nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>* Lưu ý:&nbsp;</b></p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quý khách nên đặt phòng trước 30 phút để được hỗ trợ tốt nhất.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi sẽ huỷ đặt bàn sau 15 phút nếu không liên lạc được với khách hàng.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Giá menu chưa bao gồm VAT. Quý khách có nhu cầu lấy VAT, vui lòng liên hệ trực tiếp tại cơ sở kinh doanh.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Chúng tôi không thu phí đặt cọc khi khách hàng đặt trước. Trong trường hợp khách đặt trước có các yêu cầu đặc biệt, liên hệ trực tiếp chúng tôi để biết thêm chi tiết về phí đặt cọc.&nbsp;</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Quy định thu phí khi mang đồ ăn/đồ uống từ ngoài vào: liên hệ trực tiếp chúng tôi để biết thêm thông tin chi tiết.</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">- Phí dịch vụ trang trí sự kiện được tính theo nhu cầu trang trí của khách hàng: liên hệ trực tiếp với chúng tôi để biết thêm chi tiết.&nbsp;</p>', '600,000', '100,000', 0, 0, 0, 0, 0, 0, 0, 4, NULL);
COMMIT;

-- ----------------------------
-- Table structure for karaoke_image
-- ----------------------------
DROP TABLE IF EXISTS `karaoke_image`;
CREATE TABLE `karaoke_image` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `avatar` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `karaoke_id` bigint DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc28cj6mkvb9c106tgt01ksqy4` (`karaoke_id`),
  CONSTRAINT `FKc28cj6mkvb9c106tgt01ksqy4` FOREIGN KEY (`karaoke_id`) REFERENCES `karaoke` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of karaoke_image
-- ----------------------------
BEGIN;
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (12, '2022-09-30 23:49:59', '2022-09-30 23:49:59', 0, '/photos/d4246225-bf0e-4b87-9a44-cc2bfa3e0887.webp', 10, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (13, '2022-09-30 23:49:59', '2022-09-30 23:49:59', 0, '/photos/e84ae4e0-2b92-4950-b21d-5945334a722c.webp', 10, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (14, '2022-10-01 00:00:04', '2022-10-01 00:00:04', 0, '/photos/e3331cc4-b850-44aa-b31d-643aa0d8fdf3.jpg', 11, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (15, '2022-10-01 00:00:04', '2022-10-01 00:00:04', 0, '/photos/a9cbf7b5-1bb5-4013-bd26-4d709cb6608a.jpg', 11, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (16, '2022-10-01 00:00:04', '2022-10-01 00:00:04', 0, '/photos/0ecc7686-c957-4a14-bbb9-849f110b815d.webp', 11, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (17, '2022-10-01 00:06:49', '2022-10-01 00:06:49', 0, '/photos/518251a8-8d2b-4693-af00-d5f1d77e2cf0.webp', 12, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (18, '2022-10-01 00:06:49', '2022-10-01 00:06:49', 0, '/photos/94e38adb-8cc1-4f65-8046-e2e4f11da99d.webp', 12, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (19, '2022-10-01 00:06:49', '2022-10-01 00:06:49', 0, '/photos/a05dd910-0fe6-47a3-b8da-d02e9daadfb1.webp', 12, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (20, '2022-10-01 00:39:49', '2022-10-01 00:39:49', 0, '/photos/5b7010dd-adc3-4f7f-b841-77ebdc71bfc2.webp', 14, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (21, '2022-10-01 00:39:49', '2022-10-01 00:39:49', 0, '/photos/0fbd13e6-2ff7-419c-ba76-4fa6addfb991.webp', 14, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (22, '2022-10-01 00:39:49', '2022-10-01 00:39:49', 0, '/photos/c7235afa-6cef-4a5f-921e-d275498eebd7.webp', 14, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (23, '2022-10-01 00:45:04', '2022-10-01 00:45:04', 0, '/photos/25fcbfc1-5643-4846-aa49-b2cda912d7d9.jpg', 15, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (24, '2022-10-01 00:45:04', '2022-10-01 00:45:04', 0, '/photos/2ab62a59-477e-480d-b7a6-7b4410267ab3.webp', 15, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (25, '2022-10-01 00:45:04', '2022-10-01 00:45:04', 0, '/photos/c0d8f2a9-7631-43fe-85dd-8132f4c2a942.jpg', 15, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (26, '2022-10-01 00:45:04', '2022-10-01 00:45:04', 0, '/photos/dbc473cf-ffcd-4ef3-a15c-02da007e0199.webp', 15, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (27, '2022-10-01 00:49:04', '2022-10-01 00:49:04', 0, '/photos/1cc3242d-72d6-4c98-9daf-4b997906a41c.webp', 16, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (28, '2022-10-01 00:49:04', '2022-10-01 00:49:04', 0, '/photos/dc41a593-d9bf-4b80-9c07-e28312b7198b.webp', 16, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (29, '2022-10-01 00:49:04', '2022-10-01 00:49:04', 0, '/photos/0419c4d5-eef4-4db8-91ca-235e14e8cbc1.webp', 16, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (30, '2022-10-01 00:53:18', '2022-10-01 00:53:18', 0, '/photos/233e5761-fd81-4e09-9a24-3379a1b7e272.jpg', 17, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (31, '2022-10-01 00:53:18', '2022-10-01 00:53:18', 0, '/photos/6cd9707a-fa8e-41f6-93c4-17507aa76ad0.webp', 17, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (32, '2022-10-01 00:53:18', '2022-10-01 00:53:18', 0, '/photos/337751bd-c0b2-4725-988f-94588c80da4d.webp', 17, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (33, '2022-10-01 00:57:35', '2022-10-01 00:57:35', 0, '/photos/4529f32e-1037-4484-84e3-11927e22c4cf.webp', 18, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (34, '2022-10-01 00:57:35', '2022-10-01 00:57:35', 0, '/photos/b11e6660-2ad9-44ef-b98f-a8d962bddc75.jpg', 18, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (35, '2022-10-01 00:57:35', '2022-10-01 00:57:35', 0, '/photos/e027ea40-b4f7-4d47-a858-15de40524aa7.jpg', 18, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (36, '2022-10-01 01:01:32', '2022-10-01 01:01:32', 0, '/photos/779accfc-2455-467d-a203-2d2914be59ea.webp', 19, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (37, '2022-10-01 01:01:32', '2022-10-01 01:01:32', 0, '/photos/17b5c94f-e5a7-4905-9141-f3f4bf8db624.webp', 19, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (38, '2022-10-01 01:01:32', '2022-10-01 01:01:32', 0, '/photos/b625fdfb-4318-4b63-979c-adf1d45f04c8.webp', 19, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (39, '2022-10-01 01:05:12', '2022-10-01 01:05:12', 0, '/photos/7112aace-04de-4e4c-a89c-a6e8e3b1cd26.webp', 20, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (40, '2022-10-01 01:05:12', '2022-10-01 01:05:12', 0, '/photos/13069f75-dca6-4fa6-8c85-e24d2a0ae4af.webp', 20, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (41, '2022-10-01 01:05:12', '2022-10-01 01:05:12', 0, '/photos/c78df956-08db-4a0d-86cb-7f03844ef4f3.webp', 20, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (42, '2022-10-01 01:08:28', '2022-10-01 01:08:28', 0, '/photos/e0fb86dd-ebf1-4985-96fd-4dc2b08793a8.jpg', 21, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (43, '2022-10-01 01:08:28', '2022-10-01 01:08:28', 0, '/photos/79350481-0aeb-4d45-8bfa-1021166f05be.webp', 21, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (44, '2022-10-01 01:08:28', '2022-10-01 01:08:28', 0, '/photos/f05fc45f-3cf0-4b39-980c-653e1af6cbf7.webp', 21, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (45, '2022-10-01 01:11:29', '2022-10-01 01:11:29', 0, '/photos/efb4b319-15a5-4bd3-a13b-5691c973173b.webp', 22, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (46, '2022-10-01 01:11:29', '2022-10-01 01:11:29', 0, '/photos/bf70400a-dbff-48e2-a479-f2c7f499b09c.webp', 22, b'1');
INSERT INTO `karaoke_image` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `karaoke_id`, `status`) VALUES (47, '2022-10-01 01:11:39', '2022-10-01 01:11:39', 0, '/photos/30371329-ed9f-4e96-92bb-d5e97d49666f.jpg', 22, b'1');
COMMIT;

-- ----------------------------
-- Table structure for location
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of location
-- ----------------------------
BEGIN;
INSERT INTO `location` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`) VALUES (1, '2022-09-13 20:54:38', '2022-09-13 20:54:53', 0, 'HÀ NỘI', 'HÀ NỘI', b'1');
INSERT INTO `location` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`) VALUES (2, '2022-09-13 15:48:26', '2022-09-13 20:41:17', 0, 'TPHCM', 'HỒ CHÍ MINH', b'1');
COMMIT;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`) VALUES (1, '2022-01-01 00:00:00', '2022-01-01 00:00:00', 0, 'Quản Lý Tài Khoản', 'ACCOUNT_MANAGER', b'1');
INSERT INTO `permission` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`) VALUES (2, '2022-01-01 00:00:00', '2022-01-01 00:00:00', 0, 'Quản Lý Khu Vực', 'LOCATION_MANAGER', b'1');
INSERT INTO `permission` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`) VALUES (3, '2022-01-01 00:00:00', '2022-01-01 00:00:00', 0, 'Quản Lý Quán Karaoke', 'KARAOKE_MANAGER', b'1');
INSERT INTO `permission` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`) VALUES (4, '2022-01-01 00:00:00', '2022-01-01 00:00:00', 0, 'Quản Lý Đặt Phòng', 'BOOKING_MANAGER', b'1');
INSERT INTO `permission` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`) VALUES (5, '2022-01-01 00:00:00', '2022-01-01 00:00:00', 0, 'Quản Lý Mã Giảm Giá', 'VOUCHER_MANAGER', b'1');
INSERT INTO `permission` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`) VALUES (6, '2022-01-01 00:00:00', '2022-01-01 00:00:00', 0, 'Quản Lý Bài VIết', 'POST_MANAGER', b'1');
INSERT INTO `permission` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`) VALUES (7, '2022-01-01 00:00:00', '2022-01-01 00:00:00', 0, 'Quản Lý Bình Luận', 'COMMENT_MANAGER', b'1');
INSERT INTO `permission` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`) VALUES (8, '2022-01-01 00:00:00', '2022-01-01 00:00:00', 0, 'Quản Lý Thư Viện', 'GALLERY_MANAGER', b'1');
INSERT INTO `permission` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`) VALUES (9, '2022-01-01 00:00:00', '2022-01-01 00:00:00', 0, 'Quản Lý Ca Sĩ', 'SINGER_MANAGER', b'1');
COMMIT;

-- ----------------------------
-- Table structure for point
-- ----------------------------
DROP TABLE IF EXISTS `point`;
CREATE TABLE `point` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `location_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7vxquh3p2gnh5r5y8f5nxnjfi` (`location_id`),
  CONSTRAINT `FK7vxquh3p2gnh5r5y8f5nxnjfi` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of point
-- ----------------------------
BEGIN;
INSERT INTO `point` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`, `location_id`) VALUES (1, '2022-10-02 00:21:43', '2022-10-02 00:55:08', 2, 'ffghllllllllllllllll', 'Quận Hai Bà Trưng', b'1', 1);
INSERT INTO `point` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`, `location_id`) VALUES (2, '2022-10-02 10:40:03', '2022-10-02 10:40:03', 0, '', 'Quận Hoàn Kiếm', b'1', 1);
INSERT INTO `point` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`, `location_id`) VALUES (3, '2022-10-02 10:44:54', '2022-10-02 10:44:54', 0, '', 'Quận Hoàng Mai', b'1', 1);
INSERT INTO `point` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`, `location_id`) VALUES (4, '2022-10-02 10:49:48', '2022-10-02 10:50:53', 2, 'hjkds', 'Quận Hoàn Kiếm 23', b'1', 1);
INSERT INTO `point` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`, `location_id`) VALUES (5, '2022-10-02 10:58:24', '2022-10-02 10:58:24', 0, '', 'Quận Hoàn Kiếm 2', b'1', 1);
INSERT INTO `point` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`, `location_id`) VALUES (6, '2022-10-02 19:02:56', '2022-10-02 19:03:31', 2, '', 'Quận Hoàn Kiếm b', b'1', 1);
INSERT INTO `point` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`, `location_id`) VALUES (7, '2022-10-02 19:16:10', '2022-10-04 13:58:38', 2, '', 'Quận 1', b'1', 2);
COMMIT;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `owner_id` bigint DEFAULT NULL,
  `progress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK23mmleyx52it1pbc487g0bpx9` (`owner_id`),
  CONSTRAINT `FK23mmleyx52it1pbc487g0bpx9` FOREIGN KEY (`owner_id`) REFERENCES `karaoke` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of post
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for promotion
-- ----------------------------
DROP TABLE IF EXISTS `promotion`;
CREATE TABLE `promotion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `status` bit(1) DEFAULT NULL,
  `discount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `detail` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `owner_id` bigint DEFAULT NULL,
  `location_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1k7ioeiuretsu9p27nl5ub991` (`owner_id`),
  KEY `FKjv4waqe10u8o8rtcwpxk60jrd` (`location_id`),
  CONSTRAINT `FK1k7ioeiuretsu9p27nl5ub991` FOREIGN KEY (`owner_id`) REFERENCES `karaoke` (`id`),
  CONSTRAINT `FKjv4waqe10u8o8rtcwpxk60jrd` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of promotion
-- ----------------------------
BEGIN;
INSERT INTO `promotion` (`id`, `created_on`, `updated_on`, `version`, `code`, `description`, `status`, `discount`, `end_date`, `start_date`, `avatar`, `detail`, `name`, `owner_id`, `location_id`) VALUES (3, '2022-10-01 01:18:58', '2022-10-01 08:39:08', 2, 'KENZ50', 'GIẢM GIÁ 50% (Áp dụng với các khách đặt bàn trước)', b'1', '50%', '2022-10-15 00:00:00', '2022-10-02 00:00:00', '/photos/f85ae861-f7e2-4ec8-af72-87c18d9182eb.webp', '<h4 style=\"margin-right: 0px; margin-bottom: 0.5em; margin-left: 0px; line-height: 1.4;\">GIẢM GIÁ 50% GIỜ HÁT VÀO BUỔI TRƯA TẤT CẢ CÁC NGÀY TRONG TUẦN, CÓ CƠ HỘI ĐƯỢC TẶNG HOA QUẢ VÀ SHISHA (Áp dụng với các khách đặt bàn trước)&nbsp;</h4><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Khách hàng đặt bàn trước qua SĐT và đến sử dụng dịch vụ tại Kenz sẽ được giảm giá 50% giờ hát và có cơ hội được tặng ngay hoa quả</p>', 'KENZ50', 18, 1);
INSERT INTO `promotion` (`id`, `created_on`, `updated_on`, `version`, `code`, `description`, `status`, `discount`, `end_date`, `start_date`, `avatar`, `detail`, `name`, `owner_id`, `location_id`) VALUES (4, '2022-10-01 01:23:08', '2022-10-01 01:29:40', 1, 'SN10', 'COMBO SINH NHẬT (Áp dụng cho các khách đặt phòng trước)', b'1', '30%', '2022-10-10 00:00:00', '2022-10-02 00:00:00', '/photos/98b5aef1-0a96-480b-a46a-4ffdff91fd1d.png', '<h4 style=\"margin-right: 0px; margin-bottom: 0.5em; margin-left: 0px; line-height: 1.4;\">COMBO SINH NHẬT (Áp dụng cho các khách đặt phòng trước)</h4><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Chương trình tổ chức sinh nhật miễn phí bao gồm trang trí phòng, tặng bánh sinh nhật + hoa đá khói + tháp ly, áp dụng cho các khách đặt bàn trước qua&nbsp;<font color=\"#ffffff\" face=\"SF UI, Helvetica, Arial, sans-serif\"><span style=\"font-size: 14px; background-color: rgb(31, 31, 41);\"><b>SDT</b></span></font>&nbsp;vào tất cả các ngày trong tuần.</p>', 'SN10', 14, 1);
INSERT INTO `promotion` (`id`, `created_on`, `updated_on`, `version`, `code`, `description`, `status`, `discount`, `end_date`, `start_date`, `avatar`, `detail`, `name`, `owner_id`, `location_id`) VALUES (5, '2022-10-01 01:26:57', '2022-10-01 01:27:21', 1, 'L10', 'GIẢM 10% CHO 50 KHÁCH ĐẦU TIÊN (Áp dụng cho các khách đặt phòng trước)', b'1', '10%', '2022-10-20 00:00:00', '2022-10-02 00:00:00', '/photos/52dab2ed-e880-41f3-adc0-ba61546ee712.webp', '<h4 style=\"margin-right: 0px; margin-bottom: 0.5em; margin-left: 0px; line-height: 1.4;\">GIẢM 10% CHO 50 KHÁCH ĐẦU TIÊN (Áp dụng cho các khách đặt bàn trước)</h4><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Giảm ngay 10% cho 50 khách đầu tiên khi khách hàng đặt bàn trước qua&nbsp;<font color=\"#ffffff\" face=\"SF UI, Helvetica, Arial, sans-serif\"><span style=\"font-size: 14px; background-color: rgb(31, 31, 41);\"><b>sdt&nbsp;</b></span></font>, áp dụng từ ngày 02/10/2022 đến hết ngày 20/10/2022.</p>', 'L10', 16, 1);
INSERT INTO `promotion` (`id`, `created_on`, `updated_on`, `version`, `code`, `description`, `status`, `discount`, `end_date`, `start_date`, `avatar`, `detail`, `name`, `owner_id`, `location_id`) VALUES (6, '2022-10-01 08:37:41', '2022-10-01 08:38:34', 1, 'AVATAR10', 'GIẢM NGAY 50% (Áp dụng cho các khách đặt phòng trước)', b'1', '20%', '2022-10-31 00:00:00', '2022-10-02 00:00:00', '/photos/9ed85afc-de9c-420a-b33c-59968f8b4f20.jpg', '<h4 style=\"margin-right: 0px; margin-bottom: 0.5em; margin-left: 0px; line-height: 1.4;\">GIẢM NGAY 50% (Áp dụng cho các khách đặt phòng trước)</h4><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\"><b>Giảm 50%</b>&nbsp;cho combo: Chả Cá HK + Nước Chanh Sả + Tráng Miệng + Khăn Lạnh (từ 278.000đ còn 139.000đ)</p><p style=\"margin-right: 0px; margin-left: 0px; margin-bottom: 10px !important;\">Chương trình áp dụng cho các Khách đặt phòng trước qua <font color=\"#ffffff\" face=\"SF UI, Helvetica, Arial, sans-serif\"><span style=\"font-size: 14px; background-color: rgb(31, 31, 41);\"><b>SDT</b></span></font>, áp dụng cho khung giờ từ 11h - 14h vào tất cả các ngày trong tuần.</p>', 'AVATAR10', 10, 1);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`) VALUES (1, '2022-01-01 00:00:00', '2022-01-01 00:00:00', 0, 'ADMIN', 'ADMIN', b'1');
INSERT INTO `role` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`) VALUES (2, '2022-01-01 00:00:00', '2022-01-01 00:00:00', 0, 'OWNER', 'OWNER', b'1');
INSERT INTO `role` (`id`, `created_on`, `updated_on`, `version`, `description`, `name`, `status`) VALUES (3, '2022-01-01 00:00:00', '2022-01-01 00:00:00', 0, 'USER', 'USER', b'1');
COMMIT;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `room_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `karaoke_id` bigint DEFAULT NULL,
  `regular_price` double DEFAULT '0',
  `sale_price` double DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK1x6hy0wurevn6x9acqdyj72it` (`karaoke_id`),
  CONSTRAINT `FK1x6hy0wurevn6x9acqdyj72it` FOREIGN KEY (`karaoke_id`) REFERENCES `karaoke` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of room
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for singer
-- ----------------------------
DROP TABLE IF EXISTS `singer`;
CREATE TABLE `singer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `avatar` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `detail` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of singer
-- ----------------------------
BEGIN;
INSERT INTO `singer` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `description`, `detail`, `name`, `status`) VALUES (1, '2022-10-11 22:37:25', '2022-10-12 17:39:01', 1, '/photos/be6b19b5-4b84-4b27-a313-dc42fd502d1f.jpeg', 'Ca Sĩ Mỹ Tâm', '<p>Ca Sĩ Mỹ Tâm<br></p>', 'Ca Sĩ Mỹ Tâm', b'0');
INSERT INTO `singer` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `description`, `detail`, `name`, `status`) VALUES (2, '2022-10-11 22:37:25', '2022-10-12 17:39:15', 1, '/photos/be6b19b5-4b84-4b27-a313-dc42fd502d1f.jpeg', 'Ca Sĩ Mỹ Tâm', '<p>Ca Sĩ Mỹ Tâm<br></p>', 'Ca Sĩ Mỹ Tâm', b'0');
INSERT INTO `singer` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `description`, `detail`, `name`, `status`) VALUES (3, '2022-10-11 22:37:25', '2022-10-12 17:39:20', 1, '/photos/be6b19b5-4b84-4b27-a313-dc42fd502d1f.jpeg', 'Ca Sĩ Mỹ Tâm', '<p>Ca Sĩ Mỹ Tâm<br></p>', 'Ca Sĩ Mỹ Tâm', b'0');
INSERT INTO `singer` (`id`, `created_on`, `updated_on`, `version`, `avatar`, `description`, `detail`, `name`, `status`) VALUES (4, '2022-10-11 22:37:25', '2022-10-12 17:40:40', 1, '/photos/667ff37e-d4a0-4eaa-8469-072167193f17.png', 'Ca Sĩ A', '<p>Ca Sĩ A<br></p>', 'Ca Sĩ A', b'1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
