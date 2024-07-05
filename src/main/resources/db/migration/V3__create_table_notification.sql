CREATE TABLE IF NOT EXISTS notifications(
    id VARCHAR(36) PRIMARY KEY,
    date_time TIMESTAMP,
    destination VARCHAR(100),
    message TEXT,
    channel_id BIGINT,
    status_id BIGINT,
    FOREIGN KEY (channel_id) REFERENCES channels(id),
    FOREIGN KEY (status_id) REFERENCES status(id)
);