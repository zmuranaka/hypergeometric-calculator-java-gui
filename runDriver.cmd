:: File: runDriver.cmd
:: Zachary Muranaka
:: Recompiles the program and runs the Driver class

:: prevents the commands from being echoed to the screen
@echo off
cd bin
:: deletes all of the previous class files
del *.class
cd ../src
:: compiles all of the src files
javac *.java
:: moves all of the newly created class files into the bin folder
:: the > nul is needed so the output is not displayed
move *.class ../bin > nul
cd ../bin
:: runs the Driver class file
java Driver
