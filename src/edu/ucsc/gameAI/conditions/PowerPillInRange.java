package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.DM;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PowerPillInRange implements ICondition {
	int distance;

	public PowerPillInRange(int distance) {
		this.distance = distance;
	}

	@Override
	public boolean test(Game game) {
		int[] activePills = game.getActivePowerPillsIndices();

		for (int pill : activePills) {
			if(game.getDistance(game.getPacmanCurrentNodeIndex(),
					pill, DM.MANHATTAN) < distance)
				return true;
		}
		return false;
	}

}
