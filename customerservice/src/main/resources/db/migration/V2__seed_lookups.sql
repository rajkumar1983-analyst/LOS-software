-- Seed lookup reference data (gender, id type, marital status, occupation, religion).
INSERT INTO dbo.lookup_type (id, code) VALUES
    (1, 'GENDER'),
    (2, 'ID_TYPE'),
    (3, 'MARITAL_STATUS'),
    (4, 'OCCUPATION'),
    (5, 'RELIGION');

INSERT INTO dbo.lookup_values (id, type_code, value_code, value_desc) VALUES
    (1, 1, 1, 'MALE'),
    (2, 1, 2, 'FEMALE'),
    (3, 2, 1, 'AADHAR'),
    (4, 2, 2, 'PASSPORT'),
    (5, 2, 3, 'DRIVING LICENSE'),
    (6, 3, 1, 'SINGLE'),
    (7, 3, 2, 'MARRIED'),
    (8, 3, 3, 'DIVORCED'),
    (9, 3, 4, 'OTHERS'),
    (10, 4, 1, 'SALARIED-PRIVATE LTD'),
    (11, 4, 2, 'SALARIED-PUBLIC LTD'),
    (12, 4, 3, 'SELF-EMPLOYED'),
    (13, 4, 4, 'UNEMPLOYED'),
    (14, 4, 5, 'STUDENT'),
    (15, 4, 6, 'OTHERS'),
    (16, 5, 1, 'HINDU'),
    (17, 5, 2, 'CHRISTIAN'),
    (18, 5, 3, 'MUSLIM'),
    (19, 5, 4, 'SIKH'),
    (20, 5, 5, 'OTHERS');
