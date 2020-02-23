DROP DATABASE IF EXISTS library_s;
CREATE DATABASE library_s /*!40100 DEFAULT CHARACTER SET utf8 */;
USE library_s;
CREATE TABLE `user` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `email` varchar(255) NOT NULL UNIQUE,
                        `is_active` bit(1) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        `phone_number` varchar(13) NOT NULL,
                        `username` varchar(13) NOT NULL,
                        `role` varchar(255) NOT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=304 DEFAULT CHARSET=utf8;
CREATE TABLE `book` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `in_use_by` date DEFAULT NULL,
                        `is_in_use` bit(1) DEFAULT NULL,
                        `name` varchar(255) DEFAULT NULL UNIQUE,
                        `user_id` bigint DEFAULT NULL,
                        `attribute` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `name_UNIQUE` (`name`),
                        KEY `FK1wxwagv6cm3vjrxqhmv884hir` (`user_id`),
                        CONSTRAINT `FK1wxwagv6cm3vjrxqhmv884hir` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=268 DEFAULT CHARSET=utf8;
CREATE TABLE `author` (
                          `book_id` bigint NOT NULL,
                          `authors` varchar(255) DEFAULT NULL,
                          KEY `FKqi5nll4mal57ohohlv5g0qlv2` (`book_id`),
                          CONSTRAINT `FKqi5nll4mal57ohohlv5g0qlv2` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `book_order` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `book_id` bigint(20) DEFAULT NULL,
                              `book_name` varchar(255) DEFAULT NULL,
                              `date_to` date DEFAULT NULL,
                              `user_name` varchar(255) DEFAULT NULL,
                              `usr_id` bigint(20) DEFAULT NULL,
                              `user_id` bigint(20) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=294 DEFAULT CHARSET=utf8;
CREATE TABLE `user_role` (
                             `user_id` bigint NOT NULL,
                             `roles` varchar(255) DEFAULT NULL,
                             KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`),
                             CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
