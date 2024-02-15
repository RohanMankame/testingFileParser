package com.updownlod.fileupdownlodapi;


public class UploadResponse {

    //DATA OF FILE/CLAIM
    private String claimNumber;
    private String policyNumber;
    private String closureDate;
    private String insuredName;
    private String repudiationReason;
    private String downlodeUri;



    //GETTERS AND SETTERS
    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getClosureDate() {
        return closureDate;
    }

    public void setClosureDate(String closureDate) {
        this.closureDate = closureDate;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getRepudiationReason() {
        return repudiationReason;
    }

    public void setRepudiationReason(String repudiationReason) {
        this.repudiationReason = repudiationReason;
    }

    public String getDownlodeUri() {
        return downlodeUri;
    }

    public void setDownlodeUri(String downlodeUri) {
        this.downlodeUri = downlodeUri;
    }





    ///////////////////////////////////////////////////////////////////////////////////////////////
    // EXTRA DATA
    private String fileName;

    //private long size;
    private String fileDate;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getFileDate() {
        return fileDate;
    }

    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }


}
