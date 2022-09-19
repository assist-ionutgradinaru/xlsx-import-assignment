CREATE TABLE IF NOT EXISTS booking_transaction (
    id serial PRIMARY KEY,
    customer_name TEXT NOT NULL,
    booking_date DATE NOT NULL,
    opportunity_id TEXT UNIQUE NOT NULL,
    booking_type TEXT NOT NULL,
    total NUMERIC NOT NULL,
    account_executive TEXT NOT NULL,
    sale_organization TEXT NOT NULL,
    team TEXT NOT NULL,
    product TEXT NOT NULL,
    renewable boolean NOT NULL
);