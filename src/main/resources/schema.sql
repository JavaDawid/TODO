CREATE TABLE IF NOT EXISTS task
(
    id          BIGINT AUTO_INCREMENT,
    user_id     BIGINT,
    title       VARCHAR(500),
    description VARCHAR(2000),
    priority    INT,
    create_date DATETIME,
    completed   TINYINT(1),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS user
(
    id          BIGINT AUTO_INCREMENT,
    user_name   VARCHAR(255) NOT NULL,
    first_name  VARCHAR(255) NOT NULL,
    last_name   VARCHAR(255) NOT NULL,
    email       VARCHAR(255) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    create_date DATETIME,
    PRIMARY KEY (id)
);
