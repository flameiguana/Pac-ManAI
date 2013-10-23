package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PowerPillWasEaten implements ICondition {
	
	int _oldPillIndicesCount = -1;
	
	public PowerPillWasEaten(){
	}
	/**
	 * Returns whether a pill was eaten since
	 * the last time this was tested.
	 */
	@Override
	public boolean test(Game game) {
		//lassie returns goes home
		boolean lassie = false;
		int curActivePillIndicesCount = game.getActivePowerPillsIndices().length;
		
		//if we're the old length is shorter than the current length, then we're down a pill or two
		if(_oldPillIndicesCount - curActivePillIndicesCount > 0)
			lassie = true;
		
		_oldPillIndicesCount = curActivePillIndicesCount;
		return lassie;
		
	}
}
