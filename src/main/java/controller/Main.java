package controller;

import model.keyboard.Keyboard;
import model.analyse.AnalyseFile;
import model.analyse.AnalyseFileChars;
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
        display.showMenu();

        Hashtable<String[], Integer> NGrammes = new Hashtable<>() ;

        Keyboard keyboard = Keyboard.keyboardFromJSON("src/main/resources/json/init_keyboard.json");

        DisplayKeyboard.printKeyboard(keyboard);


        KeyboardOptimizer keyboardOptimizer = new KeyboardOptimizer( keyboard );
        KeyboardEvaluator keyboardEvaluator = new KeyboardEvaluator( keyboard ) ;

        AnalyseFile.test(NGrammes);
        keyboard = keyboardOptimizer.optimize( NGrammes );


        System.out.println( "Le score du clavier avant l'optimisation est de " + keyboardEvaluator.evaluateKeyboard(NGrammes) ) ;

        JSONWriter.saveOptimizedKeyboardAsJSON( keyboard, "optimized_keyboard.json");
        keyboardEvaluator.setKeyboard(keyboard);

        System.out.println( "Le score du clavier apres optimisation est de " + keyboardEvaluator.evaluateKeyboard(NGrammes) ) ;

        //System.out.println(keyboard);

        DisplayKeyboard.printKeyboard(keyboard);

    }
}