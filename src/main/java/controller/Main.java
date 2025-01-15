package controller;

import model.keyboard.Key;
import model.keyboard.Keyboard;
import model.analyse.AnalyseFile;
import model.keyboardeval.KeyboardEvaluator;
import model.keyboardeval.KeyboardOptimizer;
import util.JSONWriter;
import view.Display;
import view.DisplayKeyboard;

import java.util.Hashtable;

public class Main
{
    private static boolean check(String[] tab, int n, String str)
    {
        return tab[n] != null && tab[n].equals(str);
    }


    public static void main() {
        System.out.println("J'AIME BIEN LE PROJET DE CPOO! ^^");
        Display display = new Display() ;
        while (true)
        {
            display.showMenu();

            String[] clrep = Display.getClient_response();  // 0: choix(tout/un seul),
                                                            // 1: type test, (1: Analyseur, 2: Evaluateur, 3: Optimisateur)
                                                            // 2: fic choisi,
                                                            // 3: clavier choisi,
                                                            // 4: clavier/texte
            try {

                if (check(clrep, 0, "1"))
                {
                    System.out.println("Tout");
                    faireTout();
                }
                else if (check(clrep, 0, "2"))
                {
                    System.out.println("Seul");
                    if (check(clrep, 1, "1"))
                    {
                        System.out.println("First option(Analyseur)");
                        analyseText();
                    }
                    else if (check(clrep, 1, "2"))
                    {
                        System.out.println("Second option(Evaluateur)");
                        evaluateKeyboard();
                    }
                    else if (check(clrep, 1, "3"))
                    {
                        System.out.println("Third option(Optimizeur)");
                        optimizeKeyboard();
                    }
                    else
                    {
                        System.out.println("Hit the bricks pal you're done");
                    }
                }
            } catch (Exception e)
            {
                e.printStackTrace();
                System.out.println("Erreur. Revenir au menu...");
            }
            System.out.println("Fin de boucle");

        }
    }




    private static void analyseText()
    {
        String[] clrep = Display.getClient_response();

        Hashtable<String[], Integer> NGrammes = new Hashtable<>();

        Keyboard keyboard = Keyboard.keyboardFromJSON(clrep[3]);

        DisplayKeyboard.printKeyboard(keyboard);

        AnalyseFile.test(NGrammes);

        KeyboardOptimizer.printMap(NGrammes);
    }


    private static void evaluateKeyboard()
    {
        Hashtable<String[], Integer> NGrammes = new Hashtable<>() ;

        String[] clrep = Display.getClient_response();

        Keyboard keyboard = Keyboard.keyboardFromJSON(clrep[3]);

        DisplayKeyboard.printKeyboard(keyboard);

        KeyboardEvaluator evaluator = new KeyboardEvaluator(keyboard);

        AnalyseFile.test(NGrammes);

        double score = evaluator.evaluateKeyboard(NGrammes);

        KeyboardOptimizer.printMap(NGrammes);

        System.out.println( "Le score du clavier avant l'optimisation est de " + score );
    }


    private static void optimizeKeyboard()
    {
        Hashtable<String[], Integer> NGrammes = new Hashtable<>() ;

        String[] clrep = Display.getClient_response();

        Keyboard keyboard = Keyboard.keyboardFromJSON(clrep[3]);

        DisplayKeyboard.printKeyboard(keyboard);


        KeyboardOptimizer keyboardOptimizer = new KeyboardOptimizer( keyboard );
        KeyboardEvaluator keyboardEvaluator = new KeyboardEvaluator( keyboard );

        AnalyseFile.test(NGrammes);

        double initial_score = keyboardEvaluator.evaluateKeyboard(NGrammes);

        System.out.println( "Le score du clavier avant l'optimisation est de " + initial_score ) ;

        keyboard = keyboardOptimizer.trueOptimize( NGrammes );
        keyboardEvaluator.setKeyboard(keyboard);

        double optimized_score = keyboardEvaluator.evaluateKeyboard(NGrammes);
        System.out.println( "Le score du clavier apres une vrai optimisation est de " + optimized_score ) ;

        if (initial_score > optimized_score)
        {
            JSONWriter.saveOptimizedKeyboardAsJSON( keyboard, "optimized_keyboard.json");
        }


        DisplayKeyboard.printKeyboard(keyboard);
    }



    private static void faireTout()
    {
        Hashtable<String[], Integer> NGrammes = new Hashtable<>() ;

        Keyboard keyboard = Keyboard.keyboardFromJSON("init_keyboard.json");

        DisplayKeyboard.printKeyboard(keyboard);


        KeyboardOptimizer keyboardOptimizer = new KeyboardOptimizer( keyboard );
        KeyboardEvaluator keyboardEvaluator = new KeyboardEvaluator( keyboard ) ;

        AnalyseFile.test(NGrammes);

        double initial_score = keyboardEvaluator.evaluateKeyboard(NGrammes);

        System.out.println( "Le score du clavier avant l'optimisation est de " + initial_score ) ;

        /*
        keyboard = keyboardOptimizer.optimize( NGrammes );
        JSONWriter.saveOptimizedKeyboardAsJSON( keyboard, "optimized_keyboard.json");
        keyboardEvaluator.setKeyboard(keyboard);

        System.out.println( "Le score du clavier apres l'optimisation est de " + keyboardEvaluator.evaluateKeyboard(NGrammes) ) ;

        //System.out.println(keyboard);

        DisplayKeyboard.printKeyboard(keyboard);

         */

        keyboard = keyboardOptimizer.trueOptimize( NGrammes );
        keyboardEvaluator.setKeyboard(keyboard);

        double optimized_score = keyboardEvaluator.evaluateKeyboard(NGrammes);
        System.out.println( "Le score du clavier apres une vrai optimisation est de " + optimized_score ) ;

        if (initial_score > optimized_score)
        {
            JSONWriter.saveOptimizedKeyboardAsJSON( keyboard, "optimized_keyboard.json");
        }


        DisplayKeyboard.printKeyboard(keyboard);
    }
}