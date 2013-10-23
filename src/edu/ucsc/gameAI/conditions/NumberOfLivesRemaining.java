package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class NumberOfLivesRemaining implements ICondition {
	
	int _min, _max;
	
	public NumberOfLivesRemaining(int min, int max){
		_min = min;
		_max = max;
	}

	@Override
	public boolean test(Game game) {
		return game.getPacmanNumberOfLivesRemaining() >= _min && game.getPacmanNumberOfLivesRemaining() <= _max;
	}
}
