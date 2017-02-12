#!/usr/bin/env bash

cd ../
./gradlew :library:clean :library:check :library:connectedAndroidTest -PpreDexEnable=false