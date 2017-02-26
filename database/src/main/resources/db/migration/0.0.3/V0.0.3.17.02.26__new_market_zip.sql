DELETE FROM market_zip WHERE postal_code NOT IN (22092,22901,22911,22903,22936,22940,22968,22947);

INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22092);
INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22901);
INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22911);
INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22903);
INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22936);
INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22940);
INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22968);
INSERT INTO market_zip (market_id, postal_code) VALUES ((SELECT id FROM market WHERE market_name = 'Charlottesville'), 22947);

