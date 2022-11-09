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

