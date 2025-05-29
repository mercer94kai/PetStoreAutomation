package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class dataProvider {

    public static String path=System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\userData.xlsx";

    @DataProvider(name="allData")
    public String[][] getData() throws IOException {


        excelUtility ex=new excelUtility(path);

        int row=ex.getRowCount("Sheet1");
        int col=ex.getCellCount("Sheet1",1);

        String[][] apiData=new String[row][col];

        for (int i=1;i<=row;i++){
            for(int j=0;j<col;j++){
                apiData[i-1][j]=ex.getCellData("Sheet1",i,j);
            }
        }
        return apiData;
    }

    @DataProvider(name="UserNames")
    public String[] getUserNames() throws IOException {

        excelUtility ex=new excelUtility(path);

        int row=ex.getRowCount("Sheet1");

        String[] username=new String[row];

        for (int i=1;i<=row;i++){
            username[i-1]=ex.getCellData("Sheet1",i,1);
        }

        return username;
    }
}
