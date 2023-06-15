# CS611-<pa2>
## <Trianta Ena>
---------------------------------------------------------------------------
Gong Chen, Li Xi.

## Files
---------------------------------------------------------------------------
<A brief description of each file and what it does>
Main.java 		 add the Trianta End game to a game room, the demo of the Trianta Ena game.
Package: Game
|______Game.java：   	 the abstract class of game, have several abstract methods like run(), addPlayer()... also implements some method like restart()...
|______TEGame.java: 	 the TEGame subclass inherits from abstract Game base class to further describe the elements in Trianta Ena, including player team, banker team, money amount, and score. It also describes the process of the game starting from creating teams and dealing cards to placing bets, hit/stand and going bust (or winning).
|______GameSet.java: 	 the class of a set of games.
|
|______	Package: GameItem
|	|______	GameItem.java: The GameItem class draws a template for the item(s) needed for playing the game.
|
|	|______	Package: Card
|   |       |______Card.java：The Card class inherits from GameItem to further describe the item，which is poker card used in the Trianta Ena game.
|   |       |______Hands.java：Hands Class is inherited from CardsPool base class. It adds and reads cards in players' hands.
|   |       |______CardsPool.java：CardsPool class creates a pool of cards for either used or unused cards.
|   |       |______RecycleCardsPool.java：Recycle used cards into the unused card pool when the unused card pool is empty.
|
|______	Package: Log
|	|______	GameLog.java:	the class of one log, to record the result of one game, which team wins.
|	|______	LogList.java: 	the class of a list of logs, record the results of different games, and calculate the final number of the wining of each team.
|
|______	Package: GameRoom
|	|______	GameRoom.java:  the class of a game room, add one or multiple games in the game room, show the games, and run the games.
|
Package: GameUtil
|______	Gameutil.java:	implements a tool to judge whether a string is a number.
|______	Scanin.java:	the Scanin class describes a few methods to scan integer, string and line.
|
Package: Player
|______Player.java:		the abstract class of player declares common variables of a player, such as name. Also implementS some methods such as win(), lose().
|______tePlayer.java:           the tePlayer class inherits from Player class and implements the comparable interface. It declares more specific variables for a Trianta Ena player, such as score, cards in hand, money, if the player is bust, if the player is banker, and some actions to the cards in hand.
|______Team.java:               the abstract class of Team declares a few common variables of a team, such as name and size. It has several abstract methods like addPlayer(), removePlayer(), setName(), getName(), win(), and lose().
|______teTeam.java:             the teTeam class inherits from Team and implements Comparable interface. It further declares attributes which are more specific to Trianta Ena teams, such as score, money, etc.

## Notes
---------------------------------------------------------------------------
1. <Files to be parsed should be stored in ConfigFiles, for parser class to read class>
None

2. <Bonus Done>


3. <Notes to grader>
:)

## How to compile and run
---------------------------------------------------------------------------
1. Download the zip "TriantaEna" and unzip it.
2. Navigate to the directory "TriantaEna/src".
3. Run the following instructions:
javac -d bin *.java
java -cp bin Main

## Input/Output Example
---------------------------------------------------------------------------
<Place here an example of how the program runs. Include both its
outputs and correctly formatted inputs. Please clearly mark the inputs.>
Output:
	Welcome to the TriantaEna!
	Please input the number of players:
Input:
	3
Output:
	Please input Player 0's name:
Input:
	Zoe
Output:
	Please input Player 1's name:
Input:
	Jack
Output:
	Please input Player 2's name:
Input:
	Amy
Output:
	The TriantaEna Game need a first banker.
	Hello,Zoe!Do you want to be the Banker?(Y/N)
Input:
	n
Output:
	Hello,Jack!Do you want to be the Banker?(Y/N)
Input:
	n
Output:
	Hello,Amy!Do you want to be the Banker?(Y/N)
Input:
	n
Output:
	Since no one want to be the Banker, Game will randomly choose one lucky guy to be the banker:
	The First Banker is:Amy
	Round 1 start!
	First Card dealt to Banker:Amy
	Since this is Banker's first Card, it's known to all of players, the card is:♣6
	-----------------------------------------
	Hello,Zoe!
	This is your Card♥K
	-----------------------------------------
	Hello,Jack!
	This is your Card♣10
	-----------------------------------------
	Hello everyone! Now It's time to choose whether to place a bet
	Hello,Zoe!
	This is overall information:
	★Amy(banker rest money:30000):♣6
	Jack(rest money:10000, current bet:0):*
	Zoe(rest money:10000, current bet:0):♥K
	-----------------------------------------
	Do you want to place a bet? (Y/N) (Y means you choose to take a bet and N means you choose to fold)
Input:
    y
Output:
    Great!How much do you want to bet?(Your rest money is:+10000)
Input:
    100
Output:
    Your bet is:100,and your rest money is:9900
    -----------------------------------------
    Hello,Jack!
    This is overall information:
    ★Amy(banker rest money:30000):♣6
    Zoe(rest money:9900, current bet:100):*
    Jack(rest money:10000, current bet:0):♣10
    -----------------------------------------
    Do you want to place a bet? (Y/N) (Y means you choose to take a bet and N means you choose to fold)
Input:
    y
Output:
    Great!How much do you want to bet?(Your rest money is:+10000)
Input:
    200
Output:
    Your bet is:200,and your rest money is:9800
    -----------------------------------------
    Hello everyone! Here are your rest 2 cards!
    Hello,Zoe!
    These are your Cards♥6,♦2
    -----------------------------------------
    Hello,Jack!
    These are your Cards♦6,♦8
    -----------------------------------------
    Hello,Zoe!This is overall information:
    ★Amy(banker rest money:30000):♣6
    Jack(rest money:9800, current bet:200):* ♦6 ♦8
    Zoe(rest money:9900, current bet:100):♥K ♥6 ♦2
    -----------------------------------------
    Do you want to hit?(Y/N)
Input:
    y
Output:
    This is your Card♣3
    Now the cards in your hand are:
    ♥K ♥6 ♦2 ♣3
    Do you want to hit once more?
Input:
    y
Output:
    This is your Card♠K
    Now the cards in your hand are:
    ♥K ♥6 ♦2 ♣3 ♠K
    Do you want to hit once more?
Input:
    n
Output:
    Hello,Jack!This is overall information:
    ★Amy(banker rest money:30000):♣6
    Zoe(rest money:9900, current bet:100):* ♥6 ♦2 * *
    Jack(rest money:9800, current bet:200):♣10 ♦6 ♦8
    -----------------------------------------
    Do you want to hit?(Y/N)
Input:
    y
Output:
    This is your Card♣8
    AND BOOM! YOU ARE BUST WITH HANDS:
    ♣10 ♦6 ♦8 ♣8
    It's time to reveal your cards to BANKER!
    Zoe(rest money:9900, current bet:100):
    ♥K ♥6 ♦2 ♣3 ♠K
    Jack(rest money:9800, current bet:200):
    ♣10 ♦6 ♦8 ♣8
    Banker hit time!
    Hello,Amy!Do you want to hit? (Y/N)
Input:
    y
Output:
    This is your Card♥5
    Now the cards in your hand are:
    ♣6 ♥5
Input:
    Do you want to hit once more?(Y/N)
Input:
    y
Output:
    This is your Card♣2
    Now the cards in your hand are:
    ♣6 ♥5 ♣2
    Do you want to hit once more?(Y/N)
Input:
    y
Output:
    This is your Card♥8
    Now the cards in your hand are:
    ♣6 ♥5 ♣2 ♥8
    Do you want to hit once more?(Y/N)
Input:
    y
Output:
    This is your Card♥A
    Now the cards in your hand are:
    ♣6 ♥5 ♣2 ♥8 ♥A
    Do you want to hit once more?(Y/N)
Input:
    y
Output:
    This is your Card♣5
    And your hands value is equals or exceeds 27 with hands:
    ♣6 ♥5 ♣2 ♥8 ♥A ♣5
    So you are not allowed to hit
    ★Amy(banker rest money:30000)♣6 ♥5 ♣2 ♥8 ♥A ♣5
    Banker's hands value is:27
    Zoe(rest money:9900, current bet:100)♥K ♥6 ♦2 ♣3 ♠K
    Zoe's hands value is:31
    Congratulation,Zoe! You win! and you can get an extra 100 from the Banker!
    Jack(rest money:9800, current bet:200)♣10 ♦6 ♦8 ♣8
    Jack is bust
    Although not a winner,but you can take your bet back!
    Zoe's money:10100
    Jack's money:10000
    Hello,Zoe!Do you want to play another round? Y/N
Input:
    y
Output:
    Hello,Jack!Do you want to play another round? Y/N
Input:
    y
Output:
    Round 2 start!
    First Card dealt to Banker:Amy
    Since this is Banker's first Card, it's known to all of players, the card is:♣A
    -----------------------------------------
    Hello,Zoe!
    This is your Card♦8
    -----------------------------------------
    Hello,Jack!
    This is your Card♣Q
    -----------------------------------------
    Hello everyone! Now It's time to choose whether to place a bet
    Hello,Zoe!
    This is overall information:
    ★Amy(banker rest money:29900):♣A
    Jack(rest money:10000, current bet:0):*
    Zoe(rest money:10100, current bet:0):♦8
    -----------------------------------------
    Do you want to place a bet? (Y/N) (Y means you choose to take a bet and N means you choose to fold)
Input:
    y
Output:
    Great!How much do you want to bet?(Your rest money is:+10100)
Input:
    10100
Output:
    Your bet is:10100,and your rest money is:0
    -----------------------------------------
    Hello,Jack!
    This is overall information:
    ★Amy(banker rest money:29900):♣A
    Zoe(rest money:0, current bet:10100):*
    Jack(rest money:10000, current bet:0):♣Q
    -----------------------------------------
    Do you want to place a bet? (Y/N) (Y means you choose to take a bet and N means you choose to fold)
Input:
    y
Output:
    Great!How much do you want to bet?(Your rest money is:+10000)
    10000
    Your bet is:10000,and your rest money is:0
    -----------------------------------------
    Hello everyone! Here are your rest 2 cards!
    Hello,Zoe!
    These are your Cards♥9,♣6
    -----------------------------------------
    Hello,Jack!
    These are your Cards♠K,♠4
    -----------------------------------------
    Hello,Zoe!This is overall information:
    ★Amy(banker rest money:29900):♣A
    Jack(rest money:0, current bet:10000):* ♠K ♠4
    Zoe(rest money:0, current bet:10100):♦8 ♥9 ♣6
    -----------------------------------------
    Do you want to hit?(Y/N)
Input:
    y
Output:
    This is your Card♠A
    Now the cards in your hand are:
    ♦8 ♥9 ♣6 ♠A
    Do you want to hit once more?
Input:
    y
Output:
    This is your Card♠7
    Now the cards in your hand are:
    ♦8 ♥9 ♣6 ♠A ♠7
    Do you want to hit once more?
Input:
    n
Output:
    Hello,Jack!This is overall information:
    ★Amy(banker rest money:29900):♣A
    Zoe(rest money:0, current bet:10100):* ♥9 ♣6 * *
    Jack(rest money:0, current bet:10000):♣Q ♠K ♠4
    -----------------------------------------
    Do you want to hit?(Y/N)
Input:
    y
Output:
    This is your Card♦Q
    AND BOOM! YOU ARE BUST WITH HANDS:
    ♣Q ♠K ♠4 ♦Q
    It's time to reveal your cards to BANKER!
    Zoe(rest money:0, current bet:10100):
    ♦8 ♥9 ♣6 ♠A ♠7
    Jack(rest money:0, current bet:10000):
    ♣Q ♠K ♠4 ♦Q
    Banker hit time!
    Hello,Amy!Do you want to hit? (Y/N)
Input:
    y
Output:
    This is your Card♣7
    Now the cards in your hand are:
    ♣A ♣7
    Do you want to hit once more?(Y/N)
Input:
    y
Output:
    This is your Card♦K
    Now the cards in your hand are:
    ♣A ♣7 ♦K
    Do you want to hit once more?(Y/N)
Input:
    y
Output:
    This is your Card♠10
    And your hands value is equals or exceeds 27 with hands:
    ♣A ♣7 ♦K ♠10
    So you are not allowed to hit
    ★Amy(banker rest money:29900)♣A ♣7 ♦K ♠10
    Banker's hands value is:28
    Zoe(rest money:0, current bet:10100)♦8 ♥9 ♣6 ♠A ♠7
    Zoe's hands value is:31
    Congratulation,Zoe! You win! and you can get an extra 10100 from the Banker!
    Jack(rest money:0, current bet:10000)♣Q ♠K ♠4 ♦Q
    Jack is bust
    Although not a winner,but you can take your bet back!
    Zoe's money:20200
    Do you want to be the new Banker?
Input:
    y
Output:
    The new Banker is:Zoe!
    Jack's money:10000
    Hello,Zoe!Do you want to play another round? Y/N
Input:
    n
Output:
    Thanks for playing!
    Zoe choose to cash out
    Hello,Jack!Do you want to play another round? Y/N
Input:
    n
Output:
    Thanks for playing!
    Jack choose to cash out
    Players number is less than 2,the game end!
    This is the score of every player:
    Zoe:20200
    Jack:10000
    Amy:19800


