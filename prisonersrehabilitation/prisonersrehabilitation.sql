-- phpMyAdmin SQL Dump
-- version 2.11.6
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 13, 2024 at 09:41 AM
-- Server version: 5.0.51
-- PHP Version: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `prisonersrehabilitation`
--

-- --------------------------------------------------------

--
-- Table structure for table `adminlogin`
--

CREATE TABLE `adminlogin` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `adminlogin`
--

INSERT INTO `adminlogin` (`username`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `id` int(50) NOT NULL auto_increment,
  `username` varchar(50) NOT NULL,
  `contact` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`id`, `username`, `contact`, `gender`, `password`) VALUES
(1, 'joy', '86786567', 'Male', '123');

-- --------------------------------------------------------

--
-- Table structure for table `doctorpostquery`
--

CREATE TABLE `doctorpostquery` (
  `id` int(50) NOT NULL auto_increment,
  `query` varchar(50) NOT NULL,
  `solution` varchar(50) NOT NULL,
  `pid` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `doctorpostquery`
--

INSERT INTO `doctorpostquery` (`id`, `query`, `solution`, `pid`) VALUES
(3, 'my life is not well', 'go tha way', 'jo');

-- --------------------------------------------------------

--
-- Table structure for table `lawyer`
--

CREATE TABLE `lawyer` (
  `id` int(50) NOT NULL auto_increment,
  `username` varchar(50) NOT NULL,
  `contact` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `lawyer`
--

INSERT INTO `lawyer` (`id`, `username`, `contact`, `gender`, `password`) VALUES
(1, 'joy', '8677888', 'Male', '123');

-- --------------------------------------------------------

--
-- Table structure for table `lawyerpostquery`
--

CREATE TABLE `lawyerpostquery` (
  `id` int(50) NOT NULL auto_increment,
  `query` varchar(50) NOT NULL,
  `solution` varchar(50) NOT NULL,
  `pid` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `lawyerpostquery`
--

INSERT INTO `lawyerpostquery` (`id`, `query`, `solution`, `pid`) VALUES
(1, 'How i live my life', 'death', 'jo');

-- --------------------------------------------------------

--
-- Table structure for table `pdfdetails`
--

CREATE TABLE `pdfdetails` (
  `id` int(50) NOT NULL auto_increment,
  `description` varchar(50) NOT NULL,
  `pathToFile` varchar(250) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `pdfdetails`
--

INSERT INTO `pdfdetails` (`id`, `description`, `pathToFile`) VALUES
(2, 'joya', '20240317070316Vinod Bio-data.pdf');

-- --------------------------------------------------------

--
-- Table structure for table `prisoner`
--

CREATE TABLE `prisoner` (
  `id` int(50) NOT NULL auto_increment,
  `username` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `prisoner`
--

INSERT INTO `prisoner` (`id`, `username`, `gender`, `password`) VALUES
(2, 'jo', 'Male', '123');
