package com.employe.api.mongo.dao;
 
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
 
import com.employe.api.model.Employee;
 
@Repository
public interface Employeedao extends MongoRepository<Employee, String> {

}