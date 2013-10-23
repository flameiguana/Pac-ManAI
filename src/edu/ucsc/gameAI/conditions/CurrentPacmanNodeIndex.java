package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class CurrentPacmanNodeIndex implements ICondition {
	
	int _index;
	
	public CurrentPacmanNodeIndex(int index){
		_index = index;
	}

	@Override
	public boolean test(Game game) {
		return game.getPacmanCurrentNodeIndex() == _index;
	}
}
