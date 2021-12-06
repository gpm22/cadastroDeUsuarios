import React from "react";

const InputButton = (props) => {
    return (
        <input
          type="button"
          value={props.loading ? "Carregando..." : props.value}
          onClick={props.handleOnClick}
          disabled={props.loading}
        />
    );
}

export default InputButton;