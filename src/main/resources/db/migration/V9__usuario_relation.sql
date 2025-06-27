-- 1. Deleta os dados (na ordem correta para evitar conflito de FK)
DELETE FROM tb_usuario_account;
DELETE FROM tb_usuario;

-- 2. Adiciona a coluna de relacionamento
ALTER TABLE tb_usuario_account
ADD COLUMN usuario_id BIGINT;

-- 3. Cria a chave estrangeira entre tb_usuario_account e tb_usuario
ALTER TABLE tb_usuario_account
ADD CONSTRAINT fk_usuario_account_usuario
FOREIGN KEY (usuario_id)
REFERENCES tb_usuario(id)
ON DELETE CASCADE;
