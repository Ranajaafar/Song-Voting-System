
INSERT INTO `voting`.`user` (`id`, `Username`, `Password`, `email`) VALUES (1, 'lynn', '1234', 'lynnm@gmail.com');
INSERT INTO `voting`.`user` (`id`, `Username`, `Password`, `email`) VALUES (2, 'rana', '123456', 'ranaj@gmail.com');
INSERT INTO `voting`.`user` (`id`, `Username`, `Password`, `email`) VALUES (3, 'lynn a', '98745', 'lynna@gmail.com');
INSERT INTO `voting`.`user` (`id`, `Username`, `Password`, `email`) VALUES (4, 'root', '0025', 'root@gmail.com');


INSERT INTO `voting`.`genre` (`name`) VALUES('Jazz');

INSERT INTO `voting`.`genre` (`name`) VALUES('Party');

INSERT INTO `voting`.`genre` (`name`) VALUES('Pop');

INSERT INTO `voting`.`genre` (`name`) VALUES('Romance');

INSERT INTO `voting`.`genre` (`name`) VALUES('Sad');



INSERT INTO `type` (`name`) VALUES ('Arabic');
 INSERT INTO `type` (`name`) VALUES ('English');
INSERT INTO `type` (`name`) VALUES ('French');
INSERT INTO `type` (`name`) VALUES ('Latin');


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



INSERT INTO `voting`.`review_visit` (`id`, `NB1`, `NB2`, `NB3`, `NB4`, `NB5`) VALUES
(1, 0, 0, 0, 0, 0);




INSERT INTO `voting`.`admin` (`id`, `Username`, `Password`) VALUES
(1, 'Admin1', '1234'),
(2, 'Admin2', '9876');

