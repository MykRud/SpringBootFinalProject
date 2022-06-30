package com.spring_final.SpringFinalProject.repo;

import com.spring_final.SpringFinalProject.model.Activity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ActivityDaoRep extends JpaRepository<Activity, Integer> {

    Activity getByName(String name);

    @Query(
            value = "SELECT *, count(req.activity_id) AS number_of_users FROM activity ac LEFT JOIN ActivityRequest req ON ac.id=req.activity_id where (req.action='Add' AND req.status='Approved') OR req.activity_id IS NULL group by ac.id",
            nativeQuery = true
    )
    List<Activity> findByNumberOfUsers(Pageable pageable);

    @Modifying
    @Query(value = "DELETE FROM activity_users WHERE users_id=:users_id", nativeQuery = true)
    void deleteByUserId(@Param("users_id") int users_id);

}
