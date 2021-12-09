import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router";

import Header from "../commons/ProjectHeader";
import Footer from "../commons/ProjectFooter";
import PersonalDataForm from "./personal-data/PersonalDataForm";
import AdressForm from "./AdressForm";
import AccountForm from "./AccountForm";
import { createUser, cpfChecker } from "../commons/CommonFunctions";
import "./Form.css";

const Form = (props) => {
  const location = useLocation();
  let navigate = useNavigate();

  if (!location.state) {
    navigate("/");
    navigate("/login");
  }

  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);
  const [loading, setLoading] = useState(false);
  const [passwordEquals, setPasswordEquals] = useState(false);

  let user = location.state;// ? location.state : props.user;

  const loged = user !== null;
  const role = (user? user.role: "") === "administrator";

  const callBack = (setSt) => {
    return (st) => setSt(st);
  };

  let [personalData, setPersonalData] = useState({
    name: "",
    cpf: "",
    emails: [],
    telephones: [],
  });

  let [adress, setAdress] = useState({
    cep: "",
    uf: "",
    city: "",
    district: "",
    publicPlace: "",
    complement: "",
  });

  let [userData, setUserData] = useState({
    username: "",
    password: "",
  });

  let [newUser, setNewUser] = useState({
    ...personalData,
    adress: {...adress},
    ...userData,
    role: "ordinary"
  });


  useEffect(() => {
    setNewUser({
      ...personalData,
      adress: {...adress},
      ...userData,
      role: "ordinary"
    });
  }, [personalData, adress, userData]);

  const executeCreateUser = () => {
    return createUser(newUser)
      .then((response) => {
        if (response.ok) {
          response.json().then((data) => {
            setSuccess("Novo Usuário Criado com Sucesso!");
            setError(null);
          });
        } else {
          response.text().then((text) => {
            setError(text);
          });
        }
        setLoading(false);
      })
      .catch((e) => {
        console.error(e);
        setError("Erro interno");
        setLoading(false);
      });
  };

  const handleCreate = (e) => {
    e.preventDefault();
    setLoading(true);
    if (cpfChecker(personalData.cpf) && passwordEquals) {
      executeCreateUser();
    } else if (!passwordEquals) {
      setLoading(false);
      setError("As senhas precisam ser iguais!");
    } else {
      setLoading(false);
      setError("CPF Inválido!");
    }
  };

  return (
    <>
      <Header user={user} loged={loged} location={"cadastro-de-usuario"}/>
      <form className="form-block"  onSubmit={handleCreate}>
        {role && (
          <>
            <h1>Cadastro de Usuários</h1>
            <PersonalDataForm callBack={callBack(setPersonalData)} />
            <AdressForm callBack={callBack(setAdress)} />
            <AccountForm
              callBack={callBack(setUserData)}
              callBackPassword={callBack(setPasswordEquals)}
            />
            {error && (
              <>
                <small className="error">{error}</small>
              </>
            )}
            {success && (
              <>
                <small className="sucess">{success}</small>
              </>
            )}
            {!error && !success && (
              <>
                <br />
              </>
            )}
            <input
              type="submit"
              className="submit-button"
              disabled={props.loading}
              value={loading ? "Carregando..." : "Criar Novo Usuário"}
            />
          </>
        )}
      </form>
      <Footer />
    </>
  );
};

export default Form;
