package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class JSONReader {
    private static final String src_path = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "json" + File.separator;

    public static Hashtable<String, String[][][]> readKeyboardHTfromJSON(String filePath)
    {
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Lire la Map depuis le fichier JSON
            Map<String, String[][][]> map = mapper.readValue( new File( src_path + filePath ) , new TypeReference<>() {} ) ;

            return new Hashtable<>(map);
        } catch ( IOException e ) {
            System.err.println( "Erreur lors de la lecture du fichier JSON : " + e.getMessage() ) ;
        }
        return null ;
    }
}
