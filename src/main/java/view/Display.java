package view;

import model.ReadFile;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Display
{
    private static Scanner scannerAnswer ;


    private static final String[] client_response = new String[5]; // choix(tout/un seul), type test, fic choisi, clavier choisi, clavier/texte

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
                case "1": // Tout

                    client_response[0] = "1";
                    chooseDisplayFile();
                    break;
                case "2": // Seul
                    client_response[0] = "2";
                    chooseTestType();
                    System.out.println(client_response[1]);
                    if (client_response[1].equals("1")) // Analyseur
                    {
                        chooseClavier();
                        chooseDisplayFile();
                        break;
                    }
                    else if (client_response[1].equals("2")) // Evaluateur
                    {
                        chooseClavier();
                        break;
                    }
                    else if (client_response[1].equals("3")) // Optimisateur
                    {
                        chooseClavier();
                        chooseDisplayFile();
                        break;
                    }
                    break;
                case "q":
                    closeScanner();
                    exit(0);
        }
    }

    /**
     * Une fonction qui affiche dans le terminal le menu
     */
    private void displayMenu() {
        System.out.println( "\n\n\n\n\n---------------- KEYBOARD ANALYSE ------------------" ) ;
        System.out.println( "1 : Faire combinaison de tout" ) ;
        System.out.println( "2 : Faire un seul" ) ;
        System.out.println( "Q : Quitter" ) ;
        System.out.print( "Choisissez une option : " ) ;
    }

    /**
     * Une fonction qui permet de choisir le fichier qu'on veut decomposer
     */
    private void chooseDisplayFile (){
        ArrayList<String> listFiles = ReadFile.getFilePaths("txt", true);
        System.out.println();
        displayFile("txt");
        String userInput = scannerAnswer.nextLine().replaceAll("\\s", "").toLowerCase();
        try {
            int i = Integer.parseInt(userInput) - 1 ;
            ReadFile.openFile(listFiles.get(i));
            client_response[2] = listFiles.get(i);

        } catch (Exception e) {
            System.out.println("\nErreur : Une erreur est apparu.");
            chooseDisplayFile();
        }
    }


    /**
     * Afficher la liste des fichiers
     */
    private void printFiles(String extension)
    {
        ArrayList<String> files = ReadFile.getFilePaths(extension, false);
        for (int i = 0; i < files.size(); i++)
        {
            System.out.println((i + 1) + ": " + files.get(i));
        }
    }


    /**
     * Une fonction qui affiche dans le terminal le choix des fichiers
     */
    private void displayFile (String extension) {
        printFiles(extension);
        System.out.print( "Choisissez le fichier que vous voulez analyser : " ) ;
    }



    private void chooseTestType()
    {
        System.out.println("1: Analyseur de fréquence de suites de caractères dans un corpus de textes donné");
        System.out.println("2: Évaluateur de disposition de clavier");
        System.out.println("3: Optimiseur de disposition de clavier");
        System.out.print("Type de test : ");


        String userInput = scannerAnswer.nextLine().replaceAll("\\s", "").toLowerCase();
        try {
            int i = Integer.parseInt(userInput);
            if (i > 3 || i < 1)
            {
                throw new Exception();
            }
            client_response[1] = i + "";

        } catch (Exception e) {
            System.out.println("\nErreur : Une erreur est apparu.");
            chooseTestType();
        }
    }



    private void chooseClavierouText()
    {
        System.out.println("1: Clavier existant");
        System.out.println("2: Clavier depuis un texte");
        System.out.print("Utiliser: ");

        String userInput = scannerAnswer.nextLine().replaceAll("\\s", "").toLowerCase();
        try {
            int i = Integer.parseInt(userInput) - 1 ;
            if (i > 2 || i < 1)
            {
                throw new Exception();
            }
            client_response[4] = i + "";

        } catch (Exception e) {
            System.out.println("\nErreur : Une erreur est apparu.");
            chooseClavierouText();
        }
    }


    private void chooseClavier()
    {
        ArrayList<String> listClaviers = ReadFile.getFilePaths("json", false);
        displayFile("json");

        String userInput = scannerAnswer.nextLine().replaceAll("\\s", "").toLowerCase();
        try {
            int i = Integer.parseInt(userInput) - 1 ;
            client_response[3] = listClaviers.get(i);

        } catch (Exception e) {
            System.out.println("\nErreur : Une erreur est apparu.");
            chooseClavier();
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