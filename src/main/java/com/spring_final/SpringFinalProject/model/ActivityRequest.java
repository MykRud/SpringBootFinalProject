package com.spring_final.SpringFinalProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "ActivityRequest") @Data @NoArgsConstructor @AllArgsConstructor
public class ActivityRequest implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Activity activity;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private String action;
    private String status;

   /* public ActivityRequest(int id, Activity activity, User user, String action, String status) {
        this.id = id;
        this.activity = activity;
        this.user = user;
        this.action = action;
        this.status = status;
    }

    public ActivityRequest(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }*/

    @Override
    public String toString(){
        return user.getUsername() + " - " + activity.getName() + " - "
                + activity + " - " + status;
    }
}
