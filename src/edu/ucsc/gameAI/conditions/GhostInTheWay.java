package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class GhostInTheWay implements ICondition {

   @Override
   public boolean test(Game game) {
       for(GHOST ghost : GHOST.values()){
          if(game.getDistance(game.getPacmanCurrentNodeIndex(), game.getGhostCurrentNodeIndex(ghost), DM.PATH) < 5)
             return true;
       }
      return false;
   }

}
