package com.example.dto;

import com.example.dbmodel.Person;

public class PersonDto {

    private long id;
    private String birthNumber;
    private String firstname;
    private String lastname;
    private String street;
    private String houseNumber;
    private String town;
    private String zipcode;
    private String birthdate;
    private String ISICnumber;
    private String IDcardNumber;
    private String DLnumber;
    private String photoByteUrl;

    public PersonDto() {
    }

    public PersonDto(Person person, boolean forAdmin) {
        this.id = person.getId();
        this.birthNumber = person.getBirthNumber();
        this.firstname = person.getFirstname();
        this.lastname = person.getLastname();
        this.street = person.getStreet();
        this.houseNumber = person.getHouseNumber();
        this.town = person.getTown();
        this.zipcode = person.getZipcode();
        this.birthdate = person.getBirthdate().toString();
        this.ISICnumber = person.getISICnumber();
        this.IDcardNumber = person.getIDcardNumber();
        this.DLnumber = person.getDLnumber();
        this.photoByteUrl = forAdmin
                ? ""
                : person.getPhotoByteUrl();
    }

    public PersonDto(long id, String birthNumber, String firstname, String lastname, 
            String street, String houseNumber, String town, String zipcode, 
            String birthdate, String ISICnumber, String IDcardNumber, 
            String DLnumber, String photoByteUrl) {
        this.id = id;
        this.birthNumber = birthNumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.street = street;
        this.houseNumber = houseNumber;
        this.town = town;
        this.zipcode = zipcode;
        this.birthdate = birthdate;
        this.ISICnumber = ISICnumber;
        this.IDcardNumber = IDcardNumber;
        this.DLnumber = DLnumber;
        this.photoByteUrl = photoByteUrl;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
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

    public String getPhotoByteUrl() {
        return photoByteUrl;
    }

    public void setPhotoByteUrl(String photoByteUrl) {
        this.photoByteUrl = photoByteUrl;
    }

}
