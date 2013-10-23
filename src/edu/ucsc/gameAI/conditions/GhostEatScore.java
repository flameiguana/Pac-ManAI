package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class GhostEatScore implements ICondition {

	int _min, _max;
	
	public GhostEatScore(int min, int max) {
		_min = min;
		_max = max;
	}

	@Override
	public boolean test(Game game) {
		return (game.getGhostCurrentEdibleScore() >= _min && game.getGhostCurrentEdibleScore() <= _max);
	}

}
