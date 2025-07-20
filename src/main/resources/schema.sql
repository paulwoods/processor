DROP TABLE IF EXISTS job;

CREATE TABLE job
(
--     id  SERIAL PRIMARY KEY,
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    url    varchar(1000) NOT NULL,
    status varchar(100)  NOT NULL
);
