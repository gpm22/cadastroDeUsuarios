import React from "react";
import PersonalData from "./personal-data/PersonalData";
import { useLocation, useNavigate } from "react-router";
import Adress from "./adress/Adress";
import AccountData from "./account-data/AccountData";
import Header from "../commons/ProjectHeader";
import Footer from "../commons/ProjectFooter";

import "./DataViz.css";

const DataViz = (props) => {
  const location = useLocation();
  let navigate = useNavigate();

  if (!location.state) {
    navigate("/login");
  }

  let user = location.state;
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
