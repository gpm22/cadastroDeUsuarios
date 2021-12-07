import React from "react";
import PersonalData from "./personal-data/PersonalData";
import { useLocation } from "react-router";
import Adress from "./adress/Adress";
import AccountData from "./account-data/AccountData";
import Header from "../commons/ProjectHeader";
import Footer from "../commons/ProjectFooter";

import "./DataViz.css";

const DataViz = (props) => {

  const location = useLocation();

  let user = ( location.state? location.state : props.user);

  return (
    <>
      <Header user={user} />
      <div className="dataviz-block">
        <h1>Dados do Usuário</h1>
        <PersonalData user={user} />
        <Adress adress={user.adress} />
        <AccountData user={user} />
      </div>
      <Footer />
    </>
  );
};

export default DataViz;
