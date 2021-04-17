package com.example.dto;

public class FileUploadDto {

    private String data;
    private String filename;

    public FileUploadDto(String data, String filename) {
        this.data = data;
        this.filename = filename;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
