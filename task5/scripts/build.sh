#!/bin/sh
set -eu
ROOT="$(CDPATH= cd -- "$(dirname -- "$0")/.." && pwd)"
cd "$ROOT"
rm -rf build dist
mkdir -p build/classes dist
javac -d build/classes src/*.java
jar --create --file dist/library-inventory.jar --main-class Main -C build/classes .
echo "Build completed: dist/library-inventory.jar"
