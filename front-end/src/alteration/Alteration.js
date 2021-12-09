import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router";
import Header from "../commons/ProjectHeader";
import Footer from "../commons/ProjectFooter";
import { getAllUsers} from "../commons/CommonFunctions";
import AlterationTable from "./AlterationTable";
import "./Alteration.css";

const Alteration = (props) => {
  const location = useLocation();

  let navigate = useNavigate();

  if (location.state === null) {
    navigate("/login");
  }

  let user = location.state;
  let [users, setUsers] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  const loged = user !== null;
  const role = (user ? user.role : "") === "administrator";

  const callBackSuccess = () => {
    window.location.reload(true);
  };

  const callBackFail = (text) => {
    setError(text);
  }

  const execGetAllUsers = async () => {
    return await getAllUsers()
      .then((response) => {
        if (response.ok) {
          response.json().then((data) => {
            setUsers([...data]);
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

  useEffect(() => {
    execGetAllUsers();
  }, []);

  return (
    <>
      <Header user={user} loged={loged} location={"alteracao-de-usuario"}/>
      <div className="alteration-block">
        {role && (
          <>
            {loading && (
              <div className="alteration-loading-block">
                <h1>Carregando lista de usuários</h1>
                <div className="loader"></div>
              </div>
            )}
            {!loading && (
              <>
                <h1>Alteração de Usuários</h1>
                {error && <small className="error">{error}</small>}
                <AlterationTable users={users} adm={user} callBackSuccess ={callBackSuccess} callBackFail={callBackFail} />
              </>
            )}
          </>
        )}
        <Footer />
      </div>
    </>
  );
};

export default Alteration;
