package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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
}

