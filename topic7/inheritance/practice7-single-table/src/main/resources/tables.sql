CREATE TABLE `animals`
(
    `id`          INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name`        VARCHAR(250)  NOT NULL,
    `cage_number` VARCHAR(100)  NOT NULL,
    `animal_type` VARCHAR(50) NOT NULL,
    `height`      DECIMAL(4, 2) NULL,
    `live_region` VARCHAR(250) NULL
);