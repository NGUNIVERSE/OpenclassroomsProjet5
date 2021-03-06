 
 DROP TABLE IF EXISTS persons;

 
CREATE TABLE persons (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  firstname VARCHAR(255) NOT NULL,
  lastname VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  city VARCHAR(255) NOT NULL,
  zip VARCHAR(255) NOT NULL,
  phone VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL
 
); 
 
INSERT INTO persons (firstname, lastname, address, city, zip, phone, email) VALUES
  ('John', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6512', 'aboyd@email.com'),
  ('Jacob', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6513', 'drk@email.com'),
  ('Tenley', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6512', 'tenz@email.com'),
  ('Roger', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6512', 'jaboyd@email.com'),
  ('Felicia', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6544', 'jaboyd@email.com'),
  ('Jonanathan', 'Marrack', '29 15th St', 'Culver', '97451', '841-874-6513', 'drk@email.com'),
  ('Tessa', 'Carman', '834 Binoc Ave', 'Culver', '97451', '841-874-6512', 'tenz@email.com'),
  ('Peter', 'Duncan', '644 Gershwin Cir', 'Culver', '97451', '841-874-6512', 'jaboyd@email.com'),
  ('Foster', 'Shepard', '748 Townings Dr', 'Culver', '97451', '841-874-6544', 'jaboyd@email.com'),
  ('Tony', 'Cooper', '112 Steppes Pl', 'Culver', '97451', '841-874-6874', 'tcoop@ymail.com'),
  ('Lily', 'Cooper', '489 Manchester St', 'Culver', '97451', '841-874-9845', 'lily@email.com'),
  ('Sophia', 'Zemicks', '892 Downing Ct', 'Culver', '97451', '841-874-7878', 'soph@email.com'),
  ('Warren', 'Zemicks', '892 Downing Ct', 'Culver', '97451', '841-874-7512', 'ward@email.com'),
  ('Zach', 'Zemicks', '892 Downing Ct', 'Culver', '97451', '841-874-7512', 'zarc@email.com'),
  ('Reginold', 'Walker', '908 73rd St', 'Culver', '97451', '841-874-8547', 'reg@email.com'),
  ('Jamie', 'Peters', '908 73rd St', 'Culver', '97451', '841-874-7462', 'jpeter@email.com'),
  ('Ron', 'Peters', '112 Steppes Pl', 'Culver', '97451', '841-874-8888', 'jpeter@email.com'),
  ('Allison', 'Boyd', '112 Steppes Pl', 'Culver', '97451', '841-874-9888', 'aly@imail.com'),
  ('Brian', 'Stelzer', '947 E. Rose Dr', 'Culver', '97451', '841-874-7784', 'bstel@email.com'),
  ('Shawna', 'Stelzer', '947 E. Rose Dr', 'Culver', '97451', '841-874-7784', 'ssanw@email.com'),
  ('Kendrik', 'Stelzer', '947 E. Rose Dr', 'Culver', '97451', '841-874-7784', 'bstel@email.com'),
  ('Clive', 'Ferguson', '748 Townings Dr', 'Culver', '97451', '841-874-6741', 'clivfd@ymail.com'),
  ('Eric', 'Cadigan', '951 LoneTree Rd', 'Culver', '97451', '841-874-7458', 'gramps@email.com');
 