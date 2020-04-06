package com.isma.school_ms_users.data.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "users")
public class AccountUser implements Serializable {
    @Id
    @Column(length = 10)
    private String code;
    @Column(unique = true)
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified_at;
    @Embedded
    private Address address;
    @Embedded
    private Contact contact;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AccountRole> accountRoles = new ArrayList<>();

    public AccountUser(String code) {
        this.code = code;
    }

    public AccountUser(String code, String username, String firstName, String lastName, String password, Date created_at, Address address, Contact contact) {
        this.code = code;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.created_at = created_at;
        this.address = address;
        this.contact = contact;
    }

    public AccountUser() {

    }

    public void addRoleToAccount(AccountRole role) {
        this.accountRoles.add(role);
    }

    public void removeRoleToAccount(AccountRole role) {
        this.accountRoles.remove(role);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setModified_at(Date modified_at) {
        this.modified_at = modified_at;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void setAccountRoles(Collection<AccountRole> accountRoles) {
        this.accountRoles = accountRoles;
    }

    public String getCode() {
        return code;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public Address getAddress() {
        return address;
    }

    public Contact getContact() {
        return contact;
    }

    public Collection<AccountRole> getAccountRoles() {
        return accountRoles;
    }
}
