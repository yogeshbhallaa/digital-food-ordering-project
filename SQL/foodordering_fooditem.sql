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
-- Table structure for table `fooditem`
--

DROP TABLE IF EXISTS `fooditem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fooditem` (
  `category` varchar(50) NOT NULL,
  `itemname` varchar(100) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `photo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`category`,`itemname`),
  KEY `fk12` (`category`),
  CONSTRAINT `fk12` FOREIGN KEY (`category`) REFERENCES `category` (`Name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fooditem`
--

LOCK TABLES `fooditem` WRITE;
/*!40000 ALTER TABLE `fooditem` DISABLE KEYS */;
INSERT INTO `fooditem` VALUES ('bread',' Ciabatta','Ciabatta is an Italian white bread made\n from wheat flour, water, olive oil, salt, and \nyeast, created in 1982','Veg',90,'src/content/1563189957797.jpg'),('bread','Breadsticks','Breadsticks (also known as grissini, \ngrissino or dipping sticks) are generally\n pencil-sized sticks of crisp, dry baked \nbread that originated in Italy.','Veg',125,'src/content/1563190084337.jpg'),('bread','Focaccia','Focaccia  is a flat oven-baked Italian bread\n product similar in style and texture to pizza\n dough.','Veg',85,'src/content/1563189231034.jpg'),('bread','Panettone','Panettone is an Italian type of sweet bread\n loaf originally from Milan usually prepared\n and enjoyed for Christmas and New Year\n in Western, Southern,','Veg',165,'src/content/1563190167809.jpg'),('Chinese Fried Rice','Char Siu Pork Fried Rice','Choose either pork fillet or scotch and \nmarinate with char sui sauce for 1 hour. ','Non-Veg',150,'src/content/1563008199375.jpg'),('Chinese Fried Rice','Shrimp Fried Rice Recipe','This recipe for Shrimp Fried Rice comes\n from my dear friend Jaden of \nSteamyKitchen.com. She learned how to\n make the best fried rice from her mother','Veg',185,'src/content/1563190497878.jpg'),('Dal','Dal Fry','Dal Fry is made by adding boiled and\n softened Dal to a seasoning of onion,\n tomato and spices which have been fried\n in ghee or oil.','Veg',100,'src/content/1563006335272.jpg'),('Dal','Dal Makhani','Dal Makhani is a dish originating from the\n Indian subcontinent, notably in the Punjab\n region.','Veg',130,'src/content/1562934082184.jpg'),('Dal','Moong dal tadka','Moong dal tadka - mung dal cooked with\n onion, tomatoes, ginger and then tempered\n with cumin, garlic, green chili, and some\n indian spice powders.','Veg',130,'src/content/1563190619303.jpg'),('Dosa','Masala Dosa ','Masala dosa or masale dose is a variation\n of the popular South Indian food dosa\n, which has its origins in Tuluva\n Mangaloren cuisine','Veg',50,'src/content/1563009160528.jpg'),('Dosa','Panner Dosa','Fry it until onions are translucent in color\n and light golden spots appear on cashew\n nuts. Add tomato, turmeric â?? chilli powder\n and salt.','Veg',70,'src/content/1563009237687.jpg'),('Idli',' Paneer Vegetable Idli ','An off-beat idli that\'ll delight every diner.','Veg',45,'src/content/1563009479771.jpg'),('Idli','Rice Idli','Idli is a round, fluffy bread roughly eight \ncentimetres in diameter.','Veg',30,'src/content/1563009342768.jpg'),('Kulcha','Butter Kulcha',' It is baked in an earthen clay oven \n(\"tandoor\") until done.','Veg',25,'src/content/1563009624320.jpg'),('Kulcha','Kulcha','A traditional Punjabi recipe, Chhola-Kulcha\n is a combination of Indian flatbread\n (Kulcha), served with chickpeas in a spicy\n gravy.','Veg',35,'src/content/1562934364088.jpg'),('Kulcha','Simple Kulcha','In particular, a spicy chickpea curry known \nas chole is the dish of choice for being\n eaten with kulcha.','Veg',15,'src/content/1563009734658.jpg'),('Noodles','Haka Noodles','haka noodles description','Veg',120,'src/content/1562933889812.jpg'),('Noodles','Soba Noodles','It usually refers to thin noodles made \nfrom buckwheat flour, or a combination \nof buckwheat and wheat flours ','Veg',130,'src/content/1563007568882.jpg'),('Noodles','Udon Noodles','Udon is a type of thick wheat flour noodle\n used frequently in Japanese cuisine. ','Veg',80,'src/content/1563007416149.jpg'),('Paneer','Mutter Paneer ',' Mutter Paneer is a vegetarian North Indian\n dish consisting of peas and paneer in a\n tomato based sauce, spiced with garam \nmasala. ','Veg',210,'src/content/1563006768944.jpg'),('Paneer','Paneer Makhani','Paneer makhani (also called paneer butter \nmasala) is a slightly sweet creamy dish of \npaneer, originating from the Indian \nsubcontinent, in which the gravy is \nprepared usually with butter (makhan), \ntomatoes, cashews or cream.','Veg',220,'src/content/1563007192111.jpg'),('Paneer','Paneer Taka Tak','Paneer Taka Tak Recipe â?? comes from\nAmritsar-Punjab. This dish is tangy, spicy\nflavorful and goes well with jeera rice or \nbutter naan. ','Veg',250,'src/content/1563007017641.jpg'),('Pasta','Creamy Pasta','Heat olive in a medium pan over medium\n heat. Add garlic and stir until fragrant, \n1 to 2 minutes.','Veg',65,'src/content/1563008691310.jpg'),('Pasta','Red Sauce Pasta ','A common sight in restaurants, pasta is\n now a household name and is something \nwith which you can make anything.','Veg',85,'src/content/1563008914692.jpg'),('Pizza','cheese pizza','Our 12-inch special-recipe pizza, featuring\n our traditional crispy crust, is topped with a\n blend of mozzarella, cheddar, provolone\n and aged Parmesan cheeses and a rich\n tomato sauce.','Veg',160,'src/content/1563008380964.jpg'),('Pizza','Chicken Pizza','Prepared pizza dough makes it easy to \nprepare this deliciously different pizza,\n topped with chicken, olives, green onion\n, mozzarella cheese and a zesty mixture of\n salsa and pizza sauce.','Non-Veg',220,'src/content/1563008504570.jpg'),('Roti','Butter Nan','butter nan desc','Veg',50,'src/content/1563355466955.jpg'),('Roti','Butter Nan 11','butter nan desc','Veg',55,'src/content/1563355494747.jpg'),('Roti','Butter Nan 12','butter nan desc','Veg',55,'src/content/1563355497454.jpg'),('Roti','Butter Nan 13','butter nan desc','Veg',55,'src/content/1563355501777.jpg'),('Roti','Butter Nan 2','butter nan desc','Veg',111,'src/content/1563355473283.jpg'),('Roti','Butter Nan 3','butter nan desc','Veg',55,'src/content/1563355478298.jpg'),('Roti','Butter Nan 4','butter nan desc','Veg',55,'src/content/1563355481970.jpg'),('Roti','Butter Nan 6','butter nan desc','Veg',55,'src/content/1563355485595.jpg'),('Roti','Butter Nan 8','butter nan desc','Veg',55,'src/content/1563355488704.jpg'),('Roti','Butter Nan 9','butter nan desc','Veg',55,'src/content/1563355491644.jpg'),('Roti','Butter Roti ','A layered roti made with butter, usually \nghee (clarified butter), but any butter can \nbe used. ','Veg',15,'src/content/1563009964775.jpg'),('Roti','Plain Roti',' Its defining characteristic is that it is\n unleavened.','Veg',8,'src/content/1563009886031.jpg'),('Roti','Roti','roti  is a round flatbread native to the Indian\n subcontinent made from stoneground\n wholemeal flour, traditionally known as atta,','Veg',7,'src/content/1562934241757.jpg');
/*!40000 ALTER TABLE `fooditem` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-16 14:39:23
