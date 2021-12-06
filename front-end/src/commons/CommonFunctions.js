import { useState } from "react";

export const useFormInput = (initialValue) => {
  const [value, setValue] = useState(initialValue);

  const handleChange = (e) => {
    setValue(e.target.value);
  };
  return {
    value,
    onChange: handleChange,
  };
};

export const updateUser = (user) => {
  return fetch(
    "http://localhost:8080/cadastro-de-usuarios/update-user/" + user.cpf,
    {
      method: "put",
      body: JSON.stringify(user),
      headers: {
        "Content-Type": "application/json",
      },
    }
  );
};

export const authenticateUser = (userInformations) => {
    return fetch(
        "http://localhost:8080/cadastro-de-usuarios/authenticate",
        {
          method: "post",
          body: JSON.stringify(userInformations),
          headers: {
            "Content-Type": "application/json",
          },
        }
      )
}