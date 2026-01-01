-- FlywayとはDB定義や初期データの登録を管理する機能を提供(postgreSQL用) --
-- Flywayの命名ルール：　V(大文字) + 数値(.や_区切り可能) + __(アンスコ２つ) + 任意の名称 + .sql --

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL UNIQUE,
	password VARCHAR(255) NOT NULL,
	enabled BOOLEAN NOT NULL DEFAULT TRUE
);

-- sample user --
INSERT INTO users (name, password, enabled) values (
	'admin',
	'$2a$10$mJcfMj4MMAFslbw.1LbsbeYOy7UiYJiuVNYI7Fa7zs7jQra13O5lu',
	TRUE
);