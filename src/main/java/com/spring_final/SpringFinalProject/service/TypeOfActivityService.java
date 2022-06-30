package com.spring_final.SpringFinalProject.service;

import com.spring_final.SpringFinalProject.model.TypeOfActivity;
import com.spring_final.SpringFinalProject.repo.TypesOfActivitiesDaoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TypeOfActivityService {

    @Autowired
    private TypesOfActivitiesDaoRep typeDao;

    public void addType(TypeOfActivity type){
        typeDao.save(type);
    }

    public List<TypeOfActivity> getTypes(){
        return typeDao.findAll();
    }

    public TypeOfActivity findType(int id){
        return typeDao.findById(id).get();
    }

    public TypeOfActivity findType(String name){
        return typeDao.getByName(name);
    }

    public TypeOfActivity getType(String name) {
        return typeDao.getByName(name);
    }
}
