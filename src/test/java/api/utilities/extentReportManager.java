package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class extentReportManager implements ITestListener {
    ExtentSparkReporter sparkReporter;
    ExtentReports reports;
    public static ExtentTest test;

    String repoName;


    public void onStart(ITestContext context){
        String timeStamp=new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
        repoName="Test Report_"+timeStamp+".html";

        sparkReporter=new ExtentSparkReporter(".\\reports\\"+repoName);

        sparkReporter.config().setDocumentTitle("Rest Assured Automation Project");
        sparkReporter.config().setReportName("Pet Store Users API");
        sparkReporter.config().setTheme(Theme.DARK);

        reports=new ExtentReports();
        reports.attachReporter(sparkReporter);
        reports.setSystemInfo("User",System.getProperty("user.name"));
        reports.setSystemInfo("OS",System.getProperty("os.name"));
        reports.setSystemInfo("Application","Swagger Pet Store");
    }

    public void onTestSuccess(ITestResult result){

        test=reports.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.PASS,"Test Passed");

    }

    public void onTestFailure(ITestResult result){

        test=reports.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.FAIL,"Test Failed");
        test.log(Status.FAIL,result.getThrowable().getMessage());

    }

    public void onTestSkips(ITestResult result){

        test=reports.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.SKIP,"Test Skipped");
        test.log(Status.SKIP,result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context){
        reports.flush();
    }

}
