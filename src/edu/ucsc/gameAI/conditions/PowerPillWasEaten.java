package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PowerPillWasEaten implements ICondition {
	
	
	public PowerPillWasEaten(){
	}
	/**
	 * Returns whether a pill was eaten since
	 * the last time this was tested.
	 */
	@Override
	public boolean test(Game game) {
	   return game.wasPowerPillEaten();
		
	}
}
