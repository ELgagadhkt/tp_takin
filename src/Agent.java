import java.awt.*;
import java.util.ArrayList;

public class Agent {


    private final int value;
    public int id;
    public int currentTile;
    public int goal;
    public Grille currentGrid;

    public Agent(int value) {
        this.value = value;
    }

    public ArrayList<Integer> convert () {
        ArrayList<Integer> res = new ArrayList();
        res.add(this.currentTile % this.currentGrid.size);
        res.add(this.currentTile / this.currentGrid.size);
        return res;
    }

    public boolean isAtGoal () {
        return this.goal == this.currentTile;
    }



    public void draw(Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);

        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String text = Integer.toString(value);
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        int textX = x + (width - textWidth) / 2;
        int textY = y + (height + textHeight) / 2;
        g.drawString(text, textX, textY);
    }
}