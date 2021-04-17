package com.example.dto;

import com.example.dbmodel.Document;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class DocumentDto {

    private long id;
    private String name;
    private String description;
    private String modified;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
            .withLocale(Locale.GERMAN).withZone(ZoneId.systemDefault());

    public DocumentDto(long id, String name, String description, String modified) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.modified = modified;
    }

    public DocumentDto(Document document) {
        this.id = document.getId();
        this.name = document.getName();
        this.description = document.getDescription();
        this.modified = formatter.format(document.getModified());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

}
