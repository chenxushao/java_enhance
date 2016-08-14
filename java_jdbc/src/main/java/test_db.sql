/*
MySQL Data Transfer
Source Host: localhost
Source Database: test_db
Target Host: localhost
Target Database: test_db
Date: 2009-12-16 22:33:01
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for register_table
-- ----------------------------
DROP TABLE IF EXISTS `register_table`;
CREATE TABLE `register_table` (
  `id` int(11) NOT NULL auto_increment,
  `name` char(20) NOT NULL,
  `password` char(20) NOT NULL,
  `birthday` date default '0000-00-00',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;


-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `register_table` VALUES ('1', '陈绪绍', '123456', '2009-12-16');
INSERT INTO `register_table` VALUES ('2', '陈奉先', 'cfeng', '2009-12-16');
INSERT INTO `register_table` VALUES ('5', '赵普', 'songcaozaxiang', '2009-12-16');

