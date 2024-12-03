package controller;

import model.AnalyseFile;
import view.DisplayHT;
import view.Display;

import java.util.Hashtable;

public class Main
{
    public static void main()
    {
        System.out.println("J'AIME BIEN LE PROJET DE CPOO! ^^");
        AnalyseFile.test();
        Display display = new Display() ;
        display.showMenu();
    }
}