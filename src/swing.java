import javax.swing.*;
import java.awt.*;

public class swing extends JFrame {

    private static final int SIZE = 4; // Taille du jeu de taquin (4x4)
    private static final int CELL_SIZE = 80; // Taille d'une case en pixels
    private static final int BORDER_SIZE = 5; // Largeur de la bordure autour des cases
    private static final Color[] COLORS = {
            Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
            Color.ORANGE, Color.CYAN, Color.MAGENTA, Color.PINK,
            Color.GRAY, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.BLACK
    }; // Tableau de couleurs pour les pièces

    private JPanel boardPanel; // Panneau pour afficher le jeu de taquin

    public swing() {
        setTitle("Jeu de Taquin");
        setSize(SIZE * CELL_SIZE, SIZE * CELL_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBoard(g);
            }
        };

        add(boardPanel);

        setVisible(true);
    }

    private void drawBoard(Graphics g) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;

                // Dessiner la case
                int pieceIndex = row * SIZE + col;
                Color pieceColor = COLORS[pieceIndex % COLORS.length];
                g.setColor(pieceColor);
                g.fillRect(x, y, CELL_SIZE, CELL_SIZE);;

                // Dessiner la pièce

                g.setColor(COLORS[pieceIndex % COLORS.length]);
                g.fillOval(x + BORDER_SIZE, y + BORDER_SIZE, CELL_SIZE - 2 * BORDER_SIZE, CELL_SIZE - 2 * BORDER_SIZE);
            }
        }
    }

}
