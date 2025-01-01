package controller;

import model.Key;
import model.Keyboard;
import model.analyse.AnalyseFile;
import model.analyse.AnalyseFileChars;
import model.keyboard.KeyboardEvaluator;
import model.keyboard.KeyboardOptimizer;
import util.JSONWriter;
import view.Display;

import java.io.FileNotFoundException;
import java.util.Hashtable;

public class Main
{
    public static void main() throws FileNotFoundException {
        System.out.println("J'AIME BIEN LE PROJET DE CPOO! ^^");
        Display display = new Display() ;
        display.showMenu();

        Hashtable<String, Integer> NGrammesPourCaractere = new Hashtable<>() ;
        Hashtable<String[], Integer> NGrammes = new Hashtable<>() ;

        Keyboard keyboard = Keyboard.keyboardFromJSON("src/main/resources/json/init_keyboard.json");
        KeyboardOptimizer keyboardOptimizer = new KeyboardOptimizer( keyboard );
        KeyboardEvaluator keyboardEvaluator = new KeyboardEvaluator( keyboard ) ;

        if (Display.getPourCaractere())
        {
            AnalyseFileChars.test( NGrammesPourCaractere );
        }
        else
        {
            AnalyseFile.test( NGrammes );
        }

        System.out.println( "Le score du clavier avant l'optimisation est de " + keyboardEvaluator.evaluateKeyboard(NGrammes) ) ;

        keyboard = keyboardOptimizer.optimize(NGrammes);

        JSONWriter.saveOptimizedKeyboardAsJSON( keyboard, "optimized_keyboard.json");
        keyboardEvaluator.setKeyboard(keyboard);

        System.out.println( "Le score du clavier apres optimisation est de " + keyboardEvaluator.evaluateKeyboard(NGrammes) ) ;

        //System.out.println(keyboard);

    }
}