CREATE DATABASE  IF NOT EXISTS `shopping` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `shopping`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: shopping
-- ------------------------------------------------------
-- Server version	5.7.12-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `address` varchar(300) NOT NULL,
  `items` varchar(600) NOT NULL,
  `status` varchar(20) DEFAULT 'received',
  `user` varchar(45) NOT NULL,
  `tracking_num` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tracking_num_UNIQUE` (`tracking_num`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `isbn_10` varchar(10) NOT NULL,
  `description` varchar(900) DEFAULT NULL,
  `img` varchar(200) DEFAULT NULL,
  `sydney_stock_level` int(10) DEFAULT NULL,
  `melbourne_stock_level` int(10) DEFAULT NULL,
  `inventory_level` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Internet & WWW How To Program','0132151006','Internet & World Wide Web How to Program, 5/e is appropriate for both introductory and intermediate-level client-side and server-side programming courses. The book is also suitable for professionals who want to update their skills with the latest Internet and web programming technologies.','0132151006.jpg',5,5,10),(2,'Web Application Architecture','047051860X','Completely revised and updated, this popular book returns with coverage on a range of new technologies. Authored by a highly respected duo, this edition provides an in-depth examination of the core concepts and general principles of Web application development. Packed with examples featuring specific technologies, this book is divided into three sections: HTTP protocol as a foundation for Web applications, markup languages (HTML, XML, and CSS), and survey of emerging technologies. After a detailed introduction to the history of Web applications, coverage segues to core Internet protocols, Web browsers, Web application development, trends and directions, and more.','047051860X.jpg',5,5,10),(3,'Design Patterns','9332555400','The authors begin by describing what patterns are and how they can help you design object-oriented software. They then go on to systematically name, explain, evaluate and catalog recurring designs in object-oriented systems. With Design Patterns as your guide, you will learn how these important patterns fit into the software development process and how you can leverage them to solve your own design problems most efficiently.','9332555400.jpg',10,0,10),(4,'Head First Servlets and JSP','0596516681','Learn how to write servlets and JSPs, what makes a web container tick (and what ticks it off), how to use JSP\'s Expression Language (EL for short), and how to write deployment descriptors for your web applications. Master the c:out tag, and get a handle on exactly what\'s changed since the older J2EE 1.4 exam. You don\'t just pass the new J2EE 1.5 SCWCD exam, you\'ll understand this stuff and put it to work immediately.','0596516681.jpg',0,10,10);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-08 22:55:54
