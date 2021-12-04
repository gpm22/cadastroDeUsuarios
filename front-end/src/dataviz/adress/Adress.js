import React from "react";

function Adress(props) {
  const cep = (cep) => {
    return (
      cep[0] +
      cep[1] +
      "." +
      cep[2] +
      cep[3] +
      cep[4] +
      "-" +
      cep[5] +
      cep[6] +
      cep[7] 
    );
  }


  return (
    <div className="adress-block">
      <h2>Endereço</h2>
      <p><b>CEP:</b> {cep(props.adress.cep)}</p>
      <p><b>UF:</b> {props.adress.uf}</p>
      <p><b>Cidade:</b> {props.adress.city}</p>
      <p><b>Bairro:</b> {props.adress.district}</p>
      <p><b>Logradouro:</b> {props.adress.publicPlace}</p>
      <p><b>Complemento:</b> {props.adress.complement} </p>
    </div>
  );
}

export default Adress;
