-- Fichier de migration pour supprimer les tables PostgreSQL (down)

-- Suppression des tables dépendantes en premier
DROP TABLE IF EXISTS DetailDebtRequest CASCADE;
DROP TABLE IF EXISTS DebtRequest CASCADE;
DROP TABLE IF EXISTS Payment CASCADE;
DROP TABLE IF EXISTS DetailDebt CASCADE;
DROP TABLE IF EXISTS Debt CASCADE;
DROP TABLE IF EXISTS Article CASCADE;
DROP TABLE IF EXISTS Client CASCADE;
DROP TABLE IF EXISTS User CASCADE;
