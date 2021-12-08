import React from "react";

const AlterationRow = (props) => {

    const telephones = props.user.telephones.map(telephone => {
       return  <p>{telephone.number} - {telephone.type}</p>
    });

    const emails = props.user.emails.map(email => {
       return <p>{email.email}</p>
    });

    return (
        <tr>
            <td>{props.user.username}</td>
            <td>{props.user.name}</td>
            <td>{props.user.cpf}</td>
            <td>{telephones}</td>
            <td>{emails}</td>
            <td>{props.user.adress.cep}</td>
            <td>{props.user.adress.uf}</td>
            <td>{props.user.adress.city}</td>
            <td>{props.user.adress.district}</td>
            <td>{props.user.adress.publicPlace}</td>
            <td>{props.user.adress.complement}</td>
        </tr>

    );
}

export default AlterationRow;