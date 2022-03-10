INSERT INTO ADRESSES (adress_id, adress_cep, adress_publicplace, adress_district, adress_city, adress_uf, adress_complement) VALUES (0, '00000000', 'Rua 98 lote 89 casa 712', 'Nova Galáxia Federal', 'Rio Roxo Azulado', 'OM', '');

INSERT INTO TELEPHONES (telephone_id, telephone_type, telephone_number) VALUES (0, 'residencial', '0000000000');
INSERT INTO TELEPHONES (telephone_id, telephone_type, telephone_number) VALUES (10, 'comercial'  , '1111111111');
INSERT INTO TELEPHONES (telephone_id, telephone_type, telephone_number) VALUES (20, 'celular'    , '22222222222');
INSERT INTO TELEPHONES (telephone_id, telephone_type, telephone_number) VALUES (30, 'celular'    , '33333333333');

INSERT INTO users (user_cpf, user_name, user_password, user_username, user_role, adress_id) VALUES ('00000000000', 'administrador', '$2a$10$iSU.HdehFiMoIkdDRwtIBu/wWhDWTJ5K.4D.sf9lruRBCV5Q36ApK', 'admin', 'administrator', 0);
INSERT INTO users (user_cpf, user_name, user_password, user_username, user_role, adress_id) VALUES ('11111111111', 'comum'        , '$2a$10$RiQmA5Zw2NHDipcGS8SsAOzUe5JIXlthdjB4bJh3G6jEtM/fcEzXG', 'comum', 'ordinary'     , 0);

INSERT INTO EMAILS (email_id, adress_email, user_cpf) VALUES (0, 'adm@bancodeusuarios.com'  , '00000000000');
INSERT INTO EMAILS (email_id, adress_email, user_cpf) VALUES (1, 'comum@bancodeusuarios.com', '11111111111');

INSERT INTO users_telephones (user_cpf, telephone_id) VALUES ('00000000000', 0);
INSERT INTO users_telephones (user_cpf, telephone_id) VALUES ('11111111111', 10);
INSERT INTO users_telephones (user_cpf, telephone_id) VALUES ('00000000000', 20);
INSERT INTO users_telephones (user_cpf, telephone_id) VALUES ('11111111111', 30);

INSERT INTO telephones_users (telephone_entity_telephone_id, users_user_cpf) VALUES (0, '00000000000');
INSERT INTO telephones_users (telephone_entity_telephone_id, users_user_cpf) VALUES (10, '11111111111');
INSERT INTO telephones_users (telephone_entity_telephone_id, users_user_cpf) VALUES (20, '00000000000');
INSERT INTO telephones_users (telephone_entity_telephone_id, users_user_cpf) VALUES (30, '11111111111');