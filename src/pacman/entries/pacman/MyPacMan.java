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
 * fix priority conflict between power pill and evadeing
 */

/*
 * TODO:
 * Update power pill in range so that it doesnt return true until all ghosts are out of lair
 * fix evade ghosts.
 * update chase ghosts so that it doesnt try paths with live ghosts and takes time intro consdieration
 * DOnt take power pills unless ghosts are nearby
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
      chaseGhosts.setAction(new ChaseGhostsAction());

      State evadeGhosts = new State();
      //evadeGhosts.setEntryAction(new RunAwayAction());
      evadeGhosts.setAction(new RunAwayAction());
      
      //Conditions
      powerPillInRange = new PowerPillInRange(50);
      PowerPillWasEaten atePill = new PowerPillWasEaten();
      NearbyGhostEdible nearbyGhostEdible = new NearbyGhostEdible(50);
      
      //Transitions:
      Transition trans_goToPowerPill = new Transition();
      trans_goToPowerPill.setCondition(powerPillInRange);
      trans_goToPowerPill.setTargetState(goToPowerPill);
      
      Transition trans_PowerPillToNeutral = new Transition();
      trans_PowerPillToNeutral.setCondition(new ORConditions(atePill, new NegateCondition(powerPillInRange)));
      trans_PowerPillToNeutral.setTargetState(stateNeutral);
      
      Transition trans_chaseNearbyGhost = new Transition();
      //also one for when ghosts arent edible
      trans_chaseNearbyGhost.setCondition(nearbyGhostEdible);
      trans_chaseNearbyGhost.setTargetState(chaseGhosts);
      
      Transition trans_stopChasing = new Transition();
      trans_stopChasing.setCondition(new NegateCondition(new NearbyGhostEdible(45)));
      trans_stopChasing.setTargetState(stateNeutral);
      
      Transition trans_runAway = new Transition();
      trans_runAway.setCondition(new GhostInTheWay(26.0));
      trans_runAway.setTargetState(evadeGhosts);
      
      Transition trans_stopRunning = new Transition();
      trans_stopRunning.setCondition(new NegateCondition(new GhostInTheWay(30.0)));
      trans_stopRunning.setTargetState(stateNeutral);
      
      //--------
      
      LinkedList<ITransition> neutralTransList = new LinkedList<ITransition>();
      neutralTransList.add(trans_chaseNearbyGhost);
      neutralTransList.add(trans_runAway);
      neutralTransList.add(trans_goToPowerPill);
      stateNeutral.setTransitions(neutralTransList);
      
      
      LinkedList<ITransition> seekPillTransList = new LinkedList<ITransition>();
      seekPillTransList.add(trans_runAway);
      seekPillTransList.add(trans_PowerPillToNeutral);
      goToPowerPill.setTransitions(seekPillTransList);
      
      LinkedList<ITransition> chaseGhostTransList = new LinkedList<ITransition>();
      chaseGhostTransList.add(trans_runAway);
      chaseGhostTransList.add(trans_stopChasing);
      chaseGhosts.setTransitions(chaseGhostTransList);
      
      LinkedList<ITransition> evadeGhostTransList = new LinkedList<ITransition>();
      evadeGhostTransList.add(trans_goToPowerPill);
      evadeGhostTransList.add(trans_stopRunning);
      evadeGhosts.setTransitions(evadeGhostTransList);
      
      machine = new StateMachine();
      machine.setCurrentState(stateNeutral);
   }


   public MOVE getMove(Game game, long timeDue) {
      Collection<IAction> actions = machine.update(game);

      // Perform all required actions.
      for (IAction action : actions) {
         action.doAction();
         myMove = action.getMove(game);
      }
      return myMove;
   }
}