SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `citr` ;
CREATE SCHEMA IF NOT EXISTS `citr` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `citr` ;

-- -----------------------------------------------------
-- Table `tbl_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_group` ;

CREATE TABLE IF NOT EXISTS `tbl_group` (
  `grp_id` INT NOT NULL,
  `grp_name` VARCHAR(255) NULL,
  `grp_state` TINYINT NULL COMMENT 'ENUM(): active /  deleted',
  `grp_mode` TINYINT NULL COMMENT 'public, private',
  PRIMARY KEY (`grp_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_user` ;

CREATE TABLE IF NOT EXISTS `tbl_user` (
  `usr_id` INT NOT NULL,
  `usr_google_id` INT NULL,
  `usr_name` VARCHAR(255) NULL,
  `usr_password` VARCHAR(255) NULL COMMENT 'Len = Hashlength',
  PRIMARY KEY (`usr_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_citation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_citation` ;

CREATE TABLE IF NOT EXISTS `tbl_citation` (
  `cit_id` INT NOT NULL,
  `grp_id` INT NOT NULL,
  `usr_id` INT NOT NULL,
  `cit_author` VARCHAR(255) NULL COMMENT 'Original author of citation. (to show)\n',
  `cit_title` VARCHAR(255) NULL,
  `cit_text` VARCHAR(5000) NULL COMMENT '	',
  `cit_date` DATETIME NULL,
  `cit_state` TINYINT NULL COMMENT '1 active\n0 deleted\n',
  PRIMARY KEY (`cit_id`, `grp_id`, `usr_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_user_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_user_group` ;

CREATE TABLE IF NOT EXISTS `tbl_user_group` (
  `usg_id` INT NOT NULL,
  `grp_id` INT NOT NULL,
  `usr_id` INT NOT NULL,
  `usg_role` TINYINT NULL COMMENT 'role types: admin, moderator, guest',
  PRIMARY KEY (`usg_id`, `grp_id`, `usr_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_tag` ;

CREATE TABLE IF NOT EXISTS `tbl_tag` (
  `tag_id` INT NOT NULL,
  `tag_title` VARCHAR(255) NULL,
  PRIMARY KEY (`tag_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tbl_citation_tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tbl_citation_tag` ;

CREATE TABLE IF NOT EXISTS `tbl_citation_tag` (
  `cta_id` INT NOT NULL,
  `object_id` INT NULL COMMENT 'cit_id / grp_id',
  `tag_id` INT NOT NULL,
  PRIMARY KEY (`cta_id`, `tag_id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
