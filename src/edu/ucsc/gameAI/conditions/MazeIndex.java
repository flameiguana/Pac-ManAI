package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class MazeIndex implements ICondition {
	
	int _index;
	
	public MazeIndex(int index){
		_index = index;
	}
	
	@Override
	public boolean test(Game game) {
		return game.getMazeIndex() == _index;
	}

}
