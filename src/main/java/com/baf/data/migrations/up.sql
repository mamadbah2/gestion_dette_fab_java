-- Fichier de migration pour PostgreSQL

-- Création de la table des utilisateurs
CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    login VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL, -- Par exemple: "Admin", "Boutiquier", "Client"
    is_active BOOLEAN DEFAULT TRUE
);

-- Création de la table des clients
CREATE TABLE Client (
    id SERIAL PRIMARY KEY,
    surname VARCHAR(255) NOT NULL,
    telephone VARCHAR(20) NOT NULL UNIQUE,
    address TEXT NOT NULL,
    date DATE NOT NULL DEFAULT CURRENT_DATE,
    user_id INT UNIQUE, -- Lien vers un utilisateur
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE SET NULL
);

-- Création de la table des articles
CREATE TABLE Article (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(255) NOT NULL,
    qte_stock INT NOT NULL DEFAULT 0,
    prix NUMERIC(10, 2) NOT NULL,
    is_archived BOOLEAN DEFAULT FALSE
);

-- Création de la table des dettes
CREATE TABLE Debt (
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL DEFAULT CURRENT_DATE,
    mount NUMERIC(10, 2) NOT NULL,
    amount_paid NUMERIC(10, 2) DEFAULT 0,
    remaining_amount NUMERIC(10, 2) NOT NULL,
    is_achievied BOOLEAN DEFAULT FALSE,
    client_id INT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES Client(id) ON DELETE CASCADE
);

-- Création de la table des détails de dettes
CREATE TABLE DetailDebt (
    id SERIAL PRIMARY KEY,
    quantity INT NOT NULL,
    prix NUMERIC(10, 2) NOT NULL,
    article_id INT NOT NULL,
    debt_id INT NOT NULL,
    FOREIGN KEY (debt_id) REFERENCES Debt(id) ON DELETE CASCADE,
    FOREIGN KEY (article_id) REFERENCES Article(id) ON DELETE CASCADE
);

-- Création de la table des paiements
CREATE TABLE Payment (
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL DEFAULT CURRENT_DATE,
    amount NUMERIC(10, 2) NOT NULL,
    debt_id INT NOT NULL,
    FOREIGN KEY (debt_id) REFERENCES Debt(id) ON DELETE CASCADE
);

-- Création de la table des demandes de dette
CREATE TABLE DebtRequest (
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL DEFAULT CURRENT_DATE,
    status VARCHAR(50) NOT NULL DEFAULT 'En Cours', -- "En Cours", "Annuler", "Valider"
    client_id INT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES Client(id) ON DELETE CASCADE
);

-- Création de la table des détails des demandes de dette
CREATE TABLE DetailDebtRequest (
    id SERIAL PRIMARY KEY,
    quantity INT NOT NULL,
    debt_request_id INT NOT NULL,
    article_id INT NOT NULL,
    FOREIGN KEY (debt_request_id) REFERENCES DebtRequest(id) ON DELETE CASCADE,
    FOREIGN KEY (article_id) REFERENCES Article(id) ON DELETE CASCADE
);

-- Contraintes supplémentaires pour garantir l'intégrité des données
ALTER TABLE Debt ADD CONSTRAINT chk_remaining_amount CHECK (remaining_amount >= 0);
ALTER TABLE Payment ADD CONSTRAINT chk_payment_amount CHECK (amount > 0);
ALTER TABLE DetailDebt ADD CONSTRAINT chk_quantity CHECK (quantity > 0);
ALTER TABLE DetailDebtRequest ADD CONSTRAINT chk_request_quantity CHECK (quantity > 0);
