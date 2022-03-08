import { useState } from "react";

export const useFormInput = (initialValue) => {
  const [value, setValue1] = useState(initialValue);

  const handleChange = (e) => {
    setValue1(e.target.value);
  };

  const setValue = (e) => {
    setValue1(e);
  };
  return {
    value,
    onChange: handleChange,
    setValue: setValue
  };
};

export const updateUser = (user) => {
  return fetch(
    "http://localhost:8080/cadastro-de-usuarios/v1/" + user.cpf,
    {
      method: "put",
      body: JSON.stringify(user),
      headers: {
        "Content-Type": "application/json",
      },
    }
  );
};

export const getAllUsers = () => {
  return fetch(
    "http://localhost:8080/cadastro-de-usuarios/v1/",
    {
      method: "get",
      headers: {
        "Content-Type": "application/json",
      },
    }
  );
};

export const deleteUser = (userCpf) => {
  return fetch(
    "http://localhost:8080/cadastro-de-usuarios/v1/" + userCpf,
    {
      method: "delete"
    }
  );
}

export const createUser = (user) => {
  return fetch(
    "http://localhost:8080/cadastro-de-usuarios/v1/",
    {
      method: "post",
      body: JSON.stringify(user),
      headers: {
        "Content-Type": "application/json",
      },
    }
  );
};



export const authenticateUser = (userInformations) => {
  return fetch("http://localhost:8080/cadastro-de-usuarios/v1/authenticate", {
    method: "post",
    body: JSON.stringify(userInformations),
    headers: {
      "Content-Type": "application/json",
    },
  });
};

export const cpfChecker = (cpf) => {
  return cpfFormatChecker(cpf) && cpfCalculationChecker(cpf);
}

const cpfFormatChecker = (cpf) => {
  return /[0-9]{11}/.test(cpf);
};

const cpfCalculationChecker = (cpf) => {
  const numbers = cpf.split("").map(Number);

  return cpfDigitChecker(numbers, 1) && cpfDigitChecker(numbers, 2) && invalidKnownCpfChecker(numbers);
};

const cpfDigitChecker = (cpfDigits, digitChecker) => {
  const digitPosition = digitChecker === 1 ? 9 : 10;

  let sum = 0;

  for (let i = 0; i < digitPosition; i++) {
    sum += cpfDigits[i] * (digitPosition + 1 - i);
  }

  let comparingValue = (sum * 10) % 11;

  if (comparingValue === 10) {
    comparingValue = 0;
  }

  return cpfDigits[digitPosition] === comparingValue;
};

const invalidKnownCpfChecker = (cpfDigits) => {
  for (let i = 1; i < cpfDigits.length; i++) {
    if (cpfDigits[i - 1] !== cpfDigits[i]) {
      return true;
    }
  }
  return false;
};
