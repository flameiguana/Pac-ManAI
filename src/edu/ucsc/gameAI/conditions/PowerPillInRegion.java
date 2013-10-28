package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PowerPillInRegion implements ICondition {
	
	int _x1, _y1, _x2, _y2;
	
	public PowerPillInRegion(int x1, int y1, int x2, int y2){
		_x1 = x1;
		_y1 = y1;
		_x2 = x2;
		_y2 = y2;
	}
	
	public void update(int x1, int y1, int x2, int y2){
	     _x1 = x1;
	     _y1 = y1;
	     _x2 = x2;
	     _y2 = y2;
	}

	@Override
	public boolean test(Game game) {
		
		for(int p: game.getPowerPillIndices()){
			int x = game.getNodeXCood(p);
			int y = game.getNodeYCood(p);
			if(x >= _x1 && x <= _x2 && y >= _y1 && y <= _y2)
				return true;
		}
		return false;
	}
}
