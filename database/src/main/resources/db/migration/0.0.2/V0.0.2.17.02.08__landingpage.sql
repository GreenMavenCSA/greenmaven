ALTER TABLE farmhacker_user ADD COLUMN is_eater INT DEFAULT 0 NOT NULL;
ALTER TABLE farmhacker_user ADD COLUMN is_grower INT DEFAULT 0 NOT NULL;
ALTER TABLE farmhacker_user ADD COLUMN is_facebook_user INT DEFAULT 0 NOT NULL;
ALTER TABLE farmhacker_user ADD COLUMN zip INT NOT NULL;

-- Allow nulls for Facebook users; no password necessary
ALTER TABLE farmhacker_user MODIFY password VARCHAR(100) NULL;

CREATE TABLE IF NOT EXISTS market (
	id INT NOT NULL AUTO_INCREMENT,
	market_name VARCHAR(50) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS market_zip (
	id INT NOT NULL AUTO_INCREMENT,
	market_id INT NOT NULL REFERENCES market(id),
	postal_code INT NOT NULL,
	PRIMARY KEY (id)
);


INSERT INTO market (market_name) VALUES ('Charlottesville');

INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22968);
INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22901);
INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22902);
INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22903);
INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22904);
INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22905);
INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22906);
INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22907);
INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22908);
INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22909);
INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22910);
INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22935);
INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22936);
INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22940);

ALTER TABLE farmhacker_user ADD COLUMN market_id INT REFERENCES market(id);

