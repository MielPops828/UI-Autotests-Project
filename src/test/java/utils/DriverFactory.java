package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    public enum Browser {
        CHROME,
        FIREFOX,
        EDGE,
        IE;

        public static Browser fromString(String value) {
            return Browser.valueOf(value.toUpperCase());
        }
    }

    public static WebDriver createWebdriver(Browser browser) {
        String runType = System.getProperty("runType", "local");
        if (runType.equalsIgnoreCase("grid")) {
            return createRemoteDriver(browser);
        } else {
            return createLocalWebdriver(browser);
        }
    }

    public static WebDriver createLocalWebdriver(Browser browser){
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
            case IE -> {
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions opt = new InternetExplorerOptions();
                opt.ignoreZoomSettings();
                opt.introduceFlakinessByIgnoringSecurityDomains();
                yield new InternetExplorerDriver(opt);
            }
            default -> throw new IllegalArgumentException("Браузер не поддерживается: " + browser);
        };
    }

    private static WebDriver createRemoteDriver(Browser browser) {
        try {
            return switch (browser) {
                case CHROME -> new RemoteWebDriver(
                        new URL(ParameterProvider.get("grid.url")),
                        new ChromeOptions()
                );
                case FIREFOX -> new RemoteWebDriver(
                        new URL(ParameterProvider.get("grid.url")),
                        new FirefoxOptions()
                );
                case EDGE -> new RemoteWebDriver(
                        new URL(ParameterProvider.get("grid.url")),
                        new EdgeOptions()
                );
                case IE -> {
                    InternetExplorerOptions opt = new InternetExplorerOptions();
                    opt.ignoreZoomSettings();
                    opt.introduceFlakinessByIgnoringSecurityDomains();
                    yield new RemoteWebDriver(
                            new URL(ParameterProvider.get("grid.url")),
                            opt
                    );
                }
                default -> throw new IllegalArgumentException("Браузер не поддерживается: " + browser);
            };
        } catch (MalformedURLException e) {
            throw new RuntimeException("Некорректный url", e);
        }
    }
}
