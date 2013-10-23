package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class NegateCondition implements ICondition {

   ICondition condition;
   
   public NegateCondition(ICondition condition){
      this.condition = condition;
   }
   
   @Override
   public boolean test(Game game) {
      //System.out.println(condition.test(game));
      return !condition.test(game);  
   }
}
