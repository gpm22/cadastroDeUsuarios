import React, { useState } from "react";
import { useNavigate } from "react-router";
import { deleteUser } from "../commons/CommonFunctions";
import { Modal, Button } from "react-bootstrap";

const AlterationRow = (props) => {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
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
            props.callBackSuccess();
          });
        } else {
          response.text().then((text) => {
            props.callBackFail(text);
          });
        }
      })
      .catch((e) => {
        console.error(e);
      });
  };

  const alterateUser = (e) => {
    e.preventDefault();
    navigate("/alteracao-de-usuario/editar-usuario", {
      state: { user: props.user, adm: props.adm },
    });
  };

  return (
    <>
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
            onClick={handleShow}
          />
        </td>
      </tr>
      <Modal  show={show} onHide={handleClose} {...props}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered>
        <Modal.Header className="bg-dark" closeButton>
          <Modal.Title>Quer mesmo remover o usuário {props.user.username}?</Modal.Title>
        </Modal.Header>
        <Modal.Footer className="bg-dark">
          <Button variant="success" onClick={handleClose}>
            Não
          </Button>
          <Button variant="danger" onClick={removeUser}>
            Sim
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default AlterationRow;
