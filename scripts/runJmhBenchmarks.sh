#!/bin/bash

echo "Running JMH Benchmarks"
./mvnw clean install -DskipTests --projects benchmarks --also-make -Pbenchmarks,jmh
java -Djmh.ignoreLock=true -jar benchmarks/target/benchmarks.jar -rf csv -rff jmh-result.csv | tee target/benchmarks.log

./mvnw clean install -DskipTests --projects benchmarks --also-make -Pbenchmarks,jmh,aspectj
java -Djmh.ignoreLock=true -jar benchmarks/target/benchmarks.jar -rf csv -rff jmh-aspectj-result.csv StartupBenchmark | tee target/aspectj-benchmarks.log

