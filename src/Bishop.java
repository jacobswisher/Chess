
public class Bishop extends Piece { 
    public Bishop(boolean white) 
    { 
        super('B', white); 
    } 
  
    @Override
    public boolean canMove(Move m)
    { 
    	 
  
    	return (Math.abs(Helper.getRow(m.origin) - Helper.getRow(m.dest)) 
    			== Math.abs(Helper.getCol(m.origin) - Helper.getCol(m.dest)));
    } 
} 