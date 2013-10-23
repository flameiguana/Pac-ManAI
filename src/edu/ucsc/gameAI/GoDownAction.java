package edu.ucsc.gameAI;

import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class GoDownAction implements IAction, IBinaryNode {

   @Override
   public IAction makeDecision(Game game) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void doAction() {
      // TODO Auto-generated method stub

   }

   @Override
   public MOVE getMove() {
      return MOVE.DOWN;
   }

}
