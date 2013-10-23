package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class LevelCount implements ICondition {

	int _level;
	
	public LevelCount(int level) {
		_level = level;
	}

	@Override
	public boolean test(Game game) {
		return game.getCurrentLevel() == _level;
	}

}
