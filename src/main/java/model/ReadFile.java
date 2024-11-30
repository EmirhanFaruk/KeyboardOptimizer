package model;

import view.Display;

import java.io.File;

public class ReadFile
{

    private static File file ;

    public ReadFile () {
        file = null ;

    }
    public static void openFile ( String s ) {
        String path = System.getProperty( "user.dir" ) ;
        try {
            file = new File( path + "/src/resources/file/" + s ) ;
        } catch (Exception e) {
            file = new File( path + "\\src\\resources\\file\\" + s ) ;
        }
        Display.closeScanner();
    }

    public File getFile() {
        return file ;
    }

    public void setFile( File file ) {
        ReadFile.file = file ;
    }
}