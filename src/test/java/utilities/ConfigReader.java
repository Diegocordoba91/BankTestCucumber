package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;



public class ConfigReader {

    private static Properties properties;


    static {
        try{
            properties = new Properties();

            FileInputStream file = new FileInputStream("src\\test\\resources\\config.properties");
            properties.load(file);
            file.close();

        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key){

        return properties.getProperty(key);
    }

    public static boolean getBooleanProperty(String key){
        return Boolean.parseBoolean(properties.getProperty(key));
    }

    public static int getIntegerProperty(String key){
        return Integer.parseInt(properties.getProperty(key));
    }
}
