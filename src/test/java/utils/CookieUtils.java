package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CookieUtils {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveCookies(WebDriver driver, String path){
        try (FileWriter fw = new FileWriter(path)){
            Set<Cookie> cookies = driver.manage().getCookies();
            List<SerializableCookie> serializableCookies = new ArrayList<>();
            for (Cookie cookie : cookies){
                serializableCookies.add(new SerializableCookie(cookie));
            }
            gson.toJson(serializableCookies, fw);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadCookies(WebDriver driver, String path){
        File file = new File(path);
        if (!file.exists()){
            return;
        }

        try (FileReader fr = new FileReader(file)){
            Type listType = new TypeToken<List<SerializableCookie>>(){}.getType();
            List<SerializableCookie> cookies = gson.fromJson(fr, listType);

            for (SerializableCookie sc : cookies) {
                driver.manage().addCookie(sc.toCookie());
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
