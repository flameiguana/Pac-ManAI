package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class Score implements ICondition {
	
	int _min, _max;
	
	public Score(int min, int max){
		_min = min;
		_max = max;
	}

	@Override
	public boolean test(Game game) {
		return(game.getScore() <= _max && game.getScore() >= _min);
	}

}
