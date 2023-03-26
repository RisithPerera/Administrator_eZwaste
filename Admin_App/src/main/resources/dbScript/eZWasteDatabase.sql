/* -------------------------------------------------------------------------------------------------------- */
/*                            Just copy and paste all these queries INTO MySQL CLI                          */
/* -------------------------------------------------------------------------------------------------------- */

DROP DATABASE IF EXISTS ezwaste;
CREATE DATABASE ezwaste;
USE ezwaste;

/* -------------------------------------------------------------------------------------------------------- */
/*                                               TABLE STRUCTURE                                            */
/* -------------------------------------------------------------------------------------------------------- */
CREATE TABLE User(
	userName VARCHAR(30) NOT NULL,
	userPassword    VARCHAR(30) NOT NULL,
    CONSTRAINT PRIMARY KEY (userName)
)engine=innodb;

CREATE TABLE Region(
    regionId   INT         NOT NULL,
    vertex     VARCHAR(200) NOT NULL,
    CONSTRAINT PRIMARY KEY (regionId) 
)engine=innodb;

CREATE TABLE Gateway(
    gatewayId   INT   NOT NULL,
    latitude   DOUBLE NOT NULL,
    longitude  DOUBLE NOT NULL,
    CONSTRAINT PRIMARY KEY (gatewayId) 
)engine=innodb;

CREATE TABLE Driver(
    driverId  INT         NOT NULL,
    userName  VARCHAR(30) NOT NULL,
	password  VARCHAR(30) NOT NULL,
    fName     VARCHAR(15) NOT NULL,
    lName     VARCHAR(15) NOT NULL,
    street    VARCHAR(50) NOT NULL,
	city      VARCHAR(50) NOT NULL,
	district  VARCHAR(20) NOT NULL,
    contact    CHAR(10)   NOT NULL,
    regionId   INT        NOT NULL,
    reputation DOUBLE     NOT NULL,
    CONSTRAINT PRIMARY KEY (driverId),
    CONSTRAINT FOREIGN KEY (regionId) REFERENCES Region (regionId) ON DELETE CASCADE
)engine=innodb;

CREATE TABLE Dustbin(
    dustbinId  INT    NOT NULL,
    latitude   DOUBLE NOT NULL,
    longitude  DOUBLE NOT NULL,
    regionId   INT    NOT NULL,
    gatewayId  INT    NOT NULL,
    CONSTRAINT PRIMARY KEY (dustbinId),
    CONSTRAINT FOREIGN KEY (regionId) REFERENCES Region (regionId) ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (gatewayId) REFERENCES Gateway (gatewayId) ON DELETE CASCADE
)engine=innodb;

CREATE TABLE Record(  
    recordDT   DATETIME   NOT NULL,
    dustbinId  INT    NOT NULL,
    level      DOUBLE NOT NULL,
    CONSTRAINT PRIMARY KEY (recordDT, dustbinId),
    CONSTRAINT FOREIGN KEY (dustbinId) REFERENCES Dustbin (dustbinId) ON DELETE CASCADE 
)engine=innodb;

/* -------------------------------------------------------------------------------------------------------- */
/*                                  Populate the TABLE with some data                                       */
/* -------------------------------------------------------------------------------------------------------- */


INSERT INTO User VALUE ("a","a");
INSERT INTO User VALUE ("b","b");

INSERT INTO Gateway VALUE (0, 10.2, 5.9);

INSERT INTO Region VALUE (0, "5.2+4.3+6.7+8.7+4.6+6.7");

INSERT INTO Driver VALUE (0,'risith','abcd','Risith','Ravisara','63/1, Delgaswatta Temple Road','Bandaragama','Kalutara','0718848825',0, 1.5);
INSERT INTO Driver VALUE (1,'kasun','wxyz','Kasun','Tharaka','Sri Lankaramaya Serpentine Road','Colombo','Colombo','0774958294',0,8);

INSERT INTO Dustbin VALUE (0, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (1, 10.2, 3.3, 0, 0);
INSERT INTO Dustbin VALUE (2, 10.2, 8.7, 0, 0);
INSERT INTO Dustbin VALUE (3, 10.2, 6.8, 0, 0);
INSERT INTO Dustbin VALUE (4, 10.2, 8.9, 0, 0);
INSERT INTO Dustbin VALUE (5, 10.2, 8.0, 0, 0);
INSERT INTO Dustbin VALUE (6, 10.2, 8.1, 0, 0);
INSERT INTO Dustbin VALUE (7, 10.2, 8.5, 0, 0);
INSERT INTO Dustbin VALUE (8, 10.2, 8.9, 0, 0);
INSERT INTO Dustbin VALUE (9, 10.2, 7.2, 0, 0);
INSERT INTO Dustbin VALUE (10, 10.2, 8.9, 0, 0);
INSERT INTO Dustbin VALUE (11, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (12, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (13, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (14, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (15, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (16, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (17, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (18, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (19, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (20, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (21, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (22, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (23, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (24, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (25, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (26, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (27, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (28, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (29, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (30, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (31, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (32, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (33, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (34, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (35, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (36, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (37, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (38, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (39, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (40, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (41, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (42, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (43, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (44, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (45, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (46, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (47, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (48, 10.2, 8.2, 0, 0);
INSERT INTO Dustbin VALUE (49, 10.2, 8.2, 0, 0);

INSERT INTO Record VALUE ('2019-08-21 06:00:00',0,20);
INSERT INTO Record VALUE ('2019-08-21 06:00:00',1,20);
INSERT INTO Record VALUE ('2019-08-21 06:00:00',2,20);
INSERT INTO Record VALUE ('2019-08-21 07:00:00',0,30);
INSERT INTO Record VALUE ('2019-08-21 07:00:00',1,30);
INSERT INTO Record VALUE ('2019-08-21 08:00:00',1,50);
INSERT INTO Record VALUE ('2019-08-21 08:00:00',2,70);
INSERT INTO Record VALUE ('2019-08-21 09:00:00',0,40);
INSERT INTO Record VALUE ('2019-08-21 10:00:00',0,50);
INSERT INTO Record VALUE ('2019-08-21 10:00:00',1,80);
INSERT INTO Record VALUE ('2019-08-21 12:00:00',0,60);
INSERT INTO Record VALUE ('2019-08-21 13:00:00',0,70);
INSERT INTO Record VALUE ('2019-08-22 05:00:00',0,80);
INSERT INTO Record VALUE ('2019-08-22 06:00:00',0,10);
INSERT INTO Record VALUE ('2019-08-22 06:00:00',1,20);
INSERT INTO Record VALUE ('2019-08-22 08:00:00',0,20);
INSERT INTO Record VALUE ('2019-08-22 08:00:00',1,40);
INSERT INTO Record VALUE ('2019-08-22 10:00:00',0,30);
INSERT INTO Record VALUE ('2019-08-22 10:00:00',1,60);
INSERT INTO Record VALUE ('2019-08-22 11:00:00',2,05);
INSERT INTO Record VALUE ('2019-08-22 12:00:00',0,60);
INSERT INTO Record VALUE ('2019-08-22 13:00:00',1,90);
INSERT INTO Record VALUE ('2019-08-22 16:00:00',2,25);
INSERT INTO Record VALUE ('2019-08-22 16:00:00',3,65);
INSERT INTO Record VALUE ('2019-08-22 10:00:00',3,60);
INSERT INTO Record VALUE ('2019-08-23 11:00:00',4,05);
INSERT INTO Record VALUE ('2019-08-24 02:00:00',5,60);
INSERT INTO Record VALUE ('2019-08-24 04:00:00',6,90);
INSERT INTO Record VALUE ('2019-08-24 07:00:00',4,25);
INSERT INTO Record VALUE ('2019-08-24 09:00:00',6,65);

/* -------------------------------------------------------------------------------------------------------- */