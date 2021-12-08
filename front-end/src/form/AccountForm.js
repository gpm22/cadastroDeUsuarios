import React, { useState, useEffect } from "react";
import { useFormInput } from "../commons/CommonFunctions";

function AccountForm(props) {
  const username = useFormInput("");
  const newPassword1 = useFormInput("");
  const newPassword2 = useFormInput("");
  const [error, setError] = useState(null);

  const verifyPassword = () => {
    if (newPassword2.value !== "") {
      if (newPassword1.value !== newPassword2.value) {
        setError("As senhas estão diferentes!");
        props.callBackPassword(false);
      } else {
        props.callBackPassword(true);
        setError(null);
      }
    }
  };

  useEffect(() => {
    props.callBack({
      username: username.value,
      password: newPassword1.value,
    });

    verifyPassword();
  }, [username.value, newPassword2.value]);

  return (
    <div className="account-data-block">
      <h2>Dados da Conta</h2>
      <p>
        <b>Nome de Usuário:</b>
      </p>
      <input
        {...username}
        type="text"
        className="input-account-data"
        required
      />
      <br/>
      <h3>Senha</h3>
      <br/>
      <p>Digite a senha: </p>
      <input
        {...newPassword1}
        type="password"
        className="input-account-data"
        required
      />
      <br />
      <p>Confirme a senha: </p>
      <input {...newPassword2} type="password" required />
      <br />
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
    </div>
  );
}

export default AccountForm;
