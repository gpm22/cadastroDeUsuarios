import React, { useState, useEffect } from "react";
import "./AccountData.css";
import {
  useFormInput,
  updateUser,
  authenticateUser,
} from "../../commons/CommonFunctions";
import InputButton from "../../commons/InputButton";

function AccountData(props) {
  const actualPassword = useFormInput("");
  const newPassword1 = useFormInput("");
  const newPassword2 = useFormInput("");
  const [error, setError] = useState(null);
  const [sucess, setSucess] = useState(null);
  const [loading, setLoading] = useState(false);
  let [verified, setVerified] = useState(false);

  async function verifyPassword() {
    return await authenticateUser({
      username: props.user.username,
      password: actualPassword.value,
    })
      .then((response) => {
        if (response.ok) {
          setVerified(true);
        } else {
          response.text().then((text) => {
            setVerified(false);
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

  const handlePasswordChange = (e) => {
    e.preventDefault();
    setLoading(true);
    verifyPassword();
  };

  useEffect(() => {
    if (verified) {
      if (newPassword1.value !== newPassword2.value) {
        setError("As senhas estão diferentes!");
        newPassword2.value = "";
      } else if (newPassword1.value === actualPassword.value) {
        setError("A nova senha não pode ser igual a atual!");
      } else {
        setLoading(true);
        props.user.password = newPassword1.value;
        console.log(JSON.stringify(props.user));
        updateUser(props.user)
        .then((response) => {
          if (response.ok) {
            setSucess("Senha alterada com sucesso!")
          } else {
            response.text().then((text) => {
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
      
    }
    setVerified(false);
  }, [
    newPassword1,
    newPassword2,
    loading,
    verified,
    props.user,
    actualPassword,
  ]);

  return (
    <div className="account-data-block">
      <h2>Dados da Conta</h2>
      <p>
        <b>Nome de Usuário:</b> {props.user.username}
      </p>
      <h3>Alterar Senha?</h3>
      <p>Digite sua senha atual: </p>
      <input
        {...actualPassword}
        type="password"
        className="input-account-data"
      />
      <br />
      <p>Digite sua nova senha: </p>
      <input {...newPassword1} type="password" className="input-account-data" />
      <br />
      <p>Confirme sua nova senha: </p>
      <input {...newPassword2} type="password" />
      <br />
      {error && (
        <>
          <small className="error">{error}</small>
        </>
      )}
      {sucess && (
        <>
          <small className="sucess">{sucess}</small>
        </>
      )}
      {!error && !sucess && (
        <>
          <br />
        </>
      )}
      <InputButton
        loading={loading}
        handleOnClick={handlePasswordChange}
        value={"Confirmar"}
      />
    </div>
  );
}

export default AccountData;
