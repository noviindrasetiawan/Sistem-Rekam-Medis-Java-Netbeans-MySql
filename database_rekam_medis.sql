/*
SQLyog Ultimate v12.5.1 (64 bit)
MySQL - 10.1.29-MariaDB : Database - database_rekam_medis
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`database_rekam_medis` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `database_rekam_medis`;

/*Table structure for table `tabel_detail_periksa` */

DROP TABLE IF EXISTS `tabel_detail_periksa`;

CREATE TABLE `tabel_detail_periksa` (
  `kode_periksa` varchar(6) NOT NULL,
  `kode_obat` varchar(6) NOT NULL,
  `nama_obat` varchar(20) NOT NULL,
  `harga_obat` varchar(8) NOT NULL,
  `jumlah_obat` int(2) NOT NULL,
  PRIMARY KEY (`kode_periksa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tabel_detail_periksa` */

/*Table structure for table `tabel_dokter` */

DROP TABLE IF EXISTS `tabel_dokter`;

CREATE TABLE `tabel_dokter` (
  `kode_dokter` varchar(6) NOT NULL,
  `nama_dokter` varchar(20) NOT NULL,
  `alamat_dokter` varchar(20) NOT NULL,
  `keahlian_dokter` varchar(20) NOT NULL,
  `no_hp_dokter` varchar(13) NOT NULL,
  PRIMARY KEY (`kode_dokter`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tabel_dokter` */

/*Table structure for table `tabel_obat` */

DROP TABLE IF EXISTS `tabel_obat`;

CREATE TABLE `tabel_obat` (
  `kode_obat` varchar(6) NOT NULL,
  `nama_obat` varchar(20) NOT NULL,
  `jenis_obat` varchar(20) NOT NULL,
  `harga_obat` bigint(8) NOT NULL,
  PRIMARY KEY (`kode_obat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tabel_obat` */

/*Table structure for table `tabel_pasien` */

DROP TABLE IF EXISTS `tabel_pasien`;

CREATE TABLE `tabel_pasien` (
  `kode_pasien` varchar(6) NOT NULL,
  `nama_pasien` varchar(20) NOT NULL,
  `tanggal_lahir_pasien` date NOT NULL,
  `jenis_kelamin_pasien` varchar(10) NOT NULL,
  `no_hp_pasien` varchar(13) NOT NULL,
  `alamat_pasien` varchar(20) NOT NULL,
  PRIMARY KEY (`kode_pasien`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tabel_pasien` */

insert  into `tabel_pasien`(`kode_pasien`,`nama_pasien`,`tanggal_lahir_pasien`,`jenis_kelamin_pasien`,`no_hp_pasien`,`alamat_pasien`) values 
('A123','Novi','1999-12-07','Laki-laki','08771015554',' Pekalongan');

/*Table structure for table `tabel_periksa` */

DROP TABLE IF EXISTS `tabel_periksa`;

CREATE TABLE `tabel_periksa` (
  `kode_periksa` varchar(6) NOT NULL,
  `tanggal_periksa` date NOT NULL,
  `penyakit_pasien` varchar(20) NOT NULL,
  `kode_pasien` varchar(8) NOT NULL,
  `kode_dokter` varchar(8) NOT NULL,
  `kode_obat` varchar(6) NOT NULL,
  `jumlah_item` varchar(8) NOT NULL,
  `jumlah_total` bigint(8) NOT NULL,
  `jumlah_bayar` bigint(8) NOT NULL,
  `kembalian` bigint(8) NOT NULL,
  PRIMARY KEY (`kode_periksa`),
  KEY `kode_pasien` (`kode_pasien`),
  KEY `kode_dokter` (`kode_dokter`),
  KEY `kode_obat` (`kode_obat`),
  CONSTRAINT `tabel_periksa_ibfk_1` FOREIGN KEY (`kode_pasien`) REFERENCES `tabel_pasien` (`kode_pasien`),
  CONSTRAINT `tabel_periksa_ibfk_2` FOREIGN KEY (`kode_dokter`) REFERENCES `tabel_dokter` (`kode_dokter`),
  CONSTRAINT `tabel_periksa_ibfk_4` FOREIGN KEY (`kode_periksa`) REFERENCES `tabel_detail_periksa` (`kode_periksa`),
  CONSTRAINT `tabel_periksa_ibfk_5` FOREIGN KEY (`kode_obat`) REFERENCES `tabel_obat` (`kode_obat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tabel_periksa` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
