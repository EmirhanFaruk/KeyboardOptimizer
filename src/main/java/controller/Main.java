package controller;

import model.AnalyseFile;
import view.DisplayHT;
import view.Display;

import java.io.FileNotFoundException;
import java.util.Hashtable;

public class Main
{
    public static void main() throws FileNotFoundException {
        System.out.println("J'AIME BIEN LE PROJET DE CPOO! ^^");
        Display display = new Display() ;
        display.showMenu();
        AnalyseFile.test();
    }
}