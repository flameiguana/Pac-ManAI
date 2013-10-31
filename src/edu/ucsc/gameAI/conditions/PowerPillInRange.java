package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PowerPillInRange implements ICondition {
	int distance;

	public PowerPillInRange(int distance) {
		this.distance = distance;
	}

   private boolean arrayContains(final int[] array, final int key) {
      for (final int i : array) {
          if (i == key) {
              return true;
          }
      }
      return false;
  }
   
	private boolean nonEdibleGhostInPath(Game game, int pill){
	   for(GHOST ghost : GHOST.values()){
	      if(arrayContains(game.getShortestPath(game.getPacmanCurrentNodeIndex(), pill), game.getGhostCurrentNodeIndex(ghost)))
	            return true;
	   }
	   return false;
	}
	//TODO: also want to check that there are no ghosts in the way
	@Override
	public boolean test(Game game) {
	   
	   for(GHOST ghost : GHOST.values()){
	      if(game.getGhostLairTime(ghost) > 15)
	         return false;
	   }
	   
		int[] activePills = game.getActivePowerPillsIndices();

		for (int pill : activePills) {
			if(game.getDistance(game.getPacmanCurrentNodeIndex(),
					pill, DM.PATH) < distance && !nonEdibleGhostInPath(game, pill))
				return true;
		}
		return false;
	}

}
