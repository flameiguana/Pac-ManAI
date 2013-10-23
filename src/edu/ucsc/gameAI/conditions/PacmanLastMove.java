package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PacmanLastMove implements ICondition {
	
	MOVE _move;
	
	public PacmanLastMove(MOVE move){
		_move = move;
	}

	@Override
	public boolean test(Game game) {
		return game.getPacmanLastMoveMade() == _move;
	}
}
