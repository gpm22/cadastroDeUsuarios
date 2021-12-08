import React from "react";
import AlterationRow from "./AlterationRow";

const AlterationTable = (props) => {
  const rows = props.users.map((user, index) => {
    return <AlterationRow key={index} adm={props.adm} user={user} callBack={props.callBack}/>;
  });

  return (
    <table className="alteration-table">
      <thead>
        <tr>
          <th>Nome de Usuário</th>
          <th>Nome Completo</th>
          <th>CPF</th>
          <th>Telefones</th>
          <th>Emails</th>
          <th>CEP</th>
          <th>UF</th>
          <th>Cidade</th>
          <th>Bairro</th>
          <th>Logradouro</th>
          <th>Complemento</th>
          <th></th>
        </tr>
      </thead>
      <tbody>{rows}</tbody>
    </table>
  );
};

export default AlterationTable;
