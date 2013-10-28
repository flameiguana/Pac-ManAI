package edu.ucsc.gameAI;

import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class KeepDirectionAction implements IAction {

   @Override
   public void doAction() {
      // TODO Auto-generated method stub

   }

   @Override
   public MOVE getMove() {
      return MOVE.NEUTRAL;
   }

   @Override
   public MOVE getMove(Game game) {
      return getMove();
   }
}
