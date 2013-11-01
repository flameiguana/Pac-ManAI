package edu.ucsc.gameAI;

import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class SeekPowerPillAction implements IAction {

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
   }

   @Override
   public MOVE getMove(Game game) {
      selectPill(game);
      //System.out.println("heading to pill");
      return game.getNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(), pillNode, DM.PATH);
      
   }

   @Override
   public MOVE getMove() {
      // TODO Auto-generated method stub
      return null;
   }
 

}
