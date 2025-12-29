package observer;

import java.util.ArrayList;
import java.util.List;

public abstract class GameSubject {
    protected List<GameObserver> observers = new ArrayList<>();

    public void attach(GameObserver o) {
        observers.add(o);
    }

    public void notifyObservers() {
        for (GameObserver o : observers) o.updateView();
    }
}
