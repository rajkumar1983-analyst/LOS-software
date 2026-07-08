-- Baseline schema for customerservice (N7_Banking).
-- Mirrors the entity mappings; replaces Hibernate ddl-auto.

CREATE TABLE dbo.[customer] (
    cust_id            BIGINT IDENTITY(1,1) NOT NULL,
    annual_income      REAL,
    dob                DATE,
    email              VARCHAR(255),
    first_name         VARCHAR(255),
    gender             INT,
    keycloak_id        VARCHAR(255) NOT NULL,
    last_name          VARCHAR(255),
    marital_status     INT,
    occupation         INT,
    phone              VARCHAR(255),
    religion           INT,
    salutation         VARCHAR(255),
    spouse_education   INT,
    spouse_income      REAL,
    spouse_name        VARCHAR(255),
    spouse_occupation  INT,
    CONSTRAINT PK_customer PRIMARY KEY (cust_id),
    CONSTRAINT UK_customer_keycloak_id UNIQUE (keycloak_id)
);

CREATE TABLE dbo.[cust_dependant] (
    dependant_id        BIGINT IDENTITY(1,1) NOT NULL,
    dependant_age       INT,
    name                VARCHAR(255),
    dependant_relation  VARCHAR(255),
    cust_id             BIGINT,
    CONSTRAINT PK_cust_dependant PRIMARY KEY (dependant_id),
    CONSTRAINT FK_cust_dependant_customer FOREIGN KEY (cust_id) REFERENCES dbo.[customer] (cust_id)
);

CREATE TABLE dbo.[cust_education] (
    edu_id           INT IDENTITY(1,1) NOT NULL,
    completion_year  INT,
    institution      VARCHAR(255),
    qualification    INT,
    cust_id          BIGINT,
    CONSTRAINT PK_cust_education PRIMARY KEY (edu_id),
    CONSTRAINT FK_cust_education_customer FOREIGN KEY (cust_id) REFERENCES dbo.[customer] (cust_id)
);

CREATE TABLE dbo.[cust_identity] (
    identity_id     INT IDENTITY(1,1) NOT NULL,
    identity_value  VARCHAR(255),
    identity_type   INT,
    valid_until     DATE,
    cust_id         BIGINT,
    CONSTRAINT PK_cust_identity PRIMARY KEY (identity_id),
    CONSTRAINT FK_cust_identity_customer FOREIGN KEY (cust_id) REFERENCES dbo.[customer] (cust_id)
);

CREATE TABLE dbo.lookup_type (
    id    INT NOT NULL,
    code  VARCHAR(255),
    CONSTRAINT PK_lookup_type PRIMARY KEY (id)
);

CREATE TABLE dbo.lookup_values (
    id          INT NOT NULL,
    type_code   INT,
    value_code  INT,
    value_desc  VARCHAR(255),
    CONSTRAINT PK_lookup_values PRIMARY KEY (id)
);
