-- =======================
-- SERVICES
-- =======================
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


-- =======================
-- GARAGES (10 Tours + 10 Ligueil)
-- =======================
INSERT INTO garage (id, name, siren, numero_voie, type_voie, libelle_voie, code_postal, libelle_commune, code_commune, quantity_technicians, longitude, latitude)
VALUES
-- Tours
(1, 'Garage Dacia Tours', '741852963', '22', 'BD', 'BERANGER', '37000', 'TOURS', '37261', 8, 0.688, 47.394),
(2, 'Garage Renault Tours Nord', '951753852', '10', 'AVENUE', 'DE L EUROPE', '37100', 'TOURS', '37261', 6, 0.70, 47.398),
(3, 'Garage Peugeot Tours Sud', '258963147', '5', 'ROUTE', 'DE CHINON', '37200', 'TOURS', '37261', 7, 0.675, 47.385),
(4, 'Garage Citroën Tours Centre', '369258147', '7', 'RUE', 'NATIONALE', '37000', 'TOURS', '37261', 9, 0.682, 47.390),
(5, 'Garage Opel Tours', '159753486', '14', 'PLACE', 'JEAN JAURÈS', '37000', 'TOURS', '37261', 4, 0.695, 47.392),
(6, 'Garage BMW Tours', '753159852', '8', 'RUE', 'DES MINIMES', '37000', 'TOURS', '37261', 10, 0.678, 47.389),
(7, 'Garage Mercedes Tours', '456123789', '11', 'RUE', 'LAMARTINE', '37000', 'TOURS', '37261', 12, 0.685, 47.396),
(8, 'Garage Ford Tours', '852456963', '9', 'RUE', 'DES HALLES', '37000', 'TOURS', '37261', 5, 0.692, 47.388),
(9, 'Garage Toyota Tours', '147258369', '15', 'AVENUE', 'DE GRAMMONT', '37000', 'TOURS', '37261', 6, 0.689, 47.395),
(10, 'Garage Kia Tours', '963852741', '6', 'BD', 'HEURTELOUP', '37000', 'TOURS', '37261', 3, 0.684, 47.387),

-- Ligueil
(11, 'Garage Barrault', '415165158', '12', 'RUE', 'DE DESCARTES', '37240', 'LIGUEIL', '37130', 5, 0.795, 47.027),
(12, 'Garage Renault Ligueil', '523489652', '8', 'AVENUE', 'DU 8 MAI', '37240', 'LIGUEIL', '37130', 4, 0.800, 47.030),
(13, 'Garage Peugeot Ligueil', '874596321', '5', 'ROUTE', 'DE LOCHES', '37240', 'LIGUEIL', '37130', 6, 0.790, 47.025),
(14, 'Garage Citroën Ligueil', '963852963', '3', 'RUE', 'DES ECOLES', '37240', 'LIGUEIL', '37130', 7, 0.805, 47.029),
(15, 'Garage Opel Ligueil', '159753486', '6', 'PLACE', 'DE L EGLISE', '37240', 'LIGUEIL', '37130', 3, 0.793, 47.028),
(16, 'Garage BMW Ligueil', '753951852', '9', 'RUE', 'DE TOURS', '37240', 'LIGUEIL', '37130', 9, 0.797, 47.032),
(17, 'Garage Mercedes Ligueil', '456852147', '7', 'RUE', 'DU COMMERCE', '37240', 'LIGUEIL', '37130', 8, 0.799, 47.026),
(18, 'Garage Ford Ligueil', '852369147', '4', 'AVENUE', 'DES SPORTS', '37240', 'LIGUEIL', '37130', 4, 0.802, 47.031),
(19, 'Garage Toyota Ligueil', '147369258', '10', 'RUE', 'DU STADE', '37240', 'LIGUEIL', '37130', 5, 0.794, 47.024),
(20, 'Garage Kia Ligueil', '963741852', '11', 'BD', 'DE LA GARE', '37240', 'LIGUEIL', '37130', 6, 0.796, 47.033);

-- =======================
-- USERS (liés aux garages)
-- =======================
INSERT INTO user (id, username, name, email, password, phone, role, garage_id) VALUES
(1, 'jdupont', 'Jean Dupont', 'jean.dupont@example.com', 'password', '0600000001', 'ENGINEER', 1),
(2, 'mbernard', 'Marie Bernard', 'marie.bernard@example.com', 'password', '0600000002', 'TECHNICIAN', 1),
(3, 'cpetit', 'Claude Petit', 'claude.petit@example.com', 'password', '0600000003', 'CUSTOMER', 2),
(4, 'pduval', 'Paul Duval', 'paul.duval@example.com', 'password', '0600000004', 'TECHNICIAN', 2),
(5, 'sroger', 'Sophie Roger', 'sophie.roger@example.com', 'password', '0600000005', 'ENGINEER', 3),
(6, 'lmartin', 'Luc Martin', 'luc.martin@example.com', 'password', '0600000006', 'TECHNICIAN', 11),
(7, 'mleclerc', 'Marc Leclerc', 'marc.leclerc@example.com', 'password', '0600000007', 'CUSTOMER', 12),
(8, 'fblanc', 'François Blanc', 'francois.blanc@example.com', 'password', '0600000008', 'ENGINEER', 13),
(9, 'epierre', 'Emma Pierre', 'emma.pierre@example.com', 'password', '0600000009', 'TECHNICIAN', 14),
(10, 'aroux', 'Antoine Roux', 'antoine.roux@example.com', 'password', '0600000010', 'CUSTOMER', 15);

-- =======================
-- GARAGE ↔ SERVICES (liaison N-N)
-- =======================
INSERT INTO garage_services (garage_id, services_id) VALUES
(1,1),(1,2),(1,3),
(2,1),(2,4),
(3,2),(3,5),(3,6),
(4,1),(4,7),
(5,3),(5,4),(5,5),
(6,2),(6,6),
(7,1),(7,3),(7,5),
(8,2),(8,4),(8,7),
(9,1),(9,6),
(10,3),(10,7),
(11,1),(11,2),
(12,3),(12,4),(12,5),
(13,2),(13,6),
(14,1),(14,7),
(15,2),(15,3),
(16,4),(16,5),(16,6),
(17,1),(17,3),(17,7),
(18,2),(18,4),
(19,5),(19,6),
(20,1),(20,2),(20,7);



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