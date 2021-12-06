import { React, useState, useEffect } from "react";
import "./Login.css";
import ProjectFooter from "../commons/ProjectFooter";
import ProjectHeader from "../commons/ProjectHeader";
import { useNavigate } from "react-router-dom";
import { useFormInput, authenticateUser } from "../commons/CommonFunctions";
import InputButton from "../commons/InputButton";

function Login(props) {
  const username = useFormInput("");
  const password = useFormInput("");
  let [user, setUser] = useState(null);
  let [loged, setLoged] = useState(false);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  let navigate = useNavigate();

  async function login() {
    return await  authenticateUser({
      username: username.value,
      password: password.value,
    })
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

  const handleLogin = (e) => {
    e.preventDefault();
    setLoading(true);
    login();
  };

  useEffect(() => {
    if (loged) {
      navigate("/dados-do-usuario", { state: user });
    }
  }, [loged, navigate, user]);

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
            <small className="error">{error}</small>
          </>
        )}
        {!error && (
          <>
            <br />
          </>
        )}
          <InputButton loading={loading} handleOnClick={handleLogin} value="Entrar" />
      </div>
      <ProjectFooter />
    </div>
  );
}

export default Login;
