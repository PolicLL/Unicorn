CREATE TABLE IF NOT EXISTS `unicorn`.`car-part` (
  `serial_number` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) DEFAULT NULL,
  `date_created` DATETIME(6) DEFAULT NULL,
  PRIMARY KEY (`serial_number`)) 
  
ENGINE=InnoDB
AUTO_INCREMENT = 1;
