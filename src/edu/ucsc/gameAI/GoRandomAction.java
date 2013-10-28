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
      int index = random.nextInt(MOVE.values().length);
      return MOVE.values()[index];
   }

   @Override
   public MOVE getMove(Game game) {
      return getMove();
   }

}
