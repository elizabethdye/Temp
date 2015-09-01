package maze.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import search.core.BestFirstObject;

public class MazeExplorer implements BestFirstObject<MazeExplorer> {
	private Maze m;
	private MazeCell location;
	private TreeSet<MazeCell> treasureFound; 
	
	public MazeExplorer(Maze m, MazeCell location) {
		this.m = m;
		this.location = location;
		treasureFound = new TreeSet<MazeCell>();
	}
	
	public MazeCell getLocation() {return location;}

	@Override
	public ArrayList<MazeExplorer> getSuccessors() {
		ArrayList<MazeExplorer> result = new ArrayList<MazeExplorer>();
		ArrayList<MazeCell> neighbors = m.getNeighbors(location);
		for (MazeCell n: neighbors) {
			if ( n.isNeighbor(location) && !m.blocked(location, n) ) {
				MazeExplorer e = new MazeExplorer(m, n);
				e.addTreasures(treasureFound);
				if (n.)
//				e.treasureFound = this.treasureFound; check n for treasure, if yes, add to e.treasurefound
				result.add(e);
			}
		}
		// TODO: It should add as a successor every adjacent, unblocked neighbor square.
        return result;
	}
	
	public void addTreasures(Collection<MazeCell> treasures) {
		treasureFound.addAll(treasures);
	}
	
	public SortedSet<MazeCell> getTreasureFound() {
		return Collections.unmodifiableSortedSet(treasureFound);
	}
	
	public String toString() {
		StringBuilder treasures = new StringBuilder();
		for (MazeCell t: treasureFound) {
			treasures.append(";");
			treasures.append(t.toString());
		}
		return "@" + location.toString() + treasures.toString();
	}
	
	@Override
	public int hashCode() {return toString().hashCode();}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof MazeExplorer) {
			return achieves((MazeExplorer)other);
		} else {
			return false;
		}
	}

	@Override
	public boolean achieves(MazeExplorer goal) {
		return this.location.equals(goal.location) && this.treasureFound.equals(goal.treasureFound);
	}

}
