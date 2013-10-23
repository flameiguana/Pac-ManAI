package edu.ucsc.gameAI.fsm;

import java.util.ArrayList;
import java.util.Collection;

import edu.ucsc.gameAI.IAction;

public class State implements IState {

   IAction action, entryAction, exitAction;
   Collection<ITransition> transitions;
   
   public State(){
      transitions = new ArrayList<ITransition>();
   }
   @Override
   public IAction getAction() {
      return action;
   }

   @Override
   public void setAction(IAction action) {
      this.action = action;
   }
   
   @Override
   public IAction getEntryAction() {
      return entryAction;
   }

   @Override
   public IAction getExitAction() {
      return exitAction;
   }

   @Override
   public Collection<ITransition> getTransitions() {
      return transitions;
   }

   @Override
   public void setEntryAction(IAction action) {
     entryAction = action;
      
   }

   @Override
   public void setExitAction(IAction action) {
      exitAction = action;
      
   }

   @Override
   public void setTransitions(Collection<ITransition> trans) {
      transitions = trans;
   }

}
