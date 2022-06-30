package com.spring_final.SpringFinalProject.repo;

import com.spring_final.SpringFinalProject.model.User;
import org.springframework.data.domain.Page;
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
public interface UserDaoRep extends JpaRepository<User, Integer> {

    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Integer integer);

    User getByUsername(String username);

    @Override
    Page<User> findAll(Pageable pageable);

    @Override
    <S extends User> S save(S entity);

    @Override
    void delete(User entity);

    @Modifying
    @Query(value = "DELETE FROM user_roles WHERE user_id=:userId", nativeQuery = true)
    void deleteAuthorities(@Param("userId") int userId);

    @Override
    void deleteById(Integer integer);
}
