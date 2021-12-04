import React from "react";
import PersonalData from "./personal-data/PersonalData";
import Adress from "./adress/Adress";
import AccountData from "./account-data/AccountData";
import Header from "../commons/ProjectHeader";
import Footer from "../commons/ProjectFooter";

import "./DataViz.css";

const DataViz = (props) => {
  return (
    <>
      <Header />
      <div className="dataviz-block">
        <h1>Dados do Usuário</h1>
        <PersonalData user={props.user} />
        <Adress adress={props.user.adress} />
        <AccountData user={props.user} />
      </div>
      <Footer />
    </>
  );
};

export default DataViz;
