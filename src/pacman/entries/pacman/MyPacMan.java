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
   State goToPowerPill;
   PowerPillInRange powerPillInRange;
   
   private MOVE myMove = MOVE.NEUTRAL;

   public MyPacMan() {
      super();
      //States
      goToPowerPill = new State();
      goToPowerPill.setAction(new SeekPowerPillAction());
   
      /*
       * Want a state that will go around collecting pills in a sweeping fashion.
       */
      State stateNeutral = new State();
      stateNeutral.setAction(new CollectPillsAction());
      
      State chaseGhosts = new State();

      State evadeGhosts;
      
      //Conditions
      powerPillInRange = new PowerPillInRange(20);
      PowerPillWasEaten atePill = new PowerPillWasEaten();
      
      
      //Transitions:
      Transition trans_goToPowerPill = new Transition();
      trans_goToPowerPill.setCondition(powerPillInRange);
      trans_goToPowerPill.setTargetState(goToPowerPill);
      
      Transition trans_PowerPillToNeutral = new Transition();
      trans_PowerPillToNeutral.setCondition(atePill);
      trans_PowerPillToNeutral.setTargetState(stateNeutral);
      
      Transition trans_chaseNearbyGhost = new Transition();
      //condition
      trans_chaseNearbyGhost.setTargetState(chaseGhosts);
      
      
      //--------
      LinkedList<ITransition> neutralTransList = new LinkedList<ITransition>();
      neutralTransList.add(trans_goToPowerPill);
      stateNeutral.setTransitions(neutralTransList);
      
      LinkedList<ITransition> seekPillTransList = new LinkedList<ITransition>();
      seekPillTransList.add(trans_PowerPillToNeutral);
      goToPowerPill.setTransitions(seekPillTransList);
      
      //one transition to collect pills, another to chase ghosts.
 

      //in action check if at turning point or stay in same direction;
      /*
       * See if there are power pills remanining
       * Choose closest one
       * Check if there are ghosts in region
       * Move to point while avoiding ghosts.
       */

      

      /*
       * If you have power pill, and there are ghosts in region, chase
       * Otherwise collect pills
       */
      
      
      //machine.setCurrentState(stateNeutral);
      machine = new StateMachine();
      machine.setCurrentState(stateNeutral);
   }

   private void updateConditions(Game game) {
      
      int pacmanNode = game.getPacmanCurrentNodeIndex();
     
      int pacmanX = game.getNodeXCood(pacmanNode);
      int pacmanY = game.getNodeYCood(pacmanNode);
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