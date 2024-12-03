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
    public static void openFile ( String s ) throws FileNotFoundException {
        String path = System.getProperty( "user.dir" ) ;
        try {
            file = new File( path + "/src/main/resources/file/" + s ) ;
        } catch (Exception e) {
            file = new File( path + "\\src\\main\\resources\\file\\" + s ) ;
        }
       readFile();
    }

    public static void readFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            text = text + str + "\n";
        }
        System.out.println("le fichier est bien lu.  ");
        scanner.close();
    }

    public File getFile() {
        return file ;
    }

    public void setFile( File file ) {
        ReadFile.file = file ;
    }
}