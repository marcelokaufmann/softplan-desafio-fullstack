INSERT INTO tb_usuario (id, nome, login, senha, tipo) VALUES (1, 'Marcelo', 'marcelo', '123', 'adm');
INSERT INTO tb_usuario (id, nome, login, senha, tipo) VALUES (2, 'Augusto', 'augusto', '456', 'triador');
INSERT INTO tb_usuario (id, nome, login, senha, tipo) VALUES (3, 'Kaufmann', 'kaufmann', '789', 'finalizador');
INSERT INTO tb_processo (id, descricao, parecer, pendente) VALUES (1, 'Processo 1', 'teste 1', false);
INSERT INTO tb_processo (id, descricao, parecer, pendente) VALUES (2, 'Processo 2', '', true);
INSERT INTO tb_processo (id, descricao, parecer, pendente) VALUES (3, 'Processo 3', 'teste 3', false);
INSERT INTO tb_usuario_processo (id, usuario_id, processo_id, data) VALUES (1, 3, 1, '2021-01-30');
INSERT INTO tb_usuario_processo (id, usuario_id, processo_id, data) VALUES (2, 3, 3, '2021-01-31');