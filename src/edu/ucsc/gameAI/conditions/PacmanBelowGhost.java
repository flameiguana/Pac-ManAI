package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PacmanBelowGhost implements ICondition {
	GHOST _ghost;
	
	public PacmanBelowGhost(GHOST ghost){
		_ghost = ghost;
	}

	@Override
	public boolean test(Game game) {
		int gIndex = game.getGhostCurrentNodeIndex(_ghost);
		int gY = game.getNodeYCood(gIndex);
		
		int index = game.getPacmanCurrentNodeIndex();
		int y = game.getNodeYCood(index);
		
		//System.out.println("I don't know if this is above or below");
		
		if(y >= gY)
			return true;
		return false;
	}
}
