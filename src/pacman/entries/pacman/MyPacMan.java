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
   PowerPillInRegion powerPillInRegion;
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

      //in action check if at turning point or stay in same direction;
      /*
       * See if there are power pills remanining
       * Choose closest one
       * Check if there are ghosts in region
       * Move to point while avoiding ghosts.
       */
      powerPillInRegion = new PowerPillInRegion(0, 0, 0, 0);
      
      State goToPowerPill = new State();
      goToPowerPill.setAction(new SeekPowerPillAction());
      Transition trans_goToPowerPill = new Transition();
      trans_goToPowerPill.setCondition(powerPillInRegion);
      trans_goToPowerPill.setTargetState(goToPowerPill);
      /*
       * If you have power pill, and there are ghosts in region, chase
       * Otherwise collect pills
       */
      State chaseGhosts;
      
      //machine.setCurrentState(stateNeutral);
      machine.setCurrentState(goToPowerPill);
      //Want to remain facing same direction until you hit intersection.
   }

   private void updateConditions(Game game) {
      
      int pacmanNode = game.getPacmanCurrentNodeIndex();
      isCurrentNodeJunction.update(pacmanNode);
     
      int pacmanX = game.getNodeXCood(pacmanNode);
      int pacmanY = game.getNodeYCood(pacmanNode);
      
      powerPillInRegion.update(pacmanX-5, pacmanY - 5, pacmanX + 5, pacmanX + 5);
   }

   public MOVE getMove(Game game, long timeDue) {
      updateConditions(game);
      Collection<IAction> actions = machine.update(game);

      // Perform all required actions.
      for (IAction action : actions) {
         action.doAction();
         myMove = action.getMove(game);
      }
      return myMove;
   }
}