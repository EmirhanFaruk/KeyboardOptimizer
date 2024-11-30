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
            case "court":
                this.choosefile = "court"; break ;
            case "moyen":
                this.choosefile = "moyen"; break ;
            case "long":
                this.choosefile = "long"; break ;
            default:
                System.out.println("\nArgument invalide. Veuillez réessayer. ");
                chooseDisplayFile();
        }
        ReadFile.openFile(this.choosefile+".txt");
    }

    public void displayFile () {
        System.out.println( "Court" ) ;
        System.out.println( "Moyen" ) ;
        System.out.println( "Long" ) ;
        System.out.print( "Choisissez le fichier que vous voulez analyser : " ) ;
    }


    public static void closeScanner () {
        scannerAnswer.close() ;
    }

    public Scanner getScanner() {
        return scannerAnswer ;
    }
}