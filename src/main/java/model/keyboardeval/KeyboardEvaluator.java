package model.keyboardeval;

import model.keyboard.Key;
import model.keyboard.Keyboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KeyboardEvaluator {

    private Keyboard keyboard;

    public KeyboardEvaluator(Keyboard keyboard) {
        this.keyboard = keyboard;
    }


    public  Map<String[], Integer> whatNgrammes (   Map<String[], Integer> ngrammes , int n ){
        Map<String[], Integer> res = new HashMap<>() ;
        for (Map.Entry<String[], Integer> entry : ngrammes.entrySet()) {
            String[] key = entry.getKey();
            Integer value = entry.getValue();

            if ( n == 2 &&  key.length <= n ) {
                res.put(key, value);
            } else if ( n == 3 && key.length == n ) {
                res.put(key, value);
            } else {
                return res ;
            }
        }
        return res ;
    }
    /**
     * Évalue la difficulté des mouvements sur un clavier donné.
     */
    public double evaluateKeyboard( Map<String[], Integer> nGrammes ) {
        double score = 0;
        Map<String[], Integer> bigrams = whatNgrammes(nGrammes , 2 ) ;
        Map<String[], Integer> trigrams =  whatNgrammes( nGrammes , 3 ) ;
        score+=evaluateSingleKeyEffort();

        score += evaluateBigrams(bigrams);

        score += evaluateTrigrams(trigrams);

        return score;
    }

    /**
     * Évalue l'effort par touche isolée (répartition par main et par doigt).
     */
    private double evaluateSingleKeyEffort() {
        double leftHandEffort = 0;
        double rightHandEffort = 0;

        for (ArrayList<Key> row : keyboard.getKeys()) {
            for (Key key : row) {
                if (key.isRightHand() ) {
                    rightHandEffort += key.getEffort();
                } else {
                    leftHandEffort += key.getEffort();
                }
            }
        }

        double handBalance = Math.abs(leftHandEffort - rightHandEffort);

        double fingerDistribution = evaluateFingerDistribution();

        return handBalance + fingerDistribution;
    }

    /**
     * Évalue la répartition par doigt (distance à une répartition idéale).
     */
    private double evaluateFingerDistribution() {
        double[] idealDistribution = {0.1, 0.2, 0.3, 0.2, 0.2}; // Exemple de répartition idéale
        double[] actualDistribution = new double[5]; // Doigts : auriculaire -> pouce

        for (ArrayList<Key> row : keyboard.getKeys()) {
            for (Key key : row) {
                actualDistribution[key.ordinal()] += key.getEffort();
            }
        }

        double distance = 0;
        for (int i = 0; i < 5; i++) {
            distance += Math.abs(idealDistribution[i] - actualDistribution[i]);
        }
        return distance;
    }

    /**
     * Évalue les bigrammes (mouvements de longueur 2).
     */
    private double evaluateBigrams(Map<String[], Integer> bigrams) {
        double score = 0;

        for (Map.Entry<String[], Integer> entry : bigrams.entrySet()) {
            String[] bigram = entry.getKey();
            int frequency = entry.getValue();

            // Vérifier les critères
            if (isSameFingerBigram(bigram)) {
                score += frequency * 2; // Pénalité pour SFB
            } else if (isLateralStretchBigram(bigram)) {
                score += frequency * 1.5; // Pénalité pour LSB
            } else if (isScissorBigram(bigram)) {
                score += frequency * 1.2; // Pénalité pour ciseaux
            } else {
                score -= frequency; // Récompense pour alternances et roulements
            }
        }

        return score;
    }

    /**
     * Évalue les trigrammes (mouvements de longueur 3).
     */
    private double evaluateTrigrams(Map<String[], Integer> trigrams) {
        double score = 0;

        for (Map.Entry<String[], Integer> entry : trigrams.entrySet()) {
            String[] trigram = entry.getKey();
            int frequency = entry.getValue();

            // Vérifier les critères
            if (isBadRedirection(trigram)) {
                score += frequency * 3; // Pénalité pour redirections
            } else if (isSameFingerSkipgram(trigram)) {
                score += frequency * 2.5; // Pénalité pour SKS
            } else {
                score -= frequency; // Récompense pour enchaînements fluides
            }
        }

        return score;
    }

    private boolean isSameFingerBigram(String[] bigram) {
        Key firstKey = keyboard.findKey(bigram[0]);
        Key secondKey = keyboard.findKey(bigram[1]);
        return  firstKey.getFinger().equals(secondKey.getFinger());
    }

    private boolean isLateralStretchBigram(String[] bigram) {
        Key firstKey = keyboard.findKey(bigram[0]);
        Key secondKey = keyboard.findKey(bigram[1]);
        return Math.abs(firstKey.getColumn() - secondKey.getColumn()) > 1;
    }

    private boolean isScissorBigram(String[] bigram) {
        Key firstKey = keyboard.findKey(bigram[0]);
        Key secondKey = keyboard.findKey(bigram[1]);
        return Math.abs(firstKey.getRangee() - secondKey.getRangee()) > 1;
    }

    private boolean isBadRedirection(String[] trigram) {
        Key firstKey = keyboard.findKey( trigram[0]) ;
        Key secondKey = keyboard.findKey( trigram[1] ) ;
        Key thirdKey = keyboard.findKey( trigram[2] ) ;
        return firstKey.isRightHand() && secondKey.isRightHand() && thirdKey.isRightHand()
                && ((firstKey.getColumn() < secondKey.getColumn() && secondKey.getColumn() > thirdKey.getColumn())
                || (firstKey.getColumn() > secondKey.getColumn() && secondKey.getColumn() < thirdKey.getColumn()));
    }

    private boolean isSameFingerSkipgram(String[] trigram) {
        Key firstKey = keyboard.findKey(trigram[0]);
        Key thirdKey = keyboard.findKey(trigram[2]);
        return firstKey.getFinger().equals(thirdKey.getFinger());
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }
}
