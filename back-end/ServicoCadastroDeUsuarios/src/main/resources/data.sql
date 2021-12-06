INSERT INTO ADRESSES (adress_id, adress_cep, adress_publicplace, adress_district, adress_city, adress_uf) VALUES (0, '00000000', 'Rua 19 lote 08 casa 01', 'Nova Iguaçu', 'Rio Verde', 'OM');

INSERT INTO TELEPHONES (telephone_id, telephone_type, telephone_number) VALUES (0, 'residencial', '0000000000');
INSERT INTO TELEPHONES (telephone_id, telephone_type, telephone_number) VALUES (1, 'comercial', '1111111111');

INSERT INTO users (user_cpf, user_name, user_password, user_username, user_role, adress_id) VALUES ('00000000000', 'administrador', '$2a$10$iSU.HdehFiMoIkdDRwtIBu/wWhDWTJ5K.4D.sf9lruRBCV5Q36ApK', 'admin', 'administrator', 0);
INSERT INTO users (user_cpf, user_name, user_password, user_username, user_role, adress_id) VALUES ('11111111111', 'comum', '$2a$10$RiQmA5Zw2NHDipcGS8SsAOzUe5JIXlthdjB4bJh3G6jEtM/fcEzXG', 'comum', 'ordinary', 0);

INSERT INTO EMAILS (email_id, adress_email, user_cpf) VALUES (0, 'adm@aoskoaksa.com', '00000000000');
INSERT INTO EMAILS (email_id, adress_email, user_cpf) VALUES (1, 'comum@aoskoaksa.com', '11111111111');
INSERT INTO users_telephones (user_cpf, telephone_id) VALUES ('00000000000', 0);
INSERT INTO users_telephones (user_cpf, telephone_id) VALUES ('11111111111', 1);