package com.updownlod.fileupdownlodapi;

//import jakarta.annotation.Resource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class FileDownloadController {

    @GetMapping("/downloadFile/{fileCode}")
        public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) throws IOException {
        FileDownloadUtil downloadUtil = new FileDownloadUtil();
        Resource resource = null;

        System.out.println("Downloading file");
        try {
             resource = downloadUtil.getFileAsResource(fileCode);
        } catch (IOException e) {
            return  ResponseEntity.internalServerError().build();
        }

        if (resource == null){
            return new ResponseEntity<>("File not found ", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename =\"" + resource.getFilename() + "\"";


        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);

    }


    @GetMapping("/downloadClaim/{claimCode}")
    public ResponseEntity<?> downloadClaim(@PathVariable("claimCode") String claimCode) throws IOException {
        FileDownloadUtil downloadUtil = new FileDownloadUtil();
        Resource resource = null;
        updateDB searchEntry = new updateDB();
        String fileCode = searchEntry.searchDBClaimFileName(claimCode);

        System.out.println("Downloading file");
        try {
            resource = downloadUtil.getFileAsResource(fileCode);
        } catch (IOException e) {
            return  ResponseEntity.internalServerError().build();
        }

        if (resource == null){
            return new ResponseEntity<>("File not found ", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename =\"" + resource.getFilename() + "\"";


        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);

    }
}
