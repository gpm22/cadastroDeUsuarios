import React from "react";
import { useLocation } from "react-router";
import Header from "../commons/ProjectHeader";
import Footer from "../commons/ProjectFooter";
import "./EdicaoRemocao.css";

const EdicaoRemocao = (props) => {

  const location = useLocation();

  let user = ( location.state? location.state : props.user);

  const loged = user != null;
  const role = user.role === "administrator";

  return (
    <>
      <Header user={user} loged={loged} />
      <div className="edicao-remocao-block">
        {role && (
          <>
            <h1>Alteração de Usuários</h1>
            <h1>Parabéns, você pode editar tudo!</h1>
          </>
        )}
      </div>
      <Footer />
    </>
  );
};

export default EdicaoRemocao;
