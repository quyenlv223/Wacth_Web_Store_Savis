-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: watch_store
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `rom`
--

DROP TABLE IF EXISTS `rom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rom` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(64) DEFAULT NULL,
  `STATUS` varchar(64) DEFAULT NULL,
  `PRODUCT_ID` int NOT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `MODIFIER_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATE_BY` varchar(64) NOT NULL,
  `MODIFIER_BY` varchar(64) NOT NULL,
  `DELETE_FLAG` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`),
  KEY `PRODUCT_ID` (`PRODUCT_ID`),
  CONSTRAINT `rom_ibfk_1` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rom`
--

LOCK TABLES `rom` WRITE;
/*!40000 ALTER TABLE `rom` DISABLE KEYS */;
INSERT INTO `rom` VALUES (158,'8 8','ON',94,'2023-10-23 17:28:10','2023-10-23 17:28:10','ADMIN','trieuldhph15903@fpt.edu.vn',_binary '\0'),(159,'8 8','ON',95,'2023-10-23 17:37:17','2023-10-23 17:37:17','ADMIN','trieuldhph15903@fpt.edu.vn',_binary '\0'),(160,'8 8','ON',96,'2023-10-24 09:45:50','2023-10-24 09:45:50','ADMIN','trieuldhph15903@fpt.edu.vn',_binary '\0'),(161,'8 8','ON',97,'2023-10-24 09:56:59','2023-10-24 09:56:59','ADMIN','trieuldhph15903@fpt.edu.vn',_binary '\0'),(162,'8 8','ON',98,'2023-10-24 17:56:36','2023-10-24 17:56:36','ADMIN','trieuldhph15903@fpt.edu.vn',_binary '\0'),(163,'8 8','ON',99,'2023-10-24 18:00:00','2023-10-24 18:00:00','ADMIN','trieuldhph15903@fpt.edu.vn',_binary '\0'),(164,'8 8','ON',100,'2023-11-01 17:47:44','2023-11-01 17:47:44','ADMIN','luongquyenct102@gmail.com',_binary '\0'),(165,'8 8','ON',101,'2023-11-01 17:48:25','2023-11-01 17:48:25','ADMIN','luongquyenct102@gmail.com',_binary '\0'),(166,'8 8','ON',102,'2023-11-01 17:53:27','2023-11-01 17:53:27','ADMIN','luongquyenct102@gmail.com',_binary '\0'),(167,'8 8','ON',103,'2023-11-01 17:55:06','2023-11-01 17:55:06','ADMIN','luongquyenct102@gmail.com',_binary '\0'),(168,'8 8','ON',104,'2023-11-01 17:57:36','2023-11-01 17:57:36','ADMIN','luongquyenct102@gmail.com',_binary '\0'),(169,'8 8','ON',105,'2023-11-01 18:16:18','2023-11-01 18:16:18','ADMIN','luongquyenct102@gmail.com',_binary '\0'),(170,'8 8','ON',106,'2023-11-01 18:32:13','2023-11-01 18:32:13','ADMIN','luongquyenct102@gmail.com',_binary '\0'),(171,'8 8','ON',107,'2023-11-01 18:53:25','2023-11-01 18:53:25','ADMIN','luongquyenct102@gmail.com',_binary '\0'),(172,'8 8','ON',108,'2023-11-01 18:56:09','2023-11-01 18:56:09','ADMIN','luongquyenct102@gmail.com',_binary '\0'),(173,'8 8','ON',109,'2023-11-01 18:58:54','2023-11-01 18:58:54','ADMIN','luongquyenct102@gmail.com',_binary '\0'),(174,'8 8','ON',110,'2023-11-01 19:03:23','2023-11-01 19:03:23','ADMIN','luongquyenct102@gmail.com',_binary '\0');
/*!40000 ALTER TABLE `rom` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-17 23:59:08
