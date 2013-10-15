SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `citr` DEFAULT CHARACTER SET utf8 ;
USE `citr` ;

-- -----------------------------------------------------
-- Table `citr`.`appusers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `citr`.`appusers` ;

CREATE  TABLE IF NOT EXISTS `citr`.`appusers` (
  `ID` INT(5) NOT NULL AUTO_INCREMENT ,
  `NAME` VARCHAR(50) NOT NULL ,
  `AGE` INT(3) NOT NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `citr`.`tbl_citation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `citr`.`tbl_citation` ;

CREATE  TABLE IF NOT EXISTS `citr`.`tbl_citation` (
  `cit_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `grp_id` INT(11) NOT NULL ,
  `usr_id` INT(11) NOT NULL ,
  `cit_author` VARCHAR(255) NULL DEFAULT NULL COMMENT 'Original author of citation. (to show)\\n' ,
  `cit_title` VARCHAR(255) NULL DEFAULT NULL ,
  `cit_text` VARCHAR(5000) NULL DEFAULT NULL COMMENT '	' ,
  `cit_date` DATETIME NULL DEFAULT NULL ,
  `cit_state` TINYINT(4) NULL DEFAULT NULL COMMENT '1 active\\n0 deleted\\n' ,
  PRIMARY KEY (`cit_id`, `grp_id`, `usr_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `citr`.`tbl_citation_tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `citr`.`tbl_citation_tag` ;

CREATE  TABLE IF NOT EXISTS `citr`.`tbl_citation_tag` (
  `cta_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `object_id` INT(11) NULL DEFAULT NULL COMMENT 'cit_id / grp_id' ,
  `tag_id` INT(11) NOT NULL ,
  PRIMARY KEY (`cta_id`, `tag_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `citr`.`tbl_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `citr`.`tbl_group` ;

CREATE  TABLE IF NOT EXISTS `citr`.`tbl_group` (
  `grp_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `grp_name` VARCHAR(255) NULL DEFAULT NULL ,
  `grp_state` TINYINT(4) NULL DEFAULT NULL COMMENT 'ENUM(): active /  deleted' ,
  `grp_mode` TINYINT(4) NULL DEFAULT NULL COMMENT 'public, private' ,
  PRIMARY KEY (`grp_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `citr`.`tbl_tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `citr`.`tbl_tag` ;

CREATE  TABLE IF NOT EXISTS `citr`.`tbl_tag` (
  `tag_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `tag_title` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`tag_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `citr`.`tbl_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `citr`.`tbl_user` ;

CREATE  TABLE IF NOT EXISTS `citr`.`tbl_user` (
  `usr_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `usr_google_id` INT(11) NULL DEFAULT NULL ,
  `usr_name` VARCHAR(255) NULL DEFAULT NULL ,
  `usr_password` VARCHAR(255) NULL DEFAULT NULL COMMENT 'Len = Hashlength' ,
  PRIMARY KEY (`usr_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `citr`.`tbl_user_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `citr`.`tbl_user_group` ;

CREATE  TABLE IF NOT EXISTS `citr`.`tbl_user_group` (
  `usg_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `grp_id` INT(11) NOT NULL ,
  `usr_id` INT(11) NOT NULL ,
  `usg_role` TINYINT(4) NULL DEFAULT NULL COMMENT 'role types: admin, moderator, guest' ,
  PRIMARY KEY (`usg_id`, `grp_id`, `usr_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
