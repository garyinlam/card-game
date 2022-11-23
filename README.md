# Card Games - Java

A java program that runs using the terminal. 
Uses a Card class to represent each playing card.
Each card has a suit (represented using unicode symbol), a symbol (A,2,3,4,5,6,7,8,9,10,J,Q,K) and associated value (Ace high).

Cards are used to create a deck of cards. 
This deck can be sorted by value or suit or be shuffled.

## Technologies

- Java

## Snap
Snap can be played as a 2 player game or versus the computer.

Players take turns dealing a card off the top of a shuffled deck.

If the dealt card's value matches that of the card that was previously dealt, the player has two seconds to type the word 'snap' to win.

In the event 'snap' is not type or mistyped that player loses.

When playing versus the computer the player must type snap to win, or the computer will win.

![Screenshot](/screenshots/Screenshot%202022-11-23%20140445.jpg)


## Blackjack
Blackjack is played versus the dealer only.

The aim of blackjack is to have the score of your cards be higher than the dealer's, but no higher than 21.

The player can choose up to 10 rounds of play.

Each round, the dealer deals a card to the player and themselves. 
A second card is then dealt to the player. 
The dealer deals themselves a second card face down.

The player can now choose to 'hit' or 'stand'.
- Hitting will give the player another card.
  - If the player's score is above 21, they will bust and their turn will end.
- Standing will end the player's turn.

![Screenshot](/screenshots/Screenshot%202022-11-23%20140319.jpg)


### Card scores:
- Number cards have their face value (2-10)
- Face cards (J,Q,K) have a score of 10
- An ace (A) is either 1 or 11. It is scored at 11 unless that would take the player's score above 21, then it is scored as 1

When the player has finished their turn, the dealer will reveal their face down card.

The dealer will then act according to their score.

- The dealer will hit until their score is greater than or equal to 17
  - If the dealer has a score over 21 they will bust
- The dealer will stand if their score is greater than or equal to 17

The winner of the round is then decided:
- If both players bust, no-one will be declared the winner
- If only one player busts, the player that did not bust will win
- If no player busts, the player with the higher score for that round will win
  - If the scores are equal, the round is a tie and no-one wins

After the specified number of rounds, the player is told how many rounds they won.

## War
War can be played as a 2 player game or versus the computer.

This version of war plays with a simplified rule set.

The aim of war is to collect more cards than the opponent.

Both players are given half of a deck of cards.

Each turn both players deal the top card of their deck.

These cards are then compared on their value (Ace high).
- The card with the higher value wins, the victor wins those cards and any left on the table from draws
- In the case of a draw both cards are held for the next round

The game ends when both players have no more unplayed cards.

The winner is the player with more cards at the end.

![Screenshot](/screenshots/Screenshot%202022-11-23%20140516.jpg)
