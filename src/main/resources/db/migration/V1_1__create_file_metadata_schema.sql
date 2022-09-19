CREATE TABLE IF NOT EXISTS file_metadata (
    id serial PRIMARY KEY,
    name TEXT NOT NULL,
    size NUMERIC NOT NULL,
    creation_date DATE NOT NULL,
    upload_date DATE NOT NULL
);