CREATE TABLE IF NOT EXISTS farmhacker_user (
    username VARCHAR(25) NOT NULL,
    password VARCHAR(100) NOT NULL, -- This will need to be encrypted
    role VARCHAR(25),
    PRIMARY KEY (username)
);

-- Simple table represents the entire product catalog.
CREATE TABLE IF NOT EXISTS catalog (
    id INT NOT NULL AUTO_INCREMENT,
    category VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    retail_price FLOAT(6,2) DEFAULT 0.00 NOT NULL,
    wholesale_price FLOAT(6,2) DEFAULT 0.00 NOT NULL,
    PRIMARY KEY (id)
);

-- Probably a useful view to have. Could create a separate category table but
-- not sure if we want to go nuts with normalization just yet. Don't think it's
-- a good idea just yet to allow people to enter categories without associated
-- products. Thoughts?
CREATE VIEW catalog_category_vw AS
SELECT DISTINCT category FROM catalog;

-- Primary inventory table. We should almost certainly put an index on this thing.
CREATE TABLE IF NOT EXISTS inventory (
	id INT NOT NULL AUTO_INCREMENT,
	catalog_id INT,
	measure SMALLINT DEFAULT 0 NOT NULL,
    time_entered DATETIME NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (catalog_id) REFERENCES catalog(id) ON DELETE CASCADE
);

-- Presents the value of the inventory across categories and pricing levels.
CREATE VIEW inventory_value AS
SELECT catalog.category, catalog.name, SUM(inventory.measure),
       SUM(catalog.retail_price) AS retail_value, SUM(catalog.wholesale_price) AS wholesale_value
FROM inventory INNER JOIN catalog ON inventory.catalog_id = catalog.id
GROUP BY catalog.category, catalog.name;

