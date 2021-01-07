/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Rafa
 * Created: 09-nov-2020
 */

/*

CREATE TABLE PROVINCIA(
   ID INTEGER NOT NULL PRIMARY KEY, 
   CODIGO CHAR(2), 
   NOMBRE VARCHAR(20) NOT NULL 
);

CREATE TABLE CLIENTE(
    dni VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(100),
    direccion VARCHAR(200),
    localidad VARCHAR(100),
    provincia INTEGER,
    telefono CHAR(9),
    FOREIGN KEY (provincia) REFERENCES provincia(id)
);

create table RESERVAS_HAB (
        fecha_in date,
        fecha_out date,
        n_hab integer,
        tipo varchar(30),
        fumador char(1),
        regimen char(1),
        dni_cliente varchar(9),
        foreign key (dni_cliente) references CLIENTE (dni),
        PRIMARY KEY (dni_cliente,fecha_in,fecha_out)
    );
CREATE TABLE RESERVAS_SALON(
    tipo_evento CHAR(1),
    num_personas INTEGER,
    tipo_cocina CHAR(1),
    num_habitaciones INTEGER,
    fecha date,
    num_dias integer,
    dni_cliente varchar(9),
    foreign key (dni_cliente) references CLIENTE (dni),
    PRIMARY KEY (dni_cliente,fecha)
);


INSERT INTO PROVINCIA VALUES (1,'MA','Málaga');
INSERT INTO PROVINCIA VALUES (2,'GR','Granada');
INSERT INTO PROVINCIA VALUES (3,'CA','Cádiz');
INSERT INTO PROVINCIA VALUES (4,'AL','Almería');
INSERT INTO PROVINCIA VALUES (5,'HV','Huelva');
INSERT INTO PROVINCIA VALUES (6,'SV','Sevilla');
INSERT INTO PROVINCIA VALUES (7,'CO','Córdoba');
INSERT INTO PROVINCIA VALUES (8,'JA','Jaén');


insert into cliente values('11111111A','Sergio Garcia','C/Coin ,7','Alahurin el Grande',1,'111111111');
insert into cliente values('22222222B','Rafael Aguilera','C/Palacios ,8','Priego de Córdoba',7,'222222222');
insert into cliente values('33333333C','Victor Garcia','C/Remedios ,12','Coin',1, '333333333');
insert into cliente values('44444444D','Raul Moreno','C/Santa Gracia ,1','Mijas',1, '444444444');
insert into cliente values('55555555E','Ana Cabello','C/Santo Cristo ,35','Malaga',1, '555555555');
*/