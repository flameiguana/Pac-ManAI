package edu.ucsc.gameAI;

import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class RunAwayAction implements IAction {

	boolean changedCourse;
	MOVE currentMove;
	
	
	public RunAwayAction(){
		changedCourse = false;
	}
   @Override
   public void doAction() {
      // TODO Auto-generated method stub

   }

   @Override
   public MOVE getMove(Game game) {
	   //turn around
	   if (!changedCourse){
		   currentMove = game.getPacmanLastMoveMade().opposite();
		   changedCourse = true;
	   }
	   return currentMove;
      /*
       * Strategy is to head for a junction that is for from ghosts. If one doesnt exist then go to a further node
       * 
       */
      //getFarthestNodeIndexFromNodeIndex
   }

   @Override
   public MOVE getMove() {
      // TODO Auto-generated method stub
      return null;
   }

}
