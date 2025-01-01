package model.keyboard;

import model.Key;
import model.Keyboard;

import java.util.*;

public class KeyboardOptimizer {

    private final Keyboard keyboardOriginal;

    private KeyboardEvaluator keyboardEvaluator ;
    private Random random;
    private List<Keyboard> pool;
    public KeyboardOptimizer( Keyboard keyboard ) {
        this.keyboardOriginal = keyboard;
        this.keyboardEvaluator = new KeyboardEvaluator( this.keyboardOriginal ) ;
        this.random = new Random() ;
        this.pool = initialisePool() ;
    }

    /**
     * Optimise la distribution des touches en fonction des fréquences d'utilisation.
     * @param frequencies Les fréquences d'utilisation des touches.
     * @return Un clavier optimisé.
     */
    public Keyboard optimize ( Map<String[], Integer> frequencies ) {
        Keyboard res =  this.keyboardOriginal ;
        double bestScore = this.keyboardEvaluator.evaluateKeyboard(frequencies) ;
        for ( Keyboard keyboard : this.pool ) {
            this.keyboardEvaluator.setKeyboard(keyboard);
            double keyboardScore = this.keyboardEvaluator.evaluateKeyboard(frequencies) ;
            if ( bestScore > keyboardScore ) {
                res = keyboard ;
            }
        }
        return res ;
    }


    /**
     * Une fonction qui switch juste une touche normale
     * @param k1 touche 1
     * @param k2 touche 2
     */
    public void switchKey ( Keyboard keyboard , Key k1 , Key k2 ) {
        int rowk1 = k1.getRangee(), colk1 = k1.getColumn();
        int rowk2 = k2.getRangee(), colk2 = k2.getColumn();

        for (ArrayList<Key> list : keyboard.getKeys()) {
            for (int i = 0; i < list.size(); i++) {
                Key key = list.get(i);

                if (key.getRangee() == rowk1 && key.getColumn() == colk1) {
                    Key newKey = new Key(key.getTouchName(), rowk2, colk2, k2.getFinger(), key.isRightHand(), key.isShifted(), key.isAltGr());
                    list.set(i, newKey);
                }
                else if (key.getRangee() == rowk2 && key.getColumn() == colk2) {
                    Key newKey = new Key(key.getTouchName(), rowk1, colk1, k1.getFinger(), key.isRightHand(), key.isShifted(), key.isAltGr());
                    list.set(i, newKey);
                }
            }
        }
    }

    public ArrayList<Keyboard> initialisePool () {
        ArrayList<Keyboard> res = new ArrayList<>() ;
        Keyboard kb1 = randomSwitchkey() ;
        Keyboard kb2 = randomSwitchkey() ;
        Keyboard kb3 = cross( kb1 , kb2 ) ;
        Keyboard kb4 = cross( kb2 , kb1 ) ;
        res.add( kb1 ) ;
        res.add( kb2 ) ;
        res.add( kb3 ) ;
        res.add( kb4 ) ;

        return res ;
    }

    /**
     * Une fonction qui est un clavier random
     * @return clavier random
     */
    private Keyboard randomSwitchkey () {
        Keyboard res = keyboardOriginal.clone() ;
        int length = res.getKeys().get(0).size() ;
        int nbChangement = random.nextInt( length ) ;
        for ( int i = 0 ; i < nbChangement ; i++ ) {
            Key k1 = res.getKeys().get(0).get( random.nextInt(length) ) ;
            Key k2 = res.getKeys().get(0).get( random.nextInt(length) ) ;
            switchKey( res , k1 , k2 );
        }
        return res ;
    }

    /**
     * Une fonction qui fait la combinaison de deux claviers
     * @param kb1 premier clavier
     * @param kb2 deuxième clavier
     * @return le clavier combiné
     */
    private Keyboard cross(Keyboard kb1, Keyboard kb2) {
        return kb1 ;
    }
}
