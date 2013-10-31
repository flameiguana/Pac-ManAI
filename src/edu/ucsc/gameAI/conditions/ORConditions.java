package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class ORConditions implements ICondition {

   ICondition[] conditions;
   public ORConditions(ICondition... conditions){
      this.conditions = conditions;
   }
   
   @Override
   public boolean test(Game game) {
      for(ICondition condition : conditions){
         if(condition.test(game))
            return true;
      }
      return false;
   }

}
