# Programming Assignment 0 - Java Warm-up

- Instructor: John Foley
- CS341BD - Building a Digital Library
- Spring 2017, Mount Holyoke College

## Motivation & Learning Goals

This programming assignment is designed to get you thinking about Java programming again, and to exercise your abilities to find patterns in data.

## Introduction

Rummy is the name for a whole family of card games. We will be designing a program that understands how to score hands (sets of cards). Typically, all cards that satisfy certain properties are matched, and a user is charged for all cards that don’t belong to a set. The objective of games is often to get the lowest score.
(See the [Wikipedia Entry](https://en.wikipedia.org/wiki/Rummy) for a detailed discussion, history, etc).

Our program will be able to accept strings of input and output information about the valid groupings.

## Playing Cards
A standard deck of playing cards has 52 individual cards. There are four “suits” - Hearts, Diamonds, Clubs and Spades. We will abbreviate the suits by their first letter in caps: “H”, “D”, “C”, “S”.

Each suit contains the same 13 cards: [Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King].

We will abbreviate the cards by their first letter or their full number: 
[A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]

## Rummy Cost of Cards:

Each of the cards is considered to have a numeric cost: numeric cards use their face value, and the rest are assigned somewhat in order: J=11, Q=12, K=13, A=15. The ace “A” is worth more because of its use in runs, it motivates players to “get rid of them” first.

## Runs or Melds:
If a player has 3 or more cards of the same suit in order, this is considered to be a “run”.

- A valid run of four cards: “2H 3H 4H 5H”
- An invalid run of three cards: “6H 7D 8H” - they must be the same suit.
- A valid run of three cards: “QH KH AH” - Aces are allowed in runs above a King.
- A valid run of three cards: “AH 2H 3H” - Aces are also allowed in runs below a 2.
- An invalid run of three cards: “KH AH 2H” - the numbering system does not wrap around.

## Sets:
If a player has 3 or more of a particular card, this is considered to be a valid “set”.

- A valid set of cards: “3H 3D 3S”
- A valid set of cards: “3H 3D 3S 3C”

## Example Program Run:

Input:

      KS 2H 3H 4H 5H 5D 5S

Output:

    Valid Plays: 
        Run(2H 3H 4H)
        Set(5H 5D 5S)
    Leftovers: 
        KS
    Score: 
        13

## The Extra Mile: Picking the best order:
Someone familiar with these card games might note that the previous example could be played differently, but it would lead to a higher, worse score. 

The Same Input:

    KS 2H 3H 4H 5H 5D 5S

Another Output:
    
    Valid Plays: 
        Run(2H 3H 4H 5H)
    Leftovers: 
        KS 5D 5S
    Score: 
        23

How might your program deal with this?
