CREATE TABLE IF NOT EXISTS Ingredient (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null
);

CREATE TABLE IF NOT EXISTS Taco (
    id identity,
    name varchar(50) not null,
    createdAt timestamp not null
);

CREATE TABLE IF NOT EXISTS Taco_Ingredients (
    taco bigint not null,
    ingredient varchar(4) not null
);

ALTER TABLE Taco_Ingredients
    ADD foreign key (taco) references Taco(id);
ALTER TABLE Taco_Ingredients
    ADD foreign key (ingredient) references Ingredient(id);

CREATE TABLE IF NOT EXISTS Taco_Order (
    id identity,
    deliveryName varchar(50) not null,
    deliveryStreet varchar(50) not null,
    deliveryCity varchar(50) not null,
    deliveryState varchar(2) not null,
    deliveryZip varchar(10) not null,
    ccNumber varchar(16) not null,
    ccExpiration varchar(5) not null,
    ccCVV varchar(3) not null,
    placedAt timestamp not null
);

CREATE TABLE IF NOT EXISTS Taco_Order_Tacos (
    tacoOrder bigint not null,
    taco bigint not null
);

ALTER TABLE Taco_Order_Tacos
    ADD foreign key (taco) references Taco(id);
ALTER TABLE Taco_Order_Tacos
    ADD foreign key (tacoOrder) references Taco(id);