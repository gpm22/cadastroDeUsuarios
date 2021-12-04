import React from "react";

function capitalizeFirstLetter(string) {
  return string.charAt(0).toUpperCase() + string.slice(1);
}

function Telephone(props) {
  if (props.telephone.type === "celular") {
    return (
      <p>
        ({props.telephone.number[0] + props.telephone.number[1]}){" "}
        {props.telephone.number[2] +
          props.telephone.number[3] +
          props.telephone.number[4] +
          props.telephone.number[5] +
          props.telephone.number[6]}
        -
        {props.telephone.number[7] +
          props.telephone.number[8] +
          props.telephone.number[9] +
          props.telephone.number[10]} - {capitalizeFirstLetter(props.telephone.type)}
      </p>
    );
  }

  return (
    <p>
      ({props.telephone.number[0] + props.telephone.number[1]}){" "}
      {props.telephone.number[2] +
        props.telephone.number[3] +
        props.telephone.number[4] +
        props.telephone.number[5]}
      -
      {props.telephone.number[6] +
        props.telephone.number[7] +
        props.telephone.number[8] +
        props.telephone.number[9]}  - {capitalizeFirstLetter(props.telephone.type)}
    </p>
  );
}

export default Telephone;
