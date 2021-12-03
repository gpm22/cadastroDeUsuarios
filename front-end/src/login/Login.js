import React, { useState } from "react";
import "./Login.css";

async function login(usernameString, passwordString) {
  await fetch(
    "http://localhost:8080/cadastro-de-usuarios/authenticate",
    {
      method: "post",
      body: JSON.stringify({
        username: usernameString,
        password: passwordString,
      }),
      headers: {
        "Content-Type": "application/json",
      },
    }).then( (response) => {
    if (response.ok && response.text().includes("true")) {
        alert("deu certo!");
      } else {
        alert("deu ruim!");
      }
    }).catch((e) => {
    console.error(e);
  });

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

  const handleRegister = () => {
    alert("partiu registrar");
  };

  return (
    <div className="login-block">
      Banco de Usuários
      <br />
      <br />
      <div>
        Nome de Usuário
        <br />
        <input type="text" {...username} autoComplete="new-password" />
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
      <div className="buttons">
        <input
          type="button"
          value={loading ? "Carregando..." : "Entrar"}
          onClick={handleLogin}
          disabled={loading}
        />
        <input
          type="button"
          value={loading ? "Carregando..." : "Cadastrar-se"}
          onClick={handleRegister}
          disabled={loading}
        />
      </div>
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