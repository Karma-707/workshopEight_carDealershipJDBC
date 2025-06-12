# ---------------------------------------------------------------------- #
# Target DBMS:           MySQL                                           #
# Project name:          workshopSeven_carDealershipDatabase             #
# ---------------------------------------------------------------------- #
DROP DATABASE IF EXISTS carDealershipDatabase;

CREATE DATABASE IF NOT EXISTS carDealershipDatabase;

USE carDealershipDatabase;

# ---------------------------------------------------------------------- #
# Tables                                                                 #
# ---------------------------------------------------------------------- #
# ---------------------------------------------------------------------- #
# Add table "Dealerships"                                                #
# ---------------------------------------------------------------------- #

CREATE TABLE `Dealerships` (
    `DealershipID` INTEGER NOT NULL AUTO_INCREMENT,
    `Name` VARCHAR(50),
    `Address` VARCHAR(50),
    `Phone` VARCHAR(14),
    CONSTRAINT `PK_Dealerships` PRIMARY KEY (`DealershipID`)
);

# ---------------------------------------------------------------------- #
# Add table "Vehicles"                                      			 #
# ---------------------------------------------------------------------- #

CREATE TABLE `Vehicles` (
    `Vin` VARCHAR(17) NOT NULL,
    `Sold` BIT NOT NULL DEFAULT 0,
	`Make` VARCHAR(30),
    `Model` VARCHAR(30),
    `Color` VARCHAR(15),
    CONSTRAINT `PK_Vehicles` PRIMARY KEY (`Vin`)
);

# ---------------------------------------------------------------------- #
# Add table "Inventory"			                                         #
# ---------------------------------------------------------------------- #

CREATE TABLE `Inventory` (
    `DealershipID` INTEGER NOT NULL,
    `Vin` VARCHAR(17) NOT NULL,
    CONSTRAINT `PK_Inventory` PRIMARY KEY (`DealershipID`, `Vin`),
    CONSTRAINT `FK_Inventory_Dealerships` FOREIGN KEY (DealershipID) REFERENCES Dealerships(DealershipID),
    CONSTRAINT `FK_Inventory_Vehicles` FOREIGN KEY (Vin) REFERENCES Vehicles(Vin)
);

# ---------------------------------------------------------------------- #
# Add table "SalesContracts"                                             #
# ---------------------------------------------------------------------- #

CREATE TABLE `SalesContracts` (
    `SalesContractID` INTEGER NOT NULL AUTO_INCREMENT,
    `Vin` VARCHAR(17) NOT NULL,
    `BuyerName` VARCHAR(50) NOT NULL,
    `SalePrice` DECIMAL(10,2) NOT NULL,
    `SaleDate` DATE NOT NULL,
    CONSTRAINT `PK_SalesContracts` PRIMARY KEY (`SalesContractID`),
    CONSTRAINT `FK_SalesContracts_Vehicles` FOREIGN KEY (Vin) REFERENCES Vehicles(Vin)
);


# ---------------------------------------------------------------------- #
# Add table "LeaseContracts"                                             #
# ---------------------------------------------------------------------- #

CREATE TABLE `LeaseContracts` (
    `LeaseContractID` INTEGER NOT NULL AUTO_INCREMENT,
    `Vin` VARCHAR(17) NOT NULL,
    `LeaseName` VARCHAR(50) NOT NULL,
    `LeaseStart` DATE NOT NULL,
    `LeaseEnd` DATE NOT NULL,
    `MonthlyPayment` DECIMAL(10,2) NOT NULL,
    CONSTRAINT `PK_LeaseContracts` PRIMARY KEY (`LeaseContractID`),
    CONSTRAINT `FK_LeaseContracts_Vehicles` FOREIGN KEY (Vin) REFERENCES Vehicles(Vin)
);


# ---------------------------------------------------------------------- #
# Add info into "Dealerships"                                             # --20
# ---------------------------------------------------------------------- #
INSERT INTO Dealerships(DealershipID, Name, Address, Phone) VALUES(1, 'Northwest Auto Mall', '305 - 14th Ave. S. Suite 3B, Seattle WA 98128', '(206) 555-4112');
INSERT INTO Dealerships(DealershipID, Name, Address, Phone) VALUES(2, 'Brooklyn Car Hub', '123 Maple Street, Brooklyn NY 11220', '(206) 555-4112');
INSERT INTO Dealerships(DealershipID, Name, Address, Phone) VALUES(3, 'Elite Auto Group', '900 W Madison St, Chicago IL 60607', '(312) 555-2299');
INSERT INTO Dealerships(DealershipID, Name, Address, Phone) VALUES(4, 'Sunrise Motors', '4800 Sunrise Blvd, Sacramento CA 95827', '(916) 555-1020');
INSERT INTO Dealerships(DealershipID, Name, Address, Phone) VALUES(5, 'Greenwood Cars', '1221 E Pike St, Seattle WA 98122', '(206) 555-8800');
INSERT INTO Dealerships(DealershipID, Name, Address, Phone) VALUES(6, 'Auto Empire', '78 Main Street, Buffalo NY 14202', '(716) 555-6677');
INSERT INTO Dealerships(DealershipID, Name, Address, Phone) VALUES(7, 'Premier Vehicles', '501 Oak Street, Austin TX 78701', '(512) 555-3090');
INSERT INTO Dealerships(DealershipID, Name, Address, Phone) VALUES(8, 'Valley Auto Sales', '1500 N 7th Ave, Phoenix AZ 85007', '(602) 555-4455');
INSERT INTO Dealerships(DealershipID, Name, Address, Phone) VALUES(9, 'Citywide Motors', '2100 Broadway, New York NY 10023', '(212) 555-3210');
INSERT INTO Dealerships(DealershipID, Name, Address, Phone) VALUES(10, 'Mountain View Autos', '3700 Foothill Blvd, Denver CO 80212', '(303) 555-7850');
INSERT INTO Dealerships(DealershipID, Name, Address, Phone) VALUES(11, 'Lakeside Auto Plaza', '555 Lakeview Dr, Orlando FL 32801', '(407) 555-1122');
INSERT INTO Dealerships(DealershipID, Name, Address, Phone) VALUES(12, 'Metroline Motors', '2020 Vine St, Los Angeles CA 90028', '(213) 555-3344');
INSERT INTO Dealerships(DealershipID, Name, Address, Phone) VALUES(13, 'Highland Car Outlet', '3400 Highland Ave, Cincinnati OH 45213', '(513) 555-7766');
INSERT INTO Dealerships(DealershipID, Name, Address, Phone) VALUES(14, 'Capital City Cars', '101 Capitol St, Richmond VA 23219', '(804) 555-4433');
INSERT INTO Dealerships(DealershipID, Name, Address, Phone) VALUES(15, 'South Bay Auto', '987 Harbor Blvd, San Jose CA 95134', '(408) 555-2323');
INSERT INTO Dealerships(DealershipID, Name, Address, Phone) VALUES(16, 'Desert Drive Motors', '7645 Sahara Ave, Las Vegas NV 89117', '(702) 555-9911');
INSERT INTO Dealerships(DealershipID, Name, Address, Phone) VALUES(17, 'Heartland Vehicles', '1600 Oak Hill Rd, Des Moines IA 50320', '(515) 555-6677');
INSERT INTO Dealerships(DealershipID, Name, Address, Phone) VALUES(18, 'Riverfront Auto Mall', '45 River Rd, St. Louis MO 63101', '(314) 555-8899');
INSERT INTO Dealerships(DealershipID, Name, Address, Phone) VALUES(19, 'Pine Ridge Autos', '88 Forest Ln, Boise ID 83702', '(208) 555-5566');
INSERT INTO Dealerships(DealershipID, Name, Address, Phone) VALUES(20, 'Coastal Cars & Trucks', '333 Ocean View Blvd, Charleston SC 29401', '(843) 555-7733');

# ---------------------------------------------------------------------- #
# Add info into "Vehicles"                                               # --100
# ---------------------------------------------------------------------- #
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1HGCM82633A123456', 0 , 'Honda', 'Accord', 'Blue');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1HGCM82633A654321', 1 , 'Ford', 'F-150', 'Black');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('3FAHP0HA9AR123456', 1, 'Nissan', 'Altima', 'Gray');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1NXBR32E54Z123456', 0, 'Hyundai', 'Elantra', 'White');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1C4RJFBG0FC123456', 0, 'Jeep', 'Grand Cherokee', 'Silver');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1HGFA16526L123456', 1, 'Honda', 'Civic', 'Red');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('2T1BURHE5FC123456', 0, 'Toyota', 'Corolla', 'Blue');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1FADP3F22JL123456', 0, 'Ford', 'Focus', 'White');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('JHMFA16586S123456', 1, 'Honda', 'Fit', 'Green');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('3CZRE4H59BG123456', 0, 'Honda', 'CR-V', 'Black');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('2HNYD28317H123456', 0, 'Acura', 'MDX', 'White');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('5NPE24AF7FH123456', 1, 'Hyundai', 'Sonata', 'Silver');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1FMCU0J9XDU123456', 0, 'Ford', 'Escape', 'Blue');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('JTDKN3DU0A1234567', 1, 'Toyota', 'Prius', 'Green');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1G1ZE5ST8HF123456', 0, 'Chevrolet', 'Malibu', 'Red');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('3N1AB7AP2HY123456', 1, 'Nissan', 'Sentra', 'Black');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('KM8JU3AC4DU123456', 0, 'Hyundai', 'Tucson', 'Gray');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1C4RJFAG3FC123456', 0, 'Jeep', 'Wrangler', 'Yellow');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('19UUA8F57FA123456', 1, 'Acura', 'ILX', 'Blue');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('2T3ZF4DV5BW123456', 0, 'Toyota', 'RAV4', 'White');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1FTFW1ET9EFA12345', 1, 'Ford', 'F-250', 'Black');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('5J6RM4H53CL123456', 0, 'Honda', 'CR-V', 'Silver');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('JN8AS5MV6FW123456', 1, 'Nissan', 'Rogue', 'Red');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1GNSKBKC0FR123456', 0, 'Chevrolet', 'Tahoe', 'Green');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('3FA6P0H73ER123456', 0, 'Ford', 'Fusion', 'Blue');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('KMHD35LE9FU123456', 1, 'Hyundai', 'Elantra', 'White');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('WAUAFAFL0AN123456', 0, 'Audi', 'A4', 'Black');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1C6RR7LT6FS123456', 1, 'Ram', '1500', 'Gray');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('19XFC2F59GE123456', 0, 'Honda', 'Civic', 'Silver');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('5UXWX9C53E0L12345', 1, 'BMW', 'X3', 'Blue');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1FTFW1EG5JFA12345', 0, 'Ford', 'F-150', 'Red');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('2C4RC1BG3ER123456', 1, 'Chrysler', 'Pacifica', 'White');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1G1ZE5ST5GF123456', 0, 'Chevrolet', 'Cruze', 'Blue');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('3FA6P0H74HR123456', 1, 'Ford', 'Fusion', 'Gray');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('5J6RM4H53CL123406', 0, 'Honda', 'CR-V', 'Black');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('JHMZF1D6XHS123456', 1, 'Honda', 'Insight', 'Silver');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1C6RR7KT7FS123456', 0, 'Ram', '1500', 'Green');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('WA1D4AFP3CA123456', 1, 'Audi', 'Q5', 'White');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('JN8AZ2KR6FT123456', 0, 'Nissan', 'Rogue', 'Red');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('3C6UR5FL1FG123456', 1, 'Ram', '2500', 'Blue');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('19UUA8F56GA123456', 0, 'Acura', 'ILX', 'Black');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1FM5K7D8XJGA12345', 1, 'Ford', 'Explorer', 'Silver');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('5N1AT2MV2JC123456', 0, 'Nissan', 'Rogue', 'White');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1GNSKCKC6FR123456', 1, 'Chevrolet', 'Suburban', 'Green');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1C4PJMCBXFW123456', 0, 'Jeep', 'Cherokee', 'Gray');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1FTFW1E53JFA12345', 1, 'Ford', 'F-150', 'Blue');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('KM8JM12D26U123456', 0, 'Hyundai', 'Tucson', 'Red');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('WDDHF8JB9EB123456', 1, 'Mercedes-Benz', 'C-Class', 'Black');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('3CZRM3H5XEG123456', 0, 'Honda', 'CR-V', 'Silver');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1G6KD57Y37U123456', 1, 'Cadillac', 'CTS', 'White');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('5YJ3E1EA7HF123456', 0, 'Tesla', 'Model 3', 'Red');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1G1BE5SM0J7123456', 1, 'Chevrolet', 'Cruze', 'Blue');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('2HGFC2F69KH123456', 0, 'Honda', 'Civic', 'White');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('5NMS3CAD9KH123456', 1, 'Hyundai', 'Santa Fe', 'Gray');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('19XFC2F59GE123406', 0, 'Honda', 'Civic', 'Black');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1C6RR7KT8FS123456', 1, 'Ram', '1500', 'Blue');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('WA1LMAFP5CA123456', 0, 'Audi', 'Q7', 'Silver');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1FTEX1CP4JK123456', 1, 'Ford', 'F-150', 'White');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('5UXWX9C53E0L12346', 0, 'BMW', 'X3', 'Black');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('3FA6P0HD1KR123456', 1, 'Ford', 'Fusion', 'Red');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1C4RJFAG8FC123457', 0, 'Jeep', 'Grand Cherokee', 'Blue');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('2FMPK4J88JBC12345', 1, 'Ford', 'Edge', 'White');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('3N1AB7AP1KY123456', 0, 'Nissan', 'Sentra', 'Gray');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('5J6RM4H57CL123457', 1, 'Honda', 'CR-V', 'Red');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1GYS4CKJ8FR123457', 0, 'Cadillac', 'Escalade', 'Black');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('WAUBFAFL5AN123456', 1, 'Audi', 'A4', 'Silver');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('JTDKN3DU7F0123456', 0, 'Toyota', 'Prius', 'White');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1FTEW1E53JFC12345', 1, 'Ford', 'F-150', 'Green');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('KM8J3CA49JU123456', 0, 'Hyundai', 'Tucson', 'Blue');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('3FA6P0LU6HR123456', 1, 'Ford', 'Fusion', 'Gray');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('5XYZTDLB1HG123456', 0, 'Hyundai', 'Santa Fe', 'Black');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1G1BE5SM9J7123457', 1, 'Chevrolet', 'Cruze', 'Red');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('2HGFB2F54FH123457', 0, 'Honda', 'Civic', 'White');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1N4AL3AP3JC123456', 1, 'Nissan', 'Altima', 'Silver');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('3C6UR5GL0FG123457', 0, 'Ram', '2500', 'Blue');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('WBA8E9C55GK123456', 1, 'BMW', '328i', 'Black');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1C4PJMCBXFW123457', 0, 'Jeep', 'Cherokee', 'Green');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('5YJSA1E23HF123456', 1, 'Tesla', 'Model S', 'White');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1GNSKCKC8FR123457', 0, 'Chevrolet', 'Suburban', 'Gray');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('JN8AZ2KR1FT123456', 1, 'Nissan', 'Rogue', 'Red');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1FTEX1EP1JK123457', 0, 'Ford', 'F-150', 'Blue');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('5UXWX9C58E0L12347', 1, 'BMW', 'X3', 'Silver');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('3FA6P0H72KR123457', 0, 'Ford', 'Fusion', 'White');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('19UUA8F51GA123457', 1, 'Acura', 'ILX', 'Black');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1FM5K7D85JGA12345', 0, 'Ford', 'Explorer', 'Red');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('5N1AT2MV3JC123457', 1, 'Nissan', 'Rogue', 'Blue');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('2HGFC2F59KH123457', 0, 'Honda', 'Civic', 'Silver');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1G6KD57Y38U123457', 1, 'Cadillac', 'CTS', 'White');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('3CZRE4H58BG123457', 0, 'Honda', 'CR-V', 'Green');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('WDDHF8JB0EB123457', 1, 'Mercedes-Benz', 'C-Class', 'Black');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1C6RR7KT9FS123457', 0, 'Ram', '1500', 'Red');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('WA1LMAFP6CA123457', 1, 'Audi', 'Q7', 'Blue');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1FTFW1EG6JFA12345', 0, 'Ford', 'F-150', 'White');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('5J8TB4H55JL123456', 1, 'Acura', 'RDX', 'Silver');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('3N6CM0KN1KK123457', 0, 'Nissan', 'Frontier', 'Gray');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1G1BE5SM0J7123458', 1, 'Chevrolet', 'Cruze', 'Blue');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('2FMPK4J81JBC12346', 0, 'Ford', 'Edge', 'White');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('JN8AZ2KR3FT123457', 1, 'Nissan', 'Rogue', 'Black');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('KM8J3CA51JU123457', 0, 'Hyundai', 'Tucson', 'Red');
INSERT INTO Vehicles(Vin, Sold, Make, Model, Color) VALUES('1GNSKCKD7FR123458', 1, 'Chevrolet', 'Suburban', 'Green');

# ---------------------------------------------------------------------- #
# Add info into "Inventory"                                              # --100
# ---------------------------------------------------------------------- #
INSERT INTO Inventory (DealershipID, Vin) VALUES (1, '1HGCM82633A123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (2, '1HGCM82633A654321');
INSERT INTO Inventory (DealershipID, Vin) VALUES (3, '3FAHP0HA9AR123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (4, '1NXBR32E54Z123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (5, '1C4RJFBG0FC123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (6, '1HGFA16526L123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (7, '2T1BURHE5FC123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (8, '1FADP3F22JL123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (9, 'JHMFA16586S123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (10, '3CZRE4H59BG123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (1, '2HNYD28317H123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (2, '5NPE24AF7FH123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (3, '1FMCU0J9XDU123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (4, 'JTDKN3DU0A1234567');
INSERT INTO Inventory (DealershipID, Vin) VALUES (5, '1G1ZE5ST8HF123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (6, '3N1AB7AP2HY123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (7, 'KM8JU3AC4DU123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (8, '1C4RJFAG3FC123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (9, '19UUA8F57FA123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (10, '2T3ZF4DV5BW123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (1, '1FTFW1ET9EFA12345');
INSERT INTO Inventory (DealershipID, Vin) VALUES (2, '5J6RM4H53CL123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (3, 'JN8AS5MV6FW123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (4, '1GNSKBKC0FR123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (5, '3FA6P0H73ER123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (6, 'KMHD35LE9FU123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (7, 'WAUAFAFL0AN123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (8, '1C6RR7LT6FS123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (9, '19XFC2F59GE123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (10, '5UXWX9C53E0L12345');
INSERT INTO Inventory (DealershipID, Vin) VALUES (1, '1FTFW1EG5JFA12345');
INSERT INTO Inventory (DealershipID, Vin) VALUES (2, '2C4RC1BG3ER123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (3, '1G1ZE5ST5GF123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (4, '3FA6P0H74HR123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (5, '5J6RM4H53CL123406');
INSERT INTO Inventory (DealershipID, Vin) VALUES (6, 'JHMZF1D6XHS123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (7, '1C6RR7KT7FS123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (8, 'WA1D4AFP3CA123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (9, 'JN8AZ2KR6FT123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (10, '3C6UR5FL1FG123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (1, '19UUA8F56GA123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (2, '1FM5K7D8XJGA12345');
INSERT INTO Inventory (DealershipID, Vin) VALUES (3, '5N1AT2MV2JC123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (4, '1GNSKCKC6FR123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (5, '1C4PJMCBXFW123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (6, '1FTFW1E53JFA12345');
INSERT INTO Inventory (DealershipID, Vin) VALUES (7, 'KM8JM12D26U123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (8, 'WDDHF8JB9EB123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (9, '3CZRM3H5XEG123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (10, '1G6KD57Y37U123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (1, '5YJ3E1EA7HF123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (2, '1G1BE5SM0J7123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (3, '2HGFC2F69KH123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (4, '5NMS3CAD9KH123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (5, '19XFC2F59GE123406');
INSERT INTO Inventory (DealershipID, Vin) VALUES (6, '1C6RR7KT8FS123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (7, 'WA1LMAFP5CA123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (8, '1FTEX1CP4JK123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (9, '5UXWX9C53E0L12346');
INSERT INTO Inventory (DealershipID, Vin) VALUES (10, '3FA6P0HD1KR123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (1, '1C4RJFAG8FC123457');
INSERT INTO Inventory (DealershipID, Vin) VALUES (2, '2FMPK4J88JBC12345');
INSERT INTO Inventory (DealershipID, Vin) VALUES (3, '3N1AB7AP1KY123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (4, '5J6RM4H57CL123457');
INSERT INTO Inventory (DealershipID, Vin) VALUES (5, '1GYS4CKJ8FR123457');
INSERT INTO Inventory (DealershipID, Vin) VALUES (6, 'WAUBFAFL5AN123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (7, 'JTDKN3DU7F0123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (8, '1FTEW1E53JFC12345');
INSERT INTO Inventory (DealershipID, Vin) VALUES (9, 'KM8J3CA49JU123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (10, '3FA6P0LU6HR123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (1, '5XYZTDLB1HG123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (2, '1G1BE5SM9J7123457');
INSERT INTO Inventory (DealershipID, Vin) VALUES (3, '2HGFB2F54FH123457');
INSERT INTO Inventory (DealershipID, Vin) VALUES (4, '1N4AL3AP3JC123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (5, '3C6UR5GL0FG123457');
INSERT INTO Inventory (DealershipID, Vin) VALUES (6, 'WBA8E9C55GK123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (7, '1C4PJMCBXFW123457');
INSERT INTO Inventory (DealershipID, Vin) VALUES (8, '5YJSA1E23HF123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (9, '1GNSKCKC8FR123457');
INSERT INTO Inventory (DealershipID, Vin) VALUES (10, 'JN8AZ2KR1FT123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (1, '1FTEX1EP1JK123457');
INSERT INTO Inventory (DealershipID, Vin) VALUES (2, '5UXWX9C58E0L12347');
INSERT INTO Inventory (DealershipID, Vin) VALUES (3, '3FA6P0H72KR123457');
INSERT INTO Inventory (DealershipID, Vin) VALUES (4, '19UUA8F51GA123457');
INSERT INTO Inventory (DealershipID, Vin) VALUES (5, '1FM5K7D85JGA12345');
INSERT INTO Inventory (DealershipID, Vin) VALUES (6, '5N1AT2MV3JC123457');
INSERT INTO Inventory (DealershipID, Vin) VALUES (7, '2HGFC2F59KH123457');
INSERT INTO Inventory (DealershipID, Vin) VALUES (8, '1G6KD57Y38U123457');
INSERT INTO Inventory (DealershipID, Vin) VALUES (9, '3CZRE4H58BG123457');
INSERT INTO Inventory (DealershipID, Vin) VALUES (10, 'WDDHF8JB0EB123457');
INSERT INTO Inventory (DealershipID, Vin) VALUES (1, '1C6RR7KT9FS123457');
INSERT INTO Inventory (DealershipID, Vin) VALUES (2, 'WA1LMAFP6CA123457');
INSERT INTO Inventory (DealershipID, Vin) VALUES (3, '1FTFW1EG6JFA12345');
INSERT INTO Inventory (DealershipID, Vin) VALUES (4, '5J8TB4H55JL123456');
INSERT INTO Inventory (DealershipID, Vin) VALUES (5, '3N6CM0KN1KK123457');
INSERT INTO Inventory (DealershipID, Vin) VALUES (6, '1G1BE5SM0J7123458');
INSERT INTO Inventory (DealershipID, Vin) VALUES (7, '2FMPK4J81JBC12346');
INSERT INTO Inventory (DealershipID, Vin) VALUES (8, 'JN8AZ2KR3FT123457');
INSERT INTO Inventory (DealershipID, Vin) VALUES (9, 'KM8J3CA51JU123457');
INSERT INTO Inventory (DealershipID, Vin) VALUES (10, '1GNSKCKD7FR123458');

# ---------------------------------------------------------------------- #
# Add info into "SalesContracts"               		                     # --20
# ---------------------------------------------------------------------- #
INSERT INTO SalesContracts (SalesContractID, Vin, BuyerName, SalePrice, SaleDate) VALUES (1, '1HGCM82633A654321', 'Alice Martin', 23500.00, '2024-01-15');
INSERT INTO SalesContracts (SalesContractID, Vin, BuyerName, SalePrice, SaleDate) VALUES (2, '3FAHP0HA9AR123456', 'Carlos Rivera', 19999.99, '2024-02-10');
INSERT INTO SalesContracts (SalesContractID, Vin, BuyerName, SalePrice, SaleDate) VALUES (3, '1HGFA16526L123456', 'Dana Li', 17800.50, '2024-03-05');
INSERT INTO SalesContracts (SalesContractID, Vin, BuyerName, SalePrice, SaleDate) VALUES (4, 'JHMFA16586S123456', 'Evan Jacobs', 16350.00, '2024-04-20');
INSERT INTO SalesContracts (SalesContractID, Vin, BuyerName, SalePrice, SaleDate) VALUES (5, '1C4RJFBG0FC123456', 'Fatima El-Sayed', 26875.75, '2024-05-30');
INSERT INTO SalesContracts (SalesContractID, Vin, BuyerName, SalePrice, SaleDate) VALUES (6, '2FMPK4J88JBC12345', 'Grace Lee', 21999.99, '2024-06-10');
INSERT INTO SalesContracts (SalesContractID, Vin, BuyerName, SalePrice, SaleDate) VALUES (7, '1N4AL3AP3JC123456', 'Henry Davis', 22500.00, '2024-06-15');
INSERT INTO SalesContracts (SalesContractID, Vin, BuyerName, SalePrice, SaleDate) VALUES (8, '5YJSA1E23HF123456', 'Isabel Gomez', 31000.00, '2024-07-05');
INSERT INTO SalesContracts (SalesContractID, Vin, BuyerName, SalePrice, SaleDate) VALUES (9, 'KM8J3CA49JU123456', 'Jack Wilson', 18999.50, '2024-07-20');
INSERT INTO SalesContracts (SalesContractID, Vin, BuyerName, SalePrice, SaleDate) VALUES (10, '3N1AB7AP1KY123456', 'Kelly Brown', 17999.75, '2024-08-01');
INSERT INTO SalesContracts (SalesContractID, Vin, BuyerName, SalePrice, SaleDate) VALUES (11, '1FTEW1E53JFC12345', 'Liam Smith', 25400.00, '2024-08-12');
INSERT INTO SalesContracts (SalesContractID, Vin, BuyerName, SalePrice, SaleDate) VALUES (12, '5XYZTDLB1HG123456', 'Mia Johnson', 29850.00, '2024-08-25');
INSERT INTO SalesContracts (SalesContractID, Vin, BuyerName, SalePrice, SaleDate) VALUES (13, '1G1BE5SM9J7123457', 'Noah Thompson', 21500.00, '2024-09-05');
INSERT INTO SalesContracts (SalesContractID, Vin, BuyerName, SalePrice, SaleDate) VALUES (14, 'JN8AZ2KR1FT123456', 'Olivia Martinez', 19999.99, '2024-09-15');
INSERT INTO SalesContracts (SalesContractID, Vin, BuyerName, SalePrice, SaleDate) VALUES (15, '1GNSKCKC8FR123457', 'Paul Harris', 23000.00, '2024-10-01');
INSERT INTO SalesContracts (SalesContractID, Vin, BuyerName, SalePrice, SaleDate) VALUES (16, 'WAUBFAFL5AN123456', 'Quinn Walker', 27999.99, '2024-10-12');
INSERT INTO SalesContracts (SalesContractID, Vin, BuyerName, SalePrice, SaleDate) VALUES (17, '2HGFB2F54FH123457', 'Rachel Adams', 18500.00, '2024-10-20');
INSERT INTO SalesContracts (SalesContractID, Vin, BuyerName, SalePrice, SaleDate) VALUES (18, '3C6UR5GL0FG123457', 'Samuel King', 24999.00, '2024-11-05');
INSERT INTO SalesContracts (SalesContractID, Vin, BuyerName, SalePrice, SaleDate) VALUES (19, '1C4PJMCBXFW123457', 'Tina Evans', 27250.00, '2024-11-18');
INSERT INTO SalesContracts (SalesContractID, Vin, BuyerName, SalePrice, SaleDate) VALUES (20, 'JTDKN3DU7F0123456', 'Victor Green', 19450.00, '2024-12-01');

# ---------------------------------------------------------------------- #
# Add info into "LeaseContracts"                                         # --20
# ---------------------------------------------------------------------- #
INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, LeaseStart, LeaseEnd, MonthlyPayment) VALUES(1, '2T1BURHE5FC123456', 'Liam Carter', '2024-01-15', '2027-01-15', 325.00);
INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, LeaseStart, LeaseEnd, MonthlyPayment) VALUES (2, '1FADP3F22JL123456', 'Sophia Nguyen', '2023-06-01', '2026-06-01', 310.75);
INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, LeaseStart, LeaseEnd, MonthlyPayment) VALUES (3, '1NXBR32E54Z123456', 'Jackson Lee', '2025-03-10', '2028-03-10', 289.99);
INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, LeaseStart, LeaseEnd, MonthlyPayment) VALUES (4, '3CZRE4H59BG123456', 'Olivia Martinez', '2024-11-01', '2027-11-01', 340.50);
INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, LeaseStart, LeaseEnd, MonthlyPayment) VALUES (5, '1HGCM82633A123456', 'Noah Kim', '2022-09-01', '2025-09-01', 295.00);
INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, LeaseStart, LeaseEnd, MonthlyPayment) VALUES (6, '5YJSA1E23HF123456', 'Emma Johnson', '2024-02-15', '2027-02-15', 400.00);
INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, LeaseStart, LeaseEnd, MonthlyPayment) VALUES (7, '1G1BE5SM9J7123457', 'Mason Brown', '2023-10-01', '2026-10-01', 320.75);
INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, LeaseStart, LeaseEnd, MonthlyPayment) VALUES (8, '3N1AB7AP1KY123456', 'Ava Davis', '2024-05-10', '2027-05-10', 310.50);
INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, LeaseStart, LeaseEnd, MonthlyPayment) VALUES (9, 'KM8J3CA49JU123456', 'Lucas Miller', '2023-08-20', '2026-08-20', 305.00);
INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, LeaseStart, LeaseEnd, MonthlyPayment) VALUES (10, '1FTEW1E53JFC12345', 'Isabella Wilson', '2024-04-01', '2027-04-01', 335.25);
INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, LeaseStart, LeaseEnd, MonthlyPayment) VALUES (11, 'JN8AZ2KR1FT123456', 'Ethan Moore', '2023-11-15', '2026-11-15', 290.00);
INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, LeaseStart, LeaseEnd, MonthlyPayment) VALUES (12, '1GNSKCKC8FR123457', 'Mia Taylor', '2024-07-10', '2027-07-10', 310.00);
INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, LeaseStart, LeaseEnd, MonthlyPayment) VALUES (13, 'WAUBFAFL5AN123456', 'James Anderson', '2023-12-01', '2026-12-01', 325.50);
INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, LeaseStart, LeaseEnd, MonthlyPayment) VALUES (14, '2HGFB2F54FH123457', 'Charlotte Thomas', '2024-06-15', '2027-06-15', 315.00);
INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, LeaseStart, LeaseEnd, MonthlyPayment) VALUES (15, '3C6UR5GL0FG123457', 'Benjamin Jackson', '2023-09-10', '2026-09-10', 340.00);
INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, LeaseStart, LeaseEnd, MonthlyPayment) VALUES (16, '1C4PJMCBXFW123457', 'Ella White', '2024-03-05', '2027-03-05', 300.00);
INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, LeaseStart, LeaseEnd, MonthlyPayment) VALUES (17, 'JTDKN3DU7F0123456', 'Alexander Harris', '2023-07-01', '2026-07-01', 320.00);
INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, LeaseStart, LeaseEnd, MonthlyPayment) VALUES (18, '5XYZTDLB1HG123456', 'Sofia Martin', '2024-01-20', '2027-01-20', 345.00);
INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, LeaseStart, LeaseEnd, MonthlyPayment) VALUES (19, '1N4AL3AP3JC123456', 'William Garcia', '2023-10-30', '2026-10-30', 310.00);
INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, LeaseStart, LeaseEnd, MonthlyPayment) VALUES (20, '2FMPK4J88JBC12345', 'Amelia Martinez', '2024-08-15', '2027-08-15', 330.00);