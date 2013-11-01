package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PowerPillInRange implements ICondition {
	int distance;
	final static int RADIUS = 40;

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
	
	private int getGhostsInArea(Game game, int node){
		int ghostsInArea = 0; 
		for(GHOST ghost : GHOST.values()){
			if(game.getDistance(node, game.getGhostCurrentNodeIndex(ghost), DM.EUCLID) <= RADIUS)
			ghostsInArea++;
		}
		return ghostsInArea;
	}
	
	@Override
	public boolean test(Game game) {
	   
		//Don't consider pills unless ghosts are out of lair.
	   for(GHOST ghost : GHOST.values()){
	      if(game.getGhostLairTime(ghost) > 15)
	         return false;
	   }
	   
		int[] activePills = game.getActivePowerPillsIndices();

		//The pill must be a certain distance from pacman, the path to the pill cannot contain ghosts, and there
		//must be at least 2 ghosts in the perrimeter.
		for (int pill : activePills) {
			if(game.getDistance(game.getPacmanCurrentNodeIndex(),
					pill, DM.PATH) < distance && !nonEdibleGhostInPath(game, pill)){
				if(getGhostsInArea(game, pill) >= 2)
					return true;
			}
		}
		return false;
	}

}
