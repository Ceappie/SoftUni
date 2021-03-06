USE Geography
GO
--All Mountain Peaks
SELECT Peaks.PeakName
FROM Peaks
ORDER BY PeakName

GO
--Biggest Countries by Population
SELECT TOP 30 CountryName, Population
FROM Countries
WHERE ContinentCode = 'EU'
ORDER BY Population DESC, CountryName DESC

GO
--Countries and Currency (Euro / Not Euro)
SELECT 
	CountryName,
	CountryCode,
	--CurrencyCode
	CASE
		WHEN CurrencyCode = 'EUR' THEN 'Euro' ELSE 'Not Euro' 
	END AS Currency
FROM Countries
ORDER BY CountryName ASC

GO
--Countries Holding 'A' 3 or More Times
SELECT CountryName AS [Country Name], IsoCode AS [ISO Code]
FROM Countries
WHERE LOWER(CountryName) LIKE '%a%a%a%'
ORDER BY IsoCode

GO
--Peaks and Mountains
SELECT p.PeakName, m.MountainRange AS Mountain, p.Elevation
FROM Peaks p
JOIN Mountains m
ON p.MountainId = m.Id
ORDER BY Elevation DESC, PeakName ASC

GO
--Peaks with Their Mountain, Country and Continent
SELECT p.PeakName, m.MountainRange AS Mountain, c.CountryName, co.ContinentName
FROM Peaks p
JOIN Mountains m
ON p.MountainId = m.Id
JOIN MountainsCountries mc
ON m.Id = mc.MountainId
JOIN Countries c
ON mc.CountryCode = c.CountryCode
JOIN Continents co
ON c.ContinentCode = co.ContinentCode
ORDER BY PeakName ASC, ContinentName ASC

GO
--Rivers Passing through 3 or More Countries
SELECT r.RiverName AS River, COUNT(r.RiverName) AS [Countries Count]
FROM Rivers r
JOIN CountriesRivers cr
ON r.Id = cr.RiverId
JOIN Countries c
ON cr.CountryCode = c.CountryCode
GROUP BY r.RiverName
HAVING COUNT(r.RiverName) >= 3
ORDER BY r.RiverName

GO
--Highest, Lowest and Average Peak Elevation
SELECT MAX(Elevation) AS MaxElevation, MIN(Elevation) AS MinElevation, AVG(Elevation) AS AverageElevation
FROM Peaks

GO
--Rivers by Country
SELECT c.CountryName, con.ContinentName, COUNT(r.RiverName) AS RiversCount, ISNULL(SUM(r.Length), 0) AS TotalLength
FROM Countries c
LEFT JOIN CountriesRivers cr
ON c.CountryCode = cr.CountryCode
LEFT JOIN Rivers r
ON cr.RiverId = r.Id
JOIN Continents con
ON c.ContinentCode = con.ContinentCode
GROUP BY c.CountryName, con.ContinentName
ORDER BY RiversCount DESC, TotalLength DESC, c.CountryName ASC

GO
--Count of Countries by Currency
SELECT crr.CurrencyCode, crr.Description AS [Currency], COUNT(c.CurrencyCode) AS NumberOfCountries
FROM Countries c
RIGHT JOIN Currencies crr
ON c.CurrencyCode = crr.CurrencyCode
GROUP BY crr.CurrencyCode, crr.Description
ORDER BY COUNT(c.CurrencyCode) DESC, crr.Description ASC

GO
--Population and Area by Continent
SELECT con.ContinentName, SUM(c.AreaInSqKm) AS CountriesArea, SUM(CAST(c.Population AS bigint)) AS CountriesPopulation
FROM Continents con
JOIN Countries c
ON con.ContinentCode = c.ContinentCode
GROUP BY con.ContinentName
ORDER BY CountriesPopulation DESC

GO
--Highest Peak and Longest River by Country
SELECT 
	c.CountryName, 
	MAX(p.Elevation) AS HighestPeakElevation, 
	MAX(r.Length) AS LongestRiverLength
FROM Countries c
LEFT JOIN CountriesRivers cr
ON cr.CountryCode = c.CountryCode
LEFT JOIN Rivers r
ON cr.RiverId = r.Id
LEFT JOIN MountainsCountries mc
ON c.CountryCode = mc.CountryCode
LEFT JOIN Mountains m
ON mc.MountainId = m.Id
LEFT JOIN Peaks p
ON p.MountainId = m.Id
GROUP BY c.CountryName
ORDER BY MAX(p.Elevation) DESC, MAX(r.Length) DESC, c.CountryName ASC

GO
--Mix of Peak and River Names
SELECT p.PeakName, r.RiverName, LOWER(LEFT(p.PeakName, (LEN(p.PeakName) - 1)) + r.RiverName) AS Mix
FROM Peaks p, Rivers r
WHERE LOWER(RIGHT(p.PeakName, 1)) = LOWER(LEFT(r.RiverName, 1))
ORDER BY Mix ASC

GO
--Highest Peak Name and Elevation by Country
SELECT 
	c.CountryName AS [Country],
	ISNULL(
		(SELECT PeakName
		FROM Peaks pin
		JOIN Mountains m
		ON pin.MountainId = m.Id
		JOIN MountainsCountries mc
		ON m.Id = mc.MountainId
		JOIN Countries cin
		ON cin.CountryCode = mc.CountryCode
		WHERE Elevation = MAX(p.Elevation) AND cin.CountryName = c.CountryName
	), '(no highest peak)') AS [Highest Peak Name],
	ISNULL(MAX(p.Elevation), 0) AS [Highest Peak Elevation],
	ISNULL(
		(SELECT MountainRange
		FROM Mountains mi
		JOIN Peaks pi
		ON pi.MountainId = mi.Id
		JOIN MountainsCountries mc
		ON mi.Id = mc.MountainId
		JOIN Countries ci
		ON ci.CountryCode = mc.CountryCode
		WHERE  pi.Elevation = MAX(p.Elevation) AND ci.CountryName = c.CountryName
	), '(no mountain)') AS Mountain
FROM Countries c
LEFT JOIN MountainsCountries mc
ON c.CountryCode = mc.CountryCode
LEFT JOIN Mountains m
ON mc.MountainId = m.Id
LEFT JOIN Peaks p
ON p.MountainId = m.Id
GROUP BY CountryName
ORDER BY CountryName ASC, [Highest Peak Name] ASC

--Changes in the Database
CREATE TABLE Monasteries(
	Id int PRIMARY KEY IDENTITY,
	Name nvarchar(80),
	CountryCode char(2) FOREIGN KEY REFERENCES Countries(CountryCode)
)

INSERT INTO Monasteries(Name, CountryCode) VALUES
('Rila Monastery “St. Ivan of Rila”', 'BG'), 
('Bachkovo Monastery “Virgin Mary”', 'BG'),
('Troyan Monastery “Holy Mother''s Assumption”', 'BG'),
('Kopan Monastery', 'NP'),
('Thrangu Tashi Yangtse Monastery', 'NP'),
('Shechen Tennyi Dargyeling Monastery', 'NP'),
('Benchen Monastery', 'NP'),
('Southern Shaolin Monastery', 'CN'),
('Dabei Monastery', 'CN'),
('Wa Sau Toi', 'CN'),
('Lhunshigyia Monastery', 'CN'),
('Rakya Monastery', 'CN'),
('Monasteries of Meteora', 'GR'),
('The Holy Monastery of Stavronikita', 'GR'),
('Taung Kalat Monastery', 'MM'),
('Pa-Auk Forest Monastery', 'MM'),
('Taktsang Palphug Monastery', 'BT'),
('Sümela Monastery', 'TR')

BEGIN TRAN

GO
ALTER TABLE Countries ADD IsDeleted bit DEFAULT 0

GO
UPDATE Countries SET IsDeleted = 1 WHERE CountryName IN (SELECT c.CountryName
	FROM Countries c
	JOIN CountriesRivers cr
	ON c.CountryCode = cr.CountryCode
	JOIN Rivers r
	ON cr.RiverId = r.Id
	GROUP BY c.CountryName
	HAVING COUNT(r.RiverName) > 3
)

GO
SELECT m.Name AS [Monastery], c.CountryName AS [Country]
FROM Countries c
JOIN Monasteries m
ON c.CountryCode = m.CountryCode
WHERE c.IsDeleted IS NULL
ORDER BY m.Name

GO

UPDATE Countries SET CountryName = 'Burma' WHERE CountryName = 'Myanmar' 

INSERT INTO Monasteries VALUES ('Hanga Abbey', (SELECT CountryCode FROM Countries WHERE CountryName = 'Tanzania'))
INSERT INTO Monasteries VALUES ('Myin-Tin-Daik', (SELECT CountryCode FROM Countries WHERE CountryName = 'Myanmar'))

GO

SELECT
	co.ContinentName,
	c.CountryName, 
	COUNT(m.Name) AS MonasteriesCount
FROM Countries c
LEFT JOIN Monasteries m
ON c.CountryCode = m.CountryCode
JOIN Continents co
ON co.ContinentCode = c.ContinentCode
WHERE c.IsDeleted IS NULL
GROUP BY co.ContinentName, c.CountryName
ORDER BY COUNT(m.Name) DESC, c.CountryName ASC


ROLLBACK TRAN
DROP TABLE Monasteries

GO
--Stored Procedures --NOT WORKING...
BEGIN TRAN
GO

CREATE FUNCTION fn_MountainsPeaksJSON() 
RETURNS @result TABLE
   (
    JSON nvarchar(Max)
   )
AS
BEGIN
DECLARE empCursor CURSOR READ_ONLY FOR
  SELECT m.MountainRange, p.PeakName, p.Elevation 
  FROM Mountains m
  FULL JOIN Peaks p
  ON m.Id = p.MountainId
  ORDER BY m.MountainRange, p.PeakName

DECLARE @Json nvarchar(max) = '{"mountains":['
DECLARE @Peaks nvarchar(max) = '['
DECLARE @mountainJson nvarchar(max) = ''

OPEN empCursor
DECLARE @currMountainName nvarchar(80), @peakName nvarchar(80), @elevation int
DECLARE @lastMountainName nvarchar(80), @lastPeakName nvarchar(80), @lastEelevation int
FETCH NEXT FROM empCursor INTO @currMountainName, @peakName, @elevation
SET @lastMountainName = @currMountainName
SET @lastPeakName = @peakName
SET @lastEelevation = @elevation

WHILE @@FETCH_STATUS = 0
  BEGIN
    IF @currMountainName = @lastMountainName
		BEGIN
			IF @Peaks = '['
				BEGIN
				IF @peakName IS NOT NULL AND @elevation IS NOT NULL
					SET @Peaks = @Peaks + '{"name":"' + @peakName + '","elevation":' + CAST(@elevation AS nvarchar(40)) + '}'
				END
			ELSE
				BEGIN
				IF @peakName IS NOT NULL AND @elevation IS NOT NULL
					SET @Peaks = @Peaks + ',{"name":"' + @peakName + '","elevation":' + CAST(@elevation AS nvarchar(40)) + '}'
				END
		END
	ELSE
		BEGIN
			SET @mountainJson = @mountainJson + '{"name":"' + @lastMountainName + '",'
			IF @Peaks = '['
				BEGIN
				IF @lastPeakName IS NOT NULL AND @lastEelevation IS NOT NULL
					SET @Peaks = @Peaks + '{"name":"' + @lastPeakName + '","elevation":' + CAST(@lastEelevation AS nvarchar(40)) + '}'
				END

			SET @Peaks = @Peaks + ']}'
			SET @Json = @Json + @mountainJson + '"peaks":' + @Peaks

			--RESET
			SET @Peaks = '['
			SET @mountainJson = ','
			SET @lastMountainName = @currMountainName
			SET @lastPeakName = @peakName
			SET @lastEelevation = @elevation
		END


	FETCH NEXT FROM empCursor INTO @currMountainName, @peakName, @elevation
  END

CLOSE empCursor
DEALLOCATE empCursor
SET @mountainJson = @mountainJson + '{"name":"' + @lastMountainName + '",'
IF @Peaks = '['
	BEGIN
	IF @peakName IS NOT NULL AND @elevation IS NOT NULL
		SET @Peaks = @Peaks + '{"name":"' + @peakName + '","elevation":' + CAST(@elevation AS nvarchar(40)) + '}'
	END

SET @Peaks = @Peaks + ']}'
SET @Json = @Json + @mountainJson + '"peaks":' + @Peaks + ']}'
INSERT @result VALUES (@Json)
RETURN
END

GO

SELECT * FROM fn_MountainsPeaksJSON()

GO
ROLLBACK TRAN