import React, { useEffect } from "react";
import ReactInputMask from "react-input-mask";
import { useFormInput } from "../../commons/CommonFunctions";

function TelephoneForm(props) {
  const number = useFormInput(props.telephone.number);
  const type = useFormInput(props.telephone.type);

  if(number.value === "" && props.telephone.number!==""){
    number.setValue(props.telephone.number);
  }

  if(type.value==="" &&  props.telephone.type!==""){
    type.setValue(props.telephone.type);
  }
  
  useEffect(() => {


    if(props.telephone.id){

      props.callBack({
        id: props.telephone.id,
        type: type.value,
        number: number.value.replaceAll(/[() -]/ig, "")
      });
    } else {
      props.callBack({
        type: type.value,
        number: number.value.replaceAll(/[() -]/ig, "")
      });
    }

    
  }, [type.value, number.value]);

  return (
    <div className="telephone-form-block">
      <p>
        {" "}
        <b>Tipo do número telefônico:</b>
      </p>{" "}
      <select type="text" {...type}>
        <option value=""></option>
        <option value="celular">Celular</option>
        <option value="residencial">Residencial</option>
        <option value="comercial">Comercial</option>
      </select>
      {type.value === "celular" && (
        <>
          <p>
            <b>Número:</b>
          </p>
          <ReactInputMask type="tel"
            {...number}
            mask="(99) 99999-9999"
            autoComplete="number"
            pattern="\([0-9]{2}\) [0-9]{5}-[0-9]{4}"
            required />
        </>
      )}
      {type.value !== "celular" && type.value !== "" && (
        <>
          <p>
            <b>Número:</b>
          </p>
          <ReactInputMask type="tel"
            {...number}
            mask="(99) 9999-9999"
            autoComplete="number"
            pattern="\([0-9]{2}\) [0-9]{4}-[0-9]{4}"
            required />
        </>
      )}
    </div>
  );
}

export default TelephoneForm;
