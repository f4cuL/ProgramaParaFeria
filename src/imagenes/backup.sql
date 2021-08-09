-- MariaDB dump 10.19  Distrib 10.4.19-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: feria
-- ------------------------------------------------------
-- Server version	10.4.19-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `feria`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `feria` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `feria`;

--
-- Table structure for table `prenda`
--

DROP TABLE IF EXISTS `prenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prenda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombrePrenda` varchar(100) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `precio` int(11) NOT NULL,
  `estadoPago` tinyint(4) NOT NULL,
  `estadoVendido` tinyint(4) NOT NULL,
  `fechaPago` date DEFAULT NULL,
  `idProovedor` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `idProovedor` (`idProovedor`),
  CONSTRAINT `prenda_ibfk_1` FOREIGN KEY (`idProovedor`) REFERENCES `proovedores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prenda`
--

LOCK TABLES `prenda` WRITE;
/*!40000 ALTER TABLE `prenda` DISABLE KEYS */;
INSERT INTO `prenda` VALUES (1,'remera osh kosh',250,0,0,NULL,1),(2,'pantalon cheeky turquesa',300,0,0,NULL,1),(3,'bermuda salmon',300,0,0,NULL,1),(4,'sandalias blancas hym',400,0,0,NULL,1),(5,'crocs violeta',300,0,0,NULL,1),(6,'pantalon rayado gris y blanco',200,0,0,NULL,1),(7,'chomba polo 10/12 gris y azul',300,0,0,NULL,1),(8,'camisa a cuadros polo',300,0,0,NULL,1),(9,'zapa flores VANS nena',300,0,0,NULL,1),(10,'maya de ba?o varon carters',300,0,0,NULL,1),(11,'pnatalon jamp color bordo',200,0,0,NULL,1),(12,'bermuda cheeky T.2',250,0,0,NULL,1),(13,'vestido grisino con lentejuelas',500,0,0,NULL,1),(14,'vestido azul 3 meses carters con bombachon',800,0,0,NULL,1),(15,'gorra camuflada',200,0,0,NULL,1),(16,'adidas tipo crocs (manchada adelante)',300,0,0,NULL,1),(17,'camisa varon m.corta rayada y lunares',150,0,0,NULL,1),(18,'bermuda hym',300,0,0,NULL,1),(19,'camiseta river',100,0,0,NULL,1),(20,'remera rip (tipo dada vuelta)',350,0,0,NULL,1),(21,'camisa celeste sin marca',250,0,0,NULL,1),(22,'jogging oshkosh (manchado rodilla)',350,0,0,NULL,1),(23,'piluso',200,0,0,NULL,1),(24,'pashmina violeta',150,0,0,NULL,1),(25,'muscu encaje blanca sweet',200,0,0,NULL,1),(26,'remera fila azul',600,0,0,NULL,1),(27,'sandalias levis',105,0,0,NULL,2),(28,'saco lurex mango',105,0,0,NULL,2),(29,'vestdo cook',105,0,0,NULL,2),(30,'vestido bambula',105,0,0,NULL,2),(31,'carpri rosa rapsodia',105,0,0,NULL,2),(32,'remera juani blanca',30,0,0,NULL,2),(33,'remera negra botones espalda',50,0,0,NULL,2),(34,'vestido maria vazquez',150,0,0,NULL,2),(35,'pant paula liarte',120,0,0,NULL,2),(36,'remera negra doble',40,0,0,NULL,2),(37,'short gabardina rie',80,0,0,NULL,2),(38,'short nike',90,0,0,NULL,2),(39,'remera paula liarte',70,0,0,NULL,2),(40,'sw blanco',90,0,0,NULL,2),(41,'pantalon gales',100,0,0,NULL,2),(42,'chaleco hombre',180,0,0,NULL,2),(43,'pantalon negro MARIA VAZQUEZ',100,0,0,NULL,2),(44,'camiseta UMA',50,0,0,NULL,2),(45,'camperiza zhoue',120,0,0,NULL,2),(46,'chomba paula liarte',60,0,0,NULL,2),(47,'saquito lanilla',60,0,0,NULL,2),(48,'musculosa tipo tunica turquesa',70,0,0,NULL,2),(49,'vestido largo con cinto cuero',100,0,0,NULL,2),(50,'calza cher',300,0,0,NULL,2),(51,'sweater fucsia ctrenzado Tomy',400,0,0,NULL,2),(52,'remera Paula liarte',200,0,0,NULL,2),(53,'Buzo ni?a gap',500,0,0,NULL,2),(54,'buzo azul M. Bertina',300,0,0,NULL,2),(55,'Camisa zara blanca ni?a',300,0,0,NULL,2),(56,'camiseta volados akiabara',200,0,0,NULL,2),(57,'remera encaje rapsodia manteca',300,0,0,NULL,2),(58,'blazer azul encaje',500,0,0,NULL,2),(59,'tunica wanama bordada',600,0,0,NULL,2),(60,'camisa gap',400,0,0,NULL,2),(61,'camisa cartera',400,0,0,NULL,2),(62,'sw zara azul finito con pitucones',600,0,0,NULL,2),(63,'camiseta oshkosh letreas verdes',300,0,0,NULL,2),(64,'sw spider man',600,0,0,NULL,2),(65,'chmba gris cn negra',250,0,0,NULL,2),(66,'Camisa lula Praga',250,0,0,NULL,3),(67,'Buzo blanco lula (manchado)',100,0,0,NULL,3),(68,'tapadito zara',300,0,0,NULL,3),(69,'enterito jean',500,0,0,NULL,3),(70,'vestido color rojo escote Marilyn algodon',300,0,0,NULL,3),(71,'musculosa blanca (estirada) berlin',50,0,0,NULL,3),(72,'remera Rosa basica',100,0,0,NULL,3),(73,'basica taverniti',100,0,0,NULL,3),(74,'biquini flores y print',250,0,0,NULL,3),(75,'calza engomada',400,0,0,NULL,3),(76,'jean slouchy',500,0,0,NULL,3),(77,'mocasin cordon negro',500,0,0,NULL,3),(78,'jean negro DC',500,0,0,NULL,4),(79,'buzo azul ellements',700,0,0,NULL,4),(80,'buzo negro con amarilo ocn',500,0,0,NULL,4),(81,'buzo rojo ocn',500,0,0,NULL,4),(82,'campera inflable roja con azul mimo reversible',1000,0,0,NULL,4),(83,'campera negra rip reeversible cuadrille',700,0,0,NULL,4),(84,'campera inflable azul rip',1200,0,0,NULL,4),(85,'chaleco coon capucha de polar mimo',700,0,0,NULL,4),(86,'campera algodon ocn',600,0,0,NULL,4),(87,'short deportivo roxy',300,0,0,NULL,5),(88,'sandalias blancas tipo ojota cuerp labradas',300,0,0,NULL,5),(89,'guillermina cuero roja mimo',300,0,0,NULL,5),(90,'botas grises mimo',400,0,0,NULL,5),(91,'guillermina con flores',300,0,0,NULL,5),(92,'sanadalia cuero con colores tipo ojota',300,0,0,NULL,5),(93,'botas mimo fuxia',400,0,0,NULL,5),(94,'vestido blanco',200,0,0,NULL,5),(95,'campera grisino unicornios',450,0,0,NULL,5),(96,'tapadito corderoy sin marca',300,0,0,NULL,6),(97,'gorro corderito con print',200,0,0,NULL,6),(98,'gorro kitty',150,0,0,NULL,6),(99,'saquito x lurex Paul carty',250,0,0,NULL,6),(100,'camisa corderoy con flores',200,0,0,NULL,6),(101,'camisa jean koxis',400,0,0,NULL,6),(102,'campera hym rayada con verdw',800,0,0,NULL,7),(103,'tapadito polar neggro',500,0,0,NULL,8),(104,'pantubotas con estrellas brillos',500,0,0,NULL,8),(105,'jean con flores bodadas',500,0,0,NULL,8),(106,'disfraz monster high',700,0,0,NULL,8),(107,'campera con corazones de tipo toalla',250,0,0,NULL,8),(108,'sw con letras de canutillos',250,0,0,NULL,8),(109,'jean guess kids',700,0,0,NULL,8),(110,'zapatillas zara negras',800,0,0,NULL,8),(111,'pantalon azul tipo joging acetato',350,0,0,NULL,8),(112,'pantalon nego con verde acetato',350,0,0,NULL,8),(113,'sw azul natutica',400,0,0,NULL,8),(114,'sw tommy blanco y gris',700,0,0,NULL,8),(115,'sw davor colores pasteles',500,0,0,NULL,8),(116,'remera levis amarilla',300,0,0,NULL,8),(117,'remera basica banana republic',250,0,0,NULL,8),(118,'camper verde tipo bomber',500,0,0,NULL,8),(119,'remera verde abana republic',300,0,0,NULL,8),(120,'short nike gris amarillo',500,0,0,NULL,8),(121,'campera gao beige y choco',700,0,0,NULL,8),(122,'sweter motivi ancho',350,0,0,NULL,9),(123,'saco negro x lurex',300,0,0,NULL,9),(124,'vestido las pochis corto creppe',350,0,0,NULL,9),(125,'vestido print con flores m.anga corta',350,0,0,NULL,9),(126,'kimono gasa abierto',300,0,0,NULL,9),(127,'sw negro ancho',300,0,0,NULL,9),(128,'saco gris tejido abierto',300,0,0,NULL,9),(129,'camiseta morley gris con hombro descubierto',250,0,0,NULL,9),(130,'pelo de mono azul',250,0,0,NULL,9),(131,'blusa ancha cuesta blanca saten',250,0,0,NULL,9),(132,'sw con bolsillito con lentejuelas',300,0,0,NULL,9),(133,'camisa Cook escoc?sa',500,0,0,NULL,9),(134,'pollera gasa manteca',300,0,0,NULL,9),(135,'sw ancho print tryme',500,0,0,NULL,9),(136,'camisa verde con tachas estrellas',500,0,0,NULL,9),(137,'camisa creppw beige lisa',200,0,0,NULL,9),(138,'remera ancha dec?an morley',350,0,0,NULL,9),(139,'camiseta morley the market',250,0,0,NULL,9),(140,'calza ren?',300,0,0,NULL,9),(141,'blusa gasa con volados y espalda descubierta',200,0,0,NULL,9),(142,'camisa rayada con azul',250,0,0,NULL,9),(143,'remera lacoste (falta tiento)',250,0,0,NULL,9),(144,'sw pelo de mono tricolor',350,0,0,NULL,9),(145,'short nike',500,0,0,NULL,9),(146,'saco negro grueso',400,0,0,NULL,9),(147,'campera nije negra',800,0,0,NULL,9),(148,'camisa cheeky t.2 con flores',500,0,0,NULL,10),(149,'vestido minimimo xxl con flores',500,0,0,NULL,10),(150,'remera con flores fuxia',250,0,0,NULL,10),(151,'tapado crudo con peluche cuello',700,0,0,NULL,10),(152,'saco rosa mimo',400,0,0,NULL,10),(153,'calza negra lisa mimo',250,0,0,NULL,10),(154,'calza lisa fuxia',250,0,0,NULL,10),(155,'bota marron alta calada',1200,0,0,NULL,3),(156,'bota baja con corderito',900,0,0,NULL,3),(157,'camisa saten rayada',250,0,0,NULL,8),(158,'tapado bordo tipo impermeable',500,0,0,NULL,8),(159,'zapa rebook Roja y gris',1000,0,0,NULL,8),(160,'zapa náutica negras',800,0,0,NULL,8);
/*!40000 ALTER TABLE `prenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proovedores`
--

DROP TABLE IF EXISTS `proovedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proovedores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `codigo` varchar(20) COLLATE utf8mb4_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proovedores`
--

LOCK TABLES `proovedores` WRITE;
/*!40000 ALTER TABLE `proovedores` DISABLE KEYS */;
INSERT INTO `proovedores` VALUES (1,'natalia gimenez','102'),(2,'nati corti','105'),(3,'nani daud','110'),(4,'paula viera','114'),(5,'jime ballerini','121'),(6,'marina bonavitta','122'),(7,'natalia macini','123'),(8,'laura grossi','130'),(9,'nueva sin nombre','131'),(10,'pao frisen','133');
/*!40000 ALTER TABLE `proovedores` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-09  5:24:35
