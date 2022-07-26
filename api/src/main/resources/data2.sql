 DROP TABLE IF EXISTS medicalrecords;

 
CREATE TABLE medicalrecords (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  firstname VARCHAR(255) NOT NULL,
  lastname VARCHAR(255) NOT NULL,
  birthdate VARCHAR(255) NOT NULL,
  medications VARCHAR(255) NOT NULL,
  allergies VARCHAR(255) NOT NULL
 
); 
 
INSERT INTO medicalrecords (firstname, lastname, birthdate, medications, allergies) VALUES

 ('John', 'Boyd', '03/06/1984', 'aznol:350mg, hydrapermazol:100mg', 'nillacilan'),
 ('Jacob', 'Boyd', '03/06/1989', 'pharmacol:5000mg, terazine:10mg, noznazol:250mg', ''),
 ('Tenley', 'Boyd', '02/18/2012', '', 'peanut'),
 ('Roger', 'Boyd', '09/06/2017', '', ''),
 ('Felicia', 'Boyd', '01/08/1986', 'tetracyclaz:650mg', 'xilliathal'),
 ('Jonanathan', 'Marrack', '01/03/1989', '', ''),
 ('Tessa', 'Carman', '02/18/2012', '', ''),
 ('Peter', 'Duncan', '09/06/2000', '', 'shellfish'),
 ('Foster', 'Shepard', '01/08/1980', '', ''),
 ('Tony', 'Cooper', '03/06/1994', 'hydrapermazol:300mg, dodoxadin:30mg', 'shellfish'),
 ('Lily', 'Cooper', '03/06/1994', '', ''),
 ('Sophia', 'Zemicks', '03/06/1988', 'aznol:60mg, hydrapermazol:900mg, pharmacol:5000mg, terazine:500mg', 'peanut, shellfish, aznol'),
 ('Warren', 'Zemicks', '03/06/1985', '', ''),
 ('Zach', 'Zemicks', '03/06/2017', '', ''),
 ('Reginold', 'Walker', '08/30/1979', 'thradox:700mg', 'illisoxian'),
 ('Jamie', 'Peters', '03/06/1982', '', ''),
 ('Ron', 'Peters', '04/06/1965', '', ''),
 ('Allison', 'Boyd', '03/15/1965', 'aznol:200mg', 'nillacilan'),
 ('Brian', 'Stelzer', '12/06/1975', 'ibupurin:200mg, hydrapermazol:400mg', 'nillacilan'),
 ('Shawna', 'Stelzer', '07/08/1980', '', ''),
 ('Kendrik', 'Stelzer', '03/06/2014', 'noxidian:100mg, pharmacol:2500mg', ''),
 ('Clive', 'Ferguson', '03/06/1994', '', ''),
 ('Eric', 'Cadigan', '08/06/1945', 'tradoxidine:400mg', '');