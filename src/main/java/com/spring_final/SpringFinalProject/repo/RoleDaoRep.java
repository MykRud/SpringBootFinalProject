package com.spring_final.SpringFinalProject.repo;

import com.spring_final.SpringFinalProject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleDaoRep extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
