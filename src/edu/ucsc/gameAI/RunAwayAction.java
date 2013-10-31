package edu.ucsc.gameAI;


import java.util.TreeMap;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class RunAwayAction implements IAction {

	MOVE currentMove;
	int targetJunction;
	Game _game;
   @Override
   public void doAction() {
      // TODO Auto-generated method stub
      
   }
   
   public RunAwayAction(){
      targetJunction = -1;
   }

   public boolean arrayContains(final int[] array, final int key) {
      for (final int i : array) {
          if (i == key) {
              return true;
          }
      }
      return false;
  }
   
   //getFarthestNodeIndexFromNodeIndex
   
   private void getNewPath(){
      //turn around
      int pacmanNode = _game.getPacmanCurrentNodeIndex();
      int[] junctions = _game.getJunctionIndices();
      TreeMap<Integer, Integer> paths = new TreeMap<Integer, Integer>();
      
      //find a path to a junction with no ghost on it. if there are none, stall (move back and forth)
    
      for(int i = 0; i < junctions.length; i++){
         boolean safe = true;
         int[] path = _game.getShortestPath(pacmanNode, junctions[i]);
         for(GHOST ghost : GHOST.values()){
            
            if(_game.isGhostEdible(ghost))
               continue;
            //store junction index and distance, considering only those without ghosts on the path towards them
            //also want to avoid ones with surrounding ghosts
            int ghostIndex= _game.getGhostCurrentNodeIndex(ghost);
            if((arrayContains(path, ghostIndex) || (_game.getDistance(junctions[i], ghostIndex, DM.EUCLID) <= 40.0))){
               safe = false;
               break;
            }
         }
         if(safe)
            paths.put((int)_game.getDistance(pacmanNode, junctions[i], DM.PATH), junctions[i]);
      }
      
      if(paths.size() > 0){
         System.out.println("There are " + paths.size() +  " possible paths");
         //get farthest junction?
         targetJunction = paths.get(paths.lastKey());
      }
      else targetJunction = -1;
   }
   
   private boolean junctionNoGood(int target){
      if (_game.getPacmanCurrentNodeIndex() == target)
         return true;
      int[] path  = _game.getShortestPath(_game.getPacmanCurrentNodeIndex(), target);
      for(GHOST ghost : GHOST.values()){
         if(arrayContains(path, _game.getGhostCurrentNodeIndex(ghost))
               || (_game.getDistance(target, _game.getGhostCurrentNodeIndex(ghost), DM.EUCLID) <= 40.0))
            return true;
      }
      return false;
   }
   
   @Override
   public MOVE getMove(Game game) {
      _game = game;
      
     if (targetJunction == -1 || junctionNoGood(targetJunction))
         getNewPath();
      
      if(targetJunction != -1){
         System.out.println("heading towards junction" + " ( " + targetJunction + " )");
         return _game.getNextMoveTowardsTarget(game.getPacmanCurrentNodeIndex(), targetJunction, DM.PATH);
      }
      else
      {
         //Find nearest ghost and turn from it.
         double min = Double.MAX_VALUE;
         int ghostIndex = 0;
         
         for(GHOST ghost : GHOST.values()){
            double distanceToGhost = game.getDistance(game.getGhostCurrentNodeIndex(ghost), game.getGhostCurrentNodeIndex(ghost), DM.MANHATTAN);
            if(distanceToGhost < min){
               ghostIndex = game.getGhostCurrentNodeIndex(ghost);
               min = distanceToGhost;
            }
         }
         System.out.println("moving away from target");
         return game.getNextMoveAwayFromTarget(game.getPacmanCurrentNodeIndex(), ghostIndex, DM.MANHATTAN);
      }
   }

   @Override
   public MOVE getMove() {
      //TODO Auto-generated method stub
      return null;
   }

}
