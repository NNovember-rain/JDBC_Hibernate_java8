CREATE DATABASE manage_bus;
CREATE TABLE Driver (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    address VARCHAR(255),
    phonenumber VARCHAR(20),
    level VARCHAR(50)
);
CREATE TABLE Route (
    id INT AUTO_INCREMENT PRIMARY KEY,
    distance INT,
    breakpoints INT
);
CREATE TABLE Assignment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    driver_id INT,
    route_id INT,
	numberoftrips INT,
    FOREIGN KEY (driver_id) REFERENCES Driver(id),
    FOREIGN KEY (route_id) REFERENCES Route(id)
);

