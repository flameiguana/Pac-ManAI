package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class IsValidMove implements ICondition {

   GHOST _ghost;
   MOVE _move;
   
   public IsValidMove(GHOST ghost, MOVE move){
      _ghost = ghost;
      _move = move;
   }
   
   @SuppressWarnings("incomplete-switch")
   @Override
   public boolean test(Game game) {
	   //game.getNe
	  //checking to see if the ghost is trying to do the
	  //the opposite of it's last move
	 /* switch(game.getGhostLastMoveMade(_ghost)){
	  case LEFT:
		  if(_move == MOVE.RIGHT){
			  System.out.println("Can't flip");
			  return false;
		  }
		  break;
	  case RIGHT:
		  if(_move == MOVE.LEFT){
			  System.out.println("Can't flip");
			  return false;
		  }
		  break;
	  case UP:
		  if(_move == MOVE.DOWN){
			  System.out.println("Can't flip");
			  return false;
		  }
		  break;
	  case DOWN:
		  if(_move == MOVE.UP){
			  System.out.println("Can't flip");
			  return false;
		  }
		  break;
	  }*/
	  
	  /*int gIndex = game.getGhostCurrentNodeIndex(_ghost);
	  int neighborNode = game.getNeighbour(gIndex, _move);
	  
	  //if that node is unreachable
	  if(game.getMoveToMakeToReachDirectNeighbour(gIndex, neighborNode) == null)
		  return false;
	  //if there is no node in that direction
	  if(neighborNode == -1)
		  return false;*/
	  
      return true; 
   }
}
