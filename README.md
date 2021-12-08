# Cadastro de Usuários

## Descrição do Sistema

### Objetivo

Sistema para cadastrar e recuperar dados de usuários, onde o cadastro é apenas feito por usuários com permissão de **administrador**, além de que todo usuário cadastrado pela plataforma será um usuário **comum**, isto é, sem permissão de administrador. Usuários comuns apenas podem visualizar os próprios dados, enquanto administradores podem visualizar os próprios dados e cadastrar, atualizar e deletar usuários já existentes.

### Tecnologias

O sistema foi divido em **front-end** (ou **cliente**) e **back-end** (ou **serviço**), sendo que o **front** foi feito utilizando o **React**, enquanto que o **back** foi criado utilizando **Java 8, Spring, Spring Boot, Spring Security, Hibernate, Maven e H2**.

## Exemplos

## Como utilizar

Devidos as tecnologias utilizadas é necessário ter instalado tanto o **Maven** quanto o **NPM** para a execução desse sistema, além de fazer um clone ou baixar os arquivos do atual repositório.

Cada tecnologia é iniciada de maneira separada, no caso do **front-end**, utilizando o terminal, deve-se entrar na pasta **front-end** e executar os comandos **npm install**, para instalar as dependências necessárias, e **npm start**, para iniciar a aplicação React. Por sua vez, também utilizando o terminal, para iniciar o **back-end**, deve-ser ir até o diretório **\back-end\ServicoCadastroDeUsuarios** e executar o comando **mvn spring-boot:run**.

O **cliente** estará disponível na url **http://localhost:3000/**, enquanto os end-points do **serviço** estão disponíveis na url **http://localhost:8080/**, assim como o cliente do banco de dados H2 que fica disponível na url **http://localhost:8080/h2-console**.

Ao entrar na url **http://localhost:3000/**, ou em qualquer url válida do sistema, o usuário será direcionando para a página de login:

![login](C:\Users\GabrielPachêcoMilhom\Pictures\sistemaDeUsuarios\login.png)

Enquanto que acessar uma url inválida do sistema irá redirecionar para a página de não encontrado:

 
