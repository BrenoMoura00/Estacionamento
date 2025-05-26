-- Sequência para herança TABLE_PER_CLASS
CREATE SEQUENCE pessoa_sequence START WITH 1 INCREMENT BY 1;

-- Tabela Estacionamento (APENAS campos próprios)
CREATE TABLE estacionamentos
(
    id         BIGSERIAL PRIMARY KEY,
    nome       VARCHAR(255) NOT NULL,
    vaga       VARCHAR(255) NOT NULL,
    andar      VARCHAR(255),
    bloco      VARCHAR(255),
    disponivel BOOLEAN      NOT NULL DEFAULT true
);

-- Tabela Cliente (herda de Pessoa)
CREATE TABLE clientes
(
    id                BIGINT DEFAULT nextval('pessoa_sequence') PRIMARY KEY,
    name              VARCHAR(255)        NOT NULL,
    cpf               VARCHAR(255) UNIQUE NOT NULL,
    telefone          VARCHAR(255),
    estacionamento_id BIGINT,
    FOREIGN KEY (estacionamento_id) REFERENCES estacionamentos (id)
);

-- Tabela Funcionario (herda de Pessoa)
CREATE TABLE funcionario
(
    id       BIGINT DEFAULT nextval('pessoa_sequence') PRIMARY KEY,
    name     VARCHAR(255)        NOT NULL,
    cpf      VARCHAR(255) UNIQUE NOT NULL,
    telefone VARCHAR(255)
);

-- Tabela Veículo
CREATE TABLE veiculos
(
    id         BIGSERIAL PRIMARY KEY,
    placa      VARCHAR(255) UNIQUE NOT NULL,
    modelo     VARCHAR(255)        NOT NULL,
    cor        VARCHAR(255),
    marca      VARCHAR(255)        NOT NULL,
    cliente_id BIGINT              NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes (id)
);

-- Tabela Ticket (TODOS os campos relacionados ao ticket)
CREATE TABLE tickets
(
    id                BIGSERIAL PRIMARY KEY,
    funcionario_id    BIGINT         NOT NULL,
    valor             NUMERIC(10, 2) NOT NULL,
    veiculo_id        BIGINT         NOT NULL,
    hora_entrada      TIMESTAMP      NOT NULL,
    hora_saida        TIMESTAMP,
    estacionamento_id BIGINT         NOT NULL,
    cliente_id        BIGINT         NOT NULL,
    FOREIGN KEY (funcionario_id) REFERENCES funcionario (id),
    FOREIGN KEY (veiculo_id) REFERENCES veiculos (id),
    FOREIGN KEY (estacionamento_id) REFERENCES estacionamentos (id),
    FOREIGN KEY (cliente_id) REFERENCES clientes (id)
);