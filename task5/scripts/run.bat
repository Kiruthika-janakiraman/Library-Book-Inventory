@echo off
setlocal
cd /d "%~dp0.."
if not exist dist\library-inventory.jar (
  echo JAR not found. Run scripts\build.bat first.
  exit /b 1
)
java -jar dist\library-inventory.jar
