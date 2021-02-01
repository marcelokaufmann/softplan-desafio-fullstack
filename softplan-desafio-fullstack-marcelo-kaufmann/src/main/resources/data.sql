INSERT INTO tb_usuario (login, nome_completo, senha) VALUES ('marcelo', 'Marcelo Adm', '123');
INSERT INTO tb_usuario (login, nome_completo, senha) VALUES ('augusto', 'Augusto Triador', '456');
INSERT INTO tb_usuario (login, nome_completo, senha) VALUES ('kaufmann', 'Kaufmann Finalizador','789');
INSERT INTO tb_processo (id, descricao, parecer, pendente) VALUES (1, 'Processo 1', 'teste 1', false);
INSERT INTO tb_processo (id, descricao, parecer, pendente) VALUES (2, 'Processo 2', '', true);
INSERT INTO tb_processo (id, descricao, parecer, pendente) VALUES (3, 'Processo 3', 'teste 3', false);
INSERT INTO tb_usuario_processo (id, usuario_id, processo_id, data) VALUES (1, 3, 1, '2021-01-30');
INSERT INTO tb_usuario_processo (id, usuario_id, processo_id, data) VALUES (2, 3, 3, '2021-01-31');