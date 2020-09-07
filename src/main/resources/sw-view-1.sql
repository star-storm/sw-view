/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50624
 Source Host           : localhost:3306
 Source Schema         : sw-view

 Target Server Type    : MySQL
 Target Server Version : 50624
 File Encoding         : 65001

 Date: 29/05/2020 15:56:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for o_dict
-- ----------------------------
DROP TABLE IF EXISTS `o_dict`;
CREATE TABLE `o_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据名',
  `value` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据值',
  `type` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `descrip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `del_flg` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for s_log
-- ----------------------------
DROP TABLE IF EXISTS `s_log`;
CREATE TABLE `s_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `model_id` int(2) NULL DEFAULT NULL COMMENT '模块名称',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '操作人',
  `option_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `options` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作内容',
  `result` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结果',
  `del_flg` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_log
-- ----------------------------
INSERT INTO `s_log` VALUES (1, NULL, 1, '2020-05-14 14:16:34', '用户名密码登录系统', '登录成功', '1', '0');
INSERT INTO `s_log` VALUES (2, NULL, 370, '2020-05-15 13:40:43', '用户名密码登录系统', '登录成功', '1', '0');
INSERT INTO `s_log` VALUES (3, NULL, 1, '2020-05-22 23:01:23', '用户名密码登录系统', '登录成功', '1', '0');
INSERT INTO `s_log` VALUES (4, NULL, 1, '2020-05-25 10:22:52', '用户名密码登录系统', '登录成功', '1', '0');

-- ----------------------------
-- Table structure for s_org
-- ----------------------------
DROP TABLE IF EXISTS `s_org`;
CREATE TABLE `s_org`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织名称',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `pid` int(11) NOT NULL COMMENT '父ID',
  `p_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父编码',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `is_parent` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否包含子节点(0:是,1:否)',
  `del_flg` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 159 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_org
-- ----------------------------
INSERT INTO `s_org` VALUES (1, '组织', '0', -1, '-1', 1, '0', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `s_org` VALUES (143, '北京市', 'bjs', 1, NULL, 1, '0', '0', 1, '2020-05-09 00:56:39', NULL, '2020-05-09 00:56:39', '');
INSERT INTO `s_org` VALUES (144, '北京市委', 'bjsw', 143, NULL, 1, '0', '0', 1, '2020-05-09 00:56:42', NULL, '2020-05-09 00:56:42', '');
INSERT INTO `s_org` VALUES (145, '北京市政府', 'bjszf', 143, NULL, 2, '0', '0', 1, '2020-05-09 00:57:28', NULL, NULL, '');
INSERT INTO `s_org` VALUES (146, '机要局', 'jyj', 144, NULL, 1, '0', '0', 1, '2020-05-09 00:58:51', NULL, NULL, '');
INSERT INTO `s_org` VALUES (147, '测试市委子部门1', 'testswsub1', 144, NULL, 2, '0', '1', 1, '2020-05-09 01:18:12', 1, '2020-05-09 15:49:53', '');
INSERT INTO `s_org` VALUES (148, '测试市委子部门2', 'testswsub2', 144, NULL, 3, '0', '1', 1, '2020-05-09 01:18:12', 1, '2020-05-09 15:49:53', '');
INSERT INTO `s_org` VALUES (149, '测试市委子部门3', 'testswsub3', 144, NULL, 4, '0', '1', 1, '2020-05-09 01:18:12', 1, '2020-05-09 15:49:53', '');
INSERT INTO `s_org` VALUES (150, '测试市委子部门1-孙部门1-up', 'testswsub1-sub1-up', 147, NULL, 3, '0', '1', 1, '2020-05-09 01:19:37', 1, '2020-05-09 15:49:53', '');
INSERT INTO `s_org` VALUES (151, '测试市委子部门1-孙部门2', 'testswsub1-sub2', 147, NULL, 2, '0', '1', 1, '2020-05-09 01:18:12', 1, '2020-05-09 15:49:53', '');
INSERT INTO `s_org` VALUES (152, '测试机构', 'test', 144, NULL, 2, '0', '1', 1, '2020-05-09 15:52:12', 1, '2020-05-09 15:53:00', '');
INSERT INTO `s_org` VALUES (153, '北京市', '28ebe6e2a7004923b0ca29380284718c', 0, '0', 0, '0', '0', -1, '2020-05-15 13:23:07', NULL, NULL, NULL);
INSERT INTO `s_org` VALUES (154, '中共北京市委', '6a2a70f4d58a4a198e61de639bf3691d', 153, '28ebe6e2a7004923b0ca29380284718c', 1, '0', '0', -1, '2020-05-15 13:23:25', NULL, NULL, NULL);
INSERT INTO `s_org` VALUES (155, '市委办公厅', '0ba7c242488b4e2abd16e8cadd1d2352', 154, '6a2a70f4d58a4a198e61de639bf3691d', 3, '0', '0', -1, '2020-05-15 13:23:34', NULL, NULL, NULL);
INSERT INTO `s_org` VALUES (156, '机要局', 'ff0a1ef15c03484198368ff8c658bf22', 155, '0ba7c242488b4e2abd16e8cadd1d2352', 2, '0', '0', -1, '2020-05-15 13:23:40', NULL, NULL, NULL);
INSERT INTO `s_org` VALUES (157, '信息化运维', '261b20a6e32b4ca18a453c6632360e73', 156, 'ff0a1ef15c03484198368ff8c658bf22', 11, '0', '0', -1, '2020-05-15 13:23:58', NULL, NULL, NULL);
INSERT INTO `s_org` VALUES (158, '太极公司', '210e023e62044dd0a210e45a19877fd5', 157, '261b20a6e32b4ca18a453c6632360e73', 99999, '0', '0', -1, '2020-05-15 13:24:08', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for s_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_permission`;
CREATE TABLE `s_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `type` int(1) NULL DEFAULT NULL COMMENT '类别',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限URL',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `descrip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `del_flg` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_permission
-- ----------------------------
INSERT INTO `s_permission` VALUES (1, 'permission', NULL, NULL, NULL, '权限管理', '0', NULL, '2020-05-07 16:44:43', NULL, '2020-05-07 16:44:43', NULL);
INSERT INTO `s_permission` VALUES (2, 'role', NULL, NULL, NULL, '角色管理', '0', 1, '2020-05-07 17:30:20', 1, '2020-05-07 17:30:20', NULL);
INSERT INTO `s_permission` VALUES (3, 'user', NULL, NULL, NULL, '用户管理', '0', 1, '2020-05-07 17:30:24', 1, '2020-05-07 17:30:24', NULL);
INSERT INTO `s_permission` VALUES (4, 'org', NULL, NULL, NULL, '部门管理', '0', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `degree` int(11) NULL DEFAULT NULL COMMENT '角色等级',
  `descrip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `del_flg` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES (1, '管理员', NULL, '管理员角色', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `s_role` VALUES (2, '用户角色', NULL, '用户角色', '0', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for s_role_per
-- ----------------------------
DROP TABLE IF EXISTS `s_role_per`;
CREATE TABLE `s_role_per`  (
  `rid` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `pid` int(11) NULL DEFAULT NULL COMMENT '权限ID',
  `del_flg` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  INDEX `rid`(`rid`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE,
  CONSTRAINT `s_role_per_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `s_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `s_role_per_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `s_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_role_per
-- ----------------------------
INSERT INTO `s_role_per` VALUES (1, 1, '0', NULL, '2020-05-08 22:02:13', NULL, '2020-05-08 22:02:13', NULL);
INSERT INTO `s_role_per` VALUES (1, 2, '0', NULL, '2020-05-08 22:02:13', NULL, '2020-05-08 22:02:13', NULL);
INSERT INTO `s_role_per` VALUES (1, 3, '0', NULL, '2020-05-08 22:02:13', NULL, '2020-05-08 22:02:13', NULL);
INSERT INTO `s_role_per` VALUES (1, 4, '0', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `user_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户唯一标识',
  `login_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录名',
  `user_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `org_id` int(11) NULL DEFAULT NULL COMMENT '组织ID',
  `org_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织唯一标识',
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织名称',
  `del_flg` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 368 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES (1, '管理员', 'admin', 'admin', '0', -1, '-1', NULL, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `s_user` VALUES (365, '测试用户权限用户', 'testuserper-user', 'testuserper-user', '075091074065079095', 144, 'bjsw', '北京市委', '1', 1, '2020-05-09 02:40:27', 1, '2020-05-09 16:02:35', NULL);
INSERT INTO `s_user` VALUES (366, '测试角色权限用户', 'testuserper-role', 'testuserper-role', '075091074065079095', 146, 'jyj', '机要局', '1', 1, '2020-05-09 16:00:20', 1, '2020-05-09 16:02:35', NULL);
INSERT INTO `s_user` VALUES (367, '测试权限权限用户', 'testuserper-per', 'testuserper-per', '075091074065079095', 145, 'bjszf', '北京市政府', '1', 1, '2020-05-09 14:43:28', 1, '2020-05-09 16:02:35', NULL);

-- ----------------------------
-- Table structure for s_user_role
-- ----------------------------
DROP TABLE IF EXISTS `s_user_role`;
CREATE TABLE `s_user_role`  (
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `rid` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `del_flg` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  INDEX `uid`(`uid`) USING BTREE,
  INDEX `rid`(`rid`) USING BTREE,
  CONSTRAINT `s_user_role_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `s_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `s_user_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `s_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_user_role
-- ----------------------------
INSERT INTO `s_user_role` VALUES (1, 1, '0', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_app_info
-- ----------------------------
DROP TABLE IF EXISTS `t_app_info`;
CREATE TABLE `t_app_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `owner` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属者',
  `pid` int(11) NULL DEFAULT NULL COMMENT '权限ID',
  `del_flg` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成的唯一文件名',
  `ori_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原始文件名',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `del_flg` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_file_ref
-- ----------------------------
DROP TABLE IF EXISTS `t_file_ref`;
CREATE TABLE `t_file_ref`  (
  `fid` int(11) NULL DEFAULT NULL COMMENT '文件ID',
  `refid` int(11) NULL DEFAULT NULL COMMENT '关联字段ID',
  `type` int(1) NULL DEFAULT NULL COMMENT '类别',
  `del_flg` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  INDEX `fid`(`fid`) USING BTREE,
  INDEX `refid`(`refid`) USING BTREE,
  CONSTRAINT `t_file_ref_ibfk_1` FOREIGN KEY (`fid`) REFERENCES `t_file` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Function structure for org_children
-- ----------------------------
DROP FUNCTION IF EXISTS `org_children`;
delimiter ;;
CREATE FUNCTION `org_children`(rootId varchar(36))
 RETURNS varchar(1000) CHARSET utf8
BEGIN
    DECLARE ptemp VARCHAR(1000);
    DECLARE ctemp VARCHAR(1000);
    DECLARE cnt INTEGER;
    SET ptemp = '';
    SET ctemp = rootId;
		SET cnt = 0;
		SET cnt=(SELECT COUNT(*) FROM s_org WHERE id = rootId);
		IF cnt = 0 THEN
			RETURN '';
		END IF;
    WHILE ctemp IS NOT NULL DO
      SET ptemp = concat(ptemp, ',', ctemp);
      SELECT group_concat(id)
      INTO ctemp
      FROM s_org
      WHERE FIND_IN_SET(pid, ctemp) > 0;
    END WHILE;
    RETURN ptemp;
  END
;;
delimiter ;

-- ----------------------------
-- Function structure for org_parent
-- ----------------------------
DROP FUNCTION IF EXISTS `org_parent`;
delimiter ;;
CREATE FUNCTION `org_parent`(p varchar(100))
 RETURNS varchar(1000) CHARSET utf8
BEGIN   
DECLARE fid varchar(100) default '';   
DECLARE str varchar(1000) default p;   
  
WHILE p <> 0  do   
    SET fid =(SELECT pId FROM s_org WHERE id = p);
		IF fid <> 0 THEN
			SET str = fid;
		END IF;
		SET p = fid;
END WHILE;   
return str;  
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
