package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class CurrentLevelTime implements ICondition {

	int _min, _max;
	
	public CurrentLevelTime(int min, int max) {		
		_min = min;
		_max = max;
	}

	@Override
	public boolean test(Game game) {
		return (game.getCurrentLevelTime() >= _min && game.getCurrentLevelTime() <= _max);
	}

}
