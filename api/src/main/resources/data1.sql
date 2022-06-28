DROP TABLE IF EXISTS firestations;

 
CREATE TABLE firestations (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  address VARCHAR(255) NOT NULL,
  station VARCHAR(255) NOT NULL
); 
 
INSERT INTO firestations (address, station) VALUES
	('1509 Culver St', '3'),
        ('29 15th St', '2'),
        ('834 Binoc Ave', '3'),
        ('644 Gershwin Cir', '1'),
        ('748 Townings Dr', '3'),
        ('112 Steppes Pl', '3'),
        ('489 Manchester St', '4'),
        ('892 Downing Ct', '2'),
        ('908 73rd St', '1'),
        ('112 Steppes Pl', '4'),
        ('947 E. Rose Dr', '1'),
        ('748 Townings Dr', '3'),
        ('951 LoneTree Rd', '2');