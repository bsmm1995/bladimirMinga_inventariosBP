INSERT INTO STORE (ID, COD, NAME, IS_MAIN)
VALUES (1, 's-1', 'Supermaxi', TRUE),
       (2, 'a-2', 'Aki', FALSE),
       (3, 'm-3', 'Megamaxi', FALSE),
       (4, 'ga-4', 'Gran Aki', FALSE);

INSERT INTO STORE_PRODUCT (ID, STORE_ID, PRODUCT_ID)
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 1, 4),
       (4, 1, 6),
       (5, 2, 1),
       (6, 2, 3),
       (7, 3, 2),
       (8, 3, 5),
       (9, 4, 1),
       (10, 4, 6);

INSERT INTO CUSTOMER (ID, NAME, DNI, PICTURE)
VALUES (1, 'Bladimir Minga', '1900875321',
        'https://d500.epimg.net/cincodias/imagenes/2016/07/04/lifestyle/1467646262_522853_1467646344_noticia_normal.jpg'),
       (2, 'Matias Minga', '1100875322',
        'https://d500.epimg.net/cincodias/imagenes/2016/07/04/lifestyle/1467646262_522853_1467646344_noticia_normal.jpg');

INSERT INTO TRANSACTION (ID, CUSTOMER_ID, STORE_ID, DATE, TOTAL)
VALUES (1, 1, 1, '2022-01-13 08:00:00', 32);

INSERT INTO TRANSACTION_DETAIL (ID, TRANSACTION_ID, PRODUCT_ID, PRICE, QUANTITY)
VALUES (1, 1, 2, 3, 5),
       (2, 1, 4, 2, 4),
       (3, 1, 6, 9, 1);

INSERT INTO TRANSACTION (ID, CUSTOMER_ID, STORE_ID, DATE, TOTAL)
VALUES (2, 2, 2, '2022-01-13 08:30:00', 17);

INSERT INTO TRANSACTION_DETAIL (ID, TRANSACTION_ID, PRODUCT_ID, PRICE, QUANTITY)
VALUES (4, 2, 1, 1.5, 6),
       (5, 2, 3, 4, 2);
