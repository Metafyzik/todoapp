CREATE TABLE IF NOT EXISTS item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(25),
    created TIMESTAMP,
    adjusted TIMESTAMP,
    task VARCHAR(500),
    PRIMARY KEY (id)
);


