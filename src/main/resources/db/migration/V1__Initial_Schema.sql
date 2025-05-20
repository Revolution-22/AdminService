CREATE TABLE users (
       user_id BIGSERIAL PRIMARY KEY,
       username VARCHAR(255),
       email VARCHAR(255),
       blocked BOOLEAN
);

CREATE TABLE payouts (
     order_id BIGINT NOT NULL,
     receiver_id BIGINT NOT NULL,
     bank_account_number VARCHAR(255),
     amount DOUBLE PRECISION,
     paid BOOLEAN,
     PRIMARY KEY (order_id, receiver_id)
);

ALTER TABLE payouts
    ADD CONSTRAINT fk_receiver
        FOREIGN KEY (receiver_id)
            REFERENCES users(user_id);

