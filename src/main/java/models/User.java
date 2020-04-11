package models;

import interfaces.ICsv;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class User implements ICsv {
    private Integer id;

    public Integer getId() {
        return id;
    }

    private String firstName;
    private String lastName;
    private Date birthday;
    private Country country;
    private String email;
    private Date createdAt;
    private Date updatedAt;

    public User(Integer id, String firstName, String lastName, Date birthday, String country, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.country = Country.valueOf(country);
        this.email = email;
        this.createdAt = new Date();
        this.updatedAt = this.createdAt;
    }

    public User(Integer id, String firstName, String lastName, Date birthday, Country country, String email, Date createdAt, Date updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.country = country;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return LocalDate.now().getYear() - Instant.ofEpochMilli(birthday.getTime()).atZone(ZoneId.systemDefault()).getYear();
    }

    public Country getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", createdAt='" + simpleDateFormat.format(createdAt) + '\'' +
                ", updatedAt='" + simpleDateFormat.format(updatedAt) + '\'' +
                '}';
    }

    @Override
    public String writeToCsv() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return String.format(
                "%d,%s,%s,%s,%s,%s,%s,%s",
                id,
                firstName,
                lastName,
                simpleDateFormat.format(birthday),
                country,
                email,
                simpleDateFormat.format(createdAt),
                simpleDateFormat.format(updatedAt)
        );
    }
}
