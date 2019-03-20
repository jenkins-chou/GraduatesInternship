/*
Navicat MySQL Data Transfer

Source Server         : 用呗云借通APP日志
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : graduate

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-03-20 16:31:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) DEFAULT NULL,
  `class_number` varchar(255) DEFAULT NULL,
  `class_abstract` varchar(255) DEFAULT NULL,
  `class_detail` varchar(255) DEFAULT NULL,
  `headmaster` varchar(255) DEFAULT NULL COMMENT '班主任',
  `school_id` varchar(255) DEFAULT NULL,
  `school_name` varchar(255) DEFAULT NULL,
  `college_id` varchar(255) DEFAULT NULL,
  `college_name` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `college_name` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `school_name` varchar(255) DEFAULT NULL,
  `college_badge` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for education_experience
-- ----------------------------
DROP TABLE IF EXISTS `education_experience`;
CREATE TABLE `education_experience` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `resume_id` varchar(255) DEFAULT NULL,
  `education_school` varchar(255) DEFAULT NULL,
  `education_record` varchar(255) DEFAULT NULL,
  `education_major` varchar(255) DEFAULT NULL,
  `education_start_time` varchar(255) DEFAULT NULL,
  `education_end_time` varchar(255) DEFAULT NULL,
  `experience` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for enterprise
-- ----------------------------
DROP TABLE IF EXISTS `enterprise`;
CREATE TABLE `enterprise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_name` varchar(255) DEFAULT NULL,
  `enterprise_email` varchar(255) DEFAULT NULL,
  `enterprise_phone` varchar(255) DEFAULT NULL,
  `enterprise_token` varchar(255) DEFAULT NULL COMMENT '登录口令',
  `enterprise_build_time` varchar(255) DEFAULT NULL,
  `enterprise_abstract` varchar(255) DEFAULT NULL,
  `enterprise_detail` varchar(255) DEFAULT NULL,
  `enterprise_type` varchar(255) DEFAULT NULL,
  `enterprise_business` varchar(255) DEFAULT NULL COMMENT '业务类型',
  `enterprise_leader` varchar(255) DEFAULT NULL,
  `enterprise_address` varchar(255) DEFAULT NULL,
  `enterprise_staff` varchar(255) DEFAULT NULL COMMENT '员工数量',
  `enterprise_status` varchar(255) DEFAULT NULL COMMENT '公司状态',
  `enterprise_image` varchar(255) DEFAULT NULL,
  `enterprise_website` varchar(255) DEFAULT NULL,
  `enterprise_credit_code` varchar(255) DEFAULT NULL,
  `enterprise_work_time` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for internship_experience
-- ----------------------------
DROP TABLE IF EXISTS `internship_experience`;
CREATE TABLE `internship_experience` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `resume_id` varchar(255) DEFAULT NULL,
  `internship_start_time` varchar(255) DEFAULT NULL,
  `internship_end_time` varchar(255) DEFAULT NULL,
  `internship_enterprise` varchar(255) DEFAULT NULL,
  `internship_department` varchar(255) DEFAULT NULL,
  `internship_job` varchar(255) DEFAULT NULL,
  `internship job_content` varchar(255) DEFAULT NULL,
  `internship_result` varchar(255) DEFAULT NULL,
  `internship_harvest` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `send_user_id` varchar(255) DEFAULT NULL,
  `send_user_type` varchar(255) DEFAULT NULL,
  `send_user_name` varchar(255) DEFAULT NULL,
  `send_user_contract` varchar(255) DEFAULT NULL,
  `receive_user_id` varchar(255) DEFAULT NULL,
  `receive_user_type` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for personal_cert
-- ----------------------------
DROP TABLE IF EXISTS `personal_cert`;
CREATE TABLE `personal_cert` (
  `id` int(11) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `resume_id` varchar(255) DEFAULT NULL,
  `cert_name` varchar(255) DEFAULT NULL,
  `cert_time` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for personal_skill
-- ----------------------------
DROP TABLE IF EXISTS `personal_skill`;
CREATE TABLE `personal_skill` (
  `id` int(11) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `resume_id` varchar(255) DEFAULT NULL,
  `skill_name` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for prize_experience
-- ----------------------------
DROP TABLE IF EXISTS `prize_experience`;
CREATE TABLE `prize_experience` (
  `id` int(11) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `resume_id` varchar(255) DEFAULT NULL,
  `prize_name` varchar(255) DEFAULT NULL,
  `prize_time` varchar(255) DEFAULT NULL,
  `prize_detail` varchar(255) DEFAULT NULL,
  `prize_result` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for recruit
-- ----------------------------
DROP TABLE IF EXISTS `recruit`;
CREATE TABLE `recruit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_id` int(11) DEFAULT NULL,
  `depertment` varchar(255) DEFAULT NULL,
  `job_name` varchar(255) DEFAULT NULL,
  `job_content` varchar(255) DEFAULT NULL,
  `working_day` varchar(255) DEFAULT NULL,
  `working_start_time` varchar(255) DEFAULT NULL,
  `working_end_time` varchar(255) DEFAULT NULL,
  `working_address` varchar(255) DEFAULT NULL,
  `job_abstract` varchar(255) DEFAULT NULL,
  `job_detail` varchar(15500) DEFAULT NULL,
  `welfare` varchar(2550) DEFAULT NULL COMMENT '福利',
  `job_requirements` varchar(255) DEFAULT NULL,
  `skill_requirement` varchar(255) DEFAULT NULL,
  `team_detail` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for recruitment_collection
-- ----------------------------
DROP TABLE IF EXISTS `recruitment_collection`;
CREATE TABLE `recruitment_collection` (
  `id` int(11) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `recruit_id` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for recruitment_delivery
-- ----------------------------
DROP TABLE IF EXISTS `recruitment_delivery`;
CREATE TABLE `recruitment_delivery` (
  `id` int(11) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `resume_id` varchar(255) DEFAULT NULL,
  `recruit_id` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for resume
-- ----------------------------
DROP TABLE IF EXISTS `resume`;
CREATE TABLE `resume` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resume_name` varchar(255) DEFAULT NULL,
  `resume_expected_salary` varchar(255) DEFAULT NULL COMMENT '期望月薪',
  `resume_work_life` varchar(255) DEFAULT NULL COMMENT '工作年限',
  `resume_education` varchar(255) DEFAULT NULL,
  `resume_age` varchar(255) DEFAULT NULL,
  `resume_website` varchar(255) DEFAULT NULL,
  `resume_wechat` varchar(255) DEFAULT NULL,
  `resume_phone` varchar(255) DEFAULT NULL,
  `resume_email` varchar(255) DEFAULT NULL,
  `resume_qq` varchar(255) DEFAULT NULL,
  `resume_address` varchar(255) DEFAULT NULL,
  `resume_intention_job` varchar(255) DEFAULT NULL,
  `resume_status` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `school_name` varchar(255) DEFAULT NULL,
  `school_abstract` varchar(255) DEFAULT NULL,
  `school_detail` varchar(255) DEFAULT NULL,
  `school_number` varchar(255) DEFAULT NULL,
  `school_address` varchar(255) DEFAULT NULL,
  `school_build_time` varchar(255) DEFAULT NULL,
  `school_badge` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_base
-- ----------------------------
DROP TABLE IF EXISTS `user_base`;
CREATE TABLE `user_base` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pass` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `slogan` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  `idnum` varchar(255) DEFAULT NULL,
  `nation` varchar(255) DEFAULT NULL COMMENT '民族',
  `registered_residence` varchar(255) DEFAULT NULL COMMENT '户籍',
  `email` varchar(255) DEFAULT NULL,
  `useridentify` varchar(255) DEFAULT NULL COMMENT '用户编号（学号，教师编号，管理员编号）',
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `health` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '用户类型',
  `class_id` varchar(255) DEFAULT NULL,
  `college_id` varchar(255) DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `college_name` varchar(255) DEFAULT NULL,
  `school_name` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` varchar(255) DEFAULT NULL COMMENT '删除标志位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
