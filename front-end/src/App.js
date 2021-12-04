import "./App.css";

import React from "react";
import { Link } from "react-router-dom";

export default function App() {

  return (
    <div>
      <h1>Teste</h1>
      <nav
        style={{
          borderBottom: "solid 1px",
          paddingBottom: "1rem"
        }}
      >
        <Link to="/login">Login</Link> |{" "}
        <Link to="/dados-do-usuario">Dados</Link>
      </nav>
    </div>
  );
}
