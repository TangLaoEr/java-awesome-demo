
CREATE DATABASE `demo` /*!40100 DEFAULT CHARACTER SET utf8 */;


CREATE TABLE `user` (
                        `id` bigint(20) NOT NULL COMMENT '主键ID',
                        `name` varchar(30) DEFAULT NULL COMMENT '姓名',
                        `age` int(11) DEFAULT NULL COMMENT '年龄',
                        `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE DATABASE `consumer`; /*!40100 DEFAULT CHARACTER SET utf8 */

CREATE TABLE `send_message` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `uid` bigint(20) DEFAULT NULL,
                                `name` varchar(255) DEFAULT NULL,
                                `content` varchar(255) DEFAULT NULL,
                                `send_time` datetime DEFAULT NULL,
                                `status` varchar(255) DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
