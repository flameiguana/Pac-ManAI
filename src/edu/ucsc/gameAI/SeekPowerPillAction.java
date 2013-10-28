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
      
      double min = Double.MAX_VALUE;
      for(int pill : activePills){
         double distanceToPill = game.getDistance(game.getPacmanCurrentNodeIndex(), pill, DM.MANHATTAN);
         if(distanceToPill < min){
         	pillNode = pill; 
         	min = distanceToPill;
         	//System.out.println("Pill is: " + game.getPowerPillIndex(pillNode) + " , distance is " + min);
         }
      }
      pillIndex = game.getPowerPillIndex(pillNode);
      
      //System.out.println("Target pill is: " + pillIndex);
      progress = 0;
   }

   @Override
   public MOVE getMove(Game game) {
      if(progress == 0 || !game.isPowerPillStillAvailable(pillIndex)){
         //select the pill
         selectPill(game);
      }
      progress++;
      //don't know how efficient this is yet.
      return game.getNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(), pillNode, DM.PATH);
      
      //Find moves.
      //Useful functions:
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
