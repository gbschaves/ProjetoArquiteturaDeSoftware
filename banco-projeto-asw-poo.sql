drop database projeto_asw_poo;

create database projeto_asw_poo;
use projeto_asw_poo;

CREATE TABLE `area` (
  `idarea` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`idarea`),
  UNIQUE KEY `idarea_UNIQUE` (`idarea`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `subarea` (
  `idsubarea` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `area_idarea` int NOT NULL,
  PRIMARY KEY (`idsubarea`),
  UNIQUE KEY `idsubarea_UNIQUE` (`idsubarea`),
  CONSTRAINT `fk_subarea_area` FOREIGN KEY (`area_idarea`) REFERENCES `area` (`idarea`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pessoa` (
    `idpessoa` INT AUTO_INCREMENT,
    `nome` VARCHAR(100),
    `email` VARCHAR(100),
    `login` VARCHAR(45) UNIQUE,
    `senha`  VARCHAR(45),
    PRIMARY KEY (`idpessoa`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `artigo` (
  `idartigo` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) NOT NULL,
  `resumo` text NOT NULL,
  `palavrasChave` varchar(45) NOT NULL,
  `envolveHumano` boolean NOT NULL,
  `processoPlataformaBrasil` varchar(45) DEFAULT NULL,
  `idarea` int NOT NULL,
  `idsubarea` int NOT NULL,
  PRIMARY KEY (`idartigo`),
  UNIQUE KEY `idartigo_UNIQUE` (`idartigo`),
  CONSTRAINT `fk_artigo_area` FOREIGN KEY (`idarea`) REFERENCES `area` (`idarea`),
  CONSTRAINT `fk_artigo_subarea` FOREIGN KEY (`idsubarea`) REFERENCES `subarea` (`idsubarea`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `autor` (
  `idautor` int NOT NULL AUTO_INCREMENT,
  `cpf` varchar(20) NOT NULL,
  PRIMARY KEY (`idautor`),
  FOREIGN KEY (idautor) REFERENCES pessoa(idpessoa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `revisor` (
  `idrevisor` int NOT NULL AUTO_INCREMENT,
  `telefone` varchar(45) NOT NULL,
  `cpf` varchar(45),
  `rne` varchar(45),
  `lattes` varchar(45) NOT NULL,
  `instituicaoQueTrabalha` varchar(45) NOT NULL,
  `researchId` varchar(45) NOT NULL,
  PRIMARY KEY (`idrevisor`),
  FOREIGN KEY (idrevisor) REFERENCES pessoa(idpessoa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `autor_has_artigo` (
  `artigo_idartigo` int NOT NULL,
  `autor_idautor` int NOT NULL,
  `eResponsavelPeloArtigo` boolean NOT NULL,
  `telefone` varchar(45),
  PRIMARY KEY (`artigo_idartigo`,`autor_idautor`),
  KEY `fk_artigo_has_autor_autor1_idx` (`autor_idautor`),
  KEY `fk_artigo_has_autor_artigo1_idx` (`artigo_idartigo`),
  CONSTRAINT `fk_artigo_has_autor_artigo1_idx` FOREIGN KEY (`artigo_idartigo`) REFERENCES `artigo` (`idartigo`),
  CONSTRAINT `fk_artigo_has_autor_autor1_idx` FOREIGN KEY (`autor_idautor`) REFERENCES `autor` (`idautor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `avaliacao` (
  `artigo_idartigo` int NOT NULL,
  `revisor_idrevisor` int NOT NULL,
  `nota` double NOT NULL,
  `observacao` varchar(100) NOT NULL,
  PRIMARY KEY (`artigo_idartigo`,`revisor_idrevisor`),
  KEY `fk_avaliacao_idrevisor` (`revisor_idrevisor`),
  KEY `fk_avaliacao_idartigo` (`artigo_idartigo`),
  CONSTRAINT `fk_avaliacao_idartigo` FOREIGN KEY (`artigo_idartigo`) REFERENCES `artigo` (`idartigo`),
  CONSTRAINT `fk_avaliacao_idrevisor` FOREIGN KEY (`revisor_idrevisor`) REFERENCES `revisor` (`idrevisor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO area (nome) VALUES ('Tecnologia');
INSERT INTO area (nome) VALUES ('Natureza');
INSERT INTO area (nome) VALUES ('Matemática');

INSERT INTO subarea (nome, area_idarea) VALUES('Programação web', 1);
INSERT INTO subarea (nome, area_idarea) VALUES('Data science', 1);
INSERT INTO subarea (nome, area_idarea) VALUES('Aquecimento global', 2);
INSERT INTO subarea (nome, area_idarea) VALUES('Emissão de gás carbono por fábricas', 2);
INSERT INTO subarea (nome, area_idarea) VALUES('Cálculo 4', 3);
INSERT INTO subarea (nome, area_idarea) VALUES('Teoria dos conjuntos', 3);

select * from area;
select * from pessoa;
select * from artigo;
select * from autor_has_artigo; 
select * from autor;
select * from avaliacao;
select * from revisor;
select * from subarea;


                








