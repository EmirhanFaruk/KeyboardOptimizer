package view;

import model.ReadFile;

import java.util.Scanner;

public class Display
{
    static Scanner scannerAnswer ;

    String choosefile ;

    public Display (){
        scannerAnswer = new Scanner(System.in) ;
        this.choosefile = null ;
    }

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
                    System.out.println("Argument invalide. Veuillez réessayer. ");
                    showMenu();
        }
    }
    public void displayMenu() {
        System.out.println( "---------------- KEYBOARD ANALYSE ------------------" ) ;
        System.out.println( "1 : Commencez" ) ;
        System.out.println( "Q : Quitter" ) ;
        System.out.print( "Choisissez une option : " ) ;
    }

    public void chooseDisplayFile () {
        displayFile();
        String userInput = scannerAnswer.nextLine().replaceAll("\\s", "").toLowerCase();
        switch (userInput) {
            case "bee_movie_script":
                this.choosefile = "Bee_Movie_Script" ; break ;
            case "bee_movie_script_with_newline":
                this.choosefile = "Bee_Movie_Script_With_Newline" ; break ;

            default:
                System.out.println("\nArgument invalide. Veuillez réessayer. ");
                chooseDisplayFile();
        }
        ReadFile.openFile(this.choosefile+".txt");
    }

    public void displayFile () {
        System.out.println( "Bee_Movie_Script" ) ;
        System.out.println( "Bee_Movie_Script_With_Newline" ) ;
        System.out.print( "Choisissez le fichier que vous voulez analyser : " ) ;
    }


    public static void closeScanner () {
        scannerAnswer.close() ;
    }

    public Scanner getScanner() {
        return scannerAnswer ;
    }
}