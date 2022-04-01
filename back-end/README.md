# User Registration (Service)

Versão em português disponível em https://github.com/gpm22/cadastroDeUsuarios/blob/main/back-end/LEIAME.md

## System Description

### Purpose

As this is the local where the business rules are, the service created here is a Restful API responsible for:

* Treat and persist the data sent by the client;
* Make changes to existing resources, when required by the client
* Return the data requested by the client;
* Communicate with the database;
* Authenticate users;

### Applied Technologies

This API was created using **Java 8**, **Spring**, **Spring Boot**, **Spring Security**, **Hibernate**, **Lombok**, **Maven**, and **H2**.

## How to Use

### Booting the System

To run this API it is necessary to have **Maven** installed, in addition to making a clone or downloading the files from the current repository. It may also be necessary to install the **Lombok** plugin, in case you want to run the system using an IDE or text editor.

After having all these requirements, you must go to the **\back-end\ServicoCadastroDeUsuarios** directory through a terminal and run the commands **mvn clean** and **mvn install**, to clean the project and install the necessary dependencies, and **mvn spring-boot:run**, to start the application.

The **service** endpoints will be available at the url **http://localhost:8080/**, as well as the **H2** database client, which will be available at the url **http://localhost:8080/cadastro-de-usuarios/v1/h2-console**.

### Endpoints

The API endpoints are:

* `http://localhost:8080/cadastro-de-usuarios/v1/`
  * Methods:
    * **GET**
      * **Purpose:** Return all users registered in the system;
    * **POST**
      * **Purpose:** Register a new user in the system;
    * **PUT**
      * **Purpose:** Modify the data of an existing user;
* `http://localhost:8080/cadastro-de-usuarios/v1/{CPF}`
  * Methods:
    * **GET**
      * **Purpose:** Return the user who has the specified **CPF**
    * **DELETE**
      * **Purpose:** Delete the user who has the specified **CPF**;
* `http://localhost:8080/cadastro-de-usuarios/v1/authenticate`
  * Methods:
    * **POST**
      * **Purpose:** Authenticate the user for login;

## Database

The database system used here is the **[H2](https://www.h2database.com/html/main.html)**, which is a system entirely created in java and that can be embedded in spring boot. Four tables were created in the database with the following fields and relationships:

![tabelas](https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/tabelas.jpg?raw=true)

To create the **admin** and **comum** users, a **SQL** script (**data.sql**) is used. This script contains the following information:

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

After analyzing the **data.sql** file, one can notice the use of the **Bcrypt** algorithm to hash the passwords before they are persisted.
