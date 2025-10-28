USE loan_db;

INSERT INTO users (enabled, password, role, username)
VALUES (
    1,
    '$2a$12$0v.85qBt7tXr9xu.JvKITuBAkIvZ.9HxobrLA1JaUtxBJgtoitCn6',
    'ROLE_ADMIN',
    'admin'
)
ON DUPLICATE KEY UPDATE username = username;

