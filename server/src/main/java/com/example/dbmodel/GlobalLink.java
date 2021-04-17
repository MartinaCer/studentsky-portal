package com.example.dbmodel;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "globallink")
public class GlobalLink implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    @NotNull
    private long id;
    @NotNull
    private String link;
    @NotNull
    private String description;
    @Convert(converter = GlobalLinkTypeConverter.class)
    @NotNull
    private GlobalLinkType type;
    @NotNull
    private boolean fileLink;
    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    private Faculty faculty;

    public GlobalLink() {
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

    public GlobalLinkType getType() {
        return type;
    }

    public void setType(GlobalLinkType type) {
        this.type = type;
    }

    public boolean isFileLink() {
        return fileLink;
    }

    public void setFileLink(boolean fileLink) {
        this.fileLink = fileLink;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

}
