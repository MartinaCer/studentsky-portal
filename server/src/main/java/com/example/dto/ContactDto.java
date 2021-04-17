package com.example.dto;

import com.example.dbmodel.Contact;
import com.example.dbmodel.ContactType;
import java.util.Objects;

public class ContactDto {

    private long id;
    private String contactValue;
    private String description;
    private ContactType type;
    private boolean personContact;

    public ContactDto(long id, String contactValue, String description, ContactType type, boolean personContact) {
        this.id = id;
        this.contactValue = contactValue;
        this.description = description;
        this.type = type;
        this.personContact = personContact;
    }

    public ContactDto(Contact contact) {
        this.id = contact.getId();
        this.contactValue = contact.getContactValue();
        this.description = contact.getDescription();
        this.type = contact.getType();
        this.personContact = contact.isContactOnPerson();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContactValue() {
        return contactValue;
    }

    public void setContactValue(String contactValue) {
        this.contactValue = contactValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public boolean isPersonContact() {
        return personContact;
    }

    public void setPersonContact(boolean personContact) {
        this.personContact = personContact;
    }

    @Override
    public String toString() {
        return "ContactDto{" + "id=" + id + ", contactValue=" + contactValue + ", description="
                + description + ", type=" + type + ", personContact=" + personContact + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ContactDto other = (ContactDto) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.personContact != other.personContact) {
            return false;
        }
        if (!Objects.equals(this.contactValue, other.contactValue)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

}
