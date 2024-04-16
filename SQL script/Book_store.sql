USE `book_store`;

-- DROP TABLE IF EXISTS `user`;
-- DROP TABLE IF EXISTS `comment`;
-- DROP TABLE IF EXISTS `authority`;
-- DROP TABLE IF EXISTS `book`;


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
`purchase_link` varchar(225) NULL, 
`user_id` int NOT NULL,
PRIMARY KEY(`id`),
CONSTRAINT `fk_book_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

Create TABLE `authority`(
`id` int NOT NULL AUTO_INCREMENT, 
`role` varchar(68) NOT NULL, 
`user_id` int NOT NULL,
CONSTRAINT `fk_authority_user_id`FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
PRIMARY KEY(`id`),
CONSTRAINT `authorities5_idx_1` UNIQUE KEY (`id`, `role`)

)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `comment`(
`id` int NOT NULL AUTO_INCREMENT, 
`title` varchar(255) NOT NULL,
`message` text NOT NULL,
`user_id` int NOT NULL,
`book_id` int NOT NULL,
PRIMARY KEY(`id`),
CONSTRAINT `fk_comment_book_id`  FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
CONSTRAINT `fk_comment_user_id` FOREIGN KEY  (`user_id`) REFERENCES `user` (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;