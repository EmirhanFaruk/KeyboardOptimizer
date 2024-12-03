package view;

import model.ReadFile;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Display
{
    static Scanner scannerAnswer ;

    String choosefile ;

    public Display (){
        scannerAnswer = new Scanner(System.in) ;
        this.choosefile = null ;
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
                    break;
                case "q":
                    closeScanner();
                    return;
                default:
                    System.out.println("Argument invalide. Veuillez réessayer. ");
                    showMenu();
        }
    }

    /**
     * Une fonction qui affiche dans le terminal le menu
     */
    public void displayMenu() {
        System.out.println( "---------------- KEYBOARD ANALYSE ------------------" ) ;
        System.out.println( "1 : Commencez" ) ;
        System.out.println( "Q : Quitter" ) ;
        System.out.print( "Choisissez une option : " ) ;
    }

    /**
     * Une fonction qui permet de choisir le fichier qu'on veut decomposer
     * @throws FileNotFoundException si le fichier n'est pas trouvé
     */
    public void chooseDisplayFile () throws FileNotFoundException {
        displayFile();
        String userInput = scannerAnswer.nextLine().replaceAll("\\s", "").toLowerCase();
        switch (userInput) {
            case "1" :
                this.choosefile = "Bee_Movie_Script.txt" ; break ;
            case "2" :
                this.choosefile = "Bee_Movie_Script_With_Newline.txt" ; break ;

            default:
                System.out.println("\nArgument invalide. Veuillez réessayer. ");
                chooseDisplayFile();
        }
        ReadFile.openFile(this.choosefile );
    }

    /**
     * Une fonction qui affiche dans le terminal le choix des fichiers
     */
    public void displayFile () {
        System.out.println( "1 : Bee_Movie_Script" ) ;
        System.out.println( "2 : Bee_Movie_Script_With_Newline" ) ;
        System.out.print( "Choisissez le fichier que vous voulez analyser : " ) ;
    }


    /**
     * Une fonction qui ferme le scanner activer dans le menu
     */
    public static void closeScanner () {
        scannerAnswer.close() ;
    }

}