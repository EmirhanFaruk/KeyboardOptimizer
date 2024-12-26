package view;

import model.ReadFile;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Display
{
    static Scanner scannerAnswer ;
    private int nrv = 0 ;
    static final ArrayList<String> listFiles = ReadFile.getFilePaths(true);
    private static boolean pourCaractere = false;

    public Display (){
        scannerAnswer = new Scanner(System.in) ;
    }

    /**
     * Une fonction qui affiche le menu qui permet l'interactionavec l'utilisateur
     * @throws FileNotFoundException si le fichier n'est pas trouvé
     */

    public void showMenu () throws FileNotFoundException {
            displayMenu();
            String userInput = scannerAnswer.nextLine().replaceAll("\\s", "").toLowerCase();
            switch (userInput) {
                case "1":
                    System.out.println();
                    chooseDisplayFile();
                    pourCaractere = true;
                    break;
                case "2":
                    System.out.println();
                    chooseDisplayFile();
                    pourCaractere = false;
                    break;
                case "q":
                    closeScanner();
                    return;
                default:
                    errorMessage();
                    showMenu();
        }
    }

    /**
     * Une fonction qui affiche dans le terminal le menu
     */
    public void displayMenu() {
        System.out.println( "---------------- KEYBOARD ANALYSE ------------------" ) ;
        System.out.println( "1 : Commencez pour les caracteres" ) ;
        System.out.println( "2 : Commencez pour les combinaisons des touches" ) ;
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
            e.printStackTrace();
            errorMessage();
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
     * Une fonction qui s'énerve quand on fait trop d'erreur de frappe
     */
    private void errorMessage (){
        if ( nrv < 3 ) {
            System.out.println("\nArgument invalide. Veuillez réessayer.");
        } else if (nrv < 6) {
            System.out.println("\nArrête de te tromper, petite merde.");
        } else if ( nrv < 9 ) {
            System.out.println("C'est bon tu m'énerves, ARRÊTE. ");
        } else if (nrv == 9) {
            System.out.println("\nLa prochaine fois que tu te trompes je pars.");
        } else {
            scannerAnswer.close();
            System.exit(0);
        }
        nrv++;
    }



    /**
     * Une fonction qui ferme le scanner activer dans le menu
     */
    public static void closeScanner () {
        scannerAnswer.close() ;
    }


    public static boolean getPourCaractere()
    {
        return pourCaractere;
    }
}