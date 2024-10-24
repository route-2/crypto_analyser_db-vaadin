
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

-- Insert data into crypto_category
INSERT INTO crypto_category (category_name) VALUES ('Stablecoin');
INSERT INTO crypto_category (category_name) VALUES ('Utility');
INSERT INTO crypto_category (category_name) VALUES ('Payment');
INSERT INTO crypto_category (category_name) VALUES ('Meme Coin');

-- Insert data into crypto (Assign category_id to match crypto_category entries)
INSERT INTO crypto (ranking, name_e, symbol, price, one_hour_change, twenty_four_hour_change, market_cap, volume, category_id) VALUES
(1, 'Bitcoin', 'BTC', 58757.01, 0.8, 5.1, 479140000, 1160743, 2);

INSERT INTO crypto (ranking, name_e, symbol, price, one_hour_change, twenty_four_hour_change, market_cap, volume, category_id) VALUES
(2, 'Ethereum', 'ETH', 2507.13, 0.4, 2.9, 234630000, 301541, 2);

INSERT INTO crypto (ranking, name_e, symbol, price, one_hour_change, twenty_four_hour_change, market_cap, volume, category_id) VALUES
(3, 'Tether', 'USDT', 0.9999, 0.1, 0.0, 669630000, 118060, 1);

INSERT INTO crypto (ranking, name_e, symbol, price, one_hour_change, twenty_four_hour_change, market_cap, volume, category_id) VALUES
(4, 'BNB', 'BNB', 529.93, 0.9, 3.4, 122170000, 774673, 2);

INSERT INTO crypto (ranking, name_e, symbol, price, one_hour_change, twenty_four_hour_change, market_cap, volume, category_id) VALUES
(5, 'Solana', 'SOL', 142.32, 0.2, 7.9, 41700000, 662857, 3);

INSERT INTO crypto (ranking, name_e, symbol, price, one_hour_change, twenty_four_hour_change, market_cap, volume, category_id) VALUES
(6, 'USDC', 'USDC', 0.9999, 0.1, 0.0, 102390000, 344325, 1);

INSERT INTO crypto (ranking, name_e, symbol, price, one_hour_change, twenty_four_hour_change, market_cap, volume, category_id) VALUES
(7, 'XRP', 'XRP', 0.5682, 0.5, 4.6, 188630000, 319617, 3);

INSERT INTO crypto (ranking, name_e, symbol, price, one_hour_change, twenty_four_hour_change, market_cap, volume, category_id) VALUES
(8, 'Dogecoin', 'DOGE', 0.09879, 1.1, 4.7, 79002000, 143875, 4);

INSERT INTO crypto (ranking, name_e, symbol, price, one_hour_change, twenty_four_hour_change, market_cap, volume, category_id) VALUES
(9, 'Polkadot', 'DOT', 4.24, 1.2, 6.8, 220840000, 595964, 2);

INSERT INTO crypto (ranking, name_e, symbol, price, one_hour_change, twenty_four_hour_change, market_cap, volume, category_id) VALUES
(10, 'Chainlink', 'LINK', 11.27, 0.9, 2.3, 432560000, 687525, 2);
