package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.util.Properties;

public class UtilsMethods {

    public static String getValue(String value){
        Properties properties=new Properties();
        try {
            FileInputStream fileInputStream=new FileInputStream("src/test/resources/userData.properties");
            properties.load(fileInputStream);
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        String data=properties.getProperty(value);
        try{
            data=new String(data.getBytes(), "UTF-8");
        } catch (Exception e){
            e.printStackTrace();
        }

        return data;
    }
}
