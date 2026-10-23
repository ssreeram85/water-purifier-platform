-- Customer Service schema
CREATE TABLE customers (
    id UUID PRIMARY KEY,
    customer_code VARCHAR(30) NOT NULL,
    first_name VARCHAR(80) NOT NULL,
    last_name VARCHAR(80) NOT NULL,
    mobile_number VARCHAR(20) NOT NULL,
    alternate_mobile VARCHAR(20),
    email VARCHAR(255),
    address_line1 VARCHAR(255),
    address_line2 VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    postal_code VARCHAR(10),
    preferred_channel VARCHAR(20) NOT NULL,
    marketing_consent BOOLEAN NOT NULL DEFAULT FALSE,
    status VARCHAR(20) NOT NULL,
    notes VARCHAR(500),
    version BIGINT NOT NULL DEFAULT 0,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    CONSTRAINT uk_customers_code UNIQUE (customer_code),
    CONSTRAINT uk_customers_mobile UNIQUE (mobile_number),
    CONSTRAINT uk_customers_email UNIQUE (email),
    CONSTRAINT ck_customers_channel CHECK (preferred_channel IN ('SMS', 'EMAIL', 'NONE')),
    CONSTRAINT ck_customers_status CHECK (status IN ('ACTIVE', 'INACTIVE', 'BLOCKED'))
);
CREATE INDEX idx_customers_name ON customers (lower(last_name), lower(first_name));
CREATE INDEX idx_customers_city ON customers (city);

CREATE TABLE purifier_purchases (
    id UUID PRIMARY KEY,
    customer_id UUID NOT NULL REFERENCES customers (id) ON DELETE CASCADE,
    product_model VARCHAR(120) NOT NULL,
    product_variant VARCHAR(120),
    serial_number VARCHAR(80),
    invoice_number VARCHAR(80) NOT NULL,
    purchase_date DATE NOT NULL,
    purchase_price NUMERIC(12, 2),
    currency VARCHAR(3) NOT NULL DEFAULT 'INR',
    warranty_months INTEGER,
    installation_date DATE,
    amc_start_date DATE,
    amc_end_date DATE,
    dealer_name VARCHAR(150),
    status VARCHAR(20) NOT NULL,
    version BIGINT NOT NULL DEFAULT 0,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    CONSTRAINT uk_purchase_invoice UNIQUE (invoice_number),
    CONSTRAINT uk_purchase_serial UNIQUE (serial_number),
    CONSTRAINT ck_purchase_warranty CHECK (warranty_months IS NULL OR warranty_months BETWEEN 1 AND 240),
    CONSTRAINT ck_purchase_price CHECK (purchase_price IS NULL OR purchase_price >= 0),
    CONSTRAINT ck_purchase_status CHECK (status IN ('ACTIVE', 'RETURNED', 'REPLACED')),
    CONSTRAINT ck_purchase_amc CHECK (amc_end_date IS NULL OR amc_start_date IS NULL OR amc_end_date >= amc_start_date),
    CONSTRAINT ck_purchase_dates CHECK (installation_date IS NULL OR installation_date >= purchase_date)
);
CREATE INDEX idx_purchase_customer ON purifier_purchases (customer_id);
CREATE INDEX idx_purchase_purchase_date ON purifier_purchases (purchase_date);

CREATE TABLE outbox_events (
    id UUID PRIMARY KEY,
    aggregate_type VARCHAR(100) NOT NULL,
    aggregate_id VARCHAR(100) NOT NULL,
    event_type VARCHAR(150) NOT NULL,
    event_version INTEGER NOT NULL,
    payload JSONB NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    published_at TIMESTAMPTZ
);
CREATE INDEX idx_customer_outbox_unpublished ON outbox_events (created_at) WHERE published_at IS NULL;

CREATE TABLE customer_number_sequence (
    prefix VARCHAR(10) PRIMARY KEY,
    last_value BIGINT NOT NULL
);
INSERT INTO customer_number_sequence (prefix, last_value) VALUES ('CUST', 0);
