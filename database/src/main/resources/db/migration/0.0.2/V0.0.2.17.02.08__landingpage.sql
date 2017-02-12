ALTER TABLE farmhacker_user ADD COLUMN is_eater INT DEFAULT 0 NOT NULL;
ALTER TABLE farmhacker_user ADD COLUMN is_grower INT DEFAULT 0 NOT NULL;
ALTER TABLE farmhacker_user ADD COLUMN is_facebook_user INT DEFAULT 0 NOT NULL;
ALTER TABLE farmhacker_user ADD COLUMN zip INT NOT NULL;

-- Allow nulls for Facebook users; no password necessary
ALTER TABLE farmhacker_user MODIFY password VARCHAR(100) NULL;