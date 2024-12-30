package controller;

import model.Keyboard;
import model.analyse.AnalyseFile;
import model.analyse.AnalyseFileChars;
import view.Display;

import java.io.FileNotFoundException;

public class Main
{
    public static void main() throws FileNotFoundException {
        System.out.println("J'AIME BIEN LE PROJET DE CPOO! ^^");
        Display display = new Display() ;
        display.showMenu();

        if (Display.getPourCaractere())
        {
            AnalyseFileChars.test();
        }
        else
        {
            AnalyseFile.test();
        }

        Keyboard keyboard = Keyboard.keyboardFromJSON("src/main/resources/json/init_keyboard.json");

        System.out.println(keyboard);

    }
}