package model;

import view.Display;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile
{

    private static File file ;
    public static String text ;

    public ReadFile () {
        file = null ;

    }

    /**
     * Une fonction qui ouvre le fichier a ouvrir avec son path
     * @param s un string qui designe le nom du fichier
     * @throws FileNotFoundException si le fichier n'est pas trouvé
     */
    public static void openFile ( String s ) throws FileNotFoundException {
        String path = System.getProperty( "user.dir" ) ;
        try {
            file = new File( path + "/src/main/resources/file/" + s ) ;
        } catch (Exception e) {
            file = new File( path + "\\src\\main\\resources\\file\\" + s ) ;
        }
       readFile();
    }

    /**
     * Une fonction qui lit le fichier txt
     * @throws FileNotFoundException si le fichier n'est pas trouvé
     */
    public static void readFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            text = text + str + "\n"; // On ajoute "\n" car on doit appuyer sur entree donc il faut le rajouter
        }
        scanner.close();
        Display.closeScanner();
    }

    public File getFile() {
        return file ;
    }

    public void setFile( File file ) {
        ReadFile.file = file ;
    }
}