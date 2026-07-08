-- Bootstrap for the containerized SQL Server.
-- Creates the application login (matching the app's admin/Test@1234 config) and the
-- two databases the running stack uses. Table creation is handled by the services
-- themselves (Hibernate ddl-auto=update); lookup reference data is seeded separately.

IF NOT EXISTS (SELECT 1 FROM sys.server_principals WHERE name = N'admin')
BEGIN
    CREATE LOGIN [admin] WITH PASSWORD = N'Test@1234', CHECK_POLICY = OFF;
    ALTER SERVER ROLE sysadmin ADD MEMBER [admin];
END
GO

IF DB_ID('N7_Banking') IS NULL CREATE DATABASE [N7_Banking];
GO

IF DB_ID('lending') IS NULL CREATE DATABASE [lending];
GO
