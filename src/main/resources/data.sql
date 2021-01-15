DELETE FROM Taco_Order_Tacos;
DELETE FROM Taco_Ingredients;
DELETE FROM Taco;
DELETE FROM Taco_Order;
DELETE FROM Ingredient;

INSERT INTO Ingredient (id, name, type) values('FLTO', 'Flour Tortilla', 'WRAP');
INSERT INTO Ingredient (id, name, type) values('COTO', 'Corn Tortilla', 'WRAP');
INSERT INTO Ingredient (id, name, type) values('GRBF', 'Ground Beef', 'PROTEIN');
INSERT INTO Ingredient (id, name, type) values('CARN', 'Carnitas', 'PROTEIN');
INSERT INTO Ingredient (id, name, type) values('TMTO', 'Diced Tomatos', 'VEGGIES');
INSERT INTO Ingredient (id, name, type) values('LETC', 'Lettuce', 'VEGGIES');
INSERT INTO Ingredient (id, name, type) values('CHED', 'Cheddar', 'CHEESE');
INSERT INTO Ingredient (id, name, type) values('JACK', 'Monteray Jack', 'CHEESE');
INSERT INTO Ingredient (id, name, type) values('SLSA', 'Salsa', 'SAUCE');
INSERT INTO Ingredient (id, name, type) values('SRCR', 'Sour Cream', 'SAUCE');

