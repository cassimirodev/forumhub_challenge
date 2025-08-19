CREATE TABLE curso
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    nome      VARCHAR(255)          NOT NULL,
    categoria VARCHAR(100)          NULL,
    CONSTRAINT pk_curso PRIMARY KEY (id)
);

CREATE TABLE perfil
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    role_user VARCHAR(255)          NULL,
    CONSTRAINT pk_perfil PRIMARY KEY (id)
);

CREATE TABLE resposta
(
    id           BIGINT AUTO_INCREMENT  NOT NULL,
    mensagem     TEXT                   NOT NULL,
    data_criacao datetime DEFAULT NOW() NULL,
    solucao      BIT(1)   DEFAULT 0     NOT NULL,
    user_id      BIGINT                 NOT NULL,
    topico_id    BIGINT                 NOT NULL,
    CONSTRAINT pk_resposta PRIMARY KEY (id)
);

CREATE TABLE topico
(
    id           BIGINT AUTO_INCREMENT  NOT NULL,
    titulo       VARCHAR(255)           NOT NULL,
    mensagem     TEXT                   NOT NULL,
    data_criacao datetime DEFAULT NOW() NULL,
    status       VARCHAR(255)           NULL,
    user_id      BIGINT                 NOT NULL,
    curso_id     BIGINT                 NOT NULL,
    CONSTRAINT pk_topico PRIMARY KEY (id)
);

CREATE TABLE user
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    nome      VARCHAR(100)          NOT NULL,
    email     VARCHAR(100)          NOT NULL,
    senha     VARCHAR(255)          NOT NULL,
    perfil_id BIGINT                NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE resposta
    ADD CONSTRAINT FK_RESPOSTA_ON_TOPICO FOREIGN KEY (topico_id) REFERENCES topico (id) ON DELETE CASCADE;

ALTER TABLE resposta
    ADD CONSTRAINT FK_RESPOSTA_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE topico
    ADD CONSTRAINT FK_TOPICO_ON_CURSO FOREIGN KEY (curso_id) REFERENCES curso (id);

ALTER TABLE topico
    ADD CONSTRAINT FK_TOPICO_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_PERFIL FOREIGN KEY (perfil_id) REFERENCES perfil (id);