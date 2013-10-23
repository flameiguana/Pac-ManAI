package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class GhostLastMove implements ICondition {
	
	GHOST _ghost;
	MOVE _move;
	
	public GhostLastMove(GHOST ghost, MOVE move){
		_ghost = ghost;
		_move = move;
	}

	@Override
	public boolean test(Game game) {
		return game.getGhostLastMoveMade(_ghost) == _move; 
	}
}
