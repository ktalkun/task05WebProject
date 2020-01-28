CREATE DATABASE barbershop_db ENCODING 'UTF8';
CREATE USER barbershop_user WITH ENCRYPTED PASSWORD 'barber';
GRANT ALL PRIVILEGES ON DATABASE barbershop_db to barbershop_user;