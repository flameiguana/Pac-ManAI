package edu.ucsc.gameAI;

import java.util.Random;

import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class GoRandomAction implements IAction {

   Random random;
   
   public GoRandomAction(){
      random = new Random();
   }
   
   @Override
   public void doAction() {
      // TODO Auto-generated method stub
   }

   @Override
   public MOVE getMove() {
      return null;
   }

   @Override
   public MOVE getMove(Game game) {
      MOVE[] myMoves = game.getPossibleMoves(game.getPacmanCurrentNodeIndex());
      int index = random.nextInt(myMoves.length);
      //System.out.println(myMoves[index].name());
      return myMoves[index];
   }

}
