/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : demo4video

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 12/02/2020 17:18:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for videolist
-- ----------------------------
CREATE TABLE `videolist`  (
  `ID` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Title` varchar(500) NOT NULL DEFAULT '',
  `VideoSourceURL` varchar(500) NOT NULL DEFAULT '',
  `VideoViewURL` varchar(500) NOT NULL DEFAULT '',
  `Content` varchar(500) NOT NULL DEFAULT '',
  `ISValid` tinyint(1) UNSIGNED NOT NULL DEFAULT 1,
  `CreateTime` datetime(0) NOT NULL DEFAULT '2013-04-11 00:00:00',
  `UpdateTime` datetime(0) NOT NULL DEFAULT '2013-04-11 00:00:00',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `UniqueIndex_VideoSourceURL`(`VideoSourceURL`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of videolist
-- ----------------------------
INSERT INTO `videolist` VALUES (1, '这是 Title', 'http://v.baidu.com/3011186/1280x720/1507534002166.mp4', 'http://v.baidu.com/5HNCZfNC9KX-FYB-UcNr_Hm7y70=/lnBQzxP-1S_sdOcDrwX-Ivet8a5o', '这是 Content', 1, '2020-02-12 00:00:00', '2020-02-12 00:00:00');
INSERT INTO `videolist` VALUES (2, 'title', 'test', 'test', 'test', 1, '2013-04-11 00:00:00', '2013-04-11 00:00:00');

SET FOREIGN_KEY_CHECKS = 1;
