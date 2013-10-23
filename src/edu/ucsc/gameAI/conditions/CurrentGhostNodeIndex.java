package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class CurrentGhostNodeIndex implements ICondition {
	
	GHOST _ghost;
	int _index;
	
	public CurrentGhostNodeIndex(GHOST ghost, int index){
		_ghost = ghost;
		_index = index;
	}

	@Override
	public boolean test(Game game) {
		return game.getGhostCurrentNodeIndex(_ghost) == _index;
	}
}
