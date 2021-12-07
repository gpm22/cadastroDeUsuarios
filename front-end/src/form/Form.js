import React, {useState} from "react";
import { useLocation } from "react-router";

import Header from "../commons/ProjectHeader";
import Footer from "../commons/ProjectFooter";
import PersonalDataForm from "./PersonalDataForm";
import "./Form.css";

const Form = (props) => {

  const location = useLocation();

  let user = ( location.state? location.state : props.user);

  const loged = user != null;
  const role = user.role === "administrator";

  const callBack = (setSt) => {
    return (st) => setSt(st);
  };

  let [personalData, setPersonalData] = useState({
    name: "",
    cpf: "",
    emails : [],
    telephones: []
  });

  let [adress, setAdress] = useState({
    cep: "",
    uf: "",
    city : "",
    district: "",
    publicPlace: "",
    complement: ""
  });

  let [userData, setUserData] = useState({
    username: "",
    password: ""
  });

  console.log(personalData);

  return (
    <>
      <Header user={user} loged={loged} />
      <div className="form-block">
        {role && (
          <>
            <h1>Cadastro de Usuários</h1>
            <PersonalDataForm callBack={callBack(setPersonalData)} />
          </>
        )}
      </div>
      <Footer />
    </>
  );
};

export default Form;
