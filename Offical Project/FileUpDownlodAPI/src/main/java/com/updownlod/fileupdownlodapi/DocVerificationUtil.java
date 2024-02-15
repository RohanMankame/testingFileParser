package com.updownlod.fileupdownlodapi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DocVerificationUtil {



    public static boolean ClaimVerification(String fileName, MultipartFile multipartFile) throws IOException {
        boolean Verified = false; // initially set to false and only set to true after the bellow check of values in document is correct

        Path uploadDirectory = Paths.get("Verification-Upload");

        String fileCode = fileName;

        try(InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadDirectory.resolve(fileName);

            XSSFWorkbook workbook = new XSSFWorkbook(filePath.toFile());


            boolean repudiationVerification = verifyRepudiation(workbook);
            boolean recordManagementVerification = verifyRecordManagement(workbook);
            boolean cvmVerification = VerifyCVM(workbook);

            if (repudiationVerification && recordManagementVerification && cvmVerification) // If all sheets verified then Verified is true
            {
                Verified = true;
                FileDeleteUtil VerifiedFile = new FileDeleteUtil();
                VerifiedFile.deleteVerificationFile(fileCode);
            }

        } catch (IOException ioe) {
            throw new IOException("Error verifying upload the file:" + fileName,ioe);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }

        return Verified;
    }

    public static boolean verifyRepudiation(XSSFWorkbook workbook){
        XSSFSheet sheet= workbook.getSheet("Repudiation dispatch ");
        if
        (  sheet.getRow(9).getCell(2).getStringCellValue().getClass().getSimpleName().equals("String")
        && DateUtil.isCellDateFormatted(sheet.getRow(10).getCell(2))
        && DateUtil.isCellDateFormatted(sheet.getRow(11).getCell(2))
        && sheet.getRow(12).getCell(2).getNumericCellValue() % 1 == 0
        && sheet.getRow(13).getCell(2).getStringCellValue().getClass().getSimpleName().equals("String")
        && sheet.getRow(14).getCell(2).getStringCellValue().getClass().getSimpleName().equals("String")
        )
        {
            System.out.println("EVERYTHING WORKS IN REP DISPATCH");
        }


        return true;
    }



    public static boolean verifyRecordManagement(XSSFWorkbook workbook){
        XSSFSheet sheet= workbook.getSheet("Record managment ");
        if
        ( sheet.getRow(2).getCell(2).getStringCellValue().getClass().getSimpleName().equals("String")
          && (sheet.getRow(3).getCell(2).getStringCellValue().equals("Yes")
              || sheet.getRow(3).getCell(2).getStringCellValue().equals("No"))
        )
        {
            System.out.println("EVERYTHING WORKS IN RECORD MANAGEMENT");
        }

        return true;
    }

    public static boolean VerifyCVM(XSSFWorkbook workbook){
        XSSFSheet sheet= workbook.getSheet("CVM");
        if
        ((sheet.getRow(10).getCell(2).getStringCellValue().equals("Yes")
                || sheet.getRow(10).getCell(2).getStringCellValue().equals("No"))
        &&(sheet.getRow(11).getCell(2).getStringCellValue().equals("Yes")
                || sheet.getRow(11).getCell(2).getStringCellValue().equals("No"))
        &&(sheet.getRow(12).getCell(2).getStringCellValue().equals("Yes")
                || sheet.getRow(12).getCell(2).getStringCellValue().equals("No"))
        &&(sheet.getRow(13).getCell(2).getStringCellValue().equals("KGI")
                || sheet.getRow(13).getCell(2).getStringCellValue().equals("Insured"))
        &&(sheet.getRow(14).getCell(2).getStringCellValue().equals("Yes")
                || sheet.getRow(14).getCell(2).getStringCellValue().equals("No"))
        &&(sheet.getRow(15).getCell(2).getStringCellValue().equals("Matching")
                || sheet.getRow(15).getCell(2).getStringCellValue().equals("Not Matching"))
        &&(sheet.getRow(16).getCell(2).getStringCellValue().equals("Yes")
                || sheet.getRow(16).getCell(2).getStringCellValue().equals("No"))
        && (sheet.getRow(17).getCell(2).getStringCellValue().getClass().getSimpleName().equals("String"))
        )
        {
            System.out.println("EVERYTHING WORKS IN CVM");
        }

        return true;
    }

}
