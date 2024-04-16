USE `book_store`;

-- DROP TABLE IF EXISTS `comment`;
-- DROP TABLE IF EXISTS `authority`;
-- DROP TABLE IF EXISTS `book`;
-- DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`(

`id` int NOT NULL AUTO_INCREMENT, 
`first_name` varchar(45) NOT NULL, 
`last_name` varchar(45) NOT NUll, 
`email` varchar(255) NOT NULL UNIQUE,
`password` char(68) NOT NULL,
`active` tinyint NOT NULL,
PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `book`(
`id` int NOT NULL AUTO_INCREMENT, 
`title` varchar(255) NOT NULL,
`description` varchar(255) NOT NULL,
`genre` varchar(45) NOT NULL,
`user_id` int,
PRIMARY KEY(`id`),
FOREIGN KEY(`user_id`) REFERENCES `user`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

Create TABLE `authority`(
`id` int NOT NULL AUTO_INCREMENT, 
`role` varchar(68) NOT NULL, 
CONSTRAINT `user_id`  FOREIGN KEY (`id`) REFERENCES `user` (`id`),
PRIMARY KEY(`id`),
CONSTRAINT `authorities5_idx_1` UNIQUE KEY (`id`, `role`)

)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `comment`(
`id` int NOT NULL AUTO_INCREMENT, 
`title` varchar(255) NOT NULL,
`message` text NOT NULL,
`book_id` int,
`user_id` int,
PRIMARY KEY(`id`),
FOREIGN KEY(`user_id`) REFERENCES `user`(`id`),
FOREIGN KEY(`book_id`) REFERENCES `book`(`id`)

)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;