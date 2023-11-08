DROP DATABASE IF EXISTS supermarket;

CREATE DATABASE supermarket;

\c supermarket;

CREATE TABLE "user_table"
(
    "user_id"         uuid         NOT NULL PRIMARY KEY,
    "first_name"      VARCHAR(30)  NOT NULL,
    "last_name"       VARCHAR(30)  NOT NULL,
    "date_of_birth"   DATE,
    "primary_email"   VARCHAR(255) NOT NULL,
    "secondary_email" VARCHAR(255),
    "password"        VARCHAR(60)
);

INSERT INTO "user_table" ("user_id", "first_name", "last_name", "date_of_birth", "primary_email", "secondary_email",
                          "password")
VALUES ('b221fd09-eec4-47c7-a202-8c6978701e98', 'Matheus', 'Sousa', '1996-10-21', 'matheus@gmail.com',
        'matheus@icloud.com', '$2a$10$jpuMFaKK8PkSaxpDW6eSB.pxiXXcvjyP9iF8hdezsrQDQ0xjFdfvO'),
       ('9e25bf82-3575-4a8e-bf68-d0dff87edaa9', 'Maria Clara', 'Rodrigues', '1996-05-19', 'maria@gmail.com',
        'maria@icloud.com', '$2a$10$qwYiSnVR.RMthpXD26xT6.hl2xMnFXApaciRwtUIFs8bHRSdZQNjC');
