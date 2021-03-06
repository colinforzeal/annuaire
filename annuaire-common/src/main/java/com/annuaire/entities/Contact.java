package com.annuaire.entities;

import com.annuaire.enums.Sex;


import java.sql.Date;

public class Contact {
    private Long id;
    private String firstName = "";
    private String lastName = "";
    private String middleName = "";
    private Date dateOfBirth;
    private Sex sex = Sex.UNKNOWN;
    private String citizenship = "";
    private String maritalStatus = "";
    private String website = "";
    private String email = "";
    private String placeOfWork = "";
    private String photoPath;
    private String country = "";
    private String city = "";
    private String address = "";
    private String zip = "";

    public Contact(Long id, String firstName, String lastName, String middleName, Date dateOfBirth, Sex sex, String citizenship, String maritalStatus, String website, String email, String placeOfWork, String photoPath, String country, String city, String address, String zip) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.citizenship = citizenship;
        this.maritalStatus = maritalStatus;
        this.website = website;
        this.email = email;
        this.placeOfWork = placeOfWork;
        this.photoPath = photoPath;
        this.country = country;
        this.city = city;
        this.address = address;
        this.zip = zip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Sex getSex() {
        return sex;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getWebsite() {
        return website;
    }

    public String getEmail() {
        return email;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
