package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class TimeOfLastGlobalReversal implements ICondition {
	
	int _min, _max;
	
	public TimeOfLastGlobalReversal(int min, int max){
		_min = min;
		_max = max;
	}

	@Override
	public boolean test(Game game) {
		return(game.getTimeOfLastGlobalReversal() <= _max && game.getTimeOfLastGlobalReversal() >= _min);
	}

}
