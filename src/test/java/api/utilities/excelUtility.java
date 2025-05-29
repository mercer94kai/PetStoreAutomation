package api.utilities;

import com.github.javafaker.Faker;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class excelUtility {

    String path;

    public FileInputStream fis;
    public FileOutputStream fos;
    public XSSFWorkbook wb;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;

    public static int rowCount;

    public excelUtility(String path){
        this.path=path;
    }

    public int getRowCount(String sheetName) throws IOException {

        fis=new FileInputStream(path);
        wb=new XSSFWorkbook(fis);
        sheet=wb.getSheet(sheetName);
        rowCount=0;

        if(sheet.getLastRowNum()==-1){
           rowCount =sheet.getLastRowNum()+1;
        }
        else {
            rowCount = sheet.getLastRowNum();
        }
        System.out.println("rowCount "+rowCount);
        wb.close();
        fis.close();

        return rowCount;
    }

    public int getCellCount(String sheetName, int rowInd) throws IOException {

        fis=new FileInputStream(path);
        wb=new XSSFWorkbook(fis);
        sheet=wb.getSheet(sheetName);
        int cellCount=0;

        row=sheet.getRow(rowInd);
        if(row.getLastCellNum()==-1){
            cellCount=0;
        }
        else{
            cellCount=row.getLastCellNum();
        }

        System.out.println("cell count "+cellCount);
        wb.close();
        fis.close();

        return cellCount;
    }

    public String getCellData(String sheetName, int rowNum, int cellNum) throws IOException {

        fis=new FileInputStream(path);
        wb=new XSSFWorkbook(fis);
        sheet=wb.getSheet(sheetName);
        row=sheet.getRow(rowNum);
        cell=row.getCell(cellNum);

        DataFormatter formatter=new DataFormatter();
        String data;

        data=formatter.formatCellValue(cell);
        fis.close();
        wb.close();

        return data;

    }

    public void createData(String sheetName, int rowInd, int cellNum, String message) throws IOException {

        fis=new FileInputStream(path);
        wb=new XSSFWorkbook(fis);
        sheet=wb.getSheet(sheetName);

        if (sheet.getRow(rowInd)==null){
            sheet.createRow(rowInd);
        }

        XSSFCell cell=sheet.getRow(rowInd).createCell(cellNum);
        cell.setCellValue(message);

        fos=new FileOutputStream(path);
        wb.write(fos);

        wb.close();
        fis.close();
        fos.close();
    }

    public void createSingleUserData(String sheetName, int row, int cellCount,excelUtility ex) throws IOException {

        Faker faker=new Faker();

        int userId=faker.number().numberBetween(10,50);
        String username=faker.regexify("[a-z]{8}");
        String firstname=faker.name().firstName();
        String lastname=faker.name().lastName();
        String email=faker.internet().emailAddress();
        String password=faker.internet().password();
        String phone=faker.phoneNumber().cellPhone();

        String[] singleUser={String.valueOf(userId),username,firstname,lastname,email,password,phone};

        for (int i=0;i<cellCount;i++){
                ex.createData(sheetName,row,i,singleUser[i]);
        }

    }

//    public static void main(String[] args) throws IOException {
//        excelUtility ex=new excelUtility("C:\\Users\\Kaifa\\Documents\\RestAPITest\\RestAssured2025\\PetStoreAutomation\\src\\test\\resources\\testData\\userData.xlsx");
//        System.out.println(" Current Row No : "+ex.getRowCount("Sheet1"));
//
//        ex.createSingleUserData("Sheet1",rowCount+1,7,ex);
//        System.out.println(" Current Row No : "+ex.getRowCount("Sheet1"));
//    }
}
