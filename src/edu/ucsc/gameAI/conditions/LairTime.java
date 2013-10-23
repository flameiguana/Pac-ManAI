package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class LairTime implements ICondition {
	
	GHOST _ghost;
	int _min, _max;
	
	public LairTime(GHOST ghost, int min, int max){
		_ghost = ghost;
		_min = min;
		_max = max;
	}

	@Override
	public boolean test(Game game) {
		return game.getGhostLairTime(_ghost) >= _min && game.getGhostLairTime(_ghost) <= _max; 
	}
}
