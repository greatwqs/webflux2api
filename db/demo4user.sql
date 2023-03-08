/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : demo4user

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 12/02/2020 17:18:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for userlist
-- ----------------------------
CREATE TABLE `userlist`  (
  `ID` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `UserName` varchar(500) NOT NULL DEFAULT '',
  `Address` varchar(500) NOT NULL DEFAULT '',
  `ISValid` tinyint(1) UNSIGNED NOT NULL DEFAULT 1,
  `CreateTime` datetime(0) NOT NULL DEFAULT '2013-04-11 00:00:00',
  `UpdateTime` datetime(0) NOT NULL DEFAULT '2013-04-11 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userlist
-- ----------------------------
INSERT INTO `userlist` VALUES (1, 'greatwqs', '成都市 高新区', 1, '2020-02-12 00:00:00', '2020-02-12 16:38:28');
INSERT INTO `userlist` VALUES (2, 'eagle', 'eagle city', 1, '2013-04-11 00:00:00', '2013-04-11 00:00:00');

SET FOREIGN_KEY_CHECKS = 1;
