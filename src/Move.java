
public class Move {
	public int origin;
	public int dest;
	Move(int origin, int dest) {
		this.origin = origin;
		this.dest = dest;
	}
	
	Move(int row, int col, int row1, int col1) {
		origin = Helper.toOne(row, col);
		dest = Helper.toOne(row1, col1);
	}
	
	int getOrigin() {
		return origin;
	}
	
	int getDest() {
		return dest;
	}
	
	public String toString() {
		return "from: " + origin + " to: " + dest;
	}
}
