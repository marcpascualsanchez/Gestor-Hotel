-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Temps de generació: 28-04-2019 a les 23:38:16
-- Versió del servidor: 10.1.36-MariaDB
-- Versió de PHP: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de dades: `proyecto_hotel`
--

-- --------------------------------------------------------

--
-- Estructura de la taula `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `dni` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `nacionalidad` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `telefono` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `email` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ocupacion` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `estado_civil` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `id_usuario_creador` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Bolcament de dades per a la taula `cliente`
--

INSERT INTO `cliente` (`id`, `nombre`, `dni`, `nacionalidad`, `telefono`, `email`, `ocupacion`, `estado_civil`, `id_usuario_creador`) VALUES
(1, 'maria', '000A', 'cat', '666', 'mail@mail.com', 'sleeper', 'enamorada', 14),
(2, 'patri', '000Z', 'esp', '999', 'email@email.com', 'siestera', 'soltera', 14),
(8, 'nombre', 'dni', 'nacionalidad', 'telf', 'email', 'ocu', 'est', 14),
(9, 'cris', '969', 'cat', '6000', 'greest@gmail.com', 'artista', 'casada', 14),
(11, 'sergi', '777', 'cat', '0009', 'seru@mail.com', 'sonidista', 'casat', 14);

-- --------------------------------------------------------

--
-- Estructura de la taula `habitacion`
--

CREATE TABLE `habitacion` (
  `id` int(11) NOT NULL,
  `numero` int(11) DEFAULT NULL,
  `precio` float DEFAULT '0',
  `id_usuario_creador` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Bolcament de dades per a la taula `habitacion`
--

INSERT INTO `habitacion` (`id`, `numero`, `precio`, `id_usuario_creador`) VALUES
(1, 66, 99.99, 14),
(2, 13, 40, 12),
(3, 17, 99.5, 14),
(4, 90, 333, 14),
(5, 12, 25.8, 14),
(13, 111, 23, 14),
(16, 123, 123, 14);

-- --------------------------------------------------------

--
-- Estructura de la taula `hospedaje`
--

CREATE TABLE `hospedaje` (
  `id` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_habitacion` int(11) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_final` date NOT NULL,
  `id_usuario_creador` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Bolcament de dades per a la taula `hospedaje`
--

INSERT INTO `hospedaje` (`id`, `id_cliente`, `id_habitacion`, `fecha_inicio`, `fecha_final`, `id_usuario_creador`) VALUES
(1, 1, 1, '2019-04-17', '2020-04-20', 14),
(2, 2, 2, '2019-04-25', '2019-04-27', 13),
(3, 2, 13, '2019-04-10', '2019-04-18', 14),
(6, 2, 1, '2019-04-03', '2019-04-19', 14),
(7, 2, 2, '2019-04-12', '2019-04-13', 14),
(8, 11, 3, '2019-04-12', '2019-04-13', 14);

-- --------------------------------------------------------

--
-- Estructura de la taula `test`
--

CREATE TABLE `test` (
  `data` varchar(45) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Bolcament de dades per a la taula `test`
--

INSERT INTO `test` (`data`) VALUES
('data de prueba');

-- --------------------------------------------------------

--
-- Estructura de la taula `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `clave` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Bolcament de dades per a la taula `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `clave`, `admin`) VALUES
(12, 'sergi', '123', 0),
(13, 'cris', 'cris', 0),
(14, 'marc', 'admin', 1),
(22, 'mwi', 'mwia', 1),
(24, 'user', 'pass', 0);

--
-- Índexs per a les taules bolcades
--

--
-- Índexs per a la taula `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario_creador` (`id_usuario_creador`);

--
-- Índexs per a la taula `habitacion`
--
ALTER TABLE `habitacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario_creador` (`id_usuario_creador`);

--
-- Índexs per a la taula `hospedaje`
--
ALTER TABLE `hospedaje`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_habitacion` (`id_habitacion`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_usuario_creador` (`id_usuario_creador`);

--
-- Índexs per a la taula `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT per les taules bolcades
--

--
-- AUTO_INCREMENT per la taula `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT per la taula `habitacion`
--
ALTER TABLE `habitacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT per la taula `hospedaje`
--
ALTER TABLE `hospedaje`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT per la taula `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Restriccions per a les taules bolcades
--

--
-- Restriccions per a la taula `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`id_usuario_creador`) REFERENCES `usuario` (`id`);

--
-- Restriccions per a la taula `habitacion`
--
ALTER TABLE `habitacion`
  ADD CONSTRAINT `habitacion_ibfk_1` FOREIGN KEY (`id_usuario_creador`) REFERENCES `usuario` (`id`);

--
-- Restriccions per a la taula `hospedaje`
--
ALTER TABLE `hospedaje`
  ADD CONSTRAINT `hospedaje_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `hospedaje_ibfk_2` FOREIGN KEY (`id_habitacion`) REFERENCES `habitacion` (`id`),
  ADD CONSTRAINT `hospedaje_ibfk_3` FOREIGN KEY (`id_usuario_creador`) REFERENCES `usuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
