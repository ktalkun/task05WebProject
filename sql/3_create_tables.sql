USE `barbershop_db`;

CREATE TABLE `users`
(
    `id`         INTEGER      NOT NULL AUTO_INCREMENT,
    `login`      VARCHAR(32)  NOT NULL,
    `password`   CHAR(88)     NOT NULL,
    `name`       VARCHAR(32)  NOT NULL,
    `surname`    VARCHAR(32)  NOT NULL,
    `patronymic` VARCHAR(32)  NOT NULL,
    `email`      VARCHAR(64)  NOT NULL,
    `phone`      BIGINT       NOT NULL,
    `image_path` VARCHAR(255),
    `role`       TINYINT      NOT NULL,
    CONSTRAINT USERS_ID_PK PRIMARY KEY (id),
    CONSTRAINT USERS_PHONE_UQ UNIQUE (phone),
    CONSTRAINT USERS_LOGIN_UQ UNIQUE (login),
    CONSTRAINT USERS_EMAIL_UQ UNIQUE (email),
    CONSTRAINT USERS_ROLE_CHK CHECK (role IN (0, 1, 2, 3))
);

CREATE TABLE `employees`
(
    `employee_id` INTEGER NOT NULL,
    `experience`  DATE    NOT NULL,
    `im`          VARCHAR(255),
    `fb`          VARCHAR(255),
    `vk`          VARCHAR(255),
    `work_week`   VARCHAR(7) NOT NULL,
    CONSTRAINT EMPLOYEES_INFO_EMPLOYEE_ID_FK
        FOREIGN KEY (employee_id) REFERENCES users (id),
    INDEX EMPLOYEES_INFO_EMPLOYEE_ID_IND (employee_id)
);

CREATE TABLE `offers`
(
    `id`          INTEGER      NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(64)  NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `image_path`  VARCHAR(255) NOT NULL,
    `price`       FLOAT(6, 2)  NOT NULL,
    `period`      TINYINT      NOT NULL,
    `isMain`      BOOL         NOT NULL,
    `isShow`      BOOL         NOT NULL,
    CONSTRAINT OFFERS_ID_PK PRIMARY KEY (id),
    CONSTRAINT OFFERS_NAME_CHK CHECK (LENGTH(name) > 0),
    CONSTRAINT OFFERS_DESCRIPTION_CHK CHECK (LENGTH(description) > 0),
    CONSTRAINT OFFERS_PRICE_CHK CHECK (price > 0),
    CONSTRAINT OFFERS_PERIOD_CHK CHECK (period > 0 AND period < 180)
);

CREATE TABLE `employees_offers`
(
    `employee_id` INTEGER NOT NULL,
    `offer_id`    INTEGER NOT NULL,
    CONSTRAINT EMPLOYEES_OFFERS_EMPLOYEE_ID_FK
        FOREIGN KEY (employee_id) REFERENCES users (id),
    INDEX EMPLOYEES_OFFERS_EMPLOYEE_ID_IND (employee_id),
    CONSTRAINT EMPLOYEES_OFFERS_OFFER_ID_FK
        FOREIGN KEY (offer_id) REFERENCES offers (id),
    INDEX EMPLOYEES_OFFER_ID_IND (offer_id)
);

CREATE TABLE `ratings`
(
    `customer_id` INTEGER NOT NULL,
    `employee_id` INTEGER NOT NULL,
    `rating`      TINYINT NOT NULL,
    CONSTRAINT RATINGS_CUSTOMER_ID_FK
        FOREIGN KEY (customer_id) REFERENCES users (id),
    CONSTRAINT RATINGS_EMPLOYEE_ID_FK
        FOREIGN KEY (employee_id) REFERENCES users (id),
    CONSTRAINT RATINGS_RATING CHECK (rating IN (1, 2, 3, 4, 5))
);

CREATE TABLE `reservations`
(
    `id`          INTEGER  NOT NULL AUTO_INCREMENT,
    `offer_id`    INTEGER  NOT NULL,
    `customer_id` INTEGER  NOT NULL,
    `employee_id` INTEGER  NOT NULL,
    `date`        DATETIME NOT NULL,
    CONSTRAINT RESERVATIONS_ID_PK PRIMARY KEY (id),
    CONSTRAINT RESERVATIONS_OFFER_ID_FK
        FOREIGN KEY (offer_id) REFERENCES offers (id),
    CONSTRAINT RESERVATIONS_CUSTOMER_ID_FK
        FOREIGN KEY (customer_id) REFERENCES users (id),
    CONSTRAINT RESERVATIONS_EMPLOYEE_ID_FK
        FOREIGN KEY (employee_id) REFERENCES users (id),
    INDEX RESERVATIONS_CUSTOMER_ID_IND (customer_id),
    INDEX RESERVATIONS_EMPLOYEE_ID_IND (employee_id)
);

CREATE TABLE `reviews`
(
    `id`          INTEGER      NOT NULL AUTO_INCREMENT,
    `customer_id` INTEGER      NOT NULL,
    `employee_id` INTEGER      NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    CONSTRAINT REVIEWS_ID_PK PRIMARY KEY (id),
    CONSTRAINT REVIEWS_CUSTOMER_ID_FK
        FOREIGN KEY (customer_id) REFERENCES users (id),
    CONSTRAINT REVIEWS_EMPLOYEE_ID_FK
        FOREIGN KEY (employee_id) REFERENCES users (id),
    INDEX REVIEWS_CUSTOMER_ID_IND (customer_id),
    INDEX REVIEWS_EMPLOYEE_ID_IND (employee_id),
    CONSTRAINT REVIEWS_DESCRIPTION_CHK CHECK (LENGTH(description) > 0),
    CONSTRAINT REVIEWS_ROW_UQ UNIQUE (customer_id, employee_id, description)
);