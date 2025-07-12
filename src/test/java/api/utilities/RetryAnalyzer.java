package api.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount=0;
    private static final int maxretryCount=2;
    @Override
    public boolean retry(ITestResult result) {

        if(retryCount<maxretryCount){
            retryCount++;
            System.out.println("Retrying "+result.getName()+" | Retry Count: "+retryCount );
            return true;
        }

        return false;
    }
}
