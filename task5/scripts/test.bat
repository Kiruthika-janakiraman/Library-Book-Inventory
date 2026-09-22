@echo off
setlocal
cd /d "%~dp0.."
if exist build\test-classes rmdir /s /q build\test-classes
mkdir build\test-classes
javac -d build\test-classes src\Book.java src\BookService.java src\IntegrationTest.java
java -cp build\test-classes IntegrationTest
