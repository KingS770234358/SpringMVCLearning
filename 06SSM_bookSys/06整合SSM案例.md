06 整合SSM框架————书籍管理系统

6.1 环境需求
·IDEA
·MySQL5.7/8.0
·Tomcat9
·Maven3.6

6.2 知识要求:熟练掌握MySQL数据库,Spring, Java Web(SpringMVC)及Mybatis知识,简单前端知识

6.3 项目流程
· 需求分析
· 设计数据库
· 业务
· 前端界面

6.4 具体步骤:

6.4.1 创建数据库
CREATE DATABASE `ssmBookSys`;
USE `ssmBookSys`;
CREATE TABLE `books`(
	`bookID` INT(10) NOT NULL AUTO_INCREMENT COMMENT '图书编好',
	`bookName` varchar(100) NOT NULL COMMENT '书名',
	`bookCounts` INT(11) NOT NULL COMMENT '书本数量',
	`detail` varchar(200) NOT NULL COMMENT '简介',
	KEY `bookID` (`bookID`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
INSERT INTO `books`(`bookID`,`bookName`,`bookCounts`,`detail`) VALUES
(1,'Java',1,'从入门到放弃'),
(2,'MySQL',10,'从删库到跑路'),
(3,'Linux',5,'从入门到入狱');

6.4.1 创建Maven项目,搭建环境
· pom.xml文件中导出依赖
