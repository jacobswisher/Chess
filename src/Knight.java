
public class Knight extends Piece { 
    public Knight(boolean white) 
    { 
        super('N', white); 
    } 
  
    @Override
    public boolean canMove(Move m)
    { 
    	int x = Math.abs(Helper.getRow(m.origin) - Helper.getRow(m.dest)); 
        int y = Math.abs(Helper.getCol(m.origin) - Helper.getCol(m.dest)); 
        return x * y == 2; 
    } 
} 