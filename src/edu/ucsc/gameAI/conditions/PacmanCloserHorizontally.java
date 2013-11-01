package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PacmanCloserHorizontally implements ICondition {
	GHOST _ghost;
	
	public PacmanCloserHorizontally(GHOST ghost){
		_ghost = ghost;
	}

	@Override
	public boolean test(Game game) {
		int gIndex = game.getGhostCurrentNodeIndex(_ghost);
		int gX = game.getNodeXCood(gIndex);
		int gY = game.getNodeYCood(gIndex);
		
		int index = game.getPacmanCurrentNodeIndex();
		int pX = game.getNodeXCood(index);
		int pY = game.getNodeYCood(index);
		
		//we want to close the greater distance
		if(Math.abs(gX-pX) > Math.abs(gY-pY))
			return true;
		return false;
	}
}
