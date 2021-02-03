INSERT INTO usuario (login, nome_completo, senha) VALUES ('marcelo', 'Marcelo Adm', '123');
INSERT INTO usuario (login, nome_completo, senha) VALUES ('augusto', 'Augusto Triador', '456');
INSERT INTO usuario (login, nome_completo, senha) VALUES ('kaufmann', 'Kaufmann Finalizador', '789');

INSERT INTO role (nome_role) VALUES ('ROLE_ADMIN');
INSERT INTO role (nome_role) VALUES ('ROLE_TRIADOR');
INSERT INTO role (nome_role) VALUES ('ROLE_FINALIZADOR');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES ('marcelo', 'ROLE_ADMIN');
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES ('augusto', 'ROLE_TRIADOR');
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES ('kaufmann', 'ROLE_FINALIZADOR');

--INSERT INTO processo (id, descricao, parecer, pendente) VALUES (1, 'Processo 1', 'teste 1', 'Não');
--INSERT INTO processo (id, descricao, parecer, pendente) VALUES (2, 'Processo 2', '', 'Sim');
--INSERT INTO processo (id, descricao, parecer, pendente) VALUES (3, 'Processo 3', 'teste 3', 'Não');
--INSERT INTO usuario_processo (id, usuario_id, processo_id, data) VALUES (1, 'kaufmann', 1, '2021-01-30');
--INSERT INTO usuario_processo (id, usuario_id, processo_id, data) VALUES (2, 'kaufmann', 3, '2021-01-31');