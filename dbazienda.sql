-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Lug 06, 2022 alle 13:14
-- Versione del server: 10.4.24-MariaDB
-- Versione PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbazienda`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `comprende`
--

CREATE TABLE `comprende` (
  `ID` int(10) UNSIGNED NOT NULL,
  `ID_O` int(10) UNSIGNED NOT NULL,
  `ID_F` int(10) UNSIGNED NOT NULL,
  `Quantita_O` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `comprende`
--

INSERT INTO `comprende` (`ID`, `ID_O`, `ID_F`, `Quantita_O`) VALUES
(167, 73, 20, 100),
(168, 73, 31, 50),
(169, 73, 36, 30),
(170, 74, 33, 30),
(171, 74, 16, 60),
(172, 75, 9, 25),
(173, 75, 29, 40),
(174, 75, 34, 40),
(175, 76, 10, 40),
(176, 76, 31, 50),
(177, 76, 2, 70),
(178, 77, 29, 80),
(179, 77, 7, 70),
(180, 77, 35, 60),
(181, 78, 31, 100),
(182, 78, 10, 500),
(183, 78, 36, 30),
(184, 79, 29, 60),
(185, 79, 34, 70),
(186, 79, 9, 70),
(187, 80, 33, 66),
(188, 80, 31, 80);

-- --------------------------------------------------------

--
-- Struttura della tabella `comprendeperiodico`
--

CREATE TABLE `comprendeperiodico` (
  `ID` int(10) UNSIGNED NOT NULL,
  `ID_O` int(10) UNSIGNED NOT NULL,
  `ID_F` int(10) UNSIGNED NOT NULL,
  `Quantita_O` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `comprendeperiodico`
--

INSERT INTO `comprendeperiodico` (`ID`, `ID_O`, `ID_F`, `Quantita_O`) VALUES
(6, 4, 7, 100),
(7, 5, 1, 500),
(8, 6, 10, 50),
(9, 7, 28, 75),
(10, 8, 30, 120),
(11, 9, 36, 250);

-- --------------------------------------------------------

--
-- Struttura della tabella `farmaco`
--

CREATE TABLE `farmaco` (
  `ID_F` int(10) UNSIGNED NOT NULL,
  `Nome_F` varchar(255) NOT NULL,
  `Principio_Attivo` varchar(255) NOT NULL,
  `Scadenza` date NOT NULL,
  `Da_Banco` tinyint(1) NOT NULL,
  `Quantita` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `farmaco`
--

INSERT INTO `farmaco` (`ID_F`, `Nome_F`, `Principio_Attivo`, `Scadenza`, `Da_Banco`, `Quantita`) VALUES
(1, 'Tachipirina', 'Paracetamolo', '2022-08-01', 1, 1900),
(2, 'Xanax', 'Alprazolam', '2022-09-01', 0, 1900),
(5, 'Maalox', 'magnesio idrossido + algeldrato', '2022-09-01', 0, 1900),
(7, 'Vagisil', 'Lidocaina', '2022-10-01', 1, 1830),
(9, 'Seki', 'Cloperastina fendizoato', '2026-06-01', 0, 1875),
(10, 'Palexia', 'tapentadolo (cloridrato)', '2022-07-01', 1, 1900),
(16, 'Qarziba', 'dinutuximab beta', '2023-07-01', 1, 1840),
(20, 'Gabanex', 'gabapentin', '2023-02-01', 1, 1900),
(28, 'Abasaglar', 'insulina glargine', '2024-06-01', 1, 1900),
(29, 'Bactocin', 'actobacillus plantarum', '2024-06-01', 0, 1780),
(30, 'Cabaser ', 'cabergolina', '2023-06-01', 1, 1900),
(31, 'Dacepton', 'apomorfina cloridrato', '2022-06-01', 0, 1820),
(33, 'Fabrazyme', 'agalsidasi beta', '2022-11-01', 0, 1804),
(34, 'Galaxia', 'latanoprost', '2023-07-01', 1, 1860),
(35, 'Ganfort', 'bimatoprost + timololo', '2023-05-01', 0, 1840),
(36, 'Rabesat ', 'irbesartan', '2022-12-01', 1, 1900),
(37, 'Rapamune', 'sirolimus', '2023-10-01', 0, 1900);

-- --------------------------------------------------------

--
-- Struttura della tabella `impiegatoazienda`
--

CREATE TABLE `impiegatoazienda` (
  `ID_I` int(10) UNSIGNED NOT NULL,
  `Nome_I` varchar(255) NOT NULL,
  `Cognome_I` varchar(255) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Ruolo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `impiegatoazienda`
--

INSERT INTO `impiegatoazienda` (`ID_I`, `Nome_I`, `Cognome_I`, `Username`, `Password`, `Ruolo`) VALUES
(1, 'Francesco', 'Camarda', 'addetto.imp', 'password', 'Addetto Azienda'),
(2, 'Gabriele', 'Zito', 'corriere.imp', 'password', 'corriere');

-- --------------------------------------------------------

--
-- Struttura della tabella `ordine`
--

CREATE TABLE `ordine` (
  `ID_O` int(10) UNSIGNED NOT NULL,
  `DataDiConsegna` date NOT NULL,
  `Indirizzo` varchar(255) NOT NULL,
  `Stato_O` varchar(255) NOT NULL,
  `ID_S` int(10) UNSIGNED DEFAULT NULL,
  `ID_A` int(10) UNSIGNED DEFAULT NULL,
  `Firma` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `ordine`
--

INSERT INTO `ordine` (`ID_O`, `DataDiConsegna`, `Indirizzo`, `Stato_O`, `ID_S`, `ID_A`, `Firma`) VALUES
(73, '2022-07-07', 'Via Brunelleschi, 64', 'In Consegna', 27, NULL, NULL),
(74, '2022-07-08', 'Via Pitrè, 33', 'In Consegna', NULL, NULL, NULL),
(75, '2022-07-07', 'Via Pitrè, 33', 'In Consegna', NULL, NULL, NULL),
(76, '2022-07-20', 'Via Pitrè, 33', 'In Lavorazione', NULL, NULL, NULL),
(77, '2022-07-08', 'Via Brunelleschi, 64', 'In Consegna', 29, NULL, NULL),
(78, '2022-07-20', 'Via Brunelleschi, 64', 'In Lavorazione', 28, NULL, NULL),
(79, '2022-07-20', 'Via Brunelleschi, 64', 'In Lavorazione', NULL, NULL, NULL),
(80, '2022-07-07', 'Via Brunelleschi, 64', 'In Consegna', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `ordineperiodico`
--

CREATE TABLE `ordineperiodico` (
  `ID_O` int(10) UNSIGNED NOT NULL,
  `Indirizzo` varchar(255) NOT NULL,
  `Periodicita` int(10) UNSIGNED NOT NULL,
  `DataUltimoOrdine` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `ordineperiodico`
--

INSERT INTO `ordineperiodico` (`ID_O`, `Indirizzo`, `Periodicita`, `DataUltimoOrdine`) VALUES
(4, 'Via Pitrè, 33', 2, '2022-07-01'),
(5, 'Via Pitrè, 33', 3, '2022-07-03'),
(6, 'Via Pitrè, 33', 1, '2022-06-29'),
(7, 'Via Brunelleschi, 64', 3, '2022-07-01'),
(8, 'Via Brunelleschi, 64', 3, '2022-07-04'),
(9, 'Via Brunelleschi, 64', 1, '2022-07-05');

-- --------------------------------------------------------

--
-- Struttura della tabella `segnalazione`
--

CREATE TABLE `segnalazione` (
  `ID_S` int(10) UNSIGNED NOT NULL,
  `Descrizione` text NOT NULL,
  `Stato_S` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `segnalazione`
--

INSERT INTO `segnalazione` (`ID_S`, `Descrizione`, `Stato_S`) VALUES
(25, 'sss', 'Chiusa'),
(26, '62', 'Chiusa'),
(27, 'Farmaci mancanti', 'Aperta'),
(28, 'Farmaci non arrivati', 'Aperta'),
(29, 'Farmaci sbagliati', 'Aperta');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `comprende`
--
ALTER TABLE `comprende`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_F` (`ID_F`),
  ADD KEY `ID_O` (`ID_O`);

--
-- Indici per le tabelle `comprendeperiodico`
--
ALTER TABLE `comprendeperiodico`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_F` (`ID_F`),
  ADD KEY `comprendeperiodico_ibfk_2` (`ID_O`);

--
-- Indici per le tabelle `farmaco`
--
ALTER TABLE `farmaco`
  ADD PRIMARY KEY (`ID_F`);

--
-- Indici per le tabelle `impiegatoazienda`
--
ALTER TABLE `impiegatoazienda`
  ADD PRIMARY KEY (`ID_I`);

--
-- Indici per le tabelle `ordine`
--
ALTER TABLE `ordine`
  ADD PRIMARY KEY (`ID_O`),
  ADD KEY `ID_S` (`ID_S`),
  ADD KEY `ID_A` (`ID_A`);

--
-- Indici per le tabelle `ordineperiodico`
--
ALTER TABLE `ordineperiodico`
  ADD PRIMARY KEY (`ID_O`);

--
-- Indici per le tabelle `segnalazione`
--
ALTER TABLE `segnalazione`
  ADD PRIMARY KEY (`ID_S`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `comprende`
--
ALTER TABLE `comprende`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=189;

--
-- AUTO_INCREMENT per la tabella `comprendeperiodico`
--
ALTER TABLE `comprendeperiodico`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT per la tabella `farmaco`
--
ALTER TABLE `farmaco`
  MODIFY `ID_F` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT per la tabella `impiegatoazienda`
--
ALTER TABLE `impiegatoazienda`
  MODIFY `ID_I` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT per la tabella `ordine`
--
ALTER TABLE `ordine`
  MODIFY `ID_O` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT per la tabella `ordineperiodico`
--
ALTER TABLE `ordineperiodico`
  MODIFY `ID_O` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT per la tabella `segnalazione`
--
ALTER TABLE `segnalazione`
  MODIFY `ID_S` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `comprende`
--
ALTER TABLE `comprende`
  ADD CONSTRAINT `comprende_ibfk_1` FOREIGN KEY (`ID_F`) REFERENCES `farmaco` (`ID_F`),
  ADD CONSTRAINT `comprende_ibfk_2` FOREIGN KEY (`ID_O`) REFERENCES `ordine` (`ID_O`);

--
-- Limiti per la tabella `comprendeperiodico`
--
ALTER TABLE `comprendeperiodico`
  ADD CONSTRAINT `comprendeperiodico_ibfk_1` FOREIGN KEY (`ID_F`) REFERENCES `farmaco` (`ID_F`),
  ADD CONSTRAINT `comprendeperiodico_ibfk_2` FOREIGN KEY (`ID_O`) REFERENCES `ordineperiodico` (`ID_O`);

--
-- Limiti per la tabella `ordine`
--
ALTER TABLE `ordine`
  ADD CONSTRAINT `ordine_ibfk_1` FOREIGN KEY (`ID_S`) REFERENCES `segnalazione` (`ID_S`),
  ADD CONSTRAINT `ordine_ibfk_2` FOREIGN KEY (`ID_A`) REFERENCES `impiegatoazienda` (`ID_I`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
