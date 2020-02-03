# Java Hypergeometric Calculator GUI

## What is this Project?

This project is the GUI version of the console hypergeometric calculator I wrote, which I have been off-and-on working on since I was a senior in high school. I have been fascinated with hypergeometric distributions for years, since I play many card games, and hypergeometric distributions can be used to find the probability of drawing certain combinations of cards out of a deck. I traditionally just went to [Stat Trek](https://stattrek.com/online-calculator/hypergeometric.aspx) to solve hypergeometric distributions, but after looking into the math involved, I realized that it is not actually too difficult. Over the course of a few days, I wrote a hypergeometric calculator program on my TI-84 Plus C Silver Edition. This was not too hard, as the TI-84 can automatically handle a lot of the calculations with its built-in math functions. However, around the same time I was learning the basics of Java in my computer programming class, and I decided that it wouldn't be too hard to port the program over to Java. That year, as a senior in high school, I completed my first version of the console program. About a year later, in my first semester of college, after learning the basics of java GUI programming, I decided to write a GUI version for my hypergeometric calculator. Over a year later, here we are.

## Directories:

bin  
&nbsp;&nbsp;&nbsp;&nbsp;This folder contains all of the compiled classes.  
lib  
&nbsp;&nbsp;&nbsp;&nbsp;This folder contains the jar (java archive) of the project. Running the jar runs the program.  
src  
&nbsp;&nbsp;&nbsp;&nbsp;This folder contains all of the source code.

## Files:

Deck.java  
&nbsp;&nbsp;&nbsp;&nbsp;This is the object that the Driver creates when the user has entered valid information. It is called Deck because drawing cards from a deck is a common scenario in which a hypergeometric distribution would be useful. This class contains information about the distribution and many methods used to calculate the probability of getting the number of successes that the user desired.  
Driver.java  
&nbsp;&nbsp;&nbsp;&nbsp;This is the Driver class that actually runs the program. It is a very small class, since I have offloaded the creation of the GUI into the GUI class and all of the calculations into the Deck class.  
GUI.java  
&nbsp;&nbsp;&nbsp;&nbsp;This class contains the entire GUI for the program. Anything you see on the screen is located in this class. The Driver class creates an object of this class.  
runDriver.cmd  
&nbsp;&nbsp;&nbsp;&nbsp;This is a simple command prompt script I wrote because I was compiling and running the program from the command prompt, and I was often reusing the same commands. Running this file runs the program. Note: the Driver file MUST be named Driver in order for this program to work properly. Similarly, the .class files must be in the bin directory, and the the .java files must be in the src directory.

## Sources:

I wrote the vast majority of code myself, and as such it may have places where it could be simplified, improved, or is just plain confusing. However, there are some sources I did take small chunks of code from or take inspiration from:  
https://stattrek.com/online-calculator/hypergeometric.aspx  
&nbsp;&nbsp;&nbsp;&nbsp;I did not take any code from this page, but essentially the entire inspiration of the project came from this website. My program is laid out very similarly, from the order the information is asked to how it is displayed. I also used this website when checking that my program was coming up with the correct numbers.  
https://www.geeksforgeeks.org/program-to-calculate-the-value-of-ncr-efficiently/  
&nbsp;&nbsp;&nbsp;&nbsp;This page was very helpful when trying to find out how to calculate the combination of n and r without overflowing the long data type. It is still not a perfect solution, but it is much better than what I had come up with.  
https://smallbusiness.chron.com/write-cmd-script-53226.html  
&nbsp;&nbsp;&nbsp;&nbsp;I had never written a command prompt script before writing runDriver.cmd, and this page was very helpful in helping me write it.  
http://www.skylit.com/javamethods/faqs/createjar.html  
&nbsp;&nbsp;&nbsp;&nbsp;This page taught me how to create a Java Archive (.jar) from the command prompt.

## Author:

Zachary Muranaka  
&nbsp;&nbsp;&nbsp;&nbsp;zacharymuranaka@mail.weber.edu  
&nbsp;&nbsp;&nbsp;&nbsp;http://icarus.cs.weber.edu/~zm83483/
