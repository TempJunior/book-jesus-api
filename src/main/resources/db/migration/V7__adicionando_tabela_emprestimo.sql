CREATE TABLE tb_emprestimo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    id_livro BIGINT NOT NULL,
    data_emprestimo DATE NOT NULL,
    data_de_devolucao DATE,
    status ENUM('ATIVO', 'FINALIZADO') DEFAULT 'ATIVO',

    CONSTRAINT fk_emprestimo_usuario FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id) ON DELETE CASCADE,
    CONSTRAINT fk_emprestimo_livro FOREIGN KEY (id_livro) REFERENCES tb_livro(id) ON DELETE CASCADE
);
