package maze.heuristics;

import maze.core.MazeExplorer;
import search.core.BestFirstHeuristic;


public class ManhattanDistance implements BestFirstHeuristic<MazeExplorer> {
    public int getDistance(MazeExplorer node, MazeExplorer goal) {
    	int nodeX = node.getLocation().X();
    	int nodeY = node.getLocation().Y();
    	int goalX = goal.getLocation().X();
    	int goalY = goal.getLocation().Y();
    	int d = distance(nodeX, goalX, nodeY, goalY);
        return d;
    }
    
    private static int distance(int x1, int x2, int y1, int y2) {
    	int sideA = Math.max(x1, x2) - Math.min(x1, x2);
    	int sideB = Math.max(y1, y2) - Math.min(y1, y2);
        return sideA + sideB;
    }
}