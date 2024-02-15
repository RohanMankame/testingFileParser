package com.updownlod.fileupdownlodapi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.List;

public class FileSearchUtil {
    public List<UploadResponse> SearchFileDate(String startDate, String endDate) {

        updateDB searchEntry = new updateDB();
        List<UploadResponse> responseData = searchEntry.searchDBEntryDates(startDate, endDate);
        System.out.println("SEARCHING");
        return responseData;
    }

    public UploadResponse SearchFileClaim(String claimCode) throws IOException, InvalidFormatException {

        updateDB searchEntry = new updateDB();
        String fileCode = searchEntry.searchDBClaimFileName(claimCode);
        UploadResponse response = ResponseDataUtil.ResponseFileData(fileCode);
        System.out.println("SEARCHING");
        return response;
    }


}
