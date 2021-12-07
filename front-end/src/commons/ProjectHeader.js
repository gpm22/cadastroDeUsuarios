import React from "react";
import { Link, NavLink } from "react-router-dom";
import "./ProjectHeader.css";
import user from "../user.svg";

const Header = (props) => {
  const role = (props.user ? props.user.role : "") === "administrator";

  let button = props.user ? (
    <button>
      <Link to="/login"> Sair</Link>
    </button>
  ) : null;

  let navbar =(
      <navbar className="navbar-header">
        <NavLink
          to={{
            pathname: "/dados-do-usuario",
            state: { user: props.user },
          }}
        >
          Dados
        </NavLink>
        <NavLink
          to={{
            pathname: "/cadastro-de-usuario",
            state: { user: props.user },
          }}
        >
          Cadastro
        </NavLink>
        <NavLink
          to={{
            pathname: "/alteracao-de-usuario",
            state: { user: props.user },
          }}
        >
          Alteração
        </NavLink>
        {button}
      </navbar>
    );

  return (
    <header>
      <span>
        <img src={user} alt="user"></img> <h1>Banco de Usuários</h1>
      </span>
      {role && navbar}
      {!role && button}
    </header>
  );
};

export default Header;
