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
    `Year` INT NOT NULL,
	`Make` VARCHAR(30),
    `Model` VARCHAR(30),
    `VehicleType` VARCHAR(30),
    `Color` VARCHAR(15),
    `Odometer` INT,
    `Price` DOUBLE,
	`Sold` BIT NOT NULL DEFAULT 0,
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
    `CustomerEmail` VARCHAR(100) NOT NULL,
    `SalePrice` DECIMAL(10,2) NOT NULL,
    `SaleDate` DATE NOT NULL,
    `RecordingFee` DECIMAL(10,2) NOT NULL,
    `ProcessingFee` DECIMAL(10,2) NOT NULL,
    `IsFinanced` BOOLEAN NOT NULL,
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
    `CustomerEmail` VARCHAR(100) NOT NULL,
    `LeaseStart` DATE NOT NULL,
    `LeaseEnd` DATE NOT NULL,
	`ExpectedEndingValue` DECIMAL(10,2) NOT NULL,
	`LeaseFee` DECIMAL(10,2) NOT NULL,
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
INSERT INTO Vehicles (Vin, Year, Make, Model, VehicleType, Color, Odometer, Price, Sold) VALUES
('1HGCM82633A123456', 2003, 'Honda', 'Accord', 'Sedan', 'Blue', 120000, 6500.00, 0),
('1HGCM82633A654321', 2015, 'Ford', 'F-150', 'Truck', 'Black', 90000, 15500.00, 1),
('3FAHP0HA9AR123456', 2010, 'Nissan', 'Altima', 'Sedan', 'Gray', 110000, 7200.00, 1),
('1NXBR32E54Z123456', 2012, 'Hyundai', 'Elantra', 'Sedan', 'White', 95000, 6300.00, 0),
('1C4RJFBG0FC123456', 2016, 'Jeep', 'Grand Cherokee', 'SUV', 'Silver', 85000, 17500.00, 0),
('1HGFA16526L123456', 2006, 'Honda', 'Civic', 'Sedan', 'Red', 105000, 5900.00, 1),
('2T1BURHE5FC123456', 2015, 'Toyota', 'Corolla', 'Sedan', 'Blue', 88000, 7400.00, 0),
('1FADP3F22JL123456', 2018, 'Ford', 'Focus', 'Hatchback', 'White', 67000, 8700.00, 0),
('JHMFA16586S123456', 2008, 'Honda', 'Fit', 'Hatchback', 'Green', 99000, 5100.00, 1),
('3CZRE4H59BG123456', 2011, 'Honda', 'CR-V', 'SUV', 'Black', 92000, 9800.00, 0),
('2HNYD28317H123456', 2007, 'Acura', 'MDX', 'SUV', 'White', 102000, 8900.00, 0),
('5NPE24AF7FH123456', 2015, 'Hyundai', 'Sonata', 'Sedan', 'Silver', 91000, 8200.00, 1),
('1FMCU0J9XDU123456', 2013, 'Ford', 'Escape', 'SUV', 'Blue', 88000, 9900.00, 0),
('JTDKN3DU0A1234567', 2010, 'Toyota', 'Prius', 'Hatchback', 'Green', 100000, 7800.00, 1),
('1G1ZE5ST8HF123456', 2017, 'Chevrolet', 'Malibu', 'Sedan', 'Red', 77000, 9400.00, 0),
('3N1AB7AP2HY123456', 2017, 'Nissan', 'Sentra', 'Sedan', 'Black', 69000, 8900.00, 1),
('KM8JU3AC4DU123456', 2013, 'Hyundai', 'Tucson', 'SUV', 'Gray', 93000, 9500.00, 0),
('1C4RJFAG3FC123456', 2016, 'Jeep', 'Wrangler', 'SUV', 'Yellow', 76000, 18500.00, 0),
('19UUA8F57FA123456', 2015, 'Acura', 'ILX', 'Sedan', 'Blue', 72000, 10200.00, 1),

('2T3ZF4DV5BW123456', 2011, 'Toyota', 'RAV4', 'SUV', 'White', 98000, 10200.00, 0),
('1FTFW1ET9EFA12345', 2014, 'Ford', 'F-250', 'Truck', 'Black', 89000, 21500.00, 1),
('5J6RM4H53CL123456', 2012, 'Honda', 'CR-V', 'SUV', 'Silver', 96000, 10800.00, 0),
('JN8AS5MV6FW123456', 2015, 'Nissan', 'Rogue', 'SUV', 'Red', 87000, 11200.00, 1),
('1GNSKBKC0FR123456', 2015, 'Chevrolet', 'Tahoe', 'SUV', 'Green', 95000, 22500.00, 0),
('3FA6P0H73ER123456', 2014, 'Ford', 'Fusion', 'Sedan', 'Blue', 91000, 9200.00, 0),
('KMHD35LE9FU123456', 2015, 'Hyundai', 'Elantra', 'Sedan', 'White', 88000, 7500.00, 1),
('WAUAFAFL0AN123456', 2010, 'Audi', 'A4', 'Sedan', 'Black', 101000, 9600.00, 0),
('1C6RR7LT6FS123456', 2015, 'Ram', '1500', 'Truck', 'Gray', 93000, 19800.00, 1),
('19XFC2F59GE123456', 2016, 'Honda', 'Civic', 'Sedan', 'Silver', 87000, 8700.00, 0),
('5UXWX9C53E0L12345', 2014, 'BMW', 'X3', 'SUV', 'Blue', 76000, 18500.00, 1),
('1FTFW1EG5JFA12345', 2018, 'Ford', 'F-150', 'Truck', 'Red', 72000, 26500.00, 0),
('2C4RC1BG3ER123456', 2014, 'Chrysler', 'Pacifica', 'Van', 'White', 97000, 11500.00, 1),
('1G1ZE5ST5GF123456', 2016, 'Chevrolet', 'Cruze', 'Sedan', 'Blue', 91000, 8100.00, 0),
('3FA6P0H74HR123456', 2017, 'Ford', 'Fusion', 'Sedan', 'Gray', 89000, 9400.00, 1),
('5J6RM4H53CL123406', 2012, 'Honda', 'CR-V', 'SUV', 'Black', 94000, 10700.00, 0),
('JHMZF1D6XHS123456', 2017, 'Honda', 'Insight', 'Hatchback', 'Silver', 67000, 9900.00, 1),
('1C6RR7KT7FS123456', 2015, 'Ram', '1500', 'Truck', 'Green', 91000, 19200.00, 0),

('WA1D4AFP3CA123456', 2012, 'Audi', 'Q5', 'SUV', 'White', 95000, 13800.00, 1),
('JN8AZ2KR6FT123456', 2015, 'Nissan', 'Rogue', 'SUV', 'Red', 88000, 11400.00, 0),
('3C6UR5FL1FG123456', 2015, 'Ram', '2500', 'Truck', 'Blue', 92000, 24800.00, 1),
('19UUA8F56GA123456', 2016, 'Acura', 'ILX', 'Sedan', 'Black', 74000, 10200.00, 0),
('1FM5K7D8XJGA12345', 2018, 'Ford', 'Explorer', 'SUV', 'Silver', 69000, 18700.00, 1),
('5N1AT2MV2JC123456', 2018, 'Nissan', 'Rogue', 'SUV', 'White', 64000, 14500.00, 0),
('1GNSKCKC6FR123456', 2015, 'Chevrolet', 'Suburban', 'SUV', 'Green', 93000, 21900.00, 1),
('1C4PJMCBXFW123456', 2015, 'Jeep', 'Cherokee', 'SUV', 'Gray', 91000, 12800.00, 0),
('1FTFW1E53JFA12345', 2018, 'Ford', 'F-150', 'Truck', 'Blue', 75000, 26700.00, 1),
('KM8JM12D26U123456', 2006, 'Hyundai', 'Tucson', 'SUV', 'Red', 102000, 5600.00, 0),
('WDDHF8JB9EB123456', 2014, 'Mercedes-Benz', 'C-Class', 'Sedan', 'Black', 82000, 16200.00, 1),
('3CZRM3H5XEG123456', 2014, 'Honda', 'CR-V', 'SUV', 'Silver', 88000, 10900.00, 0),
('1G6KD57Y37U123456', 2007, 'Cadillac', 'CTS', 'Sedan', 'White', 105000, 7500.00, 1),
('5YJ3E1EA7HF123456', 2017, 'Tesla', 'Model 3', 'Sedan', 'Red', 58000, 29900.00, 0),
('1G1BE5SM0J7123456', 2018, 'Chevrolet', 'Cruze', 'Sedan', 'Blue', 72000, 9100.00, 1),
('2HGFC2F69KH123456', 2019, 'Honda', 'Civic', 'Sedan', 'White', 53000, 12300.00, 0),
('5NMS3CAD9KH123456', 2019, 'Hyundai', 'Santa Fe', 'SUV', 'Gray', 47000, 15900.00, 1),
('19XFC2F59GE123406', 2016, 'Honda', 'Civic', 'Sedan', 'Black', 79000, 9400.00, 0),
('1C6RR7KT8FS123456', 2015, 'Ram', '1500', 'Truck', 'Blue', 85000, 19400.00, 1),
('WA1LMAFP5CA123456', 2012, 'Audi', 'Q7', 'SUV', 'Silver', 99000, 17300.00, 0),

('1FTEX1CP4JK123456', 2018, 'Ford', 'F-150', 'Truck', 'White', 65000, 24800.00, 1),
('5UXWX9C53E0L12346', 2014, 'BMW', 'X3', 'SUV', 'Black', 88000, 17200.00, 0),
('3FA6P0HD1KR123456', 2019, 'Ford', 'Fusion', 'Sedan', 'Red', 54000, 14500.00, 1),
('1C4RJFAG8FC123457', 2015, 'Jeep', 'Grand Cherokee', 'SUV', 'Blue', 78000, 19800.00, 0),
('2FMPK4J88JBC12345', 2018, 'Ford', 'Edge', 'SUV', 'White', 61000, 16900.00, 1),
('3N1AB7AP1KY123456', 2019, 'Nissan', 'Sentra', 'Sedan', 'Gray', 49000, 11200.00, 0),
('5J6RM4H57CL123457', 2012, 'Honda', 'CR-V', 'SUV', 'Red', 97000, 9800.00, 1),
('1GYS4CKJ8FR123457', 2015, 'Cadillac', 'Escalade', 'SUV', 'Black', 76000, 23900.00, 0),
('WAUBFAFL5AN123456', 2010, 'Audi', 'A4', 'Sedan', 'Silver', 101000, 8700.00, 1),
('JTDKN3DU7F0123456', 2015, 'Toyota', 'Prius', 'Hatchback', 'White', 68000, 13600.00, 0),
('1FTEW1E53JFC12345', 2018, 'Ford', 'F-150', 'Truck', 'Green', 71000, 25400.00, 1),
('KM8J3CA49JU123456', 2018, 'Hyundai', 'Tucson', 'SUV', 'Blue', 59000, 14800.00, 0),
('3FA6P0LU6HR123456', 2017, 'Ford', 'Fusion', 'Sedan', 'Gray', 63000, 13900.00, 1),
('5XYZTDLB1HG123456', 2017, 'Hyundai', 'Santa Fe', 'SUV', 'Black', 77000, 15300.00, 0),
('1G1BE5SM9J7123457', 2018, 'Chevrolet', 'Cruze', 'Sedan', 'Red', 53000, 9700.00, 1),
('2HGFB2F54FH123457', 2015, 'Honda', 'Civic', 'Sedan', 'White', 85000, 9900.00, 0),
('1N4AL3AP3JC123456', 2018, 'Nissan', 'Altima', 'Sedan', 'Silver', 57000, 12300.00, 1),
('3C6UR5GL0FG123457', 2015, 'Ram', '2500', 'Truck', 'Blue', 92000, 21700.00, 0),
('WBA8E9C55GK123456', 2016, 'BMW', '328i', 'Sedan', 'Black', 69000, 18300.00, 1),
('1C4PJMCBXFW123457', 2015, 'Jeep', 'Cherokee', 'SUV', 'Green', 83000, 12100.00, 0),

('5YJSA1E23HF123456', 2017, 'Tesla', 'Model S', 'Sedan', 'White', 42000, 39900.00, 1),
('1GNSKCKC8FR123457', 2015, 'Chevrolet', 'Suburban', 'SUV', 'Gray', 82000, 24500.00, 0),
('JN8AZ2KR1FT123456', 2015, 'Nissan', 'Rogue', 'SUV', 'Red', 67000, 14800.00, 1),
('1FTEX1EP1JK123457', 2018, 'Ford', 'F-150', 'Truck', 'Blue', 59000, 27900.00, 0),
('5UXWX9C58E0L12347', 2014, 'BMW', 'X3', 'SUV', 'Silver', 88000, 16900.00, 1),
('3FA6P0H72KR123457', 2019, 'Ford', 'Fusion', 'Sedan', 'White', 50000, 14200.00, 0),
('19UUA8F51GA123457', 2016, 'Acura', 'ILX', 'Sedan', 'Black', 61000, 13900.00, 1),
('1FM5K7D85JGA12345', 2018, 'Ford', 'Explorer', 'SUV', 'Red', 58000, 26400.00, 0),
('5N1AT2MV3JC123457', 2018, 'Nissan', 'Rogue', 'SUV', 'Blue', 53000, 15900.00, 1),
('2HGFC2F59KH123457', 2019, 'Honda', 'Civic', 'Sedan', 'Silver', 46000, 12900.00, 0),
('1G6KD57Y38U123457', 2008, 'Cadillac', 'CTS', 'Sedan', 'White', 91000, 9900.00, 1),
('3CZRE4H58BG123457', 2011, 'Honda', 'CR-V', 'SUV', 'Green', 97000, 9200.00, 0),
('WDDHF8JB0EB123457', 2014, 'Mercedes-Benz', 'C-Class', 'Sedan', 'Black', 78000, 18800.00, 1),
('1C6RR7KT9FS123457', 2015, 'Ram', '1500', 'Truck', 'Red', 73000, 20300.00, 0),
('WA1LMAFP6CA123457', 2012, 'Audi', 'Q7', 'SUV', 'Blue', 85000, 17200.00, 1),
('1FTFW1EG6JFA12345', 2018, 'Ford', 'F-150', 'Truck', 'White', 61000, 26600.00, 0),
('5J8TB4H55JL123456', 2018, 'Acura', 'RDX', 'SUV', 'Silver', 53000, 22400.00, 1),
('3N6CM0KN1KK123457', 2019, 'Nissan', 'Frontier', 'Truck', 'Gray', 49000, 21900.00, 0),
('1G1BE5SM0J7123458', 2018, 'Chevrolet', 'Cruze', 'Sedan', 'Blue', 52000, 10400.00, 1),
('2FMPK4J81JBC12346', 2018, 'Ford', 'Edge', 'SUV', 'White', 59000, 17400.00, 0),
('JN8AZ2KR3FT123457', 2015, 'Nissan', 'Rogue', 'SUV', 'Black', 74000, 13600.00, 1),
('KM8J3CA51JU123457', 2018, 'Hyundai', 'Tucson', 'SUV', 'Red', 57000, 14900.00, 0),
('1GNSKCKD7FR123458', 2015, 'Chevrolet', 'Suburban', 'SUV', 'Green', 86000, 23900.00, 1);

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
INSERT INTO SalesContracts (Vin, BuyerName, CustomerEmail, SalePrice, SaleDate, RecordingFee, ProcessingFee, IsFinanced) VALUES
('1HGCM82633A654321', 'Alice Martin', 'alice.martin@example.com', 23500.00, '2024-01-15', 100.00, 50.00, TRUE),
('3FAHP0HA9AR123456', 'Carlos Rivera', 'carlos.rivera@example.com', 19999.99, '2024-02-10', 100.00, 50.00, FALSE),
('1HGFA16526L123456', 'Dana Li', 'dana.li@example.com', 17800.50, '2024-03-05', 100.00, 50.00, TRUE),
('JHMFA16586S123456', 'Evan Jacobs', 'evan.jacobs@example.com', 16350.00, '2024-04-20', 100.00, 50.00, FALSE),
('1C4RJFBG0FC123456', 'Fatima El-Sayed', 'fatima.elsayed@example.com', 26875.75, '2024-05-30', 100.00, 50.00, TRUE),
('2FMPK4J88JBC12345', 'Grace Lee', 'grace.lee@example.com', 21999.99, '2024-06-10', 100.00, 50.00, FALSE),
('1N4AL3AP3JC123456', 'Henry Davis', 'henry.davis@example.com', 22500.00, '2024-06-15', 100.00, 50.00, TRUE),
('5YJSA1E23HF123456', 'Isabel Gomez', 'isabel.gomez@example.com', 31000.00, '2024-07-05', 100.00, 50.00, FALSE),
('KM8J3CA49JU123456', 'Jack Wilson', 'jack.wilson@example.com', 18999.50, '2024-07-20', 100.00, 50.00, TRUE),
('3N1AB7AP1KY123456', 'Kelly Brown', 'kelly.brown@example.com', 17999.75, '2024-08-01', 100.00, 50.00, FALSE),
('1FTEW1E53JFC12345', 'Liam Smith', 'liam.smith@example.com', 25400.00, '2024-08-12', 100.00, 50.00, TRUE),
('5XYZTDLB1HG123456', 'Mia Johnson', 'mia.johnson@example.com', 29850.00, '2024-08-25', 100.00, 50.00, FALSE),
('1G1BE5SM9J7123457', 'Noah Thompson', 'noah.thompson@example.com', 21500.00, '2024-09-05', 100.00, 50.00, TRUE),
('JN8AZ2KR1FT123456', 'Olivia Martinez', 'olivia.martinez@example.com', 19999.99, '2024-09-15', 100.00, 50.00, FALSE),
('1GNSKCKC8FR123457', 'Paul Harris', 'paul.harris@example.com', 23000.00, '2024-10-01', 100.00, 50.00, TRUE),
('WAUBFAFL5AN123456', 'Quinn Walker', 'quinn.walker@example.com', 27999.99, '2024-10-12', 100.00, 50.00, FALSE),
('2HGFB2F54FH123457', 'Rachel Adams', 'rachel.adams@example.com', 18500.00, '2024-10-20', 100.00, 50.00, TRUE),
('3C6UR5GL0FG123457', 'Samuel King', 'samuel.king@example.com', 24999.00, '2024-11-05', 100.00, 50.00, FALSE),
('1C4PJMCBXFW123457', 'Tina Evans', 'tina.evans@example.com', 27250.00, '2024-11-18', 100.00, 50.00, TRUE),
('JTDKN3DU7F0123456', 'Victor Green', 'victor.green@example.com', 19450.00, '2024-12-01', 100.00, 50.00, FALSE);


# ---------------------------------------------------------------------- #
# Add info into "LeaseContracts"                                         # --20
# ---------------------------------------------------------------------- #
INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, CustomerEmail, LeaseStart, LeaseEnd, ExpectedEndingValue, LeaseFee, MonthlyPayment) VALUES
(1, '2T1BURHE5FC123456', 'Liam Carter', 'liam.carter@gmail.com', '2024-01-15', '2027-01-15', 5850.00, 819.00, 325.00),
(2, '1FADP3F22JL123456', 'Sophia Nguyen', 'sophia.nguyen@yahoo.com', '2023-06-01', '2026-06-01', 5601.50, 784.21, 310.75),
(3, '1NXBR32E54Z123456', 'Jackson Lee', 'jackson.lee@outlook.com', '2025-03-10', '2028-03-10', 5219.82, 730.77, 289.99),
(4, '3CZRE4H59BG123456', 'Olivia Martinez', 'olivia.martinez@gmail.com', '2024-11-01', '2027-11-01', 6130.80, 858.31, 340.50),
(5, '1HGCM82633A123456', 'Noah Kim', 'noah.kim@hotmail.com', '2022-09-01', '2025-09-01', 5310.00, 743.40, 295.00),
(6, '5YJSA1E23HF123456', 'Emma Johnson', 'emma.j@icloud.com', '2024-02-15', '2027-02-15', 7200.00, 1008.00, 400.00),
(7, '1G1BE5SM9J7123457', 'Mason Brown', 'mason.b_92@yahoo.com', '2023-10-01', '2026-10-01', 5773.50, 808.29, 320.75),
(8, '3N1AB7AP1KY123456', 'Ava Davis', 'ava.davis@gmail.com', '2024-05-10', '2027-05-10', 5590.80, 782.71, 310.50),
(9, 'KM8J3CA49JU123456', 'Lucas Miller', 'lucas.miller123@outlook.com', '2023-08-20', '2026-08-20', 5490.00, 768.60, 305.00),
(10, '1FTEW1E53JFC12345', 'Isabella Wilson', 'isabella.wilson@gmail.com', '2024-04-01', '2027-04-01', 6034.50, 844.83, 335.25),
(11, 'JN8AZ2KR1FT123456', 'Ethan Moore', 'ethan.moore@yahoo.com', '2023-11-15', '2026-11-15', 5220.00, 730.80, 290.00),
(12, '1GNSKCKC8FR123457', 'Mia Taylor', 'mia.taylor88@gmail.com', '2024-07-10', '2027-07-10', 5580.00, 780.60, 310.00),
(13, 'WAUBFAFL5AN123456', 'James Anderson', 'j.anderson@live.com', '2023-12-01', '2026-12-01', 5869.00, 820.66, 325.50),
(14, '2HGFB2F54FH123457', 'Charlotte Thomas', 'charlotte.t@hotmail.com', '2024-06-15', '2027-06-15', 5670.00, 793.80, 315.00),
(15, '3C6UR5GL0FG123457', 'Benjamin Jackson', 'ben.jackson@gmail.com', '2023-09-10', '2026-09-10', 6120.00, 856.80, 340.00),
(16, '1C4PJMCBXFW123457', 'Ella White', 'ella.white@icloud.com', '2024-03-05', '2027-03-05', 5400.00, 756.00, 300.00),
(17, 'JTDKN3DU7F0123456', 'Alexander Harris', 'alex.harris@yahoo.com', '2023-07-01', '2026-07-01', 5760.00, 806.40, 320.00),
(18, '5XYZTDLB1HG123456', 'Sofia Martin', 'sofia.martin@gmail.com', '2024-01-20', '2027-01-20', 6210.00, 867.00, 345.00),
(19, '1N4AL3AP3JC123456', 'William Garcia', 'w.garcia@hotmail.com', '2023-10-30', '2026-10-30', 5580.00, 780.60, 310.00),
(20, '2FMPK4J88JBC12345', 'Amelia Martinez', 'amelia.mtz@gmail.com', '2024-08-15', '2027-08-15', 5940.00, 831.60, 330.00);
