# Cadastro de Usuários

## Descrição do Sistema

### Objetivo

Sistema para cadastrar e recuperar dados de usuários, onde o cadastro é apenas feito por usuários com permissão de **administrador**, além de que todo usuário que for cadastrado pela plataforma será um usuário **comum**, isto é, sem permissão de administrador. Usuários comuns apenas podem visualizar os próprios dados, enquanto administradores podem visualizar os próprios dados e cadastrar, atualizar e deletar usuários.

### Tecnologias Utilizadas

O sistema foi divido em **front-end** (ou **cliente**) e **back-end** (ou **serviço**), onde a comunicação entre essas partes é feita por meio de requisições **HTTP.** O **front** foi feito utilizando as tecnologias **React** e **bootstrap**, enquanto que o **back** foi criado utilizando **Java 8, Spring, Spring Boot, Spring Security, Hibernate, Lombok, Maven e H2**.

## Como usar

### Inicializando o Sistema

Devidos as tecnologias aqui utilizadas é necessário ter instalado tanto o **Maven** quanto o **NPM** para a execução desse sistema, além de fazer um clone ou baixar os arquivos do atual repositório. Também pode ser necessário instalar o plugin do **Lombok**, acaso queira abrir o **back-end** em alguma IDE ou editor.

Cada tecnologia é iniciada de maneira separada, no caso do **front-end**, utilizando o terminal, deve-se entrar na pasta **front-end** e executar os comandos **npm install**, para instalar as dependências necessárias, e **npm start**, para iniciar a aplicação React. Por sua vez, também utilizando o terminal, para iniciar o **back-end**, deve-ser ir até o diretório **\back-end\ServicoCadastroDeUsuarios** e executar os comandos **mvn clean e mvn install**, para limpar o projeto e instalar as dependências necessárias, e **mvn spring-boot:run**, para iniciar a aplicação.

O **cliente** estará disponível na url **http://localhost:3000/**, enquanto os end-points do **serviço** estarão disponíveis na url **http://localhost:8080/**, assim como o cliente do banco de dados **H2** que estará disponível na url **http://localhost:8080/cadastro-de-usuarios/v1/h2-console**.

Existem dois usuários já pré-definidos no sistema e que podem ser utilizados:

* Nome de Usuário: **admin**
  * Senha: **123456**
* Nome de Usuário: **comum**
  * Senha: **123456**

### Utilizando o Sistema

#### Tela de Login

Ao entrar na url **http://localhost:3000/**, ou em qualquer url válida do sistema, o usuário será direcionando para a página de **login**  <a href="#Figura1">(Figura 1)</a>:

![login](https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/login.png?raw=true)

<p id="Figura1" align="center"><b>Figura 1</b> - Tela de Login</p>

#### Tela de Não Encontrado

Ao acessar uma url inválida do sistema, o usuário será redirecionado para a página de **não encontrado** <a href="#Figura2">(Figura 2)</a>:

![not-found](https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/not-found.png?raw=true)

<p id="Figura2" align="center"><b>Figura 2</b> - Tela de Não Encontrado</p>

#### Tela de Visualização de Dados

Após ser corretamente autenticado, o usuário é redirecionado do **login** para a página de **visualização de dados** <a href="#Figura3">(Figura 3)</a>:

![dataviz](https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/dataviz.png?raw=true)

<p id="Figura3" align="center"><b>Figura 3</b> - Tela de Visualização de Dados</p>

Essa tela contém todas as informações cadastradas do usuário e uma seção para mudança de senha.

No caso de usuários **comuns**, essa é a única tela disponível, então o header fica apenas com a opção de sair <a href="#Figura4">(Figura 4)</a>:

![header-comum](https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/header-comum.png?raw=true)

<p id="Figura4" align="center"><b>Figura 4</b> - Header dos Usuários Comuns</p>

Já no caso de usuários **administradores**, ainda existem as telas de **cadastrar novos usuários** e de **modificar/deletar usuários**, então o header dos **administradores** tem mais opções, como pode ser visto na Figura 3.

#### Tela de Cadastro

A **tela de cadastro** <a href="#Figura5">(Figura 5)</a> é um formulário onde são necessários informar os dados apresentandos na tela de **visualização de dados** e o nome de usuário, sendo que todos os campos são de preenchimento **obrigatório**, com exceção do **complemento do endereço**.

![cadastro-de-usuarios](https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/cadastro-de-usuarios.png?raw=true)

<p id="Figura5" align="center"><b>Figura 5</b> - Tela de Cadastro</p>

Os campos **CPF**, **telefones** e **CEP** possuem máscaras, sendo que no caso dos **telefones**, a máscara irá varia dependendo do tipo de telefone, que pode ser **celular**, **residencial** ou **comercial**. Todos esses dados mascarados são persistidos no sistema sem máscara, sendo que o **CPF** é validado antes da inserção.

O campo **nome completo** deve receber uma string que tenha entre 3-100 caracteres alfanuméricos e/ou espaços. 

No caso do campo **email**, a validação da entrada é simples, aceitando qualquer entrada no formato: **xxxx@xxxx.xxx**.

É possível adicionar inúmeros **emails** e **telefones**, sendo obrigatório no mínimo um de cada. Para adicionar mais uma entrada nesses campos é só clicar no botão de **+** <a href="#Figura6">(Figura 6)</a>. 

 <p align="center"> <img src="https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/emails.png?raw=true" alt="emails"> </p>

<p id="#Figura6" align="center"><b>Figura 6</b> - Adicionando novas entradas de Email</p>

Já para excluir uma entrada desse campos, é necessário apenas clicar no botão **x** <a href="#Figura6">(Figura 6)</a>. Porém como é necessário que pelo menos um **email** e um **telefone** sejam preenchidos, o botão **x** não aparece quando só tem um entrada.

O sistema está configurado para buscar o **CEP** informado pelo usuário através da [API ViaCEP](https://viacep.com.br/) e preencher o formulário com a informações retornadas <a href="#Figura7">(Figura 7)</a>, sendo que o usuário pode modificá-las.

| <img src="https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/endereco-antes.png?raw=true" alt="endereco-antes" style="zoom:100%;" /> | <img src="https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/endereco-depois.png?raw=true" style="zoom:100%;" /> |
| ------------------------------------------------------------ | ------------------------------------------------------------ |

<p id="Figura7" align="center"><b>Figura 7</b> - Preenchimento Automático do Endereço por Meio do CEP</p>

Acaso tente-se cadastrar uma conta com um **CPF** ou um **nome de usuário** já cadastrados, o sistema irá mostrar uma mensagem de erro.

#### Tela de Alteração de Usuários

A tela de **alteração de usuários** contém uma **tabela** <a href="#Figura8">(Figura 8)</a> com todos os dados dos usuários:

![alteracao](https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/alteracao.png?raw=true)

<p id="#Figura8" align="center"><b>Figura 8</b> - Tabela de Usuários</p>

Na última coluna a esquerda da tabela de usuários existe os botões com as opções de **editar** ou **remover**, sendo que ao se clicar em **remover** um modal é disparado <a href="#Figura9">(Figura 9)</a>:

![alteracao](https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/modal-alteracao.png?raw=true)

<p id="Figura9" align="center"><b>Figura 9</b> - Modal de Remoção de Usuário</p>

E ao clicar em **sim** o usuário é removido e a página recarregada <a href="#Figura10">(Figura 10)</a>:

![alteracao-excluido](https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/alteracao-excluido.png?raw=true)

<p id="Figura10" align="center"><b>Figura 10</b> - Tabela de Usuários após Exclusão do Usuário Comum</p>

Por sua vez, ao se clicar em **editar** o usuário é redirecionado para a tela de **edição de usuários** <a href="#Figura11">(Figura 11)</a>, que é semelhante ao **formulário de cadastro** <a href="#Figura5">(Figura 5)</a>, mas com as informações já preenchidas e sem as seções de **CPF** e **senha**:

![edicao-de-usuario](https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/edicao-de-usuario.png?raw=true)

<p id="Figura11" align="center"><b>Figura 11 </b> - Tela de Edição de Usuários</p>

Ao terminar a edição o usuário é redirecionado de volta para a tabela de usuários <a href="#Figura8">(Figura 8)</a>.

E por fim, ao se clicar em **sair** o usuário é redirecionado para a tela de **login** <a href="#Figura1">(Figura 1)</a>.
