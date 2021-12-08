import React from "react";
import { useNavigate } from "react-router";
import { deleteUser } from "../commons/CommonFunctions";


const AlterationRow = (props) => {

    const navigate = useNavigate();
  const telephones = props.user.telephones.map((telephone, index) => {
    return (
      <p key={index}>
        {telephone.number} - {telephone.type}
      </p>
    );
  });

  const emails = props.user.emails.map((email, index) => {
    return <p key={index}>{email.email}</p>;
  });

  const removeUser = async (e) => {
    e.preventDefault();
    deleteUser(props.user.cpf)
      .then((response) => {
        if (response.ok) {
          response.json().then((data) => {

          });
        } else {
          response.text().then((text) => {

          });
        }
      })
      .catch((e) => {
        console.error(e);

      });
  };

  const alterateUser = (e) => {
    e.preventDefault();
    navigate("/alteracao-de-usuario/editar-usuario", {state: props.user})
  };

  return (
    <tr>
      <td>{props.user.username}</td>
      <td>{props.user.name}</td>
      <td>{props.user.cpf}</td>
      <td>{telephones}</td>
      <td>{emails}</td>
      <td>{props.user.adress.cep}</td>
      <td>{props.user.adress.uf}</td>
      <td>{props.user.adress.city}</td>
      <td>{props.user.adress.district}</td>
      <td>{props.user.adress.publicPlace}</td>
      <td>{props.user.adress.complement}</td>
      <td className="table-buttons">
        <input
          type="button"
          value="Editar"
          className="alteration-button-edit"
          onClick={alterateUser}
        />
        <input
          type="button"
          value="Remover"
          className="alteration-button-remove"
          onClick={removeUser}
        />
      </td>
    </tr>
  );
};

export default AlterationRow;
