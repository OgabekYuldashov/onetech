create table if not exists persistent_logins (
  username varchar_ignorecase(100) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null
  );

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

insert into role (id, role) values (1, 'SELLER');
insert into role (id, role) values (2, 'BUYER');
insert into role (id, role) values (3, 'ADMIN');


-- PASSWORD is '123'
INSERT INTO Credentials (`ID`,  `FIRST_NAME`, `LAST_NAME`,`EMAIL`, `PASSWORD`,`VERIFIED`, `ROLE_ID`) VALUES (1, 'Tina', 'Xing','seller@seller.com', '$2a$10$9iC/DKLxg4vlDJSmIxEbbePVekUUTrRNakyduFaocTUKWbB/mMbgq', 1, 1);
INSERT INTO Address (`ID`, `CITY`, `STATE`, `STREET`, `ZIP`) VALUES (1, 'Fairfield', 'IA', '1000 N 4th', '52556');
INSERT INTO Seller (`ID`, `ADDRESS_ID`, `CREDENTIALS_ID`) VALUES (1, 1, 1);

INSERT INTO Credentials (`ID`,  `FIRST_NAME`, `LAST_NAME`,`EMAIL`, `PASSWORD`,`VERIFIED`, `ROLE_ID`) VALUES (2, 'John', 'Doe','john.doe@gmail.com', '$2a$10$9iC/DKLxg4vlDJSmIxEbbePVekUUTrRNakyduFaocTUKWbB/mMbgq', 1, 2);
-- INSERT INTO Address (`ID`, `CITY`, `STATE`, `STREET`, `ZIP`) VALUES (2, 'Fairfield', 'IA', '1000 N 4th', '52557');
INSERT INTO Buyer (`ID`, `CREDENTIALS_ID`, `CART_ID`) VALUES (1, 2, null);
-- insert into cart (id, buyer_id, total_amount) values (1, 1, 0);


INSERT INTO Product (`ID`,`DATE_PRODUCT_ADDED`,`DESCRIPTION`,`DISCOUNT_RATE`,`IS_NEW_ARRIVAL`, `NAME`, `OLD_PRICE`, `PRICE`,`BRAND_ID`, `CATEGORY_ID`, `SELLER_ID`) VALUES (1,DATE '2019-08-13','Lorem Ipsum Dolor sit amet. Lorem Ipsum Dolor sit amet',3,FALSE, 'HP Envy 17', 1352, 1299,1, 1, 1);
insert into product_image (id, img_name) values (1, '7600998b-5936-4970-8312-572dbfde5ba3.jpg');
insert into product_image (id, img_name) values (2, '7ce0ad2d-f0e4-44c1-b1a5-9d0836f4bb94.jpg');
insert into product_image (id, img_name) values (3, '1f2b727d-5ffa-4357-a20b-ea8e843da40e.jpg');
insert into product_product_imgs (product_id, product_imgs_id) values (1, 1);
insert into product_product_imgs (product_id, product_imgs_id) values (1, 2);
insert into product_product_imgs (product_id, product_imgs_id) values (1, 3);

INSERT INTO Product (`DATE_PRODUCT_ADDED`,`DESCRIPTION`,`DISCOUNT_RATE`,`IS_NEW_ARRIVAL`, `NAME`, `OLD_PRICE`, `PRICE`,`BRAND_ID`, `CATEGORY_ID`, `SELLER_ID`) VALUES (DATE '2019-08-13','Lorem Ipsum Dolor sit amet. Lorem Ipsum Dolor sit amet',2,FALSE, 'HP Pavilion', 1732, 1700,1, 1, 1);
insert into product_image (id, img_name) values (4, '50f5bf4e-fd7f-42cc-ab54-154befff66c7.jpg');
insert into product_image (id, img_name) values (5, '20b416b5-fb49-4bdb-9904-9a8caddc51ec.jpg');
insert into product_image (id, img_name) values (6, '9dbd7f1b-f0b8-4c8c-9f60-2d776e8e248a.jpg');
insert into product_product_imgs (product_id, product_imgs_id) values (2, 4);
insert into product_product_imgs (product_id, product_imgs_id) values (2, 5);
insert into product_product_imgs (product_id, product_imgs_id) values (2, 6);

INSERT INTO Product (`DATE_PRODUCT_ADDED`,`DESCRIPTION`,`DISCOUNT_RATE`,`IS_NEW_ARRIVAL`, `NAME`, `OLD_PRICE`, `PRICE`,`BRAND_ID`, `CATEGORY_ID`, `SELLER_ID`) VALUES (DATE '2019-08-13','Lorem Ipsum Dolor sit amet. Lorem Ipsum Dolor sit amet',2,FALSE, 'HP Spectre', 1992, 1870,1, 1, 1);
insert into product_image (id, img_name) values (7, '7ce0ad2d-f0e4-44c1-b1a5-9d0836f4bb94.jpg');
insert into product_image (id, img_name) values (8, '1f2b727d-5ffa-4357-a20b-ea8e843da40e.jpg');
insert into product_image (id, img_name) values (9, '50f5bf4e-fd7f-42cc-ab54-154befff66c7.jpg');
insert into product_product_imgs (product_id, product_imgs_id) values (3,7);
insert into product_product_imgs (product_id, product_imgs_id) values (3,8);
insert into product_product_imgs (product_id, product_imgs_id) values (3,9);


insert into review (byr_id, date_posted, message, product_id, status, title) values (1, DATE '2019-08-13', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 1, 'APPROVED', 'This is an awesome product');
insert into review (byr_id, date_posted, message, product_id, status, title) values (1, DATE '2019-08-13', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 1, 'APPROVED', 'This is an awesome product');
insert into review (byr_id, date_posted, message, product_id, status, title) values (1, DATE '2019-08-13', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 2, 'PENDING', 'This is an awesome product');
insert into review (byr_id, date_posted, message, product_id, status, title) values (1, DATE '2019-08-13', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 2, 'APPROVED', 'This is an awesome product');
insert into review (byr_id, date_posted, message, product_id, status, title) values (1, DATE '2019-08-13', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 3, 'PENDING', 'This is an awesome product');
insert into review (byr_id, date_posted, message, product_id, status, title) values (1, DATE '2019-08-13', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 3, 'APPROVED', 'This is an awesome product');


/*INSERT INTO PRODUCT_PRODUCT_IMGS (`PRODUCT_ID`, `PRODUCT_IMGS_ID`) VALUES (1, 1);
INSERT INTO PRODUCT_PRODUCT_IMGS (`PRODUCT_ID`, `PRODUCT_IMGS_ID`) VALUES (1, 2);
INSERT INTO PRODUCT_PRODUCT_IMGS (`PRODUCT_ID`, `PRODUCT_IMGS_ID`) VALUES (1, 3);
INSERT INTO PRODUCT_PRODUCT_IMGS (`PRODUCT_ID`, `PRODUCT_IMGS_ID`) VALUES (2, 1);
INSERT INTO PRODUCT_PRODUCT_IMGS (`PRODUCT_ID`, `PRODUCT_IMGS_ID`) VALUES (2, 2);
INSERT INTO PRODUCT_PRODUCT_IMGS (`PRODUCT_ID`, `PRODUCT_IMGS_ID`) VALUES (2, 3);
INSERT INTO PRODUCT_PRODUCT_IMGS (`PRODUCT_ID`, `PRODUCT_IMGS_ID`) VALUES (3, 1);
INSERT INTO PRODUCT_PRODUCT_IMGS (`PRODUCT_ID`, `PRODUCT_IMGS_ID`) VALUES (3, 2);
INSERT INTO PRODUCT_PRODUCT_IMGS (`PRODUCT_ID`, `PRODUCT_IMGS_ID`) VALUES (3, 3);*/

/*INSERT INTO ORDER_ITEM ( `QUANTITY`, `UNIT_PRICE`,`PRODUCT_ID`) VALUES (1, 1,1);
INSERT INTO ORDER_ITEM ( `QUANTITY`, `UNIT_PRICE`,`PRODUCT_ID`) VALUES (1, 1,1);
INSERT INTO ORDER_ITEM ( `QUANTITY`, `UNIT_PRICE`,`PRODUCT_ID`) VALUES (1, 1,1);
INSERT INTO ORDER_ITEM ( `QUANTITY`, `UNIT_PRICE`,`PRODUCT_ID`) VALUES (1, 1,1);*/
