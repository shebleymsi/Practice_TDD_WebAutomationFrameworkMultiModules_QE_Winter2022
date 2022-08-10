package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteExcelFile {

    private static final String FILE_NAME = System.getProperty("user.dir") + "/DataTest/TestExcelFile.xlsx";
    //private static final String FILE_NAME = "DataTest/myFile.xlsx";

    static int min = 50;
    static int max = 500;
    //Generate random integer value from 50 to 100
    static int random_integer = (int) (Math.random() * (max - min + 1) + min);
    static String email = "yinyanxe" + random_integer + "@gmail.com";
    static String email2 = "akash" + random_integer + "@gmail.com";
    static String email3 = "akashbatash" + random_integer + "@gmail.com";
    static String username = "akashbatash" + random_integer;

    public static void writeExcel(String fileName) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("studentList");
        Object[][] stDetails = {
                {"Sl", "FirstName", "LastName", "ContactNumber"},
                {"1", "Anika", "islam", "897598759"},
                {"2", "Mahid", "Samad", "997598759"},
                {"2", "Mezba", "Ahmed", "797598759"},
        };
        int rowNum = 0;
        System.out.println("Excel file Created");
        for (Object[] std : stDetails) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : std) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            workbook.write(fileOutputStream);
            workbook.close();

        } catch (FileNotFoundException e) {
            //System.out.println("File not found Exception");
            e.printStackTrace();

        } catch (IOException io) {
            io.printStackTrace();
        }
        System.out.println("Done");
    }

    public static Object[][] writeExcelReturnArrayObjectData(String fileName) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("AccountInfo");
//        Object[][] accountDetails = {
//                {"FirstName(0)", "LastName(1)", "Address(2)", "City(3)", "State(4)", "ZipCode(5)", "PhoneNumber(6)", "birthDay(7)","birthMonth(8)","birthYear(9)","username(10)", "password(11)", "email(12)", "SSN(13)"},
//                { "Aleign", "Negash", "4 bookta faita jai Street", "delon", "VA","00401","310-447-4121",24, 12,1987, "loserforever","ABcDe123", email,"123456789"},
//                { "Easha", "Khanam","4 bookta faita jai Street", "delon", "NY","00402","310-447-4125", 7, 9,1989, "yeaBroo" ,"ABcDe123", email2, "23456788"},
//                { "Simar", "Kaur","4 bookta faita jai Street", "delon", "IL","00403","310-447-4127", 13, 6,1980, username,"ABcDe123", email3, "56789055"},
//
//        };
        Object[][] accountDetails = {
                {"FirstName(0)", "LastName(1)", "Address(2)", "City(3)", "State(4)", "ZipCode(5)", "PhoneNumber(6)","SSN(7)","username(8)", "password(9)", "confirmPassword"},
                { "Aleign", "Negash", "4 bookta faita jai Street", "delon", "VA","00401","310-447-4121","123456789", "loserforever","ABcDe123","ABcDe123" },
                { "Easha", "Khanam","4 bookta faita jai Street", "delon", "NY","00402","310-447-4125","23456788", "yeaBroo" ,"ABcDe123","ABcDe123" },
                { "Simar", "Kaur","4 bookta faita jai Street", "delon", "IL","00403","310-447-4127", "56789055", username,"ABcDe123","ABcDe123" },

        };
        int rowNum = 0;
        System.out.println("Excel file Created");
        for (Object[] std : accountDetails) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : std) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            workbook.write(fileOutputStream);
            workbook.close();

        } catch (IOException e) {
            //System.out.println("File not found Exception");
            e.printStackTrace();

        }
        System.out.println("Done");
        return accountDetails;
    }

}
