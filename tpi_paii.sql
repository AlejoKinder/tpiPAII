-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-03-2023 a las 15:09:47
-- Versión del servidor: 10.4.25-MariaDB
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tpi_paii`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `certificadopago`
--

CREATE TABLE `certificadopago` (
  `vIdCertificado` int(50) NOT NULL,
  `vCostoTotal` double NOT NULL,
  `vFechaEmision` varchar(50) NOT NULL,
  `vPagado` tinyint(1) NOT NULL,
  `vFoja` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `costo`
--

CREATE TABLE `costo` (
  `vIdCosto` int(50) NOT NULL,
  `vMonto` double NOT NULL,
  `vFechaInicioVigencia` varchar(50) NOT NULL,
  `vIdItem` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `costo`
--

INSERT INTO `costo` (`vIdCosto`, `vMonto`, `vFechaInicioVigencia`, `vIdItem`) VALUES
(1, 10, '18/03/2023', 1),
(2, 10, '18/03/2023', 2),
(3, 10, '18/03/2023', 3),
(4, 10, '18/03/2023', 4),
(5, 10, '18/03/2023', 5),
(6, 10, '18/03/2023', 6),
(7, 110, '18/03/2023', 7),
(8, 110, '18/03/2023', 8),
(9, 110, '18/03/2023', 9),
(10, 110, '18/03/2023', 10),
(11, 10, '18/03/2023', 11),
(12, 10, '18/03/2023', 12),
(13, 2000, '19/03/2023', 11),
(14, 500, '19/03/2023', 12),
(15, 1000, '20/03/2023', 12),
(16, 100, '21/03/2023', 12),
(17, 100, '21/03/2023', 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `vCuit` int(50) NOT NULL,
  `vNombre` varchar(50) NOT NULL,
  `vDireccion` varchar(50) NOT NULL,
  `vRepresentanteLegal` varchar(50) NOT NULL,
  `vRepresentanteTecnico` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`vCuit`, `vNombre`, `vDireccion`, `vRepresentanteLegal`, `vRepresentanteTecnico`) VALUES
(11, '12', '121', '12', '12'),
(111, 'robertiño', 'Newbery 3480', 'Velazquez Mauricio', 'Kinder Alejo'),
(222, 'TecnoMisiones', 'Florencio 3762', 'Echeverria Celeste', 'Pereyra Alejandro'),
(210801, 'TecnoMisiones', 'Florencio 3762', 'Echeverria Celeste', 'Pereyra Alejandro'),
(251100, 'Thai Cuisine', 'Newbery 3480', 'Velazquez Mauricio', 'Kinder Alejo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `financiacion`
--

CREATE TABLE `financiacion` (
  `vIdFinanciacion` int(50) NOT NULL,
  `vDescripcion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `financiacion`
--

INSERT INTO `financiacion` (`vIdFinanciacion`, `vDescripcion`) VALUES
(1, 'Mauri'),
(2, 'Amogus');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fojamedicion`
--

CREATE TABLE `fojamedicion` (
  `vIdFoja` int(50) NOT NULL,
  `vFechaEmision` varchar(50) NOT NULL,
  `vObra` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `fojamedicion`
--

INSERT INTO `fojamedicion` (`vIdFoja`, `vFechaEmision`, `vObra`) VALUES
(1, '18/03/2022', 4),
(2, '22/03/2022', 4),
(3, '22/03/2022', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `item`
--

CREATE TABLE `item` (
  `vIdItem` int(50) NOT NULL,
  `vDenominacion` varchar(50) NOT NULL,
  `vOrden` int(50) NOT NULL,
  `vIncidencia` int(50) NOT NULL,
  `vImpuestoFlete` double NOT NULL,
  `vImpuestoGastos` double NOT NULL,
  `vImpuestoUtilidad` double NOT NULL,
  `vIdObra` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `item`
--

INSERT INTO `item` (`vIdItem`, `vDenominacion`, `vOrden`, `vIncidencia`, `vImpuestoFlete`, `vImpuestoGastos`, `vImpuestoUtilidad`, `vIdObra`) VALUES
(1, 'a', 0, 0, 10, 10, 10, 1),
(2, 'a', 0, 0, 10, 10, 10, 1),
(3, 'a', 0, 0, 10, 10, 10, 1),
(4, 'a', 1, 1, 10, 10, 10, 2),
(5, 'a', 2, 1, 10, 10, 10, 2),
(6, 'a', 3, 98, 10, 10, 10, 2),
(7, 'Mauri', 1, 25, 10, 10, 10, 3),
(8, 'Mauri', 2, 25, 10, 10, 10, 3),
(9, 'Mauri', 4, 40, 10, 10, 10, 3),
(10, 'Mauri', 3, 10, 10, 10, 10, 3),
(11, 'f', 1, 25, 10, 10, 10, 4),
(12, 'f', 2, 75, 10, 10, 10, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `obra`
--

CREATE TABLE `obra` (
  `vIdObra` int(50) NOT NULL,
  `vDenominacion` varchar(50) NOT NULL,
  `vLocalidad` varchar(50) NOT NULL,
  `vCantidadViviendas` int(50) NOT NULL,
  `vFechaInicio` varchar(50) NOT NULL,
  `vPlazo` int(50) NOT NULL,
  `vEmpresa` int(50) NOT NULL,
  `vFinanciacion` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `obra`
--

INSERT INTO `obra` (`vIdObra`, `vDenominacion`, `vLocalidad`, `vCantidadViviendas`, `vFechaInicio`, `vPlazo`, `vEmpresa`, `vFinanciacion`) VALUES
(1, 'a', 'a', 10, '18/03/2023', 10, 11, 1),
(2, 'Mauri', 'Posadas', 10, '18/03/2023', 10, 251100, 1),
(3, 'Dachay', 'a', 10, '18/03/2023', 10, 11, 1),
(4, 'q', 'q', 10, '18/03/2023', 10, 111, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rengloncertificado`
--

CREATE TABLE `rengloncertificado` (
  `vIdRenglon` int(50) NOT NULL,
  `vCostoActual` double NOT NULL,
  `vAvance` int(50) NOT NULL,
  `vCostoAPagar` double NOT NULL,
  `vIdCertificado` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `renglonfoja`
--

CREATE TABLE `renglonfoja` (
  `vIdRenglon` int(50) NOT NULL,
  `vPorcentajeAnterior` int(50) NOT NULL,
  `vPorcentajeActual` int(50) NOT NULL,
  `vPorcentajeAcumulado` int(50) NOT NULL,
  `vIdItem` int(50) NOT NULL,
  `vIdFoja` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `renglonfoja`
--

INSERT INTO `renglonfoja` (`vIdRenglon`, `vPorcentajeAnterior`, `vPorcentajeActual`, `vPorcentajeAcumulado`, `vIdItem`, `vIdFoja`) VALUES
(1, 0, 10, 10, 11, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `certificadopago`
--
ALTER TABLE `certificadopago`
  ADD PRIMARY KEY (`vIdCertificado`),
  ADD UNIQUE KEY `vFoja` (`vFoja`);

--
-- Indices de la tabla `costo`
--
ALTER TABLE `costo`
  ADD PRIMARY KEY (`vIdCosto`),
  ADD KEY `vIdItem` (`vIdItem`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`vCuit`);

--
-- Indices de la tabla `financiacion`
--
ALTER TABLE `financiacion`
  ADD PRIMARY KEY (`vIdFinanciacion`);

--
-- Indices de la tabla `fojamedicion`
--
ALTER TABLE `fojamedicion`
  ADD PRIMARY KEY (`vIdFoja`),
  ADD KEY `fojamedicion_ibfk_1` (`vObra`);

--
-- Indices de la tabla `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`vIdItem`),
  ADD KEY `vIdObra` (`vIdObra`);

--
-- Indices de la tabla `obra`
--
ALTER TABLE `obra`
  ADD PRIMARY KEY (`vIdObra`),
  ADD KEY `vFinanciacion` (`vFinanciacion`),
  ADD KEY `vEmpresa` (`vEmpresa`);

--
-- Indices de la tabla `rengloncertificado`
--
ALTER TABLE `rengloncertificado`
  ADD PRIMARY KEY (`vIdRenglon`),
  ADD KEY `rengloncertificado_ibfk_1` (`vIdCertificado`);

--
-- Indices de la tabla `renglonfoja`
--
ALTER TABLE `renglonfoja`
  ADD PRIMARY KEY (`vIdRenglon`),
  ADD UNIQUE KEY `vIdItem` (`vIdItem`),
  ADD KEY `renglonfoja_ibfk_2` (`vIdFoja`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `certificadopago`
--
ALTER TABLE `certificadopago`
  MODIFY `vIdCertificado` int(50) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `costo`
--
ALTER TABLE `costo`
  MODIFY `vIdCosto` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `fojamedicion`
--
ALTER TABLE `fojamedicion`
  MODIFY `vIdFoja` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `item`
--
ALTER TABLE `item`
  MODIFY `vIdItem` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `obra`
--
ALTER TABLE `obra`
  MODIFY `vIdObra` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `renglonfoja`
--
ALTER TABLE `renglonfoja`
  MODIFY `vIdRenglon` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `certificadopago`
--
ALTER TABLE `certificadopago`
  ADD CONSTRAINT `certificadopago_ibfk_2` FOREIGN KEY (`vFoja`) REFERENCES `fojamedicion` (`vIdFoja`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `costo`
--
ALTER TABLE `costo`
  ADD CONSTRAINT `costo_ibfk_1` FOREIGN KEY (`vIdItem`) REFERENCES `item` (`vIdItem`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`vIdObra`) REFERENCES `obra` (`vIdObra`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `obra`
--
ALTER TABLE `obra`
  ADD CONSTRAINT `obra_ibfk_1` FOREIGN KEY (`vFinanciacion`) REFERENCES `financiacion` (`vIdFinanciacion`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `obra_ibfk_2` FOREIGN KEY (`vEmpresa`) REFERENCES `empresa` (`vCuit`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `rengloncertificado`
--
ALTER TABLE `rengloncertificado`
  ADD CONSTRAINT `rengloncertificado_ibfk_1` FOREIGN KEY (`vIdCertificado`) REFERENCES `certificadopago` (`vIdCertificado`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `renglonfoja`
--
ALTER TABLE `renglonfoja`
  ADD CONSTRAINT `renglonfoja_ibfk_2` FOREIGN KEY (`vIdFoja`) REFERENCES `fojamedicion` (`vIdFoja`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `renglonfoja_ibfk_3` FOREIGN KEY (`vIdItem`) REFERENCES `item` (`vIdItem`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
