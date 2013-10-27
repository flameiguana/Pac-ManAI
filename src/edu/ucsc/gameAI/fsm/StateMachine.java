package edu.ucsc.gameAI.fsm;

import java.util.ArrayList;
import java.util.Collection;

import pacman.game.Game;

import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.fsm.IState;

public class StateMachine implements IStateMachine {

   IState currentState;
   
   @Override
   public Collection<IAction> update(Game game) {
      ArrayList<IAction> actions = new ArrayList<IAction>();
      ITransition triggeredTrans = null;
      
      //Check if any possible transition is triggered.
      for(ITransition transition : currentState.getTransitions()){
         if(transition.isTriggered(game))
            triggeredTrans = transition;
            break;
      }
      
      //Get actions associated with making a transition. Update currentState.
      if (triggeredTrans != null){
         IState targetState = triggeredTrans.getTargetState();
         if(currentState.getExitAction() != null)
            actions.add(currentState.getExitAction());
         if(triggeredTrans.getAction() != null)
            actions.add(triggeredTrans.getAction());
         if(targetState.getEntryAction() != null)
            actions.add(targetState.getEntryAction());
         currentState = targetState;
      }
      
      else
         actions.add(currentState.getAction());
      
      return actions;
   }

   @Override
   public IState getCurrentState() {
      return currentState;
   }

   @Override
   public void setCurrentState(IState state) {
      currentState = state;
   }
}
