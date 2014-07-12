SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
-- -----------------------------------------------------
-- Schema AtomicInformationConfigurationManagerDB
-- -----------------------------------------------------
-- Author: Lee Baker
-- Descritpion: Relational Database For Liverpool University MSc Dissertation 
CREATE SCHEMA IF NOT EXISTS `AtomicInformationConfigurationManagerDB` ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`timestamps_1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`timestamps_1` (
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL);

USE `AtomicInformationConfigurationManagerDB` ;

-- -----------------------------------------------------
-- Table `AtomicInformationConfigurationManagerDB`.`Project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AtomicInformationConfigurationManagerDB`.`Project` (
  `ProjectID` INT NOT NULL,
  `ProjectReference` VARCHAR(45) NULL,
  `ProjectName` VARCHAR(45) NULL,
  `VersionNumber` INT NOT NULL,
  `IsCurrentVersion` TINYINT(1) NOT NULL,
  `PreviousVersionReference` VARCHAR(45) NULL,
  `EntityActive` TINYINT(1) NOT NULL,
  PRIMARY KEY (`ProjectID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AtomicInformationConfigurationManagerDB`.`Artefact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AtomicInformationConfigurationManagerDB`.`Artefact` (
  `ArtefactID` INT NOT NULL,
  `VersionNumber` INT NOT NULL,
  `IsCurrentVersion` TINYINT(1) NOT NULL,
  `PreviousVersionReference` VARCHAR(45) NULL,
  `EntityActive` TINYINT(1) NOT NULL,
  `ArtefactName` VARCHAR(45) NOT NULL,
  `ArtefactMajorVersionNumber` VARCHAR(45) NOT NULL DEFAULT 0,
  `ArtefactMinorVersionNumber` VARCHAR(45) NOT NULL DEFAULT 0,
  `ProjectID` INT NOT NULL,
  PRIMARY KEY (`ArtefactID`),
  INDEX `ProjectID_idx` (`ProjectID` ASC),
  CONSTRAINT `Artefact_ProjectID`
    FOREIGN KEY (`ProjectID`)
    REFERENCES `AtomicInformationConfigurationManagerDB`.`Project` (`ProjectID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AtomicInformationConfigurationManagerDB`.`TypeOfAtomicInformation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AtomicInformationConfigurationManagerDB`.`TypeOfAtomicInformation` (
  `TypeOfAtomicInformationID` INT NOT NULL,
  `Type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`TypeOfAtomicInformationID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AtomicInformationConfigurationManagerDB`.`AtomicInformation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AtomicInformationConfigurationManagerDB`.`AtomicInformation` (
  `AtomicInformationID` INT NOT NULL,
  `ArtefactID` INT NOT NULL,
  `TypeOfAtomicInformationID` INT NOT NULL,
  `VersionNumber` INT NOT NULL,
  `IsCurrentVersion` TINYINT(1) NOT NULL,
  `PreviousVersionReference` VARCHAR(45) NULL,
  `EntityActive` TINYINT(1) NOT NULL,
  `Content` VARCHAR(100) NOT NULL,
  `ProjectID` INT NOT NULL,
  PRIMARY KEY (`AtomicInformationID`),
  INDEX `ProjectID_idx` (`ProjectID` ASC),
  INDEX `ArtefactID_idx` (`ArtefactID` ASC),
  INDEX `TypeOfAtomicInformation_idx` (`TypeOfAtomicInformationID` ASC),
  CONSTRAINT `AtomicInformation_ProjectID`
    FOREIGN KEY (`ProjectID`)
    REFERENCES `AtomicInformationConfigurationManagerDB`.`Project` (`ProjectID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `AtomicInformation_ArtefactID`
    FOREIGN KEY (`ArtefactID`)
    REFERENCES `AtomicInformationConfigurationManagerDB`.`Artefact` (`ArtefactID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `AtomicInformation_TypeOfAtomicInformation`
    FOREIGN KEY (`TypeOfAtomicInformationID`)
    REFERENCES `AtomicInformationConfigurationManagerDB`.`TypeOfAtomicInformation` (`TypeOfAtomicInformationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AtomicInformationConfigurationManagerDB`.`MethodOfDistribution`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AtomicInformationConfigurationManagerDB`.`MethodOfDistribution` (
  `MethodOfDistributionID` INT NOT NULL,
  `Method` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`MethodOfDistributionID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AtomicInformationConfigurationManagerDB`.`DistributionRecipient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AtomicInformationConfigurationManagerDB`.`DistributionRecipient` (
  `DistributionRecipientID` INT NOT NULL,
  `ProjectID` INT NOT NULL,
  `VersionNumber` INT NOT NULL,
  `IsCurrentVersion` TINYINT(1) NOT NULL,
  `PreviousVersionReference` VARCHAR(45) NULL,
  `EntityActive` TINYINT(1) NOT NULL,
  `FirstName` VARCHAR(45) NOT NULL,
  `Surname` VARCHAR(45) NOT NULL,
  `E-MailAddress` VARCHAR(100) NULL,
  `MobileNumber` VARCHAR(45) NULL,
  `OfficeNumber` VARCHAR(45) NULL,
  `CompanyName` VARCHAR(50) NULL,
  PRIMARY KEY (`DistributionRecipientID`),
  INDEX `ProjectID_idx` (`ProjectID` ASC),
  CONSTRAINT `DistributionRecipientID_ProjectID`
    FOREIGN KEY (`ProjectID`)
    REFERENCES `AtomicInformationConfigurationManagerDB`.`Project` (`ProjectID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AtomicInformationConfigurationManagerDB`.`ArtefactDistribution`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AtomicInformationConfigurationManagerDB`.`ArtefactDistribution` (
  `ArtefactDistributionID` INT NOT NULL,
  `ArtefactID` INT NOT NULL,
  `MethodOfDistributionID` INT NOT NULL,
  `VersionNumber` INT NOT NULL,
  `IsCurrentVersion` TINYINT(1) NOT NULL,
  `PreviousVersionReference` VARCHAR(45) NULL,
  `EntityActive` TINYINT(1) NOT NULL,
  `DateOfArtefactDistribution` DATE NOT NULL,
  `DistributionRecipientID` INT NOT NULL,
  PRIMARY KEY (`ArtefactDistributionID`),
  INDEX `ArtefactID_idx` (`ArtefactID` ASC),
  INDEX `MethodOfDistribution_idx` (`MethodOfDistributionID` ASC),
  INDEX `DistributionRecipientID_idx` (`DistributionRecipientID` ASC),
  CONSTRAINT `ArtefactDistribution_ArtefactID`
    FOREIGN KEY (`ArtefactID`)
    REFERENCES `AtomicInformationConfigurationManagerDB`.`Artefact` (`ArtefactID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ArtefactDistribution_DistributionRecipientID`
    FOREIGN KEY (`DistributionRecipientID`)
    REFERENCES `AtomicInformationConfigurationManagerDB`.`DistributionRecipient` (`DistributionRecipientID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
