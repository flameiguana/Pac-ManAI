package edu.ucsc.gameAI;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class CollectPillsAction implements IAction {

   @Override
   public void doAction() {
      // TODO Auto-generated method stub
   }

   //if next to a pill go get it, otherwise look for a pill
   @Override
   public MOVE getMove(Game game) {
      int targetNode = -1;
      int currentPacman = game.getPacmanCurrentNodeIndex();
      
      List<Integer> activePillList = new LinkedList<Integer>();
      int[] activePills = game.getActivePillsIndices();
      for (int pill : activePills) {activePillList.add(pill);}
      Collections.sort(activePillList);
      
      //Here we check if a neighbor node contains a pill.
      int[] neighbors = game.getNeighbouringNodes(currentPacman);
      for(int neighbor : neighbors){
         if(Collections.binarySearch(activePillList, neighbor) >= 0){
           targetNode = neighbor;
           break;
         }
      }
      
      //Target node was found.
      if(targetNode != -1){
         return game.getMoveToMakeToReachDirectNeighbour(currentPacman, targetNode);
      }
      
      //Look for closest pill.
      
      else {
         targetNode = game.getClosestNodeIndexFromNodeIndex(currentPacman, activePills, DM.PATH);
         return game.getNextMoveTowardsTarget(currentPacman, targetNode, DM.PATH);
      }
   }

   @Override
   public MOVE getMove() {

      return null;
   }

}
