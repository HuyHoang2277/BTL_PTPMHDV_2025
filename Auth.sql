CREATE DATABASE IF NOT EXISTS eduzone
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE eduzone;

CREATE TABLE IF NOT EXISTS auth_accounts (
  id INT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(255) UNIQUE NOT NULL,
  password_hash VARCHAR(255),
  provider ENUM('credentials', 'google') NOT NULL DEFAULT 'credentials',
  provider_id VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  status TINYINT DEFAULT 1,
  INDEX idx_email (email),
  UNIQUE KEY unique_provider (provider, provider_id)
);

CREATE TABLE IF NOT EXISTS auth_roles (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) UNIQUE NOT NULL
);

-- Bảng `auth_account_roles`: Gán quyền cho tài khoản
CREATE TABLE IF NOT EXISTS auth_account_roles (
  account_id INT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (account_id, role_id),
  FOREIGN KEY (account_id) REFERENCES auth_accounts(id) ON DELETE CASCADE,
  FOREIGN KEY (role_id) REFERENCES auth_roles(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS auth_refresh_tokens (
  id INT PRIMARY KEY AUTO_INCREMENT,
  account_id INT NOT NULL,
  token VARCHAR(500) UNIQUE NOT NULL,
  expiry_date TIMESTAMP NOT NULL,
  FOREIGN KEY (account_id) REFERENCES auth_accounts(id) ON DELETE CASCADE,
  INDEX idx_token (token)
);

INSERT IGNORE INTO auth_roles (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_SUPER_ADMIN');