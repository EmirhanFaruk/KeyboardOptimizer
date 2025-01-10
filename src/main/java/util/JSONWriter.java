package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import model.keyboard.Key;
import model.keyboard.Keyboard;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JSONWriter {

    /**
     * Convertit un Hashtable<String[], Integer> en Map<String, Integer> avec des clés lisibles.
     * @param ht Le hashtable original
     * @return Une version avec des clés sous forme de chaînes
     */
    private static Map<String, Integer> convertHashtable( Hashtable<String[], Integer> ht ) {
        Map<String, Integer> result = new LinkedHashMap<>() ;
        for ( Map.Entry<String[], Integer> entry : ht.entrySet() ) {
            // Joindre le tableau String[] en une chaîne
            String key = String.join( " ", entry.getKey() ) ;
            result.put( key, entry.getValue() ) ;
        }
        return result ;
    }

    /**
     * Sauvegarde un ensemble de n grammes (1-grammes, 2-grammes, ...) dans un fichier JSON.
     * @param allNGrammes Map contenant les n grammes groupés par type (1-grammes, 2-grammes, ...)
     * @param filename Nom du fichier de sortie
     */
    public static void saveAllNGrammesAsJSON (Map<String, Hashtable<String[] , Integer>> allNGrammes , String filename ) {
        ObjectMapper objectMapper = new ObjectMapper() ;

        objectMapper.enable( SerializationFeature.INDENT_OUTPUT ) ;

        try {
            // Créer une liste pour assurer l'ordre
            Map<String, Map<String, Integer>> orderedNGrammes = new LinkedHashMap<>() ;

            // Ajouter les groupes dans un ordre spécifique
            for ( String ngramType : Arrays.asList( "1-grammes" , "2-grammes" , "3-grammes" ) ) {
                Hashtable <String[], Integer> nGramData = allNGrammes.get( ngramType ) ;
                if ( nGramData != null ) {
                    orderedNGrammes.put( ngramType , convertHashtable( nGramData ) ) ;
                } else {
                    orderedNGrammes.put( ngramType , new HashMap<>() ) ;
                }
            }

            String s = File.separator ;
            String path = System.getProperty( "user.dir" ) + s + "src" + s + "main" + s + "resources" + s + "json" + s + filename ;
            objectMapper.writeValue( new File( path ) , orderedNGrammes ) ;
            System.out.println( "Fichier JSON sauvegardé : " + filename ) ;
        } catch ( IOException e ) {
            System.out.println( "Le fichier json n'est pas créer." ) ;
            e.printStackTrace() ;
        }
    }


    /**
     * Créer un map qui clavier
     * @param keyboard le clavier
     * @return la map
     */
    private static Map<String, ArrayList<ArrayList<ArrayList<String>>>> getStringArrayListMap(Keyboard keyboard) {
        Map<String, ArrayList<ArrayList<ArrayList<String>>>> jsonOutput = new LinkedHashMap<>();
        ArrayList<ArrayList<ArrayList<String>>> keys = new ArrayList<>();

        for (ArrayList<Key> keyList : keyboard.getKeys()) {
            ArrayList<ArrayList<String>> range = new ArrayList<>();

            // Initialise chaque colonne de la rangée avec null
            ArrayList<ArrayList<String>> rawRange = new ArrayList<>();
            for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
                rawRange.add(new ArrayList<>(Collections.nCopies(13, null)));
            }

            // Place les touches aux positions correspondantes
            for (Key key : keyList) {
                int row = key.getRangee(); // Ligne où se trouve la touche
                int col = key.getColumn(); // Colonne où se trouve la touche
                rawRange.get(row).set(col, key.getTouchName());
            }

            // Filtre les rangées qui contiennent au moins une valeur non nulle
            for (ArrayList<String> row : rawRange) {
                if (row.stream().anyMatch(Objects::nonNull)) {
                    range.add(row);
                }
            }
            keys.add(range);
        }

        jsonOutput.put("keys", keys);
        return jsonOutput;
    }

    /**
     * Une fonction qui cree un json du clavier optimisé.
     * @param keyboard le clavier optimisé
     * @param filename nom du fichier
     */
    public static void saveOptimizedKeyboardAsJSON( Keyboard keyboard, String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            Map<String, ArrayList<ArrayList<ArrayList<String>>>> jsonOutput = getStringArrayListMap(keyboard);

            // Sauvegarder le fichier JSON
            String s = File.separator;
            String path = System.getProperty("user.dir") + s + "src" + s + "main" + s + "resources" + s + "json" + s + filename;
            objectMapper.writerWithDefaultPrettyPrinter().writeValue( new File(path), jsonOutput);
            System.out.println("Fichier JSON sauvegardé : " + filename);

        } catch (IOException e) {
            System.out.println("Le fichier json n'a pas pu être créé.");
            e.printStackTrace();
        }
    }


}

