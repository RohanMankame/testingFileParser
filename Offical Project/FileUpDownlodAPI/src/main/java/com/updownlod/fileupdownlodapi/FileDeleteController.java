package com.updownlod.fileupdownlodapi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class FileDeleteController {

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //DELETES FILE USING FILENAME
    @DeleteMapping("/deleteFile/{fileCode}")
    public ResponseEntity<?> deleteFile(@PathVariable("fileCode") String fileCode) throws IOException, InvalidFormatException {

        //Uses File Name to delete file
        FileDeleteUtil deleteUtil = new FileDeleteUtil();
        deleteUtil.deleteFile(fileCode);
        return new ResponseEntity<>( "DELETED", HttpStatus.OK);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //DELETES FILE USING CLAIM NUMBER
    @DeleteMapping("/deleteClaim/{claimCode}")
    public ResponseEntity<?> deleteClaim(@PathVariable("claimCode") String claimCode) throws IOException, InvalidFormatException {

        //Gets File Name using Claim Number to search trough existing DB
        updateDB searchEntry = new updateDB();
        String fileCode = searchEntry.searchDBClaimFileName(claimCode);
        UploadResponse response = ResponseDataUtil.ResponseFileData(fileCode);
        String fileName = response.getFileName();

        //Uses File Name to delete file
        FileDeleteUtil deleteUtil = new FileDeleteUtil();
        deleteUtil.deleteFile(fileName);
        return new ResponseEntity<>( "Claim has been DELETED", HttpStatus.OK);
    }
}