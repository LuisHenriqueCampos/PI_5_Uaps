-- MySQL dump 10.13  Distrib 5.5.35, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: uaps
-- ------------------------------------------------------
-- Server version	5.5.35-0ubuntu0.13.10.2

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
-- Table structure for table `EspecialidadeEncaminhamento`
--

DROP TABLE IF EXISTS `EspecialidadeEncaminhamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EspecialidadeEncaminhamento` (
  `IdEspecialidade` int(11) NOT NULL,
  `descricao` varchar(45) NOT NULL,
  PRIMARY KEY (`IdEspecialidade`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EspecialidadeEncaminhamento`
--

LOCK TABLES `EspecialidadeEncaminhamento` WRITE;
/*!40000 ALTER TABLE `EspecialidadeEncaminhamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `EspecialidadeEncaminhamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agentesaude`
--

DROP TABLE IF EXISTS `agentesaude`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agentesaude` (
  `IdPessoaAgenteSaude` int(11) NOT NULL,
  `matricula` int(11) NOT NULL,
  `IdMicroArea` int(11) NOT NULL,
  PRIMARY KEY (`IdPessoaAgenteSaude`),
  KEY `fk_agentesaude_pessoa` (`IdPessoaAgenteSaude`),
  KEY `fk_agentesaude_microarea` (`IdMicroArea`),
  CONSTRAINT `fk_agentesaude_microarea` FOREIGN KEY (`IdMicroArea`) REFERENCES `microarea` (`IdMicroArea`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_agentesaude_pessoa` FOREIGN KEY (`IdPessoaAgenteSaude`) REFERENCES `pessoa` (`IdPessoa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agentesaude`
--

LOCK TABLES `agentesaude` WRITE;
/*!40000 ALTER TABLE `agentesaude` DISABLE KEYS */;
/*!40000 ALTER TABLE `agentesaude` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `area` (
  `IdArea` tinyint(4) NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(10) NOT NULL,
  `IdPostoSaude` tinyint(4) NOT NULL,
  `medicoenfermeira_idMedico` int(11) NOT NULL,
  `medicoenfermeira_idEnfermeira` int(11) NOT NULL,
  PRIMARY KEY (`IdArea`),
  KEY `fk_area_postosaude` (`IdPostoSaude`),
  KEY `fk_area_idMedico` (`medicoenfermeira_idMedico`),
  KEY `fk_area_idEnfermeira` (`medicoenfermeira_idEnfermeira`),
  CONSTRAINT `fk_area_medicoenfermeira1` FOREIGN KEY (`medicoenfermeira_idEnfermeira`) REFERENCES `medicoenfermeira` (`IdPessoaMedicoEnfermeira`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_area_medicoenfermeira2` FOREIGN KEY (`medicoenfermeira_idMedico`) REFERENCES `medicoenfermeira` (`IdPessoaMedicoEnfermeira`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_area_postosaude` FOREIGN KEY (`IdPostoSaude`) REFERENCES `postosaude` (`IdPostoSaude`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES (1,'Area 1',1,1,1);
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `atribuicao`
--

DROP TABLE IF EXISTS `atribuicao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `atribuicao` (
  `IdAtribuicao` tinyint(4) NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(20) NOT NULL,
  PRIMARY KEY (`IdAtribuicao`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atribuicao`
--

LOCK TABLES `atribuicao` WRITE;
/*!40000 ALTER TABLE `atribuicao` DISABLE KEYS */;
INSERT INTO `atribuicao` VALUES (1,'Pedagoga');
/*!40000 ALTER TABLE `atribuicao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bairro`
--

DROP TABLE IF EXISTS `bairro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bairro` (
  `IdBairro` int(11) NOT NULL AUTO_INCREMENT,
  `Bairro` varchar(50) NOT NULL,
  PRIMARY KEY (`IdBairro`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bairro`
--

LOCK TABLES `bairro` WRITE;
/*!40000 ALTER TABLE `bairro` DISABLE KEYS */;
INSERT INTO `bairro` VALUES (1,'Val paraiso');
/*!40000 ALTER TABLE `bairro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `encaminhamento`
--

DROP TABLE IF EXISTS `encaminhamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `encaminhamento` (
  `IdEncaminhamento` int(11) NOT NULL AUTO_INCREMENT,
  `DataEncaminhamento` date NOT NULL,
  `MotivoEncaminhamento` text,
  `IdtipoEncaminhamento` tinyint(4) NOT NULL,
  `IdPessoaPaciente` int(11) NOT NULL,
  `IdEspecialidade` int(11) NOT NULL,
  `IdPessoaMedicoEnfermeira` int(11) NOT NULL,
  PRIMARY KEY (`IdEncaminhamento`),
  KEY `fk_encaminhamento_tipoEncaminhamento` (`IdtipoEncaminhamento`),
  KEY `fk_encaminhamento_paciente` (`IdPessoaPaciente`),
  KEY `fk_encaminhamento_EspecialidadeEncaminhamento` (`IdEspecialidade`),
  KEY `fk_encaminhamento_medicoenfermeira` (`IdPessoaMedicoEnfermeira`),
  CONSTRAINT `fk_encaminhamento_EspecialidadeEncaminhamento` FOREIGN KEY (`IdEspecialidade`) REFERENCES `EspecialidadeEncaminhamento` (`IdEspecialidade`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_encaminhamento_medicoenfermeira` FOREIGN KEY (`IdPessoaMedicoEnfermeira`) REFERENCES `medicoenfermeira` (`IdPessoaMedicoEnfermeira`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_encaminhamento_paciente` FOREIGN KEY (`IdPessoaPaciente`) REFERENCES `paciente` (`IdPessoaPaciente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_encaminhamento_tipoEncaminhamento` FOREIGN KEY (`IdtipoEncaminhamento`) REFERENCES `tipoencaminhamento` (`IdtipoEncaminhamento`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `encaminhamento`
--

LOCK TABLES `encaminhamento` WRITE;
/*!40000 ALTER TABLE `encaminhamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `encaminhamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco` (
  `IdEndereco` int(11) NOT NULL AUTO_INCREMENT,
  `Rua` varchar(100) NOT NULL,
  `Cep` varchar(9) NOT NULL,
  `IdBairro` int(11) NOT NULL,
  `IdMicroArea` int(11) NOT NULL,
  PRIMARY KEY (`IdEndereco`),
  KEY `fk_endereco_bairro` (`IdBairro`),
  KEY `fk_endereco_microArea` (`IdMicroArea`),
  CONSTRAINT `fk_endereco_bairro` FOREIGN KEY (`IdBairro`) REFERENCES `bairro` (`IdBairro`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_endereco_microArea` FOREIGN KEY (`IdMicroArea`) REFERENCES `microarea` (`IdMicroArea`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (1,'Francisco Faria','38703060',1,1);
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `familia`
--

DROP TABLE IF EXISTS `familia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `familia` (
  `IdFamilia` int(11) NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(10) NOT NULL,
  `Complemento` varchar(40) DEFAULT NULL,
  `IdEndereco` int(11) NOT NULL,
  PRIMARY KEY (`IdFamilia`),
  KEY `fk_familia_endereco` (`IdEndereco`),
  CONSTRAINT `fk_familia_endereco` FOREIGN KEY (`IdEndereco`) REFERENCES `endereco` (`IdEndereco`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `familia`
--

LOCK TABLES `familia` WRITE;
/*!40000 ALTER TABLE `familia` DISABLE KEYS */;
/*!40000 ALTER TABLE `familia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicoenfermeira`
--

DROP TABLE IF EXISTS `medicoenfermeira`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicoenfermeira` (
  `IdPessoaMedicoEnfermeira` int(11) NOT NULL,
  `Assinatura` mediumtext NOT NULL,
  `Registro` int(11) NOT NULL,
  `IdAtribuicao` tinyint(4) NOT NULL,
  PRIMARY KEY (`IdPessoaMedicoEnfermeira`),
  KEY `fk_medicoenfermeira_atribuicao` (`IdAtribuicao`),
  KEY `fk_medicoenfermeira_pessoa` (`IdPessoaMedicoEnfermeira`),
  CONSTRAINT `fk_medicoenfermeira_atribuicao` FOREIGN KEY (`IdAtribuicao`) REFERENCES `atribuicao` (`IdAtribuicao`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_medicoenfermeira_pessoa` FOREIGN KEY (`IdPessoaMedicoEnfermeira`) REFERENCES `pessoa` (`IdPessoa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicoenfermeira`
--

LOCK TABLES `medicoenfermeira` WRITE;
/*!40000 ALTER TABLE `medicoenfermeira` DISABLE KEYS */;
INSERT INTO `medicoenfermeira` VALUES (1,'Assinatura',123123,1);
/*!40000 ALTER TABLE `medicoenfermeira` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `microarea`
--

DROP TABLE IF EXISTS `microarea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `microarea` (
  `IdMicroArea` int(11) NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(10) NOT NULL,
  `IdArea` tinyint(4) NOT NULL,
  PRIMARY KEY (`IdMicroArea`),
  KEY `fk_microArea_area` (`IdArea`),
  CONSTRAINT `fk_microArea_area` FOREIGN KEY (`IdArea`) REFERENCES `area` (`IdArea`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `microarea`
--

LOCK TABLES `microarea` WRITE;
/*!40000 ALTER TABLE `microarea` DISABLE KEYS */;
INSERT INTO `microarea` VALUES (1,'micro-Area',1);
/*!40000 ALTER TABLE `microarea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paciente` (
  `IdPessoaPaciente` int(11) NOT NULL AUTO_INCREMENT,
  `DataNascimento` date NOT NULL,
  `NomeMae` varchar(80) NOT NULL,
  `NomePai` varchar(80) DEFAULT NULL,
  `Cns` bigint(20) NOT NULL,
  `Telefone` varchar(11) DEFAULT NULL,
  `IdSexo` tinyint(4) NOT NULL,
  `IdFamilia` int(11) NOT NULL,
  PRIMARY KEY (`IdPessoaPaciente`),
  KEY `fk_paciente_pessoa` (`IdPessoaPaciente`),
  KEY `fk_paciente_sexo` (`IdSexo`),
  KEY `fk_paciente_familia` (`IdFamilia`),
  CONSTRAINT `fk_paciente_familia` FOREIGN KEY (`IdFamilia`) REFERENCES `familia` (`IdFamilia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_paciente_pessoa` FOREIGN KEY (`IdPessoaPaciente`) REFERENCES `pessoa` (`IdPessoa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_paciente_sexo` FOREIGN KEY (`IdSexo`) REFERENCES `sexo` (`IdSexo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente`
--

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pessoa` (
  `IdPessoa` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  PRIMARY KEY (`IdPessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,'Gabriel Petrovick');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postosaude`
--

DROP TABLE IF EXISTS `postosaude`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `postosaude` (
  `IdPostoSaude` tinyint(4) NOT NULL AUTO_INCREMENT,
  `NomePosto` varchar(100) NOT NULL,
  PRIMARY KEY (`IdPostoSaude`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postosaude`
--

LOCK TABLES `postosaude` WRITE;
/*!40000 ALTER TABLE `postosaude` DISABLE KEYS */;
INSERT INTO `postosaude` VALUES (1,'Val Paraiso'),(2,'California');
/*!40000 ALTER TABLE `postosaude` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sexo`
--

DROP TABLE IF EXISTS `sexo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sexo` (
  `IdSexo` tinyint(4) NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(10) NOT NULL,
  PRIMARY KEY (`IdSexo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sexo`
--

LOCK TABLES `sexo` WRITE;
/*!40000 ALTER TABLE `sexo` DISABLE KEYS */;
/*!40000 ALTER TABLE `sexo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoencaminhamento`
--

DROP TABLE IF EXISTS `tipoencaminhamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipoencaminhamento` (
  `IdtipoEncaminhamento` tinyint(4) NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(20) NOT NULL,
  PRIMARY KEY (`IdtipoEncaminhamento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoencaminhamento`
--

LOCK TABLES `tipoencaminhamento` WRITE;
/*!40000 ALTER TABLE `tipoencaminhamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipoencaminhamento` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-25 23:32:03
