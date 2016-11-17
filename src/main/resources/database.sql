# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.12)
# Database: library_project
# Generation Time: 2016-11-17 16:28:30 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table books
# ------------------------------------------------------------

DROP TABLE IF EXISTS `books`;

CREATE TABLE `books` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `author` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `borrower` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `checkoutDate` date DEFAULT NULL,
  `pendingRequest` tinyint(1) NOT NULL DEFAULT '0',
  `pendingRequestBorrower` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` enum('IN','OUT') COLLATE utf8_unicode_ci NOT NULL DEFAULT 'IN',
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;

INSERT INTO `books` (`author`, `borrower`, `checkoutDate`, `pendingRequest`, `pendingRequestBorrower`, `status`, `title`)
VALUES
	('El Masri',NULL,NULL,0,NULL,'IN','Database Systems'),
	('King',NULL,NULL,0,NULL,'IN','Java Programming for Bigginers'),
	('Alex Fernandez',NULL,NULL,0,NULL,'IN','Bioinformatics Advanced Edition'),
	('Sunderraman',NULL,NULL,0,NULL,'IN','Oracle Programming Test'),
	('Tony Gaddis',NULL,NULL,0,NULL,'IN','Starting Out With C++: From Control Structures through Objects');

/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
