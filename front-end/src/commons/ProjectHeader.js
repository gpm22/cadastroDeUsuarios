import React from "react";
import { useNavigate, Link } from "react-router-dom";
import "./ProjectHeader.css";
import user from "../user.svg";

const Header = (props) => {
  const role = (props.user ? props.user.role : "") === "administrator";
  const navigate = useNavigate();

  let button = props.user ? (
    <button>
      <Link to="/login"> Sair</Link>
    </button>
  ) : null;

  const goTo = (e) => {
    e.preventDefault();
    navigate(e.target.href.replace("http://localhost:3000", ""), { state: props.user });
  };

  let navbar = (
    <nav className="navbar-header">
      <a href="/dados-do-usuario" className={props.location==="dados-do-usuario"? "active" : null} onClick={goTo}>
        Dados
      </a>
      <a href="/cadastro-de-usuario" className={props.location==="cadastro-de-usuario"? "active" : null} onClick={goTo}>
        Cadastro
      </a>
      <a href="/alteracao-de-usuario" className={props.location==="alteracao-de-usuario"? "active" : null} onClick={goTo}>
        Alteração
      </a>
      {button}
    </nav>
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
