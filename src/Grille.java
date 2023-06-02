import java.awt.*;
import java.util.ArrayList;

public class Grille {

    public int size;
    private final int[][] taquin;
    public ArrayList<Agent> agents;

    public Grille(int size, int nbvalue) {
        this.size = size;
        taquin = new int[size][size];

        // Initialisation du taquin
        int count = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(count<=nbvalue){
                    taquin[i][j] = count++;
                }else{
                    taquin[i][j] = 0;
                }

            }
        }
    }

    public void draw(Graphics g, int width, int height) {
        int cellWidth = width / size;
        int cellHeight = height / size;

        // Dessiner les cases
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int value = taquin[i][j];
                if (value != 0) {
                    Agent c = new Agent(value);
                    c.draw(g, j * cellWidth, i * cellHeight, cellWidth, cellHeight);
                }
            }
        }
    }


    public ArrayList<Integer> getNeighboors(int caseId) {

        ArrayList<Integer> voisins = new ArrayList<>();
        if (caseId - this.size >= 0) {
            voisins.add(caseId - 3);
        }
        if (caseId + 3 < (this.size * this.size)) {
            voisins.add(caseId + 3);
        }
        if ((caseId + 1) % this.size != 0) {
            voisins.add(caseId + 1);
        }
        if ((caseId - 1) % this.size != this.size-1) {
            voisins.add(caseId - 1);
        }
        return voisins;
    }

    public boolean isCompleted() {
        for (Agent a : this.agents) {
            if (!a.isAtGoal()) {
                return false;
            }
        }
        return true;
    }

    public void makeMove(Agent a) {

        if (!a.isAtGoal()) {
            ArrayList<Integer> voisins = getNeighboors(a.currentTile);
            int bestDistance = dist(a.currentTile, a.goal, this.size);
            int bestMove = a.currentTile;
            for (int v : voisins) {
                if (!isOccupied(v) && dist(v, a.goal, this.size) < bestDistance) {
                    bestDistance = dist(v, a.goal, this.size);
                    bestMove = v;
                }
            }
            a.currentTile = bestMove;
        }
    }

    public void moveEveryone() {
        for (Agent a : this.agents) {
            makeMove(a);
        }
    }

    public int dist(int agent1, int agent2, int size) {

        int x1 = agent1 % size;
        int y1 = agent1 / size;
        int x2 = agent2 % size;
        int y2 = agent2 / size;

        int distance = Math.abs(x1 - x2) + Math.abs(y1 - y2);
        return distance;
    }

    public boolean isOccupied (int caseId) {
        for (Agent a : this.agents) {
            if (a.currentTile == caseId) {
                return true;
            }
        }
        return false;
    }



}