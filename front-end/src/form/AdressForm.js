import React, { useEffect, useState } from "react";
import { useFormInput } from "../commons/CommonFunctions";
import InputButton from "../commons/InputButton";
import ReactInputMask from "react-input-mask";

function AdressForm(props) {
  const cep = useFormInput(props.adress ? props.adress.cep :"");
  const uf = useFormInput(props.adress ? props.adress.uf :"");
  const city = useFormInput(props.adress ? props.adress.city :"");
  const district = useFormInput(props.adress ? props.adress.district :"");
  const publicPlace = useFormInput(props.adress ? props.adress.publicPlace :"");
  const complement = useFormInput(props.adress ? props.adress.complement :"");
  const [error, setError] = useState(null);
  const [changed, setChanged] = useState(false);
  const [loading, setLoading] = useState(false);

  async function getCepInfo(cepValue) {
    return await fetch("https://viacep.com.br/ws/" + cepValue + "/json/", {
      method: "get",
    })
      .then((response) => {
        if (response.ok) {
          response.json().then((data) => {

            if (!data.erro) {
              cep.setValue(data.cep);
              city.setValue(data.localidade);
              district.setValue(data.bairro);
              uf.setValue(data.uf);
              publicPlace.setValue(data.logradouro);
              complement.setValue(data.complemento);
              setError(null);
            } else {
              setError("CEP desconhecido");
            }
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
  }

  const isAdressesEquals = () => {

    const objectToCompare = {
      cep: cep.value.replaceAll(/[. -]/ig, ""),
      uf: uf.value,
      city: city.value,
      district: district.value,
      publicPlace: publicPlace.value,
      complement: complement.value,
    }

    if(!props.adress){
      return false;
    }

    for(let key of Object.keys(props.adress)){
      if(key==="id"){
        continue;
      }

      if(props.adress[key] !== objectToCompare[key]){
        return false;
      }
    }

    return true;

  }

  const handleCep = (e) => {
    e.preventDefault();
    setLoading(true);
    getCepInfo(cep.value.replaceAll(/[. -]/ig, ""));
  };

  useEffect(() => {

    setChanged(!isAdressesEquals());
    console.log("changed - " + changed);

    if(props.adress && !changed){
      props.callBack({
        id: props.adress.id,
        cep: cep.value.replaceAll(/[. -]/ig, ""),
        uf: uf.value,
        city: city.value,
        district: district.value,
        publicPlace: publicPlace.value,
        complement: complement.value,
      });
    } else {
      props.callBack({
        cep: cep.value.replaceAll(/[. -]/ig, ""),
        uf: uf.value,
        city: city.value,
        district: district.value,
        publicPlace: publicPlace.value,
        complement: complement.value,
      });

    }
  }, [
    cep.value,
    uf.value,
    city.value,
    district.value,
    publicPlace.value,
    complement.value,
  ]);

  return (
    <section className="adress-form-block">
      <h2>Endere??o</h2>
      <p>
        <b>CEP:</b>
      </p>{" "}
      <ReactInputMask mask="99.999-999" type="text" {...cep} autoComplete="cep" required/>
      {error && (
        <>
          <small className="error">{error}</small>
        </>
      )}
      <InputButton
        loading={loading}
        handleOnClick={handleCep}
        value="pesquisar CEP"
      />
      <br/>
      <p>
        <b>UF:</b>{" "}
      </p>{" "}
      <input type="text" {...uf} autoComplete="uf" required />
      <p>
        <b>Cidade:</b>{" "}
      </p>{" "}
      <input type="text" {...city} autoComplete="city" required />
      <br/>
      <p>
        <b>Bairro:</b>{" "}
      </p>{" "}
      <input type="text" {...district} autoComplete="district" required />
      <p>
        <b>Logradouro:</b>{" "}
      </p>{" "}
      <input type="text" {...publicPlace} autoComplete="publicPlace" required />
      <br/>
      <p>
        <b>Complemento:</b>{" "}
      </p>{" "}
      <input type="text" {...complement} autoComplete="complement" />
    </section>
  );
}

export default AdressForm;
