import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;


public class ChessGUI {  
	static Game game;
	static Enemy enemy;
	final JTextField origin;
	final JTextField dest;
	static ChessGUI gui;
	JLabel move1;
	JLabel board = new JLabel("");
	JLabel board1 = new JLabel("");
	JLabel board2 = new JLabel("");
	JLabel board3 = new JLabel("");
	JLabel board4 = new JLabel("");
	JLabel board5 = new JLabel("");
	JLabel board6 = new JLabel("");
	JLabel board7 = new JLabel("");
	JLabel board8 = new JLabel("");
	
	public ChessGUI(Game game, Enemy enemy) {
		JFrame f=new JFrame("Swish Chess"); 
		
		origin = new JTextField();  
		origin.setBounds(10,10, 150, 20);  
		
		dest = new JTextField();  
		dest.setBounds(10,35, 150, 20);  
		
		move1 = new JLabel("Enter move:");
		move1.setBounds(10, 105, 150, 20);
		
		JButton b=new JButton("Submit Move");  
		b.setBounds(10,60,150,40);  
		b.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				action();
			}});  
		
		JButton b2=new JButton("AI Move");  
		b2.setBounds(160,60,150,40);  
		b2.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				enemyTurn();
			}});
		
		
		board.setBounds(10, 130, 300, 25);
		board.setFont(new Font("Futura", Font.PLAIN, 25));
		board1.setBounds(10, 155, 300, 25);
		board1.setFont(new Font("Futura", Font.PLAIN, 25));
		board2.setBounds(10, 180, 300, 25);
		board2.setFont(new Font("Futura", Font.PLAIN, 25));
		board3.setBounds(10, 205, 300, 25);
		board3.setFont(new Font("Futura", Font.PLAIN, 25));
		board4.setBounds(10, 230, 300, 25);
		board4.setFont(new Font("Futura", Font.PLAIN, 25));
		board5.setBounds(10, 255, 300, 25);
		board5.setFont(new Font("Futura", Font.PLAIN, 25));
		board6.setBounds(10, 280, 300, 25);
		board6.setFont(new Font("Futura", Font.PLAIN, 25));
		board7.setBounds(10, 305, 300, 25);
		board7.setFont(new Font("Futura", Font.PLAIN, 25));
		board8.setBounds(10, 330, 300, 25);
		board8.setFont(new Font("Futura", Font.PLAIN, 20));
		
		
		updateStringBoard(game);
		
		
		f.add(b);f.add(b2);f.add(origin);f.add(dest);f.add(move1);
		f.add(board);f.add(board1);f.add(board2);f.add(board3);
		f.add(board4);f.add(board5);f.add(board6);f.add(board7); f.add(board8);
		f.setSize(400,400);  
		f.setLayout(null);  
		f.setResizable(false);
		f.setVisible(true);   
	}
	
	public void updateStringBoard(Game game) {
		board.setText("8 " + boardToString(game.getMainBoard(), 0));
		board1.setText("7 " + boardToString(game.getMainBoard(), 1));
		board2.setText("6 " + boardToString(game.getMainBoard(), 2));
		board3.setText("5 " + boardToString(game.getMainBoard(), 3));
		board4.setText("4 " + boardToString(game.getMainBoard(), 4));
		board5.setText("3 " + boardToString(game.getMainBoard(), 5));
		board6.setText("2 " + boardToString(game.getMainBoard(), 6));
		board7.setText("1 " + boardToString(game.getMainBoard(), 7));
		board8.setText("    a   b  c   d  e  f    g  h");
	}
	
	public String boardToString(Board board, int row) {
		String string = "";
		for (int col = 0; col < 8; col++) {
			Piece piece = board.getPiece(row, col);
			if (board.isEmpty(row, col)) {
				string += "\u2573";
				continue;
			}
			if (piece.isWhite()) {
				if (piece.getType() == 'R') string += "\u2656";
				else if (piece.getType() == 'B') string += "\u2657";
				else if (piece.getType() == 'P') string += "\u2659";
				else if (piece.getType() == 'N') string += "\u2658";
				else if (piece.getType() == 'K') string += "\u2654";
				else if (piece.getType() == 'Q') string += "\u2655";
			}
			else {
				if (piece.getType() == 'R') string += "\u265C";
				else if (piece.getType() == 'B') string += "\u265D";
				else if (piece.getType() == 'P') string += "\u265F";
				else if (piece.getType() == 'N') string += "\u265E";
				else if (piece.getType() == 'K') string += "\u265A";
				else if (piece.getType() == 'Q') string += "\u265B";
			}
		}
		return string;
	}
	
	public void action() {
		try {
			//test if you can move and move
			Move m = new Move(
					Helper.translateRow(origin.getText().charAt(1)),
					Helper.translateCol((int) origin.getText().charAt(0)),
					Helper.translateRow(dest.getText().charAt(1)),
					Helper.translateCol((int) dest.getText().charAt(0))
					);
			if (game.getMainBoard().move(m, game.getWhite())) {
				move1.setText("Valid Move");
				updateStringBoard(game);
				game.toggleWhite();
				origin.setText("");  
				dest.setText("");
			}
			else {
				move1.setText("Invalid Move");
			}
			 
		} catch (Exception t) {
			move1.setText("Text Field is bad try again");
			System.out.println(t);
		}
	}  
	
	public void enemyTurn() {
		for (int i = 0; i < 1; i++)
			enemy.AI(game, gui);
	}
	

	public static void main(String[] args) {  
		game = new Game();
		enemy = new Enemy();
		gui = new ChessGUI(game, enemy);
	}  

}  


