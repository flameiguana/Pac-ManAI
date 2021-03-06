package edu.ucsc.gameAI.conditions;


import pacman.game.Game;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import edu.ucsc.gameAI.ICondition;

public class NearbyGhostEdible implements ICondition {

   int distance, timeEdible;
   public NearbyGhostEdible(int distance){
      this.distance = distance;
   }
   
   @Override
   public boolean test(Game game) {
      for (GHOST ghost : GHOST.values()){
         if(game.isGhostEdible(ghost)){
            if(game.getDistance(game.getPacmanCurrentNodeIndex(),
               game.getGhostCurrentNodeIndex(ghost), DM.PATH) <= distance)
               return true;
         }
      }
      return false;
   }
}
