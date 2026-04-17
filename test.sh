#!/bin/bash

mvn clean test-compile dependency:copy-dependencies
java \
  -XX:+UseCompactObjectHeaders -XX:+UseStringDeduplication \
  -Xmx1G \
  --class-path target/dependency/*:target/test-classes \
  com.example.TestRunner
#  org.junit.platform.console.ConsoleLauncher execute --select-package=com.example