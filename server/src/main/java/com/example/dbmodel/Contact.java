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
import javax.validation.constraints.Size;

@Entity
@Table(name = "contact")
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    @NotNull
    private long id;
    @NotNull
    @Size(max = 50)
    private String contactValue;
    @Column(nullable = true)
    private String description = null;
    @NotNull
    private boolean contactOnPerson;
    @Convert(converter = ContactTypeConverter.class)
    @NotNull
    private ContactType type;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    public Contact() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isContactOnPerson() {
        return contactOnPerson;
    }

    public void setContactOnPerson(boolean contactOnPerson) {
        this.contactOnPerson = contactOnPerson;
    }

    public String getContactValue() {
        return contactValue;
    }

    public void setContactValue(String contactValue) {
        this.contactValue = contactValue;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
