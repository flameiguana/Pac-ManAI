package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class IsPowerPillStillAvailable implements ICondition {
	
   int _pill;
	public IsPowerPillStillAvailable(int pill){
	   _pill = pill;
	}
	/**
	 * Returns whether a pill was eaten since
	 * the last time this was tested.
	 */
	@Override
	public boolean test(Game game) {
		return game.isPowerPillStillAvailable(_pill);
	}
}
