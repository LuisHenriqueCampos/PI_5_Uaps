CREATE TABLE `EspecialidadeEncaminhamento` (
  `IdEspecialidade` int(11) NOT NULL,
  `descricao` varchar(45) NOT NULL,
  PRIMARY KEY (`IdEspecialidade`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

CREATE TABLE `atribuicao` (
  `IdAtribuicao` tinyint(4) NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(20) NOT NULL,
  PRIMARY KEY (`IdAtribuicao`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `bairro` (
  `IdBairro` int(11) NOT NULL AUTO_INCREMENT,
  `Bairro` varchar(50) NOT NULL,
  PRIMARY KEY (`IdBairro`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

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

CREATE TABLE `familia` (
  `IdFamilia` int(11) NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(10) NOT NULL,
  `Complemento` varchar(40) DEFAULT NULL,
  `IdEndereco` int(11) NOT NULL,
  PRIMARY KEY (`IdFamilia`),
  KEY `fk_familia_endereco` (`IdEndereco`),
  CONSTRAINT `fk_familia_endereco` FOREIGN KEY (`IdEndereco`) REFERENCES `endereco` (`IdEndereco`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

CREATE TABLE `microarea` (
  `IdMicroArea` int(11) NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(10) NOT NULL,
  `IdArea` tinyint(4) NOT NULL,
  PRIMARY KEY (`IdMicroArea`),
  KEY `fk_microArea_area` (`IdArea`),
  CONSTRAINT `fk_microArea_area` FOREIGN KEY (`IdArea`) REFERENCES `area` (`IdArea`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

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

CREATE TABLE `pessoa` (
  `IdPessoa` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  PRIMARY KEY (`IdPessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `postosaude` (
  `IdPostoSaude` tinyint(4) NOT NULL AUTO_INCREMENT,
  `NomePosto` varchar(100) NOT NULL,
  PRIMARY KEY (`IdPostoSaude`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

CREATE TABLE `sexo` (
  `IdSexo` tinyint(4) NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(10) NOT NULL,
  PRIMARY KEY (`IdSexo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `tipoencaminhamento` (
  `IdtipoEncaminhamento` tinyint(4) NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(20) NOT NULL,
  PRIMARY KEY (`IdtipoEncaminhamento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

