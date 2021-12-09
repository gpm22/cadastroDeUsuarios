import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router";
import { Modal, Button } from "react-bootstrap";
import Header from "../commons/ProjectHeader";
import Footer from "../commons/ProjectFooter";
import PersonalDataForm from "../form/personal-data/PersonalDataForm";
import AdressForm from "../form/AdressForm";
import { updateUser } from "../commons/CommonFunctions";
import "../form/Form.css";
import AccountAlteration from "./AccountAlteration";

const EditUser = (props) => {
  const location = useLocation();
  let navigate = useNavigate();

  if (!location.state) {
    navigate("/");
    navigate("/login");
  }

  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const [show, setShow] = useState(false);

  const handleClose = () => {
    setShow(false);
    navigate(-1);
  };
  const handleShow = () => setShow(true);

  let user = location.state ? location.state.user : null;
  let adm = location.state ? location.state.adm : null;

  const loged = user != null;
  const role = (adm ? adm.role : "") === "administrator";

  const callBack = (setSt) => {
    return (st) => setSt(st);
  };

  let [personalData, setPersonalData] = useState({
    name: user ? user.name : null,
    cpf: user ? user.cpf : null,
    emails: user ? [...user.emails] : null,
    telephones: user ? [...user.telephones] : null,
  });

  let [adress, setAdress] = useState(user ? { ...user.adress } : null);

  let [userData, setUserData] = useState({
    username: user ? user.username : null,
  });

  let [newUser, setNewUser] = useState(
    user
      ? {
          ...personalData,
          adress: { ...adress },
          ...userData,
          role: user.role,
        }
      : null
  );

  useEffect(() => {
    setNewUser({
      ...personalData,
      adress: { ...adress },
      ...userData,
      role: user ? user.role : "ordinary",
      password: user ? user.password : "",
    });
  }, [personalData, adress, userData]);

  const executeUpdateUser = async () => {
    try {
      const response = await updateUser(newUser);
      if (response.ok) {
        response.json().then((data) => {
          handleShow();
          setError(null);
        });
      } else {
        response.text().then((text) => {
          setError(text);
        });
      }
      setLoading(false);
    } catch (e) {
      console.error(e);
      setError("Erro interno");
      setLoading(false);
    }
  };

  const handleUpdate = (e) => {
    e.preventDefault();
    setLoading(true);
    executeUpdateUser();
  };

  //const myModal = new bootstrap.Modal(document.getElementById('modal-edit-user'), options)

  return (
    <>
      <Header user={adm} loged={loged} location={"alteracao-de-usuario"} />
      <form className="form-block" onSubmit={handleUpdate}>
        {role && (
          <>
            <h1>Alteração de Usuários</h1>
            <PersonalDataForm
              personalData={personalData}
              callBack={callBack(setPersonalData)}
            />
            <AdressForm adress={adress} callBack={callBack(setAdress)} />
            <AccountAlteration
              userData={userData}
              callBack={callBack(setUserData)}
            />
            {error && (
              <>
                <small className="error">{error}</small>
              </>
            )}
            <input
              type="submit"
              className="submit-button"
              disabled={props.loading}
              value={loading ? "Carregando..." : "Alterar Usuário"}
            />
          </>
        )}
      </form>
      <Footer />
      <Modal show={show} onHide={handleClose}>
        <Modal.Header className="bg-dark" closeButton>
          <Modal.Title>
            Usuário "{user.username}" Editado com Sucesso!
          </Modal.Title>
        </Modal.Header>
        <Modal.Footer className="bg-dark">
          <Button variant="success" onClick={handleClose}>
            OK
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default EditUser;
