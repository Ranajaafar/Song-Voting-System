CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `email` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `genre` (
  `name` char(10) NOT NULL,
  PRIMARY KEY (`name`)
);

CREATE TABLE `type` (
  `name` char(10) NOT NULL,
  PRIMARY KEY (`name`)
);

CREATE TABLE `song` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `genre_name` char(10) NOT NULL,
  `type_name` char(10) NOT NULL,
  `Path` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `genre_name` (`genre_name`),
  KEY `type_name` (`type_name`),
  CONSTRAINT `song_ibfk_1` FOREIGN KEY (`genre_name`) REFERENCES `genre` (`name`),
  CONSTRAINT `song_ibfk_2` FOREIGN KEY (`type_name`) REFERENCES `type` (`name`)
);

CREATE TABLE `vote` (
  `id_user` int NOT NULL,
  `id_song` int NOT NULL,
  PRIMARY KEY (`id_user`,`id_song`),
  KEY `id_song` (`id_song`),
  CONSTRAINT `vote_ibfk_1` FOREIGN KEY (`id_song`) REFERENCES `song` (`id`),
  CONSTRAINT `vote_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
);




CREATE TABLE `review_visit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `NB1` int NOT NULL DEFAULT '0',
  `NB2` int NOT NULL DEFAULT '0',
  `NB3` int NOT NULL DEFAULT '0',
  `NB4` int NOT NULL DEFAULT '0',
  `NB5` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
);


CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
   PRIMARY KEY (`id`)
);