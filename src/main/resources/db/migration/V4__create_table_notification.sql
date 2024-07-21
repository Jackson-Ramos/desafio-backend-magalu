CREATE TABLE IF NOT EXISTS notifications (
    id VARCHAR(36) PRIMARY KEY,
    user_id VARCHAR(36),
    date_time TIMESTAMP,
    destination VARCHAR(100),
    message TEXT,
    channel_id BIGINT,
    status_id BIGINT,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_channel FOREIGN KEY (channel_id) REFERENCES channels(id),
    CONSTRAINT fk_status FOREIGN KEY (status_id) REFERENCES status(id)
    );
