package utils;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {
    @DataProvider(name = "validLoginData")
    public static Object[][] validLoginData() {
        return new Object[][]{
                {"angular", "password", "Valid"}
        };
    }
    @DataProvider(name = "invalidLoginData")
    public static Object[][] invalidLoginData() {
        return new Object[][]{
                {"react", "pass", "Invalid"}
        };
    }
}
