import React, { useState, useEffect } from "react";
import { useFormInput } from "../commons/CommonFunctions";

const PersonalDataForm = (props) => {
  const name = useFormInput("");
  const cpf = useFormInput("");
  const emails = [];
  const telephones = [];

  useEffect(() => {
      props.callBack({
          name: name.value,
          cpf: cpf.value
      })
  }, [name.value, cpf.value]);

  return (
    <div className="personal-data-form-block">
      <h2>Dados Pessoais</h2>
      <p>
        {" "}
        <b>Nome Completo:</b>
      </p>{" "}
      <input type="text" {...name} autoComplete="name" />
      <p>
        {" "}
        <b>CPF:</b>
      </p>{" "}
      <input type="text" {...cpf} autoComplete="cpf" />
      <section className="emails">
        <h3>Emails</h3>
        {emails}
      </section>
      <section className="telephones">
        <h3>Telefones</h3>
        {telephones}
      </section>
    </div>
  );
};

export default PersonalDataForm;
