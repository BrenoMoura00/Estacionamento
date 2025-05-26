---

# 🚗 Projeto de Estacionamento

## 🎯 Objetivo

O projeto de estacionamento tem como objetivo a demonstração de aptidão em Java para a disciplina de **Programação II** da instituição de ensino **UNIFACOL**, em Vitória de Santo Antão.

---

## ✅ Metas

* ✅ Implementar uma aplicação Java.
* ✅ Possuir no mínimo **5 entidades**.
* ✅ Aplicar os **3 pilares da POO** (Programação Orientada a Objetos):

  * Encapsulamento
  * Herança
  * Polimorfismo

---

## 🗂️ Diagrama de Classes

> Adicionar futuramente.

---

## 👥 Grupo

<div style="display:flex; flex-direction:row; align-items:center; gap:30px">

<a href="https://github.com/BrenoMoura00" style="display:flex; flex-direction:column; align-items:center">
<img height="50" style="border-radius:50%;" src="https://avatars.githubusercontent.com/u/133166425?v=4">
<p>Breno Moura</p>
</a>

<a href="https://github.com/Bobonimo111" style="display:flex; flex-direction:column; align-items:center">
<img height="50" style="border-radius:50%;" src="https://avatars.githubusercontent.com/u/86368140?v=4">
<p>William Rodrigues</p>
</a>

<a href="https://github.com/Pedro-Falcao" style="display:flex; flex-direction:column; align-items:center">
<img height="50" style="border-radius:50%;" src="https://avatars.githubusercontent.com/u/176639563?v=4">
<p>Pedro Falcão</p>
</a>

</div>

---

## ⚙️ Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Flyway (migrations)
* Docker

---

## 🛠️ Configuração do Banco de Dados

### ✅ Passos para configurar:

1. Certifique-se que o **Docker** está instalado e em execução.
2. Execute o comando:

```bash
docker-compose up
```

> Esse comando irá iniciar o container com o PostgreSQL já configurado para o projeto.

3. A criação das tabelas será feita automaticamente pelo **Flyway**, através do arquivo de migração localizado em:

```
src/main/resources/db/migration/V202505261255.0.0__create_tables.sql
```

Ou seja, **não é necessário rodar comandos manuais** — basta subir a aplicação e o Flyway executará as migrations automaticamente, criando o banco de dados com todas as tabelas.

---

## 🗃️ Estrutura do Banco de Dados (DDL)

```sql
-- Sequência para herança TABLE_PER_CLASS
CREATE SEQUENCE pessoa_sequence START WITH 1 INCREMENT BY 1;

-- Tabela Estacionamento
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

-- Tabela Ticket
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
```

---
