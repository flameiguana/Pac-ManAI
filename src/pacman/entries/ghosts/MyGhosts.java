package pacman.entries.ghosts;

import java.util.EnumMap;

import pacman.controllers.Controller;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.GoAwayFromPacMan;
import edu.ucsc.gameAI.GoDownAction;
import edu.ucsc.gameAI.GoLeftAction;
import edu.ucsc.gameAI.GoRightAction;
import edu.ucsc.gameAI.GoTowardsPacMan;
import edu.ucsc.gameAI.GoUpAction;
import edu.ucsc.gameAI.conditions.GhostCloserToPacManThanPacManIsToPowerPill;
import edu.ucsc.gameAI.conditions.IsEdible;
import edu.ucsc.gameAI.conditions.IsValidMove;
import edu.ucsc.gameAI.conditions.PacmanBelowGhost;
import edu.ucsc.gameAI.conditions.PacmanCloserHorizontally;
import edu.ucsc.gameAI.conditions.PacmanToRightOfGhost;
import edu.ucsc.gameAI.decisionTrees.binary.BinaryDecision;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getActions() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.ghosts.mypackage).
 */
public class MyGhosts extends Controller<EnumMap<GHOST,MOVE>>
{
	private EnumMap<GHOST, MOVE> myMoves=new EnumMap<GHOST, MOVE>(GHOST.class);
	private BinaryDecision root[];
	
	public MyGhosts(){
		BinaryDecision pursueTree[] = makePursueTree();
		BinaryDecision evadeTree[] = makeEvadeTree();
		
		root = new BinaryDecision[GHOST.values().length];
		BinaryDecision pacmanCloseToPPill[] = new BinaryDecision[GHOST.values().length];
		
		int i = 0;
		for(GHOST ghost: GHOST.values()){
			//TODO factor in global reversal
			root[i] = new BinaryDecision();
			pacmanCloseToPPill[i] = new BinaryDecision();
			
			root[i].setCondition(new IsEdible(ghost));
			pacmanCloseToPPill[i].setCondition(new GhostCloserToPacManThanPacManIsToPowerPill(ghost, 20));
			
			root[i].setTrueBranch(new GoAwayFromPacMan(ghost, DM.EUCLID));
			root[i].setFalseBranch(pacmanCloseToPPill[i]);

			pacmanCloseToPPill[i].setTrueBranch(new GoAwayFromPacMan(ghost, DM.EUCLID));
			pacmanCloseToPPill[i].setFalseBranch(new GoTowardsPacMan(ghost, DM.EUCLID));
			
			//root[i].setTrueBranch(evadeTree[i]);
			//root[i].setFalseBranch(pursueTree[i]);
			i++;
		}
	}
	
	/**
	 * An organizational method. Returns the tree for pursuing pacman.
	 * @return
	 */
	public BinaryDecision[] makePursueTree(){
		BinaryDecision pacmanBelow[], pacmanToTheRightUp[], pacmanToTheRightDown[],	//for the Pursue Tree 
		leftPrefOverDown[], rightPrefOverDown[], leftPrefOverUp[], rightPrefOverUp[],
		leftAvailableDown[], leftAvailableUp[], rightAvailableDown[], rightAvailableUp[], 
		upAvailableLeft[], upAvailableRight[], downAvailableLeft[], downAvailableRight[];
		
		pacmanBelow = new BinaryDecision[GHOST.values().length];	//the root for now
		pacmanToTheRightUp = new BinaryDecision[GHOST.values().length];
		pacmanToTheRightDown = new BinaryDecision[GHOST.values().length];
		leftPrefOverDown = new BinaryDecision[GHOST.values().length];
		rightPrefOverDown = new BinaryDecision[GHOST.values().length];
		leftPrefOverUp = new BinaryDecision[GHOST.values().length];
		rightPrefOverUp = new BinaryDecision[GHOST.values().length];
		leftAvailableDown = new BinaryDecision[GHOST.values().length];
		leftAvailableUp = new BinaryDecision[GHOST.values().length];
		rightAvailableDown = new BinaryDecision[GHOST.values().length];
		rightAvailableUp = new BinaryDecision[GHOST.values().length];
		upAvailableLeft = new BinaryDecision[GHOST.values().length];
		upAvailableRight = new BinaryDecision[GHOST.values().length];
		downAvailableLeft = new BinaryDecision[GHOST.values().length];
		downAvailableRight = new BinaryDecision[GHOST.values().length];
		
		
		int i = 0;
		for(GHOST ghost: GHOST.values()){	//making all the ghost's ai the same for now
			pacmanBelow[i] = new BinaryDecision();
			pacmanToTheRightUp[i] = new BinaryDecision();
			pacmanToTheRightDown[i] = new BinaryDecision();
			leftPrefOverDown[i] = new BinaryDecision();
			rightPrefOverDown[i] = new BinaryDecision();
			leftPrefOverUp[i] = new BinaryDecision();
			rightPrefOverUp[i] = new BinaryDecision();
			leftAvailableDown[i] = new BinaryDecision();
			leftAvailableUp[i] = new BinaryDecision();
			rightAvailableDown[i] = new BinaryDecision();
			rightAvailableUp[i] = new BinaryDecision();
			upAvailableLeft[i] = new BinaryDecision();
			upAvailableRight[i] = new BinaryDecision();
			downAvailableLeft[i] = new BinaryDecision();
			downAvailableRight[i] = new BinaryDecision();
			
			
			//set the conditions for all the BinaryDecision nodes
			pacmanBelow[i].setCondition(new PacmanBelowGhost(ghost));
			pacmanToTheRightUp[i].setCondition(new PacmanToRightOfGhost(ghost));
			pacmanToTheRightDown[i].setCondition(new PacmanToRightOfGhost(ghost));
			leftPrefOverDown[i].setCondition(new PacmanCloserHorizontally(ghost));
			rightPrefOverDown[i].setCondition(new PacmanCloserHorizontally(ghost));
			leftPrefOverUp[i].setCondition(new PacmanCloserHorizontally(ghost));
			rightPrefOverUp[i].setCondition(new PacmanCloserHorizontally(ghost));
			leftAvailableDown[i].setCondition(new IsValidMove(ghost, MOVE.LEFT));
			leftAvailableUp[i].setCondition(new IsValidMove(ghost, MOVE.LEFT));
			rightAvailableDown[i].setCondition(new IsValidMove(ghost, MOVE.RIGHT));
			rightAvailableUp[i].setCondition(new IsValidMove(ghost, MOVE.RIGHT));
			upAvailableLeft[i].setCondition(new IsValidMove(ghost, MOVE.UP));
			upAvailableRight[i].setCondition(new IsValidMove(ghost, MOVE.UP));
			downAvailableLeft[i].setCondition(new IsValidMove(ghost, MOVE.DOWN));
			downAvailableRight[i].setCondition(new IsValidMove(ghost, MOVE.DOWN));
			
			//making the branches!
			pacmanBelow[i].setTrueBranch(pacmanToTheRightDown[i]);
			pacmanBelow[i].setFalseBranch(pacmanToTheRightUp[i]);
			
			pacmanToTheRightDown[i].setTrueBranch(leftPrefOverDown[i]);
			pacmanToTheRightDown[i].setFalseBranch(rightPrefOverDown[i]);
			
			pacmanToTheRightUp[i].setTrueBranch(leftPrefOverUp[i]);
			pacmanToTheRightUp[i].setFalseBranch(rightPrefOverUp[i]);
			
			leftPrefOverDown[i].setTrueBranch(leftAvailableDown[i]);
			leftPrefOverDown[i].setFalseBranch(downAvailableLeft[i]);

			rightPrefOverDown[i].setTrueBranch(rightAvailableDown[i]);
			rightPrefOverDown[i].setFalseBranch(downAvailableRight[i]);
			
			leftPrefOverUp[i].setTrueBranch(leftAvailableUp[i]);
			leftPrefOverUp[i].setFalseBranch(upAvailableLeft[i]);
			
			rightPrefOverUp[i].setTrueBranch(rightAvailableUp[i]);
			rightPrefOverUp[i].setFalseBranch(upAvailableRight[i]);
			
			leftAvailableDown[i].setTrueBranch(new GoLeftAction());
			leftAvailableDown[i].setFalseBranch(new GoDownAction());
			
			downAvailableLeft[i].setTrueBranch(new GoDownAction());
			downAvailableLeft[i].setFalseBranch(new GoLeftAction());
			
			rightAvailableDown[i].setTrueBranch(new GoRightAction());
			rightAvailableDown[i].setFalseBranch(new GoDownAction());
			
			downAvailableRight[i].setTrueBranch(new GoDownAction());
			downAvailableRight[i].setFalseBranch(new GoRightAction());
			
			leftAvailableUp[i].setTrueBranch(new GoLeftAction());
			leftAvailableUp[i].setFalseBranch(new GoUpAction());
			
			upAvailableLeft[i].setTrueBranch(new GoUpAction());
			upAvailableLeft[i].setFalseBranch(new GoLeftAction());
			
			rightAvailableUp[i].setTrueBranch(new GoRightAction());
			rightAvailableUp[i].setFalseBranch(new GoUpAction());
			
			upAvailableRight[i].setTrueBranch(new GoUpAction());
			upAvailableRight[i].setFalseBranch(new GoRightAction());

			
			// i need to draft this out
			i++;
		}
		return pacmanBelow;
	}
	
	public BinaryDecision[] makeEvadeTree(){
		BinaryDecision pacmanBelow[], pacmanToTheRightUp[], pacmanToTheRightDown[], 
		leftPrefOverDown[], rightPrefOverDown[], leftPrefOverUp[], rightPrefOverUp[];
		pacmanBelow = new BinaryDecision[GHOST.values().length];	//the root for now
		pacmanToTheRightUp = new BinaryDecision[GHOST.values().length];
		pacmanToTheRightDown = new BinaryDecision[GHOST.values().length];
		leftPrefOverDown = new BinaryDecision[GHOST.values().length];
		rightPrefOverDown = new BinaryDecision[GHOST.values().length];
		leftPrefOverUp = new BinaryDecision[GHOST.values().length];
		rightPrefOverUp = new BinaryDecision[GHOST.values().length];
		
		
		int i = 0;
		for(GHOST ghost: GHOST.values()){	//making all the ghost's ai the same for now
			pacmanBelow[i] = new BinaryDecision();
			pacmanToTheRightUp[i] = new BinaryDecision();
			pacmanToTheRightDown[i] = new BinaryDecision();
			leftPrefOverDown[i] = new BinaryDecision();
			rightPrefOverDown[i] = new BinaryDecision();
			leftPrefOverUp[i] = new BinaryDecision();
			rightPrefOverUp[i] = new BinaryDecision();
			
			
			//set the conditions for all the BinaryDecision nodes
			pacmanBelow[i].setCondition(new PacmanBelowGhost(ghost));
			pacmanToTheRightUp[i].setCondition(new PacmanToRightOfGhost(ghost));
			pacmanToTheRightDown[i].setCondition(new PacmanToRightOfGhost(ghost));
			leftPrefOverDown[i].setCondition(new PacmanCloserHorizontally(ghost));
			rightPrefOverDown[i].setCondition(new PacmanCloserHorizontally(ghost));
			leftPrefOverUp[i].setCondition(new PacmanCloserHorizontally(ghost));
			rightPrefOverUp[i].setCondition(new PacmanCloserHorizontally(ghost));
			
			//making the branches!
			pacmanBelow[i].setTrueBranch(pacmanToTheRightDown[i]);
			pacmanBelow[i].setFalseBranch(pacmanToTheRightUp[i]);
			
			pacmanToTheRightDown[i].setTrueBranch(leftPrefOverDown[i]);
			pacmanToTheRightDown[i].setFalseBranch(rightPrefOverDown[i]);
			
			pacmanToTheRightUp[i].setTrueBranch(leftPrefOverUp[i]);
			pacmanToTheRightUp[i].setFalseBranch(rightPrefOverUp[i]);
			
			leftPrefOverDown[i].setTrueBranch(new GoRightAction());
			leftPrefOverDown[i].setFalseBranch(new GoUpAction());

			rightPrefOverDown[i].setTrueBranch(new GoLeftAction());
			rightPrefOverDown[i].setFalseBranch(new GoUpAction());
			
			leftPrefOverUp[i].setTrueBranch(new GoRightAction());
			leftPrefOverUp[i].setFalseBranch(new GoDownAction());
			
			rightPrefOverUp[i].setTrueBranch(new GoLeftAction());
			rightPrefOverUp[i].setFalseBranch(new GoDownAction());

			
			// i need to draft this out
			i++;
		}
		return pacmanBelow;
	}
	
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue)
	{
		myMoves.clear();
		
		int i = 0;
		for(GHOST ghost : GHOST.values()){	//for each ghost
			myMoves.put(ghost, root[i].makeDecision(game).getMove());
			
			i++;
		}
		
		return myMoves;
	}
}