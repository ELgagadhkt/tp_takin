import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class JeuDeTaquin extends JFrame {

    private Grille grille;

    public JeuDeTaquin(int size,int nbvalue) {
        super("Jeu de Taquin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));

        // Initialisation de la grille
        grille = new Grille(size, nbvalue);

        // Création d'un panneau personnalisé pour afficher la grille
        JPanel gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                grille.draw(g, getWidth(), getHeight());
            }
        };

        getContentPane().add(gamePanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Mélanger la grille au démarrage du jeu
        melangerGrille();

        // Gestionnaire d'événements pour les déplacements
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        gamePanel.addKeyListener(new DeplacementListener());
    }

    private void melangerGrille() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int direction = random.nextInt(4);
            grille.deplacerCaseVide(String.valueOf(direction));
        }
    }

    private class DeplacementListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            String direction = "";
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    direction = "haut";
                    break;
                case KeyEvent.VK_DOWN:
                    direction = "bas";
                    break;
                case KeyEvent.VK_LEFT:
                    direction = "gauche";
                    break;
                case KeyEvent.VK_RIGHT:
                    direction = "droite";
                    break;
            }
            grille.deplacerCaseVide(direction);
            repaint();
        }
    }
}