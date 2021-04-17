package com.example.dto;

import java.util.Collections;
import java.util.List;

public class PersonDetailDto {

    private String photoByteUrl;
    private List<String> phoneNumbers;
    private List<String> emails;
    private List<String> bankNumbers;

    public PersonDetailDto(String photoByteUrl, List<String> phoneNumbers, List<String> emails, List<String> bankNumbers) {
        this.photoByteUrl = photoByteUrl;
        this.phoneNumbers = phoneNumbers == null ? Collections.emptyList() : phoneNumbers;
        this.emails = emails == null ? Collections.emptyList() : emails;
        this.bankNumbers = bankNumbers == null ? Collections.emptyList() : bankNumbers;
    }

    public String getPhotoByteUrl() {
        return photoByteUrl;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public List<String> getEmails() {
        return emails;
    }

    public List<String> getBankNumbers() {
        return bankNumbers;
    }

}
