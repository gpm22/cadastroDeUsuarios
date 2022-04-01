# Cadastro de Usuários (Back-end)

## Descrição do Sistema

### Objetivo

Como é o local onde as regras de negócio estão, o back-end aqui criado é uma API Restful responsável por: 

* Tratar e persisitir os dados enviados pelo front-end;
* Fazer as alterações nos recursos existentes, quando foir requisitado pelo front-end;
* Retornar os dados requisitados pelo front-end;
* Fazer a comunicação com o banco de dados;
* Autenticar usuários;

### Tecnologias Utilizadas

Essa API foi criada utilizando **Java 8**, **Spring**, **Spring Boot**, **Spring Security**, **Hibernate**, **Lombok**, **Maven** e **H2**.

## Como usar

### Inicializando o Sistema

Para fazer uso dessa API é necessário ter o **Maven** instalado, além de fazer um clone ou baixar os arquivos do atual repositório. Também pode ser necessário instalar o plugin do **Lombok**, acaso queira rodar o sistema com o auxilio de alguma IDE ou editor de texto.

Após ter todos esses requisitios, deve-ser ir até o diretório **\back-end\ServicoCadastroDeUsuarios** por meio de um terminal e executar os comandos **mvn clean e mvn install**, para limpar o projeto e instalar as dependências necessárias, e **mvn spring-boot:run**, para iniciar a aplicação.

Os end-points do **serviço** estarão disponíveis na url **http://localhost:8080/**, assim como o cliente do banco de dados **H2** que estará disponível na url **http://localhost:8080/cadastro-de-usuarios/v1/h2-console**.

### End-Points

Os end-points dessa API são:

* `http://localhost:8080/cadastro-de-usuarios/v1/`
  * Métodos:
    * **GET**
      * **Objetivo:** Retornar todos os usuários cadastrados no sistema;
    * **POST**
      * **Objetivo:** Cadastrar um novo usuário no sistema;
    * **PUT**
      * **Objetivo:** Alterar os dados de um usuário existente;
* `http://localhost:8080/cadastro-de-usuarios/v1/{CPF}`
  * Métodos:
    * **GET**
      * **Objetivo:** Retornar o usuário que possui o **CPF** especificado;
    * **DELETE**
      * **Objetivo:** Deletar o usuário que possui o **CPF** especificado
* `http://localhost:8080/cadastro-de-usuarios/v1/authenticate`
  * Métodos:
    * **POST**
      * **Objetivo:** Fazer a autenticação do usuário para o login;

## Banco de Dados

O sistema de banco de dados utilizado é o **[H2](https://www.h2database.com/html/main.html)**, que é um sistema totalmente criado em java e que pode ser embebido no spring boot. Foram criadas 4 tabelas no banco de dados com os seguintes campos e relações:

![tabelas](https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/tabelas.jpg?raw=true)

Para criar os usuários **admin** e **comum**, um script **SQL** (**data.sql**) é utilizado. Esse script contém as seguintes informações:

```sql
INSERT INTO ADRESSES (adress_id, adress_cep, adress_publicplace, adress_district, adress_city, adress_uf, adress_complement) 
VALUES (1000000, '00000000', 'Rua 98 lote 89 casa 712', 'Nova Galáxia Federal', 'Rio Roxo Azulado', 'OM', '');

INSERT INTO TELEPHONES (telephone_id, telephone_type, telephone_number) 
VALUES (1000000, 'residencial', '0000000000');
INSERT INTO TELEPHONES (telephone_id, telephone_type, telephone_number) 
VALUES (1000001, 'comercial'  , '1111111111');
INSERT INTO TELEPHONES (telephone_id, telephone_type, telephone_number) 
VALUES (1000002, 'celular'    , '22222222222');
INSERT INTO TELEPHONES (telephone_id, telephone_type, telephone_number) 
VALUES (1000003, 'celular'    , '33333333333');

INSERT INTO users (user_cpf, user_name, user_password, user_username, user_role, adress_id) 
VALUES ('00000000000', 'administrador', '$2a$10$iSU.HdehFiMoIkdDRwtIBu/wWhDWTJ5K.4D.sf9lruRBCV5Q36ApK', 'admin', 'administrator', 1000000);
INSERT INTO users (user_cpf, user_name, user_password, user_username, user_role, adress_id) 
VALUES ('11111111111', 'comum'        , '$2a$10$RiQmA5Zw2NHDipcGS8SsAOzUe5JIXlthdjB4bJh3G6jEtM/fcEzXG', 'comum', 'ordinary'     , 1000000);

INSERT INTO EMAILS (email_id, adress_email, user_cpf) 
VALUES (1000000, 'adm@bancodeusuarios.com'  , '00000000000');
INSERT INTO EMAILS (email_id, adress_email, user_cpf) 
VALUES (1000001, 'comum@bancodeusuarios.com', '11111111111');

INSERT INTO users_telephones (user_cpf, telephone_id) 
VALUES ('00000000000', 1000000);
INSERT INTO users_telephones (user_cpf, telephone_id) 
VALUES ('11111111111', 1000001);
INSERT INTO users_telephones (user_cpf, telephone_id)
VALUES ('00000000000', 1000002);
INSERT INTO users_telephones (user_cpf, telephone_id) 
VALUES ('11111111111', 1000003);

INSERT INTO telephones_users (telephone_entity_telephone_id, users_user_cpf) 
VALUES (1000000, '00000000000');
INSERT INTO telephones_users (telephone_entity_telephone_id, users_user_cpf) 
VALUES (1000001, '11111111111');
INSERT INTO telephones_users (telephone_entity_telephone_id, users_user_cpf) 
VALUES (1000002, '00000000000');
INSERT INTO telephones_users (telephone_entity_telephone_id, users_user_cpf)
VALUES (1000003, '11111111111');
```

Ao se analisar o arquivo **data.sql**, pode-se perceber o uso do algoritmo **Bcrypt** para hashear as senhas antes delas serem persistidas.
