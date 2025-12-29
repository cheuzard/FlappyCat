package strategy;

import entity.GameObject;

// ==========================================
// 2. PATRON STRATEGIE : Comportement de Mouvement
// ==========================================
// Rôle : Encapsuler les algorithmes de mouvement (Voler vs Défiler).
public interface MovementStrategy {
    void move(GameObject obj);
}
