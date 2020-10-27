
public class Queen extends Piece { 
    public Queen(boolean white) 
    { 
        super('Q', white); 
    } 
  
    @Override
    public boolean canMove(Move m)
    { 
    	 return (Helper.getRow(m.origin) == Helper.getRow(m.dest) ^ Helper.getCol(m.origin) == Helper.getCol(m.dest) //rook movement
    			 || Math.abs(Helper.getRow(m.origin) - Helper.getRow(m.dest)) == Math.abs(Helper.getCol(m.origin) - Helper.getCol(m.dest))); //or bishop movement
    } 
} 