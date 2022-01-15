
INSERT INTO STORE (ID, COD, NAME, IS_MAIN)
VALUES (1, 's-1', 'Supermaxi', TRUE),
       (2, 'a-2', 'Aki', FALSE),
       (3, 'm-3', 'Megamaxi', FALSE),
       (4, 'ga-4', 'Gran Aki', FALSE);

UPDATE GENERATOR SET NEXT_VAL = 53 WHERE SEQUENCE_NAME  = 'store';