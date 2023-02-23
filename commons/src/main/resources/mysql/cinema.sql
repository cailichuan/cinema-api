/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : cinema

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 29/03/2023 19:42:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_activity
-- ----------------------------
DROP TABLE IF EXISTS `t_activity`;
CREATE TABLE `t_activity`  (
  `content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `number` int(0) NULL DEFAULT NULL,
  `startTime` datetime(0) NULL DEFAULT NULL,
  `endTime` datetime(0) NULL DEFAULT NULL,
  `createAt` datetime(0) NULL DEFAULT NULL,
  `id` bigint(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_activity
-- ----------------------------
INSERT INTO `t_activity` VALUES ('1221', 121, '2023-03-03 21:02:12', '2023-03-03 21:02:17', '2023-03-03 21:02:18', 1212);
INSERT INTO `t_activity` VALUES ('heih1', 553, '2023-03-03 21:02:55', '2023-03-03 21:02:56', '2023-03-03 21:02:57', 3343);
INSERT INTO `t_activity` VALUES ('来啦', 2, '2023-02-17 12:49:00', '2023-03-17 12:49:00', '2023-03-10 18:41:24', 7039908092838023168);
INSERT INTO `t_activity` VALUES ('sag', 2, '2023-03-21 21:02:17', '2023-03-03 21:02:17', '2023-03-12 14:50:54', 7040574863261700096);
INSERT INTO `t_activity` VALUES ('string', 0, '1999-01-01 12:00:00', '1999-01-04 12:00:00', '2023-03-15 11:11:44', 7041606868954910720);
INSERT INTO `t_activity` VALUES ('string', 0, '1999-09-21 03:00:00', '1999-09-21 02:00:00', '2023-03-16 13:55:21', 7042010434152566784);
INSERT INTO `t_activity` VALUES ('string', 0, '1999-09-21 03:00:00', '1999-09-21 02:00:00', '2023-03-16 13:58:41', 7042011273927725056);

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` bigint(0) NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin
-- ----------------------------

-- ----------------------------
-- Table structure for t_arrangement
-- ----------------------------
DROP TABLE IF EXISTS `t_arrangement`;
CREATE TABLE `t_arrangement`  (
  `id` bigint(0) NOT NULL,
  `fid` bigint(0) NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `seatNumber` int(0) NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `startTime` time(0) NULL DEFAULT NULL,
  `boxOffice` int(0) NULL DEFAULT NULL,
  `endTime` time(0) NULL DEFAULT NULL,
  `founder` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createAt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_arrangement
-- ----------------------------
INSERT INTO `t_arrangement` VALUES (7039911189928882176, 2, '梦幻剧场222', 40, 33, 1, '2023-03-19', '22:51:16', 322, '23:51:16', '333', '2023-03-10 19:00:06');

-- ----------------------------
-- Table structure for t_cart
-- ----------------------------
DROP TABLE IF EXISTS `t_cart`;
CREATE TABLE `t_cart`  (
  `id` bigint(0) NOT NULL,
  `uid` bigint(0) NULL DEFAULT NULL,
  `aid` bigint(0) NULL DEFAULT NULL,
  `seats` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` int(0) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_cart
-- ----------------------------
INSERT INTO `t_cart` VALUES (7039914637952958464, 55, 7039911189928882176, '11坐2坐3坐', 1110, 1, 55);

-- ----------------------------
-- Table structure for t_daily_work
-- ----------------------------
DROP TABLE IF EXISTS `t_daily_work`;
CREATE TABLE `t_daily_work`  (
  `id` bigint(0) NOT NULL,
  `type` int(0) NULL DEFAULT NULL,
  `content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createAt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_daily_work
-- ----------------------------
INSERT INTO `t_daily_work` VALUES (32313, 1, '狗屎的电影', '2023-02-21 13:04:51');
INSERT INTO `t_daily_work` VALUES (7039916450831814656, 2, '飒飒详细信息', '2023-03-10 19:14:37');

-- ----------------------------
-- Table structure for t_film
-- ----------------------------
DROP TABLE IF EXISTS `t_film`;
CREATE TABLE `t_film`  (
  `id` bigint(0) NOT NULL,
  `name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `releaseTime` date NULL DEFAULT NULL,
  `type` char(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` tinyint(1) NULL DEFAULT NULL,
  `region` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `introduction` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `hot` int(0) NULL DEFAULT NULL,
  `cover` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `duration` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_film
-- ----------------------------
INSERT INTO `t_film` VALUES (2, '海上钢琴师', '2023-02-24', '悬疑', 1, '国内', '11', 888883, '撒大苏打', 120);
INSERT INTO `t_film` VALUES (11, '荒原狼', '2023-02-22', '喜剧', 1, '国外', '飒飒', 52, '飒飒', 120);
INSERT INTO `t_film` VALUES (33, '大鱼', '2023-02-15', '剧情', 1, '国内', '好的电影', 51, '\\\'x\\\'x\\\'x\\\'', 120);
INSERT INTO `t_film` VALUES (112, '美国往事', '2023-02-16', '悬疑', 1, '国内', '12212', 4, '212', 120);
INSERT INTO `t_film` VALUES (7039920010369851392, '中古把年', '2023-06-07', '悬疑', 1, '国内', '来', 0, 'string', 120);

-- ----------------------------
-- Table structure for t_film_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `t_film_evaluate`;
CREATE TABLE `t_film_evaluate`  (
  `id` bigint(0) NOT NULL,
  `fid` bigint(0) NULL DEFAULT NULL,
  `uid` bigint(0) NULL DEFAULT NULL,
  `star` int(0) NULL DEFAULT NULL,
  `comment` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createAt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_film_evaluate
-- ----------------------------

-- ----------------------------
-- Table structure for t_leaving_message
-- ----------------------------
DROP TABLE IF EXISTS `t_leaving_message`;
CREATE TABLE `t_leaving_message`  (
  `id` bigint(0) NOT NULL,
  `uid` bigint(0) NULL DEFAULT NULL,
  `reply` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createAt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_leaving_message
-- ----------------------------
INSERT INTO `t_leaving_message` VALUES (222, 55, '请不要这样', '哎呀', '2023-02-22 13:00:08');
INSERT INTO `t_leaving_message` VALUES (21212, 55, '6', '牛啊', '2023-02-22 12:59:45');
INSERT INTO `t_leaving_message` VALUES (7039924207475654656, 55, 'l', '吃啥', NULL);
INSERT INTO `t_leaving_message` VALUES (7040630897422532608, 55, 'string', 's洒洒水ing', '2023-03-12 18:33:34');
INSERT INTO `t_leaving_message` VALUES (7040631080864612352, 55, 'string', 's洒洒水ing', '2023-03-12 18:34:18');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` bigint(0) NOT NULL,
  `uid` bigint(0) NULL DEFAULT NULL,
  `phone` int(0) NULL DEFAULT NULL,
  `aid` bigint(0) NULL DEFAULT NULL,
  `seats` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `createAt` datetime(0) NULL DEFAULT NULL,
  `payAt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (555, 55, NULL, 123, '22号33号55号', NULL, NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (7039928041916764160, 55, 21212, 7039911189928882176, '2', 44, 0, '2023-03-10 20:00:40', NULL);
INSERT INTO `t_order` VALUES (7039928612484714496, 55, 21212, 7039911189928882176, '26号', 44, 0, '2023-03-10 20:02:56', NULL);
INSERT INTO `t_order` VALUES (7039934144968822784, 55, 21212, 7039911189928882176, '29号', 44, 0, '2023-03-10 20:24:55', NULL);
INSERT INTO `t_order` VALUES (7039934242528333824, 55, 21212, 7039911189928882176, '51号', 44, 2, '2023-03-10 20:25:19', '2023-03-13 23:51:22');
INSERT INTO `t_order` VALUES (7039935224981131264, 55, 21212, 7039911189928882176, '55号', 44, 2, '2023-03-10 20:29:13', '2023-03-10 20:56:55');
INSERT INTO `t_order` VALUES (7039936399113621504, 55, 222, 7039911189928882176, '88号', 22, 2, '2023-03-10 20:33:53', '2023-03-10 20:33:53');
INSERT INTO `t_order` VALUES (7039940342212366336, 55, 1111, 7039911189928882176, '76号', 0, 2, '2023-03-13 23:37:56', '2023-03-13 23:39:03');

-- ----------------------------
-- Table structure for t_order_exception
-- ----------------------------
DROP TABLE IF EXISTS `t_order_exception`;
CREATE TABLE `t_order_exception`  (
  `id` bigint(0) NOT NULL,
  `oid` bigint(0) NULL DEFAULT NULL,
  `reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `reviewer` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` tinyint(1) NULL DEFAULT NULL,
  `result` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createAt` datetime(0) NULL DEFAULT NULL,
  `endAt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order_exception
-- ----------------------------
INSERT INTO `t_order_exception` VALUES (6546, 6464, '646', '5646', 5, '嘿嘿', '2023-02-22 14:21:43', '2023-02-22 14:21:44');
INSERT INTO `t_order_exception` VALUES (7039942531358040064, 7039928612484714496, '牛逼33', '7', 1, '7', NULL, '2023-03-10 21:05:29');
INSERT INTO `t_order_exception` VALUES (7041065163751071744, 7039940342212366336, 'java.text.ParseException: Unparseable date: \"null900000\"', NULL, 0, NULL, '2023-03-13 23:19:11', NULL);
INSERT INTO `t_order_exception` VALUES (7041065163910455296, 7039940342212366336, 'java.text.ParseException: Unparseable date: \"null900000\"', NULL, 0, NULL, '2023-03-13 23:19:11', NULL);
INSERT INTO `t_order_exception` VALUES (7041065163998535680, 7039940342212366336, 'java.text.ParseException: Unparseable date: \"null900000\"', NULL, 0, NULL, '2023-03-13 23:19:11', NULL);
INSERT INTO `t_order_exception` VALUES (7041065164082421760, 7039940342212366336, 'java.text.ParseException: Unparseable date: \"null900000\"', NULL, 0, NULL, '2023-03-13 23:19:11', NULL);
INSERT INTO `t_order_exception` VALUES (7041065164162113536, 7039940342212366336, 'java.text.ParseException: Unparseable date: \"null900000\"', NULL, 0, NULL, '2023-03-13 23:19:11', NULL);
INSERT INTO `t_order_exception` VALUES (7041065164245999616, 7039940342212366336, 'java.text.ParseException: Unparseable date: \"null900000\"', NULL, 0, NULL, '2023-03-13 23:19:11', NULL);
INSERT INTO `t_order_exception` VALUES (7041065164325691392, 7039940342212366336, 'java.text.ParseException: Unparseable date: \"null900000\"', NULL, 0, NULL, '2023-03-13 23:19:11', NULL);
INSERT INTO `t_order_exception` VALUES (7041067220813914112, 7039940342212366336, 'java.text.ParseException: Unparseable date: \"null900000\"', NULL, 0, NULL, '2023-03-13 23:27:22', NULL);
INSERT INTO `t_order_exception` VALUES (7041067220960714752, 7039940342212366336, 'java.text.ParseException: Unparseable date: \"null900000\"', NULL, 0, NULL, '2023-03-13 23:27:22', NULL);
INSERT INTO `t_order_exception` VALUES (7041067221061378048, 7039940342212366336, 'java.text.ParseException: Unparseable date: \"null900000\"', NULL, 0, NULL, '2023-03-13 23:27:22', NULL);
INSERT INTO `t_order_exception` VALUES (7041067221145264128, 7039940342212366336, 'java.text.ParseException: Unparseable date: \"null900000\"', NULL, 0, NULL, '2023-03-13 23:27:22', NULL);
INSERT INTO `t_order_exception` VALUES (7041067221224955904, 7039940342212366336, 'java.text.ParseException: Unparseable date: \"null900000\"', NULL, 0, NULL, '2023-03-13 23:27:22', NULL);
INSERT INTO `t_order_exception` VALUES (7041067221308841984, 7039940342212366336, 'java.text.ParseException: Unparseable date: \"null900000\"', NULL, 0, NULL, '2023-03-13 23:27:22', NULL);
INSERT INTO `t_order_exception` VALUES (7041067221384339456, 7039940342212366336, 'java.text.ParseException: Unparseable date: \"null900000\"', NULL, 0, NULL, '2023-03-13 23:27:22', NULL);
INSERT INTO `t_order_exception` VALUES (7041070162040233984, 7039940342212366336, '订单已支付，请勿重复支付', NULL, 0, NULL, '2023-03-13 23:39:03', NULL);
INSERT INTO `t_order_exception` VALUES (7041070162178646016, 7039940342212366336, '订单已支付，请勿重复支付', NULL, 0, NULL, '2023-03-13 23:39:03', NULL);
INSERT INTO `t_order_exception` VALUES (7041070162245754880, 7039940342212366336, '订单已支付，请勿重复支付', NULL, 0, NULL, '2023-03-13 23:39:03', NULL);
INSERT INTO `t_order_exception` VALUES (7041070162317058048, 7039940342212366336, '订单已支付，请勿重复支付', NULL, 0, NULL, '2023-03-13 23:39:03', NULL);
INSERT INTO `t_order_exception` VALUES (7041070162388361216, 7039940342212366336, '订单已支付，请勿重复支付', NULL, 0, NULL, '2023-03-13 23:39:03', NULL);
INSERT INTO `t_order_exception` VALUES (7041070162522578944, 7039940342212366336, '订单已支付，请勿重复支付', NULL, 0, NULL, '2023-03-13 23:39:03', NULL);
INSERT INTO `t_order_exception` VALUES (7041070701486448640, 7039940342212366336, '订单已支付，请勿重复支付', NULL, 0, NULL, '2023-03-13 23:41:12', NULL);
INSERT INTO `t_order_exception` VALUES (7041070701616472064, 7039940342212366336, '订单已支付，请勿重复支付', NULL, 0, NULL, '2023-03-13 23:41:12', NULL);
INSERT INTO `t_order_exception` VALUES (7041070701746495488, 7039940342212366336, '订单已支付，请勿重复支付', NULL, 0, NULL, '2023-03-13 23:41:12', NULL);
INSERT INTO `t_order_exception` VALUES (7041070701813604352, 7039940342212366336, '订单已支付，请勿重复支付', NULL, 0, NULL, '2023-03-13 23:41:12', NULL);
INSERT INTO `t_order_exception` VALUES (7041070701880713216, 7039940342212366336, '订单已支付，请勿重复支付', NULL, 0, NULL, '2023-03-13 23:41:12', NULL);
INSERT INTO `t_order_exception` VALUES (7041070702010736640, 7039940342212366336, '订单已支付，请勿重复支付', NULL, 0, NULL, '2023-03-13 23:41:12', NULL);
INSERT INTO `t_order_exception` VALUES (7041070702073651200, 7039940342212366336, '订单已支付，请勿重复支付', NULL, 0, NULL, '2023-03-13 23:41:12', NULL);
INSERT INTO `t_order_exception` VALUES (7041070702140760064, 7039940342212366336, '订单已支付，请勿重复支付', NULL, 0, NULL, '2023-03-13 23:41:12', NULL);
INSERT INTO `t_order_exception` VALUES (7041073576434376704, 7039934242528333824, '订单已支付，请勿重复支付', NULL, 0, NULL, '2023-03-13 23:52:37', NULL);

-- ----------------------------
-- Table structure for t_poster
-- ----------------------------
DROP TABLE IF EXISTS `t_poster`;
CREATE TABLE `t_poster`  (
  `id` bigint(0) NOT NULL,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `upid` bigint(0) NULL DEFAULT NULL,
  `status` tinyint(1) NULL DEFAULT NULL,
  `createAt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_poster
-- ----------------------------
INSERT INTO `t_poster` VALUES (7041622765237088256, '狗屎2', 7041625067368931328, 1, NULL);
INSERT INTO `t_poster` VALUES (7041755062623973376, '自告奋勇', 7041755062695288832, 1, '2023-03-15 21:00:36');
INSERT INTO `t_poster` VALUES (7041788688971509760, '黑恶黑可那', 7041788688971522049, 1, '2023-03-15 23:14:13');
INSERT INTO `t_poster` VALUES (7041961218638262272, '夹', 7041961218659246080, 0, '2023-03-16 10:39:47');
INSERT INTO `t_poster` VALUES (7041961468639752192, '夹', 7041961468639764481, 0, '2023-03-16 10:40:47');
INSERT INTO `t_poster` VALUES (7041962958255206400, '夹', 7041962958255218689, 0, '2023-03-16 10:46:42');
INSERT INTO `t_poster` VALUES (7041999185457098752, '夹', 7041999185528414208, 0, '2023-03-16 13:10:39');

-- ----------------------------
-- Table structure for t_registration
-- ----------------------------
DROP TABLE IF EXISTS `t_registration`;
CREATE TABLE `t_registration`  (
  `id` bigint(0) NOT NULL,
  `aid` bigint(0) NULL DEFAULT NULL,
  `uid` bigint(0) NULL DEFAULT NULL,
  `createAt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_registration
-- ----------------------------
INSERT INTO `t_registration` VALUES (23, 123, 55, '2023-02-22 20:49:56');
INSERT INTO `t_registration` VALUES (24, 12, 55, '2023-02-22 20:50:05');
INSERT INTO `t_registration` VALUES (7040166960486858752, 7039908092838023168, 55, '2023-03-11 11:50:03');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` bigint(0) NOT NULL,
  `wid` bigint(0) NULL DEFAULT NULL,
  `value` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createAt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (66, 76, 'dffsaf', '2023-02-23 13:26:41');
INSERT INTO `t_role` VALUES (111, 777, 'sdfsf', '2023-02-23 13:26:52');
INSERT INTO `t_role` VALUES (7040176331266641920, 7040176331241488384, 'ROLE_WORKER', '2023-03-11 12:27:17');

-- ----------------------------
-- Table structure for t_upload
-- ----------------------------
DROP TABLE IF EXISTS `t_upload`;
CREATE TABLE `t_upload`  (
  `id` bigint(0) NOT NULL,
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `suffix` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_upload
-- ----------------------------
INSERT INTO `t_upload` VALUES (7035494182374531072, 'yanyu', 'jpg');
INSERT INTO `t_upload` VALUES (7035494435643383808, 'HS', 'png');
INSERT INTO `t_upload` VALUES (7039967933707247616, 'p', 'png');
INSERT INTO `t_upload` VALUES (7039968020160241664, 'p', 'png');
INSERT INTO `t_upload` VALUES (7039968606662352896, 'p', 'png');
INSERT INTO `t_upload` VALUES (7039968617747898368, 'p', 'png');
INSERT INTO `t_upload` VALUES (7039968628510482432, 'p', 'png');
INSERT INTO `t_upload` VALUES (7039968629462589440, 'p', 'png');
INSERT INTO `t_upload` VALUES (7039968630125289472, 'p', 'png');
INSERT INTO `t_upload` VALUES (7039968630746046464, 'p', 'png');
INSERT INTO `t_upload` VALUES (7039968631404552192, 'p', 'png');
INSERT INTO `t_upload` VALUES (7039968632159526912, 'p', 'png');
INSERT INTO `t_upload` VALUES (7039968633010970624, 'p', 'png');
INSERT INTO `t_upload` VALUES (7039968633694642176, 'p', 'png');
INSERT INTO `t_upload` VALUES (7039968634403479552, 'p', 'png');
INSERT INTO `t_upload` VALUES (7039968635204591616, 'p', 'png');
INSERT INTO `t_upload` VALUES (7039968636014092288, 'p', 'png');
INSERT INTO `t_upload` VALUES (7039968636785844224, 'p', 'png');
INSERT INTO `t_upload` VALUES (7039968637515653120, 'p', 'png');
INSERT INTO `t_upload` VALUES (7039968638279016448, 'p', 'png');
INSERT INTO `t_upload` VALUES (7039968639042379776, 'p', 'png');
INSERT INTO `t_upload` VALUES (7039983240677089280, 'p', 'png');
INSERT INTO `t_upload` VALUES (7040680559982600192, 'HS', 'png');
INSERT INTO `t_upload` VALUES (7041365954554683392, 'HS', 'jpg');
INSERT INTO `t_upload` VALUES (7041366510895554560, 'HS', 'jpg');
INSERT INTO `t_upload` VALUES (7041368729908862976, 'HS', 'png');
INSERT INTO `t_upload` VALUES (7041605145699999744, 'HS', 'jpg');
INSERT INTO `t_upload` VALUES (7041608774087532544, 'cahsho', 'jpg');
INSERT INTO `t_upload` VALUES (7041625067368931328, 'poster', 'jpg');
INSERT INTO `t_upload` VALUES (7041755062695288832, 'poster', 'jpg');
INSERT INTO `t_upload` VALUES (7041788688971522049, 'poster', 'png');
INSERT INTO `t_upload` VALUES (7041961218659246080, 'poster', 'png');
INSERT INTO `t_upload` VALUES (7041961468639764481, 'poster', 'png');
INSERT INTO `t_upload` VALUES (7041962958255218689, 'poster', 'png');
INSERT INTO `t_upload` VALUES (7041999185528414208, 'poster', 'jpg');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(0) NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nickname` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `gender` int(0) NULL DEFAULT NULL,
  `info` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `upid` bigint(0) NULL DEFAULT NULL,
  `createAt` datetime(0) NULL DEFAULT NULL,
  `updateAt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `t_user_pk`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (55, 'user', '123456', 'ooo', '1934242815@qq.com', '2023-02-09', 1, 'waw', 12121, '2023-02-21 12:30:08', '2023-02-21 12:30:11');
INSERT INTO `t_user` VALUES (7035494435714691072, 'laocai', '$2a$10$sQRF90j9SdUpo.uDIyrDAOoUhtuIb9VOvJPvme7k9j2QGCSyWaPye', '老此', '1934242815@qq.com', '1999-09-11', 1, NULL, 7035494435643383808, '2023-02-26 14:23:06', '2023-02-26 14:23:06');
INSERT INTO `t_user` VALUES (7040680559986798592, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 7040680559982600192, '2023-03-12 21:50:55', '2023-03-12 21:50:55');
INSERT INTO `t_user` VALUES (7041365954621796352, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 7041365954554683392, '2023-03-14 19:14:25', '2023-03-14 19:14:25');
INSERT INTO `t_user` VALUES (7041366510895558657, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 7041366510895554560, '2023-03-14 19:16:38', '2023-03-14 19:16:38');
INSERT INTO `t_user` VALUES (7041368731582394368, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 7041368729908862976, '2023-03-14 19:25:27', '2023-03-14 19:25:27');
INSERT INTO `t_user` VALUES (7041605145771307008, 'xxx', '123456', '仨', '111', '1999-09-11', 1, NULL, 7041605145699999744, '2023-03-15 11:04:53', '2023-03-15 11:04:53');

-- ----------------------------
-- Table structure for t_worker
-- ----------------------------
DROP TABLE IF EXISTS `t_worker`;
CREATE TABLE `t_worker`  (
  `id` bigint(0) NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nickname` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` int(0) NULL DEFAULT NULL,
  `phone` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `entry` tinyint(1) NULL DEFAULT NULL,
  `department` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createAt` datetime(0) NULL DEFAULT NULL,
  `updateAt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_worker
-- ----------------------------
INSERT INTO `t_worker` VALUES (88, 'strinqgqqqqq', 'string', 'string', 0, 'string', 'string', 1, 'string', NULL, '2023-03-11 12:32:50');
INSERT INTO `t_worker` VALUES (7040176331241488384, 'string', 'string', 'string', 0, 'string', 'string', 1, 'string', '2023-03-11 12:27:17', '2023-03-11 12:27:17');

-- ----------------------------
-- Table structure for t_worker_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `t_worker_evaluate`;
CREATE TABLE `t_worker_evaluate`  (
  `id` bigint(0) NOT NULL,
  `wid` bigint(0) NULL DEFAULT NULL,
  `uid` bigint(0) NULL DEFAULT NULL,
  `content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  `createAt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_worker_evaluate
-- ----------------------------
INSERT INTO `t_worker_evaluate` VALUES (7040183343115341824, 7040176331241488384, 55, 'string', 0, '2023-03-11 12:55:09');

SET FOREIGN_KEY_CHECKS = 1;
