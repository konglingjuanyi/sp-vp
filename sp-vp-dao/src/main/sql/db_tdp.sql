/*
Navicat MySQL Data Transfer

Source Server         : 86
Source Server Version : 50505
Source Host           : 10.91.230.86:3306
Source Database       : db_tdp

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2014-11-27 14:38:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mysql_sequence
-- ----------------------------
DROP TABLE IF EXISTS `mysql_sequence`;
CREATE TABLE `mysql_sequence` (
  `pk_name` varchar(50) NOT NULL,
  `current_value` bigint(14) NOT NULL,
  UNIQUE KEY `pk_name` (`pk_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_vehicle
-- ----------------------------
DROP TABLE IF EXISTS `tb_vehicle`;
CREATE TABLE `tb_vehicle` (
  `ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
