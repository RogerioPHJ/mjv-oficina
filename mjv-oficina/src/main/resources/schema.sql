DROP TABLE IF EXISTS TB_REGISTRO_PROBLEMA;
DROP TABLE IF EXISTS TB_PROBLEMA;
DROP TABLE IF EXISTS TB_DEFEITO_PECA;
DROP TABLE IF EXISTS TB_PECA_VEICULO;
DROP TABLE IF EXISTS TB_DEFEITO;
DROP TABLE IF EXISTS TB_PECA;
DROP TABLE IF EXISTS TB_VEICULO;
DROP TABLE IF EXISTS TB_REGISTRO;

CREATE TABLE IF NOT EXISTS TB_DEFEITO(
	idDefeito INT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS TB_PECA(
	idPeca INT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS TB_VEICULO(
	idVeiculo INT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS TB_REGISTRO(
	idRegistro INT AUTO_INCREMENT PRIMARY KEY,
	nomeCliente VARCHAR(70) NOT NULL,
	data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	nomeVeiculo VARCHAR(70) NOT NULL
);

CREATE TABLE IF NOT EXISTS TB_DEFEITO_PECA(
	idPecaDefeito INT AUTO_INCREMENT PRIMARY KEY,
	fkIdDefeito INT NOT NULL,
	fkIdPeca INT NOT NULL,
	FOREIGN KEY (fkIdDefeito) REFERENCES TB_DEFEITO(idDefeito),
	FOREIGN KEY (fkIdPeca) REFERENCES TB_PECA(idPeca)
);

CREATE TABLE IF NOT EXISTS TB_PECA_VEICULO(
	idPecaVeiculo INT AUTO_INCREMENT PRIMARY KEY,
	fkIdPeca INT NOT NULL,
	fkIdVeiculo INT NOT NULL,
	FOREIGN KEY (fkIdPeca) REFERENCES TB_PECA(idPeca),
	FOREIGN KEY (fkIdVeiculo) REFERENCES TB_VEICULO(idVeiculo)
);

CREATE TABLE IF NOT EXISTS TB_PROBLEMA(
	idProblema INT AUTO_INCREMENT PRIMARY KEY,
	fkIdDefeito INT NOT NUll,
	fkIdPeca INT NOT NULL,
	fkIdVeiculo INT NOT NULL,
	FOREIGN KEY (fkIdDefeito) REFERENCES TB_DEFEITO(idDefeito),
	FOREIGN KEY (fkIdPeca) REFERENCES TB_PECA(idPeca),
	FOREIGN KEY (fkIdVeiculo) REFERENCES TB_VEICULO(idVeiculo)
);

CREATE TABLE IF NOT EXISTS TB_REGISTRO_PROBLEMA (
	fkIdProblema INT NOT NUll,
	fkIdRegistro INT NOT NULL,
	FOREIGN KEY (fkIdProblema) REFERENCES TB_PROBLEMA(idProblema),
	FOREIGN KEY (fkIdRegistro) REFERENCES TB_REGISTRO(idRegistro)
);

INSERT INTO TB_DEFEITO (nome) VALUES
('Quebrado'), 
('Enferrujado'), 
('Furado'), 
('Amassado');

INSERT INTO TB_PECA (nome) VALUES
('Motor'), 
('Pneu'), 
('Porta'), 
('Volante'), 
('Roda'), 
('Eixo'), 
('Asa'), 
('Casco'), 
('Banco'); 

INSERT INTO TB_VEICULO (nome) VALUES
('Carro'),
('Moto'), 
('Navio'), 
('Avião'); 

INSERT INTO TB_DEFEITO_PECA(fkIdDefeito, fkIdPeca) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(2, 1),
(2, 3),
(2, 5),
(2, 6),
(2, 7),
(2, 8),
(3, 2),
(3, 3),
(3, 4),
(3, 5),
(3, 6),
(3, 7),
(3, 8),
(3, 9),
(4, 3),
(4, 5),
(4, 6),
(4, 8);

INSERT INTO TB_PECA_VEICULO(fkIdPeca, fkIdVeiculo ) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 1),
(2, 2),
(2, 4),
(3, 1),
(3, 3),
(3, 4),
(4, 1),
(5, 1),
(5, 2),
(5, 4),
(6, 1),
(6, 4),
(7, 4),
(8, 3),
(9, 1),
(9, 2),
(9, 3),
(9, 4);

INSERT INTO TB_PROBLEMA(fkIdDefeito, fkIdPeca, fkIdVeiculo ) VALUES
(1, 1, 1),
(1, 1, 2),
(1, 1, 3),
(1, 1, 4),
(1, 3, 1),
(1, 3, 3),
(1, 3, 4),
(1, 5, 1),
(1, 5, 2),
(1, 5, 4),
(1, 6, 1),
(1, 6, 4),
(1, 7, 4),
(1, 8, 3),
(1, 9, 1),
(1, 9, 2),
(1, 9, 3),
(1, 9, 4),
(2, 1, 1),
(2, 1, 2),
(2, 1, 3),
(2, 1, 4),
(2, 2, 1),
(2, 2, 2),
(2, 2, 4),
(2, 3, 1),
(2, 3, 3),
(2, 3, 4),
(2, 4, 1),
(2, 5, 1),
(2, 5, 2),
(2, 5, 4),
(2, 6, 1),
(2, 6, 4),
(2, 7, 4),
(2, 8, 3),
(2, 9, 1),
(2, 9, 2),
(2, 9, 3),
(2, 9, 4),
(3, 2, 1),
(3, 2, 2),
(3, 2, 4),
(3, 3, 1),
(3, 3, 3),
(3, 3, 4),
(3, 4, 1),
(3, 5, 1),
(3, 5, 2),
(3, 5, 4),
(3, 6, 1),
(3, 6, 4),
(3, 7, 4),
(3, 8, 3),
(3, 9, 1),
(3, 9, 2),
(3, 9, 3),
(3, 9, 4),
(4, 3, 1),
(4, 3, 3),
(4, 3, 4),
(4, 5, 1),
(4, 5, 2),
(4, 5, 4),
(4, 6, 1),
(4, 6, 4),
(4, 8, 3);

INSERT INTO TB_REGISTRO (nomeCliente, data, nomeVeiculo) VALUES
('Fher Kassino', '2020-09-21 21:14:23', 'Carro'),
('Olga da Silva', '2020-11-04 14:12:38', 'Moto'),
('Tristano de Boninho', '2020-01-14 12:21:51', 'Navio'),
('Olga da Silva', '2020-11-16 17:25:48','Avião'),
('Fher Kassino', '2020-11-21 04:21:30', 'Carro');

INSERT INTO TB_REGISTRO_PROBLEMA (fkIdRegistro, fkIdProblema) VALUES
(1, 1),
(1, 5),
(1, 8),
(2, 2),
(2, 9),
(3, 3),
(3, 6),
(4, 7),
(5, 5);