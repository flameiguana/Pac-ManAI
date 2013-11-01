package edu.ucsc.gameAI;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class GoAwayFromPacMan implements IAction, IBinaryNode {

	GHOST _ghost;
	DM _moveType;
	MOVE _nextMove;
	
	public GoAwayFromPacMan(GHOST ghost, DM moveType){
		_ghost = ghost;
		_moveType = moveType;
	}
	
	public void doAction() {
	}
	
	@Override
	public IAction makeDecision(Game game) {
		int g = game.getGhostCurrentNodeIndex(_ghost);
		int p = game.getPacmanCurrentNodeIndex();
		
		_nextMove = game.getNextMoveAwayFromTarget(g, p, game.getGhostLastMoveMade(_ghost), _moveType);
		return this;
	}

	@Override
	public MOVE getMove() {
		return _nextMove;
	}

	@Override
	public MOVE getMove(Game game) {
		// TODO Auto-generated method stub
		return null;
	}
}
