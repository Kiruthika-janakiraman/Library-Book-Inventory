#!/bin/sh
set -eu
ROOT="$(CDPATH= cd -- "$(dirname -- "$0")/.." && pwd)"
cd "$ROOT"
rm -rf build/test-classes
mkdir -p build/test-classes
javac -d build/test-classes src/Book.java src/BookService.java src/IntegrationTest.java
java -cp build/test-classes IntegrationTest
