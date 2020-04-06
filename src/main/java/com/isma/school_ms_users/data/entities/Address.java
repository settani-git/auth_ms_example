package com.isma.school_ms_users.data.entities;


import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String zipCode;
    private String street;
    private String city;
    private String country;

    public Address() {
    }

    public Address(String zipCode, String street, String city, String country) {
        this.zipCode = zipCode;
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
