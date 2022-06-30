package com.spring_final.SpringFinalProject.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class Activity implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String name;
    private String status;
    private String description;
    private int duration;
    private Date startTime;
    private Date endTime;

    @ManyToOne
    private TypeOfActivity type;

    @ManyToMany(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "activity", cascade=CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<ActivityRequest> activityRequests = new HashSet<>();

    /*public Activity() {
    }

    public Activity(String name, TypeOfActivity type) {
        this.name = name;
        this.type = type;
    }

    public Activity(int id, String name, String status, String description, int duration, Date startTime, Date endTime, TypeOfActivity type, List<User> users) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.description = description;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public TypeOfActivity getType() {
        return type;
    }

    public void setType(TypeOfActivity type) {
        this.type = type;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<ActivityRequest> getActivityRequests() {
        return activityRequests;
    }

    public void setActivityRequests(List<ActivityRequest> activityRequests) {
        this.activityRequests = activityRequests;
    }*/

    @Override
    public String toString(){
        return name;
    }
}
