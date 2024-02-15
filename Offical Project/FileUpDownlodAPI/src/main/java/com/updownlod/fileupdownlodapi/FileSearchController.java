package com.updownlod.fileupdownlodapi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class FileSearchController {

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //SEARCH IN-BETWEEN EXACT DATE RANGE, RETURNS LIST OF JSON CLAIMS
    @GetMapping("/SearchDates/{startDate}/{endDate}")
    public ResponseEntity<?> SearchFileDates(@PathVariable("startDate") String startDate,@PathVariable("endDate") String endDate ) throws IOException {

        System.out.println("Searching files");
        FileSearchUtil SearchUtil = new FileSearchUtil();

        List<UploadResponse> responseData = SearchUtil.SearchFileDate( startDate, endDate);
        return new ResponseEntity<>( responseData, HttpStatus.OK);
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //SEARCH ON EXACT DATE, RETURNS LIST OF JSON CLAIMS
    @GetMapping("/SearchDate/{Date}")
    public ResponseEntity<?> SearchFileDate(@PathVariable("Date") String Date) throws IOException {

        System.out.println("Searching files");
        FileSearchUtil SearchUtil = new FileSearchUtil();
        String startDate = Date;
        String endDate = Date;

        List<UploadResponse> responseData = SearchUtil.SearchFileDate( startDate, endDate);
        return new ResponseEntity<>( responseData, HttpStatus.OK);
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //SEARCH ON EXACT CLAIM DETAILS, RETURNS EXACT CLAIM JSON DATA
    @GetMapping("/SearchClaim/{Claim}")
    public ResponseEntity<?> SearchFileClaim(@PathVariable("Claim") String claimNumber) throws IOException, InvalidFormatException {

        System.out.println("Searching file");
        FileSearchUtil SearchUtil = new FileSearchUtil();

        UploadResponse responseData = SearchUtil.SearchFileClaim( claimNumber);
        return new ResponseEntity<>( responseData, HttpStatus.OK);
    }
}