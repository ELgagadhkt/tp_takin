
import java.util.ArrayList;

public class Agent {

    public int currentTile;
    public int goal;

    public int id;
    public Grille currentGrid;

    public boolean isAtGoal () {
        return this.goal == this.currentTile;
    }

    public Grille move(Grille g) {
        Grille result = new Grille();
        result.size = g.size;
        result.agents = g.agents;
        int bestMove = this.currentTile;

        if (!isAtGoal()) {
            ArrayList<Integer> voisins = g.getNeighboors(this.currentTile);
            int bestDistance = dist(this.currentTile, this.goal, g.size);
            for (int v : voisins) {
                if (!g.isOccupied(v) && dist(v, this.goal, g.size) < bestDistance) {
                    bestDistance = dist(v, this.goal, g.size);
                    bestMove = v;
                }
            }
        } else {
            return result;
        }
        this.currentTile = bestMove;
        return result;
    }

    public int dist(int agent1, int agent2, int size) {

        int x1 = agent1 % size;
        int y1 = agent1 / size;
        int x2 = agent2 % size;
        int y2 = agent2 / size;

        int distance = Math.abs(x1 - x2) + Math.abs(y1 - y2);
        return distance;
    }


}