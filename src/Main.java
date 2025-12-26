import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Création de la fenêtre
        JFrame frame = new JFrame("Flappy Cat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Assemblage MVC
        Model model = new Model();
        View view = new View(model);
        new Controller(model, view); // Le contrôleur démarre le timer

        frame.add(view);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}