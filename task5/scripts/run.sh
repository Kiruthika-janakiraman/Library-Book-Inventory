#!/bin/sh
set -eu
ROOT="$(CDPATH= cd -- "$(dirname -- "$0")/.." && pwd)"
cd "$ROOT"
if [ ! -f dist/library-inventory.jar ]; then
  echo "JAR not found. Run ./scripts/build.sh first."
  exit 1
fi
java -jar dist/library-inventory.jar
