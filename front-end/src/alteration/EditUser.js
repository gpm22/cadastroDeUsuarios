import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router";

import Header from "../commons/ProjectHeader";
import Footer from "../commons/ProjectFooter";
import PersonalDataForm from "../form/personal-data/PersonalDataForm";
import AdressForm from "../form/AdressForm";
import { updateUser } from "../commons/CommonFunctions";
import "../form/Form.css";
import AccountAlteration from "./AccountAlteration";

const EditUser = (props) => {
  const location = useLocation();
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  console.log(location.state);

  let user = location.state ? location.state.user : props.user;
  let adm  = location.state.adm;

  const loged = user != null;
  const role = adm.role === "administrator";

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
      password: user.password,
    });
  }, [personalData, adress, userData]);

  const executeUpdateUser = async () => {
    try {
      const response = await updateUser(newUser);
      if (response.ok) {
        response.json().then((data) => {
          alert("Usuário Editado com Sucesso!");
          setError(null);
          navigate(-1);
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

  return (
    <>
      <Header user={adm} loged={loged} />
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
    </>
  );
};

export default EditUser;
