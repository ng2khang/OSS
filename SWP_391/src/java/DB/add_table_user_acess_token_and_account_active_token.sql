CREATE TABLE password_reset_tokens (
    id int IDENTITY(1,1) PRIMARY KEY,
    user_email NVARCHAR(50),
    token VARCHAR(255),
    expiration_time DATETIME
);
CREATE TABLE account_active_tokens (
    id int IDENTITY(1,1) PRIMARY KEY,
    user_email NVARCHAR(50),
    token VARCHAR(255),
    expiration_time DATETIME
);

ALTER TABLE password_reset_tokens ADD CONSTRAINT password_reset_tokens_email_fkey FOREIGN KEY (user_email) REFERENCES users(email);
ALTER TABLE account_active_tokens ADD CONSTRAINT account_active_tokens_email_fkey FOREIGN KEY (user_email) REFERENCES users(email);