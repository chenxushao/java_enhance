/*
MySQL Data Transfer
Source Host: localhost
Source Database: testdb
Target Host: localhost
Target Database: testdb
Date: 2009-12-17 17:24:32
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for acid_table
-- ----------------------------
DROP TABLE IF EXISTS `acid_table`;
CREATE TABLE `acid_table` (
  `id` int(11) NOT NULL auto_increment,
  `name` char(20) NOT NULL,
  `money` double(10,0) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for register_table
-- ----------------------------
DROP TABLE IF EXISTS `register_table`;
CREATE TABLE `register_table` (
  `id` int(11) NOT NULL auto_increment,
  `name` char(20) NOT NULL,
  `password` char(20) NOT NULL,
  `birthday` date NOT NULL default '0000-00-00',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `acid_table` VALUES ('1', 'zhangsan', '970');
INSERT INTO `acid_table` VALUES ('2', 'lisi', '810');
INSERT INTO `register_table` VALUES ('1', '陈绪绍', '123456', '2009-12-16');
INSERT INTO `register_table` VALUES ('2', '陈奉先', 'cfeng', '2009-12-16');
INSERT INTO `register_table` VALUES ('5', '赵普', 'songcaozaxiang', '2009-12-16');
