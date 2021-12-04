import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import DataViz from "./dataviz/DataViz";
import Login from "./login/Login";
import NotFound from "./not-found/NotFound";

const user = {
  cpf: "00000000000",
  name: "administrador",
  username: "admin",
  password: "123456",
  role: "administrator",
  emails: [
    {
      id: 0,
      email: "askaoskoaks@aoskoaksa.com",
    },
    {
      id: 1,
      email: "23123213213@aoskoaksa.com",
    },
  ],
  telephones: [
    {
      id: 0,
      type: "fixo",
      number: "6132818210",
    },
    {
      id: 1,
      type: "celular",
      number: "61992818210",
    },
  ],
  adress: {
    id: 0,
    cep: "00000000",
    publicPlace: "Rua 19 lote 08 casa 01",
    district: "Nova Iguaçu",
    city: "Rio Verde",
    uf: "OM",
    complement: "ao lado do posto de saúde",
  },
};

ReactDOM.render(
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<App />} />
      <Route path="dados-do-usuario" element={<DataViz user={user} />} />
      <Route path="login" element={<Login />} />
      <Route path="*" element={<NotFound />} />
    </Routes>
  </BrowserRouter>,
  document.getElementById("root")
);
