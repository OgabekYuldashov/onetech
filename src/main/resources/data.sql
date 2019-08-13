
INSERT INTO Category (`NAME`) VALUES ('Computers & Laptops');
INSERT INTO Category (`NAME`) VALUES ('Cameras & Photos');
INSERT INTO Category (`NAME`) VALUES ('Hardware');
INSERT INTO Category (`NAME`) VALUES ('Smartphones & Tablets');
INSERT INTO Category (`NAME`) VALUES ('TV & Audio');
INSERT INTO Category (`NAME`) VALUES ('Gadgets');
INSERT INTO Category (`NAME`) VALUES ('Car Electronics');
INSERT INTO Category (`NAME`) VALUES ('Video Games $ Consoles');
INSERT INTO Category (`NAME`) VALUES ('Accessories');

INSERT INTO Brand (`NAME`) VALUES ('Apple');
INSERT INTO Brand (`NAME`) VALUES ('Beoplay');
INSERT INTO Brand (`NAME`) VALUES ('Beoplay');
INSERT INTO Brand (`NAME`) VALUES ('Meizu');
INSERT INTO Brand (`NAME`) VALUES ('OnePlus');
INSERT INTO Brand (`NAME`) VALUES ('Samsung');
INSERT INTO Brand (`NAME`) VALUES ('Sony');
INSERT INTO Brand (`NAME`) VALUES ('Xiaomi');
INSERT INTO Brand (`NAME`) VALUES ('Other');

INSERT INTO Credentials (`ID`,  `FIRST_NAME`, `LAST_NAME`,`EMAIL`, `PASSWORD`,`VERIFIED`) VALUES (1, 'Tina', 'Xing','seller@seller.com', '132',1);

INSERT INTO Address (`ID`, `CITY`, `STATE`, `STREET`, `ZIP`) VALUES (1, 'Fairfield', 'IA', '1000 N 4th', '52557');

INSERT INTO Seller (`ID`, `ADDRESS_ID`, `CREDENTIALS_ID`) VALUES (1, 1, 1);


INSERT INTO Product (`DATE_PRODUCT_ADDED`,`DESCRIPTION`,`DISCOUNT_RATE`,`IS_NEW_ARRIVAL`, `NAME`, `PRICE`,`BRAND_ID`, `CATEGORY_ID`, `SELLER_ID`) VALUES (DATE '2019-08-13','Lorem Ipsum Dolor sit amet. Lorem Ipsum Dolor sit amet',2,FALSE, 'HP Envy 17', 1700,1, 1, 1);
INSERT INTO Product (`DATE_PRODUCT_ADDED`,`DESCRIPTION`,`DISCOUNT_RATE`,`IS_NEW_ARRIVAL`, `NAME`, `PRICE`,`BRAND_ID`, `CATEGORY_ID`, `SELLER_ID`) VALUES (DATE '2019-08-13','Lorem Ipsum Dolor sit amet. Lorem Ipsum Dolor sit amet',2,FALSE, 'HP Envy 17', 1700,1, 1, 1);
INSERT INTO Product (`DATE_PRODUCT_ADDED`,`DESCRIPTION`,`DISCOUNT_RATE`,`IS_NEW_ARRIVAL`, `NAME`, `PRICE`,`BRAND_ID`, `CATEGORY_ID`, `SELLER_ID`) VALUES (DATE '2019-08-13','Lorem Ipsum Dolor sit amet. Lorem Ipsum Dolor sit amet',2,FALSE, 'HP Envy 17', 1700,1, 1, 1);