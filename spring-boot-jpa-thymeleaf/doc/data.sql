CREATE TABLE `remark_info` (
	`id` INT (11) NOT NULL AUTO_INCREMENT,
	`author` VARCHAR (50) DEFAULT NULL,
	`name` varchar (100) DEFAULT NULL ,
	`remark` VARCHAR (1024) DEFAULT NULL,
	`des` LONGTEXT,
	`create_time` datetime DEFAULT NULL ,
	`modify_time` datetime DEFAULT NULL ,
PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 5 DEFAULT CHARSET = utf8;
