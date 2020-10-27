
public class Rook extends Piece { 
    public Rook(boolean white) 
    { 
        super('R', white); 
    } 
  
    @Override
    public boolean canMove(Move m)
    { 
    	return (Helper.getRow(m.origin) == Helper.getRow(m.dest) ^ Helper.getCol(m.origin) == Helper.getCol(m.dest));
    } 
} 