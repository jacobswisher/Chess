
public class King extends Piece { 
    public King(boolean white) 
    { 
        super('K', white); 
    } 
  
    @Override
    public boolean canMove(Move m)
    { 
    	 
  
        if (m.origin - 1 == m.dest || m.origin + 1 == m.dest || m.origin + 9 == m.dest 
        		|| m.origin + 7 == m.dest || m.origin - 9 == m.dest 
        		|| m.origin - 7 == m.dest || m.origin + 8 == m.dest 
        		|| m.origin - 8 == m.dest) return true;
        
        if (this.isWhite()) {
        	if (m.origin == 60) {
            	if (m.dest == 58) return true;
            	else if (m.dest == 62) return true;
            }
        }
        else {
        	if (m.origin == 4) {
            	if (m.dest == 6) return true;
            	else if (m.dest == 2) return true;
            }
        }
        
        return false;
    } 
} 