package com.updownlod.fileupdownlodapi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDeleteUtil {


    public Resource deleteFile(String fileCode) throws IOException, InvalidFormatException {


        Path uploadDirectory = Paths.get("File-Upload");
        Path filePath = uploadDirectory.resolve(fileCode);
        //XSSFWorkbook workbook = new XSSFWorkbook(filePath.toFile());
        //XSSFSheet sheet= workbook.getSheet("Repudiation dispatch ");
        //UploadResponse response = new UploadResponse();
        //response.setClaimNumber(String.valueOf((int)sheet.getRow(1).getCell(2).getNumericCellValue()));
        //String claimNum = response.getClaimNumber();


        UploadResponse response = ResponseDataUtil.ResponseFileData(fileCode);
        String claimNum = response.getClaimNumber();


        updateDB deleteEntry = new updateDB();
        deleteEntry.updateDBdeleteEntry(claimNum);

        File f = new File(String.valueOf(filePath));
        if (f.delete()) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("Failed to delete the file");
        }



        return null;
    }

    public Resource deleteVerificationFile(String fileCode) throws IOException {


        Path uploadDirectory = Paths.get("Verification-Upload");
        Path filePath = uploadDirectory.resolve(fileCode);

        File f = new File(String.valueOf(filePath));
        if (f.delete()) {
            System.out.println("Verification File deleted successfully");
        } else {
            System.out.println("Verification Failed to delete the file");
        }

        return null;
    }
}
