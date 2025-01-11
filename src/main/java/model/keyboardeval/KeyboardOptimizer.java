package model.keyboardeval;

import model.keyboard.Key;
import model.keyboard.Keyboard;

import java.util.*;

public class KeyboardOptimizer {


    private enum ImmutableKey {
        SHIFT("shift"),
        MAJ("maj"),
        SPACE("space"),
        ALT("alt"),
        CTRL("ctrl"),
        ALT_GR("alt gr"),
        TAB("tab");

        private final String touchName;

        ImmutableKey(String touchName) {
            this.touchName = touchName;
        }

        public String getTouchName() {
            return touchName;
        }
    }


    private final Keyboard keyboardOriginal;

    private final KeyboardEvaluator keyboardEvaluator ;
    private final Random random;
    private final List<Keyboard> pool;
    public KeyboardOptimizer( Keyboard keyboard ) {
        this.keyboardOriginal = keyboard;
        this.keyboardEvaluator = new KeyboardEvaluator( this.keyboardOriginal ) ;
        this.random = new Random() ;
        this.pool = initialisePool() ;
    }

    /**
     * Classe interne pour encapsuler un clavier et son score.
     */
    private record KeyboardScore(Keyboard keyboard, double score) { }

    /**
     * Optimise la distribution des touches en fonction des fréquences et du type d'évaluation.
     * @param frequencies Les fréquences d'utilisation (caractères ou groupes de caractères).
     * @return Un clavier optimisé.
     */
    public Keyboard optimize( Map<String[], Integer> frequencies ) {
        Keyboard res = this.keyboardOriginal ;
        double bestScore = this.keyboardEvaluator.evaluateKeyboard(frequencies);
        List<Thread> threads = new ArrayList<>();
        List<KeyboardScore> results = Collections.synchronizedList(new ArrayList<>());

        for (Keyboard keyboard : this.pool) {
            Thread thread = new Thread(() -> {
                KeyboardEvaluator evaluator = new KeyboardEvaluator(keyboard);
                double score =  evaluator.evaluateKeyboard(frequencies);
                results.add(new KeyboardScore(keyboard, score));
            });
            threads.add(thread);
            thread.start();
        }

        //On attends la fin de tous les threads
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Problème de thread");
            }
        }

        //On trouve le clavier avec le meilleur score
        synchronized (results) {
            for (KeyboardScore keyboardScore : results) {
                if (keyboardScore.score > bestScore ) {
                    bestScore = keyboardScore.score;
                    res = keyboardScore.keyboard;
                }
            }
        }
        return res ;
    }


    /**
     * Checks if the keyname is in immutable keys
     * @param key key
     * @return true if in the list
     */
    private boolean isForbidden(Key key)
    {
        for (ImmutableKey immutableKey : ImmutableKey.values())
        {
            if (immutableKey.getTouchName().equals(key.getTouchName()))
            {
                return true;
            }
        }

        return false;
    }


    /**
     * Returns sk name in dk coordinates and fingers etc with a new key.
     * @param sk source key
     * @param dk destination key
     * @return new key
     */
    private Key getCounterKey(Key sk, Key dk)
    {
        return new Key(sk.getTouchName(), dk.getLine(), dk.getColumn(), dk.getFinger(), dk.isRightHand(), dk.isShifted(), dk.isAltGr());
    }


    /**
     * Une fonction qui switch juste une touche normale
     * @param k1 touche 1
     * @param k2 touche 2
     */
    public void switchKey (Keyboard keyboard , Key k1 , Key k2 ) {
        if (k1 == null || k2 == null)
        {
            return;
        }
        if (isForbidden(k1) || isForbidden(k2))
        {
            return;
        }

        int rowk1 = k1.getRangee(), colk1 = k1.getColumn();
        int rowk2 = k2.getRangee(), colk2 = k2.getColumn();

        for (ArrayList<Key> list : keyboard.getKeys()) {
            for (int i = 0; i < list.size(); i++) {
                Key key = list.get(i);

                if (key.getRangee() == rowk1 && key.getColumn() == colk1) {
                    Key newKey = new Key(key.getTouchName(), rowk2, colk2, k2.getFinger(), k2.isRightHand(), key.isShifted(), key.isAltGr());
                    list.set(i, newKey);
                }
                else if (key.getRangee() == rowk2 && key.getColumn() == colk2) {
                    Key newKey = new Key(key.getTouchName(), rowk1, colk1, k1.getFinger(), k1.isRightHand(), key.isShifted(), key.isAltGr());
                    list.set(i, newKey);
                }
            }
        }
    }

    /**
     * Une fonction d'initialisation du pool qui fournit un nb aléatoire de clavier
     * @return une liste de keyboard
     */
    public ArrayList<Keyboard> initialisePool () {
        ArrayList<Keyboard> res = new ArrayList<>() ;

        int nbKeyboards = 3 + random.nextInt( 8 ) ;
        for ( int i = 0 ; i < nbKeyboards ; i++ ) {
            res.add( randomSwitchkey() ) ;
        }

        int numCross = random.nextInt( nbKeyboards ) ;
        for ( int i = 0 ; i < numCross ; i++ ) {
            Keyboard A = res.get( random.nextInt( res.size() ) ) ;
            Keyboard B = res.get( random.nextInt( res.size() ) ) ;

            res.add( cross( A , B ) ) ;
        }
        return res ;
    }

    /**
     * Une fonction qui renvoie un clavier qui change aléatoirement les touches
     * @return un clavier randomise
     */
    private Keyboard randomSwitchkey () {
        Keyboard res = keyboardOriginal.clone() ;
        int length = res.getKeys().get(0).size() ;
        int nbChangement = random.nextInt( length ) ;
        for ( int i = 0 ; i < nbChangement ; i++ ) {
            Key k1 = res.getKeys().get(0).get( random.nextInt(length) ) ;
            Key k2 = res.getKeys().get(0).get( random.nextInt(length) ) ;

            if (k1 == null || k2 == null || (isForbidden(k1) || isForbidden(k2)))
            {
                i--;
                continue;
            }

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
        int length = kb1.getKeys().get(0).size() ;
        ArrayList<ArrayList<Key>> res = new ArrayList<> ( Collections.nCopies( kb1.getKeys().size() , null ) ) ;
        for ( int i = 0 ; i < res.size() ; i++ ) res.set(i , new ArrayList<>() ) ;

        int half = length / 2 ;
        ArrayList<Key> usedKeys = new ArrayList<>() ;

        // Remplir la première moitié avec les touches de kb1
        for ( int i = 0 ; i < half; i++ ) {
            for ( int j = 0 ; j < res.size() ; j++ ) {
                if (i < kb1.getKeys().get(j).size()) {
                    Key key = kb1.getKeys().get(j).get(i);
                    res.get(j).add(key);
                    usedKeys.add(key);
                }
            }
        }

        // Remplir la deuxième moitié avec les touches de kb2 sans doublons
        for ( int i = half ; i < kb2.getKeys().get(0).size() ; i++ ) {
            for ( int j = 0 ; j < res.size() ; j++ ) {
                if (i < kb2.getKeys().get(j).size()) {
                    Key key = kb2.getKeys().get(j).get(i);
                    if (!usedKeys.contains(key)) {
                        res.get(j).add(key);
                        usedKeys.add(key);
                    }
                }
            }
        }

        completeWithKeys( kb1 , usedKeys , res );
        completeWithKeys( kb2 , usedKeys , res );

        return new Keyboard( res ) ;
    }

    /**
     * Une fonction qui complete res avec les touches manquantes de kb
     * @param kb le clavier
     * @param usedKeys la liste de touche deja dans res
     * @param res le resultat
     */
    private void completeWithKeys(Keyboard kb, ArrayList<Key> usedKeys, ArrayList<ArrayList<Key>> res) {
        for (int j = 0; j < kb.getKeys().size(); j++) {
            for (int i = 0; i < kb.getKeys().get(j).size(); i++) {
                Key key = kb.getKeys().get(j).get(i);
                if (!usedKeys.contains(key)) {
                    boolean added = false;
                    for (int k = 0; k < res.size(); k++) {
                        if (res.get(k).size() < res.get(0).size()) {
                            res.get(k).add(key);
                            usedKeys.add(key);
                            added = true;
                            break;
                        }
                    }
                    if (!added) {
                        return;
                    }
                }
            }
        }
    }
}
