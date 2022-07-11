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
-- Database: `dbfarmacia`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `farmacia`
--

CREATE TABLE `farmacia` (
  `ID_FARM` int(10) UNSIGNED NOT NULL,
  `Indirizzo` varchar(255) NOT NULL,
  `Nome` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `farmacia`
--

INSERT INTO `farmacia` (`ID_FARM`, `Indirizzo`, `Nome`) VALUES
(1, 'Via Pitrè, 33', 'Farmacia Di Benedetto'),
(3, 'Via Brunelleschi, 64', 'Impellizzeri and family');

-- --------------------------------------------------------

--
-- Struttura della tabella `farmacista`
--

CREATE TABLE `farmacista` (
  `ID_F` int(10) UNSIGNED NOT NULL,
  `Nome` varchar(255) NOT NULL,
  `Cognome` varchar(255) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `ID_FARM` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `farmacista`
--

INSERT INTO `farmacista` (`ID_F`, `Nome`, `Cognome`, `Username`, `Password`, `ID_FARM`) VALUES
(11, 'Andrea ', 'Impellizzeri', 'farmacista1.farm', 'password', 3),
(12, 'Silvio Christian', 'Benanti', 'farmacista2.farm', 'password', 1);

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
  `Quantita` int(10) UNSIGNED NOT NULL,
  `ID_FARM` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `farmaco`
--

INSERT INTO `farmaco` (`ID_F`, `Nome_F`, `Principio_Attivo`, `Scadenza`, `Da_Banco`, `Quantita`, `ID_FARM`) VALUES
(37, 'Tachipirina', 'Paracetamolo', '2023-09-01', 1, 1000, 3),
(38, 'Xanax', 'Alprazolam', '2023-01-01', 0, 250, 3),
(39, 'Vagisil', 'Lidocaina', '2022-12-01', 1, 100, 3),
(40, 'Dacepton', 'apomorfina cloridrato', '2022-10-01', 0, 60, 3),
(41, 'Bactocin', 'actobacillus plantarum', '2024-05-01', 0, 75, 3),
(42, 'Maalox', 'magnesio idrossido + algeldrato', '2022-09-01', 0, 50, 1),
(43, 'Qarziba', 'dinutuximab beta', '2022-12-01', 1, 100, 1),
(44, 'Seki', 'Cloperastina fendizoato', '2023-12-01', 0, 75, 1),
(45, 'Palexia', 'tapentadolo (cloridrato)', '2024-05-01', 1, 500, 1),
(46, 'Galaxia', 'latanoprost', '2024-01-01', 1, 150, 1);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `farmacia`
--
ALTER TABLE `farmacia`
  ADD PRIMARY KEY (`ID_FARM`);

--
-- Indici per le tabelle `farmacista`
--
ALTER TABLE `farmacista`
  ADD PRIMARY KEY (`ID_F`),
  ADD KEY `ID_FARM` (`ID_FARM`);

--
-- Indici per le tabelle `farmaco`
--
ALTER TABLE `farmaco`
  ADD PRIMARY KEY (`ID_F`),
  ADD KEY `ID_FARM` (`ID_FARM`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `farmacia`
--
ALTER TABLE `farmacia`
  MODIFY `ID_FARM` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT per la tabella `farmacista`
--
ALTER TABLE `farmacista`
  MODIFY `ID_F` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT per la tabella `farmaco`
--
ALTER TABLE `farmaco`
  MODIFY `ID_F` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `farmacista`
--
ALTER TABLE `farmacista`
  ADD CONSTRAINT `farmacista_ibfk_1` FOREIGN KEY (`ID_FARM`) REFERENCES `farmacia` (`ID_FARM`);

--
-- Limiti per la tabella `farmaco`
--
ALTER TABLE `farmaco`
  ADD CONSTRAINT `farmaco_ibfk_1` FOREIGN KEY (`ID_FARM`) REFERENCES `farmacia` (`ID_FARM`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
