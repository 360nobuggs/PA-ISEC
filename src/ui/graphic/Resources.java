package ui.graphic;

import java.io.InputStream;

public class Resources {
    public static InputStream getResourceFile(String name){
        // Getting named resource from Resources.class location...
        InputStream in=Resources.class.getResourceAsStream(name);
        return in;
    }
}
