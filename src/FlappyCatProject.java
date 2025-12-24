import javax.swing.*;

// ==========================================
// 3. PATRON COMPOSITE : Les Objets du Jeu
// ==========================================
// Rôle : Traiter les objets simples (Livre) et complexes (Paire de livres) de la même façon.

// ==========================================
// 4. PATRON OBSERVATEUR : Liaison Modèle-Vue
// ==========================================
// Rôle : La Vue se met à jour automatiquement quand le Modèle change.

// ==========================================
// MAIN CLASS (Point d'entrée)
// ==========================================
public class FlappyCatProject {
    public static void main(String[] args) {
        // Création de la fenêtre
        JFrame frame = new JFrame("Flappy Cat - Mini Projet L3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Assemblage MVC
        FlappyModel model = new FlappyModel();
        FlappyView view = new FlappyView(model);
        new FlappyController(model, view); // Le contrôleur démarre le timer

        frame.add(view);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}