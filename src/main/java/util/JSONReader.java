package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class JSONReader {

    public static Hashtable<String[], Integer> readHashtableFromJSON( String filePath ) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Lire la Map depuis le fichier JSON
            Map<String, Integer> map = mapper.readValue( new File( filePath ) , new TypeReference<>() {} ) ;

            // Convertir la Map en Hashtable<String[], Integer>
            Hashtable<String[], Integer> hashtable = new Hashtable<>() ;
            map.forEach( ( key , value ) -> {
                String[] keysArray = key.split(" ") ;
                hashtable.put( keysArray , value ) ;
            } ) ;

            return hashtable ;
        } catch ( IOException e ) {
            System.err.println( "Erreur lors de la lecture du fichier JSON : " + e.getMessage() ) ;
        }
        return null ;
    }


    public static Hashtable<String, String[][][]> readKeyboardHTfromJSON(String filePath)
    {


        return null;
    }
}
