import MVC.controller.Controller;
import MVC.model.Model;
import MVC.view.View;

import javax.swing.*;

void main() {
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

