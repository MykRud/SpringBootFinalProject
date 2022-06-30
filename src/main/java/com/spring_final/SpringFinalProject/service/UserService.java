package com.spring_final.SpringFinalProject.service;

import com.spring_final.SpringFinalProject.model.Role;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private UserDaoRep userDao;
    @Autowired
    private RoleDaoRep roleDao;
    @Autowired
    private ActivityDaoRep activityDao;
    @Autowired
    private ActivityRequestDaoRep requestDao;
    @Autowired
    private TypesOfActivitiesDaoRep typeDao;
    private final PasswordEncoder passwordEncoder;

    public void addUser(User user){
        log.info("Saving new user {} to the database", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        User foundUser = getUser(user.getUsername());
        if(foundUser != null)
            addRoleToUser(foundUser.getUsername(), "USER");
    }

    public Role saveRole(Role role){
        log.info("Saving new role {} to the database", role.getName());
        return roleDao.save(role);
    }

    public User getUser(int id){
        return userDao.findById(id).get();
    }

    public void addRoleToUser(String username, String roleName){
        log.info("Adding role {} to user {}", roleName, username);
        User user = userDao.getByUsername(username);
        Role role = roleDao.findByName(roleName);
        user.getRoles().add(role);
    }

    public List<Role> getRoles(){
        return roleDao.findAll();
    }

    public Role getRole(String name){
        return roleDao.findByName(name);
    }

    public User getUser(String username){
        log.info("Fetching user {}", username);
        return userDao.getByUsername(username);
    }

    public List<User> getUsers(){
        log.info("Fetching all users");
        return userDao.findAll();
    }

    public void deleteUser(int id){
        activityDao.deleteByUserId(id);
        userDao.deleteAuthorities(id);
        userDao.deleteById(id);
    }

    public void updateProfile(User user){
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        userDao.save(user);
    }

    public void updateUser(User user){
       // user.setAuthorities(foundUser.getAuthorities());
        userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getByUsername(username);

        if(user == null)
            throw new UsernameNotFoundException("User is not found");

        return new UserPrincipal(user);
    }

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getByUsername(username);
        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else{
            log.info("User found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }*/

}
