package com.isma.school_ms_users.data.dto;

import com.isma.school_ms_users.data.entities.AccountUser;
import com.isma.school_ms_users.data.entities.Address;
import com.isma.school_ms_users.data.entities.Contact;

import java.util.Date;

public class AccountUserDTO {
    private String code;
    private String username;
    private String firstName;
    private String lastName;
    private Date created_at;
    private Date modified_at;
    private Address address;
    private Contact contact;

    public AccountUserDTO() {
    }

    public AccountUserDTO(AccountUser accountUser) {
        this.code = accountUser.getCode();
        this.username = accountUser.getUsername();
        this.firstName = accountUser.getFirstName();
        this.lastName = accountUser.getLastName();
        this.created_at = accountUser.getCreated_at();
        this.modified_at = accountUser.getModified_at();
        this.address = accountUser.getAddress();
        this.contact = accountUser.getContact();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public void setModified_at(Date modified_at) {
        this.modified_at = modified_at;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}

