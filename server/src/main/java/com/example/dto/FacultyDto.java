package com.example.dto;

import com.example.dbmodel.Faculty;

public class FacultyDto {

    private Long id;
    private String shortName;
    private String longName;
    private String website;
    private String logoByteUrl;

    public FacultyDto(Long id, String shortName, String longName, String website,
            String logoByteUrl) {
        this.id = id;
        this.shortName = shortName;
        this.longName = longName;
        this.website = website;
        this.logoByteUrl = logoByteUrl;
    }

    public FacultyDto(Faculty f) {
        this.id = f.getId();
        this.shortName = f.getShortName();
        this.longName = f.getLongName();
        this.website = f.getWebsite();
        this.logoByteUrl = f.getLogoByteUrl();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogoByteUrl() {
        return logoByteUrl;
    }

    public void setLogoByteUrl(String logoByteUrl) {
        this.logoByteUrl = logoByteUrl;
    }

}
