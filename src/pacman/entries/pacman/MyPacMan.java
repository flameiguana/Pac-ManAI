package pacman.entries.pacman;

import java.util.Collection;
import java.util.LinkedList;

import pacman.controllers.Controller;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.conditions.*;
import edu.ucsc.gameAI.fsm.*;
import edu.ucsc.gameAI.*;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getAction() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.pacman.mypackage).
 */
public class MyPacMan extends Controller<MOVE> {

   StateMachine machine;
   IsJunction isCurrentNodeJunction;
   private MOVE myMove = MOVE.NEUTRAL;

   public MyPacMan() {
      super();

      
      machine = new StateMachine();
      //TODO Handle case when running into a wall, not just junctions.
      isCurrentNodeJunction = new IsJunction(0);

      State stateNeutral = new State();
      stateNeutral.setAction(new KeepDirectionAction());

      //Instead of having a state where you turn, have transition turn and just remain in state.
      State stateTurn = new State();

      Transition atInter = new Transition();
      atInter.setTargetState(stateTurn);
      atInter.setAction(new GoRandomAction());
      atInter.setCondition(isCurrentNodeJunction);

      LinkedList<ITransition> neutralTransList = new LinkedList<ITransition>();
      neutralTransList.add(atInter);
      stateNeutral.setTransitions(neutralTransList);
      
      Transition onPath = new Transition();
      onPath.setTargetState(stateNeutral);
      NegateCondition notJunctionNode = new NegateCondition(isCurrentNodeJunction);
      onPath.setCondition(notJunctionNode);
      
      LinkedList<ITransition> turnTransList = new LinkedList<ITransition>();
      turnTransList.add(onPath);
      stateTurn.setTransitions(turnTransList);

      machine.setCurrentState(stateNeutral);
      //Want to remain facing same direction until you hit intersection.
   }

   private void updateConditions(Game game) {
      //update index of conditional.
      isCurrentNodeJunction.setIndex(game.getPacmanCurrentNodeIndex());
   }

   public MOVE getMove(Game game, long timeDue) {
      updateConditions(game);
      // Place your game logic here to play the game as Ms Pac-Man
      Collection<IAction> actions = machine.update(game);

      // Perform all required actions.
      for (IAction action : actions) {
         if (action != null) {
            action.doAction();
            if (action.getMove() != null)
               myMove = action.getMove();
         }
      }
      return myMove;
   }
}