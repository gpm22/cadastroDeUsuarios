import React from "react";

import Telephone from "./Telephone";

function PersonalData(props) {
  const cpf = (cpf) => {
    return (
      cpf[0] +
      cpf[1] +
      cpf[2] +
      "." +
      cpf[3] +
      cpf[4] +
      cpf[5] +
      "." +
      cpf[6] +
      cpf[7] +
      cpf[8] +
      "-" +
      cpf[9] +
      cpf[10]
    );
  };

  const emails = props.user.emails.map((email) => <p key={email.id}>{email.email}</p>);

  const telephones = props.user.telephones.map((telephone) => (
    <Telephone key={telephone.number} telephone={telephone} />
  ));

  return (
    <div className="personal-data-block">
      <h2>Dados Pessoais</h2>
      <p> <b>Nome Completo:</b> {props.user.name}</p>
      <p> <b>CPF:</b> {cpf(props.user.cpf)}</p>
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
}

export default PersonalData;
