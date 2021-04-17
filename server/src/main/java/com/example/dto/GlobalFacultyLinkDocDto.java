package com.example.dto;

import com.example.dbmodel.GlobalLink;
import com.example.dbmodel.GlobalLinkType;

public class GlobalFacultyLinkDocDto {

    private long id;
    private String link;
    private String description;
    private GlobalLinkType globalLinkType;
    private boolean hasFaculty;
    private String faculty;

    public GlobalFacultyLinkDocDto(long id, String link, String description,
            GlobalLinkType globalLinkType, boolean hasFaculty, String faculty) {
        this.id = id;
        this.link = link;
        this.description = description;
        this.globalLinkType = globalLinkType;
        this.hasFaculty = hasFaculty;
        this.faculty = faculty;
    }

    public GlobalFacultyLinkDocDto(GlobalLink gl) {
        this.id = gl.getId();
        this.link = gl.getLink();
        this.description = gl.getDescription();
        this.globalLinkType = gl.getType();
        this.hasFaculty = gl.getFaculty() != null;
        this.faculty = gl.getFaculty() == null
                ? null
                : gl.getFaculty().getShortName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GlobalLinkType getGlobalLinkType() {
        return globalLinkType;
    }

    public void setGlobalLinkType(GlobalLinkType globalLinkType) {
        this.globalLinkType = globalLinkType;
    }

    public boolean isHasFaculty() {
        return hasFaculty;
    }

    public void setHasFaculty(boolean hasFaculty) {
        this.hasFaculty = hasFaculty;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

}
