package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer {

    private int retryCount = 0;
    private  int retryMaxCount = Integer.parseInt(ParameterProvider.get("retry.count"));

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (retryCount < retryMaxCount){
            retryCount++;
            return true;
        }
        return false;
    }
}
