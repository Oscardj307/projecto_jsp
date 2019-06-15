-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-06-2019 a las 22:35:13
-- Versión del servidor: 10.1.37-MariaDB
-- Versión de PHP: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_profinal`
--
CREATE DATABASE bd_profinal;
USE bd_profinal;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_personal`
--

CREATE TABLE `tb_personal` (
  `id_empleado` int(11) NOT NULL,
  `dui` varchar(12) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `telefono` int(8) NOT NULL,
  `email` varchar(50) NOT NULL,
  `tipo_empleado` varchar(60) NOT NULL,
  `comentarios` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_planilla`
--

CREATE TABLE `tb_planilla` (
  `id_planilla` varchar(20) NOT NULL,
  `lunes1` varchar(2000) NOT NULL,
  `martes1` varchar(2000) NOT NULL,
  `miercoles1` varchar(2000) NOT NULL,
  `jueves1` varchar(2000) NOT NULL,
  `viernes1` varchar(2000) NOT NULL,
  `sabado1` varchar(2000) NOT NULL,
  `lunes2` varchar(2000) NOT NULL,
  `martes2` varchar(2000) NOT NULL,
  `miercoles2` varchar(2000) NOT NULL,
  `jueves2` varchar(2000) NOT NULL,
  `viernes2` varchar(2000) NOT NULL,
  `sabado2` varchar(2000) NOT NULL,
  `dui` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_usuario`
--

CREATE TABLE `tb_usuario` (
  `id_usuario` varchar(12) NOT NULL,
  `nombres` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `clave` varchar(20) NOT NULL,
  `tipo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tb_personal`
--
ALTER TABLE `tb_personal`
  ADD PRIMARY KEY (`id_empleado`);

--
-- Indices de la tabla `tb_planilla`
--
ALTER TABLE `tb_planilla`
  ADD PRIMARY KEY (`id_planilla`),
  ADD KEY `dui` (`dui`);

--
-- Indices de la tabla `tb_usuario`
--
ALTER TABLE `tb_usuario`
  ADD PRIMARY KEY (`id_usuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
