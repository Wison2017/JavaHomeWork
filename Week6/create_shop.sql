CREATE TABLE `tb_user`(
    `id` bigint(18) NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `password` varchar(100) NOT NULL,
    `phone_number` varchar(100) NOT NULL,
    `create_time` timestamp default current_timestamp,
    `update_time` timestamp default current_timestamp,
    PRIMARY KEY(`id`)
)ENGINE = INNODB DEFAULT CHARSET=utf8



CREATE TABLE `tb_order`(
    `id` bigint(18) NOT NULL AUTO_INCREMENT,
    `user_id` bigint(18) NOT NULL,
    `order_product_id` bigint NOT NULL,
    `pay_status` varchar(20) NOT NULL,
    `order_status` varchar(20) NOT NULL,
    `total_price` double NOT NULL,
    `create_time` timestamp default current_timestamp,
    `update_time` timestamp default current_timestamp,
    PRIMARY KEY(`id`)
)ENGINE = INNODB DEFAULT CHARSET=utf8



CREATE TABLE `tb_product`(
    `id` bigint(18) NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `price` double NOT NULL,
    `create_time` timestamp default current_timestamp,
    `update_time` timestamp default current_timestamp,
    PRIMARY KEY(`id`)
)ENGINE = INNODB DEFAULT CHARSET=utf8


CREATE TABLE `tb_order_product`(
    `id` bigint(18) NOT NULL AUTO_INCREMENT,
    `order_id` bigint(18) NOT NULL,
    `product_id` bigint(18) NOT NULL,
    `amount` int(10) NOT NULL,
    `total_price` double NOT NULL,
    `create_time` timestamp default current_timestamp,
    `update_time` timestamp default current_timestamp,
    PRIMARY KEY(`id`)
)ENGINE = INNODB DEFAULT CHARSET=utf8


CREATE TABLE `tb_address`(
    `id` bigint(18) NOT NULL AUTO_INCREMENT,
    `user_id` bigint(18) NOT NULL,
    `country` varchar(100) NOT NULL,
    `province` varchar(100) NOT NULL,
    `city` varchar(100) NOT NULL,
    `detail_address` varchar(100) NOT NULL,
    `create_time` timestamp default current_timestamp,
    `update_time` timestamp default current_timestamp,
    PRIMARY KEY(`id`)
)ENGINE = INNODB DEFAULT CHARSET=utf8









