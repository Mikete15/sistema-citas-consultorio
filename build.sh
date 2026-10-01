#!/bin/bash
set -e

rm -rf out dist
mkdir -p out dist

javac -d out src/consultorio/*.java

jar cfe dist/SistemaCitasConsultorio.jar consultorio.Main -C out .

echo "FAT JAR generado correctamente:"
echo "dist/SistemaCitasConsultorio.jar"
