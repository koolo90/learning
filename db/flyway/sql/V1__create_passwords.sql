CREATE TABLE passwords (
    page_url VARCHAR(500),
    username VARCHAR(64),
    password varchar(255) -- so far plaintext, should be encrypted with salt and master password, possibly a key file.
);
