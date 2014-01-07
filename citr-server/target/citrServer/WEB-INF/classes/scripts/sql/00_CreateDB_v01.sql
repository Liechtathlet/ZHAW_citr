SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `citr` DEFAULT CHARACTER SET utf8 ;
USE `citr` ;

-- -----------------------------------------------------
-- Table `citr`.`tbl_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `citr`.`tbl_user` ;

CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openId` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -----------------------------------------------------
-- Table `citr`.`tbl_citation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `citr`.`tbl_citation` ;

CREATE TABLE `tbl_citation` (
  `cit_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `grp_id` int(11) DEFAULT NULL,
  `cit_text` varchar(255) DEFAULT NULL,
  `cit_date` datetime DEFAULT NULL,
  `usr_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -----------------------------------------------------
-- Table `citr`.`tbl_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `citr`.`tbl_group` ;

CREATE TABLE `tbl_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mode` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `owner_usr_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKEC53D2BE117744B8` (`owner_usr_id`),
  CONSTRAINT `FKEC53D2BE117744B8` FOREIGN KEY (`owner_usr_id`) REFERENCES `tbl_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- -----------------------------------------------------
-- Table `citr`.`tbl_subscription`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `citr`.`tbl_subscription` ;

CREATE TABLE `tbl_subscription` (
  `usr_id` int(11) NOT NULL,
  `grp_id` int(11) NOT NULL,
  `usg_state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`usr_id`,`grp_id`),
  KEY `FK7C9E07FE2ADD19AC` (`usr_id`),
  KEY `FK7C9E07FE1D32409F` (`grp_id`),
  CONSTRAINT `FK7C9E07FE1D32409F` FOREIGN KEY (`grp_id`) REFERENCES `tbl_group` (`id`),
  CONSTRAINT `FK7C9E07FE2ADD19AC` FOREIGN KEY (`usr_id`) REFERENCES `tbl_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `citr`.`tbl_user`
(`openId`,`password`, `username`)
VALUES
('DS','DS','4890bb244647d632e141e9e445e92359204777f849b41dd334adeae9f47cb4060d444976c36e1ed44808cd245b47f5a18a5d5d29ba639846d084734cd7f1b30c');


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
