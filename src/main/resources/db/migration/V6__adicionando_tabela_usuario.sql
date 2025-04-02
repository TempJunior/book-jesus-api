CREATE TABLE tb_usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(250),
    email VARCHAR(250) UNIQUE,
    telefone BIGINT,
    data_de_registro VARCHAR (250)
)