CREATE SCHEMA `voting` ;
 USE `voting` ;
 
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `email` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `voting`.`user` (`id`, `Username`, `Password`, `email`) VALUES (1, 'lynn', '1234', 'lynnm@gmail.com');
INSERT INTO `voting`.`user` (`id`, `Username`, `Password`, `email`) VALUES (2, 'rana', '123456', 'ranaj@gmail.com');
INSERT INTO `voting`.`user` (`id`, `Username`, `Password`, `email`) VALUES (3, 'lynn a', '98745', 'lynna@gmail.com');
INSERT INTO `voting`.`user` (`id`, `Username`, `Password`, `email`) VALUES (4, 'root', '0025', 'root@gmail.com');


CREATE TABLE `genre` (
  `name` char(10) NOT NULL,
  PRIMARY KEY (`name`)
);


INSERT INTO `voting`.`genre` (`name`) VALUES('Jazz');

INSERT INTO `voting`.`genre` (`name`) VALUES('Party');

INSERT INTO `voting`.`genre` (`name`) VALUES('Pop');

INSERT INTO `voting`.`genre` (`name`) VALUES('Romance');

INSERT INTO `voting`.`genre` (`name`) VALUES('Sad');


CREATE TABLE `type` (
  `name` char(10) NOT NULL,
  PRIMARY KEY (`name`)
);


INSERT INTO `type` (`name`) VALUES ('Arabic');
 INSERT INTO `type` (`name`) VALUES ('English');
INSERT INTO `type` (`name`) VALUES ('French');
INSERT INTO `type` (`name`) VALUES ('Latin');


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


INSERT INTO `voting`.`song` (`id`, `name`, `genre_name`, `type_name`, `Path`) VALUES
(1, '2Step', 'Pop', 'English', '2step.jpg'),
(2, 'RIP-Love', 'Pop', 'English', 'RIP.jpg'),
(3, 'As it was', 'Pop', 'English', 'As it was.jpg'),
(4, 'J\'me tire', 'Pop', 'French', 'j\'me tire.jpg'),
(5, 'Despacito', 'Pop', 'Latin', 'despacito.jpg'),
(6, 'It\'s you', 'Romance', 'English', 'it\'s you.jpg'),
(7, 'Perfect', 'Romance', 'English', 'perfect.jpg'),
(8, 'Disparo al corazon', 'Romance', 'Latin', 'disparo al corazon.jpg'),
(9, 'Darte un beso', 'Romance', 'Latin', 'darte un beso.jpg'),
(10, 'kl el asayed', 'Romance', 'Arabic', 'asayed.jpg'),
(11, 'baaed shu', 'Sad', 'Arabic', 'baaed shu.jpg'),
(12, 'Sans amour', 'Sad', 'French', 'sans amour.jpg'),
(13, 'Je suis malade', 'Sad', 'French', 'je suis malade.jpg'),
(14, 'Elle me tue', 'Sad', 'French', 'elle me tue.jpg'),
(15, 'Creo en ti', 'Sad', 'Latin', 'creo en ti.jpg'),
(16, 'Vivir mi vida', 'Party', 'Latin', 'vivir mi vida.jpg'),
(17, 'The motto', 'Party', 'English', 'the motto.jpg'),
(18, 'Ghazala rayea', 'Party', 'Arabic', 'ghazala rayaa.jpg'),
(19, 'Salamat', 'Party', 'Arabic', 'salamat.png'),
(20, 'Aada lkalam', 'Party', 'Arabic', 'aada lkalam.jpg'),
(21, 'Baila Maria', 'Party', 'French', 'baila maria.jpg'),
(22, 'Belly dancer', 'Party', 'English', 'belly dancer.jpg'),
(23, 'Djadja', 'Party', 'French', 'djadja.jpg'),
(24, 'Fever', 'Jazz', 'English', 'Fever.jpg'),
(25, 'Baquaqua', 'Jazz', 'Latin', 'Baquaqua.jpg'),
(26, 'Sympathique', 'Jazz', 'French', 'sympathique.jpg');


CREATE TABLE `voting`.`vote` (
  `id_user` int NOT NULL,
  `id_song` int NOT NULL,
  PRIMARY KEY (`id_user`,`id_song`),
  KEY `id_song` (`id_song`),
  CONSTRAINT `vote_ibfk_1` FOREIGN KEY (`id_song`) REFERENCES `song` (`id`),
  CONSTRAINT `vote_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
);


INSERT INTO `voting`.`vote` (`id_user`, `id_song`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 5),
(1, 6),
(1, 10),
(1, 15),
(1, 20),
(1, 25),
(1, 26),
(2, 3),
(2, 6),
(2, 7),
(2, 9),
(2, 13),
(2, 14),
(2, 17),
(2, 21),
(2, 24),
(3, 4),
(3, 5),
(3, 11),
(3, 15),
(3, 16),
(3, 19),
(3, 20),
(3, 23),
(4, 8),
(4, 12),
(4, 18),
(4, 21),
(4, 22);




CREATE TABLE `review_visit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `NB1` int NOT NULL DEFAULT '0',
  `NB2` int NOT NULL DEFAULT '0',
  `NB3` int NOT NULL DEFAULT '0',
  `NB4` int NOT NULL DEFAULT '0',
  `NB5` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
);

INSERT INTO `voting`.`review_visit` (`id`, `NB1`, `NB2`, `NB3`, `NB4`, `NB5`) VALUES
(1, 0, 0, 0, 0, 0);



CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
   PRIMARY KEY (`id`)
);


INSERT INTO `voting`.`admin` (`id`, `Username`, `Password`) VALUES
(1, 'Admin1', '1234'),
(2, 'Admin2', '9876');


DROP USER IF EXISTS 'phpUser';
CREATE USER 'phpUser' IDENTIFIED BY 'USER_1235';
GRANT ALL ON voting.* TO 'phpUser';
