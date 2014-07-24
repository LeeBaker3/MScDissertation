-- -----------------------------------------------------
-- Schema AtomicInformationConfigurationManagerDB
-- -----------------------------------------------------
-- Author: Lee Baker
-- Descritpion: Relational Database For Liverpool University MSc Dissertation 
-- Insert test Data into Database

USE `AtomicInformationConfigurationManagerDB` ;

-- -----------------------------------------------------
-- Insert Data into Project Table
-- -----------------------------------------------------
 
INSERT INTO project (ProjectReference, ProjectName, VersionNumber, IsCurrentVersion, PreviousVersionReference, EntityActive)
	VALUES('P001', 'Test Project1 Entity Active', 1, true, NULL, true) ON DUPLICATE KEY UPDATE
	ProjectReference=VALUES(ProjectReference), 
	ProjectName=VALUES(ProjectName), 
	VersionNumber=VAlUES(VersionNumber), 
	IsCurrentVersion=VALUES(IsCurrentVersion), 
	PreviousVersionReference=VALUES(PreviousVersionReference), 
	EntityActive=VALUES(EntityActive);

INSERT INTO project (ProjectReference, ProjectName, VersionNumber, IsCurrentVersion, PreviousVersionReference, EntityActive)
	VALUES('P002', 'Test Project2 Entity Active', 1, true, NULL, true) ON DUPLICATE KEY UPDATE
	ProjectReference=VALUES(ProjectReference), 
	ProjectName=VALUES(ProjectName), 
	VersionNumber=VAlUES(VersionNumber), 
	IsCurrentVersion=VALUES(IsCurrentVersion), 
	PreviousVersionReference=VALUES(PreviousVersionReference), 
	EntityActive=VALUES(EntityActive);

INSERT INTO project (ProjectReference, ProjectName, VersionNumber, IsCurrentVersion, PreviousVersionReference, EntityActive)
	VALUES('P003', 'Test Project3 Entity Not Active', 1, true, NULL, false) ON DUPLICATE KEY UPDATE
	ProjectReference=VALUES(ProjectReference), 
	ProjectName=VALUES(ProjectName), 
	VersionNumber=VAlUES(VersionNumber), 
	IsCurrentVersion=VALUES(IsCurrentVersion), 
	PreviousVersionReference=VALUES(PreviousVersionReference), 
	EntityActive=VALUES(EntityActive);

-- -----------------------------------------------------
-- Insert Data into artefact Table
-- -----------------------------------------------------

INSERT INTO artefact (`VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`, `ArtefactName`, `ArtefactMajorVersionNumber`, `ArtefactMinorVersionNumber`, `ProjectID`) 
	VALUES (1, true, '', true, 'Test Artefact 1', '1', '0', 1)  ON DUPLICATE KEY UPDATE
	VersionNumber=VALUES(VersionNumber), 
	IsCurrentVersion=VALUES(IsCurrentVersion), 
	PreviousVersionReference=VALUES(PreviousVersionReference), 
	EntityActive=VALUES(EntityActive), 
	ArtefactName=VALUES(ArtefactName), 
	ArtefactMajorVersionNumber=VALUES(ArtefactMajorVersionNumber), 
	ArtefactMinorVersionNumber=VALUES(ArtefactMinorVersionNumber), 
	ProjectID=VALUES(ProjectID);

INSERT INTO artefact (`VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`, `ArtefactName`, `ArtefactMajorVersionNumber`, `ArtefactMinorVersionNumber`, `ProjectID`) 
	VALUES (1, true, '', true, 'Test Artefact 2', '1', '0', 1)  ON DUPLICATE KEY UPDATE
	VersionNumber=VALUES(VersionNumber), 
	IsCurrentVersion=VALUES(IsCurrentVersion), 
	PreviousVersionReference=VALUES(PreviousVersionReference), 
	EntityActive=VALUES(EntityActive), 
	ArtefactName=VALUES(ArtefactName), 
	ArtefactMajorVersionNumber=VALUES(ArtefactMajorVersionNumber), 
	ArtefactMinorVersionNumber=VALUES(ArtefactMinorVersionNumber), 
	ProjectID=VALUES(ProjectID);

INSERT INTO artefact (`VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`, `ArtefactName`, `ArtefactMajorVersionNumber`, `ArtefactMinorVersionNumber`, `ProjectID`) 
	VALUES (1, true, '', true, 'Test Artefact 3', '1', '0', 2)  ON DUPLICATE KEY UPDATE
	VersionNumber=VALUES(VersionNumber), 
	IsCurrentVersion=VALUES(IsCurrentVersion), 
	PreviousVersionReference=VALUES(PreviousVersionReference), 
	EntityActive=VALUES(EntityActive), 
	ArtefactName=VALUES(ArtefactName), 
	ArtefactMajorVersionNumber=VALUES(ArtefactMajorVersionNumber), 
	ArtefactMinorVersionNumber=VALUES(ArtefactMinorVersionNumber), 
	ProjectID=VALUES(ProjectID);

-- -----------------------------------------------------
-- Insert Data into atomicinformation Table
-- -----------------------------------------------------

-- TO DO

-- -----------------------------------------------------
-- Insert Data into Project artefactdistribution Table
-- -----------------------------------------------------

-- TO DO

-- -----------------------------------------------------
-- Insert Data into Project methodofdistribution Table
-- -----------------------------------------------------

-- TO DO

-- -----------------------------------------------------
-- Insert Data into Project typeofatomicinformation Table
-- -----------------------------------------------------

-- TO DO
