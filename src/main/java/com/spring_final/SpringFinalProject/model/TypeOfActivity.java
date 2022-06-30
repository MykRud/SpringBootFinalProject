package com.spring_final.SpringFinalProject.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "typeofactivity")
public class TypeOfActivity implements Serializable{
    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "type", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Activity> activities = new HashSet<>();

    /*public TypeOfActivity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TypeOfActivity(String name) {
        this.name = name;
    }

    public TypeOfActivity() {
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

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }*/

    @Override
    public String toString(){
        return name;
    }
}
