package edu.ucsc.gameAI;

import java.util.ArrayList;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class ChaseGhostsAction implements IAction {

   boolean choseTarget;
   int targetGhost;
   
   @Override
   public void doAction() {
      // TODO Auto-generated method stub
   }

   private void chooseTarget(Game game){
      //Pick closest ghost that is edible
      ArrayList<GHOST> edibleGhosts = new ArrayList<GHOST>();
   
      for (GHOST ghost : GHOST.values()){
         if(game.isGhostEdible(ghost))
            edibleGhosts.add(ghost);
      }
      
      int[] ghostLocations = new int[edibleGhosts.size()];
      for(int i = 0; i < edibleGhosts.size(); i++){
         ghostLocations[i] = game.getGhostCurrentNodeIndex(edibleGhosts.get(i));
      }
      
     targetGhost = game.getClosestNodeIndexFromNodeIndex(game.getPacmanCurrentNodeIndex(), ghostLocations, DM.PATH);
     for(int i = 0; i < ghostLocations.length; i++){
        if(ghostLocations[i] == targetGhost)
           System.out.println("target ghost is " + edibleGhosts.get(i).name());
     }
   }
   
   @Override
   public MOVE getMove(Game game) {
      chooseTarget(game);
      return game.getNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(), targetGhost, DM.PATH);
   }

   @Override
   public MOVE getMove() {
      //TODO Auto-generated method stub
      return null;
   }

}
