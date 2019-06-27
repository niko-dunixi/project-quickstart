#!/usr/bin/env bash

function rollback() {
  sleep 3s
  mvn release:rollback
  exit 1
}

mvn clean package
mvn release:prepare release:perform || rollback
mvn deploy
