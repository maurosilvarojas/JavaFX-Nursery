package nurseryApp;

/**
 * Created by regga on 17/12/2016.
 */
public class ScreensPalette  {
    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        ScreensPalette.id = id;
    }

    public static String getPathFXml() {
        return pathFXml;
    }

    public static void setPathFXml(String pathFXml) {
        ScreensPalette.pathFXml = pathFXml;
    }

    private static String id;
    private static String pathFXml ;
}

