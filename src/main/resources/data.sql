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

INSERT INTO Role (`ROLE`) VALUES ('ADMIN');
INSERT INTO Role (`ROLE`) VALUES ('SELLER');
INSERT INTO Role (`ROLE`) VALUES ('BUYER');


/*INSERT INTO Category VALUES (4,'Computers & Laptops');
INSERT INTO Category VALUES (5,'Cameras & Photos');
INSERT INTO Category VALUES (6,'Hardware');
INSERT INTO Category VALUES (7,'Smartphones & Tablets');*/

-- prepopulating the CATEGORIES
INSERT INTO Category (`NAME`) VALUES ('Computers & Laptops');
INSERT INTO Category (`NAME`) VALUES ('Cameras & Photos');
INSERT INTO Category (`NAME`) VALUES ('Hardware');
INSERT INTO Category (`NAME`) VALUES ('Smartphones & Tablets');
INSERT INTO Category (`NAME`) VALUES ('TV & Audio');
INSERT INTO Category (`NAME`) VALUES ('Gadgets');
INSERT INTO Category (`NAME`) VALUES ('Car Electronics');
INSERT INTO Category (`NAME`) VALUES ('Video Games $ Consoles');
INSERT INTO Category (`NAME`) VALUES ('Accessories');
