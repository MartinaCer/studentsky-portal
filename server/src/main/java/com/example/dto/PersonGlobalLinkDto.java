package com.example.dto;

import com.example.dbmodel.GlobalLinkType;
import com.example.dbmodel.PersonGlobalLink;

public class PersonGlobalLinkDto {

    private Long id;
    private String link;
    private String description;
    private GlobalLinkType type;

    public PersonGlobalLinkDto(Long id, String link, String description, GlobalLinkType type) {
        this.id = id;
        this.link = link;
        this.description = description;
        this.type = type;
    }

    public PersonGlobalLinkDto(PersonGlobalLink pgl) {
        this.id = pgl.getId();
        this.link = pgl.getLink();
        this.description = pgl.getDescription();
        this.type = pgl.getType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public GlobalLinkType getType() {
        return type;
    }

    public void setType(GlobalLinkType type) {
        this.type = type;
    }

}
