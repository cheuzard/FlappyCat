package MVC.controller;

import MVC.model.Model;
import MVC.view.View;
import config.GameConfig;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// ==========================================
// CONTRÔLEUR (MVC)
// ==========================================
public class Controller implements KeyListener {
    private final Model model;

    public Controller(Model model, View view) {
        this.model = model;

        // Gestion des touches
        view.addKeyListener(this);

        // Boucle de jeu (60 FPS environ)
        Timer timer = new Timer((int) ((1 / (double) GameConfig.getInstance().FPS) * 1000), _ -> model.updateGame());
        timer.start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            model.jump();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
