INSERT INTO ADRESSES (adress_id, adress_cep, adress_publicplace, adress_district, adress_city, adress_uf) VALUES (0, '0000000', 'Rua 19 lote 08 casa 01', 'Nova Iguaçu', 'Rio Verde', 'OM');


INSERT INTO users (user_cpf, user_name, user_password, user_username, user_role, adress_id) VALUES ('0', 'administrador', '123456', 'admin', 'administrator', 0);
INSERT INTO users (user_cpf, user_name, user_password, user_username, user_role, adress_id) VALUES ('1', 'comum', '123456', 'comum', 'ordinary', 0);