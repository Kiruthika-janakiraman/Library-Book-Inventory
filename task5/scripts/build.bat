@echo off
setlocal
cd /d "%~dp0.."
if exist build rmdir /s /q build
if exist dist rmdir /s /q dist
mkdir build\classes
mkdir dist
javac -d build\classes src\*.java
jar --create --file dist\library-inventory.jar --main-class Main -C build\classes .
echo Build completed: dist\library-inventory.jar
