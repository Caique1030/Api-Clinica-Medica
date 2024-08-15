ALTER TABLE medicos
ADD COLUMN ativo BOOLEAN;

-- Atualiza todos os valores nulos para 'true'
UPDATE medicos
SET ativo = TRUE
WHERE ativo IS NULL;

-- Define a coluna como NOT NULL
ALTER TABLE medicos
ALTER COLUMN ativo SET NOT NULL;