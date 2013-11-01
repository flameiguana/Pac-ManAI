package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class GhostInTheWay implements ICondition {

   
   double distance;
   
   public GhostInTheWay(double distance){
      this.distance = distance;   
   }
   
   @Override
   public boolean test(Game game) {
      
       for(GHOST ghost : GHOST.values()){
          if(game.isGhostEdible(ghost))
             continue;
          //MANHAT or PATH?
          double ghostDistance = game.getDistance(game.getPacmanCurrentNodeIndex(), game.getGhostCurrentNodeIndex(ghost), DM.PATH);
          if(ghostDistance <= distance && ghostDistance != -1.0){
             return true;
          }
       }
      return false;
   }

}
