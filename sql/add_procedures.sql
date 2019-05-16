USE `barbershop_db`;

CREATE FUNCTION IS_ID_CORRESPOND_ROLE(check_id INTEGER, check_role TINYINT) RETURNS BOOL
  BEGIN
    IF (SELECT COUNT(`id`)
        FROM `users`
        WHERE `id` = check_id
          AND `role` = check_role) = 1
    THEN
      RETURN 1;
    END IF;
    RETURN 0;
  END;