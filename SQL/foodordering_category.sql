CREATE DATABASE  IF NOT EXISTS `foodordering` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `foodordering`;
-- MySQL dump 10.13  Distrib 5.5.9, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: foodordering
-- ------------------------------------------------------
-- Server version	5.5.13

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `Name` varchar(50) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `Cuisine` varchar(50) NOT NULL,
  `Photo` varchar(100) NOT NULL,
  PRIMARY KEY (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('bread','Bread is a staple food prepared from a dough of flour and \nwater, usually by baking.','Italian','src/content/1563189056395.jpg'),('Chinese Fried Rice','Canton is a Cantonese style fried rice, typically served with\n a thick gravy poured on it.','Chinese','src/content/1562923949594.jpg'),('Dal','Dal is a term used in the Indian subcontinent for dried, split\n pulses','Indian','src/content/1562923626065.jpg'),('Dosa','A dosa is a cooked flat thin layered rice batter, originating\n from the Indian subcontinent, made from a fermented\n batter. ','South Indian','src/content/1562924435392.jpg'),('Idli','Idli  are a type of savoury rice cake, originating from the\n Indian subcontinent, popular as breakfast foods in\n southern India ','South Indian','src/content/1562924497737.jpg'),('Kulcha','Kulcha is made from maida flour, water, a pinch of salt and\n a leavening agent','Indian','src/content/1562923759237.jpg'),('Noodles','A noodle is a piece of pasta, especially a long, skinny one.','Chinese','src/content/1562923836541.jpeg'),('Paneer','Paneer   is a fresh cheese common in the\n Indian subcontinent.','Indian','src/content/1563006620362.jpg'),('Pasta','Pasta is a type of food typically made from an unleavened \ndough of durum wheat flour','Italian','src/content/1562924265220.jpg'),('Pizza',' Pizza is a savory dish of Italian origin, consisting of a\n usually round, flattened base of leavened wheat-based\n dough topped with tomatoes, cheese, and various\n other ingredients ','Italian','src/content/1562924370358.jpg'),('Roti','Roti is a round flatbread native to the Indian subcontinent\n made from stoneground wholemeal flour','Indian','src/content/1562923704503.jpg');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-16 14:39:22
