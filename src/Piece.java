

public abstract class Piece {
	private char type;
	private boolean white;
	
	
	Piece(char type, boolean white) {
		this.type = type;
		this.white = white;
	}
	
	void setType(char type) {
		this.type = type;
	}
	
	void setWhite(boolean white) {
		this.white = white;
	}
	
	char getType() {
		return type;
	}
	
	boolean isWhite() {
		return white;
	}
	
	public abstract boolean canMove(Move m); 
}
