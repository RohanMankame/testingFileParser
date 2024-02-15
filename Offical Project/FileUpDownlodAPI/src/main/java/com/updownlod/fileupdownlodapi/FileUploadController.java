package com.updownlod.fileupdownlodapi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class FileUploadController {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //SAVES FILE SELECTED

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file")MultipartFile multipartFile) throws IOException, InvalidFormatException {

        //GET FILE NAME FROM MULTIPART FILE SELECTED
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        //VERIFY FILE, Verified WILL BE TRUE IF ALL DETAILS IN DOCUMENT ARE OF CORRECT DATATYPE AND FORMAT,Verified WILL BE FALSE OTHERWISE
        FileUploadUtil.saveVerificationFile(fileName, multipartFile);
        boolean Verified = DocVerificationUtil.ClaimVerification(fileName, multipartFile);

        //CHECK, IF Verified IS TRUE PROCEED WITH SAVING DOC, IF Verified IS FALSE SKIP SAVE AND RETURN NO JSON
        if (Verified) {
            String fileCode = FileUploadUtil.saveFile(fileName, multipartFile);
            UploadResponse response = FileUploadUtil.saveFileToDB(fileCode);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } else //Verified is FALSE
        {
            System.out.println("Claim document not filled out correctly please redo and try submit again");
            return new ResponseEntity<>("Document Filled out incorrectly", HttpStatus.OK);
        }
    }
}

