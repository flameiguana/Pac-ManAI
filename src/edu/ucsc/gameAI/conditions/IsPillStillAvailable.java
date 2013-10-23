package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class IsPillStillAvailable implements ICondition {
	
	public IsPillStillAvailable(int pill){
	   
	}
	/**
	 * Returns whether a pill was eaten since
	 * the last time this was tested.
	 */
	@Override
	public boolean test(Game game) {
		return game.getPillIndices().length > 0;
	}
}
