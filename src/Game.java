
public class Game {
	boolean white;
	Board mainBoard;
	Game() {
		white = true;
		mainBoard = new Board();
	}
	
	public void toggleWhite() {
		white = !white;
	}
	
	public boolean getWhite() {
		return white;
	}
	
	public void setMainBoard(Board mainBoard) {
		this.mainBoard = mainBoard;
	}
	
	public Board getMainBoard() {
		return mainBoard;
	}
	
	
	
}
