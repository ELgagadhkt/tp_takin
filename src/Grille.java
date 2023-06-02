import java.util.ArrayList;

public class Grille {

    public int size;

    public ArrayList<Agent> agents;

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

    public boolean isOccupied (int caseId) {
        for (Agent a : this.agents) {
            if (a.currentTile == caseId) {
                return true;
            }
        }
        return false;
    }

}
