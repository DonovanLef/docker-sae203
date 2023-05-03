#!/bin/bash

javac -cp /var/java:/var/dependency/* -encoding utf-8 /var/java/*/*.java
java  -cp /var/java:/var/dependency/* server.Main