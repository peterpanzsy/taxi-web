/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50508
Source Host           : localhost:3306
Source Database       : oil

Target Server Type    : MYSQL
Target Server Version : 50508
File Encoding         : 65001

Date: 2013-12-23 16:21:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `T_MapPro`
-- ----------------------------
DROP TABLE IF EXISTS `T_MapPro`;
CREATE TABLE `T_MapPro` (
  `ID` int(8) NOT NULL AUTO_INCREMENT,
  `ProName` varchar(25) DEFAULT NULL,
  `FilePath` varchar(255) DEFAULT NULL,
  `AddDate` datetime DEFAULT NULL,
  UNIQUE KEY `idpk` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of T_MapPro
-- ----------------------------
INSERT INTO `T_MapPro` VALUES ('1', '123', '12341', '2013-12-23 14:25:01');
INSERT INTO `T_MapPro` VALUES ('2', '123', '12341', '2013-12-23 15:48:58');
INSERT INTO `T_MapPro` VALUES ('3', 'asd', 'adfsa', '2013-12-23 15:50:12');
INSERT INTO `T_MapPro` VALUES ('5', 'asd', 'adfsa', '2013-12-23 15:50:15');
INSERT INTO `T_MapPro` VALUES ('6', 'asd', 'adfsa', '2013-12-23 15:50:17');
INSERT INTO `T_MapPro` VALUES ('9', 'asd', 'adfsa', '2013-12-23 15:50:20');
INSERT INTO `T_MapPro` VALUES ('10', 'asd', 'adfsa', '2013-12-23 15:50:29');
INSERT INTO `T_MapPro` VALUES ('11', 'asd', 'adfsa', '2013-12-23 15:50:32');
INSERT INTO `T_MapPro` VALUES ('12', 'asd', 'adfsa', '2013-12-23 15:50:34');
INSERT INTO `T_MapPro` VALUES ('13', 'asd', 'adfsa', '2013-12-23 15:50:36');
INSERT INTO `T_MapPro` VALUES ('14', 'asd', 'adfsa', '2013-12-23 15:50:38');
INSERT INTO `T_MapPro` VALUES ('15', 'asd', 'adfsa', '2013-12-23 15:50:39');
INSERT INTO `T_MapPro` VALUES ('16', 'asd', 'adfsa', '2013-12-23 15:50:41');
INSERT INTO `T_MapPro` VALUES ('17', 'asd', 'adfsa', '2013-12-23 15:50:44');
INSERT INTO `T_MapPro` VALUES ('18', 'asd', 'adfsa', '2013-12-23 15:50:47');
INSERT INTO `T_MapPro` VALUES ('92', 'asd', 'adfsa', '2013-12-23 15:50:25');


CREATE TABLE `T_MapLine` (
`ProID`  int(11) NULL DEFAULT NULL ,
`Start`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`End`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Type`  int(11) NULL DEFAULT NULL 
);

CREATE TABLE `T_MapPoint` (
`ProID`  int(11) NULL DEFAULT NULL ,
`PointName`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Type`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`GeodeticCoordinatesX`  double NULL DEFAULT NULL ,
`GeodeticCoordinatesY`  double NULL DEFAULT NULL ,
`Latitude`  double NULL DEFAULT NULL ,
`Longitude`  double NULL DEFAULT NULL 
)

CREATE TABLE `T_Physical` (
`ID`  int(11) NOT NULL AUTO_INCREMENT ,
`CName`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`EName`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Description`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ISOBasicUnit`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
UNIQUE INDEX `pk_Physical` (`ID`) USING BTREE 
)

CREATE TABLE `T_Measure` (
`ID`  int(11) NOT NULL AUTO_INCREMENT ,
`PhysicalID`  int(11) NULL DEFAULT NULL ,
`EName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CName`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Symbol`  varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`RatioA`  double NULL DEFAULT NULL ,
`RatioB`  double NULL DEFAULT NULL ,
`StyleID`  int(11) NULL DEFAULT NULL ,
UNIQUE INDEX `PK_Measure` (`ID`) USING BTREE 
)

CREATE TABLE `T_PhysicalStyle` (
`StyleID`  int(11) NULL DEFAULT NULL ,
`StyleName`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL 
)

INSERT INTO `T_PhysicalStyle` VALUES ('1', 'ISO');
INSERT INTO `T_PhysicalStyle` VALUES ('2', 'English');
INSERT INTO `T_PhysicalStyle` VALUES ('3', 'UserDefine');
commit;