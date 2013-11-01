package pacman.entries.ghosts;

import java.util.EnumMap;

import pacman.controllers.Controller;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.GoAwayFromPacMan;
import edu.ucsc.gameAI.GoTowardsPacMan;
import edu.ucsc.gameAI.conditions.GhostCloserToPacManThanPacManIsToPowerPill;
import edu.ucsc.gameAI.conditions.IsEdible;
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
			
			i++;
		}
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