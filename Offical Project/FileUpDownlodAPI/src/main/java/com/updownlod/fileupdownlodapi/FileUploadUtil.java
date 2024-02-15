package com.updownlod.fileupdownlodapi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {


    public static String saveVerificationFile(String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadDirectory = Paths.get("Verification-Upload");

        String fileCode = fileName;

        try(InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadDirectory.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Error Verification upload of file:" + fileName,ioe);
        }
        return fileCode;
    }

    public static String saveFile(String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadDirectory = Paths.get("File-Upload");

        String fileCode = fileName;

        try(InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadDirectory.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Error saving upload the file:" + fileName,ioe);
        }
        return fileCode;
    }

    public static UploadResponse saveFileToDB(String fileCode) throws IOException, InvalidFormatException {
        UploadResponse response = ResponseDataUtil.ResponseFileData(fileCode);
        String claimNum =response.getClaimNumber();
        String policyNum = response.getPolicyNumber();
        String closureDate = response.getClosureDate();
        String insuredName = response.getInsuredName();
        String repudiationReason = response.getRepudiationReason();
        String URL = response.getDownlodeUri();
        String fileName = response.getFileName();

        updateDB newEntry = new updateDB();
        newEntry.updateDBnewEntry(claimNum, policyNum, closureDate, insuredName ,repudiationReason, fileName);

        return response;

    }

}
