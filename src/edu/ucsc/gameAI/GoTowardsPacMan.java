package edu.ucsc.gameAI;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class GoTowardsPacMan implements IAction, IBinaryNode {

	GHOST _ghost;
	DM _moveType;
	MOVE _nextMove;
	
	public GoTowardsPacMan(GHOST ghost, DM moveType){
		_ghost = ghost;
		_moveType = moveType;
	}
	
	public void doAction() {
	}
	
	@Override
	public IAction makeDecision(Game game) {
		int g = game.getGhostCurrentNodeIndex(_ghost);
		int p = game.getPacmanCurrentNodeIndex();
		
		_nextMove = game.getNextMoveTowardsTarget(g, p, game.getGhostLastMoveMade(_ghost), _moveType);
		//we want to avoid having 2 ghosts on the same path
		if(ghostOnPathToPacman(game, g, p, _nextMove)){
			MOVE temp = getAltMove(game, g, p);
			//if there's not already a ghost on the alt path
			if(!ghostOnPathToPacman(game, g, p, temp)){
				_nextMove = temp;
			}
		}
		
		return this;
	}
	
	public boolean ghostOnPathToPacman(Game game, int gIndex, int pIndex, MOVE nextMove){
		int pathNode = gIndex;
		MOVE nextMoveOnPath = _nextMove;
		while(pathNode != gIndex){
			pathNode = game.getNeighbour(pathNode, nextMoveOnPath);
			nextMoveOnPath = game.getNextMoveTowardsTarget(pathNode, pIndex, _moveType);
			
			//check each ghost
			for(GHOST ghost: GHOST.values()){
				if(ghost != _ghost && pathNode == game.getGhostCurrentNodeIndex(ghost)){
					return true;
				}
			}
		}
		return false;
	}
	
	public MOVE getAltMove(Game game, int gIndex, int pIndex){
		//if pacman is above ghost
		if(game.getNodeXCood(pIndex) > game.getNodeYCood(gIndex)){
			if(game.getNodeYCood(pIndex) > game.getNodeXCood(gIndex)){
				if(_nextMove == MOVE.RIGHT){
					return MOVE.UP;
				}
				return MOVE.RIGHT;
			}
			else{
				if(_nextMove == MOVE.LEFT){
					return MOVE.UP;
				}
				return MOVE.LEFT;
			}
		}
		else{
			if(game.getNodeYCood(pIndex) > game.getNodeXCood(gIndex)){
				if(_nextMove == MOVE.RIGHT){
					return MOVE.DOWN;
				}
				return MOVE.RIGHT;
			}
			else{
				if(_nextMove == MOVE.LEFT){
					return MOVE.DOWN;
				}
				return MOVE.LEFT;
			}
		}
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
