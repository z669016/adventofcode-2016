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
visited and points visited more than once updated by the ```move``` method. The answer for part 2, is the first element 
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
of all decrypted names).

## Day 5
Brute force search for a hash starting with five zeros and when found, return character at position 6 (the first 
char after the five zeroes). Repeat this enough times, so it generates a password of sufficient length (8 for part 1). 

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
at index 0, contains the map with all characters at position 0 and the count of their occurrence at that position).

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
when the ```swipe()``` method is called on the card reader, all instructions are read one by one from the card
reader, and each instruction is executed against (consumes) the fixed grid (LED lights) of the door lock. After the 
swipe, the ```pixelsLit()``` method, can count the LEDs lit in the grid.

For part 2, the ```display()``` method of the ```DoorLock``` can show the code on the screen.

## Day 9
This doesn't look too difficult, so there is probably a catch in part 2. For part 1, I created a ```Decompressor```
it creates a decompressed version using a ```StringBuilder```. This will probably work if the string isn't going to be
too long.

For part 2, I took an entirely different approach. Let's split the input into a ```Sequence```, and a sequence is 
either a ```FixedSequence``` (piece of non-repeated text) or a ```RepeatSequence``` (a repeating ```Sequence```, notice 
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

## Day 11
A long time a go I solved part 1, but wasn't able to solve part 2 in a reasonable amount of time (to run). Even using
the tips on pruning didn't work that well, part 2 took so long I couldn't wait for it. I checked a few solutions
online, found a ridiculous simple one (based of number of steps to move x items up) that didn't work for the test 
input but provided the right answers on my input.

Then I found another solution by [Matt Wade](https://github.com/romellem/advent-of-code/blob/master/2016/11/notes.txt) 
(Chicago) in Javascript. He simply ignored the different types of chips and RTGs, and calculated the number of steps 
to solve. The only invalid combination is one with more chips than RTGs on a floor. I've implemented it in Java, using 
BSF and a PriorityQueue (ordered by number of steps taken), and it works like a charm for the sample input and my 
inputs. Added history checking, and optimization to avoid moving to already emptied floors.

Runs amazingly fast, although I still don't see why it's okay to ignore the type of chips/RTGs. It feels like it cannot
work for all kinds of input.

## Day 12
Always fun to implement a virtual computer (```Assembunny```). Compile the input into a ```List<Instruction>```, and
execute it. The ```Instruction.execute()``` always returns an offset to add to the instruction pointer.

## Day 13
Again a BSF based solution, with a Maze that can basically only tell you if a location in the maze is a wall or not, 
and that's all it needs to be able to do. The ```Finder.find()``` searches the maze using BSF until a certain
predicate is fulfilled, after which the number of steps and the set of visited points is returned in as ```Pair``` as 
an ```Optional```. The predicate is a lambda which checks the current state for the find-criteria.

For part 1, ```Finder.locate()``` simply calls ```Finder.find()```, and the predicate checks if the right location
has been found. For part 2, ```Finder.distinct()``` calls ```Finder.find()```, and the predicate checks if the 
max number of steps have been taken. Clean and simple!

## Day 14
A few things to be noticed; first generate MD5 hashes, check for a sequence of three characters, and look forward in 
future hashes if an even longer sequence of 5 characters occur within the next 1000. This feels like caching is 
required to prevent unnecessary hash calculations (which are pretty expensive to do).
So, the value to generate is an ```IndexedHash``` (record with int index, and hash string). The class providing the 
hash values ```IndexHashSupplier``` is a ```Supplier<IndexedHash>```. To enable looking forward, wrap it with a 
```IndexedHashPeekableSupplier```, which also implements caching to prevent too many calculations.
The ```IndexedHashGenerator``` uses the ```IndexedHashSupplier``` to get next values, and a ```SequenceValidator```
to check if the generated hash matches the right conditions. That is enough to solve part 1, with the different 
features clearly seperated in different classes.
For part 2, actually only the ```IndexedHashSupplier```  needs to change into a ```IndexStretchedHashSupplier```, end the rest
(caching, checking values) can be the same. 

## Day 15
The trick with this one is to calculate the position of a ```Disk``` at a certain point in time, which is 
```(id + start + t) % positions``` (id = sequence number of the disk, start = starting position, positions =
number of positions on the disk). A ```Sculpture``` is a sequence of ```Disk```s, or a sequence of functions
that calculates the position of a disk at a certain point in time. So yes, you can replace disks, with a sequence 
of functions, which for a time ```t```, calculate the position of the disk.
Now the solution for part 1 has become simple, ```Sculpture.isOpen()```, calculates the position for each disk, and
sums the result. WEHen the result is 0, all disks are open. SO, you only need to iterate over time starting at 
```t = 0``` to find the first moment all disks are open.
For part 2, just add another disk and redo the math.

## Day 16
Hmmm, chop the problem into pieces (grow the fill data block, and calculate checksum), and write the code. Very 
straight forward.

## Day 17
Again a BSF challenge. The maze is a 4x4 grid, and directions depend on a passcode from a generated MD5 hash.
```Passcode``` generates a 4 character hash for the current route. ```PasscodeDirection``` wraps ```Passcode```
to generate a list op available directions (a list of which directions are 'open') for the current point of a route.
```Point``` provides a position and navigation within a 4x4 grid (so, you cannot move off the grid). 
```Finder.solve()``` implements an abstract/generic BSF search, and RouteFinder implements the generic BSF for this 
problem (yes, it's a bit over-designed). ```Me``` is a helper class that is able to determine the current 
location at the end of a route (string).

For part 1, I just used RouteFinder to find a solution. For part 2, a small change was required to find all solutions 
to the destination (simply don't stop after finding one, but stop when the queue of next options is empty). I 
refactored RouteFinder to always find all routes (so I only need to run it once), then grouped the routes by their 
length in a map, filter the shortest route (part 1), and the longest route (part 2) to solve day 17.

## Day 18
Basic arithmetic, nothing really fancy. Created a ```Room``` class to hold the room related methods. The class gets 
created using a factory method ```Room.from()``` which takes a layout line (first line of tiles), and generates a
room according to the requested size (it build an array of arrays of ```Tile```s). Depending on the symbol during
initialization, a tile is safe, or it's a trap. The slightly sophisticated part is the ```TileRowSupplier``` which
generates the subsequent rows from the current row (it's a ```Supplier<Tile[]>```).

Part 1 and two are identical, although the room for part 2 is 10,000 times larger. On my laptop this wasn't any
issue at all end didn't cause any problems.

## Day 19
The name of the puzzle refers to the [Josephus problem](https://www.youtube.com/watch?v=uCsD3ZGzMgE), and there 
is a very simple algorithm to solve that. 

On part two [aceshades](https://www.reddit.com/user/aceshades/) published a brilliant solution using two 
```Deque<Integer>``` (though his solution is in Python), published on 
[Reddit](https://www.reddit.com/r/adventofcode/comments/5j4lp1/2016_day_19_solutions/). He uses a deque left (counting
up: 1, 2, 3, ...) and right (counting down: 1000, 999, 998, ...), making left and right a full ordered circle 
counter-clockwise.

While there are elements in one of the deque's, removes the last element from the longest queue (the ```right```
one, if they're of equal size), and then rotates (moves first element from ```left``` as first element on the 
```right```deque, and last element of the ```right``` as last element of the ```left``` deque). This means the entire
circle moves clockwise 1 step forward (clockwise), so the first Elf on the left deque has the turn.

A Java ```LinkedList``` holds references to the first and last element in the list, making additions and removals to
either sides of the deque very fast.

## Day 20
Started with a ```Range``` record with a lower and upper bound (to represent ranges of IP addresses). The type
can identify ```overlap()``` (or adjacent) with another range, and if so, can ```merge()``` two ranges into one new 
range. The ```RangeSet```, loads the input data, checks for overlap with known ranges, and merges in case of overlap
or adjacent ranges.

For part 1, get the first range (of the ordered list of known ranges), and add 1 to the upper bound value. For part 2,
the number of allowed addresses is MAX_VALUE_INCLUSIVE minus the number of blocked IP addresses if all known blocked 
ranges.
