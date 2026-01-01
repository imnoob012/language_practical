-- R(リピータブル)：ファイルの中身を変更すると実行されることに注意する --
DELETE FROM skills;
DELETE FROM users;
-- パスワードは「password」 --
INSERT INTO users (name, password, role) VALUES
('admin', '$2a$10$mJcfMj4MMAFslbw.1LbsbeYOy7UiYJiuVNYI7Fa7zs7jQra13O5lu', 'ROLE_ADMIN'),
('tanaka_taro', '$2a$10$mJcfMj4MMAFslbw.1LbsbeYOy7UiYJiuVNYI7Fa7zs7jQra13O5lu', 'ROLE_USER'),
('sato_hanako', '$2a$10$mJcfMj4MMAFslbw.1LbsbeYOy7UiYJiuVNYI7Fa7zs7jQra13O5lu', 'ROLE_USER');

INSERT INTO skills (user_id, skill) VALUES
((SELECT id FROM users WHERE name = 'admin'), 'Java'),
((SELECT id FROM users WHERE name = 'admin'), 'Spring Boot'),
((SELECT id FROM users WHERE name = 'tanaka_taro'), 'HTML/CSS'),
((SELECT id FROM users WHERE name = 'tanaka_taro'), 'JavaScript'),
((SELECT id FROM users WHERE name = 'sato_hanako'), 'SQL'),
((SELECT id FROM users WHERE name = 'sato_hanako'), 'Python');