CREATE DATABASE IF NOT EXISTS `barbershop_db` DEFAULT CHARACTER SET utf8;

GRANT SELECT,INSERT,UPDATE,DELETE
ON `barbershop_db`.*
TO barbershop_user@'%'
IDENTIFIED BY 'barber';