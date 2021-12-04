import React, { useState } from "react";
import "./Login.css";
import ProjectFooter from "../commons/ProjectFooter";
import ProjectHeader from "../commons/ProjectHeader";

async function login(usernameString, passwordString) {
  await fetch("http://localhost:8080/cadastro-de-usuarios/authenticate", {
    method: "post",
    body: JSON.stringify({
      username: usernameString,
      password: passwordString,
    }),
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      if (response.ok) {
        response.json().then((data) => {
          alert("deu certo!\n" + data.password); //JSON.stringify(data, null, 1));
        });
      } else {
        response.text().then((text) => {
          alert(text);
        });
      }
    })
    .catch(console.error);
}
function Login(props) {
  const username = useFormInput("");
  const password = useFormInput("");
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  // handle button click of login form
  const handleLogin = () => {
    login(username.value, password.value);
  };

  return (
    <div id="login-wrapper">
    <ProjectHeader/>
      <div className="login-block">
        <h1>Acesso ao Sistema</h1>
        <br />
        <div>
          Nome de Usuário
          <br />
          <input type="text" {...username} autoComplete="username" />
        </div>
        <div style={{ marginTop: 10 }}>
          Senha
          <br />
          <input type="password" {...password} autoComplete="new-password" />
        </div>
        {error && (
          <>
            <small style={{ color: "red" }}>{error}</small>
            <br />
          </>
        )}
        <br />
        <input
          type="button"
          value={loading ? "Carregando..." : "Entrar"}
          onClick={handleLogin}
          disabled={loading}
        />
      </div>
      <ProjectFooter/>
    </div>
  );
}

const useFormInput = (initialValue) => {
  const [value, setValue] = useState(initialValue);

  const handleChange = (e) => {
    setValue(e.target.value);
  };
  return {
    value,
    onChange: handleChange,
  };
};

export default Login;
