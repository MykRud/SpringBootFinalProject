package com.spring_final.SpringFinalProject.service;

import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.repo.ActivityDaoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class ActivityService {

    @Autowired
    private ActivityDaoRep activityDao;

    public void addActivity(Activity activity){
        activityDao.save(activity);
    }

    public Activity getActivity(int id){
        return activityDao.findById(id).get();
    }

    public Activity getActivity(String name){
        return activityDao.getByName(name);
    }

    public List<Activity> getActivities(){
        return activityDao.findAll();
    }

    public int getNumberOfActivities(){
        return (int) activityDao.count();
    }

   // public List<Activity> getActivitiesInLimit(int size, int page){
     //   return activityDao.getActivitiesInLimit(size, page);
   // }

   // public List<Activity> getActivitiesInLimitByName(int size, int page) {
     //   return activityDao.getActivitiesInLimitByName(size, page);
   // }

   // public List<Activity> getActivitiesInLimitByType(int size, int page) {
     //   return activityDao.getActivitiesInLimitByType(size, page);
   // }

    @Transactional
    public void deleteActivity(int id){
        activityDao.deleteById(id);
    }

    public void takeActivityTime(int id, User user, int duration){
        Activity activity = activityDao.findById(id).get();
        if(activity.getStatus().equals("Active")){
            activity.setDuration(duration);
            activityDao.save(activity);
        }
    }

    @Transactional
    public void updateActivity(Activity activity){
        activityDao.save(activity);
    }

    public List<Activity> getActivitiesInLimitByName(int size, int page) {
        Pageable pages = PageRequest.of(page, size, Sort.by("name"));
        return activityDao.findAll(pages).toList();
    }

    public List<Activity> getActivitiesInLimitByType(int size, int page) {
        Pageable pages = PageRequest.of(page, size, Sort.by("type_id"));
        return activityDao.findAll(pages).toList();
    }

    public List<Activity> getActivitiesInLimitByNumberOfUsers(int size, int page) {
        Pageable pages = PageRequest.of(page, size, Sort.by("number_of_users").descending());
        return activityDao.findByNumberOfUsers(pages);
    }
}
