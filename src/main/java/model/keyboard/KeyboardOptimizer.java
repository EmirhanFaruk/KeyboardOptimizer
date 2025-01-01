package model.keyboard;

import model.Key;
import model.Keyboard;

import java.util.*;

public class KeyboardOptimizer {

    private Keyboard keyboard;

    public KeyboardOptimizer(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    /**
     * Optimise la distribution des touches en fonction des fréquences d'utilisation.
     * @param frequencies Les fréquences d'utilisation des touches.
     * @return Un clavier optimisé.
     */
    public Keyboard optimize(Map<String[], Integer> frequencies) {

        return keyboard;
    }


    /**
     * Une fonction qui switch juste une touche normale
     * @param k1 touche 1
     * @param k2 touche 2
     */
    public void switchKey(Key k1, Key k2) {
        ArrayList<ArrayList<Key>> res = new ArrayList<>();
        int rowk1 = k1.getRangee(), colk1 = k1.getColumn();
        int rowk2 = k2.getRangee(), colk2 = k2.getColumn();

        for (ArrayList<Key> list : keyboard.getKeys()) {
            ArrayList<Key> temp = new ArrayList<>();
            for (Key key : list) {
                if (key.getRangee() == rowk1 && key.getColumn() == colk1) {
                    temp.add(new Key(key.getTouchName(), k2.getRangee(), k2.getColumn(), k2.getFinger(), k1.isRightHand(), k1.isShifted(), k1.isAltGr()));
                } else if (key.getRangee() == rowk2 && key.getColumn() == colk2) {
                    temp.add(new Key(key.getTouchName(), k1.getRangee(), k1.getColumn(), k1.getFinger(), k2.isRightHand(), k2.isShifted(), k2.isAltGr()));
                } else {
                    // Pas de changement pour les autres touches
                    temp.add(key);
                }
            }
            res.add(temp);
        }
        keyboard = new Keyboard(res);
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }
}
