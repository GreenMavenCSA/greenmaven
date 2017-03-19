ALTER TABLE inventory MODIFY measure FLOAT(8,4) DEFAULT 0.0000 NOT NULL;

ALTER TABLE inventory ADD COLUMN inventory_code VARCHAR(4) DEFAULT NULL;

CREATE VIEW catalog_inventory_vw AS (
	SELECT catalog.id, catalog.category, catalog.name, catalog.retail_price, catalog.wholesale_price, COUNT(inventory.id) AS units_available
	FROM catalog LEFT OUTER JOIN inventory ON catalog.id = inventory.catalog_id
	GROUP BY catalog.id
);