-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.18-0ubuntu0.16.04.1 - (Ubuntu)
-- Server OS:                    Linux
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table tim9.access_log
CREATE TABLE IF NOT EXISTS `access_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `description` longtext COLLATE utf8mb4_croatian_ci NOT NULL,
  `object_id` bigint(20) NOT NULL,
  `table_name` varchar(45) COLLATE utf8mb4_croatian_ci NOT NULL,
  `type` varchar(45) COLLATE utf8mb4_croatian_ci NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc4dbfewt4475c4h38ipe8bq28` (`user_id`),
  CONSTRAINT `FKc4dbfewt4475c4h38ipe8bq28` FOREIGN KEY (`user_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Dumping data for table tim9.access_log: ~0 rows (approximately)
DELETE FROM `access_log`;
/*!40000 ALTER TABLE `access_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `access_log` ENABLE KEYS */;

-- Dumping structure for table tim9.account
CREATE TABLE IF NOT EXISTS `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL,
  `full_name` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL,
  `username` varchar(16) COLLATE utf8mb4_croatian_ci NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_q0uja26qgu1atulenwup9rxyr` (`email`),
  UNIQUE KEY `UK_gex1lmaqpg0ir5g1f5eftyaa1` (`username`),
  KEY `FKgdpd8e1vs356bjg287jr27pl7` (`role_id`),
  CONSTRAINT `FKgdpd8e1vs356bjg287jr27pl7` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Dumping data for table tim9.account: ~3 rows (approximately)
DELETE FROM `account`;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`id`, `email`, `full_name`, `password`, `username`, `role_id`) VALUES
	(1, 'admin@etf.unsa.ba', 'Admin', '$2a$10$OwUH2fqyQ/ap1EbVj35sIOM1RJyPeWa2lZ3ndrT/hUJ.YIqR5ykvq', 'admin', 1),
	(2, 'finance@etf.unsa.ba', 'Finance', '$2a$10$X2scjC15hX2MaYtsfO4gZeJyPDs3/grNZywUYvMRDDkiYKmkwlfde', 'finance', 2),
	(3, 'auditteam@etf.unsa.ba', 'Audit Team', '$2a$10$s8l85TxDdNw9fQTrcbeLTuLQJZNyxgWPCeisXsGY83Vdf1YUrVY6C', 'auditteam', 3);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- Dumping structure for table tim9.audit
CREATE TABLE IF NOT EXISTS `audit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `finished` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL,
  `updated_at` datetime NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `location_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK57qrqyi62v4ur2336ajp5diho` (`user_id`),
  KEY `FKk3v3xw9ikl9x4iclfpcmi4y0c` (`location_id`),
  CONSTRAINT `FK57qrqyi62v4ur2336ajp5diho` FOREIGN KEY (`user_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FKk3v3xw9ikl9x4iclfpcmi4y0c` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Dumping data for table tim9.audit: ~0 rows (approximately)
DELETE FROM `audit`;
/*!40000 ALTER TABLE `audit` DISABLE KEYS */;
/*!40000 ALTER TABLE `audit` ENABLE KEYS */;

-- Dumping structure for table tim9.audit_item
CREATE TABLE IF NOT EXISTS `audit_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `note` longtext COLLATE utf8mb4_croatian_ci,
  `present` bit(1) NOT NULL,
  `sku_correct` bit(1) NOT NULL,
  `audit_id` bigint(20) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  `status_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo09515d7l20yd3gx9q111an1f` (`audit_id`),
  KEY `FKq0iasoq5pq0ydw1ipfmqtx7pp` (`item_id`),
  KEY `FKn64f4stkqby87xbyil0hdtnmx` (`status_id`),
  CONSTRAINT `FKn64f4stkqby87xbyil0hdtnmx` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `FKo09515d7l20yd3gx9q111an1f` FOREIGN KEY (`audit_id`) REFERENCES `audit` (`id`),
  CONSTRAINT `FKq0iasoq5pq0ydw1ipfmqtx7pp` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Dumping data for table tim9.audit_item: ~0 rows (approximately)
DELETE FROM `audit_item`;
/*!40000 ALTER TABLE `audit_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `audit_item` ENABLE KEYS */;

-- Dumping structure for table tim9.category
CREATE TABLE IF NOT EXISTS `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` longtext COLLATE utf8mb4_croatian_ci NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2y94svpmqttx80mshyny85wqr` (`parent_id`),
  CONSTRAINT `FK2y94svpmqttx80mshyny85wqr` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Dumping data for table tim9.category: ~0 rows (approximately)
DELETE FROM `category`;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- Dumping structure for table tim9.item
CREATE TABLE IF NOT EXISTS `item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_of_purchase` datetime NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL,
  `person_responsible` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL,
  `purchased_by` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL,
  `sku_number` varchar(45) COLLATE utf8mb4_croatian_ci NOT NULL,
  `unit_of_measurement` varchar(20) COLLATE utf8mb4_croatian_ci NOT NULL,
  `value` decimal(19,2) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `location_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2n9w8d0dp4bsfra9dcg0046l4` (`category_id`),
  KEY `FKd490m4r8ekpyh5glux9jt7j3b` (`location_id`),
  CONSTRAINT `FK2n9w8d0dp4bsfra9dcg0046l4` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKd490m4r8ekpyh5glux9jt7j3b` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Dumping data for table tim9.item: ~0 rows (approximately)
DELETE FROM `item`;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
/*!40000 ALTER TABLE `item` ENABLE KEYS */;

-- Dumping structure for table tim9.location
CREATE TABLE IF NOT EXISTS `location` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` longtext COLLATE utf8mb4_croatian_ci NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm5xl34yboc8re75qsac6svt6g` (`parent_id`),
  KEY `FK3l2oelpu05dyarlppuv42vu0p` (`type_id`),
  CONSTRAINT `FK3l2oelpu05dyarlppuv42vu0p` FOREIGN KEY (`type_id`) REFERENCES `location_type` (`id`),
  CONSTRAINT `FKm5xl34yboc8re75qsac6svt6g` FOREIGN KEY (`parent_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Dumping data for table tim9.location: ~0 rows (approximately)
DELETE FROM `location`;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
/*!40000 ALTER TABLE `location` ENABLE KEYS */;

-- Dumping structure for table tim9.location_type
CREATE TABLE IF NOT EXISTS `location_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Dumping data for table tim9.location_type: ~0 rows (approximately)
DELETE FROM `location_type`;
/*!40000 ALTER TABLE `location_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `location_type` ENABLE KEYS */;

-- Dumping structure for table tim9.past_audit
CREATE TABLE IF NOT EXISTS `past_audit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `audit_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK28cmlxu1qj9hwm64kno3sj9er` (`audit_id`),
  CONSTRAINT `FK28cmlxu1qj9hwm64kno3sj9er` FOREIGN KEY (`audit_id`) REFERENCES `audit` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Dumping data for table tim9.past_audit: ~0 rows (approximately)
DELETE FROM `past_audit`;
/*!40000 ALTER TABLE `past_audit` DISABLE KEYS */;
/*!40000 ALTER TABLE `past_audit` ENABLE KEYS */;

-- Dumping structure for table tim9.past_audit_item
CREATE TABLE IF NOT EXISTS `past_audit_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `note` longtext COLLATE utf8mb4_croatian_ci NOT NULL,
  `present` bit(1) NOT NULL,
  `sku_correct` bit(1) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  `past_audit_id` bigint(20) NOT NULL,
  `status_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn7fau4i7yed9bp0vwnya31eio` (`item_id`),
  KEY `FKokqqf8b66bl09bq90psdngxev` (`past_audit_id`),
  KEY `FKb6459e1q6gisx2ay4f4jc1kbh` (`status_id`),
  CONSTRAINT `FKb6459e1q6gisx2ay4f4jc1kbh` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `FKn7fau4i7yed9bp0vwnya31eio` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FKokqqf8b66bl09bq90psdngxev` FOREIGN KEY (`past_audit_id`) REFERENCES `past_audit` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Dumping data for table tim9.past_audit_item: ~0 rows (approximately)
DELETE FROM `past_audit_item`;
/*!40000 ALTER TABLE `past_audit_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `past_audit_item` ENABLE KEYS */;

-- Dumping structure for table tim9.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_croatian_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Dumping data for table tim9.roles: ~3 rows (approximately)
DELETE FROM `roles`;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `name`) VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_FINANCE'),
	(3, 'ROLE_AUDITTEAM');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

-- Dumping structure for table tim9.status
CREATE TABLE IF NOT EXISTS `status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Dumping data for table tim9.status: ~3 rows (approximately)
DELETE FROM `status`;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` (`id`, `name`) VALUES
	(1, 'Ispravno'),
	(2, 'Neispravno'),
	(3, 'Oštećeno');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
