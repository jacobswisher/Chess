
public class Helper {
	public static int getRow(int num) {
		return (num/8);
	}
	public static int getCol(int num) {
		return (num%8);
	}
	public static int toOne(int row, int col) {
		return (row*8+col);
	}
	public static int translateRow(char row) {
		row-=48;
		return (int)Math.abs(row-8);
	}
	public static int translateCol(int col) {
		return col-97;
	}
}
