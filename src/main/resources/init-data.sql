insert into CUSTOMER (CUSTOMER_NO, VBKZ) values ('kunde1', 'vbkz');
insert into CUSTOMER (CUSTOMER_NO, VBKZ) values ('kunde2', 'wert');

insert into CONTACT_PERSON (ID, SALUTATION, FIRST_NAME, LAST_NAME, PHONE_NUMBER) values (1,'MR', 'Atze', 'Peng', '+495551224');
insert into CONTACT_PERSON (ID, SALUTATION, FIRST_NAME, LAST_NAME, PHONE_NUMBER) values (2,'MRS', 'Inge', 'Knall', '+495551225');
insert into CONTACT_PERSON (ID, SALUTATION, FIRST_NAME, LAST_NAME, PHONE_NUMBER) values (3,'MR', 'Peter', 'Pow', '+495553324');

insert into LOCATION (ID, ADDRESS_LINE1, ZIP_CODE, CITY, COUNTRY_CODE, CUSTOMER_CUSTOMER_NO) values (1, 'Bahnhofstraße 3', '66225', 'Nirgendwo', 'DE', 'kunde1');
insert into LOCATION (ID, ADDRESS_LINE1, ZIP_CODE, CITY, COUNTRY_CODE, CUSTOMER_CUSTOMER_NO) values (2, 'Bergstraße 3', '44553', 'Idyll', 'DE', 'kunde1');

insert into customer_contact_people (customer_customer_no, contact_people_id) values ('kunde1', 1);
insert into customer_contact_people (customer_customer_no, contact_people_id) values ('kunde1', 2);
insert into customer_contact_people (customer_customer_no, contact_people_id) values ('kunde2', 3);

alter sequence hibernate_sequence restart with 100;