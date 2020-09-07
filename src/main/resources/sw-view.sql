/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : sw-view

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2020-05-09 16:04:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for o_dict
-- ----------------------------
DROP TABLE IF EXISTS `o_dict`;
CREATE TABLE `o_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(120) DEFAULT NULL COMMENT '数据名',
  `value` varchar(120) DEFAULT NULL COMMENT '数据值',
  `type` varchar(120) NOT NULL COMMENT '类型',
  `descrip` varchar(255) DEFAULT NULL COMMENT '描述',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `del_flg` char(1) DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COMMENT='数据字典表';

-- ----------------------------
-- Records of o_dict
-- ----------------------------

-- ----------------------------
-- Table structure for s_log
-- ----------------------------
DROP TABLE IF EXISTS `s_log`;
CREATE TABLE `s_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `model_id` int(2) DEFAULT NULL COMMENT '模块名称',
  `user_id` int(11) DEFAULT NULL COMMENT '操作人',
  `option_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `options` varchar(255) DEFAULT NULL COMMENT '操作',
  `content` varchar(255) DEFAULT NULL COMMENT '操作内容',
  `result` char(1) DEFAULT NULL COMMENT '结果',
  `del_flg` char(1) DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_log
-- ----------------------------

-- ----------------------------
-- Table structure for s_org
-- ----------------------------
DROP TABLE IF EXISTS `s_org`;
CREATE TABLE `s_org` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(120) DEFAULT NULL COMMENT '组织名称',
  `code` varchar(32) DEFAULT NULL COMMENT '编码',
  `pid` int(11) NOT NULL COMMENT '父ID',
  `p_code` varchar(32) DEFAULT NULL COMMENT '父编码',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `is_parent` char(1) DEFAULT '0' COMMENT '是否包含子节点(0:是,1:否)',
  `del_flg` char(1) DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8 COMMENT='组织信息表';

-- ----------------------------
-- Records of s_org
-- ----------------------------
INSERT INTO `s_org` VALUES ('1', '组织', '0', '-1', '-1', '1', '0', '0', null, null, null, null, null);
INSERT INTO `s_org` VALUES ('143', '北京市', 'bjs', '1', null, '1', '0', '0', '1', '2020-05-09 00:56:39', null, '2020-05-09 00:56:39', '');
INSERT INTO `s_org` VALUES ('144', '北京市委', 'bjsw', '143', null, '1', '0', '0', '1', '2020-05-09 00:56:42', null, '2020-05-09 00:56:42', '');
INSERT INTO `s_org` VALUES ('145', '北京市政府', 'bjszf', '143', null, '2', '0', '0', '1', '2020-05-09 00:57:28', null, null, '');
INSERT INTO `s_org` VALUES ('146', '机要局', 'jyj', '144', null, '1', '0', '0', '1', '2020-05-09 00:58:51', null, null, '');
INSERT INTO `s_org` VALUES ('147', '测试市委子部门1', 'testswsub1', '144', null, '2', '0', '1', '1', '2020-05-09 01:18:12', '1', '2020-05-09 15:49:53', '');
INSERT INTO `s_org` VALUES ('148', '测试市委子部门2', 'testswsub2', '144', null, '3', '0', '1', '1', '2020-05-09 01:18:12', '1', '2020-05-09 15:49:53', '');
INSERT INTO `s_org` VALUES ('149', '测试市委子部门3', 'testswsub3', '144', null, '4', '0', '1', '1', '2020-05-09 01:18:12', '1', '2020-05-09 15:49:53', '');
INSERT INTO `s_org` VALUES ('150', '测试市委子部门1-孙部门1-up', 'testswsub1-sub1-up', '147', null, '3', '0', '1', '1', '2020-05-09 01:19:37', '1', '2020-05-09 15:49:53', '');
INSERT INTO `s_org` VALUES ('151', '测试市委子部门1-孙部门2', 'testswsub1-sub2', '147', null, '2', '0', '1', '1', '2020-05-09 01:18:12', '1', '2020-05-09 15:49:53', '');
INSERT INTO `s_org` VALUES ('152', '测试机构', 'test', '144', null, '2', '0', '1', '1', '2020-05-09 15:52:12', '1', '2020-05-09 15:53:00', '');

-- ----------------------------
-- Table structure for s_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_permission`;
CREATE TABLE `s_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `type` int(1) DEFAULT NULL COMMENT '类别',
  `url` varchar(255) DEFAULT NULL COMMENT '权限URL',
  `method` varchar(255) DEFAULT NULL COMMENT '方法名',
  `descrip` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `del_flg` char(1) DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_permission
-- ----------------------------
INSERT INTO `s_permission` VALUES ('1', 'permission', null, null, null, '权限管理', '0', null, '2020-05-07 16:44:43', null, '2020-05-07 16:44:43', null);
INSERT INTO `s_permission` VALUES ('2', 'role', null, null, null, '角色管理', '0', '1', '2020-05-07 17:30:20', '1', '2020-05-07 17:30:20', null);
INSERT INTO `s_permission` VALUES ('3', 'user', null, null, null, '用户管理', '0', '1', '2020-05-07 17:30:24', '1', '2020-05-07 17:30:24', null);
INSERT INTO `s_permission` VALUES ('4', 'org', null, null, null, '部门管理', '0', null, null, null, null, null);

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `degree` int(11) DEFAULT NULL COMMENT '角色等级',
  `descrip` varchar(255) DEFAULT NULL COMMENT '描述',
  `del_flg` char(1) DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES ('1', '管理员', null, null, '0', null, null, null, null, null);
INSERT INTO `s_role` VALUES ('11', '测试用户权限角色', null, '测试用户权限角色', '1', '1', '2020-05-08 21:54:38', '1', '2020-05-09 16:02:50', null);
INSERT INTO `s_role` VALUES ('12', '测试角色权限角色', null, '测试角色权限角色', '1', '1', '2020-05-08 21:54:20', '1', '2020-05-09 16:02:50', null);
INSERT INTO `s_role` VALUES ('13', '测试权限权限角色', null, '测试权限权限角色', '1', '1', '2020-05-08 21:55:44', '1', '2020-05-09 16:02:50', null);

-- ----------------------------
-- Table structure for s_role_per
-- ----------------------------
DROP TABLE IF EXISTS `s_role_per`;
CREATE TABLE `s_role_per` (
  `rid` int(11) DEFAULT NULL COMMENT '角色ID',
  `pid` int(11) DEFAULT NULL COMMENT '权限ID',
  `del_flg` char(1) DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  KEY `rid` (`rid`),
  KEY `pid` (`pid`),
  CONSTRAINT `s_role_per_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `s_role` (`id`),
  CONSTRAINT `s_role_per_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `s_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_role_per
-- ----------------------------
INSERT INTO `s_role_per` VALUES ('1', '1', '0', null, '2020-05-08 22:02:13', null, '2020-05-08 22:02:13', null);
INSERT INTO `s_role_per` VALUES ('1', '2', '0', null, '2020-05-08 22:02:13', null, '2020-05-08 22:02:13', null);
INSERT INTO `s_role_per` VALUES ('1', '3', '0', null, '2020-05-08 22:02:13', null, '2020-05-08 22:02:13', null);
INSERT INTO `s_role_per` VALUES ('12', '2', '1', '1', '2020-05-09 16:02:50', '1', '2020-05-09 16:02:50', null);
INSERT INTO `s_role_per` VALUES ('11', '3', '1', '1', '2020-05-09 16:02:50', '1', '2020-05-09 16:02:50', null);
INSERT INTO `s_role_per` VALUES ('13', '1', '1', '1', '2020-05-09 16:02:49', '1', '2020-05-09 16:02:50', null);
INSERT INTO `s_role_per` VALUES ('1', '4', '0', null, null, null, null, null);

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `user_code` varchar(32) DEFAULT NULL COMMENT '用户唯一标识',
  `login_name` varchar(255) DEFAULT NULL COMMENT '登录名',
  `user_pwd` varchar(255) DEFAULT NULL COMMENT '密码',
  `org_id` int(11) DEFAULT NULL COMMENT '组织ID',
  `org_code` varchar(32) DEFAULT NULL COMMENT '组织唯一标识',
  `org_name` varchar(255) DEFAULT NULL COMMENT '组织名称',
  `del_flg` char(1) DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=375 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('1', '管理员', 'admin', 'admin', '0', '-1', '-1', null, '0', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('365', '测试用户权限用户', 'testuserper-user', 'testuserper-user', '075091074065079095', '144', 'bjsw', '北京市委', '1', '1', '2020-05-09 02:40:27', '1', '2020-05-09 16:02:35', null);
INSERT INTO `s_user` VALUES ('366', '测试角色权限用户', 'testuserper-role', 'testuserper-role', '075091074065079095', '146', 'jyj', '机要局', '1', '1', '2020-05-09 16:00:20', '1', '2020-05-09 16:02:35', null);
INSERT INTO `s_user` VALUES ('367', '测试权限权限用户', 'testuserper-per', 'testuserper-per', '075091074065079095', '145', 'bjszf', '北京市政府', '1', '1', '2020-05-09 14:43:28', '1', '2020-05-09 16:02:35', null);

-- ----------------------------
-- Table structure for s_user_role
-- ----------------------------
DROP TABLE IF EXISTS `s_user_role`;
CREATE TABLE `s_user_role` (
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `rid` int(11) DEFAULT NULL COMMENT '角色ID',
  `del_flg` char(1) DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  KEY `uid` (`uid`),
  KEY `rid` (`rid`),
  CONSTRAINT `s_user_role_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `s_user` (`id`),
  CONSTRAINT `s_user_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `s_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user_role
-- ----------------------------
INSERT INTO `s_user_role` VALUES ('1', '1', '0', null, null, null, null, null);

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件ID',
  `name` varchar(255) DEFAULT NULL COMMENT '生成的唯一文件名',
  `ori_name` varchar(255) DEFAULT NULL COMMENT '原始文件名',
  `path` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `del_flg` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5794 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_file
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_ref
-- ----------------------------
DROP TABLE IF EXISTS `t_file_ref`;
CREATE TABLE `t_file_ref` (
  `fid` int(11) DEFAULT NULL COMMENT '文件ID',
  `refid` int(11) DEFAULT NULL COMMENT '关联字段ID',
  `type` int(1) DEFAULT NULL COMMENT '类别',
  `del_flg` char(1) DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  KEY `fid` (`fid`),
  KEY `refid` (`refid`),
  CONSTRAINT `t_file_ref_ibfk_1` FOREIGN KEY (`fid`) REFERENCES `t_file` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_file_ref
-- ----------------------------

-- ----------------------------
-- Function structure for org_children
-- ----------------------------
DROP FUNCTION IF EXISTS `org_children`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `org_children`(rootId varchar(36)) RETURNS varchar(1000) CHARSET utf8
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
DELIMITER ;

-- ----------------------------
-- Function structure for org_parent
-- ----------------------------
DROP FUNCTION IF EXISTS `org_parent`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `org_parent`(p varchar(100)) RETURNS varchar(1000) CHARSET utf8
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
DELIMITER ;
