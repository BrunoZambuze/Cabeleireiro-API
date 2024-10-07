CREATE TABLE cliente (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(80) NOT NULL,
    email VARCHAR(50) NOT NULL,
    data_nascimento DATE NOT NULL,
    dataAgendamento DATETIME NOT NULL,
    dataPrevista DATETIME NOT NULL,
    statusAgendamento VARCHAR(15) NOT NULL
);