/*create database BDEmpresa;
drop database bdempresa;*/
use BDEmpresa;

create table empregado(
	nombre varchar(25) NOT NULL,
    apelido_1 varchar(25) NOT NULL,
    apelido_2 varchar(25) NOT NULL,
    NSS varchar(15) primary key NOT NULL,
    rua varchar(30),
    numero_rua int,
    piso varchar(4),
    cp char(5),
    localidade char(25),
    data_nacimento date,
    salario float,
    sexo char(1),
    NSS_supervisa varchar(15),
    Num_departamento int
    );
    
create table departamento(
	Num_departamento int primary key NOT NULL,
    nome_departamento varchar(25) NOT NULL,
    NSS_dirige varchar(15),
    data_direccion date
    );
    
create table proxecto(
	Num_proxecto int primary key NOT NULL,
    nome_proxecto varchar(25) Not null,
    lugar varchar(25) not null,
    Num_departamento int not null
);

create table empregado_proxecto(
	NSS_empregado varchar(15) NOT NULL,
    Num_proxecto int NOT NULL,
    horas_semanais int
);

alter table empregado_proxecto 
add FOREIGN KEY (NSS_empregado) REFERENCES empregado(NSS),
add FOREIGN KEY (Num_proxecto) REFERENCES proxecto(Num_proxecto);

alter table proxecto
add FOREIGN KEY (Num_departamento) REFERENCES departamento(Num_departamento);

alter table departamento 
add FOREIGN KEY (NSS_dirige) REFERENCES empregado(NSS);

alter table empregado
add FOREIGN KEY (NSS_supervisa) REFERENCES empregado(NSS),
add FOREIGN KEY (Num_departamento) REFERENCES departamento(Num_departamento);

/*INSERTS*/
set FOREIGN_KEY_CHECKS=0;

INSERT INTO DEPARTAMENTO VALUES (1, 'PERSOAL', '1111111', '2000-03-12');
INSERT INTO DEPARTAMENTO VALUES (2, 'CONTABILIDADE', '2525252', '2001-02-22');
INSERT INTO DEPARTAMENTO VALUES (3, 'TÉCNICO', '2221111', '2002-11-13');
INSERT INTO DEPARTAMENTO VALUES (4, 'INFORMÁTICA', '8888889', '2001-08-19');
INSERT INTO DEPARTAMENTO VALUES (5, 'ESTADÍSTICA', '4444444', '2003-05-14');
INSERT INTO DEPARTAMENTO VALUES (6, 'INNOVACIÓN', '7777777', '2004-06-16');
INSERT INTO EMPREGADO VALUES ('Rocio','López', 'Ferreiro', '0010010', 'Montero Ríos', 145, '6-
G','36208','Vigo', '1999-3-12', 1300, 'M', '1010001', 1);
INSERT INTO EMPREGADO VALUES ('Javier','Quintero', 'Alvarez', '0110010', 'Montevideo', 10, '2-F', '36209',
'Vigo','1987-5-12', 1456.99, 'H','1010001', 1);
INSERT INTO EMPREGADO VALUES ('Germán', 'Gómez', 'Rodríguez','0999900','Sanjurjo Badía', 98, '3 -D',
'36212','Vigo','1997-5-13', 8500.40, 'H', '8888889', 4);
INSERT INTO EMPREGADO VALUES ('Diego', 'Lamela', 'Bello', '1010001', 'Camelias', 123, '4-A', '36211', 'Vigo',
'1989-4-23', 2000, 'H', '1111111', 1);
INSERT INTO EMPREGADO VALUES ('Felix', 'Barreiro', 'Casa', '1100222', 'Rinlo', 5, ' 5-C', '27709', 'Ribadeo',
'1987-6-22', 5000, 'H', '7777777', 6);
INSERT INTO EMPREGADO VALUES ('Pepe', 'López', 'López', '1111111', 'Olmo', 23, '4-B', '27003', 'Lugo', '1997-5-11', 3000, 'H', NULL, 1);
INSERT INTO EMPREGADO VALUES ('Xiao', 'Vecino', 'Vecino', '1122331', 'Brasil', 10, '2', NULL, 'Vigo', '1998-4-26', 2390, 'H', '2525252', 2);
INSERT INTO EMPREGADO VALUES ('Eligio', 'Rodrigo', 'Olmo', '1231231', 'Espiño', 3, '8', '15708', 'Santi ago',
'1987-12-9', 2197, 'H', '4444444', 5);
INSERT INTO EMPREGADO VALUES ('Marta', 'Bello', 'Arias', '1341431', 'Gran Vía', 23, '4-D', NULL, 'Vigo',
'1997-6-12', 1500, 'M', '2525252', 2);
INSERT INTO EMPREGADO VALUES ('Duarte', 'Xil', 'Torres', '2221111', 'Sol', 44, '1 -A', '27002', 'Lugo', '1987-11-14', 1500, 'H', '1111111', 3);
INSERT INTO EMPREGADO VALUES ('José Manuel', 'García', 'Graña', '2525252', 'Illas Canarias', 101, '2-B', NULL,
'Vigo', '2000-9-1', 3110, 'H', '1111111', 2);
INSERT INTO EMPREGADO VALUES ('Javier', 'Lamela', 'López', '3330000', 'Avda de Vigo', 120, '4-C', NULL,
'Pontevedra', '1977-7-2', 2650, 'H', '2221111', 3);
INSERT INTO EMPREGADO VALUES ('Paula', 'Mar', 'López', '3338883', 'Piñeira', 10, NULL, '27400', 'Monfo rte',
'1967-5-11', 2250, 'M', '6000006', 3);
INSERT INTO EMPREGADO VALUES ('Rosa', 'Murillo', 'Rivera', '4044443', 'Piñeira', 25, NULL, '27400',
'Monforte', '1987-5-11', 2150, 'M','6000006', 3);
INSERT INTO EMPREGADO VALUES ('Sara', 'Plaza', 'Marín', '4444444', 'Ciruela', 10, '6 -B', '15705', 'Santiago',
'1987-8-12', 2950, 'M', '1111111', 5);
INSERT INTO EMPREGADO VALUES ('Antonia', 'Romero', 'Boo', '4444999', 'Olmedo', 10, NULL, NULL, 'Santiago',
'1988-6-2', 2850, 'M', '8888889', 4);
INSERT INTO EMPREGADO VALUES ('Uxío', 'Cabado', 'Penalta', '5000000', 'Nueva', 20, '3 -C', NULL, 'Santiago',
'1987-8-14', 2600, 'H', '2221111', 3);
INSERT INTO EMPREGADO VALUES ('Anxos', 'Loures', 'Freire', '5555000', 'Rosalía de Castro', 105, '4-F', NULL,
'Santiago', '1978-8-12', 20500.0000, 'M', '5000000', 3);
INSERT INTO EMPREGADO VALUES ('Beatríz', 'Mallo', 'López', '6000006', 'Cardenal Quiroga', 10, '2-A', '27400',
'Monforte', '1976-5-9', 26000.0000, 'M', '2221111', 3);
INSERT INTO EMPREGADO VALUES ('Carmen', 'Jurado', 'Vega', '6000600', 'Oliva', 10, '2', NULL, 'Pontev edra',
'1983-7-15',1500, 'M', '3330000', 3);
INSERT INTO EMPREGADO VALUES ('Valeriano', 'Boo', 'Boo', '6565656', 'Marina', 23, '2', NULL, 'Ribadeo','1987-6-16', 25000, 'H', '1100222', 6);
INSERT INTO EMPREGADO VALUES ('Alex', 'Méndez', 'García', '7000007', 'Peregrina', 3, '1', NULL , 'Pontevedra',
'1986-5-11', 2300, 'H', '3330000', 3);
INSERT INTO EMPREGADO VALUES ('Ana María', 'Ramilo', 'Barreiro', '7777777', 'Virxe da cerca', 23, NULL, NULL,
'Santiago', '1986-4-8', 3100, 'M', '1111111', 6);
INSERT INTO EMPREGADO VALUES ('Rubén', 'Guerra', 'Vázquez', '8888877', 'Preguntoiro', 11, '1', NULL,
'Santiago', '1986-6-6', 2500, 'H', '7777777', 6);
INSERT INTO EMPREGADO VALUES ('Agostiño', 'Cerviño', 'Seoane', '8888889', 'Montero Ríos', 120, '4 -D', '36208',
'Vigo','1986-7-14', 3250, 'H', '1111111', 4);
INSERT INTO EMPREGADO VALUES ('Angeles', 'López', 'Arias', '9876567', 'San Telmo', 5, '2-C', '36680', 'A
Estrada', '1987-7-13', 1560, 'M', '4444444', 5);
INSERT INTO EMPREGADO VALUES ('Breixo', 'Pereiro', 'Lamela', '9900000', 'Sar', 29, '1' , NULL, 'Santiago',
'1987-2-3', 1400, 'H', '4444999', 4);
INSERT INTO EMPREGADO VALUES ('Celia', 'Bueno', 'Valiña', '9990009', 'Montero Ríos', 120, '4-D', '36208',
'Vigo', '1985-7-1', 1800, 'M', '1010001', 1);
INSERT INTO EMPREGADO VALUES ('Paulo', 'Máximo', 'Guerra', '9998888', 'Montero Ríos', 29, '2-A', NULL,'Santiago', '1984-5-8', 1500, 'H', '7777777', 6);
INSERT INTO PROXECTO VALUES (1,'XESTION DE PERSOAL', 'VIGO', 4);
INSERT INTO PROXECTO VALUES (2,'PORTAL', 'SANTIAGO', 4);
INSERT INTO PROXECTO VALUES (3,'APLICACIÓN CONTABLE', 'VIGO', 4);
INSERT INTO PROXECTO VALUES (4,'INFORME ESTADISTICO ANUAL', 'A ESTRADA', 5);
INSERT INTO PROXECTO VALUES (5,'PRODUCIÓN NOVO PRODUTO', 'RIBADEO', 6);
INSERT INTO PROXECTO VALUES (6,'DESEÑO NOVO CPD LUGO', 'LUGO', 3);
INSERT INTO PROXECTO VALUES (7,'MELLORAS SOCIAIS', 'VIGO', 1);
INSERT INTO PROXECTO VALUES (8,'DESEÑO NOVA TENDA VIGO', 'MONFORTE', 3);
INSERT INTO PROXECTO VALUES (9,'PROXECTO X', 'SANTIAGO', 5);
INSERT INTO PROXECTO VALUES (10,'PROXECTO Y', 'PONTEVEDRA', 3);
INSERT INTO EMPREGADO_PROXECTO VALUES ('0010010', 8, 20);
INSERT INTO EMPREGADO_PROXECTO VALUES ('0110010', 7, 20);
INSERT INTO EMPREGADO_PROXECTO VALUES ('0999900', 1, 30);
INSERT INTO EMPREGADO_PROXECTO VALUES ('0999900', 3, 20);
INSERT INTO EMPREGADO_PROXECTO VALUES ('1010001', 1, 25);
INSERT INTO EMPREGADO_PROXECTO VALUES ('1010001', 7, 15);
INSERT INTO EMPREGADO_PROXECTO VALUES ('1100222', 5, 30);
INSERT INTO EMPREGADO_PROXECTO VALUES ('1122331', 8, 35);
INSERT INTO EMPREGADO_PROXECTO VALUES ('1231231', 4, 20);
INSERT INTO EMPREGADO_PROXECTO VALUES ('1231231', 9, 20);
INSERT INTO EMPREGADO_PROXECTO VALUES ('1341431', 3, 15);
INSERT INTO EMPREGADO_PROXECTO VALUES ('2221111', 6, 20);
INSERT INTO EMPREGADO_PROXECTO VALUES ('2221111', 8, 10);
INSERT INTO EMPREGADO_PROXECTO VALUES ('3330000', 10, 25);
INSERT INTO EMPREGADO_PROXECTO VALUES ('3338883', 8, 30);
INSERT INTO EMPREGADO_PROXECTO VALUES ('4044443', 8, 15);
INSERT INTO EMPREGADO_PROXECTO VALUES ('4444999', 2, 30);
INSERT INTO EMPREGADO_PROXECTO VALUES ('6000006', 8, 25);
INSERT INTO EMPREGADO_PROXECTO VALUES ('8888889', 1, 30);
INSERT INTO EMPREGADO_PROXECTO VALUES ('8888889', 2, 10);
INSERT INTO EMPREGADO_PROXECTO VALUES ('8888889', 7, 5);
INSERT INTO EMPREGADO_PROXECTO VALUES ('9876567', 4, 35);
INSERT INTO EMPREGADO_PROXECTO VALUES ('9876567', 9, 10);
INSERT INTO EMPREGADO_PROXECTO VALUES ('9900000', 2, 40);
INSERT INTO EMPREGADO_PROXECTO VALUES ('9990009', 7, 20);

set FOREIGN_KEY_CHECKS=1;

 

    
    