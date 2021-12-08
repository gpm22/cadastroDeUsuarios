import React, { useState, useEffect } from "react";
import { useFormInput } from "../commons/CommonFunctions";

function AccountAlteration(props) {
  const username = useFormInput(props.userData.username);
  const [error, setError] = useState(null);


  useEffect(() => {
    props.callBack({
      username: username.value
    });
  }, [username.value]);

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

export default AccountAlteration;
