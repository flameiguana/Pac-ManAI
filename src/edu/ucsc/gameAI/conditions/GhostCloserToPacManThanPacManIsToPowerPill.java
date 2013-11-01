package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class GhostCloserToPacManThanPacManIsToPowerPill implements ICondition {
	
	GHOST _ghost;
	double _minDist;
	
	public GhostCloserToPacManThanPacManIsToPowerPill(GHOST ghost, double minDist){
		_ghost = ghost;
		_minDist = minDist;
	}

	@Override
	public boolean test(Game game) {
		int gIndex = game.getGhostCurrentNodeIndex(_ghost);
		int pIndex = game.getPacmanCurrentNodeIndex();
		MOVE gMove = game.getGhostLastMoveMade(_ghost);
		for(int i: game.getActivePowerPillsIndices()){
			//dist from pacman to power pill
			double pacDist = game.getDistance(pIndex, i, DM.PATH);
			
			//dist from ghost to pacman
			double gDist = game.getDistance(gIndex, pIndex, gMove, DM.PATH);
			if(pacDist < gDist && pacDist < _minDist){
				return true;
			}
		}
		return false;
	}
}
