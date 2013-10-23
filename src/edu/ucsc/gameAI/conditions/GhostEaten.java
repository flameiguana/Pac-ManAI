package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class GhostEaten implements ICondition {
	
	GHOST _ghost;
	
	public GhostEaten(GHOST ghost){
		_ghost = ghost;
	}

	@Override
	public boolean test(Game game) {
		return game.wasGhostEaten(_ghost);
	}
}
