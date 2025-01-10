package view;

import model.ReadFile;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Display
{
    static Scanner scannerAnswer ;
    static final ArrayList<String> listFiles = ReadFile.getFilePaths(true);

    public Display (){
        scannerAnswer = new Scanner(System.in) ;
    }

    /**
     * Une fonction qui affiche le menu qui permet l'interaction avec l'utilisateur
     */
    public void showMenu () {
            displayMenu();
            String userInput = scannerAnswer.nextLine().replaceAll("\\s", "").toLowerCase();
            switch (userInput) {
                case "1":
                    System.out.println();
                    chooseDisplayFile();
                    break;
                case "q":
                    closeScanner();
                    return;
                default:
                    showMenu();
        }
    }

    /**
     * Une fonction qui affiche dans le terminal le menu
     */
    public void displayMenu() {
        System.out.println( "---------------- KEYBOARD ANALYSE ------------------" ) ;
        System.out.println( "1 : Commencez pour les combinaisons des touches" ) ;
        System.out.println( "Q : Quitter" ) ;
        System.out.print( "Choisissez une option : " ) ;
    }

    /**
     * Une fonction qui permet de choisir le fichier qu'on veut decomposer
     */
    public void chooseDisplayFile (){
        displayFile();
        String userInput = scannerAnswer.nextLine().replaceAll("\\s", "").toLowerCase();
        try {
            int i = Integer.parseInt(userInput) - 1 ;
            ReadFile.openFile(listFiles.get(i));
            displayMenu();

        } catch (Exception e) {
            System.out.println("\nErreur : Une erreur est apparu.");
            chooseDisplayFile();
        }
    }


    /**
     * Afficher la liste des fichiers
     */
    private void printFiles()
    {
        ArrayList<String> files = ReadFile.getFilePaths(false);
        for (int i = 0; i < files.size(); i++)
        {
            System.out.println((i + 1) + ": " + files.get(i));
        }
    }


    /**
     * Une fonction qui affiche dans le terminal le choix des fichiers
     */
    public void displayFile () {
        printFiles();
        System.out.print( "Choisissez le fichier que vous voulez analyser : " ) ;
    }

    /**
     * Une fonction qui ferme le scanner activer dans le menu
     */
    public static void closeScanner () {
        scannerAnswer.close() ;
    }

}