package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class ExcelDataProvider {
    @DataProvider(name = "loginData")
    public String[][] loginData() throws IOException {
        String path = ".\\testData\\login_data.xlsx";
        ExcelUtility excelUtility = new ExcelUtility(path);
        String sheetName = "Sheet1";
        int rowCount = excelUtility.getRowCount(sheetName);
        int cellCount = excelUtility.getCellCount(sheetName, 1);
        String[][] loginData = new String[rowCount][cellCount];

        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < cellCount; j++) {
                loginData[i-1][j] = excelUtility.getCellData(sheetName, i, j);
            }
        }
        return loginData;
    }
}
