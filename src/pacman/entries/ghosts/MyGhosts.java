package pacman.entries.ghosts;

import java.util.EnumMap;

import pacman.controllers.Controller;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.conditions.IsEdible;
import edu.ucsc.gameAI.decisionTrees.binary.*;
import edu.ucsc.gameAI.*;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getActions() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.ghosts.mypackage).
 */
public class MyGhosts extends Controller<EnumMap<GHOST,MOVE>>
{
	private EnumMap<GHOST, MOVE> myMoves=new EnumMap<GHOST, MOVE>(GHOST.class);
	
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue)
	{
		myMoves.clear();
		
		for(GHOST ghost : GHOST.values())	//for each ghost
		{
			myMoves.put(ghost, MOVE.RIGHT);
		}
		return myMoves;
	}
}