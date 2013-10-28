package edu.ucsc.gameAI;

import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class SeekPowerPillAction implements IAction {

   int progress = 0;
   int pillIndex;
   int pillNode;
   @Override
   public void doAction() {
      // TODO Auto-generated method stub

   }
   
   private void selectPill(Game game){
      //This returns the NODE indices of the power pills. Very confusing function name.
      int[] activePills = game.getActivePowerPillsIndices();
      
      int min = Integer.MAX_VALUE;
      for(int pill : activePills){
         int distanceToPill = (int) game.getDistance(game.getPacmanCurrentNodeIndex(), pill, DM.MANHATTAN);
         if(distanceToPill < min);
         pillNode = pill;  
      }
      pillIndex = game.getPowerPillIndex(pillNode);
      
      System.out.println("Pill is" + pillIndex);
      progress = 0;
   }

   @Override
   public MOVE getMove(Game game) {
      if(progress == 0 || !game.isPowerPillStillAvailable(pillIndex)){
         //select the pill
         selectPill(game);
      }
      progress++;
      return game.getNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(), pillNode, DM.PATH);
      
      /*
       * option 1: find nearest pill
       * 
       */
      //Find moves.
      //getMoveToMakeToReachDirectNeighbour
      //getNextMoveTowardsTarget
      //getApproximateNextMoveTowardsTarget
      //getShortestPath
      //return null;
   }

   @Override
   public MOVE getMove() {
      // TODO Auto-generated method stub
      return null;
   }
 

}
