SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema AtomicInformationConfigurationManagerDB
-- -----------------------------------------------------
-- Author: Lee Baker
-- Descritpion: Relational Database For Liverpool University MSc Dissertation 
-- 
-- 06/08/14 Updated from Version 1 to include changes for the addition of the Link Table
-- ArtefactAtomicInfromation
--
-- 17/08/14 Updated PreviousVersionReference for all tables from a String to an Int

CREATE SCHEMA IF NOT EXISTS `AtomicInformationConfigurationManagerDB` ;

USE `AtomicInformationConfigurationManagerDB` ;

-- -----------------------------------------------------
-- Table `AtomicInformationConfigurationManagerDB`.`Project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AtomicInformationConfigurationManagerDB`.`Project` (
  `ProjectID` INT NOT NULL AUTO_INCREMENT,
  `ProjectReference` VARCHAR(45) NULL,
  `ProjectName` VARCHAR(45) NULL,
  `VersionNumber` INT NOT NULL,
  `IsCurrentVersion` TINYINT(1) NOT NULL,
  `PreviousVersionReference` INT NULL,
  `EntityActive` TINYINT(1) NOT NULL,
  PRIMARY KEY (`ProjectID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AtomicInformationConfignManagerDB`.`Artefact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AtomicInformationConfigurationManagerDB`.`Artefact` (
  `ArtefactID` INT NOT NULL AUTO_INCREMENT,
  `VersionNumber` INT NOT NULL,
  `IsCurrentVersion` TINYINT(1) NOT NULL,
  `PreviousVersionReference` INT NULL,
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
-- Table `AtomicInformationConfiguManagerDB`.`TypeOfAtomicInformation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AtomicInformationConfigurationManagerDB`.`TypeOfAtomicInformation` (
  `TypeOfAtomicInformationID` INT NOT NULL AUTO_INCREMENT,
  `Type` VARCHAR(45) NOT NULL,
  `VersionNumber` INT NOT NULL,
  `IsCurrentVersion` TINYINT(1) NOT NULL,
  `PreviousVersionReference` INT NULL,
  `EntityActive` TINYINT(1) NOT NULL,
  PRIMARY KEY (`TypeOfAtomicInformationID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AtomicInformationConfigurationManagerDB`.`AtomicInformation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AtomicInformationConfigurationManagerDB`.`AtomicInformation` (
  `AtomicInformationID` INT NOT NULL AUTO_INCREMENT,
  `TypeOfAtomicInformationID` INT NOT NULL,
  `VersionNumber` INT NOT NULL,
  `IsCurrentVersion` TINYINT(1) NOT NULL,
  `PreviousVersionReference` INT NULL,
  `EntityActive` TINYINT(1) NOT NULL,
  `Content` VARCHAR(100) NOT NULL,
  `ProjectID` INT NOT NULL,
  PRIMARY KEY (`AtomicInformationID`),
  INDEX `ProjectID_idx` (`ProjectID` ASC),
  INDEX `TypeOfAtomicInformation_idx` (`TypeOfAtomicInformationID` ASC),
  CONSTRAINT `AtomicInformation_ProjectID`
    FOREIGN KEY (`ProjectID`)
    REFERENCES `AtomicInformationConfigurationManagerDB`.`Project` (`ProjectID`)
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
  `MethodOfDistributionID` INT NOT NULL AUTO_INCREMENT,
  `Method` VARCHAR(45) NOT NULL,
  `VersionNumber` INT NOT NULL,
  `IsCurrentVersion` TINYINT(1) NOT NULL,
  `PreviousVersionReference` INT NULL,
  `EntityActive` TINYINT(1) NOT NULL,
  PRIMARY KEY (`MethodOfDistributionID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AtomicInformationConfigurationManagerDB`.`DistributionRecipient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AtomicInformationConfigurationManagerDB`.`DistributionRecipient` (
  `DistributionRecipientID` INT NOT NULL AUTO_INCREMENT,
  `ProjectID` INT NOT NULL,
  `VersionNumber` INT NOT NULL,
  `IsCurrentVersion` TINYINT(1) NOT NULL,
  `PreviousVersionReference` INT NULL,
  `EntityActive` TINYINT(1) NOT NULL,
  `FirstName` VARCHAR(45) NOT NULL,
  `Surname` VARCHAR(45) NOT NULL,
  `EMailAddress` VARCHAR(100) NULL,
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
  `ArtefactDistributionID` INT NOT NULL AUTO_INCREMENT,
  `ArtefactID` INT NOT NULL,
  `MethodOfDistributionID` INT NOT NULL,
  `VersionNumber` INT NOT NULL,
  `IsCurrentVersion` TINYINT(1) NOT NULL,
  `PreviousVersionReference` INT NULL,
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
    ON UPDATE NO ACTION,
  CONSTRAINT `ArtefactDistribution_MethodOfDistributionID`
    FOREIGN KEY (`MethodOfDistributionID`)
    REFERENCES `AtomicInformationConfigurationManagerDB`.`MethodOfDistribution` (`MethodOfDistributionID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `AtomicInformationConfigurationManagerDB`.`ArtefactAtomicInformation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AtomicInformationConfigurationManagerDB`.`ArtefactAtomicInformation` (
  `ArtefactAtomicInformationID` INT NOT NULL AUTO_INCREMENT,
  `AtomicInformationID` INT NOT NULL,
  `ArtefactID` INT NOT NULL,
  `VersionNumber` INT NOT NULL,
  `IsCurrentVersion` TINYINT(1) NOT NULL,
  `PreviousVersionReference` INT NULL,
  `EntityActive` TINYINT(1) NOT NULL,
  PRIMARY KEY (`ArtefactAtomicInformationID`),
  INDEX `AtomicInformationID_idx` (`AtomicInformationID` ASC),
  INDEX `ArtefactID_idx` (`ArtefactID` ASC),
  CONSTRAINT `ArtefactAtomicInformation_AtomicInformationID`
    FOREIGN KEY (`AtomicInformationID`)
    REFERENCES `AtomicInformationConfigurationManagerDB`.`AtomicInformation` (`AtomicInformationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ArtefactAtomicInformation_ArtefactID`
    FOREIGN KEY (`ArtefactID`)
    REFERENCES `AtomicInformationConfigurationManagerDB`.`Artefact` (`ArtefactID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- -----------------------------------------------------
-- insert Standard Methods of Distribution into methodsofdistribution Table
-- -----------------------------------------------------

INSERT INTO methodofdistribution (`Method`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`) 
	VALUES ('E-Mail', 1, true, NULL, true);

INSERT INTO methodofdistribution (`Method`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`) 
	VALUES ('Post', 1, true, NULL, true);

INSERT INTO methodofdistribution (`Method`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`) 
	VALUES ('FTP', 1, true, NULL, true);

INSERT INTO methodofdistribution (`Method`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`) 
	VALUES ('Courier', 1, true, NULL, true);

INSERT INTO methodofdistribution (`Method`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`) 
	VALUES ('Other', 1, true, NULL, true);

-- -----------------------------------------------------
-- insert Standard Types of Atomic Infromation into typesofatomicinfromation Table
-- -----------------------------------------------------

INSERT INTO typeofatomicinformation (`Type`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`) 
	VALUES ('Requirement', 1, true, NULL, true);

INSERT INTO typeofatomicinformation (`Type`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`) 
	VALUES ('Function', 1, true, NULL, true);

INSERT INTO typeofatomicinformation (`Type`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`) 
	VALUES ('Stakeholder', 1, true, NULL, true);

INSERT INTO typeofatomicinformation (`Type`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`) 
	VALUES ('Project Manager', 1, true, NULL, true);

INSERT INTO typeofatomicinformation (`Type`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`) 
	VALUES ('Project Sponsor', 1, true, NULL, true);