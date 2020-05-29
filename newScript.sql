USE [master]
GO

/****** Object:  Database [SuperSmashDatabase]    Script Date: 5/21/2020 12:10:52 PM ******/
CREATE DATABASE [SuperSmashDatabasedataimport2]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SuperSmashDatabasedataimport2', FILENAME = N'E:\Database\MSSQL12.MSSQLSERVER\MSSQL\DATA\SuperSmashDatabasedataimport2.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 10%)
 LOG ON 
( NAME = N'SuperSmashDatabasedataimport2_log', FILENAME = N'E:\Database\MSSQL12.MSSQLSERVER\MSSQL\DATA\SuperSmashDatabasedataimport_log2.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO

IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SuperSmashDatabasedataimport2].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO

USE [SuperSmashDatabasedataimport2]



/****** Object:  User [appUserSuperSmash]    Script Date: 5/21/2020 12:12:02 PM ******/
CREATE USER [appUserSuperSmash] FOR LOGIN [appUserSuperSmash] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_datareader] ADD MEMBER [appUserSuperSmash]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [appUserSuperSmash]
GO
/****** Object:  Table [dbo].[Character]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Character](
	[GameName] [varchar](50) NOT NULL,
	[Name] [varchar](50) NOT NULL,
	[Origin] [varchar](50) NULL,
	[Speed] [int] NULL,
	[Weight] [int] NULL,
	[Image] [image] NULL,
 CONSTRAINT [PK_Character] PRIMARY KEY CLUSTERED 
(
	[GameName] ASC,
	[Name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Game]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Game](
	[Name] [varchar](50) NOT NULL,
	[Made] [date] NOT NULL,
	[Consoles] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Game] PRIMARY KEY CLUSTERED 
(
	[Name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Item]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Item](
	[Name] [varchar](50) NOT NULL,
	[Origin] [varchar](50) NULL,
	[Type] [int] NULL,
	[GameName] [varchar](50) NOT NULL,
	[Image] [image] NULL,
 CONSTRAINT [PK__Item] PRIMARY KEY CLUSTERED 
(
	[Name] ASC,
	[GameName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UC_ItemName] UNIQUE NONCLUSTERED 
(
	[Name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Move]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Move](
	[CharacterName] [varchar](50) NOT NULL,
	[GameName] [varchar](50) NOT NULL,
	[Type] [int] NOT NULL,
	[Direction] [int] NOT NULL,
	[DamageStartFrame] [int] NULL,
	[DamageEndFrame] [int] NULL,
	[MoveID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](50) NULL,
 CONSTRAINT [PK__Move__0703338A8133C018] PRIMARY KEY CLUSTERED 
(
	[MoveID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Stage]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Stage](
	[Name] [varchar](50) NOT NULL,
	[Origin] [varchar](50) NULL,
	[Music] [varchar](50) NULL,
	[GameName] [varchar](50) NOT NULL,
	[Image] [image] NULL,
 CONSTRAINT [PK__Stage] PRIMARY KEY CLUSTERED 
(
	[Name] ASC,
	[GameName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UC_StageName] UNIQUE NONCLUSTERED 
(
	[Name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[PasswordSalt] [varchar](50) NOT NULL,
	[Username] [varchar](50) NOT NULL,
	[PasswordHash] [varchar](50) NOT NULL,
	[Rank] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_USER] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User Favorite]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User Favorite](
	[Username] [varchar](50) NOT NULL,
	[Character] [varchar](50) NULL,
	[Game] [varchar](50) NULL,
	[CharacterGameName] [varchar](50) NULL,
 CONSTRAINT [PK_User Favorite] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Character]  WITH CHECK ADD  CONSTRAINT [FK__Character] FOREIGN KEY([GameName])
REFERENCES [dbo].[Game] ([Name])
GO
ALTER TABLE [dbo].[Character] CHECK CONSTRAINT [FK__Character]
GO
ALTER TABLE [dbo].[Item]  WITH CHECK ADD  CONSTRAINT [FK__Item] FOREIGN KEY([GameName])
REFERENCES [dbo].[Game] ([Name])
GO
ALTER TABLE [dbo].[Item] CHECK CONSTRAINT [FK__Item]
GO
ALTER TABLE [dbo].[Move]  WITH CHECK ADD  CONSTRAINT [FK_Move_Character] FOREIGN KEY([GameName], [CharacterName])
REFERENCES [dbo].[Character] ([GameName], [Name])
GO
ALTER TABLE [dbo].[Move] CHECK CONSTRAINT [FK_Move_Character]
GO
ALTER TABLE [dbo].[Stage]  WITH CHECK ADD  CONSTRAINT [FK__Stage] FOREIGN KEY([GameName])
REFERENCES [dbo].[Game] ([Name])
GO
ALTER TABLE [dbo].[Stage] CHECK CONSTRAINT [FK__Stage]
GO
ALTER TABLE [dbo].[User Favorite]  WITH CHECK ADD  CONSTRAINT [FK__User Favo__Usern] FOREIGN KEY([Username])
REFERENCES [dbo].[User] ([Username])
GO
ALTER TABLE [dbo].[User Favorite] CHECK CONSTRAINT [FK__User Favo__Usern]
GO
ALTER TABLE [dbo].[Stage]  WITH CHECK ADD  CONSTRAINT [CHECK_quotes] CHECK  (([Music] like '"%"'))
GO
ALTER TABLE [dbo].[Stage] CHECK CONSTRAINT [CHECK_quotes]
GO
/****** Object:  StoredProcedure [dbo].[delete_Character]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*** HEADER ***
Name - Cullen LaKemper

Date - 5/1/2020

Purpose – To delete a Character from the Smash Bros Series into the database.

Example – To delete Mario from Melee to the database, we would use 

EXEC delete_Character
@GameName_1 Melee
@Name_2 Mario
*/

CREATE PROCEDURE [dbo].[delete_Character]
(@GameName_1 varchar(50),
 @Name_2 varchar(50))
AS

--check if game exists
IF ((SELECT COUNT(Name) FROM [Game] WHERE  @GameName_1 = Name) = 0)
BEGIN
	PRINT 'Game does not exist in database'
	RETURN (1)
END

-- check if the character already exists in the database
IF ((SELECT COUNT(Name) FROM [Character] WHERE Name = @Name_2 and @GameName_1 = GameName) = 0)
BEGIN
	PRINT 'Character does not exist in database.'
	RETURN (1)
END

DELETE FROM Character
WHERE Name = @Name_2 and GameName=@GameName_1

PRINT ('The Character was successfully deleted.')
RETURN(0)
GO
/****** Object:  StoredProcedure [dbo].[delete_Costume]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*** HEADER ***
Name - Cullen LaKemper

Date - 5/1/2020

Purpose – To delete a Costume from the Smash Bros Series into the database.

Example – To delete a costume use

EXEC delete_Costume
@CostumeID 20
*/
CREATE PROCEDURE [dbo].[delete_Costume]
(
@CostumeID int
 )
AS

--check if the move exists
IF ((SELECT COUNT(CostumeID) FROM [Costume] WHERE CostumeID = @CostumeID) = 0)
BEGIN
	PRINT 'Costume does not exist'
	RETURN (1)
END

--insert move
DELETE FROM Costume
WHERE CostumeID = @CostumeID

PRINT ('The Costume was successfully added.')
RETURN(0)
GO
/****** Object:  StoredProcedure [dbo].[delete_Game]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*** HEADER ***
Name - Russel Staples

Date - 5/6/2020

Purpose – To delete a Game from the database
Example – If you want to remove "Super Smash Bros Ultimate" from the table
EXEC delete_Game
@Name_1 = 'Super Smash Bros Ultimate'
*/

CREATE PROCEDURE [dbo].[delete_Game]
(@Name_1 varchar(50))
AS 

-- Checks to see if the Stage exists in the table
IF ((SELECT COUNT(Name) FROM Game WHERE Name = @Name_1 ) <= 0)
BEGIN
	PRINT ('Game currently does not exist in the Database')
	RETURN (1)
END

DELETE FROM Game WHERE Name = @Name_1
IF (@@ERROR = 0)
BEGIN
	PRINT ('The Game was successfully deleted from the database.')
END
RETURN(0)
GO
/****** Object:  StoredProcedure [dbo].[delete_Item]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*** HEADER ***
Name - Jasmine Scott

CREATED - 5/13/2020

Purpose – To delete a item from the database.
Example –
EXEC delete_Item
@Name_1 Shooting Star
@GameName_2 SuperSmash

*/
CREATE PROCEDURE [dbo].[delete_Item]
(@Name_1 varchar(50),
@GameName_2 varchar(50))
AS 

-- Check if a Game already exits. If not, Raise Error.
IF ((SELECT COUNT(Name) FROM [Game] WHERE Name = @GameName_2) < 0)
BEGIN
	PRINT ('Game does not exist in the database')
	RETURN (1)
END

-- Check if a Stage already exits. If not, Raise Error.
IF ((SELECT COUNT(Name) FROM [Item] WHERE Name = @Name_1 AND GameName = @GameName_2) < 0)
BEGIN
	PRINT ('Stage is not in the database')
	RETURN (2)
END

DELETE FROM Item WHERE Name = @Name_1 AND GameName = @GameName_2
IF (@@ERROR = 0)
BEGIN
	PRINT ('The Item was successfully deleted from the database.')
END
RETURN(0)
GO
/****** Object:  StoredProcedure [dbo].[delete_Move]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*** HEADER ***
Name - Cullen LaKemper

Date - 5/1/2020

Purpose – To delete a Move from the Smash Bros Series into the database.

Example – To delete a move use

EXEC delete_Move
@MoveID 20
*/
CREATE PROCEDURE [dbo].[delete_Move]
(
 @CharacterName varchar(50),
 @GameName varchar(50),
 @Type int,
 @Direction int
 )
AS

--check if the move exists
IF ((SELECT COUNT(MoveID) FROM [Move] WHERE CharacterName = @CharacterName AND @GameName = GameName AND @Type = [Type] AND @Direction = Direction) = 0)
BEGIN
	PRINT 'Move does not exist'
	RETURN (1)
END

--insert move
DELETE FROM Move
WHERE CharacterName = @CharacterName AND @GameName = GameName AND @Type = [Type] AND @Direction = Direction

PRINT ('The Move was successfully added.')
RETURN(0)
GO
/****** Object:  StoredProcedure [dbo].[delete_Stage]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*** HEADER ***
Name - Jasmine Scott

Date - 5/13/2020

Purpose – To delete a stage from the database. Stages are only from Melee, Brawl, Smash 4, and Ultimate.
Example – If you want to remove "Big Blue" from the table
EXEC update_Stages
@Name_1 Big Blue
@GameName_2 SuperSmash

*/
CREATE PROCEDURE [dbo].[delete_Stage]
(@Name_1 varchar(50),
@GameName_2 varchar(50))
AS 

-- Check if a Game already exits. If not, Raise Error.
IF ((SELECT COUNT(Name) FROM [Game] WHERE Name = @GameName_2) < 0)
BEGIN
	PRINT ('Game does not exist in the database')
	RETURN (1)
END

-- Check if a Stage already exits. If not, Raise Error.
IF ((SELECT COUNT(Name) FROM [Stage] WHERE Name = @Name_1 AND GameName = @GameName_2) < 0)
BEGIN
	PRINT ('Stage is not in the database')
	RETURN (2)
END

DELETE FROM Stage WHERE Name = @Name_1 AND GameName = @GameName_2
IF (@@ERROR = 0)
BEGIN
	PRINT ('The Stage was successfully deleted from the database.')
END
RETURN(0)
GO
/****** Object:  StoredProcedure [dbo].[delete_User]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
Name - Russel Staples

Date - 5/20/2020

Purpose – To delete a User from the database.

*/

CREATE PROCEDURE [dbo].[delete_User]
(@Username_1 varchar(50))
AS

DELETE FROM [User] WHERE Username = @Username_1

IF (@@ERROR = 0)
BEGIN
	PRINT ('Your account has been deleted')
END

RETURN(0)

GO
/****** Object:  StoredProcedure [dbo].[insert_Character]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*** HEADER ***
Name - Cullen LaKemper

Date - 5/1/2020

Purpose – To insert a Character from the Smash Bros Series into the database.

Example – To add Mario from Melee to the database, we would use 

EXEC insert_Character
@GameName_1 Melee
@Name_2 Mario
@Costume_3 howevercostumewillbeformatted
@Origin_4 Origintext
*/

CREATE PROCEDURE [dbo].[insert_Character]
(@GameName_1 varchar(50),
 @Name_2 varchar(50),
 @Origin_3 varchar(50) = NULL,
 @Speed_4 int = NULL,
 @Weight_5 int = NULL)
AS

--check if game exists
IF ((SELECT COUNT(Name) FROM [Game] WHERE  @GameName_1 = Name) = 0)
BEGIN
	PRINT 'Game does not exist in database'
	RETURN (1)
END

-- check if the character already exists in the database
IF ((SELECT COUNT(Name) FROM [Character] WHERE Name = @Name_2 and @GameName_1 = GameName) > 0)
BEGIN
	PRINT 'Character already exists in database'
	RETURN (1)
END

-- insert Chracter into database
INSERT INTO Character([GameName], [Name], Origin, Speed, Weight)
VALUES (@GameName_1,  @Name_2, @Origin_3, @Speed_4, @Weight_5)

PRINT ('The Character was successfully added to the database.')
RETURN(0)
GO
/****** Object:  StoredProcedure [dbo].[insert_Costume]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*** HEADER ***
Name - Cullen LaKemper

Date - 5/1/2020

Purpose – To insert a Costume from the Smash Bros Series into the database.

Example – To insert a Costume

EXEC insert_Costume
@CharacterName Mario
@GameName Melee
@Image imagebinary
*/

CREATE PROCEDURE [dbo].[insert_Costume]
 (
 @CharacterName varchar(50),
 @GameName varchar(50),
 @Image image
 )
AS

--check if game exists
IF ((SELECT COUNT(Name) FROM [Game] WHERE  @GameName = Name) = 0)
BEGIN
	PRINT 'Game does not exist in database'
	RETURN (1)
END

-- check if charcter the exists in the database
IF ((SELECT COUNT(Name) FROM [Character] WHERE Name = @CharacterName and @GameName = GameName) = 0)
BEGIN
	PRINT 'Character does not exist in database, you may want to add'
	RETURN (2)
END

--insert move
INSERT INTO Costume(Image, GameName,  CharacterName)
VALUES (@Image, @GameName, @CharacterName)

PRINT ('The Costume was successfully added.')
RETURN(0)
GO
/****** Object:  StoredProcedure [dbo].[insert_Game]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*** HEADER ***
Name - Russel Staples

Date - 4/30/2020

Purpose – To insert a Game from the Smash Bros Series into the database.

Example – To add Super Smash Ultimate to the database, we would use 
EXEC insert_Game
@Name_1 = 'Super Smash Bros Ultimate'
@Made_2 '2018-05-06'
@Consoles_3 = 'Switch'
*/

CREATE PROCEDURE [dbo].[insert_Game]
(@Name_1 varchar(50),
 @Made_2 varchar(50),
 @Consoles_3 varchar(50))
AS

-- check if the Game already exists in the database
IF ((SELECT COUNT(Name) FROM [Game] WHERE Name = @Name_1 ) > 0)
BEGIN
	PRINT 'Game already exists in database'
	RETURN (1)
END

-- Check to see if Name was left NULL
IF (@Name_1 is NULL OR @Name_1 = '')
BEGIN
	PRINT ('Game Name cannot be NULL')
	RETURN (2)
END

-- insert Game into database
INSERT INTO Game([Name], Made, Consoles)
VALUES (@Name_1, @Made_2, @Consoles_3)

PRINT ('The Game was successfully added to the database.')
RETURN(0)
GO
/****** Object:  StoredProcedure [dbo].[insert_Item]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*** HEADER ***
Name - Jasmine Scott

UPDATED - 5/13/2020

Purpose – To insert an item into the database. Multiple instances of the same item will show up in this table.
EXEC insert_Item
@Name_1 varchar(50) = Star Shooter
@Origin_2 varchar(50) = NULL
 @Type_4 int = 1
 @GameName_5 varchar(50) = Super Smash Ultimate
*/

CREATE PROCEDURE [dbo].[insert_Item]
(@Name_1 varchar(50),
@Origin_2 varchar(50) = NULL,
 @Type_3 int = NULL,
 @GameName_4 varchar(50))
AS

-- Check if a Game already exits. If not, Raise Error.
IF ((SELECT COUNT(Name) FROM [Game] WHERE Name = @GameName_4) < 0)
BEGIN
	PRINT ('Game does not exist in the database')
	RETURN (1)
END

-- Check if a Item already exits. If so, Raise Error.
IF ((SELECT COUNT(Name) FROM [Item] WHERE Name = @Name_1 AND GameName = @GameName_4) > 0)
BEGIN
	PRINT ('Item is already in database')
	RETURN (2)
END

-- Check to see if Name was left NULL
IF (@Name_1 is NULL OR @Name_1 = '')
BEGIN
	PRINT ('Item Name cannot be NULL')
	RETURN (3)

END

-- Check to see if Game was left NULL
IF (@GameName_4 is NULL OR @GameName_4 = '')
BEGIN
	PRINT ('GameName cannot be NULL')
	RETURN (4)
END

INSERT INTO Item
( [Name], [Origin], [Type], [GameName])
VALUES ( @Name_1, @Origin_2, @Type_3, @GameName_4)

IF (@@ERROR = 0)
BEGIN
	PRINT ('The Item was successfully added to the database.')
END

RETURN(0)
GO
/****** Object:  StoredProcedure [dbo].[insert_Move]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*** HEADER ***
Name - Cullen LaKemper

Date - 5/1/2020

Purpose – To insert a Move from the Smash Bros Series into the database.

Example – To update Mario from Melee to the database, we would use 

EXEC update_Character
@GameName_1 Melee
@Name_2 Mario
@Costume_3 howevercostumewillbeformatted
@Origin_4 Origintext
*/

CREATE PROCEDURE [dbo].[insert_Move]
(@Name varchar(50) = NULL,
 @CharacterName varchar(50),
 @GameName varchar(50),
 @Type int,
 @Direction int,
 @DamageStartFrame int = NULL,
 @DamageEndFrame int = NULL
 )
AS

--check if the move exists
IF ((SELECT COUNT(MoveID) FROM [Move] WHERE CharacterName = @CharacterName and @GameName = GameName and [Type] = @Type and Direction = @Direction) > 0)
BEGIN
	UPDATE [Move]
	SET DamageStartFrame = @DamageStartFrame, DamageEndFrame = @DamageEndFrame
	PRINT ('The Move was successfully updated')
	RETURN(1)
END
--insert move
INSERT INTO Move(Name, CharacterName, GameName, Type, Direction, DamageStartFrame, DamageEndFrame)
VALUES (@Name, @CharacterName, @GameName, @Type, @Direction, @DamageStartFrame, @DamageEndFrame)

PRINT ('The Move was successfully added.')
RETURN(0)
GO
/****** Object:  StoredProcedure [dbo].[insert_Stage]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*** HEADER ***
Name - Jasmine Scott

UPDATED - 5/13/2020

Purpose – To insert a stage into the database. Stages come from Melee, Brawl, Ultimate, and Smash 4. Stages can show up multiple
times within this table. To distinguish between them, a GameName must be specificed.
Example – To add Big Blue to the database, we would use 
EXEC insert_Stages
@Name_1 Big Blue
@Origin_2 SubZero
@Music_3 “Big Blue”
@GameName_4 Super Smash Ultimate
*/

CREATE PROCEDURE [dbo].[insert_Stage]
(@Name_1 varchar(50),
@Origin_2 varchar(50) = NULL,
 @Music_3 varchar(50) = NULL,
 @GameName_4 varchar(50))
AS

-- Check if a Game already exits. If not, Raise Error.
IF ((SELECT COUNT(Name) FROM [Game] WHERE Name = @GameName_4) < 0)
BEGIN
	PRINT ('Game does not exist in the database')
	RETURN (1)
END

-- Check if a Stage already exits. If so, Raise Error.
IF ((SELECT COUNT(Name) FROM [Stage] WHERE Name = @Name_1 AND GameName = @GameName_4) > 0)
BEGIN
	PRINT ('Stage is already in database')
	RETURN (2)
END

-- Check to see if Name was left NULL
IF (@Name_1 is NULL OR @Name_1 = '')
BEGIN
	PRINT ('Stage Name cannot be NULL')
	RETURN (3)

END

-- Check to see if Game was left NULL
IF (@GameName_4 is NULL OR @GameName_4 = '')
BEGIN
	PRINT ('GameName cannot be NULL')
	RETURN (4)
END

INSERT INTO Stage
( [Name], [Origin], [Music], [GameName])
VALUES ( @Name_1, @Origin_2, '"' + @Music_3 + '"', @GameName_4)

IF (@@ERROR = 0)
BEGIN
	PRINT ('The Stage was successfully added to the database.')
END

RETURN(0)
GO
/****** Object:  StoredProcedure [dbo].[insert_User]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*** HEADER ***
Name - Jasmine Scott

CREATED - 5/13/2020

Purpose – To add a User into the database. The password will be

EXEC insert_Stages
@Username_1 dannyphantom123
@Passwordsalt_2 sdfadfaksdlgaf
@Passwordhash_3 asdfasdflasdf

*/

CREATE PROCEDURE [dbo].[insert_User]
(@Username_1 varchar(50),
@Passwordsalt_2 varchar(50),
@Passwordhash_3 varchar(50))
AS

IF (@Username_1 is NULL OR @Username_1 = '')
BEGIN
	PRINT('Cannot leave Username blank')
	RETURN (1)
END

IF (@Passwordsalt_2 is NULL OR @Passwordsalt_2 = '')
BEGIN
	PRINT('Cannot leave Password Salt blank')
	RETURN (2)
END

IF (@Passwordhash_3 is NULL OR @Passwordhash_3 = '')
BEGIN
	PRINT('Cannot leave Password Hash blank')
	RETURN (3)
END

IF ((SELECT COUNT(Username) FROM [User] WHERE Username = @Username_1) > 0)
BEGIN
	PRINT('This username already exists. Please choose another name')
	RETURN (4)
END

INSERT INTO [User]
( [Username], [PasswordSalt], [PasswordHash])
VALUES ( @Username_1, @Passwordsalt_2, @Passwordhash_3)

IF (@@ERROR = 0)
BEGIN
	PRINT ('Your account has been registered')
END

RETURN(0)

GO
/****** Object:  StoredProcedure [dbo].[update_Character]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*** HEADER ***
Name - Cullen LaKemper

Date - 5/1/2020

Purpose – To update a Character from the Smash Bros Series into the database.

Example – To update Mario from Melee to the database, we would use 

EXEC update_Character
@GameName_1 Melee
@Name_2 Mario
@Costume_3 howevercostumewillbeformatted
@Origin_4 Origintext
*/

CREATE PROCEDURE [dbo].[update_Character]
(@GameName_1 varchar(50),
 @Name_2 varchar(50),
 @Origin_3 varchar(50) = NULL,
 @Speed_4 int = NULL,
 @Weight_5 int = NULL)
AS

--check if game exists
IF ((SELECT COUNT(Name) FROM [Game] WHERE  @GameName_1 = Name) = 0)
BEGIN
	PRINT 'Game does not exist in database'
	RETURN (1)
END

-- check if the character already exists in the database
IF ((SELECT COUNT(Name) FROM [Character] WHERE Name = @Name_2 and @GameName_1 = GameName) = 0)
BEGIN
	PRINT 'Character does not exist in database, you may want to add'
	RETURN (1)
END

--update costume if provided
IF @Origin_3 is not NULL
BEGIN
Update Character
SET Origin=@Origin_3
WHERE GameName=@GameName_1 and Name = @Name_2
END

--update speed if provided
IF @Speed_4 is not NULL
BEGIN
Update Character
SET Speed=@Speed_4
WHERE GameName=@GameName_1 and Name = @Name_2
END

--update weight if provided
IF @Weight_5 is not NULL
BEGIN
Update Character
SET Weight=@Weight_5
WHERE GameName=@GameName_1 and Name = @Name_2
END

PRINT ('The Character was successfully updated.')
RETURN(0)
GO
/****** Object:  StoredProcedure [dbo].[update_Costume]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*** HEADER ***
Name - Cullen LaKemper

Date - 5/1/2020

Purpose – To update a Costume from the Smash Bros Series into the database.

Example – To update a costume 20 use

EXEC update_Costume
@CostumeID 20
@Image imagebinary
*/
CREATE PROCEDURE [dbo].[update_Costume]
(
 @CostumeID int,
 @Image image
 )
AS

--check if the Costume exists
IF ((SELECT COUNT(CostumeID) FROM [Costume] WHERE CostumeID = @CostumeID) = 0)
BEGIN
	PRINT 'Costume does not exist'
	RETURN (1)
END

--update Image
UPDATE Costume
SET Image = @Image
WHERE CostumeID = @CostumeID

PRINT ('The Costume was successfully added.')
RETURN(0)
GO
/****** Object:  StoredProcedure [dbo].[update_Fav]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[update_Fav]
(@Name_1 varchar(50),
@Game_2 varchar(50) = NULL,
 @Character_3 varchar(50) = NULL)
AS 

IF ((SELECT COUNT([Name]) FROM [Game] WHERE [Name] = @Game_2) = 0)
BEGIN
	PRINT ('Game does not exist in the database')
	RETURN (1)
END


IF ((SELECT COUNT([Name]) FROM [Character] WHERE [Name] = @Character_3) = 0)
BEGIN
	PRINT ('Character does not exist in the database')
	RETURN (2)
END

IF ((SELECT COUNT([Username]) FROM [User Favorite] WHERE [Username] = @Name_1) = 0)
BEGIN
	INSERT INTO [User Favorite]
	VALUES(@Name_1, NULL, NULL, NULL)
END

UPDATE [User Favorite] 
SET [Game] = @Game_2, [Character] = @Character_3
WHERE [Username] = @Name_1

IF (@@ERROR = 0)
BEGIN
	PRINT ('Favorites were successfully updated')
END
RETURN(0)
GO
/****** Object:  StoredProcedure [dbo].[update_Game]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*** HEADER ***
Name - Russel Staples

Date - 5/6/2020

Purpose – To update a Game in the database. 
Example – update_Game
EXEC update_Game 'Super Smash Bros Ultimate', @Consoles_5 = 'Nintendo Switch'

*/
CREATE PROCEDURE [dbo].[update_Game]
(@Name_1 varchar(50),
@Made_2 varchar(50) = NULL,
@Consoles_3 varchar(50) = NULL)
AS

-- Check to see if Name was left NULL
IF (@Name_1 is NULL OR @Name_1 = '')
BEGIN
	PRINT ('Stage Name cannot be NULL')
	RETURN (2)
END

-- Checks to see if the Game exists in the table
IF ((SELECT COUNT(Name) FROM Game WHERE Name = @Name_1 ) <= 0)
BEGIN
	PRINT ('Game currently does not exist in the Database')
	RETURN (1)
END

-- This is where we update what needs to be updated

IF (@Made_2 IS NOT NULL)
BEGIN
	UPDATE Game
	SET Made = @Made_2
	WHERE (Name = @Name_1)
END

IF (@Consoles_3 IS NOT NULL)
BEGIN
	UPDATE Game
	SET Consoles = @Consoles_3
	WHERE (Name = @Name_1)
END

IF (@@ERROR = 0)
BEGIN
	PRINT ('The Stage was successfully updated.')
END
RETURN(0)
GO
/****** Object:  StoredProcedure [dbo].[update_Item]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*** HEADER ***
Name - Jasmine Scott

CREATED - 5/13/2020

Purpose – To update an item into the database. Multiple instances of the same item will show up in this table.
EXEC insert_Item
@Name_1 varchar(50) = Star Shooter
@Origin_2 varchar(50) = NULL
@Type_4 int = NULL
@GameName_5 varchar(50) = Super Smash Ultimate


Note - You could update the Origin, Music or both. However, you cannot leave all parameters blank (because then there's no
reason to be calling update). 

***This method ASSUMES you do not want to change the name of a item (as item names don't really change). In the case
that a item was added to the database with an incorrect spelling, delete that stage instead.***

*/

CREATE PROCEDURE [dbo].[update_Item]
(@Name_1 varchar(50),
@Origin_2 varchar(50) = NULL,
 @Type_3 int = NULL,
 @GameName_4 varchar(50))
AS

-- Check if a Game already exits. If not, Raise Error.
IF ((SELECT COUNT(Name) FROM [Game] WHERE Name = @GameName_4) < 0)
BEGIN
	PRINT ('Game does not exist in the database')
	RETURN (1)
END

-- Check if a Item already exits. If so, Raise Error.
IF ((SELECT COUNT(Name) FROM [Item] WHERE Name = @Name_1 AND GameName = @GameName_4) < 0)
BEGIN
	PRINT ('Stage is already in database')
	RETURN (2)
END

-- Checks to see if the @Origin and @Music parametes are both NULL
IF ((@Origin_2 IS NULL AND @Type_3 IS NULL) OR (@Origin_2 = '' AND @Type_3 = ''))
BEGIN
	PRINT('Cannot leave both Origin, Projectile, and Type Empty')
	RETURN (3)
END

-- This is where we update what needs to be updated
IF (@Origin_2 IS NOT NULL)
BEGIN
	UPDATE [Item]
	SET [Origin] = @Origin_2
	WHERE (Name = @Name_1 AND GameName = @GameName_4)
END

IF (@Type_3 IS NOT NULL)
BEGIN
	UPDATE [Item]
	SET [Type] = @Type_3
	WHERE (Name = @Name_1 AND GameName = @GameName_4)
END


IF (@@ERROR = 0)
BEGIN
	PRINT ('The Item was successfully updated.')
END
RETURN(0)
GO
/****** Object:  StoredProcedure [dbo].[update_Move]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*** HEADER ***
Name - Cullen LaKemper

Date - 5/1/2020

Purpose – To update a Move from the Smash Bros Series into the database.

Example – To update a move 20 use

EXEC update_Move
@MoveID 20
@Name exampleMove
@Type 0
@Direction 0
@DamageStartFrame 4
@DamageEndFrame 10
*/
CREATE PROCEDURE [dbo].[update_Move]
(
 @MoveID int,
 @Name varchar(50) = NULL,
 @Type int = NULL,
 @Direction int = NULL,
 @DamageStartFrame int = NULL,
 @DamageEndFrame int = NULL
 )
AS

--check if the move exists
IF ((SELECT COUNT(MoveID) FROM [Move] WHERE MoveID = @MoveID) = 0)
BEGIN
	PRINT 'Move does not exist'
	RETURN (1)
END

--update Name if provided
IF @Name is not null
BEGIN
UPDATE Move
SET Name = @Name
WHERE MoveID = @MoveID
END

--update Type if provided
IF @Type is not null
BEGIN
UPDATE Move
SET Type = @Type
WHERE MoveID = @MoveID
END

--update Direction if provided
IF @Direction is not null
BEGIN
UPDATE Move
SET Direction = @Direction
WHERE MoveID = @MoveID
END

--update DamageStartFrame if provided
IF @DamageStartFrame is not null
BEGIN
UPDATE Move
SET @DamageStartFrame = @DamageStartFrame
WHERE MoveID = @MoveID
END

--update DamageEndFrame if provided
IF @DamageEndFrame is not null
BEGIN
UPDATE Move
SET DamageEndFrame = @DamageEndFrame
WHERE MoveID = @MoveID
END


PRINT ('The Move was successfully added.')
RETURN(0)
GO
/****** Object:  StoredProcedure [dbo].[update_Stage]    Script Date: 5/21/2020 12:12:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*** HEADER ***
Name - Jasmine Scott

UPDATED - 5/13/2020

Purpose – To update a stage into the database. Stages are only from Melee, Brawl, Smash 4, and Ultimate.
Example – Perhaps you want to update the stage "Big Blue" to have different music. 
EXEC update_Stages
@Name_1 Big Blue
@Origin_2 SubZero
@Music_3 “This ain't a scene it's an arm race”
@GameName_4 SuperSmashUltimate

Note - You could update the Origin, Music or both. However, you cannot leave all parameters blank (because then there's no
reason to be calling update). 

***This method ASSUMES you do not want to change the name of a stage (as Stage names don't really change). In the case
that a stage was added to the database with an incorrect spelling, delete that stage instead.***

*/
CREATE PROCEDURE [dbo].[update_Stage]
(@Name_1 varchar(50),
@Origin_2 varchar(50) = NULL,
 @Music_3 varchar(50) = NULL,
 @GameName_4 varchar(50))
AS 

-- Check if a Game already exits. If not, Raise Error.
IF ((SELECT COUNT(Name) FROM [Game] WHERE Name = @GameName_4) < 0)
BEGIN
	PRINT ('Game does not exist in the database')
	RETURN (1)
END

-- Check if a Stage already exits. If not, Raise Error.
IF ((SELECT COUNT(Name) FROM [Stage] WHERE Name = @Name_1 AND GameName = @GameName_4) < 0)
BEGIN
	PRINT ('Stage is not in the database')
	RETURN (2)
END

-- Checks to see if the @Origin and @Music parametes are both NULL
IF ((@Origin_2 IS NULL AND @Music_3 IS NULL) OR (@Origin_2 = '' AND @Music_3 = ''))
BEGIN
	PRINT('Cannot leave both Origin and Music NULL')
	RETURN (3)
END

-- This is where we update what needs to be updated
IF (@Origin_2 IS NOT NULL)
BEGIN
	UPDATE [Stage]
	SET [Origin] = @Origin_2
	WHERE (Name = @Name_1 AND GameName = @GameName_4)
END

IF (@Music_3 IS NOT NULL)
BEGIN
	UPDATE [Stage]
	SET [Music] = '"' + @Music_3 + '"'
	WHERE (Name = @Name_1 AND GameName = @GameName_4)
END
IF (@@ERROR = 0)
BEGIN
	PRINT ('The Stage was successfully updated.')
END
RETURN(0)
GO

grant execute on OBJECT::dbo.insert_Game to public
grant execute on OBJECT::dbo.delete_Character to public
grant execute on OBJECT::dbo.delete_Item to public
grant execute on OBJECT::dbo.delete_Move to public
grant execute on OBJECT::dbo.delete_Stage to public
grant execute on OBJECT::dbo.delete_User to public
grant execute on OBJECT::dbo.delete_Game to public
grant execute on OBJECT::dbo.insert_Character to public
grant execute on OBJECT::dbo.insert_Item to public
grant execute on OBJECT::dbo.insert_Move to public
grant execute on OBJECT::dbo.insert_Stage to public
grant execute on OBJECT::dbo.insert_User to public
grant execute on OBJECT::dbo.update_Character to public
grant execute on OBJECT::dbo.update_Fav to public
grant execute on OBJECT::dbo.update_Game to public
grant execute on OBJECT::dbo.update_Item to public
grant execute on OBJECT::dbo.update_Move to public
grant execute on OBJECT::dbo.update_Stage to public

create nonclustered index character_game on dbo.character(GameName)

create nonclustered index item_game on dbo.item(GameName)

create nonclustered index stage_game on dbo.stage(GameName)
