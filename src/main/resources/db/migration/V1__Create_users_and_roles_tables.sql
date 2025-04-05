CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id),
    PRIMARY KEY (user_id, role_id)
);

-- Insert initial data
INSERT INTO users (username, password) VALUES ('admin', '$2a$12$SOME_ENCRYPTED_PASSWORD'); -- Replace with an actual encrypted password
INSERT INTO roles (name) VALUES ('ADMIN');

-- Get the IDs of the inserted user and role
WITH admin_user AS (
    SELECT id FROM users WHERE username = 'admin'
), admin_role AS (
    SELECT id FROM roles WHERE name = 'ADMIN'
)
-- Insert the user role
INSERT INTO user_roles (user_id, role_id)
SELECT (SELECT id FROM admin_user), (SELECT id FROM admin_role);
