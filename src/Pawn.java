
public class Pawn extends Piece { 
    public Pawn(boolean white) 
    { 
        super('P', white); 
    } 
  
    @Override
    public boolean canMove(Move m)
    { 
    	if (this.isWhite()) {
    		if (m.origin - 8 == m.dest || m.origin - 16 == m.dest || m.origin - 9 == m.dest || m.origin - 7 == m.dest) return true;
    	}
    	else {
    		if (m.origin + 8 == m.dest || m.origin + 16 == m.dest || m.origin + 9 == m.dest || m.origin + 7 == m.dest) return true;
    	}
    	return false;
    } 
} 