import { React, useState, useEffect } from "react";
import "./Login.css";
import ProjectFooter from "../commons/ProjectFooter";
import ProjectHeader from "../commons/ProjectHeader";
import { useNavigate } from "react-router-dom";

function Login(props) {
  const username = useFormInput("");
  const password = useFormInput("");
  let [user, setUser] = useState(null);
  let [loged, setLoged] = useState(false);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  let navigate = useNavigate();

  async function login() {
    return await fetch(
      "http://localhost:8080/cadastro-de-usuarios/authenticate",
      {
        method: "post",
        body: JSON.stringify({
          username: username.value,
          password: password.value,
        }),
        headers: {
          "Content-Type": "application/json",
        },
      }
    )
      .then((response) => {
        if (response.ok) {
          response.json().then((data) => {
            setUser(data);
            setLoged(true);
          });
        } else {
          response.text().then((text) => {
            setLoged(false);
            setError(text);
          });
        }
        setLoading(false);
      })
      .catch((e) => {
        console.error(e);
        setError("Erro interno");
        setLoading(false);
      });
  }

  // handle button click of login form
  const handleLogin = (e) => {
    e.preventDefault();
    setLoading(true);
    login();
  };

  useEffect(() => {
    if (loged) {
      navigate("/dados-do-usuario", { state: user });
    }
  }, [loged]);

  return (
    <div id="login-wrapper">
      <div id="invisible-header"></div>
      <ProjectHeader />
      <div className="login-block">
        <h1>Acesso ao Sistema</h1>
        <br />
        <div>
          Nome de Usuário
          <br />
          <input type="text" {...username} autoComplete="username" />
        </div>
        <br />
        <div>
          Senha
          <br />
          <input type="password" {...password} autoComplete="new-password" />
        </div>
        {error && (
          <>
            <br />
            <small className="error">{error}</small>
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
      <ProjectFooter />
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
