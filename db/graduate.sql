/*
Navicat MySQL Data Transfer

Source Server         : manager
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : graduate

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-03-24 00:31:24
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
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('1', '管理1141班', '65', '无', '无', '罗宁', '1', '1', '1', '1', null, null, 'delete');
INSERT INTO `class` VALUES ('2', '管理1141班', '65', '无', '无', '罗宁', '1', '1', '1', '1', '1553217949', '无', 'normal');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES ('1', '管理学院', '1', '华南理工大学', '无', null, '无', 'delete');

-- ----------------------------
-- Table structure for education_experience
-- ----------------------------
DROP TABLE IF EXISTS `education_experience`;
CREATE TABLE `education_experience` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of education_experience
-- ----------------------------
INSERT INTO `education_experience` VALUES ('1', '1', '华南理工大学', '在校经历', '专业', '开始时间', '结束时间', '总结', null, '总结', 'delete');
INSERT INTO `education_experience` VALUES ('2', '2', '学校可口可乐了', '经历', '专业蚊鸡', '开始', '结束', '总结', '1553357835', null, 'delete');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of enterprise
-- ----------------------------
INSERT INTO `enterprise` VALUES ('1', '企业名称', '企业邮箱', '企业联系电话', '登录口令', '2018', '企业简介', '详细介绍', '民营', '业务类型', '企业法人', '企业所在地址', '企业员工数', '企业现状', '企业宣传海报', '企业官网', '统一信用代码', '工作时间', null, '备注', 'delete');

-- ----------------------------
-- Table structure for internship_experience
-- ----------------------------
DROP TABLE IF EXISTS `internship_experience`;
CREATE TABLE `internship_experience` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `internship_start_time` varchar(255) DEFAULT NULL,
  `internship_end_time` varchar(255) DEFAULT NULL,
  `internship_enterprise` varchar(255) DEFAULT NULL,
  `internship_department` varchar(255) DEFAULT NULL,
  `internship_job` varchar(255) DEFAULT NULL,
  `internship_job_content` varchar(255) DEFAULT NULL,
  `internship_result` varchar(255) DEFAULT NULL,
  `internship_harvest` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of internship_experience
-- ----------------------------
INSERT INTO `internship_experience` VALUES ('1', '1', '2018', '2019', '中铁', '文化部', '实习记者', '000', '无', '收获', null, 'dsadsa', 'delete');
INSERT INTO `internship_experience` VALUES ('2', '1大萨达撒', '2018', '2019', '中铁', '文化部', '实习记者', null, '无', '收获', null, null, 'delete');
INSERT INTO `internship_experience` VALUES ('3', '2', '看看', '你咋来了', '同事', '落地', '测试', '有几个', '兜里', '欧缇丽', '1553328018', null, 'delete');
INSERT INTO `internship_experience` VALUES ('4', '2', '测试', '测试', '漏了', '你无赖', '测试', '嗯家里急', '突击', '努力', '1553328018', null, 'delete');
INSERT INTO `internship_experience` VALUES ('5', '2', '。', '，', '？', '。', '测试修改溜了溜了', '。？', '。？', '！！？', '1553328018', null, 'delete');
INSERT INTO `internship_experience` VALUES ('6', '2', '2019年1月', '至今', '广州虎牙信息有限公司', '技术部', '安卓开发实习生', '安卓开发', '正常实习', '无', '1553330425', null, 'normal');
INSERT INTO `internship_experience` VALUES ('7', '2', '2019年7月', '至今', '中软公司', '技术部', '数据分析师', '我没看见', '正常实习', '总结', '1553330490', null, 'normal');
INSERT INTO `internship_experience` VALUES ('8', '5', '开始', '结束', '单位', '部门', '测试', '内容', '结果', '总结', '1553335399', null, 'normal');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '1', '企业', '2', '123456789', '2', '毕业生', '恭喜您，你的面试通过啦', null, '无', 'delete');

-- ----------------------------
-- Table structure for personal_cert
-- ----------------------------
DROP TABLE IF EXISTS `personal_cert`;
CREATE TABLE `personal_cert` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `cert_name` varchar(255) DEFAULT NULL,
  `cert_time` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of personal_cert
-- ----------------------------
INSERT INTO `personal_cert` VALUES ('1', '122', '名称', '2018年', null, '备注', 'delete');
INSERT INTO `personal_cert` VALUES ('2', '2', '证书', '活的时间', '1553355009', null, 'delete');
INSERT INTO `personal_cert` VALUES ('3', '2', '雅思证书', '2019年', '1553355040', null, 'normal');
INSERT INTO `personal_cert` VALUES ('4', '2', '证书', '来来来', '1553355100', null, 'delete');

-- ----------------------------
-- Table structure for personal_skill
-- ----------------------------
DROP TABLE IF EXISTS `personal_skill`;
CREATE TABLE `personal_skill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `skill_name` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of personal_skill
-- ----------------------------
INSERT INTO `personal_skill` VALUES ('1', '1', '技能', null, '无', 'delete');
INSERT INTO `personal_skill` VALUES ('2', '1', '技能2', null, '无', 'delete');
INSERT INTO `personal_skill` VALUES ('3', '2', '技能一', '1553343100', null, 'delete');
INSERT INTO `personal_skill` VALUES ('4', '2', '技能三', '1553343259', null, 'delete');
INSERT INTO `personal_skill` VALUES ('5', '2', '四六级', '1553343617', null, 'normal');

-- ----------------------------
-- Table structure for prize_experience
-- ----------------------------
DROP TABLE IF EXISTS `prize_experience`;
CREATE TABLE `prize_experience` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `prize_name` varchar(255) DEFAULT NULL,
  `prize_time` varchar(255) DEFAULT NULL,
  `prize_detail` varchar(255) DEFAULT NULL,
  `prize_result` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prize_experience
-- ----------------------------
INSERT INTO `prize_experience` VALUES ('1', '2', '名称', '2018', '说明', '总结', null, '无', 'delete');

-- ----------------------------
-- Table structure for recruit
-- ----------------------------
DROP TABLE IF EXISTS `recruit`;
CREATE TABLE `recruit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_id` int(11) DEFAULT NULL,
  `enterprise_name` varchar(255) DEFAULT NULL,
  `depertment` varchar(255) DEFAULT NULL,
  `job_name` varchar(255) DEFAULT NULL,
  `job_content` varchar(255) DEFAULT NULL,
  `working_day` varchar(255) DEFAULT NULL,
  `working_start_time` varchar(255) DEFAULT NULL,
  `working_end_time` varchar(255) DEFAULT NULL,
  `working_region` varchar(255) DEFAULT NULL,
  `working_address` varchar(255) DEFAULT NULL,
  `job_abstract` varchar(255) DEFAULT NULL,
  `job_detail` varchar(255) DEFAULT NULL,
  `welfare` varchar(2550) DEFAULT NULL COMMENT '福利',
  `job_requirements` varchar(255) DEFAULT NULL,
  `skill_requirement` varchar(255) DEFAULT NULL,
  `team_detail` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL COMMENT '发布者',
  `salary` varchar(255) DEFAULT NULL COMMENT '薪水',
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recruit
-- ----------------------------
INSERT INTO `recruit` VALUES ('1', '2', '聚缘计算机系统', '技术部', 'Android开发工程师', '工作内容', '3-4天/周', '8:00', '16:00', '广州', '广州天河区', '岗位简介负责Android系统平台上的客户端软件的产品开发与维护；', '根据产品需求完成框架和模块设计、编码、测试工作；', '五险一金', '要求工作认真负责', 'Android技术熟练；基础扎实', '团队详情', '周先生', '2000-4000元/月', '1550200465', '备注', 'normal');

-- ----------------------------
-- Table structure for recruitment_collection
-- ----------------------------
DROP TABLE IF EXISTS `recruitment_collection`;
CREATE TABLE `recruitment_collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `recruit_id` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recruitment_collection
-- ----------------------------
INSERT INTO `recruitment_collection` VALUES ('1', '1', '3', null, '无', 'delete');

-- ----------------------------
-- Table structure for recruitment_delivery
-- ----------------------------
DROP TABLE IF EXISTS `recruitment_delivery`;
CREATE TABLE `recruitment_delivery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `resume_id` varchar(255) DEFAULT NULL,
  `recruit_id` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recruitment_delivery
-- ----------------------------
INSERT INTO `recruitment_delivery` VALUES ('1', '1', '2', '2', null, '无', 'delete');

-- ----------------------------
-- Table structure for resume
-- ----------------------------
DROP TABLE IF EXISTS `resume`;
CREATE TABLE `resume` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resume
-- ----------------------------
INSERT INTO `resume` VALUES ('1', '112', '简历1', '8000', '1', '本科', null, null, null, null, null, null, null, null, null, null, null, 'delete');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES ('1', '华南理工大学', '简介', '详细', '23000', '广州', '1900年', '校徽', null, '无', 'delete');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_base
-- ----------------------------
INSERT INTO `user_base` VALUES ('2', '123', '123456', '周宁', 'upload/1553335698856.jpg', '不个性何来个性签名', '女', '13', '123', '汉', '广东', '13413607283@163.com', '123456', '12345678910', '常住地址', '1996', 'student', null, null, null, null, null, null, null, '无', 'normal');
INSERT INTO `user_base` VALUES ('5', '6558455', '123', '周宁', 'upload/1553335338579.jpg', null, null, null, null, null, null, null, '123', null, null, null, 'student', 'null', 'null', 'null', 'null', 'null', 'null', '1553332979', null, 'normal');
