-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 17, 2021 at 04:11 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `kamera_table`
--

CREATE TABLE `kamera_table` (
  `id_kamera` int(11) NOT NULL,
  `nama_kamera` varchar(30) NOT NULL,
  `harga_harian` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kamera_table`
--

INSERT INTO `kamera_table` (`id_kamera`, `nama_kamera`, `harga_harian`) VALUES
(1, 'CANON EOS 750D', 135000),
(2, 'SONY 6000', 140000),
(3, 'FUJIFILM X-A3', 135000),
(4, 'CANON EOS 1100D', 90000);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Telp` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`, `nama`, `Email`, `Telp`) VALUES
('Ila123', 'abcd123', 'Ila', 'ila@gmail.com', '081234567890');

-- --------------------------------------------------------

--
-- Table structure for table `penyewa_table`
--

CREATE TABLE `penyewa_table` (
  `id_penyewa` int(11) NOT NULL,
  `nama_penyewa` varchar(30) NOT NULL,
  `alamat_penyewa` varchar(30) NOT NULL,
  `telp_penyewa` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `rental`
--

CREATE TABLE `rental` (
  `tanggal_rental` varchar(30) NOT NULL,
  `id_kamera` int(11) NOT NULL,
  `lama_hari` int(11) NOT NULL,
  `total_harga` int(11) NOT NULL,
  `nama_penyewa` varchar(30) NOT NULL,
  `alamat_penyewa` varchar(30) NOT NULL,
  `telp_penyewa` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rental`
--

INSERT INTO `rental` (`tanggal_rental`, `id_kamera`, `lama_hari`, `total_harga`, `nama_penyewa`, `alamat_penyewa`, `telp_penyewa`) VALUES
('13 Desember 2021', 1, 1, 135000, 'ila', '0987161515', 'jember'),
('15 Desember2021', 1, 4, 540000, 'ila', '081234567890', 'Jember'),
('14 Desember2021', 4, 2, 180000, 'ila', '081234567890', 'jember');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_penyewa` int(11) NOT NULL,
  `tanggal_rental` varchar(30) NOT NULL,
  `harga_harian` int(11) NOT NULL,
  `lama_hari` int(11) NOT NULL,
  `total_harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kamera_table`
--
ALTER TABLE `kamera_table`
  ADD PRIMARY KEY (`id_kamera`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `penyewa_table`
--
ALTER TABLE `penyewa_table`
  ADD PRIMARY KEY (`id_penyewa`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_penyewa`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
