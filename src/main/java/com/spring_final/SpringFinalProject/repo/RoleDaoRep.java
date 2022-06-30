package com.spring_final.SpringFinalProject.repo;

import com.spring_final.SpringFinalProject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RoleDaoRep extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}
