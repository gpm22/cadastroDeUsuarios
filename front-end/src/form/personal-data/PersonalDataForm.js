import React, { useState, useEffect } from "react";
import { useFormInput } from "../../commons/CommonFunctions";
import TelephoneForm from "./TelephoneForm";
import ReactInputMask from "react-input-mask";

const PersonalDataForm = (props) => {
  const name = useFormInput(props.personalData ? props.personalData.name : "");
  const cpf = useFormInput(props.personalData ? props.personalData.cpf : "");
  let [emails, setEmails] = useState(
    props.personalData ? [...props.personalData.emails] : [""]
  );
  let [telephones, setTelephones] = useState(
    props.personalData
      ? [...props.personalData.telephones]
      : [
          {
            type: "",
            number: "",
          },
        ]
  );

  const handleChange = (e) => {
    let emailsCopy = [...emails];
    emailsCopy[e.target.title] = e.target.value;
    setEmails([...emailsCopy]);
  };

  const callBackTelephone = (index) => {
    return (st) => {
      let telephonesCopy = [...telephones];
      telephonesCopy[index] = st;
      setTelephones([...telephonesCopy]);
    };
  };

  const addEmail = () => {
    setEmails([...emails, ""]);
  };

  const addTelephone = () => {
    setTelephones([
      ...telephones,
      {
        type: "",
        number: "",
      },
    ]);
  };

  const removeEmail = (e) => {
    let emailsCopy = [...emails];
    emailsCopy.splice(e.target.title, 1);
    setEmails([...emailsCopy]);
  };

  const removeTelephone = (e) => {
    let telephonesCopy = [...telephones];
    telephonesCopy.splice(e.target.title, 1);
    setTelephones([...telephonesCopy]);
  };

  let telephoneHtml = (value, index) => {
    let button =
      index > 0 ? (
        <input
          type="button"
          value="x"
          title={index}
          className="button-delete"
          onClick={removeTelephone}
        />
      ) : null;

    return (
      <>
        <span>
          <h4> Telefone nº {index + 1}</h4> {button}
        </span>
        <TelephoneForm
          key={index}
          telephone={value}
          callBack={callBackTelephone(index)}
        />
      </>
    );
  };

  let telephonesHtml = telephones.map((value, index) =>
    telephoneHtml(value, index)
  );

  const emailHtml = (value, index) => {
    let button =
      index > 0 ? (
        <input
          type="button"
          value="x"
          title={index}
          className="button-delete"
          onClick={removeEmail}
        />
      ) : null;

    return (
      <>
        <br />
        <span>
          <h4> Email nº {index + 1}</h4>
        </span>
        <input
          key={index}
          value={value.email? value.email : value}
          type="email"
          title={index}
          autoComplete="email"
          required
          onChange={handleChange}
        />
        {button}
      </>
    );
  };

  let emailsHtml = emails.map((value, index) => emailHtml(value, index));

  useEffect(() => {
    props.callBack({
      name: name.value,
      cpf: cpf.value.replaceAll(/[(. -]/gi, ""),
      telephones: telephones,
      emails: emails.map((value) => {
        if(value.id > -1) {
          return value;
        }

        return {
          email: value,
        };
      }),
    });
  }, [name.value, cpf.value, emails, telephones]);
  return (
    <section className="personal-data-form-block">
      <h2>Dados Pessoais</h2>
      <p>
        {" "}
        <b>Nome Completo:</b>
      </p>{" "}
      <input type="text" {...name} autoComplete="name" required />
      {!props.personalData && (
        <>
          <p>
            <b>CPF:</b>
          </p>
          <ReactInputMask type="text" mask="999.999.999-99" {...cpf} required />
        </>
      )}
      <section className="emails">
        <h3>Emails</h3>{" "}
        <span>
          <input
            type="button"
            value="+"
            className="button-add"
            onClick={addEmail}
          />
        </span>
        {emailsHtml}
      </section>
      <section className="telephones">
        <h3>Telefones</h3>{" "}
        <span>
          <input
            type="button"
            value="+"
            className="button-add"
            onClick={addTelephone}
          />
        </span>
        <br />
        {telephonesHtml}
      </section>
    </section>
  );
};

export default PersonalDataForm;
