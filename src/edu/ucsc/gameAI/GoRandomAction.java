package edu.ucsc.gameAI;

import java.util.Random;

import pacman.game.Constants.MOVE;

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
      //excludes last element
      int index = random.nextInt(MOVE.values().length);
      //get a random index into the enum of moves. Move may be invalid.
      // TODO check if move is valid
      return MOVE.values()[index];
   }

}
