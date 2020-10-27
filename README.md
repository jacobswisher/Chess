# SwishChess

SwishChess is a simple GUI (Graphical User Interface) for playing chess. Two people can play together on one system or you can battle the computer!  



![alt text](https://cdn.pixabay.com/photo/2018/11/07/23/51/chess-3801531_960_720.jpg)

## Author

Created by Jacob Swisher <jacobswisher853@gmail.com>

## Description of materials

This Java project consists of 11 classes, a manifest file, and an executable JAR.

## Installation

You must have Java installed to run this program. You can find the installation for Windows here: https://www.java.com/en/download/

To install, click on the "Code" dropdown at the top of the repository, then click "download ZIP". Excract the zipped file then go to SwishChess -> run. 
Right click on "jarexample.jar" then click "Open". You can now play SwishChess!

## Usage

To play the game, choose whether you want to play as black or white. If you are playing as black, click "AI Move" then make your move. If you are playing as white play your move immediately.

To make a move, insert your starting square (E.g. "e2") in the first text box and your ending square in the next text box. The program will then tell you if your move is valid and move it if so.

When you are done with your game simply close the program and open again to start a new game.

## Code example 

This code checks if the player is in checkmate
```Java
boolean checkmate(boolean white) {
		Move m;
		for (int i = 0; i < 64; i++) {
			for (int j = 0; j < 64; j++) {
				m = new Move(i,j);
				if (validMove(m, white) && !inCheckAfter(m, white)) {
					return false;
				}
			}
		}
		return true;
	}
```

## FAQ

Question: My game wont run, whats going on?
Answer: Make sure you have Java installed. If it still doesn't run try to run the program as administrator.

Question: Why can't I en passent/ promote to something other than a queen? 
Answer: These moves have not been implemented yet.

Question: Why is the A.I. so bad?
Answer: The "A.I." only makes random legal moves.

## Known Issues

1. When moving, kings can occasionally jump to the other side of the board.
2. Sometimes the A.I. will not realize it is in check and play an illegal move that does not prevent its capture

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
