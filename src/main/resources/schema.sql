create database parking;
CREATE TABLE `user_login` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` Varchar(500) NOT NULL ,
  `password` Varchar(200) ,
  `name` varchar(200) not null,
  `mobile` varchar(20),
  `registration_date` datetime not null,
  `facebook_id` varchar(500),
  `gmail_id` varchar(500),
  `status` int default '1',
  `last_login_time` datetime not null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;