
-- Create crypto_category table
CREATE TABLE IF NOT EXISTS crypto_category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(50) UNIQUE NOT NULL
);

-- Create crypto table with a foreign key referencing crypto_category
CREATE TABLE IF NOT EXISTS crypto (
    ranking INT PRIMARY KEY,
    name_e VARCHAR(255) NOT NULL,
    symbol VARCHAR(10) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    one_hour_change DECIMAL(10, 2),
    twenty_four_hour_change DECIMAL(10, 2),
    market_cap BIGINT,
    volume BIGINT,
    category_id INT,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES crypto_category(id)
);

-- Insert categories into crypto_category table
INSERT IGNORE INTO crypto_category (category_name) VALUES ('Stablecoin');
INSERT IGNORE INTO crypto_category (category_name) VALUES ('Utility');
INSERT IGNORE INTO crypto_category (category_name) VALUES ('Payment');
INSERT IGNORE INTO crypto_category (category_name) VALUES ('Meme Coin');

-- Insert cryptos into crypto table 
INSERT IGNORE INTO crypto (market_cap, name_e, one_hour_change, price, ranking, symbol, twenty_four_hour_change, volume, category_id) VALUES (479140000, 'Bitcoin', 0.8, 58757.01, 1, 'BTC', 5.1, 1160743, 1);
INSERT IGNORE INTO crypto (market_cap, name_e, one_hour_change, price, ranking, symbol, twenty_four_hour_change, volume, category_id) VALUES (234630000, 'Ethereum', 0.4, 2507.13, 2, 'ETH', 2.9, 301541, 2);
INSERT IGNORE INTO crypto (market_cap, name_e, one_hour_change, price, ranking, symbol, twenty_four_hour_change, volume, category_id) VALUES (669630000, 'Tether', 0.1, 0.9999, 3, 'USDT', 0.0, 118060, 1);
INSERT IGNORE INTO crypto (market_cap, name_e, one_hour_change, price, ranking, symbol, twenty_four_hour_change, volume, category_id) VALUES (122170000, 'BNB', 0.9, 529.93, 4, 'BNB', 3.4, 774673, 2);
INSERT IGNORE INTO crypto (market_cap, name_e, one_hour_change, price, ranking, symbol, twenty_four_hour_change, volume, category_id) VALUES (41700000, 'Solana', 0.2, 142.32, 5, 'SOL', 7.9, 662857, 3);
INSERT IGNORE INTO crypto (market_cap, name_e, one_hour_change, price, ranking, symbol, twenty_four_hour_change, volume, category_id) VALUES (102390000, 'USDC', 0.1, 0.9999, 6, 'USDC', 0.0, 344325, 1);
INSERT IGNORE INTO crypto (market_cap, name_e, one_hour_change, price, ranking, symbol, twenty_four_hour_change, volume, category_id) VALUES (188630000, 'XRP', 0.5, 0.5682, 7, 'XRP', 4.6, 319617, 3);
INSERT IGNORE INTO crypto (market_cap, name_e, one_hour_change, price, ranking, symbol, twenty_four_hour_change, volume, category_id) VALUES (79002000, 'Dogecoin', 1.1, 0.09879, 8, 'DOGE', 4.7, 143875, 4);
INSERT IGNORE INTO crypto (market_cap, name_e, one_hour_change, price, ranking, symbol, twenty_four_hour_change, volume, category_id) VALUES (220840000, 'Polkadot', 1.2, 4.24, 9, 'DOT', 6.8, 595964, 2);
INSERT IGNORE INTO crypto (market_cap, name_e, one_hour_change, price, ranking, symbol, twenty_four_hour_change, volume, category_id) VALUES (432560000, 'Chainlink', 0.9, 11.27, 10, 'LINK', 2.3, 687525, 2);
