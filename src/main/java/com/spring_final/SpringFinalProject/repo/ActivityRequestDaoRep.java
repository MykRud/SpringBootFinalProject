package com.spring_final.SpringFinalProject.repo;

import com.spring_final.SpringFinalProject.model.ActivityRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ActivityRequestDaoRep extends JpaRepository<ActivityRequest, Integer> {

    @Query("FROM ActivityRequest WHERE user_id = :userId AND activity_id = :activityId")
    List<ActivityRequest> findByUserIdAndActivityId(@Param("userId") Integer userId, @Param("activityId") Integer activityId);

}
