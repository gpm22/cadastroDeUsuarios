# Cadastro de Usuários

## Descrição do Sistema

### Objetivo

Sistema para cadastrar e recuperar dados de usuários, onde o cadastro é apenas feito por usuários com permissão de **administrador**, além de que todo usuário que for cadastrado pela plataforma será um usuário **comum**, isto é, sem permissão de administrador. Usuários comuns apenas podem visualizar os próprios dados, enquanto administradores podem visualizar os próprios dados e cadastrar, atualizar e deletar usuários.

### Tecnologias Utilizadas

O sistema foi divido em **front-end** (ou **cliente**) e **back-end** (ou **serviço**), onde a comunicação entre essas partes é feita por meio de requisições **HTTP.** O **front** foi feito utilizando as tecnologias **React** e **bootstrap**, enquanto que o **back** foi criado utilizando **Java 8, Spring, Spring Boot, Spring Security, Hibernate, Lombok, Maven e H2**.

## Como usar

### Inicializando o Sistema

Devidos as tecnologias aqui utilizadas é necessário ter instalado tanto o **Maven** quanto o **NPM** para a execução desse sistema, além de fazer um clone ou baixar os arquivos do atual repositório. Também pode ser necessário instalar o plugin do **Lombok**, acaso queira abrir o **back-end** em alguma IDE ou editor.

Por sua vez, também utilizando o terminal, para iniciar o **back-end**, deve-ser ir até o diretório **\back-end\ServicoCadastroDeUsuarios** e executar os comandos **mvn clean e mvn install**, para limpar o projeto e instalar as dependências necessárias, e **mvn spring-boot:run**, para iniciar a aplicação.

Os end-points do **serviço** estarão disponíveis na url **http://localhost:8080/**, assim como o cliente do banco de dados **H2** que estará disponível na url **http://localhost:8080/cadastro-de-usuarios/v1/h2-console**.

## Descrição do Back-end

Foram criadas 4 tabelas no banco de dados com os seguintes campos e relações:

![tabelas](https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/tabelas.jpg?raw=true)

Para fazer o **ORM** foram criadas 4 **entidades** com seus respectivos **repositórios** e **serviços**. Existe apenas um controlador para receber as requisições HTTP da aplicação contendo os seguintes end-points:

* **/cadastro-de-usuarios/{cpf}**
  * tipo: **GET**
  * retorna todas as informações de um usuário com o **CPF** informado
* **/cadastro-de-usuarios/retornar-usuarios**
  * tipo: **GET**
  * retorna uma lista contendo todos os usuários.
* **/cadastro-de-usuarios/authenticate**
  * tipo: **POST**
  * recebe um body contendo o nome de usuário e a senha e faz a autenticação.
  * Se for autenticado, retorna um json contendo as informações do usuário.
* **/cadastro-de-usuarios/create-user**
  * tipo: **POST**
  * receber um body contendo as informações do usuário a ser criado.
  * se o usuário for criado, retorna o usuário persistido.
* **/cadastro-de-usuarios/update-user/{cpf}**
  * tipo: **PUT**
  * recebe um body contendo as informações para atualizar o usuário do **CPF** fornecido.
  * se o usuário for atualizado, retorna o usuário.
* **/cadastro-de-usuarios/update-user/{cpf}**
  * tipo: **DELETE**
  * deleta o usuário do **CPF** fornecido.
  * se o usuário for deletado, retorna o usuário.

Existe um script **SQL** (data.sql) que faz a persistência dos usuários **admin** e **comum**:

```sql
INSERT INTO ADRESSES (adress_id, adress_cep, adress_publicplace, adress_district, adress_city, adress_uf, adress_complement) VALUES (0, '00000000', 'Rua 98 lote 89 casa 712', 'Nova Galáxia Federal', 'Rio Roxo Azulado', 'OM', '');

INSERT INTO TELEPHONES (telephone_id, telephone_type, telephone_number) VALUES (0, 'residencial', '0000000000');
INSERT INTO TELEPHONES (telephone_id, telephone_type, telephone_number) VALUES (10, 'comercial'  , '1111111111');
INSERT INTO TELEPHONES (telephone_id, telephone_type, telephone_number) VALUES (20, 'celular'    , '22222222222');
INSERT INTO TELEPHONES (telephone_id, telephone_type, telephone_number) VALUES (30, 'celular'    , '33333333333');

INSERT INTO users (user_cpf, user_name, user_password, user_username, user_role, adress_id) VALUES ('00000000000', 'administrador', '$2a$10$iSU.HdehFiMoIkdDRwtIBu/wWhDWTJ5K.4D.sf9lruRBCV5Q36ApK', 'admin', 'administrator', 0);
INSERT INTO users (user_cpf, user_name, user_password, user_username, user_role, adress_id) VALUES ('11111111111', 'comum'        , '$2a$10$RiQmA5Zw2NHDipcGS8SsAOzUe5JIXlthdjB4bJh3G6jEtM/fcEzXG', 'comum', 'ordinary'     , 0);

INSERT INTO EMAILS (email_id, adress_email, user_cpf) VALUES (0, 'adm@bancodeusuarios.com'  , '00000000000');
INSERT INTO EMAILS (email_id, adress_email, user_cpf) VALUES (1, 'comum@bancodeusuarios.com', '11111111111');

INSERT INTO users_telephones (user_cpf, telephone_id) VALUES ('00000000000', 0);
INSERT INTO users_telephones (user_cpf, telephone_id) VALUES ('11111111111', 10);
INSERT INTO users_telephones (user_cpf, telephone_id) VALUES ('00000000000', 20);
INSERT INTO users_telephones (user_cpf, telephone_id) VALUES ('11111111111', 30);
```

Como pode se perceber pelo **data.sql**, as senhas são persisitidas no banco de dados após serem hasheadas usando o algoritmo **Bcrypt**.

