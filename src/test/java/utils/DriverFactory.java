package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    public enum Browser {
        CHROME,
        FIREFOX,
        EDGE;

        public static Browser fromString(String value) {
            return Browser.valueOf(value.toUpperCase());
        }
    }

    public static WebDriver createWebdriver(Browser browser){
        return switch (browser) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver();
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver();
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                yield new EdgeDriver();
            }
            default -> throw new IllegalArgumentException("Browser not supported: " + browser);
        };
    }
}
