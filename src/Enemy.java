import java.util.Random;

public class Enemy {
	int difficulty = 0;
	
	Enemy() {
		
	}
	
	public void AI(Game game, ChessGUI gui) {
		Move[] arr = game.getMainBoard().getValidMoves(game.getWhite());
		Random rand = new Random();
		
		int i;
		for (i = 0; i < arr.length; i++) {
			if (arr[i] == null) break;
		}
		if (i == 0) {
			gui.move1.setText("CheckMate");
			return;
		}
		for (int j = 0; j < i; j++) {
			System.out.println(j + ". " + arr[j].toString());
		}
		System.out.println("\n");
		
		int random = rand.nextInt(i);
		String color;
		if(game.getWhite()) color = "white";
		else color = "Black";
		System.out.println(color + " moved: " + arr[random].toString());
		game.getMainBoard().move(arr[random], game.getWhite());
		game.toggleWhite();
		gui.updateStringBoard(game);
	}
}
