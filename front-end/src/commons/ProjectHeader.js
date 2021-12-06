import React from "react";
import { Link } from "react-router-dom";
import "./ProjectHeader.css";
import user from "../user.svg";


const Header = (props) => {
  let button = props.loged ? (
    <button>
      <Link to="/login"> Sair</Link>
    </button>
  ) : null;

  console.log(props.role);

  let navbar = props.role === "administrator" ? (
<h1>O adm está online</h1>
  ) : null ;

  return (
    <header>
      <span>
        <img src={user} alt="user"></img> <h1>Banco de Usuários</h1>
      </span>
      {button}
      {navbar}
    </header>
  );
};

export default Header;
