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

    @Override
    List<ActivityRequest> findAll();

    @Override
    Page<ActivityRequest> findAll(Pageable pageable);

    @Override
    <S extends ActivityRequest> S save(S entity);

    @Override
    Optional<ActivityRequest> findById(Integer integer);

    @Query("FROM ActivityRequest WHERE user_id = :userId AND activity_id = :activityId")
    List<ActivityRequest> findByUserIdAndActivityId(@Param("userId") Integer userId, @Param("activityId") Integer activityId);

    @Query("FROM ActivityRequest WHERE user_id = :userId")
    List<ActivityRequest> findByUserId(@Param("userId") Integer userId);

    @Query("FROM ActivityRequest WHERE activity_id = :activityId")
    List<ActivityRequest> findByActivityId(@Param("activityId") Integer activityId);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(ActivityRequest entity);

}
