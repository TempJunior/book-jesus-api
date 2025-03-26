ALTER TABLE tb_autor
ADD COLUMN livro_id BIGINT;

ALTER TABLE tb_autor
ADD CONSTRAINT fk_livro FOREIGN KEY (livro_id) REFERENCES tb_livro(id);