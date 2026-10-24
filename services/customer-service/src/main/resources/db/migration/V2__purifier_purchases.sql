-- V2__purifier_purchases.sql — customer-service purchase records

CREATE TABLE purifier_purchases (
                                    id                UUID          NOT NULL,
                                    customer_id       UUID          NOT NULL,
                                    product_model     VARCHAR(120)  NOT NULL,
                                    product_variant   VARCHAR(120),
                                    serial_number     VARCHAR(80),
                                    invoice_number    VARCHAR(80)   NOT NULL,
                                    purchase_date     DATE          NOT NULL,
                                    purchase_price    NUMERIC(12,2),
                                    currency          VARCHAR(3)    NOT NULL,
                                    warranty_months   INTEGER,
                                    installation_date DATE,
                                    amc_start_date    DATE,
                                    amc_end_date      DATE,
                                    dealer_name       VARCHAR(150),
                                    status            VARCHAR(20)   NOT NULL,
                                    version           BIGINT        NOT NULL,
                                    created_at        TIMESTAMP(6) WITH TIME ZONE NOT NULL,
                                    updated_at        TIMESTAMP(6) WITH TIME ZONE NOT NULL,
                                    CONSTRAINT pk_purifier_purchases PRIMARY KEY (id),
                                    CONSTRAINT fk_purifier_purchases_customer
                                        FOREIGN KEY (customer_id) REFERENCES customers (id)
);

CREATE INDEX idx_purifier_purchases_customer_id ON purifier_purchases (customer_id);
CREATE INDEX idx_purifier_purchases_serial_number ON purifier_purchases (serial_number);
