package com.spring_final.SpringFinalProject.service;

import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.model.ActivityRequest;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.repo.ActivityDaoRep;
import com.spring_final.SpringFinalProject.repo.ActivityRequestDaoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class ActivityRequestService {

    @Autowired
    private ActivityRequestDaoRep requestDao;
    @Autowired
    private ActivityDaoRep activityDao;

    public void addRequest(ActivityRequest request){
        requestDao.save(request);
    }

    public ActivityRequest getRequest(int id){
        return requestDao.findById(id).get();
    }

    public int getNumberOfRequests(){
        return (int)requestDao.count();
    }

    //public List<ActivityRequest> getRequestsInLimit(int size, int page){
      //  return requestDao.getRequestsInLimit(size, page);
    //}

    public void makeAddRequest(User user, Activity activity){
        List<ActivityRequest> requests = requestDao.findByUserIdAndActivityId(user.getId(), activity.getId());

        if(!requests.isEmpty()) {
            for (ActivityRequest currentRequest : requests) {
                if (!currentRequest.getAction().equals("Add") && !currentRequest.getStatus().equals("Rejected"))
                    return;
            }
        }

        if(activity.getStatus().equals("Completed")){
            return;
        }
        else if(activity.getStatus().equals("Active")){
            return;
        }
        ActivityRequest request = new ActivityRequest();

        request.setUser(user);
        request.setActivity(activity);
        request.setAction("Add");
        request.setStatus("Pending");

        requestDao.save(request);
    }

    @Transactional
    public void makeCompleteRequest(User user, Activity activity) {

        List<ActivityRequest> requests = requestDao.findByUserIdAndActivityId(user.getId(), activity.getId()); // Error is here!!!

        if(requests.isEmpty())
            return;
        for(ActivityRequest currentRequest : requests){
            if(currentRequest.getAction().equals("Complete") && !currentRequest.getStatus().equals("Rejected")
                    || currentRequest.getAction().equals("Add") && currentRequest.getStatus().equals("Pending"))
                return;
        }

        if(activity.getStatus().equals("Completed")){
            return;
        }
        else if(activity.getStatus().equals("Pending"))
            return;
        ActivityRequest request = new ActivityRequest();
        request.setUser(user);
        request.setActivity(activity);
        request.setAction("Complete");
        request.setStatus("Pending");

        requestDao.save(request);

    }

    public void approveRequest(ActivityRequest request){
        Activity activity = request.getActivity();
        User user = request.getUser();

        String status = activity.getStatus();

        if(status.equals("Pending")){
            Timestamp startTime = Timestamp.valueOf(LocalDateTime.now());
            activity.setStartTime(new Date(startTime.getTime()));
            activity.setStatus("Active");
            activity.getUsers().add(user);
            user.getActivities().add(activity);
            request.setStatus("Approved");

            activityDao.save(activity);
        } else if(status.equals("Active")){
            activity.getUsers().add(user);
            user.getActivities().add(activity);
            request.setStatus("Approved");

            activityDao.save(activity);
        } else if(status.equals("Completed")){
            request.setStatus("Rejected");
        }

        requestDao.save(request);
    }

    public void completeRequest(ActivityRequest request){
        Activity activity = request.getActivity();
        User user = request.getUser();

        String status = activity.getStatus();

        if(status.equals("Pending")){
            return;
        } else if(status.equals("Active")){
            Timestamp startTime = Timestamp.valueOf(LocalDateTime.now());
            activity.setEndTime(new Date(startTime.getTime()));
            activity.setStatus("Completed");
            request.setStatus("Approved");

            activityDao.save(activity);
        } else if(status.equals("Completed")){
            request.setStatus("Rejected");
        }

        requestDao.save(request);
    }

    public void rejectRequest(ActivityRequest request){
        request.setStatus("Rejected");
        requestDao.save(request);
    }

    public List<ActivityRequest> getRequests() {
        return requestDao.findAll();
    }

    public List<ActivityRequest> getRequestsInLimit(int page, int size) {
        Pageable pages = PageRequest.of(page, size, Sort.by("id").descending());
        return requestDao.findAll(pages).toList();

    }

    public ActivityRequestDaoRep getRequestDao() {
        return requestDao;
    }

    public void setRequestDao(ActivityRequestDaoRep requestDao) {
        this.requestDao = requestDao;
    }

    public ActivityDaoRep getActivityDao() {
        return activityDao;
    }

    public void setActivityDao(ActivityDaoRep activityDao) {
        this.activityDao = activityDao;
    }
}
