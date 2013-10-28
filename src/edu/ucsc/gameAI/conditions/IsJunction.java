package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class IsJunction implements ICondition {

   int nodeIndex;
   
   public IsJunction(int index){
      nodeIndex = index;
   }
   
   public void update(int index){
      nodeIndex = index;
   }
   
   @Override
   public boolean test(Game game) {
      return game.isJunction(nodeIndex);
   }
}
