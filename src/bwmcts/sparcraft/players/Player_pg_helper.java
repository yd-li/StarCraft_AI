/**
* This file is based on and translated from the open source project: Sparcraft
* https://code.google.com/p/sparcraft/
* author of the source: David Churchill
**/
package bwmcts.sparcraft.players;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bwmcts.sparcraft.Constants;
import bwmcts.sparcraft.GameState;
import bwmcts.sparcraft.Position;
import bwmcts.sparcraft.Unit;
import bwmcts.sparcraft.UnitAction;
import bwmcts.sparcraft.UnitActionTypes;

public class Player_pg_helper extends Player {
	//helper for PORTFOLIO GREEDY SEARCH
	
	private int _id=0;
	ArrayList<Player> ourScripts;
	
	public Player_pg_helper(int playerID,ArrayList<Player> ourScripts) {
		//BASICALLY THIS HELPER PLAYER HAS A COLLECTION OF SCRIPTS
		//AND EACH UNIT IN A PLAYOUT WILL BE FOLLOWING ITS OWN SCRIPT TO THE END.
		this.ourScripts = ourScripts;
		_id=playerID;
		setID(playerID);
	}

	public void getMoves(GameState state, HashMap<Integer,List<UnitAction>> moves, List<UnitAction>  moveVec)
	{
	    moveVec.clear();

	    makeMove(state,moves,moveVec);
	}
	
	public void makeMove(GameState state, HashMap<Integer,List<UnitAction>> moves,
			List<UnitAction> moveVec){
		for (Integer u : moves.keySet()){
			//this u is a unit index!!
			Player scriptToUse = ourScripts.get(u); //
			HashMap<Integer,List<UnitAction>> oneUnitMap = new HashMap<Integer,List<UnitAction>>();
			oneUnitMap.put(u, moves.get(u));
			scriptToUse.getMoves(state, oneUnitMap, moveVec);
		}
	}
	
	public String toString(){
		return "PGS helper";
	}
}
