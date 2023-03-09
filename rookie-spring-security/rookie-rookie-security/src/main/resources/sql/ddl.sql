/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50527
 Source Host           : localhost:3306
 Source Schema         : demo_spring_security

 Target Server Type    : MySQL
 Target Server Version : 50527
 File Encoding         : 65001

 Date: 10/08/2021 16:07:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                         `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
                         `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
                         `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user password:user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'root', '$2a$10$a7H4/4wMnhWOrpOEeT8pkeJcDXyw8QROZ5HZzKWSnEtoGKNuKbu2y', 'ADMIN');
INSERT INTO `user` VALUES (2, 'user', '$2a$10$a7H4/4wMnhWOrpOEeT8pkeJcDXyw8QROZ5HZzKWSnEtoGKNuKbu2y', 'USER');

SET FOREIGN_KEY_CHECKS = 1;
