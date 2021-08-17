# Design Report
### 曾揚哲 B08902134

---

## Software Design

### Card (58 SLOC)
This is the class for the Poker Card, which has 2 fields, suit and rank.
There are two constructors for this class, one of which is for some constant settings, and the other can be used to handle the input. 
* getters: ```getRank()```, ```getSuit()``` To get the properties of a Card instance.
* ```getNum()```  for the convenience of sorting and comparing.
* ```@Override equals()``` for some built-in function, e.g. ```contains()```
* ```toString()``` for the output

<!-- ### Configuration (42 SLOC)
The class for configurations, used to put OCP into practice.
* ```addChecker(PlayChecker)```: To add an instance of play checker to check for a play (e.g. straight)
* ```loadStraights(String)```: To load all possible combinations of straights (or flushes) from the specific path. If the path is not specified or the file specified by the path is invalid, load the default settings (i.e. HW's spec). -->

<!-- ### FullHouse, Pair, Single, Straight 
#### (18, 15, 15, 27 SLOC, respectively)
The classes that implement *PlayChecker*, used to check whether the given play is the given type.
* ```check(ArrayList\<Card\>)```: Returns if the given card combination is a given type.
* ```getStyle()```: Returns *PlayStyle*.?, for implementation. -->

### Game (79 SLOC)
The whole game flow, including cards dealing.
* constructor ```Game()```: To initialize the fields.
* ```prepare()```: To read the input and deal the deck to all players.
* ```start()```: To determine the first player and call ```handleRound()```
* ```handleRound()```: To handle each round, play the legal action for each player and communicate with *HandleIO* to print the information.
* ```getCurrentPlay()```: To get the valid action from the input calling *HandleIO* and play the cards if the actions is not to pass.

### HandleIO (49 SLOC)
The class to handle the input and output, which contains the scanner used throughout the whole program.
* ```NL()```, ```never_loses()```: The identical method to ```System.in.nextLine()```, just for the mischief as ```nextLine()``` has the same abbreviation as my favorite streamer.
* ```output(Message, [String, [Play]])```: To output the corresponding message based on the arguments passed in.

### Main (10 SLOC)
The entry point of the whole program.
* ```main(String[])```: To load the configurations and start the game.

### Message (10 SLOC)
The **enum** that defines all types of *Message*s.

### Play (90 SLOC)
The class that indicates a play, or a set of cards. 
* constructors ```Play (ArrayList<Card>)```, ```Play ()```, ```Play (PlayStyle)```: To initialize an instance with given arguments.
* getters ```getStyle()```, ```getDom()```, ```getCards()```: Returns the play style, dominating card and all cards, respectively.
* ```toString()``` For the convenience of the output.

<!-- ### PlayChecker (6 SLOC)
An interface that defines what a *PlayChecker* implementation should look like. -->

### PlayStyle (18 SLOC)
The **enum** that defines all types of *PlayStyle*s.

### Player (80 SLOC)
The class that indicates a player with some related methods.
* ```deal(Card)```, ```deal(ArrayList<Card>)```: Called by *Game* to deal a card or cards to a player.
* ```arrange()```: To sort the hand cards.
* ```getHand()```: Returns the string that represents the hand cards of a player.
* ```play(String)```: Called by *Game* to play cards based on the input.

### Suit (7 SLOC)
The **enum** that defines all types of *Suit*s.

    
## Bonus Design: Open-Closed Principle (OCP)
> You will either get 0 or 20 points on this bonus.
> If you can't achieve it, don't report anything here.