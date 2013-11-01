package edu.ucsc.gameAI;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class CollectPillsAction implements IAction {

   @Override
   public void doAction() {
      // TODO Auto-generated method stub
   }


   @Override
   public MOVE getMove(Game game) {
     
      //eat some pills at spawn before going crazy
      if(game.getNumberOfActivePills()  >= game.getPillIndices().length - 4)
         return MOVE.RIGHT;
      
      int targetNode = -1;
      int currentPacman = game.getPacmanCurrentNodeIndex();
      
      int[] activePills = game.getActivePillsIndices();
      
      List<Integer> junctionPillsList = new LinkedList<Integer>();
      for(int i = 0; i < activePills.length; i++){
         if(game.isJunction(activePills[i]))
            junctionPillsList.add(activePills[i]);
      }

      if(junctionPillsList.size() >= game.getJunctionIndices().length/2){
         int[] junctionPillsArray = new int[junctionPillsList.size()];
         for (int i = 0; i < junctionPillsList.size(); i++) {
            junctionPillsArray[i] = junctionPillsList.get(i);
         }
         targetNode = game.getClosestNodeIndexFromNodeIndex(currentPacman, junctionPillsArray, DM.PATH);
      }
      else
         targetNode = game.getClosestNodeIndexFromNodeIndex(currentPacman, activePills, DM.PATH);
      
      return game.getNextMoveTowardsTarget(currentPacman, targetNode, DM.PATH);
   }

   @Override
   public MOVE getMove() {

      return null;
   }

}
