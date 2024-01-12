CREATE TABLE Plant (
                       PlantID VARCHAR (20) PRIMARY KEY,
                       PlantName VARCHAR(50) NOT NULL,
                       Price DECIMAL(10,2) NOT NULL,
                       PlantAge varchar (20) NOT NULL,
                       PlantCount INT (200) NOT NULL
);

CREATE TABLE Supplier(
                         SupplierID varchar(20) primary key,
                         PlantName varchar (50),
                         PlantID varchar (20),
                         PlantCount int (100),
                         Price decimal (10,2),
                         foreign key (PlantID) references Plant(PlantID) on update cascade on delete cascade
);

CREATE TABLE Inventory (
                           InventoryID VARCHAR (20) PRIMARY KEY,
                           GreenHouse VARCHAR (20) NOT NULL ,
                           PlantID VARCHAR (20) NOT NULL,
                           FOREIGN KEY (PlantID) REFERENCES Plant(PlantID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Customer (
                          CustomerID VARCHAR (20) PRIMARY KEY,
                          CustomerName VARCHAR(50) NOT NULL,
                          Address VARCHAR(255) NOT NULL,
                          PhoneNumber VARCHAR(20) NOT NULL
);

CREATE TABLE Orders (
                        OrderID VARCHAR(20) PRIMARY KEY,
                        OrderDate DATE NOT NULL,
                        SoldPlant VARCHAR (20) NOT NULL,
                        PlantCount VARCHAR (200) NOT NULL,
                        CustomerID VARCHAR (20)  NOT NULL,
                        PlantID VARCHAR (20) NOT NULL,
                        FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID) ON UPDATE CASCADE ON DELETE CASCADE,
                        FOREIGN KEY (PlantID) REFERENCES Plant(PlantID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Payment (
                         PaymentID VARCHAR (20) PRIMARY KEY,
                         Date date NOT NULL,
                         Price decimal (10,2)NOT NULL,
                         SoldPlant VARCHAR (20) NOT NULL,
                         PlantCount int (250) NOT NULL,
                         OrderID VARCHAR (20),
                         CustomerID VARCHAR (20),
                         FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)  ON UPDATE CASCADE ON DELETE CASCADE,
                         FOREIGN KEY (OrderID) REFERENCES Orders(OrderID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Sale (
                      SaleID VARCHAR (20) PRIMARY KEY,
                      SaleDate DATE NOT NULL,
                      Discount DECIMAL(10,2) NOT NULL,
                      PaymentID VARCHAR (20) NOT NULL,
                      FOREIGN KEY (PaymentID) REFERENCES Payment(PaymentID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Employee (
                          EmployeeID VARCHAR (20) PRIMARY KEY,
                          EmployeeName VARCHAR(50) NOT NULL,
                          JobTitle VARCHAR(20) NOT NULL,
                          OrderID VARCHAR (20),
                          FOREIGN KEY (OrderID) REFERENCES Orders(OrderID) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE PlantOrder (
                            PlantID VARCHAR (20) NOT NULL,
                            OrderID VARCHAR (20) NOT NULL,
                            Quantity VARCHAR (20) NOT NULL,
                            FOREIGN KEY (PlantID) REFERENCES Plant(PlantID) ON UPDATE CASCADE ON DELETE CASCADE,
                            FOREIGN KEY (OrderID) REFERENCES Orders(OrderID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE InventoryOrder (
                                InventoryID VARCHAR (20) NOT NULL,
                                OrderID VARCHAR (20) NOT NULL,
                                DeliveryDate DATE NOT NULL,
                                FOREIGN KEY (InventoryID) REFERENCES Inventory(InventoryID) ON UPDATE CASCADE ON DELETE CASCADE,
                                FOREIGN KEY (OrderID) REFERENCES Orders(OrderID) ON UPDATE CASCADE ON DELETE CASCADE
);




