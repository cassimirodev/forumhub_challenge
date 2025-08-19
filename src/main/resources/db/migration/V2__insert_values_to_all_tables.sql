-- Inserir Perfis
INSERT INTO perfil (id, role_user) VALUES (1, 'ALUNO');
INSERT INTO perfil (id, role_user) VALUES (2, 'PROFESSOR');
INSERT INTO perfil (id, role_user) VALUES (3, 'MODERADOR');
INSERT INTO perfil (id, role_user) VALUES (4, 'ADMINISTRADOR');

-- Inserir Cursos
INSERT INTO curso (id, nome, categoria) VALUES (1, 'Spring Boot 3: Desenvolvendo uma API Rest em Java', 'Desenvolvimento');
INSERT INTO curso (id, nome, categoria) VALUES (2, 'Java: Dominando a Linguagem', 'Desenvolvimento');
INSERT INTO curso (id, nome, categoria) VALUES (3, 'SQL com MySQL: Manipule e consulte dados', 'Banco de Dados');

-- Inserir Usuários
-- A senha 'senha123' é apenas para exemplo. Em um ambiente real, use senhas com hash.
INSERT INTO user (id, nome, email, senha, perfil_id) VALUES (1, 'Aluno Fulano', 'aluno@email.com', 'senha123', 1);
INSERT INTO user (id, nome, email, senha, perfil_id) VALUES (2, 'Professor Ciclano', 'professor@email.com', 'senha123', 2);
INSERT INTO user (id, nome, email, senha, perfil_id) VALUES (3, 'Moderador Beltrano', 'moderador@email.com', 'senha123', 3);

-- Inserir Tópicos
INSERT INTO topico (id, titulo, mensagem, data_criacao, status, user_id, curso_id) VALUES (1, 'Dúvida sobre DTOs', 'Qual a melhor forma de converter uma entidade JPA para um DTO?', NOW(), 'PENDENTE', 1, 1);
INSERT INTO topico (id, titulo, mensagem, data_criacao, status, user_id, curso_id) VALUES (2, 'Erro ao conectar no banco', 'Estou recebendo um erro de autenticação no MySQL.', NOW(), 'PENDENTE', 1, 3);
INSERT INTO topico (id, titulo, mensagem, data_criacao, status, user_id, curso_id) VALUES (3, 'Como usar o Lombok?', 'Quais as principais anotações do Lombok para reduzir código boilerplate?', NOW(), 'RESOLVIDO', 2, 2);

-- Inserir Respostas
INSERT INTO resposta (id, mensagem, data_criacao, solucao, user_id, topico_id) VALUES (1, 'Você pode usar o padrão de construtor no DTO ou uma biblioteca como MapStruct.', NOW(), 0, 2, 1);
INSERT INTO resposta (id, mensagem, data_criacao, solucao, user_id, topico_id) VALUES (2, 'Verifique se o seu usuário e senha no arquivo application.properties estão corretos.', NOW(), 0, 3, 2);
INSERT INTO resposta (id, mensagem, data_criacao, solucao, user_id, topico_id) VALUES (3, 'As principais são @Getter, @Setter, @NoArgsConstructor, @AllArgsConstructor e @Data.', NOW(), 1, 2, 3);