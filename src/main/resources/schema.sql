DROP TABLE IF EXISTS PRICES;
DROP TABLE IF EXISTS CADENA_GRUPO;



CREATE TABLE IF NOT EXISTS CADENA_GRUPO (
BRAND_ID INTEGER NOT NULL AUTO_INCREMENT,
NOMBRE VARCHAR2(20) NOT NULL,
PRIMARY KEY (BRAND_ID) );


CREATE TABLE IF NOT EXISTS PRICES (
ID INTEGER NOT NULL AUTO_INCREMENT,
BRAND_ID INTEGER NOT NULL,
START_DATE TIMESTAMP NOT NULL,
END_DATE TIMESTAMP NOT NULL,
PRICE_LIST INTEGER NOT NULL,
PRODUCT_ID INTEGER NOT NULL,
PRIORITY INTEGER NOT NULL,
PRICE DECIMAL(20,2) NOT NULL,
CURR VARCHAR2(4) NOT NULL,
PRIMARY KEY (ID)
);



INSERT INTO prices (brand_id,start_date,end_date,price_list,product_id,priority,price,curr) VALUES (1,'2020-06-14T00.00.00','2020-12-31T23.59.59',1,35455,0,35.50,'EUR');
INSERT INTO prices (brand_id,start_date,end_date,price_list,product_id,priority,price,curr) VALUES (1,'2020-06-14T15.00.00','2020-06-14T18.30.00',2,35455,1,25.45,'EUR');
INSERT INTO prices (brand_id,start_date,end_date,price_list,product_id,priority,price,curr) VALUES (1,'2020-06-15T00.00.00','2020-06-15T11.00.00',3,35455,1,30.50,'EUR');
INSERT INTO prices (brand_id,start_date,end_date,price_list,product_id,priority,price,curr) VALUES (1,'2020-06-15T16.00.00','2020-12-31T23.59.59',4,35455,1,38.95,'EUR');
