-- -----------------------------------------------------
-- Schema AtomicInformationConfigurationManagerDB
-- -----------------------------------------------------
-- Author: Lee Baker
-- Descritpion: Relational Database For Liverpool University MSc Dissertation 
-- Insert test Data into Database
-- 
-- 06/08/14 Updated from Version 1 to include changes for the addition of the Link Table
-- ArtefactAtomicInfromation
--
-- 17/08/14 Updated PreviousVersionReference for all tables to insert Null instead of ''

USE `AtomicInformationConfigurationManagerDB`;

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
	VALUES (1, true, NULL, true, 'Test Artefact 1 Requirements Specification', '1', '0', 1)  ON DUPLICATE KEY UPDATE
	VersionNumber=VALUES(VersionNumber), 
	IsCurrentVersion=VALUES(IsCurrentVersion), 
	PreviousVersionReference=VALUES(PreviousVersionReference), 
	EntityActive=VALUES(EntityActive), 
	ArtefactName=VALUES(ArtefactName), 
	ArtefactMajorVersionNumber=VALUES(ArtefactMajorVersionNumber), 
	ArtefactMinorVersionNumber=VALUES(ArtefactMinorVersionNumber), 
	ProjectID=VALUES(ProjectID);

INSERT INTO artefact (`VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`, `ArtefactName`, `ArtefactMajorVersionNumber`, `ArtefactMinorVersionNumber`, `ProjectID`) 
	VALUES (1, true, NULL, true, 'Test Artefact 2 Functional Specificaltion', '1', '0', 1)  ON DUPLICATE KEY UPDATE
	VersionNumber=VALUES(VersionNumber), 
	IsCurrentVersion=VALUES(IsCurrentVersion), 
	PreviousVersionReference=VALUES(PreviousVersionReference), 
	EntityActive=VALUES(EntityActive), 
	ArtefactName=VALUES(ArtefactName), 
	ArtefactMajorVersionNumber=VALUES(ArtefactMajorVersionNumber), 
	ArtefactMinorVersionNumber=VALUES(ArtefactMinorVersionNumber), 
	ProjectID=VALUES(ProjectID);

INSERT INTO artefact (`VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`, `ArtefactName`, `ArtefactMajorVersionNumber`, `ArtefactMinorVersionNumber`, `ProjectID`) 
	VALUES (1, true, NULL, true, 'Test Artefact 3 Project Initiation Document', '1', '0', 2)  ON DUPLICATE KEY UPDATE
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

 INSERT INTO atomicinformation (`TypeOfAtomicInformationID`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`, `Content`, `ProjectID`) 
	VALUES (1, 1, true, NULL, true, 'Requirement 1: Must Be able to Save Something', 1) ON DUPLICATE KEY UPDATE
	TypeOfAtomicInformationID=VALUES(TypeOfAtomicInformationID),
	VersionNumber=VALUES(VersionNumber), 
	IsCurrentVersion=VALUES(IsCurrentVersion), 
	PreviousVersionReference=VALUES(PreviousVersionReference), 
	EntityActive=VALUES(EntityActive), 
	Content=VALUES(Content),
	ProjectID=VALUES(ProjectID);

 INSERT INTO atomicinformation (`TypeOfAtomicInformationID`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`, `Content`, `ProjectID`) 
	VALUES (3, 1, true, NULL, true, 'Senior User: Joe Bloggs', 1) ON DUPLICATE KEY UPDATE
	TypeOfAtomicInformationID=VALUES(TypeOfAtomicInformationID),
	VersionNumber=VALUES(VersionNumber), 
	IsCurrentVersion=VALUES(IsCurrentVersion), 
	PreviousVersionReference=VALUES(PreviousVersionReference), 
	EntityActive=VALUES(EntityActive), 
	Content=VALUES(Content),
	ProjectID=VALUES(ProjectID);

 INSERT INTO atomicinformation (`TypeOfAtomicInformationID`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`, `Content`, `ProjectID`) 
	VALUES (2, 1, true, NULL, true, 'Function001: When User Presses Save Check No Current Matching Records', 1) ON DUPLICATE KEY UPDATE
	TypeOfAtomicInformationID=VALUES(TypeOfAtomicInformationID),
	VersionNumber=VALUES(VersionNumber), 
	IsCurrentVersion=VALUES(IsCurrentVersion), 
	PreviousVersionReference=VALUES(PreviousVersionReference), 
	EntityActive=VALUES(EntityActive), 
	Content=VALUES(Content),
	ProjectID=VALUES(ProjectID);

 INSERT INTO atomicinformation (`TypeOfAtomicInformationID`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`, `Content`, `ProjectID`) 
	VALUES (4, 1, true, NULL, true, 'John Doe', 1) ON DUPLICATE KEY UPDATE
	TypeOfAtomicInformationID=VALUES(TypeOfAtomicInformationID),
	VersionNumber=VALUES(VersionNumber), 
	IsCurrentVersion=VALUES(IsCurrentVersion), 
	PreviousVersionReference=VALUES(PreviousVersionReference), 
	EntityActive=VALUES(EntityActive), 
	Content=VALUES(Content),
	ProjectID=VALUES(ProjectID);

-- -----------------------------------------------------
-- Insert Data into distrbutionrecipient  Table
-- -----------------------------------------------------
INSERT INTO distributionrecipient (`ProjectID`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`, `FirstName`, `Surname`, `EMailAddress`, `MobileNumber`, `OfficeNumber`, `CompanyName`) 
	VALUES (1, 1, true, NULL, true, 'John', 'Doe', 'john.doe@somecompany.com', '123456789', '987654321', 'Test Company 1') ON DUPLICATE KEY UPDATE
	ProjectID=VALUES(ProjectID),
	VersionNumber=VALUES(VersionNumber), 
	IsCurrentVersion=VALUES(IsCurrentVersion), 
	PreviousVersionReference=VALUES(PreviousVersionReference), 
	EntityActive=VALUES(EntityActive), 
	FirstName=VALUES(FirstName),
	Surname=VALUES(Surname),
	EMailAddress=VALUES(EMailAddress),
	MobileNumber=VALUES(MobileNumber),
	OfficeNumber=VALUES(OfficeNumber),
	CompanyName=VALUES(CompanyName);

INSERT INTO distributionrecipient (`ProjectID`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`, `FirstName`, `Surname`, `EMailAddress`, `MobileNumber`, `OfficeNumber`, `CompanyName`) 
	VALUES (1, 1, true, NULL, true, 'Joe', 'Bloggs', 'joe.bloggs@somecompany.com', '123456789', '987654321', 'Test Company 2') ON DUPLICATE KEY UPDATE
	ProjectID=VALUES(ProjectID),
	VersionNumber=VALUES(VersionNumber), 
	IsCurrentVersion=VALUES(IsCurrentVersion), 
	PreviousVersionReference=VALUES(PreviousVersionReference), 
	EntityActive=VALUES(EntityActive), 
	FirstName=VALUES(FirstName),
	Surname=VALUES(Surname),
	EMailAddress=VALUES(EMailAddress),
	MobileNumber=VALUES(MobileNumber),
	OfficeNumber=VALUES(OfficeNumber),
	CompanyName=VALUES(CompanyName);

INSERT INTO distributionrecipient (`ProjectID`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`, `FirstName`, `Surname`, `EMailAddress`, `MobileNumber`, `OfficeNumber`, `CompanyName`) 
	VALUES (1, 1, true, NULL, true, 'Jane', 'Roe', 'jane.roe@somecompany.com', '123456789', '987654321', 'Test Company 1') ON DUPLICATE KEY UPDATE
	ProjectID=VALUES(ProjectID),
	VersionNumber=VALUES(VersionNumber), 
	IsCurrentVersion=VALUES(IsCurrentVersion), 
	PreviousVersionReference=VALUES(PreviousVersionReference), 
	EntityActive=VALUES(EntityActive), 
	FirstName=VALUES(FirstName),
	Surname=VALUES(Surname),
	EMailAddress=VALUES(EMailAddress),
	MobileNumber=VALUES(MobileNumber),
	OfficeNumber=VALUES(OfficeNumber),
	CompanyName=VALUES(CompanyName);
-- -----------------------------------------------------
-- Insert Data into artefactdistribution Table
-- -----------------------------------------------------
INSERT INTO artefactdistribution (`ArtefactID`, `MethodOfDistributionID`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`, `DateOfArtefactDistribution`, `DistributionRecipientID`) 
	VALUES (2, 1, 1, true, NULL, true, '2014-07-25', 1) ON DUPLICATE KEY UPDATE
	ArtefactID=VALUES(ArtefactID),
	MethodOfDistributionID=VALUES(MethodOfDistributionID),
	VersionNumber=VALUES(VersionNumber), 
	IsCurrentVersion=VALUES(IsCurrentVersion), 
	PreviousVersionReference=VALUES(PreviousVersionReference), 
	EntityActive=VALUES(EntityActive),
	DateOfArtefactDistribution=VALUES(DateOfArtefactDistribution),
	DistributionRecipientID=VALUES(DistributionRecipientID);

INSERT INTO artefactdistribution (`ArtefactID`, `MethodOfDistributionID`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`, `DateOfArtefactDistribution`, `DistributionRecipientID`) 
	VALUES (3, 2, 1, true, NULL, true, '2014-07-12', 2) ON DUPLICATE KEY UPDATE
	ArtefactID=VALUES(ArtefactID),
	MethodOfDistributionID=VALUES(MethodOfDistributionID),
	VersionNumber=VALUES(VersionNumber), 
	IsCurrentVersion=VALUES(IsCurrentVersion), 
	PreviousVersionReference=VALUES(PreviousVersionReference), 
	EntityActive=VALUES(EntityActive),
	DateOfArtefactDistribution=VALUES(DateOfArtefactDistribution),
	DistributionRecipientID=VALUES(DistributionRecipientID);

INSERT INTO artefactdistribution (`ArtefactID`, `MethodOfDistributionID`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`, `DateOfArtefactDistribution`, `DistributionRecipientID`) 
	VALUES (2, 1, 1, true, NULL, true, '2014-07-14', 3) ON DUPLICATE KEY UPDATE
	ArtefactID=VALUES(ArtefactID),
	MethodOfDistributionID=VALUES(MethodOfDistributionID),
	VersionNumber=VALUES(VersionNumber), 
	IsCurrentVersion=VALUES(IsCurrentVersion), 
	PreviousVersionReference=VALUES(PreviousVersionReference), 
	EntityActive=VALUES(EntityActive),
	DateOfArtefactDistribution=VALUES(DateOfArtefactDistribution),
	DistributionRecipientID=VALUES(DistributionRecipientID);

-- -----------------------------------------------------
-- Insert Data into artefactatomicinfromation Table
-- -----------------------------------------------------

INSERT INTO artefactatomicinformation ( `AtomicInformationID`, `ArtefactID`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`) 
	VALUES (1, 1, 1, true, NULL, true) ON DUPLICATE KEY UPDATE
	AtomicInformationID=VALUES(AtomicInformationID),
	ArtefactID=VALUES(ArtefactID),
	VersionNumber=VALUES(VersionNumber), 
	IsCurrentVersion=VALUES(IsCurrentVersion), 
	PreviousVersionReference=VALUES(PreviousVersionReference), 
	EntityActive=VALUES(EntityActive);

INSERT INTO artefactatomicinformation ( `AtomicInformationID`, `ArtefactID`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`) 
	VALUES (2, 1, 1, true, NULL, true) ON DUPLICATE KEY UPDATE
	AtomicInformationID=VALUES(AtomicInformationID),
	ArtefactID=VALUES(ArtefactID),
	VersionNumber=VALUES(VersionNumber), 
	IsCurrentVersion=VALUES(IsCurrentVersion), 
	PreviousVersionReference=VALUES(PreviousVersionReference), 
	EntityActive=VALUES(EntityActive);

INSERT INTO artefactatomicinformation ( `AtomicInformationID`, `ArtefactID`, `VersionNumber`, `IsCurrentVersion`, `PreviousVersionReference`, `EntityActive`) 
	VALUES (3, 2, 1, true, NULL, true) ON DUPLICATE KEY UPDATE
	AtomicInformationID=VALUES(AtomicInformationID),
	ArtefactID=VALUES(ArtefactID),
	VersionNumber=VALUES(VersionNumber), 
	IsCurrentVersion=VALUES(IsCurrentVersion), 
	PreviousVersionReference=VALUES(PreviousVersionReference), 
	EntityActive=VALUES(EntityActive);