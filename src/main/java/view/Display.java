package view;

import model.ReadFile;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Display
{
    private static Scanner scannerAnswer ;
    private static final ArrayList<String> listFiles = ReadFile.getFilePaths(true);

    private static String[] client_response = new String[3];

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
                    client_response[0] = "1";
                    chooseDisplayFile();
                    break;
                case "2":
                    client_response[1] = "2";
                    chooseTestType();
                    chooseDisplayFile();
                    break;
                case "q":
                    closeScanner();
                    exit(0);
                    return;
                default:
                    showMenu();
        }
    }

    /**
     * Une fonction qui affiche dans le terminal le menu
     */
    public void displayMenu() {
        System.out.println( "\n\n\n\n\n---------------- KEYBOARD ANALYSE ------------------" ) ;
        System.out.println( "1 : Faire combinaison de tout" ) ;
        System.out.println( "2 : Faire un seul" ) ;
        System.out.println( "Q : Quitter" ) ;
        System.out.print( "Choisissez une option : " ) ;
    }

    /**
     * Une fonction qui permet de choisir le fichier qu'on veut decomposer
     */
    public void chooseDisplayFile (){
        System.out.println();
        displayFile();
        String userInput = scannerAnswer.nextLine().replaceAll("\\s", "").toLowerCase();
        try {
            int i = Integer.parseInt(userInput) - 1 ;
            ReadFile.openFile(listFiles.get(i));
            client_response[1] = listFiles.get(i);
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



    public void chooseTestType()
    {
        System.out.println("Type de test : ");
        System.out.println("1: Analyseur de fréquence de suites de caractères dans un corpus de textes donné");
        System.out.println("2: Évaluateur de disposition de clavier");
        System.out.println("3: Optimiseur de disposition de clavier");

        String userInput = scannerAnswer.nextLine().replaceAll("\\s", "").toLowerCase();
        try {
            int i = Integer.parseInt(userInput) - 1 ;
            if (i > 3 || i < 1)
            {
                throw new Exception();
            }
            client_response[2] = listFiles.get(i);
            displayMenu();

        } catch (Exception e) {
            System.out.println("\nErreur : Une erreur est apparu.");
            chooseDisplayFile();
        }
    }




    /**
     * Une fonction qui ferme le scanner activer dans le menu
     */
    public static void closeScanner () {
        scannerAnswer.close() ;
    }

    public static String[] getClient_response()
    {
        return client_response;
    }

}