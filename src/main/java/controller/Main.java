package controller;

import model.Keyboard;
import model.analyse.AnalyseFile;
import model.analyse.AnalyseFileChars;
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

        if (Display.getPourCaractere())
        {
            AnalyseFileChars.test( NGrammesPourCaractere );
        }
        else
        {
            AnalyseFile.test( NGrammes );
        }

        KeyboardOptimizer keyboardOptimizer = new KeyboardOptimizer( keyboard );
        keyboard = keyboardOptimizer.optimize(NGrammes);
        JSONWriter.saveOptimizedKeyboardAsJSON( keyboard, "optimized_keyboard.json");
        //System.out.println(keyboard);

    }
}