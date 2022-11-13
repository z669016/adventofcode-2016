# Advent of code 2016

## Convenience methods and classes
I have a small library with some convenience methods used for other AoC exercises. Like the ```ResourceLines``` class 
to read a resource file and transform the content into a ```List<String>```, or the CSV class to read a resource 
file containing comma separated values and returning a List of these values, optionally after transformation from 
```String``` to ```Integer```.

Also uses the algorithm library, which contains generic classes for addressing classic compute problems (from the book 
**Classic Computer Science Problems In Java** (c) Manning.com - 2020) 

It was never my intention to create the shortest program possible. I did try to create clear and simple implementations.

## Day 1
Not too difficult using the ```Point``` class from the AoC library (for moving around a grid). Created a ```Location``` 
helper class to keep track of a current location (```Point```) and facing (current ```Direction```). Translate the CVS 
data in a stream of pairs with ```Turn``` and distance, and ```move``` the current location.
The answer to part 1 is simply the Manhattan distance from the origin to the current location after all moves.
For part 2, I've added a ```trace``` (```Set```) and a ```doubles``` (```List```) property, to keep track of points 
visited and points visited more than once updated by the ```move``` method. The anser for part 2, is the first element 
in the ```doubles``` list.

## Day 2
Actually not too much different from day 1. Instead of moving across a grid of points, now you need to move across a
```KeyPad```, and press the key at the end of every move sequence. For part 1, I created a 3x3 keypad, translate the
input in a series of directions and after moving according to the directions, ```press``` the current key to 
remember it. The result is the string made out of the remembered pressed keys.
For part 2, I renamed ```KeyPad``` into ```SquareKeyPad```, abstracted a ```KeyPad``` interface, moved generic methods
into an ```AbstractKeyPad``` and implemented a DiamondKeyPad *which uses a 5x5 grid under the hood. The only
difference between the two is the way the grid boundaries are managed.

## Day 3
Implemented a simple ```Figure``` record, with a method ```possibleTriangle```. For part 1, transform the input
into a list of ```Figure```s, filter the possible triangles, and count the number of elements in the list.
Part 2 is the same, although the transformation of the input into a list of (a,b,c) values is changed.

## Day 4
Created an ```EncryptedName``` record with a ```name```, ```sectorId```, and ```checksum``` property and a factory
method which returns an ```Optional<>```. The factory method returns an ```Optional.empty()``` on invalid names. The 
trick is of course in validating the checksum.

For part 1 simply transform the input into a list of ```EncryptedName```s, and sum the sectorId, which is straight 
forward using streams. For part 2, I added a ```decrypt``` to the ```EncryptedName```, and simply searched for the
```EncryptedName``` that decrypted to the name "northpole object storage" (which I stumbled upon after looking at list 
of all decrypted names.

## Day 5
Brute force search for a hash starting with five zero's and when found, return character at position 6 (the first 
char after the five zeroes). Repeat this enough times so it generates a password of sufficient length (8 for part 1). 

The ```PasswordGenerator``` takes care of this, the class initializes with an empty list of the required size of
empty strings (each string is a character for the password). The ```generate()``` method, fills the list one by one
from beginning to the end, and returns the password found (joining the list elements) when the list is complete.
Part 2 wasn't that much more difficult. What changed compared to part 1, is the way the position of the character
in the password is determined (for part 1, it was a simple increasing index, for part two the position is a character 
in the hash), and the character to extract for the password (for part 1 its character 6, for part 2 it's character 7).

So, I've made the solution more generic, by passing two functions as parameter to the constructor, one for determining
the position for the next character, and one function for extracting the password character from the hash.nThe trick
for part 2, of course is that a position may only be set once, and an invalid position must be ignored. This is solved
in the ```generate()``` method and can be handled identical for part 1 and 2.

## Day 6
Basic approach, is to transform the input (list of strings), into a ```List<List<Character>>``` (split each string 
into a list of character). Then count the characters on each position, and replace the list of lists into a 
```List<Map<Character,Integer>>``` where the map contains the count of each character in a position (so the map
at index 0, contains the map with all characters at position 0 and the count of their occurance at that position).

For part 1, filter the most occurring keys from each map, and join the result into a string. This approach proofed 
helpful for part 2, for now simply filter to the least occurring keys for each position.

## Day 7
Really ABBA encoded IPv7 addresses? So, started with a record called IP7, with a method called ```supportsTL()```).
To determine if an IP7 address supports TLS, it's split into a list of hypernet-sequences (parts inside brackets) and
supernet-sequences (parts outside of brackets). An ```ABBA.containsABBA``` method then helps to validate which 
addresses contain an ABBA in any supernet-sequence but not in any hypernet-sequence. That's all what's needed to solve 
part 1. For part 2, I added a ```listABA(text)``` and a ```containsBAB(abaList,text)``` to the ```ABBA``` helper class. 
listABA is used by the ```IP7.supportsTLS()``` to extract all ABA's from an address's supernet sequences, and 
containsBAB checks if any of the hypernet-sequences contains the BAB of any ABA in the list.

## Day 8
The basic idea: I have a ```CardReader``` crass which is a ```Supplier``` of instructions read from the card. An
instruction can be a ```RectInstruction```, a ```RotateRowInstruction```, or a ```RotateColumnInstruction```. Each
instruction is a ```Consumer``` of a ```FixedGrid<Integer>```. A ```DoorLock``` class has a ```CardReader```, and
and when the ```swipe()``` method is called on the card reader, all instructions are read one by one from the card
reader, and each instruction is executed against (consumes) the fixed grid (led lights) of the door lock. AFter the 
swipe, the ```pixelsLit()``` method, can count the leds lit in the grid.

For part 2, the ```display()``` method of the ```DoorLock``` can show the code on the screen.

## Day 9
This doesn't look too difficult, so there is probably a catch in part 2. For part 1, I created a ```Decompressor```
it creates a decompressed version using a ```StringBuilder```. This will probably work if the string isn't going to be
too long.

For part 2, I took an entirely different approach. Let's split the input into a ```Sequence```, and a sequence is 
either a ```FixedSequence``` (piece of non repeated text) or a ```RepeatSequence``` (a repeating ```Sequence```, notice 
the recursion!). The length of a ```FixedSequence``` is the length of the text, while the length of a 
```RepeatSequence``` is repeat-factor times the ```Sequence``` to repeat (which in turn can be a ```FixedSequence``` or 
a ```RepeatSequence```). The ```SequenceBuilder``` is the top level ```Sequence```, which transforms the input into
a ```List<Sequence>```. The total length for part 2, is now simply the sum of the length of all sequences in the list.
And that calculation is simple, but the resulting string will probably take too much memory (and a lot of time to
display on the screen).

## Day 10
According to the story, there's a ```ChipFactory``` where ```Microchip```s are handled (consumed) by either a ```Bot``` 
or they end up in an ```Output```. A ```Bot``` can have a higher and lower value ```Microchip```, so microchips must
be ```Comparable```. Once a ```Bot``` has two microchips, he passes them on to a next ```Consumer```  (being another 
```Bot``` or an ```Output```).
The record ```Microchip``` simply holds a value and implements ```Comparable<Microchip>```. A ```Bot``` and an 
```Output``` implement ```Consumer<Microchip>```, and a Bot can have a higher-consumer amd a lower-consumer to pass
on the chips once he has two of them. The ```ChipFactory.run()``` creates a map of ```Consumers```from the instructions
and wires them all together. 
For part 1, run the factory, and search the list of ```Bot```s for the one that contains chip 17 and 61. For part 2,
select ```Output``` from the factory output list and multiply the values of the chips they contain.



