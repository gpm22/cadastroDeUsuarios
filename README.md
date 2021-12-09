# Cadastro de Usuários

## Descrição do Sistema

### Objetivo

Sistema para cadastrar e recuperar dados de usuários, onde o cadastro é apenas feito por usuários com permissão de **administrador**, além de que todo usuário cadastrado pela plataforma será um usuário **comum**, isto é, sem permissão de administrador. Usuários comuns apenas podem visualizar os próprios dados, enquanto administradores podem visualizar os próprios dados e cadastrar, atualizar e deletar usuários já existentes.

### Tecnologias

O sistema foi divido em **front-end** (ou **cliente**) e **back-end** (ou **serviço**), onde a comunicação entre essas partes é feita por meio de requisições HTTP. O **front** foi feito utilizando o **React**, enquanto que o **back** foi criado utilizando **Java 8, Spring, Spring Boot, Spring Security, Hibernate, Lombok, Maven, H2 e bootstrap**.

## Como utilizar

Devidos as tecnologias utilizadas é necessário ter instalado tanto o **Maven** quanto o **NPM** para a execução desse sistema, além de fazer um clone ou baixar os arquivos do atual repositório. Também pode ser necessário instalar o plugin do **Lombok**, acaso queira abrir o **back-end** em alguma IDE ou editor.

Cada tecnologia é iniciada de maneira separada, no caso do **front-end**, utilizando o terminal, deve-se entrar na pasta **front-end** e executar os comandos **npm install**, para instalar as dependências necessárias, e **npm start**, para iniciar a aplicação React. Por sua vez, também utilizando o terminal, para iniciar o **back-end**, deve-ser ir até o diretório **\back-end\ServicoCadastroDeUsuarios** e executar os comandos **mvn clean e mvn install**, para limpar o projeto e instalar as dependências necessárias, e **mvn spring-boot:run**, para iniciar a aplicação.

O **cliente** estará disponível na url **http://localhost:3000/**, enquanto os end-points do **serviço** estão disponíveis na url **http://localhost:8080/**, assim como o cliente do banco de dados H2 que fica disponível na url **http://localhost:8080/h2-console**.

Existem dois usuários já definidos no sistema e que podem ser utilizados:

* Nome de Usuário: **admin**
  * Senha: **123456**
* Nome de Usuário: **comum**
  * Senha: **123456**

Ao entrar na url **http://localhost:3000/**, ou em qualquer url válida do sistema, o usuário será direcionando para a página de **login**:

![login](C:\MeusProgramas\cadastroDeUsuarios\img\login.png)

Por sua vez, quando acessar uma url inválida do sistema, o usuário será redirecionado para a página de **não encontrado**:

![not-found](C:\MeusProgramas\cadastroDeUsuarios\img\not-found.png)

Após a autenticação. o usuário é redirecionado para a página de **visualização de dados**:

![dataviz](C:\MeusProgramas\cadastroDeUsuarios\img\dataviz.png)

Essa tela contém todas as informações do usuário e uma seção de alterar a senha.

No caso de usuários comuns, essa é a única tela disponível, então o header fica apenas com a opção de sair:

![header-comum](C:\MeusProgramas\cadastroDeUsuarios\img\header-comum.png)

Para o usuário administrador ainda existem as telas de cadastrar novos usuários e a de modificar/deletar usuários, como pode ser visto na imagem da página de visualização de dados no seu header.

 A tela de cadastro é um formulário que possui máscara para os campos **CPF, telefones**, que varia entre celular, residencial e comercial, e **CEP**, sendo que a persistência no sistema é feita sem máscara e o  **CPF** é verificado antes da inserção:

![cadastro-de-usuarios](C:\MeusProgramas\cadastroDeUsuarios\img\cadastro-de-usuarios.png)

 Todos os campos são obrigatórios, com exceção do **complemento do endereço**. O nome completo deve ser entre 3-100 caracteres alfanuméricos e/ou espaços. A verificação da validade do **email** é simples, aceitando qualquer coisa no formato: xxxx@xxxx.xxx.

 Ao se clicar no botão de **+** é possível adicionar novos **emails** ou **telefones**:

 ![emails](C:\MeusProgramas\cadastroDeUsuarios\img\emails.png)

Enquanto que apertando no botão **x** se exclui um dos **emails** ou **telefones**, porém é necessário que pelo menos um **email** e um **telefone** seja preenchido.

O sistema está configurado para buscar o **CEP** do o usuário através da [API ViaCEP](https://viacep.com.br/) e preencher o formulário com a informações buscadas, sendo que o usuário pode modificá-las.

| <img src="C:\MeusProgramas\cadastroDeUsuarios\img\endereco-antes.png" alt="endereco-antes" style="zoom:100%;" /> | <img src="C:\MeusProgramas\cadastroDeUsuarios\img\endereco-depois.png" alt="endereco-depois" style="zoom:100%;" /> |
| ------------------------------------------------------------ | ------------------------------------------------------------ |

Acaso tente-se cadastrar uma conta com um **CPF** ou um **nome de usuário** já cadastrados, o sistema irá mostrar uma mensagem de erro.

A tela de **alteração de usuários** contém uma tabela com todos os dados dos usuários:

![alteracao](C:\MeusProgramas\cadastroDeUsuarios\img\alteracao.png)

Ao se clicar em **remover** um modal é disparado:

![alteracao](C:\MeusProgramas\cadastroDeUsuarios\img\modal-alteracao.png)

E ao clicar em **sim** o usuário é removido e a página recarregada:

![alteracao-excluido](C:\MeusProgramas\cadastroDeUsuarios\img\alteracao-excluido.png)

Ao se clicar em **editar** o usuário é redirecionado para a tela de **edição de usuários**, que é semelhante ao formulário de cadastro, mas com as informações já preenchidas e sem as seções de **CPF** e **senha**:

![edicao-de-usuario](C:\MeusProgramas\cadastroDeUsuarios\img\edicao-de-usuario.png)

Ao terminar a edição o usuário é redirecionado de volta para a tabela de usuários.

E por fim, ao se clicar em **sair** o usuário é redirecionado para a tela de **login**.

## Descrição do Back-end

Foram criadas 4 tabelas no banco de dados com os seguintes campos e relações:

![tabelas](C:\MeusProgramas\cadastroDeUsuarios\img\tabelas.jpg)

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



