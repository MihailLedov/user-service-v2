CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       role VARCHAR(20) NOT NULL DEFAULT 'USER',
                       active BOOLEAN NOT NULL DEFAULT TRUE
);