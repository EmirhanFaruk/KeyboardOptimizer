package controller;

import model.keyboard.Keyboard;
import model.analyse.AnalyseFile;
import model.keyboardeval.KeyboardEvaluator;
import model.keyboardeval.KeyboardOptimizer;
import util.JSONWriter;
import view.Display;
import view.DisplayKeyboard;

import java.io.FileNotFoundException;
import java.util.Hashtable;

public class Main
{
    public static void main() {
        System.out.println("J'AIME BIEN LE PROJET DE CPOO! ^^");
        Display display = new Display() ;
        while (true)
        {
            display.showMenu();

            String[] clrep = Display.getClient_response();

            Hashtable<String[], Integer> NGrammes = new Hashtable<>() ;

            Keyboard keyboard = Keyboard.keyboardFromJSON("optimized_keyboard.json");

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
}