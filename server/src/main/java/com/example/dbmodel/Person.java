package com.example.dbmodel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    @NotNull
    private long id;
    @Column(unique = true)
    @NotNull
    @Size(min = 10, max = 12)
    private String birthNumber;
    @NotNull
    @Size(max = 50)
    private String firstname;
    @NotNull
    @Size(max = 50)
    private String lastname;
    @NotNull
    @Size(max = 50)
    private String street;
    @NotNull
    @Size(max = 10)
    private String houseNumber;
    @NotNull
    @Size(max = 50)
    private String town;
    @NotNull
    @Size(max = 10)
    private String zipcode;
    @NotNull
    private LocalDate birthdate;
    @Column(unique = true)
    @NotNull
    @Size(max = 30)
    private String ISICnumber;
    @Column(unique = true)
    @NotNull
    @Size(min = 8, max = 10)
    private String IDcardNumber;
    @Column(nullable = true)
    @Size(max = 50)
    private String DLnumber = null;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(nullable = true)
    private String photoByteUrl = null;

    @OneToMany(targetEntity = Contact.class, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE},
            fetch = FetchType.LAZY, orphanRemoval = true, mappedBy="person")
    private List<Contact> contacts = new ArrayList<>();

    @OneToMany(targetEntity = Document.class, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE},
            fetch = FetchType.LAZY, orphanRemoval = true, mappedBy="person")
    private List<Document> documents = new ArrayList<>();

    @OneToMany(targetEntity = Link.class, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE},
            fetch = FetchType.LAZY, orphanRemoval = true, mappedBy="person")
    private List<Link> links = new ArrayList<>();
    
    @OneToMany(targetEntity = PersonGlobalLink.class, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE},
            fetch = FetchType.LAZY, orphanRemoval = true, mappedBy="person")
    private List<PersonGlobalLink> personGlobalLinks = new ArrayList<>();

    public Person() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBirthNumber() {
        return birthNumber;
    }

    public void setBirthNumber(String birthNumber) {
        this.birthNumber = birthNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getISICnumber() {
        return ISICnumber;
    }

    public void setISICnumber(String ISICnumber) {
        this.ISICnumber = ISICnumber;
    }

    public String getIDcardNumber() {
        return IDcardNumber;
    }

    public void setIDcardNumber(String IDcardNumber) {
        this.IDcardNumber = IDcardNumber;
    }

    public String getDLnumber() {
        return DLnumber;
    }

    public void setDLnumber(String DLnumber) {
        this.DLnumber = DLnumber;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getPhotoByteUrl() {
        return photoByteUrl;
    }

    public void setPhotoByteUrl(String photoByteUrl) {
        this.photoByteUrl = photoByteUrl;
    }

    public List<PersonGlobalLink> getPersonGlobalLinks() {
        return personGlobalLinks;
    }

    public void setPersonGlobalLinks(List<PersonGlobalLink> personGlobalLinks) {
        this.personGlobalLinks = personGlobalLinks;
    }

}
