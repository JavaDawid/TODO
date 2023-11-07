CREATE TABLE IF NOT EXISTS task
(
    id          BIGINT AUTO_INCREMENT,
    title       VARCHAR(500),
    description VARCHAR(2000),
    priority    INT,
    create_date DATETIME,
    completed   TINYINT(1)
);

CREATE TABLE IF NOT EXISTS task_seq
(
    id       BIGINT,
    next_val BIGINT
);