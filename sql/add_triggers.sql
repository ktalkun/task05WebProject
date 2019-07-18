USE `barbershop_db`;

CREATE TRIGGER `insert_offer`
  BEFORE INSERT
  ON `reservations`
  FOR EACH ROW
  IF IS_ID_CORRESPOND_ROLE(NEW.customer_id, 3) = 0
     OR IS_ID_CORRESPOND_ROLE(NEW.employee_id, 2) = 0
  THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Cannot add row: user role mismatch.';
  END IF;

CREATE TRIGGER `update_offer`
  BEFORE UPDATE
  ON `reservations`
  FOR EACH ROW
  IF IS_ID_CORRESPOND_ROLE(NEW.customer_id, 3) = 0
     OR IS_ID_CORRESPOND_ROLE(NEW.employee_id, 2) = 0
  THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Cannot update row: user role mismatch.';
  END IF;

CREATE TRIGGER `insert_review`
  BEFORE INSERT
  ON `reviews`
  FOR EACH ROW
  IF IS_ID_CORRESPOND_ROLE(NEW.customer_id, 3) = 0
     OR IS_ID_CORRESPOND_ROLE(NEW.employee_id, 2) = 0
  THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Cannot add row: user role mismatch.';
  END IF;

CREATE TRIGGER `update_review`
  BEFORE UPDATE
  ON `reviews`
  FOR EACH ROW
  IF IS_ID_CORRESPOND_ROLE(NEW.customer_id, 3) = 0
     OR IS_ID_CORRESPOND_ROLE(NEW.employee_id, 2) = 0
  THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Cannot update row: user role mismatch.';
  END IF;