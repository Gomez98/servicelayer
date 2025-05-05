/*!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19  Distrib 10.11.8-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: goals
-- ------------------------------------------------------
-- Server version	10.11.8-MariaDB-0ubuntu0.24.04.1

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
-- Table structure for table `goal_detail`
--

DROP TABLE IF EXISTS `goal_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goal_detail` (
  `id` varchar(50) NOT NULL,
  `header_id` varchar(50) NOT NULL,
  `content` longtext DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `headerId` (`header_id`),
  CONSTRAINT `goal_detail_ibfk_1` FOREIGN KEY (`header_id`) REFERENCES `goal_header` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goal_detail`
--

LOCK TABLES `goal_detail` WRITE;
/*!40000 ALTER TABLE `goal_detail` DISABLE KEYS */;
INSERT INTO `goal_detail` VALUES
('30a6e439-c79c-4118-bd8e-5e90ab12d412','67e31212-1458-4bfb-828d-5d4316d7c337','[{\"Meta\":1000},{\"Meta\":2000},{\"Meta\":3000},{\"Meta\":4000},{\"Meta\":5000},{\"Meta\":6000},{\"Meta\":7000},{\"Meta\":8000},{\"Meta\":9000},{\"Meta\":2000},{\"Meta\":2000},{\"Meta\":2000},{\"Meta\":3000},{\"Meta\":4000},{\"Meta\":5000},{\"Meta\":6000},{\"Meta\":7000},{\"Meta\":8000}]'),
('68fbd646-4d89-453f-b033-5d4e90586fac','c6fb9bc9-a449-4897-925d-9c6b4213d960','[{\"Zonal\":\"zonal1\",\"Meta\":1000},{\"Zonal\":\"zonal1\",\"Meta\":2000},{\"Zonal\":\"zonal1\",\"Meta\":3000},{\"Zonal\":\"zonal1\",\"Meta\":4000},{\"Zonal\":\"zonal1\",\"Meta\":5000},{\"Zonal\":\"zonal1\",\"Meta\":6000},{\"Zonal\":\"zonal1\",\"Meta\":7000},{\"Zonal\":\"zonal1\",\"Meta\":8000},{\"Zonal\":\"zonal1\",\"Meta\":9000},{\"Zonal\":\"zonal1\",\"Meta\":2000},{\"Zonal\":\"zonal1\",\"Meta\":2000},{\"Zonal\":\"zonal1\",\"Meta\":2000},{\"Zonal\":\"zonal1\",\"Meta\":3000},{\"Zonal\":\"zonal1\",\"Meta\":4000},{\"Zonal\":\"zonal1\",\"Meta\":5000},{\"Zonal\":\"zonal1\",\"Meta\":6000},{\"Zonal\":\"zonal1\",\"Meta\":7000},{\"Zonal\":\"zonal1\",\"Meta\":8000}]'),
('ccac16c6-58b3-4796-bba1-306722d7d0bf','ce9679fb-3ea4-46fa-8526-01b0716593bb','[{\"Zonal\":\"zonal1\",\"Meta\":1000},{\"Zonal\":\"zonal1\",\"Meta\":2000},{\"Zonal\":\"zonal1\",\"Meta\":3000},{\"Zonal\":\"zonal1\",\"Meta\":4000},{\"Zonal\":\"zonal1\",\"Meta\":5000},{\"Zonal\":\"zonal1\",\"Meta\":6000},{\"Zonal\":\"zonal1\",\"Meta\":7000},{\"Zonal\":\"zonal1\",\"Meta\":8000},{\"Zonal\":\"zonal1\",\"Meta\":9000},{\"Zonal\":\"zonal1\",\"Meta\":2000},{\"Zonal\":\"zonal1\",\"Meta\":2000},{\"Zonal\":\"zonal1\",\"Meta\":2000},{\"Zonal\":\"zonal1\",\"Meta\":3000},{\"Zonal\":\"zonal1\",\"Meta\":4000},{\"Zonal\":\"zonal1\",\"Meta\":5000},{\"Zonal\":\"zonal1\",\"Meta\":6000},{\"Zonal\":\"zonal1\",\"Meta\":7000},{\"Zonal\":\"zonal1\",\"Meta\":8000}]');
/*!40000 ALTER TABLE `goal_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goal_header`
--

DROP TABLE IF EXISTS `goal_header`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goal_header` (
  `id` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goal_header`
--

LOCK TABLES `goal_header` WRITE;
/*!40000 ALTER TABLE `goal_header` DISABLE KEYS */;
INSERT INTO `goal_header` VALUES
('3acc5b3b-46ba-4e1f-9a24-910ac4b657f2','META + ZONAL','ggomez','ggomez','PREPARADO'),
('67e31212-1458-4bfb-828d-5d4316d7c337','SOLO CAMPO META','ggomez','ggomez','PREPARADO'),
('c6fb9bc9-a449-4897-925d-9c6b4213d960','sdqwd','ggomez','ggomez','PREPARADO'),
('ce9679fb-3ea4-46fa-8526-01b0716593bb','meta + zonal 2','ggomez','ggomez','PREPARADO');
/*!40000 ALTER TABLE `goal_header` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `master_fields`
--

DROP TABLE IF EXISTS `master_fields`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `master_fields` (
  `id` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `master_fields`
--

LOCK TABLES `master_fields` WRITE;
/*!40000 ALTER TABLE `master_fields` DISABLE KEYS */;
INSERT INTO `master_fields` VALUES
('3d5b9fea-195e-4410-bbeb-bf373b9dde08','zonal',0),
('3d7fc1c9-baed-4d53-adc3-0adeefdf8051','Meta',1),
('44db1ea3-8362-4b49-bce0-cbcbff24895c','Zonal',1),
('5668327a-754e-431c-9c00-18eed3d47ab3','nuevo1',1),
('5670b4e5-6f3c-4915-b884-51eb95c1fa38','Meta',1);
/*!40000 ALTER TABLE `master_fields` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` char(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES
('c26d16ec-faca-11ef-96ae-00163ed344ca','PERMISO_ACTUALIZAR_CAMPO_MAESTRO'),
('c26d2260-faca-11ef-96ae-00163ed344ca','PERMISO_ACTUALIZAR_DETALLE_METAS'),
('c26d26b4-faca-11ef-96ae-00163ed344ca','PERMISO_AGREGAR_PERMISO_AL_ROL'),
('c26d27a0-faca-11ef-96ae-00163ed344ca','PERMISO_CONVERTIR_ARCHIVO'),
('c26d27f9-faca-11ef-96ae-00163ed344ca','PERMISO_CREAR_CAMPO_MAESTRO'),
('c26d2843-faca-11ef-96ae-00163ed344ca','PERMISO_CREAR_DETALLE_METAS'),
('c26d288d-faca-11ef-96ae-00163ed344ca','PERMISO_CREAR_METAS'),
('c26d28cf-faca-11ef-96ae-00163ed344ca','PERMISO_CREAR_PERMISOS'),
('c26d2b27-faca-11ef-96ae-00163ed344ca','PERMISO_CREAR_ROLES'),
('c26d2b92-faca-11ef-96ae-00163ed344ca','PERMISO_CREAR_USUARIO'),
('c26d2c15-faca-11ef-96ae-00163ed344ca','PERMISO_ELIMINAR_PERMISOS'),
('c26d2bd5-faca-11ef-96ae-00163ed344ca','PERMISO_ELIMINAR_PERMISO_AL_ROL'),
('c26d2c63-faca-11ef-96ae-00163ed344ca','PERMISO_ELIMINAR_USUARIO'),
('c26d2ced-faca-11ef-96ae-00163ed344ca','PERMISO_MODIFICAR_USUARIO'),
('c26d2d42-faca-11ef-96ae-00163ed344ca','PERMISO_VER_CAMPOS_MAESTROS'),
('c26d2d99-faca-11ef-96ae-00163ed344ca','PERMISO_VER_DETALLE_METAS'),
('c26d2dea-faca-11ef-96ae-00163ed344ca','PERMISO_VER_METAS'),
('c26d2e28-faca-11ef-96ae-00163ed344ca','PERMISO_VER_PERMISOS'),
('c26d2e62-faca-11ef-96ae-00163ed344ca','PERMISO_VER_REPORTE'),
('c26d300f-faca-11ef-96ae-00163ed344ca','PERMISO_VER_ROLES');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` char(36) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES
('f52114d1-faca-11ef-96ae-00163ed344ca','ROLE_ADMIN'),
('f5211d61-faca-11ef-96ae-00163ed344ca','ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permissions`
--

DROP TABLE IF EXISTS `role_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permissions` (
  `role_id` char(36) NOT NULL,
  `permission_id` char(36) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `permission_id` (`permission_id`),
  CONSTRAINT `role_permissions_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE,
  CONSTRAINT `role_permissions_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permissions`
--

LOCK TABLES `role_permissions` WRITE;
/*!40000 ALTER TABLE `role_permissions` DISABLE KEYS */;
INSERT INTO `role_permissions` VALUES
('f52114d1-faca-11ef-96ae-00163ed344ca','c26d16ec-faca-11ef-96ae-00163ed344ca'),
('f52114d1-faca-11ef-96ae-00163ed344ca','c26d2260-faca-11ef-96ae-00163ed344ca'),
('f52114d1-faca-11ef-96ae-00163ed344ca','c26d26b4-faca-11ef-96ae-00163ed344ca'),
('f52114d1-faca-11ef-96ae-00163ed344ca','c26d27a0-faca-11ef-96ae-00163ed344ca'),
('f52114d1-faca-11ef-96ae-00163ed344ca','c26d27f9-faca-11ef-96ae-00163ed344ca'),
('f52114d1-faca-11ef-96ae-00163ed344ca','c26d2843-faca-11ef-96ae-00163ed344ca'),
('f52114d1-faca-11ef-96ae-00163ed344ca','c26d288d-faca-11ef-96ae-00163ed344ca'),
('f52114d1-faca-11ef-96ae-00163ed344ca','c26d28cf-faca-11ef-96ae-00163ed344ca'),
('f52114d1-faca-11ef-96ae-00163ed344ca','c26d2b27-faca-11ef-96ae-00163ed344ca'),
('f52114d1-faca-11ef-96ae-00163ed344ca','c26d2b92-faca-11ef-96ae-00163ed344ca'),
('f52114d1-faca-11ef-96ae-00163ed344ca','c26d2bd5-faca-11ef-96ae-00163ed344ca'),
('f52114d1-faca-11ef-96ae-00163ed344ca','c26d2c15-faca-11ef-96ae-00163ed344ca'),
('f52114d1-faca-11ef-96ae-00163ed344ca','c26d2c63-faca-11ef-96ae-00163ed344ca'),
('f52114d1-faca-11ef-96ae-00163ed344ca','c26d2ced-faca-11ef-96ae-00163ed344ca'),
('f52114d1-faca-11ef-96ae-00163ed344ca','c26d2d42-faca-11ef-96ae-00163ed344ca'),
('f52114d1-faca-11ef-96ae-00163ed344ca','c26d2d99-faca-11ef-96ae-00163ed344ca'),
('f52114d1-faca-11ef-96ae-00163ed344ca','c26d2dea-faca-11ef-96ae-00163ed344ca'),
('f52114d1-faca-11ef-96ae-00163ed344ca','c26d2e28-faca-11ef-96ae-00163ed344ca'),
('f52114d1-faca-11ef-96ae-00163ed344ca','c26d2e62-faca-11ef-96ae-00163ed344ca'),
('f52114d1-faca-11ef-96ae-00163ed344ca','c26d300f-faca-11ef-96ae-00163ed344ca');
/*!40000 ALTER TABLE `role_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` varchar(50) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES
('4c2771f1-e38e-4039-bcb9-6c938a20c827','ggomez','$2a$10$3c1ui9bhURmbPMQ5.ipsOul4OBhOaEaWkiGWUJvplCoFqOFOx0CA6','german'),
('e768f2ff-ebdb-437d-ba98-54955dd889fb','Kevin','$2a$10$vPo2cLloVnhsgQkUUXZXPeikKWU3ZXF263SgwAUIJ9ehdk2BAJuQW','Kevin Chacon');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_id` char(36) NOT NULL,
  `role_id` char(36) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES
('e768f2ff-ebdb-437d-ba98-54955dd889fb','f52114d1-faca-11ef-96ae-00163ed344ca');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-05 15:08:12
