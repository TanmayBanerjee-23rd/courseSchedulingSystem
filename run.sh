#!/bin/bash

gradle clean build -x test --no-daemon
java -jar build/libs/courseScheduler.jar sample_input/input4.txt