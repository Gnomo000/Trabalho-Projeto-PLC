-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 21-Abr-2021 às 12:03
-- Versão do servidor: 10.4.16-MariaDB
-- versão do PHP: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `biblioteca_santigo`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `livros`
--

CREATE TABLE `livros` (
  `cod_livro` int(6) NOT NULL,
  `nome_livro_primario` varchar(255) NOT NULL,
  `nome_livro_secundario` varchar(255) NOT NULL,
  `autor` varchar(255) NOT NULL,
  `categoria` varchar(255) NOT NULL,
  `edicao` varchar(255) NOT NULL,
  `editora` varchar(255) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `sinopse` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `requesitos`
--

CREATE TABLE `requesitos` (
  `cod_requesito` int(15) NOT NULL,
  `cod_livro` int(6) NOT NULL,
  `cod_utilizador` int(8) NOT NULL,
  `data_requesicao` date NOT NULL,
  `data_entrega` date NOT NULL,
  `estado` varchar(255) NOT NULL,
  `quantidade` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `utilizadores`
--

CREATE TABLE `utilizadores` (
  `cod_utilizador` int(8) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `data_nascimento` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `telemovel` int(9) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `palavra_chave` varchar(10) NOT NULL,
  `tipo` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `livros`
--
ALTER TABLE `livros`
  ADD PRIMARY KEY (`cod_livro`);

--
-- Índices para tabela `requesitos`
--
ALTER TABLE `requesitos`
  ADD PRIMARY KEY (`cod_requesito`),
  ADD KEY `cod_livro` (`cod_livro`),
  ADD KEY `cod_utilizador` (`cod_utilizador`);

--
-- Índices para tabela `utilizadores`
--
ALTER TABLE `utilizadores`
  ADD PRIMARY KEY (`cod_utilizador`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `livros`
--
ALTER TABLE `livros`
  MODIFY `cod_livro` int(6) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `requesitos`
--
ALTER TABLE `requesitos`
  MODIFY `cod_requesito` int(15) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `utilizadores`
--
ALTER TABLE `utilizadores`
  MODIFY `cod_utilizador` int(8) NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `requesitos`
--
ALTER TABLE `requesitos`
  ADD CONSTRAINT `requesitos_ibfk_1` FOREIGN KEY (`cod_livro`) REFERENCES `livros` (`cod_livro`),
  ADD CONSTRAINT `requesitos_ibfk_2` FOREIGN KEY (`cod_utilizador`) REFERENCES `utilizadores` (`cod_utilizador`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
