drop table if exists beer;

drop table if exists customer;

create table beer
(
    id               VARCHAR(36)    NOT NULL PRIMARY KEY,
    beer_name        VARCHAR(50)    NOT NULL,
    beer_style       SMALLINT       NOT NULL,
    created_date     DATETIME(6),
    price            decimal(38, 2) NOT NULL,
    quantity_on_hand INTEGER,
    upc              VARCHAR(255)   NOT NULL,
    update_date      DATETIME(6),
    version          INTEGER
) engine = InnoDB;

create table customer
(
    id           VARCHAR(36) NOT NULL PRIMARY KEY,
    created_date DATETIME(6),
    name         VARCHAR(255),
    update_date  DATETIME(6),
    version      INTEGER
) engine = InnoDB;