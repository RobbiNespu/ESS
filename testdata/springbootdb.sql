-- Adminer 4.8.0 MySQL 5.5.5-10.5.10-MariaDB-1:10.5.10+maria~focal dump

SET NAMES utf8;
SET
time_zone = '+00:00';
SET
foreign_key_checks = 0;
SET
sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `class_subject_lists`;
CREATE TABLE `class_subject_lists`
(
    `id`              varchar(255) NOT NULL,
    `form_year`       int(11) DEFAULT NULL,
    `group_slot`      int(11) NOT NULL,
    `subject_id`      varchar(255) DEFAULT NULL,
    `teacher_role_id` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `class_subject_lists` (`id`, `form_year`, `group_slot`, `subject_id`, `teacher_role_id`)
VALUES ('class_subject_lists_001', 1, 1, 'subject_001', 'role_003'),
       ('class_subject_lists_002', 1, 2, 'subject_002', 'role_003'),
       ('class_subject_lists_003', 1, 3, 'subject_003', 'role_003');

DROP TABLE IF EXISTS `forms`;
CREATE TABLE `forms`
(
    `id`        varchar(255) NOT NULL,
    `form_year` int(11) DEFAULT NULL,
    `name`      varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `forms` (`id`, `form_year`, `name`)
VALUES ('form_001', 1, 'F1K1'),
       ('form_002', 2, 'F2K1');

DROP TABLE IF EXISTS `nodes`;
CREATE TABLE `nodes`
(
    `id`     varchar(255) NOT NULL,
    `child`  varchar(255) DEFAULT NULL,
    `level`  int(11) NOT NULL,
    `parent` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `nodes` (`id`, `child`, `level`, `parent`)
VALUES ('node_001', 'F1K1', 2, 'T1'),
       ('node_002', 'T1', 1, 'X'),
       ('node_003', 'F2K1', 2, 'T2'),
       ('node_004', 'T2', 1, 'X'),
       ('node_005', 'subject_001', 3, 'F1K1'),
       ('node_006', 'G1', 4, 'subject_001'),
       ('node_007', 'SLOT7', 5, 'G1'),
       ('node_008', 'subject_002', 3, 'F1K1'),
       ('node_009', 'G2', 4, 'subject_002'),
       ('node_010', 'SLOT1', 5, 'G2'),
       ('node_011', 'SLOT3', 5, 'G2'),
       ('node_012', 'SLOT6', 5, 'G2'),
       ('node_013', 'subject_003', 3, 'F1K1'),
       ('node_014', 'G3', 4, 'subject_003'),
       ('node_015', 'SLOT2', 5, 'G3'),
       ('node_016', 'SLOT4', 5, 'G3'),
       ('node_017', 'SLOT5', 5, 'G3');

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`       varchar(255) NOT NULL,
    `type`     varchar(255) DEFAULT NULL,
    `forms_id` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY        `FK2wk7joawhxj0u7pyexg566e4n` (`forms_id`),
    CONSTRAINT `FK2wk7joawhxj0u7pyexg566e4n` FOREIGN KEY (`forms_id`) REFERENCES `forms` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `role` (`id`, `type`, `forms_id`)
VALUES ('role_001', 'student', 'form_001'),
       ('role_002', 'student', 'form_002'),
       ('role_003', 'teacher', NULL);

DROP TABLE IF EXISTS `seq_class_subject_lists`;
CREATE TABLE `seq_class_subject_lists`
(
    `next_not_cached_value` bigint(21) NOT NULL,
    `minimum_value`         bigint(21) NOT NULL,
    `maximum_value`         bigint(21) NOT NULL,
    `start_value`           bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
    `increment`             bigint(21) NOT NULL COMMENT 'increment value',
    `cache_size`            bigint(21) unsigned NOT NULL,
    `cycle_option`          tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
    `cycle_count`           bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;


DROP TABLE IF EXISTS `seq_form`;
CREATE TABLE `seq_form`
(
    `next_not_cached_value` bigint(21) NOT NULL,
    `minimum_value`         bigint(21) NOT NULL,
    `maximum_value`         bigint(21) NOT NULL,
    `start_value`           bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
    `increment`             bigint(21) NOT NULL COMMENT 'increment value',
    `cache_size`            bigint(21) unsigned NOT NULL,
    `cycle_option`          tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
    `cycle_count`           bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;


DROP TABLE IF EXISTS `seq_node`;
CREATE TABLE `seq_node`
(
    `next_not_cached_value` bigint(21) NOT NULL,
    `minimum_value`         bigint(21) NOT NULL,
    `maximum_value`         bigint(21) NOT NULL,
    `start_value`           bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
    `increment`             bigint(21) NOT NULL COMMENT 'increment value',
    `cache_size`            bigint(21) unsigned NOT NULL,
    `cycle_option`          tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
    `cycle_count`           bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;


DROP TABLE IF EXISTS `seq_role`;
CREATE TABLE `seq_role`
(
    `next_not_cached_value` bigint(21) NOT NULL,
    `minimum_value`         bigint(21) NOT NULL,
    `maximum_value`         bigint(21) NOT NULL,
    `start_value`           bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
    `increment`             bigint(21) NOT NULL COMMENT 'increment value',
    `cache_size`            bigint(21) unsigned NOT NULL,
    `cycle_option`          tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
    `cycle_count`           bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;


DROP TABLE IF EXISTS `seq_slot`;
CREATE TABLE `seq_slot`
(
    `next_not_cached_value` bigint(21) NOT NULL,
    `minimum_value`         bigint(21) NOT NULL,
    `maximum_value`         bigint(21) NOT NULL,
    `start_value`           bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
    `increment`             bigint(21) NOT NULL COMMENT 'increment value',
    `cache_size`            bigint(21) unsigned NOT NULL,
    `cycle_option`          tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
    `cycle_count`           bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;


DROP TABLE IF EXISTS `seq_subject`;
CREATE TABLE `seq_subject`
(
    `next_not_cached_value` bigint(21) NOT NULL,
    `minimum_value`         bigint(21) NOT NULL,
    `maximum_value`         bigint(21) NOT NULL,
    `start_value`           bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
    `increment`             bigint(21) NOT NULL COMMENT 'increment value',
    `cache_size`            bigint(21) unsigned NOT NULL,
    `cycle_option`          tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
    `cycle_count`           bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;


DROP TABLE IF EXISTS `seq_user`;
CREATE TABLE `seq_user`
(
    `next_not_cached_value` bigint(21) NOT NULL,
    `minimum_value`         bigint(21) NOT NULL,
    `maximum_value`         bigint(21) NOT NULL,
    `start_value`           bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
    `increment`             bigint(21) NOT NULL COMMENT 'increment value',
    `cache_size`            bigint(21) unsigned NOT NULL,
    `cycle_option`          tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
    `cycle_count`           bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;


DROP TABLE IF EXISTS `slots`;
CREATE TABLE `slots`
(
    `id`                    varchar(255) NOT NULL,
    `active`                bit(1)       NOT NULL,
    `booked`                bit(1)       NOT NULL,
    `booked_by`             varchar(255) DEFAULT NULL,
    `booked_date`           datetime(6) DEFAULT NULL,
    `exam_date`             datetime(6) DEFAULT NULL,
    `name`                  varchar(255) DEFAULT NULL,
    `class_subject_list_id` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                     `FK4iid320ev9spcvh8676xr1jdu` (`class_subject_list_id`),
    CONSTRAINT `FK4iid320ev9spcvh8676xr1jdu` FOREIGN KEY (`class_subject_list_id`) REFERENCES `class_subject_lists` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `slots` (`id`, `active`, `booked`, `booked_by`, `booked_date`, `exam_date`, `name`, `class_subject_list_id`)
VALUES ('slot_001', CONV('1', 2, 10) + 0, CONV('0', 2, 10) + 0, NULL, NULL, NULL, 'SLOT7', 'class_subject_lists_001'),
       ('slot_002', CONV('1', 2, 10) + 0, CONV('0', 2, 10) + 0, NULL, NULL, NULL, 'SLOT1', 'class_subject_lists_002'),
       ('slot_003', CONV('1', 2, 10) + 0, CONV('0', 2, 10) + 0, NULL, NULL, NULL, 'SLOT3', 'class_subject_lists_002'),
       ('slot_004', CONV('1', 2, 10) + 0, CONV('0', 2, 10) + 0, NULL, NULL, NULL, 'SLOT6', 'class_subject_lists_002'),
       ('slot_005', CONV('1', 2, 10) + 0, CONV('0', 2, 10) + 0, NULL, NULL, NULL, 'SLOT2', 'class_subject_lists_003'),
       ('slot_006', CONV('1', 2, 10) + 0, CONV('0', 2, 10) + 0, NULL, NULL, NULL, 'SLOT4', 'class_subject_lists_003'),
       ('slot_007', CONV('1', 2, 10) + 0, CONV('0', 2, 10) + 0, NULL, NULL, NULL, 'SLOT5', 'class_subject_lists_003');

DROP TABLE IF EXISTS `subjects`;
CREATE TABLE `subjects`
(
    `id`   varchar(255) NOT NULL,
    `form` int(11) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `subjects` (`id`, `form`, `name`)
VALUES ('subject_001', 1, 'SCIENCE'),
       ('subject_002', 1, 'MATH'),
       ('subject_003', 1, 'HISTORY');

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `id`         varchar(255) NOT NULL,
    `department` varchar(255) DEFAULT NULL,
    `email`      varchar(255) DEFAULT NULL,
    `name`       varchar(255) NOT NULL,
    `password`   varchar(255) DEFAULT NULL,
    `username`   varchar(255) DEFAULT NULL,
    `roles_id`   varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY          `FKru7eepockqj2bwuk27er9otbg` (`roles_id`),
    CONSTRAINT `FKru7eepockqj2bwuk27er9otbg` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `users` (`id`, `department`, `email`, `name`, `password`, `username`, `roles_id`)
VALUES ('user_001', NULL, 'ahmad@test.com', 'ahmad', 'pass12345', 'ahmad', 'role_001'),
       ('user_002', NULL, 'shizuka@test.com', 'shizuka', 'pass12345', 'shizuka', 'role_002'),
       ('user_003', NULL, 'suraya@test.com', 'suraya', 'pass12345', 'suraya', 'role_003');

/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  /home/robbi/Documents/workplace/java/ESS/testdata/springbootdb.sql
 * Last modified:  5/29/21, 12:33 AM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

-- 2021-05-28 16:31:56
