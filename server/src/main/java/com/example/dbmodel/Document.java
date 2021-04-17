package com.example.dbmodel;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "document")
public class Document implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    @NotNull
    private long id;
    @NotNull
    @Size(max = 50)
    private String name;
    @Column(nullable = true)
    private String description = null;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @NotNull
    private byte[] dataFile;
    @NotNull
    private Instant modified = Instant.now();
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    public Document() {
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

    public byte[] getDataFile() {
        return dataFile;
    }

    public void setDataFile(byte[] dataFile) {
        this.dataFile = dataFile;
    }

    public Instant getModified() {
        return modified;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
