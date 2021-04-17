package com.example.dbmodel;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "faculty")
public class Faculty implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    @NotNull
    private long id;
    @NotNull
    @Size(max = 10)
    private String shortName;
    @Size(max = 50)
    private String longName;
    @NotNull
    @Size(max = 50)
    private String website;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(nullable = true)
    private String logoByteUrl = null;

    public Faculty() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
