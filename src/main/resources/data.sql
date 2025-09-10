-- ==========================
-- 1. Services
-- ==========================
INSERT INTO services (id, name, description) VALUES

(1,  'Vidange + Filtre à Huile',
     'Vidange moteur (huile conforme constructeur), remplacement du filtre à huile et du joint de bouchon, contrôle d''étanchéité et des niveaux, remise à zéro de l''indicateur de maintenance, contrôle visuel (durites, fuites, courroies accessoires).'),

(2,  'Pneumatiques',
     'Démontage/remontage pneus, remplacement de valve si nécessaire, équilibrage des roues, contrôle de l''usure et des pressions, resserrage au couple, réinitialisation TPMS le cas échéant.'),

(3,  'Freins',
     'Contrôle épaisseur disques/plaquettes, remplacement si hors tolérance, nettoyage des portées et moyeux, graissage des coulisseaux (hors réfection d’étrier), purge et remplacement du liquide de frein selon préconisations, essai routier.'),

(4,  'Batterie',
     'Test de capacité batterie et de la charge alternateur, remplacement par une batterie équivalente, enregistrement BMS si applicable, nettoyage/serrage des cosses, contrôle de consommation parasite de base.'),

(5,  'Distribution',
     'Remplacement kit distribution (courroie/chaîne selon modèle) avec galets et, si prévu, pompe à eau; calage et serrages au couple; remplissage/purge du liquide de refroidissement; contrôles finaux et essai. Hors pièces ou déposes complémentaires non prévues.'),

(6,  'Amortisseurs',
     'Remplacement amortisseurs avant/arrière, contrôle et remplacement des butées/coupelles si besoin, serrages aux couples constructeur, vérification soufflets et biellettes; recommandation d''une géométrie après intervention.'),

(7,  'Climatisation',
     'Contrôle d''étanchéité, tirage au vide, recharge de gaz (R134a/R1234yf selon véhicule), ajout de traceur UV, test de performance (température soufflage), désinfection de l''habitacle en option.'),

(8,  'Diagnostic électronique',
     'Lecture des codes défauts via valise multimarque, effacement si pertinent, mesures de paramètres en temps réel, capture d''écrans/rapport synthèse, recommandations de tests complémentaires si nécessaire.'),

(9,  'Géométrie / Parallélisme',
     'Mesure 3D des angles de train(s), réglage du pincement/carrossage/chasse si réglables, centrage volant, impression d''un rapport avant/après; contrôle jeu de direction et silent-blocs.'),

(10, 'Filtres air & habitacle',
     'Remplacement du filtre à air moteur et du filtre d''habitacle (charbon actif possible), nettoyage du logement, contrôle du conduit d''admission, vérification des débits et de l''aspiration anormale.'),

(11, 'Bougies / Préchauffage',
     'Remplacement bougies d''allumage (essence) ou de préchauffage (diesel), contrôle bobines/antiparasites ou relais de préchauffage, respect du couple de serrage constructeur, effacement des voyants si présent.'),

(12, 'Embrayage',
     'Remplacement kit embrayage (mécanisme, disque, butée), purge hydraulique si nécessaire, contrôle du volant moteur, serrages au couple, essai routier et réglage du jeu de pédale si applicable (hors volant moteur si non prévu).');



-- ==========================
-- 2. Garages
-- ==========================
INSERT INTO garage (id, name, siren, numero_voie, type_voie, libelle_voie, code_postal, libelle_commune, code_commune, quantity_technicians, longitude, latitude)
VALUES
(1, 'Garage Dacia Tours', '741852963', '22', 'BD', 'Béranger', '37000', 'Tours', '37261', 8, 0.688, 47.394),
(2, 'Garage Renault Lyon', '741852964', '10', 'RUE', 'Victor Hugo', '69002', 'Lyon', '69382', 12, 4.835, 45.764),
(3, 'Peugeot Marseille', '741852965', '5', 'AV', 'Prado', '13008', 'Marseille', '13208', 15, 5.387, 43.270),
(4, 'Citroën Bordeaux', '741852966', '18', 'RUE', 'Sainte-Catherine', '33000', 'Bordeaux', '33063', 10, -0.579, 44.837),
(5, 'Garage Opel Paris', '741852967', '50', 'BD', 'Haussmann', '75009', 'Paris', '75056', 20, 2.332, 48.876),
(6, 'Garage Nissan Lille', '741852968', '12', 'RUE', 'Faidherbe', '59000', 'Lille', '59350', 7, 3.057, 50.629),
(7, 'Toyota Nice', '741852969', '2', 'AV', 'Jean Médecin', '06000', 'Nice', '06088', 14, 7.265, 43.700),
(8, 'Kia Nantes', '741852970', '30', 'RUE', 'Crébillon', '44000', 'Nantes', '44109', 9, -1.553, 47.218),
(9, 'Fiat Strasbourg', '741852971', '8', 'RUE', 'des Grandes Arcades', '67000', 'Strasbourg', '67482', 11, 7.750, 48.573),
(10, 'Hyundai Toulouse', '741852972', '16', 'RUE', 'd''Alsace-Lorraine', '31000', 'Toulouse', '31555', 13, 1.444, 43.604);

-- ==========================
-- 3. Users (password: Azerty12&)
-- ==========================
INSERT INTO user (id, username, name, email, password, phone, role, garage_id) VALUES
(1, 'jdubois', 'Jean Dubois', 'jean.dubois@example.com', '$2a$10$Dow1kTr4a0QDD7xH.uZ8V.SSfxC8pnvNUnzZcCkBt/j0T1YFFxC1S', '0600000001', 'ENGINEER', 1),
(2, 'mpetit', 'Marie Petit', 'marie.petit@example.com', '$2a$10$Dow1kTr4a0QDD7xH.uZ8V.SSfxC8pnvNUnzZcCkBt/j0T1YFFxC1S', '0600000002', 'ENGINEER', 2),
(3, 'tbernard', 'Thomas Bernard', 'thomas.bernard@example.com', '$2a$10$Dow1kTr4a0QDD7xH.uZ8V.SSfxC8pnvNUnzZcCkBt/j0T1YFFxC1S', '0600000003', 'ENGINEER', 3),
(4, 'sroux', 'Sophie Roux', 'sophie.roux@example.com', '$2a$10$Dow1kTr4a0QDD7xH.uZ8V.SSfxC8pnvNUnzZcCkBt/j0T1YFFxC1S', '0600000004', 'ENGINEER', 4),
(5, 'pdupont', 'Paul Dupont', 'paul.dupont@example.com', '$2a$10$Dow1kTr4a0QDD7xH.uZ8V.SSfxC8pnvNUnzZcCkBt/j0T1YFFxC1S', '0600000005', 'ENGINEER', 5),
(6, 'ccarrier', 'Claire Carrier', 'claire.carrier@example.com', '$2a$10$Dow1kTr4a0QDD7xH.uZ8V.SSfxC8pnvNUnzZcCkBt/j0T1YFFxC1S', '0600000006', 'ENGINEER', 6),
(7, 'flaurent', 'François Laurent', 'francois.laurent@example.com', '$2a$10$Dow1kTr4a0QDD7xH.uZ8V.SSfxC8pnvNUnzZcCkBt/j0T1YFFxC1S', '0600000007', 'ENGINEER', 7),
(8, 'abelin', 'Alice Belin', 'alice.belin@example.com', '$2a$10$Dow1kTr4a0QDD7xH.uZ8V.SSfxC8pnvNUnzZcCkBt/j0T1YFFxC1S', '0600000008', 'ENGINEER', 8),
(9, 'mchevalier', 'Marc Chevalier', 'marc.chevalier@example.com', '$2a$10$Dow1kTr4a0QDD7xH.uZ8V.SSfxC8pnvNUnzZcCkBt/j0T1YFFxC1S', '0600000009', 'ENGINEER', 9),
(10, 'jmartin', 'Julie Martin', 'julie.martin@example.com', '$2a$10$Dow1kTr4a0QDD7xH.uZ8V.SSfxC8pnvNUnzZcCkBt/j0T1YFFxC1S', '0600000010', 'ENGINEER', 10);

-- ==========================
-- 4. Garage ↔ Services
-- ==========================
INSERT INTO garage_services (garage_id, services_id) VALUES
(1,1),(1,2),(1,7),
(2,2),(2,3),(2,4),
(3,1),(3,3),(3,5),
(4,1),(4,6),(4,7),
(5,2),(5,3),(5,5),(5,6),
(6,1),(6,4),(6,7),
(7,2),(7,3),(7,6),
(8,1),(8,5),(8,7),
(9,3),(9,4),(9,6),
(10,1),(10,2),(10,3),(10,7);

-- ==========================
-- 5. APPOINTMENTS
-- ==========================
INSERT INTO appointment (id, garage_id, customer_id, technician_id, start_date, end_date, totale_price, comment) VALUES
(1, 1, 3, 2, '2025-09-15 09:00:00', '2025-09-15 10:00:00', 89.99,  'Vidange + contrôle général'),
(2, 2, 4, 2, '2025-09-16 14:30:00', '2025-09-16 15:30:00', 199.00, 'Remplacement plaquettes avant'),
(3, 3, 5, 1, '2025-09-17 08:30:00', '2025-09-17 09:15:00', 59.00,  'Diagnostic électronique'),
(4, 4, 6, 7, '2025-09-18 11:00:00', '2025-09-18 12:30:00', 320.00, 'Courroie + pompe à eau (distribution)'),
(5, 5, 7, 8, '2025-09-19 10:00:00', '2025-09-19 11:00:00', 120.50, 'Test et remplacement batterie');

-- ==========================
-- 6. APPOINTMENT ↔ SERVICES (liaison N-N)
--    (nom attendu par JPA par défaut : appointment_services)
-- ==========================
INSERT INTO appointment_services (appointment_id, services_id) VALUES
(1, 1),  -- vidange
(1, 7),  -- diagnostic
(2, 3),  -- freins / pneumatiques (ici freins)
(3, 7),  -- diagnostic
(4, 5),  -- distribution
(4, 6),  -- batterie (si on veut joindre plusieurs services à même RDV)
(5, 6);  -- batterie


-- Associer un engineer à chaque garage
UPDATE garage SET user_id = 1 WHERE id = 1;
UPDATE garage SET user_id = 2 WHERE id = 2;
UPDATE garage SET user_id = 3 WHERE id = 3;
UPDATE garage SET user_id = 4 WHERE id = 4;
UPDATE garage SET user_id = 5 WHERE id = 5;
UPDATE garage SET user_id = 6 WHERE id = 6;
UPDATE garage SET user_id = 7 WHERE id = 7;
UPDATE garage SET user_id = 8 WHERE id = 8;
UPDATE garage SET user_id = 9 WHERE id = 9;
UPDATE garage SET user_id = 10 WHERE id = 10;