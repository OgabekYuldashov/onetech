/*create table if not exists persistent_logins (
  username varchar_ignorecase(100) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null
  );

INSERT INTO Role VALUES (1, 'ADMIN');
INSERT INTO Role VALUES (2, 'DBA');
INSERT INTO Role VALUES (3, 'DEVELOPER');
INSERT INTO Role VALUES (4, 'USER');
*/

-- INSERT INTO Role (`ROLE`) VALUES ('ADMIN');
-- INSERT INTO Role (`ROLE`) VALUES ('SELLER');
-- INSERT INTO Role (`ROLE`) VALUES ('BUYER');

/*INSERT INTO Credentials (`ID`, `EMAIL`, `FIRST_NAME`, `LAST_NAME`, `PASSWORD`, `VERIFIED`, `ROLE_ID`) VALUES (1, 'seller@seller.com', 'Tina', 'Xing', '132', 1, 2);

INSERT INTO Address (`ID`, `CITY`, `STATE`, `STREET`, `ZIP`) VALUES (1, 'Fairfield', 'IA', '1000 N 4th', '52557');

INSERT INTO Seller (`ID`, `ADDRESS_ID`, `CREDENTIALS_ID`) VALUES (1, 1, 1);

INSERT INTO Product (`DESCRIPTION`, `NAME`, `PRICE`, `CATEGORY_ID`, `SELLER_ID`) VALUES ('Lorem Ipsum Dolor sit amet. Lorem Ipsum Dolor sit amet', 'MacBook Air 13', 2000, 1, 1);
INSERT INTO Product (`DESCRIPTION`, `NAME`, `PRICE`, `CATEGORY_ID`, `SELLER_ID`) VALUES ('Lorem Ipsum Dolor sit amet. Lorem Ipsum Dolor sit amet', 'HP Envy 17', 1700, 1, 1);*/


-- prepopulating the CATEGORIES
-- INSERT INTO Category (`NAME`) VALUES ('Computers & Laptops');
-- INSERT INTO Category (`NAME`) VALUES ('Cameras & Photos');
-- INSERT INTO Category (`NAME`) VALUES ('Hardware');
-- INSERT INTO Category (`NAME`) VALUES ('Smartphones & Tablets');
-- INSERT INTO Category (`NAME`) VALUES ('TV & Audio');
-- INSERT INTO Category (`NAME`) VALUES ('Gadgets');
-- INSERT INTO Category (`NAME`) VALUES ('Car Electronics');
-- INSERT INTO Category (`NAME`) VALUES ('Video Games $ Consoles');
-- INSERT INTO Category (`NAME`) VALUES ('Accessories');
