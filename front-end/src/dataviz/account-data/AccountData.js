import React, { useState } from "react";
import "./AccountData.css";

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

function AccountData(props){

    const actualPassword = useFormInput("");
    const newPassword1 = useFormInput("");
    const newPassword2 = useFormInput("");

    return (
        <div className="account-data-block">
            <h2>Dados da Conta</h2>
            <p><b>Nome de Usuário:</b> {props.user.username}</p>
            <h3>Alterar Senha?</h3>
            <p>Digite sua senha atual: </p>
            <input {...actualPassword} type="password" className="input-account-data"/>
            <br/>
            <p>Digite sua nova senha: </p>
            <input {...newPassword1} type="password" className="input-account-data"/>
            <br/>
            <p>Confirme sua nova senha: </p>
            <input {...newPassword2} type="password"/>
            <br/>
        </div>
    );
}

export default AccountData;