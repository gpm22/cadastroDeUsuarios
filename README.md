# User Registration

Versão em português disponível em https://github.com/gpm22/cadastroDeUsuarios/blob/main/LEIAME.md.

## System Description

### Purpose

Web System for registering and retrieving user data, where registration is only performed by users with **administrator** permission, moreover every user who is registered by the platform will be a **ordinary** user, that is, without **administrator** permission. **Ordinary** users can only view their own data, while **administrators** can view their own data and register, update and delete users.

### Applied Technologies

The systems was divided into **front-end** (or **client**) and **back-end** (or **service**), where communication between these parts is done trough **HTTP** requests. The **client** was made using **React** and **bootstrap**, while the **service** was created using **Java 8, Spring, Spring Boot, Spring Security, Hibernate, Lombok , Maven, and H2**.

## How to Use

### Booting the System

Due to the technologies used here, it is necessary to have installed both **Maven** and **NPM** to run this system, in addition to making a clone or downloading the files from the current repository. You may also need to install the **Lombok** plugin if you want to open the **service** in an IDE or text editor.

Each technology is started separately, in the case of the **client**, using the terminal, enter the **front-end** folder and execute the commands **npm install**, to install the necessary dependencies, and **npm start**, to start the React application. To start the **service**, also using the terminal, you must go to the **\back-end\ServicoCadastroDeUsuarios** directory and run the commands **mvn clean** and **mvn install**, to clean the project and install the necessary dependencies, and **mvn spring -boot:run**, to start the application.

The client will be available at the url **http://localhost:3000/**, while the service endpoints will be available at the url **http://localhost:8080/**, as well as the **H2** database client, which will be available at the url **http://localhost:8080/cadastro-de-usuarios/v1/h2-console**.

There are two pre-defined users in the system that can be used:

* Username: **admin**
  * Password: **123456**
  * Permission: **Administrator User**
* Username: **comum**
  * Password: **123456**
  * Permission: **Ordinary User**

### Using the System

#### Login Page

By entering the url **http://localhost:3000/**, or any other valid system url, the user will be redirected to the **login** page <a href="#Figure1">(Figure 1 )</a>:

![login](https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/login.png?raw=true)

<p id="Figura1" align="center"><b>Figure 1</b> - Login Page</p>

#### Not Found Page

When accessing an invalid system url, the user will be redirected to the **not found** page <a href="#Figure2">(Figure 2)</a>:

![not-found](https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/not-found.png?raw=true)

<p id="Figura2" align="center"><b>Figure 2</b> - Not Found Page</p>

#### Data Visualization Page

After being properly authenticated, the user is redirected from **login** to the **data view** page <a href="#Figure3">(Figure 3)</a>:

![dataviz](https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/dataviz.png?raw=true)

<p id="Figura3" align="center"><b>Figure 3</b> - Data Visualization Page</p>

This page contains all the registered user information and a password change section.

In the case of **ordinary** users, this is the only page available, so the header only shows the option to log out <a href="#Figure4">(Figure 4)</a>:

![header-comum](https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/header-comum.png?raw=true)

<p id="Figure4" align="center"><b>Figure 4</b> - Ordinary Users Header</p>

In the case of **administrators** users, they will have access to two more pages: **registration** and **alteration** pages. So the **administrators** header shows more options, as can be seen seen in <a href="#Figure3">Figure 3</a>.

#### Registration Page

The **registration page** <a href="#Figure5">(Figure 5)</a> is a form where it is necessary to inform the data presented in the **data visualization page** <a href="#Figure3">(Figure 3)</a> and the **username**. All fields, but the **address complement**, are **mandatory**.

![cadastro-de-usuarios](https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/cadastro-de-usuarios.png?raw=true)

<p id="Figure5" align="center"><b>Figure 5</b> - Registration Page</p>

The **CPF**, **telephones**, and **CEP** fields have masks, and in the case of **telephones**, the mask will vary depending on the type of telephone, which can be **mobile**, **residential** or **commercial**, as mobile phone numbers have an additional digit in Brazil. All these masked data are persisted in the system without a mask, and the **CPF** is validated before insertion.

The **full name** field must receive a string that is between 3-100 alphanumeric characters and/or spaces.

In the case of the **email** field, input validation is simple, accepting any input in the format: **xxxx@xxxx.xxx**.

It is possible to add countless **emails** and **telephones**, but at least one of each is required. To add one more entry in these fields, just click on the **+** button <a href="#Figure6">(Figure 6)</a>. 

 <p align="center"> <img src="https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/emails.png?raw=true" alt="emails"> </p>

<p id="#Figure6" align="center"><b>Figure 6</b> - Adding New Email Entries</p>



To delete an entry from these fields, it is only necessary to click on the **x** button <a href="#Figure6">(Figure 6)</a>. However, as it is necessary that at least one **email** and one phone number be filled in, the **x** button does not appear when there is only one entry.

The system is configured to search for the **CEP** informed by the user through the [ViaCEP API](https://viacep.com.br/) to fill in the form with the information returned <a href="#Figure7">(Figure 7)</a>, whereas the user can modify it.

| <img src="https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/endereco-antes.png?raw=true" alt="endereco-antes" style="zoom:100%;" /> | <img src="https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/endereco-depois.png?raw=true" style="zoom:100%;" /> |
| ------------------------------------------------------------ | ------------------------------------------------------------ |

<p id="Figure7" align="center"><b>Figure 7</b> - Address Autofill by CEP</p>

Finally, if there is an attempt to register an account with a **CPF** or a **username** already registered, the system will show an error message.

#### User Modification Page

The **user modification page** contains a **table** <a href="#Figure8">(Figure 8)</a> with all the users data:

![alteracao](https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/alteracao.png?raw=true)

<p id="#Figure8" align="center"><b>Figure 8</b> - Users Table</p>

In the last column on the left of the users table are the buttons with the options to **edit** or **remove**, whereas when clicking on the **remove** button a modal is triggered <a href="#Figure9">(Figure 9)</a>:

![alteracao](https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/modal-alteracao.png?raw=true)

<p id="Figure9" align="center"><b>Figure 9</b> - User Removal Modal</p>

And when clicking on the **yes** button, the user is removed and the table is updated <a href="#Figure10">(Figure 10)</a>:

![alteracao-excluido](https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/alteracao-excluido.png?raw=true)

<p id="Figure10" align="center"><b>Figure 10</b> - Users Table after the Ordinary User Removal</p>

For its part, when clicking on the **edit** button, the user is redirected to the **user editing page** <a href="#Figure11">(Figure 11)</a>, which is similar to the **registration form**  <a href="#Figure5">(Figure 5)</a>, but with the information already filled in and without the **CPF** and **password** sections:

![edicao-de-usuario](https://github.com/gpm22/cadastroDeUsuarios/blob/main/img/edicao-de-usuario.png?raw=true)

<p id="Figure11" align="center"><b>Figure 11 </b> - User Editing Page</p>

After finishing the editing, the user is redirected back to the users table <a href="#Figure8">(Figure 8)</a>.

Finally, when clicking on the **log out** option, the user is redirected to the **login page** <a href="#Figure1">(Figure 1)</a>.
