# Java Hypergeometric Calculator GUI

## What is this Project?

This project is a hypergeometric calculator GUI. A hypergeometric calculator can determine the probability to get a desired number of successes out of a number of draws from a population without replacement. For example, if you have a standard deck of playing cards, and you want to determine the probability that you get all four aces out of a hand of five cards, a hypergeometric calculator can solve that.

## How do I use the Program?

All you need to do is provide the population size, population successes, sample size, and desired successes, and then click the 'Calculate' button, and the calculator will tell you what the probability is.

## What do the Variables Mean?

Population size  
&nbsp;&nbsp;&nbsp;&nbsp;Population size means the total number of individuals in the population. For example, in a standard deck of cards there are 52 cards, so the population size is 52.  
Population successes  
&nbsp;&nbsp;&nbsp;&nbsp;Population successes means the number of "successes" in the entire population. For example, if you are looking for diamonds out of a standard deck of cards, there are 13 diamonds in a standard deck. Therefore, the population successes is 13.  
Sample size  
&nbsp;&nbsp;&nbsp;&nbsp;Sample size means the number of draws you are taking from the population. For example, a standard poker hand is 5 cards, so if you are looking for the probability you get a certain number of successes from a standard poker hand, the sample size is 5.  
Desired successes  
&nbsp;&nbsp;&nbsp;&nbsp;Desired successes means the number of "successes" you would like to draw in your sample size. For example, if you are looking for the probability of getting all four aces in a standard poker hand, the desired successes is 4.

## Directories:

bin  
&nbsp;&nbsp;&nbsp;&nbsp;This folder contains all of the compiled classes.  
lib  
&nbsp;&nbsp;&nbsp;&nbsp;This folder contains the jar (java archive) of the project.  
src  
&nbsp;&nbsp;&nbsp;&nbsp;This folder contains all of the source code.

## Files:

Deck.java  
&nbsp;&nbsp;&nbsp;&nbsp;This is the object that the Driver creates when the user has entered valid information. It is called Deck because drawing cards from a deck is a common scenario in which a hypergeometric distribution would be useful. This class contains information about the distribution and many methods used to calculate the probability of getting the number of successes that the user desired.  
Driver.java  
&nbsp;&nbsp;&nbsp;&nbsp;This is the Driver class that actually runs the program. It is a very small class, since I have offloaded the creation of the GUI into the GUI class and all of the calculations into the Deck class.  
GUI.java  
&nbsp;&nbsp;&nbsp;&nbsp;This class contains the entire GUI for the program. Anything you see on the screen is located in this class. The Driver class creates an object of this class.  
compileAndRun.cmd  
&nbsp;&nbsp;&nbsp;&nbsp;This is a simple command prompt script I wrote because I was compiling and running the project from the command prompt, and I was often reusing the same commands. Running this script recompiles the project and then runs the Driver class. Note: the Driver src file must be named Driver.java in order for this script to work properly. Similarly, the .class files must be in the bin directory, and the the .java files must be in the src directory.  
runDriver.cmd  
&nbsp;&nbsp;&nbsp;&nbsp;This is a simple command prompt script I wrote that is similar to compileAndRun.cmd, except it does not recompile the program. Running this script simply runs the Driver class. Note: the Driver.class file must be in the bin directory for this script to work properly.

## Sources:

Websites that helped me create the project:  
https://stattrek.com/online-calculator/hypergeometric.aspx  
&nbsp;&nbsp;&nbsp;&nbsp;The inspiration of the project came from this website. My program is laid out very similarly, from the order the information is asked to how it is displayed. I also used this website when checking that my program was coming up with the correct numbers.  
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
