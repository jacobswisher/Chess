

public class Board {
	private Piece[] board;
	
	
	Board() {
		initBoard();
	}
	
	Board(boolean type) {
		board = null;
	}
	
	void initBoard() {
		board = new Piece[64];
		//initiate black pieces, this is the fastest way to add pieces at run time
		board[0] = new Rook(false);
		board[1] = new Knight(false);
		board[2] = new Bishop(false);
		board[3] = new Queen(false);
		board[4] = new King(false);
		board[5] = new Bishop(false);
		board[6] = new Knight(false);
		board[7] = new Rook(false);
		board[8] = new Pawn(false);
		board[9] = new Pawn(false);
		board[10] = new Pawn(false);
		board[11] = new Pawn(false);
		board[12] = new Pawn(false);
		board[13] = new Pawn(false);
		board[14] = new Pawn(false);
		board[15] = new Pawn(false);
		
		//initiate white pieces
		board[48] = new Pawn(true);
		board[49] = new Pawn(true);
		board[50] = new Pawn(true);
		board[51] = new Pawn(true);
		board[52] = new Pawn(true);
		board[53] = new Pawn(true);
		board[54] = new Pawn(true);
		board[55] = new Pawn(true);
		board[56] = new Rook(true);
		board[57] = new Knight(true);
		board[58] = new Bishop(true);
		board[59] = new Queen(true);
		board[60] = new King(true);
		board[61] = new Bishop(true);
		board[62] = new Knight(true);
		board[63] = new Rook(true);
	}
	
	void setBoard(Piece[] board) {
		this.board = board;
	}
	
	void setPiece(Piece piece, int place) {
		board[place] = piece;
	}
	
	Piece[] getBoard() {
		return board;
	}
	
	Piece getPiece(int place) {
		return board[place];
	}
	
	Piece getPiece(int row, int col) {
		return board[Helper.toOne(row, col)];
	}
	
	boolean isEmpty(int place) {
		return (board[place] == null);
	}
	
	boolean isEmpty(int row, int col) {
		return isEmpty(Helper.toOne(row, col));
	}
	
	boolean inCheck(boolean white) {
		int kingPos = 0;
		for (int i = 0; i < 64; i++) {
			if (!isEmpty(i) && board[i].getType() == 'K' && board[i].isWhite() == white) {
				kingPos = i;
				break;
			}
				
		}
		for (int i = 0; i < 64; i++) {
			if (validMove(new Move(i,kingPos),!white)) return true;
		}
		return false;
	}
	
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
	
	boolean inCheckAfter(Move m, boolean white) {
		Piece piece = board[m.dest];
		board[m.dest] = board[m.origin];
		board[m.origin] = null;
		if (inCheck(white)) {
			board[m.origin] = board[m.dest];
			board[m.dest] = piece;
			return true;
		}
		board[m.origin] = board[m.dest];
		board[m.dest] = piece;
		return false;
	}
	
	boolean validMove(Move m, boolean white) {
		//out of bounds
		if (m.origin > 63 || m.origin < 0 || m.dest > 63 || m.dest < 0) return false;
			
		//no movement happened
		if (m.origin == m.dest) return false;
		
		//attempting to move a piece that does not exist
		if (board[m.origin] == null) return false;
				
		//player is playing wrong piece color
		if (board[m.origin].isWhite() != white) return false;
		
		//friendly fire
		if(!isEmpty(m.dest) && board[m.origin].isWhite() == board[m.dest].isWhite()) return false;
				
		//if legal movement type
		if (board[m.origin].canMove(m)) {
			//check if there are pieces in the way
			//Knights are not checked since they can jump pieces
			if (board[m.origin].getType() != 'N' && isBlocked(m, white)) return false;
			//pawn movement
			if (board[m.origin].getType() == 'P')
			{
				//pawn diagonally attacking
				if (Math.abs(m.origin-m.dest) == 7 || Math.abs(m.origin-m.dest) == 9) {
					if (isEmpty(m.dest)) return false;
				}
				//2 move
				else if (m.origin-m.dest == 16) 
					if (!isEmpty(m.origin - 8) || !isEmpty(m.origin - 16)) return false;
				else if (m.origin-m.dest == -16)
					if (!isEmpty(m.origin + 8) || !isEmpty(m.origin + 16)) return false;
				
				//1 move
				else {
					if (white) if (!isEmpty(m.origin - 8)) return false;
					else if (!isEmpty(m.origin + 8)) return false;
				}
				
			}
			return (true);
		}
		return false;
	}
	
	boolean move(Move m, boolean white) {
		if (validMove(m, white) && !inCheckAfter(m, white)) {
		
			
			board[m.dest] = board[m.origin];
			board[m.origin] = null;
			//need to promote?
			if (board[m.dest].getType() == 'P') {
				
				if (Helper.getRow(m.dest) == 0) {
					board[m.dest] = new Queen(true);
				}
				else if (Helper.getRow(m.dest) == 7) {
					board[m.dest] = new Queen(false);
				}
			}
			return true;
		}
		return false;
	}
	
	
	Move[] getValidMoves(boolean white) {
		Move[] arr = new Move[100];
		Move m;
		int it = 0;
		for (int i = 0; i < 64; i++) {
			for (int j = 0; j < 64; j++) {
				m = new Move(i,j);
				if (validMove(m, white) && !inCheckAfter(m, white)) {
					arr[it] = m;
					it++;
				}
			}
		}
		return arr;
	}
	

	
	boolean isBlocked(Move m, boolean white) {
		int col = Helper.getCol(m.origin);
		int col1 = Helper.getCol(m.dest);
		int row = Helper.getRow(m.origin);
		int row1 = Helper.getRow(m.dest);
		//moving horizontally
		if (row == row1) {
			//moving right
			if (col < col1) {
				for (int i = m.origin + 1; i <= m.dest; i++) {
					//encountered piece
					if (!isEmpty(i))  {
						//attacking friendly piece
						if (board[i].isWhite() == white) return true;
						//attacking enemy piece
						else return (!(i == m.dest));
					}
				}
			}
			//moving left
			else {
				for (int i = m.origin - 1; i >= m.dest; i--) {
					//encountered piece
					if (!isEmpty(i))  {
						//attacking friendly piece
						if (board[i].isWhite() == white) return true;
						//attacking enemy piece
						else return (!(i == m.dest));
					}
				}
			}
		}
		//moving vertically
		else if (col == col1) {
			//moving down
			if (row < row1) {
				for (int i = m.origin + 8; i <= m.dest; i+=8) {
					//encountered piece
					if (!isEmpty(i))  {
						//attacking friendly piece
						if (board[i].isWhite() == white) return true;
						//attacking enemy piece
						else return (!(i == m.dest));
					}
				}
			}
			//moving up
			else {
				for (int i = m.origin - 8; i >= m.dest; i-=8) {
					//encountered piece
					if (!isEmpty(i))  {
						//attacking friendly piece
						if (board[i].isWhite() == white) return true;
						//attacking enemy piece
						else return (!(i == m.dest));
					}
				}
			}
		}
		
		//up left - down right diagonal
		else if (m.origin % 9 == m.dest % 9) {
			//moving down right
			if (m.dest > m.origin) {
				for (int i = m.origin + 9; i <= m.dest; i+=9) {
					//encountered piece
					if (!isEmpty(i))  {
						//attacking friendly piece
						if (board[i].isWhite() == white) return true;
						//attacking enemy piece
						else return (!(i == m.dest));
					}
				}
			}
			//moving up left
			else {
				for (int i = m.origin - 9; i >= m.dest; i-=9) {
					//encountered piece
					if (!isEmpty(i))  {
						//attacking friendly piece
						if (board[i].isWhite() == white) return true;
						//attacking enemy piece
						else return (!(i == m.dest));
					}
				}
			}
		}
		
		//up right - down left diagonal
		else if (m.origin % 7 == m.dest % 7) {
			//moving down left
			if (m.origin < m.dest) {
				for (int i = m.origin + 7; i <= m.dest; i+=7) {
					//encountered piece
					if (!isEmpty(i))  {
						//attacking friendly piece
						if (board[i].isWhite() == white) return true;
						//attacking enemy piece
						else return (!(i == m.dest));
					}
				}
			}
			//moving up right
			else {
				for (int i = m.origin - 7; i >= m.dest; i-=7) {
					//encountered piece
					if (!isEmpty(i))  {
						//attacking friendly piece
						if (board[i].isWhite() == white) return true;
						//attacking enemy piece
						else return (!(i == m.dest));
					}
				}
			}
		}
		return false;
	}
	
	
	
	
}
