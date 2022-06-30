package com.spring_final.SpringFinalProject.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

@Entity @Data @AllArgsConstructor
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int age;
    private String contact;
    private String gender;

    //@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    //public Set<Authority> authorities = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Role> roles = new ArrayList<>();

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Activity> activities = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<ActivityRequest> activityRequests = new HashSet<>();

    public User(int id, String firstname, String lastName, String username, String password, int age, String contact, String gender) {
        this.id = id;
        this.firstName = firstname;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.age = age;
        this.contact = contact;
        this.gender = gender;
    }

    public User(String firstname, String lastName, String username, String password, int age, String contact, String gender) {
        this(0, firstname, lastName, username, password, age, contact, gender);
    }

    public User() {
    }

    /*
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    public Set<ActivityRequest> getActivityRequests() {
        return activityRequests;
    }

    public void setActivityRequests(Set<ActivityRequest> activityRequests) {
        this.activityRequests = activityRequests;
    }*/

    @Override
    public String toString(){
        return firstName + " " + lastName;
    }
}
