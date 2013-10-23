package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class TotalTime implements ICondition {

	int _min, _max;
	
	public TotalTime(int min, int max) {
		_min = min;
		_max = max;
	}

	@Override
	public boolean test(Game game) {
		return (game.getTotalTime() >= _min && game.getTotalTime() <= _max);
	}

}
