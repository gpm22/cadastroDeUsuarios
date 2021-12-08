import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router";

import Header from "../commons/ProjectHeader";
import Footer from "../commons/ProjectFooter";
import PersonalDataForm from "../form/personal-data/PersonalDataForm";
import AdressForm from "../form/AdressForm";
import InputButton from "../commons/InputButton";
import { updateUser } from "../commons/CommonFunctions";
import "../form/Form.css";
import AccountAlteration from "./AccountAlteration";

const EditUser = (props) => {
  const location = useLocation();
  const [error, setError] = useState(null);
  const [sucess, setSucess] = useState(null);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  let user = location.state ? location.state : props.user;

  const loged = user != null;
  const role = user.role === "administrator";

  const callBack = (setSt) => {
    return (st) => setSt(st);
  };

  let [personalData, setPersonalData] = useState({
    name: user.name,
    cpf: user.cpf,
    emails: [...user.emails],
    telephones: [...user.telephones],
  });

  let [adress, setAdress] = useState({ ...user.adress });

  let [userData, setUserData] = useState({
    username: user.username,
  });

  let [newUser, setNewUser] = useState({
    ...personalData,
    adress: { ...adress },
    ...userData,
    role: user.role,
  });

  useEffect(() => {
    setNewUser({
      ...personalData,
      adress: { ...adress },
      ...userData,
      role: user.role,
      password: user.password
    });
  }, [personalData, adress, userData]);

  const executeUpdateUser = async () => {
    try {
      const response = await updateUser(newUser);
      if (response.ok) {
        response.json().then((data) => {
          setSucess("Usuário Editado com Sucesso!");
          setError(null);
          navigate("/alteracao-de-usuario");
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

  const handleCreate = (e) => {
    e.preventDefault();
    setLoading(true);
    executeUpdateUser();
  };

  return (
    <>
      <Header user={user} loged={loged} />
      <form className="form-block">
        {role && (
          <>
            <h1>Cadastro de Usuários</h1>
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
              handleOnClick={handleCreate}
              value="Alterar Usuário"
            />
          </>
        )}
      </form>
      <Footer />
    </>
  );
};

export default EditUser;
