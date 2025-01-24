****************
* GridMonitor
* CS 221
* 01/24/2025
* Evan Wallace
**************** 
# 221-Warmup

# Overview:
GridMonitor is a program that analyzes a grid of double values read from a file. It then provides various computed grids based on input, and identifies cells in a "dangerous" state based on specific criteria.

# Included Files:
-**GridMonitor.java** - source file
-**GridMonitorInterface.java** - source file
-**GridMonitorTest.java** - source file
-**README.md** - this file

# Building and Running:
From the directory containing all source files, compile the test
class (and all dependent classes) with the command:
$ javac GridMonitor.java

Run the compiled SetTester class with the command:
$ java GridMonitor

**Note:** GridMonitor has no output console or visible output. 
To run this file create an input text file containing grid data, the top row should be two integers separated by a space which represents the size of the array given. The next lines will be the actual array values that will be taken, it must follow the size of the array given and each value must be separated by spaces. After creating an input text file, initialize a "GridMonitor" object and call the desired methods. You can also call "toString()" to get a string with the generated summary of the grid status.

# Program Design:
Constructor:
GridMonitor reads an input file containing a grid of double values, where the first line specifies the width and length of the array, and the following lines contain the array. With this data GridMonitor creates a 2d array following the input files instructions.
After a GridMonitor object is initialized we can call on any of the other methods.
-**getSurroundingSumGrid()** is a method which takes the inputed array and creates a new one with the same dimensions of the previous one. In this new array it sets each value to be the sum of the four neighboring cells in the original array.
-**getSurroundingAvgGrid()** takes getSurroundingSumGrid()'s output, divides each value in the output by 4(which takes the average of the four neighboring cells) and returns that output.
-**getDeltaGrid()** takes getSurroundingAvgGrid()'s output and computes half the absolute value of the average for each cell
-**getDangerGrid()** marks a cell as "dangerous" if its value is outside the range defined by its average - delta, and its average + delta.

The program also has a **toString()** method which provides a detailed report including:
Original Grid Values:
Danger Grid Values:
and which cells are at risk of explosion.

# Testing:
The program was tested mainly using the provided GridMonitorTest, aswell as a test class of my own which called specific methods to see if they worked.
To verify that the program works as intended lots of scenarios were tested including
-3x3 grid with uniform values
-3x3 grid with a wide range of values
-4x5 grid with uniform values
-Aswell as edge cases with strange amount of rows and columns, 1x1, 10x1, ect.

These tests confirmed that
-Input files were parsed correctly
-Grid calculations were accurate
-Accurate identification of dangerous and safe cells

# Discussion:
Developing GridMonitor was a great overview of what CS-121 taught us. I found this project to be very insightful as it allowed me to try out new forms of condition checks. Up until this point I had never utilized try catch to test conditions so that was super helpful to learn.

Future potential enhancements to this project could be:
-The addition of a graphical display
-Allowing for the array with holes and differently sized rows and columns

Overall this project demonstrated the importance of breaking down large computations into smaller and more dijestable methods aswell as thorough testing.