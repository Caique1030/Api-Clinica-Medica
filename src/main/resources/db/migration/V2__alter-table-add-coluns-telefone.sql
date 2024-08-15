ALTER TABLE medicos
ADD COLUMN telefone VARCHAR(20);

UPDATE medicos
SET telefone = 'N/A'
WHERE telefone IS NULL;

ALTER TABLE medicos
ALTER COLUMN telefone SET NOT NULL;
