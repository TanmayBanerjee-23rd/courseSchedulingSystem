# Pre-requisites

- Java 8.0.xxx
- Gradle 5.1

# How to run the code

We have provided scripts to execute the code.

Use `run.sh` if you are Linux/Unix/macOS Operating systems and `run.bat` if you are on Windows. Both the files run the commands silently and prints only output from the input file `sample_input/input1.txt`.

Internally both the scripts run the following commands

- `gradle clean build -x test --no-daemon` - This will create a jar file `metroCardSystem.jar` in the `build/libs` folder.
- `java -jar build/libs/courseSchedulingSystem.jar sample_input/input1.txt` - This will execute the jar file passing in the sample input file as the command line argument

# Running the code for multiple test cases

Please fill `input1.txt` and `input2.txt` with the input commands and use those files in `run.bat` or `run.sh`. Replace `java -jar build/libs/courseSchedulingSystem.jar sample_input/input1.txt` with `java -jar build/libs/courseSchedulingSystem.jar sample_input/input2.txt` to run the test case from the second file.

# How to execute the unit tests

`gradle clean test --no-daemon` will execute the unit test cases.
