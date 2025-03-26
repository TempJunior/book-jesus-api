CREATE TABLE tb_livro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR (250) NOT NULL,
    autor_id BIGINT NOT NULL,
    categoria VARCHAR (250),
    ano_de_publicacao YEAR NOT NULL,
    quantidade_em_estoque BIGINT,

    CONSTRAINT FOREIGN KEY (autor_id) REFERENCES tb_autor(id) ON DELETE CASCADE
);