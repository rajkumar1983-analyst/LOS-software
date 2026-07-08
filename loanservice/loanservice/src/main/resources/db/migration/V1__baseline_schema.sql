-- Baseline schema for loanservice (lending).
-- Mirrors the Loan entity mapping; replaces Hibernate ddl-auto.

CREATE TABLE dbo.[loan] (
    id                   BIGINT IDENTITY(1,1) NOT NULL,
    approvercomments     VARCHAR(255),
    customer_id          BIGINT,
    isapproved           INT,
    isunderwritten       INT,
    keycloak_id          VARCHAR(255) NOT NULL,
    loan_amount          NUMERIC(38,2),
    loan_type            VARCHAR(255),
    start_date           DATE,
    status               INT,
    term_in_months       INT,
    underwritercomments  VARCHAR(255),
    CONSTRAINT PK_loan PRIMARY KEY (id)
);
