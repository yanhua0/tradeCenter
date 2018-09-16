/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : tradecenter

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-09-14 23:41:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for baojia
-- ----------------------------
DROP TABLE IF EXISTS `baojia`;
CREATE TABLE `baojia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` varchar(255) NOT NULL,
  `number` int(11) NOT NULL,
  `bid` int(11) NOT NULL COMMENT 'bid和uid只能有一个',
  `check_level` int(11) NOT NULL,
  `unit_price` varchar(10) DEFAULT NULL COMMENT '煤单价',
  `transport_price` varchar(10) DEFAULT NULL COMMENT '运输单价',
  `create_area` varchar(255) DEFAULT NULL,
  `send_area` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `bid` (`bid`),
  CONSTRAINT `baojia_ibfk_2` FOREIGN KEY (`bid`) REFERENCES `buyinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of baojia
-- ----------------------------

-- ----------------------------
-- Table structure for baojia_gys
-- ----------------------------
DROP TABLE IF EXISTS `baojia_gys`;
CREATE TABLE `baojia_gys` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bjid` int(11) NOT NULL,
  `gid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `gid` (`gid`),
  KEY `baojia_gys_ibfk_1` (`bjid`),
  CONSTRAINT `baojia_gys_ibfk_1` FOREIGN KEY (`bjid`) REFERENCES `baojia` (`id`) ON DELETE CASCADE,
  CONSTRAINT `baojia_gys_ibfk_2` FOREIGN KEY (`gid`) REFERENCES `gys` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of baojia_gys
-- ----------------------------

-- ----------------------------
-- Table structure for baojia_users
-- ----------------------------
DROP TABLE IF EXISTS `baojia_users`;
CREATE TABLE `baojia_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bid` int(11) DEFAULT NULL,
  `uid` int(11) NOT NULL,
  `check_advice` varchar(255) NOT NULL,
  `check_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `bid` (`bid`),
  CONSTRAINT `baojia_users_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`id`),
  CONSTRAINT `baojia_users_ibfk_2` FOREIGN KEY (`bid`) REFERENCES `buyinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of baojia_users
-- ----------------------------

-- ----------------------------
-- Table structure for buyinfo
-- ----------------------------
DROP TABLE IF EXISTS `buyinfo`;
CREATE TABLE `buyinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '存放采购单id',
  `uid` int(11) NOT NULL,
  `check_level` int(255) NOT NULL COMMENT '-2驳回-1保存0审核中1一级审核2二级审核',
  `end_time` date NOT NULL,
  `create_company` varchar(255) NOT NULL,
  `sno` varchar(255) NOT NULL,
  `create_person` varchar(255) DEFAULT NULL,
  `sign_person` varchar(255) NOT NULL,
  `create_time` date NOT NULL,
  `type1` varchar(255) NOT NULL,
  `type2` varchar(255) DEFAULT NULL,
  `number` int(11) NOT NULL,
  `transport` varchar(255) NOT NULL,
  `high_price` decimal(10,2) NOT NULL,
  `low_price` decimal(10,2) NOT NULL,
  `explains` varchar(255) DEFAULT NULL,
  `pay_type` varchar(255) NOT NULL,
  `baojia_price` decimal(10,2) NOT NULL,
  `agree_price` decimal(10,2) NOT NULL,
  `stime` date NOT NULL,
  `etime` date NOT NULL,
  `Applicant` varchar(255) NOT NULL,
  `place` varchar(255) NOT NULL,
  `calculate_type` varchar(255) NOT NULL,
  `checkgoods_type` varchar(255) NOT NULL,
  `hot1` varchar(255) NOT NULL,
  `air` varchar(255) NOT NULL,
  `hot2` varchar(255) NOT NULL,
  `remark` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  CONSTRAINT `buyinfo_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buyinfo
-- ----------------------------
INSERT INTO `buyinfo` VALUES ('1', '1', '2', '2018-08-31', '2', '2-2018-08-14', 'robot1', '2', '2018-08-14', '烟煤', '原煤', '21', '火车', '23.00', '2.00', '23', '23', '12.00', '121.00', '2018-08-14', '2018-08-14', '21', '2', '一票结算', '到厂验收', '23', '1', '1', '1');
INSERT INTO `buyinfo` VALUES ('2', '1', '2', '2018-08-31', '23', '23-2018-08-09', 'robot1', '2', '2018-08-09', '烟煤', '其它', '1', '火车', '1.00', '1.00', '1', '1', '-1.00', '-1.00', '2018-08-14', '2018-08-14', '32', '32', '一票结算', '到厂验收', '23', '1', '1', '1');
INSERT INTO `buyinfo` VALUES ('3', '7', '2', '2018-08-31', '2', '2-2018-08-14', '机器人1号', '2', '2018-08-14', '烟煤', '原煤', '2', '火车', '2.00', '2.00', '2', '2', '-1.00', '-1.00', '2018-08-14', '2018-08-14', '1', '2', '一票结算', '到厂验收', '32', '213', '1', '1');
INSERT INTO `buyinfo` VALUES ('4', '1', '2', '2018-08-31', '2', '2-20180816110257002', '机器人1号', '2', '2017-08-16', '烟煤', '原煤', '2', '火车', '22.00', '2.00', '2', '2', '1.00', '-1.00', '2018-08-16', '2018-08-16', '2', '2', '一票结算', '到厂验收', '2', '2', '2', '2');
INSERT INTO `buyinfo` VALUES ('6', '1', '2', '2018-08-31', '23', '20180817143504946', '机器人1号', '23', '2018-08-17', '烟煤', '原煤', '23', '火车', '23.00', '23.00', '23', '32', '1.00', '3.00', '2018-08-17', '2018-08-17', '23', '23', '一票结算', '到厂验收', '23', '2', '2', '2');
INSERT INTO `buyinfo` VALUES ('8', '7', '2', '2018-08-31', '2', '20180818135230003', '机器人7号', '2', '2018-08-18', '烟煤', '原煤', '2', '火车', '2.00', '2.00', '22', '2', '-1.00', '-1.00', '2018-08-18', '2018-08-18', '2', '2', '一票结算', '到厂验收', '2', '2', '2', '2');
INSERT INTO `buyinfo` VALUES ('11', '1', '-1', '2018-08-31', '重庆邮电大学', '20180830103451019', '机器人1号', 'zjl', '2018-08-21', '烟煤', '原煤', '2', '火车', '1.00', '2.00', '无', '结算方式', '1.00', '1.00', '2018-08-21', '2018-08-31', 'zjl', '南岸区', '一票结算', '到厂第三方验收', '2', '2', '2', '请尽快审批！');
INSERT INTO `buyinfo` VALUES ('12', '7', '2', '2018-08-31', '2', '20180821104849256', '机器人7号', '2', '2018-08-21', '烟煤', '原煤', '2', '火车', '2.00', '2.00', '2', '2', '-1.00', '-1.00', '2018-08-21', '2018-08-21', '2', '2', '一票结算', '到厂验收', '2', '2', '2', '2');
INSERT INTO `buyinfo` VALUES ('13', '1', '2', '2018-08-25', '重庆煤电', '20180822110623615', '机器人1号', '2', '2018-08-22', '烟煤', '其它', '1', '船运', '2.00', '2.00', '1', '1', '1.00', '1.00', '2018-08-22', '2018-08-24', '2', '重庆', '二票结算', '到厂第三方验收', '1', '2', '3', '请尽快审核！');
INSERT INTO `buyinfo` VALUES ('14', '7', '2', '2018-08-26', 'CQ', '20180822110834661', '机器人7号', '1', '2018-08-22', '烟煤', '原煤', '1', '火车', '1.00', '1.00', '1', '1', '-1.00', '-1.00', '2018-08-22', '2018-08-24', '2', '1', '铁路大票', '到厂验收', '1', '1', '1', '1');
INSERT INTO `buyinfo` VALUES ('15', '1', '2', '2018-08-29', '1', '20180823091252293', '机器人1号', '1', '2018-08-20', '烟煤', '原煤', '1', '火车', '1.00', '1.00', '1', '1', '1.00', '1.00', '2018-08-23', '2018-08-26', '1', '1', '一票结算', '到厂验收', '1', '1', '1', '1');
INSERT INTO `buyinfo` VALUES ('16', '7', '2', '2018-08-28', '1', '20180823091358214', '机器人7号', '1', '2018-08-23', '其它', '混煤', '1', '火车', '1.00', '1.00', '1', '1', '-1.00', '-1.00', '2018-08-23', '2018-08-27', '2', '1', '一票结算', '到厂验收', '1', '2', '3', '4');
INSERT INTO `buyinfo` VALUES ('17', '1', '2', '2018-09-30', '2', '20180827203125081', '机器人1号', '2', '2018-08-27', '无烟煤', '混煤', '2', '火车', '2.00', '1.00', '2', '2', '1.00', '1.00', '2018-08-27', '2018-08-25', 'zzz', '2', '一票结算', '到厂验收', '2', '2', '3', '创建');

-- ----------------------------
-- Table structure for buyinfo_users
-- ----------------------------
DROP TABLE IF EXISTS `buyinfo_users`;
CREATE TABLE `buyinfo_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bid` int(11) DEFAULT NULL,
  `uid` int(11) NOT NULL,
  `check_advice` varchar(255) NOT NULL,
  `check_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `bid` (`bid`),
  CONSTRAINT `buyinfo_users_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`id`),
  CONSTRAINT `buyinfo_users_ibfk_2` FOREIGN KEY (`bid`) REFERENCES `buyinfo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buyinfo_users
-- ----------------------------
INSERT INTO `buyinfo_users` VALUES ('34', '11', '1', '--', '2018-08-30 10:34:51');

-- ----------------------------
-- Table structure for gys
-- ----------------------------
DROP TABLE IF EXISTS `gys`;
CREATE TABLE `gys` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `hot` varchar(255) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gys
-- ----------------------------
INSERT INTO `gys` VALUES ('1', '重庆供应商公司1', '4', '4', '4');
INSERT INTO `gys` VALUES ('2', '重庆供应商公司2', '4', '4', '6');
INSERT INTO `gys` VALUES ('3', '重庆供应商公司11', '4', '4', '10');

-- ----------------------------
-- Table structure for mes
-- ----------------------------
DROP TABLE IF EXISTS `mes`;
CREATE TABLE `mes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `receieveid` int(255) NOT NULL,
  `sendname` varchar(255) NOT NULL,
  `state` int(11) NOT NULL COMMENT '1表示已读，0表示未读',
  `send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `receieveid` (`receieveid`),
  CONSTRAINT `mes_ibfk_1` FOREIGN KEY (`receieveid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mes
-- ----------------------------
INSERT INTO `mes` VALUES ('1', '你对订单号为<span>2-2018-08-14</span>的报价信息已经作废,<span>保证金已自动返还账户！</span>作废理由：作废', '6', '机器人5号', '1', '2018-08-16 12:17:10');
INSERT INTO `mes` VALUES ('2', '你对订单号为<span>2-2018-08-14</span>的报价信息已经作废,<span>保证金已自动返还账户！</span>作废理由：作废', '4', '机器人5号', '1', '2018-08-16 12:17:10');
INSERT INTO `mes` VALUES ('3', '你报价的订单号为<span>2-2018-08-14</span>已经中标!!系统已经自动退还冻结的保证金', '6', '机器人3号', '1', '2018-08-16 12:29:47');
INSERT INTO `mes` VALUES ('4', '你报价的订单号为<span>2-2018-08-14</span>已经中标!!系统已经自动退还冻结的保证金', '4', '机器人3号', '1', '2018-08-16 12:29:47');
INSERT INTO `mes` VALUES ('5', '你的订单号为<span>2-2018-08-14</span>已经有供应商报价成功!!系统已经自动缴纳履约保证金', '1', '机器人3号', '1', '2018-08-16 12:29:47');
INSERT INTO `mes` VALUES ('6', '你报价的订单号为<span>2-2018-08-14</span>已经中标!!系统已经自动退还冻结的保证金', '6', '机器人3号', '1', '2018-08-16 12:36:16');
INSERT INTO `mes` VALUES ('7', '你报价的订单号为<span>2-2018-08-14</span>已经中标!!系统已经自动退还冻结的保证金', '4', '机器人3号', '1', '2018-08-16 12:36:16');
INSERT INTO `mes` VALUES ('9', '你报价的订单号为<span>2-2018-08-14</span>已经中标!!系统已经自动退还冻结的保证金', '6', '机器人3号', '1', '2018-08-16 12:40:05');
INSERT INTO `mes` VALUES ('10', '你报价的订单号为<span>2-2018-08-14</span>已经中标!!系统已经自动退还冻结的保证金', '4', '机器人3号', '1', '2018-08-16 12:40:05');
INSERT INTO `mes` VALUES ('11', '你的订单号为<span>2-2018-08-14</span>已经有供应商报价成功!!系统已经自动缴纳履约保证金', '1', '机器人3号', '1', '2018-08-16 12:40:05');
INSERT INTO `mes` VALUES ('12', '你报价的订单号为<span>2-2018-08-14</span>已经中标!!系统已经自动退还冻结的保证金', '6', '机器人3号', '1', '2018-08-16 12:43:18');
INSERT INTO `mes` VALUES ('13', '你报价的订单号为<span>2-2018-08-14</span>已经中标!!系统已经自动退还冻结的保证金', '4', '机器人3号', '1', '2018-08-16 12:43:18');
INSERT INTO `mes` VALUES ('14', '你的订单号为<span>2-2018-08-14</span>已经有供应商报价成功!!系统已经自动缴纳履约保证金', '1', '机器人3号', '1', '2018-08-16 12:43:18');
INSERT INTO `mes` VALUES ('15', '你报价的订单号为<span>23-2018-08-09</span>已经中标!!系统已经自动退还冻结的保证金', '4', '机器人3号', '1', '2018-08-16 12:48:48');
INSERT INTO `mes` VALUES ('16', '你报价的订单号为<span>23-2018-08-09</span>已经中标!!系统已经自动退还冻结的保证金', '6', '机器人3号', '1', '2018-08-16 12:48:48');
INSERT INTO `mes` VALUES ('17', '你的订单号为<span>23-2018-08-09</span>已经有供应商报价成功!!系统已经自动缴纳履约保证金', '1', '机器人3号', '1', '2018-08-16 12:48:48');
INSERT INTO `mes` VALUES ('18', '你报价的订单号为<span>23-2018-08-09</span>已经中标!!系统已经自动退还冻结的保证金', '4', '机器人3号', '1', '2018-08-16 12:55:04');
INSERT INTO `mes` VALUES ('19', '你报价的订单号为<span>23-2018-08-09</span>已经中标!!系统已经自动退还冻结的保证金', '6', '机器人3号', '1', '2018-08-16 12:55:04');
INSERT INTO `mes` VALUES ('20', '你的订单号为<span>23-2018-08-09</span>已经有供应商报价成功!!系统已经自动缴纳履约保证金', '1', '机器人3号', '1', '2018-08-16 12:55:04');
INSERT INTO `mes` VALUES ('23', '你报价的订单号为<span>20180818135230003</span>已经中标!!系统已经自动退还冻结的保证金', '4', '机器人3号', '1', '2018-08-18 14:21:13');
INSERT INTO `mes` VALUES ('24', '你的订单号为<span>20180818135230003</span>已经有供应商报价成功!!系统已经自动缴纳履约保证金', '7', '机器人3号', '1', '2018-08-18 14:21:13');
INSERT INTO `mes` VALUES ('25', '你对订单号为<span>20180818135230003</span>的报价信息已经作废,<span>保证金已自动返还账户！</span>作废理由：1', '4', '机器人5号', '1', '2018-08-18 14:26:14');
INSERT INTO `mes` VALUES ('26', '你报价的订单号为<span>2-2018-08-14</span>已经中标!!系统已经自动退还冻结的保证金', '4', '机器人3号', '1', '2018-08-18 14:27:45');
INSERT INTO `mes` VALUES ('27', '你的订单号为<span>2-2018-08-14</span>已经有供应商报价成功!!系统已经自动缴纳履约保证金', '1', '机器人3号', '1', '2018-08-18 14:27:45');
INSERT INTO `mes` VALUES ('28', '你报价的订单号为<span>20180818135230003</span>已经中标!!系统已经自动退还冻结的保证金', '4', '机器人3号', '1', '2018-08-18 14:28:03');
INSERT INTO `mes` VALUES ('29', '你的订单号为<span>20180818135230003</span>已经有供应商报价成功!!系统已经自动缴纳履约保证金', '7', '机器人3号', '1', '2018-08-18 14:28:03');
INSERT INTO `mes` VALUES ('30', '你报价的订单号为<span>20180817143504946</span>已经中标!!系统已经自动退还冻结的报价保证金<span>1.0</span>元', '4', '机器人3号', '1', '2018-08-18 14:36:03');
INSERT INTO `mes` VALUES ('31', '你的订单号为<span>20180817143504946</span>已经有供应商报价成功!!系统已经自动缴纳履约保证金<span>245.0</span>元', '1', '机器人3号', '1', '2018-08-18 14:36:03');
INSERT INTO `mes` VALUES ('35', '你对订单号为<span>2-2018-08-14</span>的报价信息已经作废,保证金<span>12.0元</span>已自动返还账户！作废理由：', '4', '机器人5号', '1', '2018-08-21 09:26:12');
INSERT INTO `mes` VALUES ('36', '你对订单号为<span>2-2018-08-14</span>的报价信息已经作废,保证金<span>24.0元</span>已自动返还账户！作废理由：', '6', '机器人5号', '1', '2018-08-21 09:26:12');
INSERT INTO `mes` VALUES ('37', '你报价的订单号为<span>20180821090344218</span>已经中标!!系统已经自动退还冻结的报价保证金<span>3.0</span>元', '4', '机器人3号', '1', '2018-08-21 09:32:03');
INSERT INTO `mes` VALUES ('38', '你报价的订单号为<span>20180821090344218</span>已经中标!!系统已经自动退还冻结的报价保证金<span>2.0</span>元', '6', '机器人3号', '1', '2018-08-21 09:32:03');
INSERT INTO `mes` VALUES ('39', '你的订单号为<span>20180821090344218</span>已经有供应商报价成功!!系统已经自动缴纳履约保证金<span>250.0</span>元', '1', '机器人3号', '1', '2018-08-21 09:32:03');
INSERT INTO `mes` VALUES ('40', '你报价的订单号为<span>20180822110834661</span>已经中标!!系统已经自动退还冻结的报价保证金<span>0.0</span>元', '4', '机器人9号', '1', '2018-08-22 12:10:41');
INSERT INTO `mes` VALUES ('41', '你的订单号为<span>20180822110834661</span>已经有供应商报价成功!!系统已经自动缴纳履约保证金<span>0.0</span>元', '7', '机器人9号', '1', '2018-08-22 12:10:41');
INSERT INTO `mes` VALUES ('42', '你报价的订单号为<span>20180822110623615</span>已经中标!!系统已经自动退还冻结的报价保证金<span>2.0</span>元', '4', '机器人3号', '1', '2018-08-22 12:12:44');
INSERT INTO `mes` VALUES ('43', '你的订单号为<span>20180822110623615</span>已经有供应商报价成功!!系统已经自动缴纳履约保证金<span>252.0</span>元', '1', '机器人3号', '1', '2018-08-22 12:12:44');
INSERT INTO `mes` VALUES ('44', '你创建的订单号为<span>20180823091358214</span>的采购信息已经被上级驳回,驳回理由：w', '7', '机器人9号', '1', '2018-08-23 09:15:21');
INSERT INTO `mes` VALUES ('45', '你对订单号为<span>2-2018-08-14</span>的报价信息已经作废,保证金<span>24.0元</span>已自动返还账户！作废理由：w', '4', '机器人5号', '1', '2018-08-23 09:19:10');
INSERT INTO `mes` VALUES ('46', '你对订单号为<span>2-2018-08-14</span>的报价信息已经作废,保证金<span>24.0元</span>已自动返还账户！作废理由：w', '10', '机器人5号', '0', '2018-08-23 09:19:10');
INSERT INTO `mes` VALUES ('47', '你对订单号为<span>20180821104849256</span>的报价信息已经作废,保证金<span>0.0元</span>已自动返还账户！作废理由：', '4', '机器人8号', '1', '2018-08-23 09:20:38');
INSERT INTO `mes` VALUES ('48', '你报价的订单号为<span>20180823091252293</span>已经中标!!系统已经自动退还冻结的报价保证金<span>10.0</span>元', '4', '机器人3号', '1', '2018-08-23 09:21:03');
INSERT INTO `mes` VALUES ('49', '你的订单号为<span>20180823091252293</span>已经有供应商报价成功!!系统已经自动缴纳履约保证金<span>262.0</span>元', '1', '机器人3号', '1', '2018-08-23 09:21:03');
INSERT INTO `mes` VALUES ('50', '你报价的订单号为<span>20180823091358214</span>已经中标!!系统已经自动退还冻结的报价保证金<span>0.0</span>元', '4', '机器人9号', '1', '2018-08-23 09:21:21');
INSERT INTO `mes` VALUES ('51', '你的订单号为<span>20180823091358214</span>已经有供应商报价成功!!系统已经自动缴纳履约保证金<span>0.0</span>元', '7', '机器人9号', '0', '2018-08-23 09:21:21');
INSERT INTO `mes` VALUES ('52', '你创建的订单号为<span>20180827203125081</span>的采购信息已经被上级驳回,驳回理由：d', '1', '机器人2号', '1', '2018-08-27 20:33:24');
INSERT INTO `mes` VALUES ('53', '你对订单号为<span>20180827203125081</span>的报价信息已经作废,保证金<span>12.0元</span>已自动返还账户！作废理由：W', '4', '机器人2号', '1', '2018-08-27 20:40:37');
INSERT INTO `mes` VALUES ('54', '你报价的订单号为<span>20180823091252293</span>已经中标!!系统已经自动退还冻结的报价保证金<span>10.0</span>元', '4', '机器人3号', '1', '2018-08-27 20:43:03');
INSERT INTO `mes` VALUES ('55', '你的订单号为<span>20180823091252293</span>已经有供应商报价成功!!系统已经自动缴纳履约保证金<span>272.0</span>元', '1', '机器人3号', '1', '2018-08-27 20:43:03');
INSERT INTO `mes` VALUES ('56', '你报价的订单号为<span>20180827203125081</span>已经中标!!系统已经自动退还冻结的报价保证金<span>2.0</span>元', '4', '机器人3号', '1', '2018-08-27 20:45:40');
INSERT INTO `mes` VALUES ('57', '你的订单号为<span>20180827203125081</span>已经有供应商报价成功!!系统已经自动缴纳履约保证金<span>2.0</span>元', '1', '机器人3号', '0', '2018-08-30 20:45:40');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `action` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('电厂创建', '1');
INSERT INTO `role` VALUES ('电厂审核', '2');
INSERT INTO `role` VALUES ('分子公司审批', '3');
INSERT INTO `role` VALUES ('阳光用户', '4');
INSERT INTO `role` VALUES ('分子公司审核', '5');

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `coal_type` varchar(255) DEFAULT NULL,
  `Base_low_calorific_value` varchar(255) DEFAULT NULL,
  `air_dry_basis_moisture` varchar(255) DEFAULT NULL,
  `dry_base_high_calorific_value` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `delivery_method` varchar(255) DEFAULT NULL,
  `trading_locations` varchar(255) DEFAULT NULL,
  `release_end_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES ('1', '烟煤', '5500', '10', '5000', '500', '10', '火车', '电厂煤场', '2018-08-09 23:08:59', '2018-08-16 11:14:37');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `money` decimal(10,2) NOT NULL,
  `freeze_money` decimal(10,2) NOT NULL,
  `freeze_money2` decimal(10,2) NOT NULL,
  `sno` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rid` (`rid`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '1', '1', '1', '机器人1号', '100000.00', '0.00', '0.00', '1');
INSERT INTO `users` VALUES ('2', '2', '2', '2', '机器人2号', '100000.00', '0.00', '0.00', '1');
INSERT INTO `users` VALUES ('3', '3', '3', '3', '机器人3号', '100000.00', '0.00', '0.00', '1');
INSERT INTO `users` VALUES ('4', '4', '4', '4', '机器人4号', '100000.00', '0.00', '0.00', null);
INSERT INTO `users` VALUES ('5', '5', '5', '5', '机器人5号', '100000.00', '0.00', '0.00', '1');
INSERT INTO `users` VALUES ('6', '4', '6', '6', '机器人6号', '100000.00', '0.00', '0.00', null);
INSERT INTO `users` VALUES ('7', '1', '7', '7', '机器人7号', '100000.00', '0.00', '0.00', '2');
INSERT INTO `users` VALUES ('8', '2', '8', '8', '机器人8号', '100000.00', '0.00', '0.00', '2');
INSERT INTO `users` VALUES ('9', '3', '9', '9', '机器人9号', '100000.00', '0.00', '0.00', '2');
INSERT INTO `users` VALUES ('10', '4', '10', '10', '机器人10号', '100000.00', '0.00', '0.00', null);
INSERT INTO `users` VALUES ('11', '5', '11', '11', '机器人11号', '100000.00', '0.00', '0.00', '2');
