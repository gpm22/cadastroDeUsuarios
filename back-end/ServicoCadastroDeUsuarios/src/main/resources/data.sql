INSERT INTO ADRESSES (adress_id, adress_cep, adress_publicplace, adress_district, adress_city, adress_uf) VALUES (0, '0000000', 'Rua 19 lote 08 casa 01', 'Nova Iguaçu', 'Rio Verde', 'OM');

INSERT INTO TELEPHONES (telephone_id, telephone_type, telephone_number) VALUES (0, 'fixo', '32818210');

INSERT INTO users (user_cpf, user_name, user_password, user_username, user_role, adress_id) VALUES ('0', 'administrador', '$2a$10$iSU.HdehFiMoIkdDRwtIBu/wWhDWTJ5K.4D.sf9lruRBCV5Q36ApK', 'admin', 'administrator', 0);
INSERT INTO users (user_cpf, user_name, user_password, user_username, user_role, adress_id) VALUES ('1', 'comum', '$2a$10$RiQmA5Zw2NHDipcGS8SsAOzUe5JIXlthdjB4bJh3G6jEtM/fcEzXG', 'comum', 'ordinary', 0);

INSERT INTO EMAILS (email_id, adress_email, user_cpf) VALUES (0, 'askaoskoaks@aoskoaksa.com', '0');
INSERT INTO users_telephones (user_cpf, telephone_id) VALUES ('0', 0);