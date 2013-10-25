package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class GhostInRegion implements ICondition {
	
	int _x1, _y1, _x2, _y2;
	
	public GhostInRegion(int x1, int y1, int x2, int y2){
		_x1 = x1;
		_y1 = y1;
		_x2 = x2;
		_y2 = y2;
	}

	@Override
	public boolean test(Game game) {
	   
	   for(GHOST ghost : GHOST.values()){
	      int index = game.getGhostCurrentNodeIndex(ghost);
	      int x = game.getNodeXCood(index);
	      int y = game.getNodeYCood(index);
	      if(x >= _x1 && x <= _x2 && y >= _y1 && y <= _y2)
	         return true;
	   }
		return false;
	}
}
