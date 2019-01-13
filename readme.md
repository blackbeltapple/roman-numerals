# Roman Numerals Challenge

## Problem definition

In whatever language you prefer, write a class that implements the following interface (example given in Java):

```
public interface RomanNumeralGenerator {
   public String generate(int number); 
}
```

For example, see the following sample inputs and outputs: 
1 = “I” 
5 = “V” 
10 = “X” 
20 = “XX” 
3999 = “MMMCMXCIX”

Caveat: Only support numbers between 1 and 3999 


### Build 

Navigate to the project root directory and execute the following command:

`./gradlew build`

This will download all the required dependencies and build the artifact 'roman-numerals-1.0-SNAPSHOT.jar'.


### Run from the command line

In a terminal window, navigate to the root directory of the project. 

Run the following command, where <n> is the integer that you wish to convert to roman numerals. 

`java -jar build/libs/roman-numerals-1.0-SNAPSHOT.jar <n>`

e.g. 
`java -jar build/libs/roman-numerals-1.0-SNAPSHOT.jar 9`

You should see the output in the format below

`The roman numeral for 9 is 'IX'`


### Additional Reading
For an in-depth description of Roman Numerals, see 
http://en.wikipedia.org/wiki/Roman_numerals