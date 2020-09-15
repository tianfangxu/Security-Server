/*
 Navicat Premium Data Transfer

 Source Server         : location
 Source Server Type    : MySQL
 Source Server Version : 50561
 Source Host           : localhost:3306
 Source Schema         : server

 Target Server Type    : MySQL
 Target Server Version : 50561
 File Encoding         : 65001

 Date: 14/09/2020 19:52:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sy_dept
-- ----------------------------
DROP TABLE IF EXISTS `sy_dept`;
CREATE TABLE `sy_dept`  (
  `id` int(11) NOT NULL,
  `dept_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dept_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT NULL,
  `level` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `create_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `update_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delflag` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sy_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sy_resource
-- ----------------------------
DROP TABLE IF EXISTS `sy_resource`;
CREATE TABLE `sy_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源路径或者名称',
  `sort` int(3) NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父id',
  `is_leaf` tinyint(1) NULL DEFAULT NULL COMMENT '是否是叶子',
  `type` int(2) NULL DEFAULT NULL COMMENT '类型：资源页、方法、',
  `state` tinyint(1) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `create_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `update_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delflag` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sy_resource
-- ----------------------------
INSERT INTO `sy_resource` VALUES ('1', 'SYSTEM', '系统管理', null, '1', null, '0', '1', '1', '2020-08-29 22:44:04', '0', '2020-08-29 22:44:08', '0', '1');
INSERT INTO `sy_resource` VALUES ('2', 'USER', '用户管理', null, '1', '1', '1', '1', '1', '2020-08-29 22:45:10', '0', '2020-08-29 22:45:13', '0', '1');
INSERT INTO `sy_resource` VALUES ('3', 'ROLE', '角色管理', '', '2', '1', '1', '1', '1', '2020-08-29 22:45:10', '0', '2020-08-29 22:45:13', '0', '1');
INSERT INTO `sy_resource` VALUES ('4', 'DEPT', '部门管理', '', '3', '1', '1', '1', '1', '2020-08-29 22:45:10', '0', '2020-08-29 22:45:13', '0', '1');
INSERT INTO `sy_resource` VALUES ('5', 'RESOURCE', '资源管理', '', '4', '1', '1', '1', '1', '2020-08-29 22:45:10', '0', '2020-08-29 22:45:13', '0', '1');
INSERT INTO `sy_resource` VALUES ('6', 'queryUser', '管理系统.用户.查询', '/queryUser', '1', null, null, '2', '1', '2020-08-29 22:53:13', '0', '2020-08-29 22:53:21', '0', '1');
INSERT INTO `sy_resource` VALUES ('7', 'STRUK', '下级机构', null, '1', '4', '1', '1', '1', '2020-09-01 22:09:30', '0', '2020-09-01 22:09:34', '0', '1');
INSERT INTO `sy_resource` VALUES ('8', 'insertUser', '管理系统.用户.新增', '/insertUser', '2', null, null, '2', '1', '2020-09-03 15:15:18', '0', '2020-09-03 15:15:25', '0', '1');
INSERT INTO `sy_resource` VALUES ('9', 'queryRole', '管理系统.角色.查询', '/queryRole', '3', null, null, '2', '1', '2020-09-04 21:52:51', '0', '2020-09-04 21:52:54', '0', '1');
INSERT INTO `sy_resource` VALUES ('10', 'queryResource', '管理系统.资源.查询', '/queryResource', '4', null, null, '2', '1', '2020-09-04 21:52:51', '0', '2020-09-04 21:52:51', '0', '1');
INSERT INTO `sy_resource` VALUES ('11', 'insertResource', '管理系统.资源.新增', '/insertResource', '5', null, null, '2', '1', '2020-09-04 21:52:51', '0', '2020-09-04 21:52:51', '0', '1');
INSERT INTO `sy_resource` VALUES ('12', 'insertRole', '管理系统.角色.新增', '/insertRole', '6', null, '0', '2', '0', '2020-09-05 15:39:24', '0', null, null, '1');
INSERT INTO `sy_resource` VALUES ('13', 'queryRoleResource', '管理系统.资源.查询角色绑定的资源', '/queryRoleResource', '7', null, '0', '2', '0', '2020-09-05 17:36:10', '0', null, null, '1');
INSERT INTO `sy_resource` VALUES ('14', 'batchInsertRoleResource', '管理系统.资源.新增角色绑定的资源', '/batchInsertRoleResource', '8', null, '0', '2', '0', '2020-09-05 17:36:10', '0', '2020-09-05 23:13:46', '0', '1');
INSERT INTO `sy_resource` VALUES ('15', 'batchDeleteRoleResource', '管理系统.资源.删除角色绑定的资源', '/batchDeleteRoleResource', '9', null, '0', '2', '0', '2020-09-05 17:36:10', '0', '2020-09-05 23:13:49', '0', '1');
INSERT INTO `sy_resource` VALUES ('16', 'batchUpdateRoleResource', '管理系统.资源.更新角色绑定的资源', '/batchUpdateRoleResource', '10', null, '0', '2', '0', '2020-09-05 17:36:10', '0', '2020-09-05 23:13:49', '0', '1');
INSERT INTO `sy_resource` VALUES ('17', 'queryRoleByUserId', '管理系统.角色.查询用户绑定的角色', '/queryRoleByUserId', '11', null, '0', '2', '0', '2020-09-08 21:15:17', '1', null, null, '1');
INSERT INTO `sy_resource` VALUES ('18', 'updateUser', '管理系统.用户.更新', '/updateUser', '12', null, '0', '2', '0', '2020-09-12 15:03:22', '1', null, null, '1');
INSERT INTO `sy_resource` VALUES ('19', 'resetPassword', '管理系统.用户.重置密码', '/resetPassword', '13', null, '0', '2', '0', '2020-09-12 16:18:57', '1', null, null, '1');

-- ----------------------------
-- Table structure for sy_role
-- ----------------------------
DROP TABLE IF EXISTS `sy_role`;
CREATE TABLE `sy_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色标识：ADMIN..',
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime NULL DEFAULT NULL,
  `create_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `update_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delflag` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sy_role
-- ----------------------------
INSERT INTO `sy_role` VALUES (1, 'admin', '超级管理员', '拥有系统所有权限', '2020-08-29 22:56:25', '0', '2020-08-29 22:56:29', '0', 1);
INSERT INTO `sy_role` VALUES (2, 'common', '普通用户', '只有查询系统部分数据权限，没有修改权限', '2020-09-04 13:07:14', '0', '2020-09-04 13:07:19', '0', 1);
INSERT INTO `sy_role` VALUES (3, 'manage', '管理员', '拥有部分系统查询和修改的权限', '2020-09-05 16:08:23', '0', NULL, NULL, 1);

-- ----------------------------
-- Table structure for sy_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sy_role_resource`;
CREATE TABLE `sy_role_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NOT NULL COMMENT '角色主键',
  `resource_id` int(11) NOT NULL COMMENT '资源主键',
  `create_time` datetime NULL DEFAULT NULL,
  `create_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `update_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delflag` int(5) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sy_role_resource
-- ----------------------------
INSERT INTO `sy_role_resource` VALUES (1, 1, 1, '2020-08-29 22:59:17', '0', '2020-08-29 22:59:21', '0', 1);
INSERT INTO `sy_role_resource` VALUES (2, 1, 2, '2020-08-29 22:59:17', '0', '2020-08-29 22:59:21', '0', 1);
INSERT INTO `sy_role_resource` VALUES (3, 1, 3, '2020-08-29 22:59:17', '0', '2020-08-29 22:59:21', '0', 1);
INSERT INTO `sy_role_resource` VALUES (4, 1, 4, '2020-08-29 22:59:17', '0', '2020-08-29 22:59:21', '0', 1);
INSERT INTO `sy_role_resource` VALUES (5, 1, 5, '2020-08-29 22:59:17', '0', '2020-08-29 22:59:21', '0', 1);
INSERT INTO `sy_role_resource` VALUES (6, 1, 6, '2020-08-29 22:59:17', '0', '2020-08-29 22:59:21', '0', 1);
INSERT INTO `sy_role_resource` VALUES (8, 1, 7, '2020-08-29 22:59:17', '0', '2020-08-29 22:59:21', '0', 1);
INSERT INTO `sy_role_resource` VALUES (9, 1, 8, '2020-08-29 22:59:17', '0', '2020-08-29 22:59:21', '0', 1);
INSERT INTO `sy_role_resource` VALUES (10, 1, 9, '2020-08-29 22:59:17', '0', '2020-08-29 22:59:21', '0', 1);
INSERT INTO `sy_role_resource` VALUES (11, 1, 10, '2020-08-29 22:59:17', '0', '2020-08-29 22:59:21', '0', 1);
INSERT INTO `sy_role_resource` VALUES (12, 1, 11, '2020-08-29 22:59:17', '0', '2020-08-29 22:59:21', '0', 1);
INSERT INTO `sy_role_resource` VALUES (14, 1, 12, '2020-08-29 22:59:17', '0', '2020-08-29 22:59:17', '0', 1);
INSERT INTO `sy_role_resource` VALUES (18, 1, 13, '2020-08-29 22:59:17', '0', '2020-08-29 22:59:17', '0', 1);
INSERT INTO `sy_role_resource` VALUES (19, 1, 14, '2020-08-29 22:59:17', '0', '2020-08-29 22:59:21', '0', 1);
INSERT INTO `sy_role_resource` VALUES (21, 1, 15, '2020-08-29 22:59:17', '0', '2020-08-29 22:59:21', '0', 1);
INSERT INTO `sy_role_resource` VALUES (27, 1, 16, '2020-09-05 23:29:36', '0', NULL, NULL, 1);
INSERT INTO `sy_role_resource` VALUES (61, 2, 6, '2020-09-05 23:51:15', '1', NULL, NULL, 1);
INSERT INTO `sy_role_resource` VALUES (62, 2, 9, '2020-09-05 23:51:15', '1', NULL, NULL, 1);
INSERT INTO `sy_role_resource` VALUES (63, 2, 10, '2020-09-05 23:51:15', '1', NULL, NULL, 1);
INSERT INTO `sy_role_resource` VALUES (64, 2, 13, '2020-09-05 23:51:15', '1', NULL, NULL, 1);
INSERT INTO `sy_role_resource` VALUES (65, 1, 17, '2020-09-08 21:15:22', '1', NULL, NULL, 1);
INSERT INTO `sy_role_resource` VALUES (66, 1, 18, '2020-09-12 15:03:32', '1', NULL, NULL, 1);
INSERT INTO `sy_role_resource` VALUES (67, 1, 19, '2020-09-12 16:19:04', '1', NULL, NULL, 1);
INSERT INTO `sy_role_resource` VALUES (68, 2, 17, '2020-09-12 16:28:40', '1', NULL, NULL, 1);
INSERT INTO `sy_role_resource` VALUES (69, 3, 6, '2020-09-12 16:36:47', '1', NULL, NULL, 1);
INSERT INTO `sy_role_resource` VALUES (70, 3, 8, '2020-09-12 16:36:47', '1', NULL, NULL, 1);
INSERT INTO `sy_role_resource` VALUES (71, 3, 9, '2020-09-12 16:36:47', '1', NULL, NULL, 1);
INSERT INTO `sy_role_resource` VALUES (72, 3, 10, '2020-09-12 16:36:47', '1', NULL, NULL, 1);
INSERT INTO `sy_role_resource` VALUES (73, 3, 17, '2020-09-12 16:36:47', '1', NULL, NULL, 1);
INSERT INTO `sy_role_resource` VALUES (74, 3, 19, '2020-09-12 16:36:47', '1', NULL, NULL, 1);

-- ----------------------------
-- Table structure for sy_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sy_role_user`;
CREATE TABLE `sy_role_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NOT NULL COMMENT '部门主键',
  `user_id` int(11) NOT NULL COMMENT '资源主键',
  `create_time` datetime NULL DEFAULT NULL,
  `create_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `update_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delflag` int(5) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sy_role_user
-- ----------------------------
INSERT INTO `sy_role_user` VALUES (10, 1, 6, '2020-09-04 20:45:34', '0', NULL, NULL, 1);
INSERT INTO `sy_role_user` VALUES (15, 2, 5, '2020-09-12 15:51:32', '1', NULL, NULL, 1);
INSERT INTO `sy_role_user` VALUES (16, 2, 4, '2020-09-12 15:51:37', '1', NULL, NULL, 1);
INSERT INTO `sy_role_user` VALUES (17, 2, 3, '2020-09-12 15:51:41', '1', NULL, NULL, 1);
INSERT INTO `sy_role_user` VALUES (18, 2, 2, '2020-09-12 15:51:47', '1', NULL, NULL, 1);
INSERT INTO `sy_role_user` VALUES (21, 1, 1, '2020-09-12 16:13:14', '1', NULL, NULL, 1);

-- ----------------------------
-- Table structure for sy_user
-- ----------------------------
DROP TABLE IF EXISTS `sy_user`;
CREATE TABLE `sy_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆账号',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `real_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` tinyint(1) NULL DEFAULT NULL,
  `locked` tinyint(1) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `create_user` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `update_user` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delflag` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sy_user
-- ----------------------------
INSERT INTO `sy_user` VALUES (1, 'admin', '$2a$10$.sqa6ghadn5mVO4t1OcJ7uN8cqCw9C1Zs5MEKcGBGfaaljslVgdUi', '田方旭', NULL, '18130570156', '15755502377@163.com', 1, 0, '2020-08-29 22:31:12', '0', '2020-09-12 16:13:14', '1', 1);
INSERT INTO `sy_user` VALUES (2, 'zhangsan', '$2a$10$zAoj9EwvbXwn/.1uzR85kOD5HamWupOtI8TzLZAhBBqwbr/M.GMQG', '张三', NULL, '454812415', '1564564@163.com', 1, 0, '2020-09-04 20:42:55', '0', '2020-09-12 16:21:12', '1', 1);
INSERT INTO `sy_user` VALUES (3, 'lisi', '$2a$10$L.Z0qdpujsMIN66A5EI1EuK6DU2zZ8LcZFK73L45s/5cFOHmEyRSG', '李四', NULL, '198484868546', '198484868546@qq.com', 0, 0, '2020-09-04 20:44:40', '0', '2020-09-12 15:51:41', '1', 1);
INSERT INTO `sy_user` VALUES (4, 'wangwu', '$2a$10$edhctyI5urWOcZbhbGDiSuX0ok7vdRTpgYUpYKxfhf.1OgIlmT0Qy', '王五', NULL, '38468486464', '38468486464@163.com', 1, 0, '2020-09-04 20:45:09', '0', '2020-09-12 15:51:37', '1', 1);
INSERT INTO `sy_user` VALUES (5, 'zhaoliu', '$2a$10$poF7v3HWGcTAvfuA7LNuruB99Dn8otasgOJrY1beNpedJE2OzLolm', '赵六', NULL, '457685489', '457685489@qq.com', 1, 0, '2020-09-04 20:45:34', '0', '2020-09-12 16:21:27', '1', 1);
INSERT INTO `sy_user` VALUES (6, 'nareto', '$2a$10$lNtKCSJ4ismbtAufS2P9XeUdyDGTFCgv9GdMIz.OkY.LDJXx511nS', '鸣人', NULL, '18130157026', '18130157026@qq.com', 1, 0, '2020-09-04 18:13:58', '0', NULL, NULL, 1);

SET FOREIGN_KEY_CHECKS = 1;
