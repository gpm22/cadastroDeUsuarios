import React from "react";
import "./ProjectHeader.css";
import user from "../user.svg";

const Header = () => (
    <header>
       <span><img src={user}></img>  <h1>Banco de Usuários</h1></span>
    </header>
);

export default Header;