package com.example.dbmodel;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "persongloballink")
public class PersonGlobalLink implements Serializable {

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
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    public PersonGlobalLink() {
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
