package com.updownlod.fileupdownlodapi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResponseDataUtil {

    public static UploadResponse ResponseFileData(String fileCode) throws IOException, InvalidFormatException {

        //System.out.println("this is the OUTPUT: "+fileCode);
        UploadResponse response = new UploadResponse();
        if (fileCode != null) {
            Path uploadDirectory = Paths.get("File-Upload");
            Path filePath = uploadDirectory.resolve(fileCode);

            //Get date when the document was originally uploaded into File-Upload
            String creationTime = Files.getAttribute(Path.of(String.valueOf(filePath)), "creationTime").toString();
            System.out.println("creationTime:" + creationTime);
            String dateFile = creationTime.split("T")[0];

            //Open exel doc where data is held, used to access and set attributes
            //UploadResponse response = new UploadResponse();
            XSSFWorkbook workbook = new XSSFWorkbook(filePath.toFile());
            XSSFSheet sheet = workbook.getSheet("Repudiation dispatch ");

            //Reformat date into Date format (exel XSSF Date format is not parsable, so we must reformat into parsable format)
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sheet.getRow(10).getCell(2).getDateCellValue();

            //Set all Attributes for type UploadResponse
            response.setClaimNumber(String.valueOf((int) sheet.getRow(1).getCell(2).getNumericCellValue()));
            response.setPolicyNumber(String.valueOf((int) sheet.getRow(3).getCell(2).getNumericCellValue()));
            response.setClosureDate(df.format(date));
            response.setInsuredName(sheet.getRow(6).getCell(2).getStringCellValue());
            response.setRepudiationReason(sheet.getRow(9).getCell(2).getStringCellValue());
            response.setDownlodeUri("/downloadFile/" + fileCode);
            response.setFileName(fileCode);
            response.setFileDate(dateFile);

            return response;
        }
        else
        {
            return response;
        }
    }
}
