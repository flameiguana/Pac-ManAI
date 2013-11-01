package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PacmanToRightOfGhost implements ICondition {
	GHOST _ghost;
	
	public PacmanToRightOfGhost(GHOST ghost){
		_ghost = ghost;
	}

	@Override
	public boolean test(Game game) {
		int gIndex = game.getGhostCurrentNodeIndex(_ghost);
		int gX = game.getNodeXCood(gIndex);
		
		int index = game.getPacmanCurrentNodeIndex();
		int x = game.getNodeXCood(index);
		
		//System.out.println("I don't know if this is left or right");
		
		if(x >= gX)
			return true;
		return false;
	}
}
