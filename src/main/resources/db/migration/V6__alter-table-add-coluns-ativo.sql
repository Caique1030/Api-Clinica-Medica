ALTER TABLE pacientes
ADD COLUMN ativo BOOLEAN;

-- Atualiza todos os valores nulos para 'true'
UPDATE pacientes
SET ativo = TRUE
WHERE ativo IS NULL;

-- Define a coluna como NOT NULL
ALTER TABLE pacientes
ALTER COLUMN ativo SET NOT NULL;