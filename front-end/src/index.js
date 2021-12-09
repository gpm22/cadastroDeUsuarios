import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import ReactDOM from "react-dom";
import "./index.css";
import DataViz from "./dataviz/DataViz";
import Login from "./login/Login";
import NotFound from "./not-found/NotFound";
import Form from "./form/Form";
import Alteration from "./alteration/Alteration";
import EditUser from "./alteration/EditUser";


ReactDOM.render(
  <BrowserRouter>
    <Routes>
      <Route path="/"  element={<Login />} />
      <Route path="dados-do-usuario" element={<DataViz />} />
      <Route path="cadastro-de-usuario" element={<Form  />} />
      <Route path="alteracao-de-usuario" element={<Alteration  />} />
      <Route path="alteracao-de-usuario/editar-usuario" element={<EditUser />} />
      <Route path="login" element={<Login />} />
      <Route path="*" element={<NotFound />} />
    </Routes>
  </BrowserRouter>,
  document.getElementById("root")
);
