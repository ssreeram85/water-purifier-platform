-- V1__init.sql — customer-service schema

CREATE TABLE customers (
                           id                  UUID         NOT NULL,
                           customer_code       VARCHAR(30)  NOT NULL,
                           first_name          VARCHAR(80)  NOT NULL,
                           last_name           VARCHAR(80)  NOT NULL,
                           mobile_number       VARCHAR(20)  NOT NULL,
                           alternate_mobile    VARCHAR(20),
                           email               VARCHAR(255),
                           address_line1       VARCHAR(255),
                           address_line2       VARCHAR(255),
                           city                VARCHAR(100),
                           state               VARCHAR(100),
                           postal_code         VARCHAR(6),
                           preferred_channel   VARCHAR(20)  NOT NULL,
                           status              VARCHAR(20)  NOT NULL,
                           marketing_consent   BOOLEAN      NOT NULL,
                           notes               VARCHAR(500),
                           version             BIGINT       NOT NULL,
                           created_at          TIMESTAMP(6) WITH TIME ZONE NOT NULL,
                           updated_at          TIMESTAMP(6) WITH TIME ZONE NOT NULL,
                           CONSTRAINT pk_customers PRIMARY KEY (id),
                           CONSTRAINT uk_customers_customer_code UNIQUE (customer_code)
);

CREATE INDEX idx_customers_mobile_number ON customers (mobile_number);
CREATE INDEX idx_customers_email ON customers (email);
