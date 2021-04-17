package com.example.dto;

import com.example.dbmodel.Link;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class LinkDto {

    private long id;
    private String link;
    private String description;
    private String modified;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
            .withLocale(Locale.GERMAN).withZone(ZoneId.systemDefault());

    public LinkDto(long id, String link, String description, String modified) {
        this.id = id;
        this.link = link;
        this.description = description;
        this.modified = modified;
    }

    public LinkDto(Link link) {
        this.id = link.getId();
        this.link = link.getLink();
        this.description = link.getDescription();
        this.modified = formatter.format(link.getModified());
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

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

}
