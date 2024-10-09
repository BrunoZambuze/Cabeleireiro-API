CREATE TABLE agendamento (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    data_agendamento DATETIME NOT NULL,
    data_prevista DATETIME NOT NULL,
    status_agendamento VARCHAR(15) NOT NULL,
    fk_id_profissional INT NOT NULL,
    fk_id_cliente INT NOT NULL,
    FOREIGN KEY (fk_id_profissional) REFERENCES profissional (id),
    FOREIGN KEY (fk_id_cliente) REFERENCES cliente (id)
);