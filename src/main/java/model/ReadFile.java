package model;

import view.Display;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile
{

    private static File file ;
    public static String text ;

    public ReadFile () {
        file = null ;

    }


    /**
     * Avoir chemin des ressources.
     * @return le chemin des fichiers
     */
    private static String getPath()
    {
        String res;
        // Avoir path de projet/program
        String path = System.getProperty( "user.dir" );
        // Avoir le séparateur: / ou \\
        String s = File.separator;
        // Mettre chemin de ressources dans un string
        res = path + s +
                "src" + s +
                "main" + s +
                "resources" + s +
                "file" + s;

        return res;
    }


    /**
     * Avoir les fichiers de classeur de ressources(type File)
     * @return les fichiers de type File
     */
    private static File[] getFiles()
    {
        File[] res;

        try
        {
            // Avoir les fichiers de classeur de ressources
            String path = getPath();
            File dir = new File(path);
            res = dir.listFiles();
        }
        catch(Exception e)
        {
            System.out.println("Erreur pendant avoir les fichiers de ressources.");
            return null;
        }

        return res;
    }


    /**
     * Renvoyer une liste de fichiers de "file" finissent par .txt
     * @param path Vrai: path complet. Faux: que le nom
     * @return liste des fichiers
     */
    public static ArrayList<String> getFilePaths(Boolean path)
    {
        File[] files = getFiles();
        ArrayList<String> res = new ArrayList<>();

        // Si files est null, ça veut dire aucun fichier dans la classeur
        if (files == null)
        {
            System.out.println("Pas de fichier dans la classeur de ressources.");
            return res;
        }

        // Mettre le chemin des fichiers dans res
        for (File f : files)
        {
            if (f.isFile() && f.getName().endsWith(".txt"))
            {
                if (path)
                {
                    res.add(f.getAbsolutePath());
                }
                else
                {
                    res.add(f.getName());
                }
            }
        }

        return res;
    }

    /**
     * Une fonction qui ouvre le fichier a ouvrir avec son path
     * @param s un string qui designe le nom du fichier
     * @throws FileNotFoundException si le fichier n'est pas trouvé
     */
    public static void openFile ( String s ) throws FileNotFoundException {
        file = new File( s ) ;
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