import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import ReactDOM from "react-dom";
import "./index.css";
import DataViz from "./dataviz/DataViz";
import Login from "./login/Login";
import NotFound from "./not-found/NotFound";
import Form from "./form/Form";
import EdicaoRemocao from "./edicao-remocao/EdicaoRemocao";

const user = {
  cpf: "22222222222",
  name: "testador",
  username: "tester",
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
      number: "0000000000",
    },
    {
      id: 1,
      type: "celular",
      number: "00000000000",
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
      <Route path="/" element={<Login />} />
      <Route path="dados-do-usuario" element={<DataViz user={user} />} />
      <Route path="cadastro-de-usuario" element={<Form user={user} />} />
      <Route path="alteracao-de-usuario" element={<EdicaoRemocao user={user} />} />
      <Route path="login" element={<Login />} />
      <Route path="*" element={<NotFound />} />
    </Routes>
  </BrowserRouter>,
  document.getElementById("root")
);
